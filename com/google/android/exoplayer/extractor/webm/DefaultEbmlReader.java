// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.extractor.ExtractorInput;
import java.util.Stack;

final class DefaultEbmlReader implements EbmlReader
{
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private final Stack<DefaultEbmlReader$MasterElement> masterElementsStack;
    private EbmlReaderOutput output;
    private final byte[] scratch;
    private final VarintReader varintReader;
    
    DefaultEbmlReader() {
        this.scratch = new byte[8];
        this.masterElementsStack = new Stack<DefaultEbmlReader$MasterElement>();
        this.varintReader = new VarintReader();
    }
    
    private long maybeResyncToNextLevel1Element(final ExtractorInput extractorInput) {
        extractorInput.resetPeekPosition();
        int unsignedVarintLength;
        int n;
        while (true) {
            extractorInput.peekFully(this.scratch, 0, 4);
            unsignedVarintLength = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
            if (unsignedVarintLength != -1 && unsignedVarintLength <= 4) {
                n = (int)VarintReader.assembleVarint(this.scratch, unsignedVarintLength, false);
                if (this.output.isLevel1Element(n)) {
                    break;
                }
            }
            extractorInput.skipFully(1);
        }
        extractorInput.skipFully(unsignedVarintLength);
        return n;
    }
    
    private double readFloat(final ExtractorInput extractorInput, final int n) {
        final long integer = this.readInteger(extractorInput, n);
        if (n == 4) {
            return Float.intBitsToFloat((int)integer);
        }
        return Double.longBitsToDouble(integer);
    }
    
    private long readInteger(final ExtractorInput extractorInput, final int n) {
        int i = 0;
        extractorInput.readFully(this.scratch, 0, n);
        long n2 = 0L;
        while (i < n) {
            n2 = (n2 << 8 | (this.scratch[i] & 0xFF));
            ++i;
        }
        return n2;
    }
    
    private String readString(final ExtractorInput extractorInput, final int n) {
        if (n == 0) {
            return "";
        }
        final byte[] array = new byte[n];
        extractorInput.readFully(array, 0, n);
        return new String(array);
    }
    
    @Override
    public void init(final EbmlReaderOutput output) {
        this.output = output;
    }
    
    @Override
    public boolean read(final ExtractorInput extractorInput) {
        Assertions.checkState(this.output != null);
        while (this.masterElementsStack.isEmpty() || extractorInput.getPosition() < this.masterElementsStack.peek().elementEndPosition) {
            if (this.elementState == 0) {
                long n;
                if ((n = this.varintReader.readUnsignedVarint(extractorInput, true, false, 4)) == -2L) {
                    n = this.maybeResyncToNextLevel1Element(extractorInput);
                }
                if (n == -1L) {
                    return false;
                }
                this.elementId = (int)n;
                this.elementState = 1;
            }
            if (this.elementState == 1) {
                this.elementContentSize = this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                this.elementState = 2;
            }
            final int elementType = this.output.getElementType(this.elementId);
            switch (elementType) {
                default: {
                    throw new ParserException("Invalid element type " + elementType);
                }
                case 1: {
                    final long position = extractorInput.getPosition();
                    this.masterElementsStack.add(new DefaultEbmlReader$MasterElement(this.elementId, this.elementContentSize + position, null));
                    this.output.startMasterElement(this.elementId, position, this.elementContentSize);
                    this.elementState = 0;
                    return true;
                }
                case 2: {
                    if (this.elementContentSize > 8L) {
                        throw new ParserException("Invalid integer size: " + this.elementContentSize);
                    }
                    this.output.integerElement(this.elementId, this.readInteger(extractorInput, (int)this.elementContentSize));
                    this.elementState = 0;
                    return true;
                }
                case 5: {
                    if (this.elementContentSize != 4L && this.elementContentSize != 8L) {
                        throw new ParserException("Invalid float size: " + this.elementContentSize);
                    }
                    this.output.floatElement(this.elementId, this.readFloat(extractorInput, (int)this.elementContentSize));
                    this.elementState = 0;
                    return true;
                }
                case 3: {
                    if (this.elementContentSize > 2147483647L) {
                        throw new ParserException("String element size: " + this.elementContentSize);
                    }
                    this.output.stringElement(this.elementId, this.readString(extractorInput, (int)this.elementContentSize));
                    this.elementState = 0;
                    return true;
                }
                case 4: {
                    this.output.binaryElement(this.elementId, (int)this.elementContentSize, extractorInput);
                    this.elementState = 0;
                    return true;
                }
                case 0: {
                    extractorInput.skipFully((int)this.elementContentSize);
                    this.elementState = 0;
                    continue;
                }
            }
        }
        this.output.endMasterElement(this.masterElementsStack.pop().elementId);
        return true;
    }
    
    @Override
    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }
}
