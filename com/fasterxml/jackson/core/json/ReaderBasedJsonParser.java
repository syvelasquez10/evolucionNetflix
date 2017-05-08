// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser$Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.Reader;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;

public class ReaderBasedJsonParser extends ParserBase
{
    protected static final int[] _icLatin1;
    protected boolean _bufferRecyclable;
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected int _nameStartCol;
    protected long _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    
    static {
        _icLatin1 = CharTypes.getInputCodeLatin1();
    }
    
    public ReaderBasedJsonParser(final IOContext ioContext, final int n, final Reader reader, final ObjectCodec objectCodec, final CharsToNameCanonicalizer symbols) {
        super(ioContext, n);
        this._reader = reader;
        this._inputBuffer = ioContext.allocTokenBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._objectCodec = objectCodec;
        this._symbols = symbols;
        this._hashSeed = symbols.hashSeed();
        this._bufferRecyclable = true;
    }
    
    private String _handleOddName2(int currentLength, int n, final int[] array) {
        this._textBuffer.resetWithShared(this._inputBuffer, currentLength, this._inputPtr - currentLength);
        char[] array2 = this._textBuffer.getCurrentSegment();
        currentLength = this._textBuffer.getCurrentSegmentSize();
        final int length = array.length;
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr];
            if (c <= length) {
                if (array[c] != 0) {
                    break;
                }
            }
            else if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            ++this._inputPtr;
            n = n * 33 + c;
            final int n2 = currentLength + 1;
            array2[currentLength] = c;
            if (n2 >= array2.length) {
                array2 = this._textBuffer.finishCurrentSegment();
                currentLength = 0;
            }
            else {
                currentLength = n2;
            }
        }
        this._textBuffer.setCurrentLength(currentLength);
        final TextBuffer textBuffer = this._textBuffer;
        final char[] textBuffer2 = textBuffer.getTextBuffer();
        currentLength = textBuffer.getTextOffset();
        return this._symbols.findSymbol(textBuffer2, currentLength, textBuffer.size(), n);
    }
    
    private final void _matchFalse() {
        final int inputPtr = this._inputPtr;
        if (inputPtr + 4 < this._inputEnd) {
            final char[] inputBuffer = this._inputBuffer;
            if (inputBuffer[inputPtr] == 'a') {
                final int n = inputPtr + 1;
                if (inputBuffer[n] == 'l') {
                    final int n2 = n + 1;
                    if (inputBuffer[n2] == 's') {
                        final int n3 = n2 + 1;
                        if (inputBuffer[n3] == 'e') {
                            final int inputPtr2 = n3 + 1;
                            final char c = inputBuffer[inputPtr2];
                            if (c < '0' || c == ']' || c == '}') {
                                this._inputPtr = inputPtr2;
                                return;
                            }
                        }
                    }
                }
            }
        }
        this._matchToken("false", 1);
    }
    
    private final void _matchNull() {
        final int inputPtr = this._inputPtr;
        if (inputPtr + 3 < this._inputEnd) {
            final char[] inputBuffer = this._inputBuffer;
            if (inputBuffer[inputPtr] == 'u') {
                final int n = inputPtr + 1;
                if (inputBuffer[n] == 'l') {
                    final int n2 = n + 1;
                    if (inputBuffer[n2] == 'l') {
                        final int inputPtr2 = n2 + 1;
                        final char c = inputBuffer[inputPtr2];
                        if (c < '0' || c == ']' || c == '}') {
                            this._inputPtr = inputPtr2;
                            return;
                        }
                    }
                }
            }
        }
        this._matchToken("null", 1);
    }
    
    private final void _matchTrue() {
        final int inputPtr = this._inputPtr;
        if (inputPtr + 3 < this._inputEnd) {
            final char[] inputBuffer = this._inputBuffer;
            if (inputBuffer[inputPtr] == 'r') {
                final int n = inputPtr + 1;
                if (inputBuffer[n] == 'u') {
                    final int n2 = n + 1;
                    if (inputBuffer[n2] == 'e') {
                        final int inputPtr2 = n2 + 1;
                        final char c = inputBuffer[inputPtr2];
                        if (c < '0' || c == ']' || c == '}') {
                            this._inputPtr = inputPtr2;
                            return;
                        }
                    }
                }
            }
        }
        this._matchToken("true", 1);
    }
    
    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        final JsonToken nextToken = this._nextToken;
        this._nextToken = null;
        if (nextToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        }
        else if (nextToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return this._currToken = nextToken;
    }
    
    private final JsonToken _parseFloat(int inputPtr, final int inputPtr2, int n, final boolean b, final int n2) {
        int n3 = 0;
        final int n4 = 0;
        final int inputEnd = this._inputEnd;
        int n6 = 0;
        Label_0090: {
            if (inputPtr == 46) {
                inputPtr = 0;
                for (int i = n; i < inputEnd; i = n) {
                    final char[] inputBuffer = this._inputBuffer;
                    n = i + 1;
                    final int n5 = inputBuffer[i];
                    if (n5 < 48 || n5 > 57) {
                        if (inputPtr == 0) {
                            this.reportUnexpectedNumberChar(n5, "Decimal point not followed by a digit");
                        }
                        n6 = inputPtr;
                        inputPtr = n;
                        n = n5;
                        break Label_0090;
                    }
                    ++inputPtr;
                }
                return this._parseNumber2(b, inputPtr2);
            }
            n6 = 0;
            final int n7 = inputPtr;
            inputPtr = n;
            n = n7;
        }
        int n8 = 0;
        int n9 = 0;
        Label_0289: {
            if (n != 101) {
                n8 = inputPtr;
                if ((n9 = n) != 69) {
                    break Label_0289;
                }
            }
            if (inputPtr >= inputEnd) {
                this._inputPtr = inputPtr2;
                return this._parseNumber2(b, inputPtr2);
            }
            final char[] inputBuffer2 = this._inputBuffer;
            final int n10 = inputPtr + 1;
            n = inputBuffer2[inputPtr];
            int n11;
            if (n == 45 || n == 43) {
                if (n10 >= inputEnd) {
                    this._inputPtr = inputPtr2;
                    return this._parseNumber2(b, inputPtr2);
                }
                final char[] inputBuffer3 = this._inputBuffer;
                inputPtr = n10 + 1;
                n = inputBuffer3[n10];
                n11 = n4;
            }
            else {
                inputPtr = n10;
                n11 = n4;
            }
            while (n <= 57 && n >= 48) {
                ++n11;
                if (inputPtr >= inputEnd) {
                    this._inputPtr = inputPtr2;
                    return this._parseNumber2(b, inputPtr2);
                }
                n = this._inputBuffer[inputPtr];
                ++inputPtr;
            }
            n3 = n11;
            n8 = inputPtr;
            n9 = n;
            if (n11 == 0) {
                this.reportUnexpectedNumberChar(n, "Exponent indicator not followed by a digit");
                n9 = n;
                n8 = inputPtr;
                n3 = n11;
            }
        }
        inputPtr = n8 - 1;
        this._inputPtr = inputPtr;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace(n9);
        }
        this._textBuffer.resetWithShared(this._inputBuffer, inputPtr2, inputPtr - inputPtr2);
        return this.resetFloat(b, n2, n6, n3);
    }
    
    private String _parseName2(int currentLength, int n, int size) {
        this._textBuffer.resetWithShared(this._inputBuffer, currentLength, this._inputPtr - currentLength);
        char[] array = this._textBuffer.getCurrentSegment();
        currentLength = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(": was expecting closing '" + (char)size + "' for name");
            }
            final char c = this._inputBuffer[this._inputPtr++];
            char decodeEscaped;
            if ((decodeEscaped = c) <= '\\') {
                if (c == '\\') {
                    decodeEscaped = this._decodeEscaped();
                }
                else if ((decodeEscaped = c) <= size) {
                    if (c == size) {
                        break;
                    }
                    if ((decodeEscaped = c) < ' ') {
                        this._throwUnquotedSpace(c, "name");
                        decodeEscaped = c;
                    }
                }
            }
            n = n * 33 + decodeEscaped;
            final int n2 = currentLength + 1;
            array[currentLength] = decodeEscaped;
            if (n2 >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                currentLength = 0;
            }
            else {
                currentLength = n2;
            }
        }
        this._textBuffer.setCurrentLength(currentLength);
        final TextBuffer textBuffer = this._textBuffer;
        final char[] textBuffer2 = textBuffer.getTextBuffer();
        currentLength = textBuffer.getTextOffset();
        size = textBuffer.size();
        return this._symbols.findSymbol(textBuffer2, currentLength, size, n);
    }
    
    private final JsonToken _parseNumber2(final boolean b, int n) {
        int inputPtr = n;
        if (b) {
            inputPtr = n + 1;
        }
        this._inputPtr = inputPtr;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (b) {
            n = 1;
            emptyAndGetCurrentSegment[0] = '-';
        }
        else {
            n = 0;
        }
        int n2 = 0;
        char nextChar;
        if (this._inputPtr < this._inputEnd) {
            nextChar = this._inputBuffer[this._inputPtr++];
        }
        else {
            nextChar = this.getNextChar("No digit following minus sign");
        }
        char verifyNoLeadingZeroes = nextChar;
        if (nextChar == '0') {
            verifyNoLeadingZeroes = this._verifyNoLeadingZeroes();
        }
        final int n3 = 0;
        char c = verifyNoLeadingZeroes;
        while (true) {
        Label_0286_Outer:
            while (c >= '0' && c <= '9') {
                ++n2;
                int n4 = n;
                char[] array = emptyAndGetCurrentSegment;
                if (n >= emptyAndGetCurrentSegment.length) {
                    array = this._textBuffer.finishCurrentSegment();
                    n4 = 0;
                }
                final int n5 = n4 + 1;
                array[n4] = c;
                if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                    n = 1;
                    c = '\0';
                    final int n6 = n2;
                    final int n7 = n5;
                    if (n6 == 0) {
                        return this._handleInvalidNumberStart(c, b);
                    }
                    int n8 = 0;
                    int n12 = 0;
                    int n14 = 0;
                    char[] array2 = null;
                    Label_0316: {
                        if (c == '.') {
                            final int n9 = n7 + 1;
                            array[n7] = c;
                            int n10 = n9;
                            while (true) {
                                while (this._inputPtr < this._inputEnd || this.loadMore()) {
                                    c = this._inputBuffer[this._inputPtr++];
                                    if (c >= '0') {
                                        if (c <= '9') {
                                            ++n8;
                                            if (n10 >= array.length) {
                                                array = this._textBuffer.finishCurrentSegment();
                                                n10 = 0;
                                            }
                                            final int n11 = n10 + 1;
                                            array[n10] = c;
                                            n10 = n11;
                                            continue Label_0286_Outer;
                                        }
                                    }
                                    if (n8 == 0) {
                                        this.reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
                                    }
                                    n12 = n8;
                                    final int n13 = n10;
                                    n14 = n;
                                    array2 = array;
                                    n = n13;
                                    break Label_0316;
                                }
                                n = 1;
                                continue;
                            }
                        }
                        n12 = 0;
                        final int n15 = n;
                        n = n7;
                        array2 = array;
                        n14 = n15;
                    }
                    final int n16 = 0;
                    int n22 = 0;
                    int n23 = 0;
                    char c3 = '\0';
                    int currentLength = 0;
                    Label_0610: {
                        if (c == 'e' || c == 'E') {
                            int n17 = n;
                            char[] array3 = array2;
                            if (n >= array2.length) {
                                array3 = this._textBuffer.finishCurrentSegment();
                                n17 = 0;
                            }
                            n = n17 + 1;
                            array3[n17] = c;
                            char c2;
                            if (this._inputPtr < this._inputEnd) {
                                c2 = this._inputBuffer[this._inputPtr++];
                            }
                            else {
                                c2 = this.getNextChar("expected a digit for number exponent");
                            }
                            int n18;
                            if (c2 == '-' || c2 == '+') {
                                if (n >= array3.length) {
                                    array3 = this._textBuffer.finishCurrentSegment();
                                    n = 0;
                                }
                                array3[n] = c2;
                                if (this._inputPtr < this._inputEnd) {
                                    c2 = this._inputBuffer[this._inputPtr++];
                                }
                                else {
                                    c2 = this.getNextChar("expected a digit for number exponent");
                                }
                                ++n;
                                n18 = n16;
                            }
                            else {
                                n18 = n16;
                            }
                            while (true) {
                                while (c2 <= '9' && c2 >= '0') {
                                    ++n18;
                                    int n19 = n;
                                    char[] finishCurrentSegment = array3;
                                    if (n >= array3.length) {
                                        finishCurrentSegment = this._textBuffer.finishCurrentSegment();
                                        n19 = 0;
                                    }
                                    n = n19 + 1;
                                    finishCurrentSegment[n19] = c2;
                                    if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                                        final int n20 = n18;
                                        n14 = 1;
                                        final int n21 = n;
                                        n = n20;
                                        n22 = n;
                                        n23 = n14;
                                        c3 = c2;
                                        currentLength = n21;
                                        if (n == 0) {
                                            this.reportUnexpectedNumberChar(c2, "Exponent indicator not followed by a digit");
                                            currentLength = n21;
                                            c3 = c2;
                                            n23 = n14;
                                            n22 = n;
                                        }
                                        break Label_0610;
                                    }
                                    else {
                                        c2 = this._inputBuffer[this._inputPtr++];
                                        array3 = finishCurrentSegment;
                                    }
                                }
                                final int n24 = n;
                                n = n18;
                                final int n21 = n24;
                                continue;
                            }
                        }
                        c3 = c;
                        n22 = 0;
                        n23 = n14;
                        currentLength = n;
                    }
                    if (n23 == 0) {
                        --this._inputPtr;
                        if (this._parsingContext.inRoot()) {
                            this._verifyRootSpace(c3);
                        }
                    }
                    this._textBuffer.setCurrentLength(currentLength);
                    return this.reset(b, n6, n12, n22);
                }
                else {
                    final char[] inputBuffer = this._inputBuffer;
                    n = this._inputPtr++;
                    c = inputBuffer[n];
                    n = n5;
                    emptyAndGetCurrentSegment = array;
                }
            }
            final int n6 = n2;
            char[] array = emptyAndGetCurrentSegment;
            final int n7 = n;
            n = n3;
            continue;
        }
    }
    
    private final int _skipAfterComma2() {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr++];
            if (c > ' ') {
                if (c == '/') {
                    this._skipComment();
                }
                else {
                    if (c != '#' || !this._skipYAMLComment()) {
                        return c;
                    }
                    continue;
                }
            }
            else {
                if (c >= ' ') {
                    continue;
                }
                if (c == '\n') {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (c == '\r') {
                    this._skipCR();
                }
                else {
                    if (c == '\t') {
                        continue;
                    }
                    this._throwInvalidSpace(c);
                }
            }
        }
        throw this._constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
    }
    
    private void _skipCComment() {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr++];
            if (c <= '*') {
                if (c == '*') {
                    if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                        break;
                    }
                    if (this._inputBuffer[this._inputPtr] == '/') {
                        ++this._inputPtr;
                        return;
                    }
                    continue;
                }
                else {
                    if (c >= ' ') {
                        continue;
                    }
                    if (c == '\n') {
                        ++this._currInputRow;
                        this._currInputRowStart = this._inputPtr;
                    }
                    else if (c == '\r') {
                        this._skipCR();
                    }
                    else {
                        if (c == '\t') {
                            continue;
                        }
                        this._throwInvalidSpace(c);
                    }
                }
            }
        }
        this._reportInvalidEOF(" in a comment");
    }
    
    private final int _skipColon() {
        if (this._inputPtr + 4 >= this._inputEnd) {
            return this._skipColon2(false);
        }
        final char c = this._inputBuffer[this._inputPtr];
        if (c == ':') {
            final char[] inputBuffer = this._inputBuffer;
            final int inputPtr = this._inputPtr + 1;
            this._inputPtr = inputPtr;
            final char c2 = inputBuffer[inputPtr];
            if (c2 <= ' ') {
                if (c2 == ' ' || c2 == '\t') {
                    final char[] inputBuffer2 = this._inputBuffer;
                    final int inputPtr2 = this._inputPtr + 1;
                    this._inputPtr = inputPtr2;
                    final char c3 = inputBuffer2[inputPtr2];
                    if (c3 > ' ') {
                        if (c3 == '/' || c3 == '#') {
                            return this._skipColon2(true);
                        }
                        ++this._inputPtr;
                        return c3;
                    }
                }
                return this._skipColon2(true);
            }
            if (c2 == '/' || c2 == '#') {
                return this._skipColon2(true);
            }
            ++this._inputPtr;
            return c2;
        }
        else {
            char c4;
            if (c == ' ' || (c4 = c) == '\t') {
                final char[] inputBuffer3 = this._inputBuffer;
                final int inputPtr3 = this._inputPtr + 1;
                this._inputPtr = inputPtr3;
                c4 = inputBuffer3[inputPtr3];
            }
            if (c4 != ':') {
                return this._skipColon2(false);
            }
            final char[] inputBuffer4 = this._inputBuffer;
            final int inputPtr4 = this._inputPtr + 1;
            this._inputPtr = inputPtr4;
            final char c5 = inputBuffer4[inputPtr4];
            if (c5 <= ' ') {
                if (c5 == ' ' || c5 == '\t') {
                    final char[] inputBuffer5 = this._inputBuffer;
                    final int inputPtr5 = this._inputPtr + 1;
                    this._inputPtr = inputPtr5;
                    final char c6 = inputBuffer5[inputPtr5];
                    if (c6 > ' ') {
                        if (c6 == '/' || c6 == '#') {
                            return this._skipColon2(true);
                        }
                        ++this._inputPtr;
                        return c6;
                    }
                }
                return this._skipColon2(true);
            }
            if (c5 == '/' || c5 == '#') {
                return this._skipColon2(true);
            }
            ++this._inputPtr;
            return c5;
        }
    }
    
    private final int _skipColon2(boolean b) {
        char c;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            c = this._inputBuffer[this._inputPtr++];
            if (c > ' ') {
                if (c == '/') {
                    this._skipComment();
                }
                else {
                    if (c == '#' && this._skipYAMLComment()) {
                        continue;
                    }
                    if (b) {
                        break;
                    }
                    if (c != ':') {
                        if (c < ' ') {
                            this._throwInvalidSpace(c);
                        }
                        this._reportUnexpectedChar(c, "was expecting a colon to separate field name and value");
                    }
                    b = true;
                }
            }
            else {
                if (c >= ' ') {
                    continue;
                }
                if (c == '\n') {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (c == '\r') {
                    this._skipCR();
                }
                else {
                    if (c == '\t') {
                        continue;
                    }
                    this._throwInvalidSpace(c);
                }
            }
        }
        return c;
    }
    
    private final int _skipComma(int skipAfterComma2) {
        if (skipAfterComma2 != 44) {
            this._reportUnexpectedChar(skipAfterComma2, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
        }
        while (this._inputPtr < this._inputEnd) {
            final char[] inputBuffer = this._inputBuffer;
            skipAfterComma2 = this._inputPtr++;
            final int n = inputBuffer[skipAfterComma2];
            if (n > 32) {
                if (n == 47 || (skipAfterComma2 = n) == 35) {
                    --this._inputPtr;
                    skipAfterComma2 = this._skipAfterComma2();
                }
                return skipAfterComma2;
            }
            if (n >= 32) {
                continue;
            }
            if (n == 10) {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
            }
            else if (n == 13) {
                this._skipCR();
            }
            else {
                if (n == 9) {
                    continue;
                }
                this._throwInvalidSpace(n);
            }
        }
        return this._skipAfterComma2();
    }
    
    private void _skipComment() {
        if (!this.isEnabled(JsonParser$Feature.ALLOW_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            this._reportInvalidEOF(" in a comment");
        }
        final char c = this._inputBuffer[this._inputPtr++];
        if (c == '/') {
            this._skipLine();
            return;
        }
        if (c == '*') {
            this._skipCComment();
            return;
        }
        this._reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
    }
    
    private void _skipLine() {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr++];
            if (c < ' ') {
                if (c == '\n') {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                    break;
                }
                if (c == '\r') {
                    this._skipCR();
                    return;
                }
                if (c == '\t') {
                    continue;
                }
                this._throwInvalidSpace(c);
            }
        }
    }
    
    private final int _skipWSOrEnd() {
        int eofAsNextChar;
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            eofAsNextChar = this._eofAsNextChar();
        }
        else {
            final char c = this._inputBuffer[this._inputPtr++];
            if (c <= ' ') {
                if (c != ' ') {
                    if (c == '\n') {
                        ++this._currInputRow;
                        this._currInputRowStart = this._inputPtr;
                    }
                    else if (c == '\r') {
                        this._skipCR();
                    }
                    else if (c != '\t') {
                        this._throwInvalidSpace(c);
                    }
                }
                while (this._inputPtr < this._inputEnd) {
                    final char c2 = this._inputBuffer[this._inputPtr++];
                    if (c2 > ' ') {
                        if (c2 == '/' || (eofAsNextChar = c2) == 35) {
                            --this._inputPtr;
                            return this._skipWSOrEnd2();
                        }
                        return eofAsNextChar;
                    }
                    else {
                        if (c2 == ' ') {
                            continue;
                        }
                        if (c2 == '\n') {
                            ++this._currInputRow;
                            this._currInputRowStart = this._inputPtr;
                        }
                        else if (c2 == '\r') {
                            this._skipCR();
                        }
                        else {
                            if (c2 == '\t') {
                                continue;
                            }
                            this._throwInvalidSpace(c2);
                        }
                    }
                }
                return this._skipWSOrEnd2();
            }
            if (c == '/' || (eofAsNextChar = c) == 35) {
                --this._inputPtr;
                return this._skipWSOrEnd2();
            }
        }
        return eofAsNextChar;
    }
    
    private int _skipWSOrEnd2() {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr++];
            if (c > ' ') {
                if (c != '/') {
                    int eofAsNextChar;
                    if ((eofAsNextChar = c) == 35) {
                        eofAsNextChar = c;
                        if (this._skipYAMLComment()) {
                            continue;
                        }
                    }
                    return eofAsNextChar;
                }
                this._skipComment();
            }
            else {
                if (c == ' ') {
                    continue;
                }
                if (c == '\n') {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (c == '\r') {
                    this._skipCR();
                }
                else {
                    if (c == '\t') {
                        continue;
                    }
                    this._throwInvalidSpace(c);
                }
            }
        }
        return this._eofAsNextChar();
    }
    
    private boolean _skipYAMLComment() {
        if (!this.isEnabled(JsonParser$Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        this._skipLine();
        return true;
    }
    
    private final void _updateLocation() {
        final int inputPtr = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + inputPtr;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = inputPtr - this._currInputRowStart;
    }
    
    private final void _updateNameLocation() {
        final int inputPtr = this._inputPtr;
        this._nameStartOffset = inputPtr;
        this._nameStartRow = this._currInputRow;
        this._nameStartCol = inputPtr - this._currInputRowStart;
    }
    
    private char _verifyNLZ2() {
        char c;
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            c = '0';
        }
        else {
            final char c2 = this._inputBuffer[this._inputPtr];
            if (c2 < '0' || c2 > '9') {
                return '0';
            }
            if (!this.isEnabled(JsonParser$Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                this.reportInvalidNumber("Leading zeroes not allowed");
            }
            ++this._inputPtr;
            if ((c = c2) == '0') {
                c = c2;
                while (this._inputPtr < this._inputEnd || this.loadMore()) {
                    final char c3 = this._inputBuffer[this._inputPtr];
                    if (c3 < '0' || c3 > '9') {
                        return '0';
                    }
                    ++this._inputPtr;
                    if ((c = c3) != '0') {
                        return c3;
                    }
                }
            }
        }
        return c;
    }
    
    private final char _verifyNoLeadingZeroes() {
        if (this._inputPtr < this._inputEnd) {
            final char c = this._inputBuffer[this._inputPtr];
            if (c < '0' || c > '9') {
                return '0';
            }
        }
        return this._verifyNLZ2();
    }
    
    private final void _verifyRootSpace(final int n) {
        ++this._inputPtr;
        switch (n) {
            default: {
                this._reportMissingRootWS(n);
            }
            case 9:
            case 32: {}
            case 13: {
                this._skipCR();
            }
            case 10: {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
            }
        }
    }
    
    @Override
    protected void _closeInput() {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || this.isEnabled(JsonParser$Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }
    
    @Override
    protected char _decodeEscaped() {
        int n = 0;
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            this._reportInvalidEOF(" in character escape sequence");
        }
        char handleUnrecognizedCharacterEscape;
        final char c = handleUnrecognizedCharacterEscape = this._inputBuffer[this._inputPtr++];
        switch (c) {
            default: {
                handleUnrecognizedCharacterEscape = this._handleUnrecognizedCharacterEscape(c);
                return handleUnrecognizedCharacterEscape;
            }
            case 34:
            case 47:
            case 92: {
                return handleUnrecognizedCharacterEscape;
            }
            case 98: {
                return '\b';
            }
            case 116: {
                return '\t';
            }
            case 110: {
                return '\n';
            }
            case 102: {
                return '\f';
            }
            case 114: {
                return '\r';
            }
            case 117: {
                for (int i = 0; i < 4; ++i) {
                    if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                        this._reportInvalidEOF(" in character escape sequence");
                    }
                    final char c2 = this._inputBuffer[this._inputPtr++];
                    final int charToHex = CharTypes.charToHex(c2);
                    if (charToHex < 0) {
                        this._reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence");
                    }
                    n = (n << 4 | charToHex);
                }
                return (char)n;
            }
        }
    }
    
    @Override
    protected final void _finishString() {
        int inputPtr = this._inputPtr;
        final int inputEnd = this._inputEnd;
        int inputPtr2 = inputPtr;
        if (inputPtr < inputEnd) {
            final int[] icLatin1 = ReaderBasedJsonParser._icLatin1;
            final int length = icLatin1.length;
            do {
                final char c = this._inputBuffer[inputPtr];
                if (c < length && icLatin1[c] != 0) {
                    inputPtr2 = inputPtr;
                    if (c == '\"') {
                        this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, inputPtr - this._inputPtr);
                        this._inputPtr = inputPtr + 1;
                        return;
                    }
                    break;
                }
                else {
                    inputPtr2 = inputPtr + 1;
                }
            } while ((inputPtr = inputPtr2) < inputEnd);
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, inputPtr2 - this._inputPtr);
        this._inputPtr = inputPtr2;
        this._finishString2();
    }
    
    protected void _finishString2() {
        char[] array = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        final int[] icLatin1 = ReaderBasedJsonParser._icLatin1;
        final int length = icLatin1.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value");
            }
            final char c = this._inputBuffer[this._inputPtr++];
            char decodeEscaped;
            if ((decodeEscaped = c) < length) {
                decodeEscaped = c;
                if (icLatin1[c] != 0) {
                    if (c == '\"') {
                        break;
                    }
                    if (c == '\\') {
                        decodeEscaped = this._decodeEscaped();
                    }
                    else if ((decodeEscaped = c) < ' ') {
                        this._throwUnquotedSpace(c, "string value");
                        decodeEscaped = c;
                    }
                }
            }
            if (currentSegmentSize >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            final int n = currentSegmentSize + 1;
            array[currentSegmentSize] = decodeEscaped;
            currentSegmentSize = n;
        }
        this._textBuffer.setCurrentLength(currentSegmentSize);
    }
    
    protected final String _getText2(final JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken.id()) {
            default: {
                return jsonToken.asString();
            }
            case 5: {
                return this._parsingContext.getCurrentName();
            }
            case 6:
            case 7:
            case 8: {
                return this._textBuffer.contentsAsString();
            }
        }
    }
    
    protected JsonToken _handleApos() {
        char[] array = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value");
            }
            final char c = this._inputBuffer[this._inputPtr++];
            char decodeEscaped;
            if ((decodeEscaped = c) <= '\\') {
                if (c == '\\') {
                    decodeEscaped = this._decodeEscaped();
                }
                else if ((decodeEscaped = c) <= '\'') {
                    if (c == '\'') {
                        break;
                    }
                    if ((decodeEscaped = c) < ' ') {
                        this._throwUnquotedSpace(c, "string value");
                        decodeEscaped = c;
                    }
                }
            }
            if (currentSegmentSize >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            final int n = currentSegmentSize + 1;
            array[currentSegmentSize] = decodeEscaped;
            currentSegmentSize = n;
        }
        this._textBuffer.setCurrentLength(currentSegmentSize);
        return JsonToken.VALUE_STRING;
    }
    
    protected JsonToken _handleInvalidNumberStart(int n, final boolean b) {
        double n2 = Double.NEGATIVE_INFINITY;
        int n3 = n;
        if (n == 73) {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOFInValue();
            }
            final char[] inputBuffer = this._inputBuffer;
            n = this._inputPtr++;
            n = inputBuffer[n];
            if (n == 78) {
                String s;
                if (b) {
                    s = "-INF";
                }
                else {
                    s = "+INF";
                }
                this._matchToken(s, 3);
                if (this.isEnabled(JsonParser$Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!b) {
                        n2 = Double.POSITIVE_INFINITY;
                    }
                    return this.resetAsNaN(s, n2);
                }
                this._reportError("Non-standard token '" + s + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                n3 = n;
            }
            else if ((n3 = n) == 110) {
                String s2;
                if (b) {
                    s2 = "-Infinity";
                }
                else {
                    s2 = "+Infinity";
                }
                this._matchToken(s2, 3);
                if (this.isEnabled(JsonParser$Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!b) {
                        n2 = Double.POSITIVE_INFINITY;
                    }
                    return this.resetAsNaN(s2, n2);
                }
                this._reportError("Non-standard token '" + s2 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                n3 = n;
            }
        }
        this.reportUnexpectedNumberChar(n3, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }
    
    protected String _handleOddName(int inputPtr) {
        if (inputPtr == 39 && this.isEnabled(JsonParser$Feature.ALLOW_SINGLE_QUOTES)) {
            return this._parseAposName();
        }
        if (!this.isEnabled(JsonParser$Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            this._reportUnexpectedChar(inputPtr, "was expecting double-quote to start field name");
        }
        final int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        final int length = inputCodeLatin1JsNames.length;
        int javaIdentifierPart;
        if (inputPtr < length) {
            if (inputCodeLatin1JsNames[inputPtr] == 0) {
                javaIdentifierPart = 1;
            }
            else {
                javaIdentifierPart = 0;
            }
        }
        else {
            javaIdentifierPart = (Character.isJavaIdentifierPart((char)inputPtr) ? 1 : 0);
        }
        if (javaIdentifierPart == 0) {
            this._reportUnexpectedChar(inputPtr, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        final int inputPtr2 = this._inputPtr;
        int hashSeed = this._hashSeed;
        final int inputEnd = this._inputEnd;
        int n = hashSeed;
        if ((inputPtr = inputPtr2) < inputEnd) {
            inputPtr = inputPtr2;
            int n2;
            do {
                final char c = this._inputBuffer[inputPtr];
                if (c < length) {
                    if (inputCodeLatin1JsNames[c] != 0) {
                        final int n3 = this._inputPtr - 1;
                        this._inputPtr = inputPtr;
                        return this._symbols.findSymbol(this._inputBuffer, n3, inputPtr - n3, hashSeed);
                    }
                }
                else if (!Character.isJavaIdentifierPart(c)) {
                    final int n4 = this._inputPtr - 1;
                    this._inputPtr = inputPtr;
                    return this._symbols.findSymbol(this._inputBuffer, n4, inputPtr - n4, hashSeed);
                }
                n = hashSeed * 33 + c;
                n2 = inputPtr + 1;
                hashSeed = n;
            } while ((inputPtr = n2) < inputEnd);
            inputPtr = n2;
        }
        final int inputPtr3 = this._inputPtr;
        this._inputPtr = inputPtr;
        return this._handleOddName2(inputPtr3 - 1, n, inputCodeLatin1JsNames);
    }
    
    protected JsonToken _handleOddValue(int n) {
        switch (n) {
            case 39: {
                if (this.isEnabled(JsonParser$Feature.ALLOW_SINGLE_QUOTES)) {
                    return this._handleApos();
                }
                break;
            }
            case 78: {
                this._matchToken("NaN", 1);
                if (this.isEnabled(JsonParser$Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("NaN", Double.NaN);
                }
                this._reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            }
            case 73: {
                this._matchToken("Infinity", 1);
                if (this.isEnabled(JsonParser$Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            }
            case 43: {
                if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                    this._reportInvalidEOFInValue();
                }
                final char[] inputBuffer = this._inputBuffer;
                n = this._inputPtr++;
                return this._handleInvalidNumberStart(inputBuffer[n], false);
            }
        }
        if (Character.isJavaIdentifierStart(n)) {
            this._reportInvalidToken("" + (char)n, "('true', 'false' or 'null')");
        }
        this._reportUnexpectedChar(n, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }
    
    protected final void _matchToken(final String s, int n) {
        int n2;
        do {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidToken(s.substring(0, n));
            }
            if (this._inputBuffer[this._inputPtr] != s.charAt(n)) {
                this._reportInvalidToken(s.substring(0, n));
            }
            ++this._inputPtr;
            n2 = n + 1;
        } while ((n = n2) < s.length());
        if (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr];
            if (c >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
                this._reportInvalidToken(s.substring(0, n2));
            }
        }
    }
    
    protected String _parseAposName() {
        final int inputPtr = this._inputPtr;
        final int hashSeed = this._hashSeed;
        final int inputEnd = this._inputEnd;
        int n = hashSeed;
        int inputPtr2 = inputPtr;
        Label_0099: {
            if (inputPtr < inputEnd) {
                final int[] icLatin1 = ReaderBasedJsonParser._icLatin1;
                final int length = icLatin1.length;
                inputPtr2 = inputPtr;
                n = hashSeed;
                int n2;
                int n3;
                do {
                    final char c = this._inputBuffer[inputPtr2];
                    if (c == '\'') {
                        final int inputPtr3 = this._inputPtr;
                        this._inputPtr = inputPtr2 + 1;
                        return this._symbols.findSymbol(this._inputBuffer, inputPtr3, inputPtr2 - inputPtr3, n);
                    }
                    if (c < length && icLatin1[c] != 0) {
                        break Label_0099;
                    }
                    n3 = n * 33 + c;
                    n2 = inputPtr2 + 1;
                    n = n3;
                } while ((inputPtr2 = n2) < inputEnd);
                n = n3;
                inputPtr2 = n2;
            }
        }
        final int inputPtr4 = this._inputPtr;
        this._inputPtr = inputPtr2;
        return this._parseName2(inputPtr4, n, 39);
    }
    
    protected final String _parseName() {
        int i = this._inputPtr;
        int hashSeed = this._hashSeed;
        final int[] icLatin1 = ReaderBasedJsonParser._icLatin1;
        while (i < this._inputEnd) {
            final char c = this._inputBuffer[i];
            if (c < icLatin1.length && icLatin1[c] != 0) {
                if (c == '\"') {
                    final int inputPtr = this._inputPtr;
                    this._inputPtr = i + 1;
                    return this._symbols.findSymbol(this._inputBuffer, inputPtr, i - inputPtr, hashSeed);
                }
                break;
            }
            else {
                hashSeed = hashSeed * 33 + c;
                ++i;
            }
        }
        final int inputPtr2 = this._inputPtr;
        this._inputPtr = i;
        return this._parseName2(inputPtr2, hashSeed, 34);
    }
    
    protected final JsonToken _parseNegNumber() {
        final int inputPtr = this._inputPtr;
        final int n = inputPtr - 1;
        final int inputEnd = this._inputEnd;
        if (inputPtr >= inputEnd) {
            return this._parseNumber2(true, n);
        }
        final char[] inputBuffer = this._inputBuffer;
        int i = inputPtr + 1;
        final char c = inputBuffer[inputPtr];
        if (c > '9' || c < '0') {
            this._inputPtr = i;
            return this._handleInvalidNumberStart(c, true);
        }
        if (c == '0') {
            return this._parseNumber2(true, n);
        }
        int n2 = 1;
        while (i < inputEnd) {
            final char[] inputBuffer2 = this._inputBuffer;
            final int inputPtr2 = i + 1;
            final char c2 = inputBuffer2[i];
            if (c2 < '0' || c2 > '9') {
                if (c2 == '.' || c2 == 'e' || c2 == 'E') {
                    this._inputPtr = inputPtr2;
                    return this._parseFloat(c2, n, inputPtr2, true, n2);
                }
                final int inputPtr3 = inputPtr2 - 1;
                this._inputPtr = inputPtr3;
                if (this._parsingContext.inRoot()) {
                    this._verifyRootSpace(c2);
                }
                this._textBuffer.resetWithShared(this._inputBuffer, n, inputPtr3 - n);
                return this.resetInt(true, n2);
            }
            else {
                ++n2;
                i = inputPtr2;
            }
        }
        return this._parseNumber2(true, n);
    }
    
    protected final JsonToken _parsePosNumber(int n) {
        int i = this._inputPtr;
        final int inputPtr = i - 1;
        final int inputEnd = this._inputEnd;
        if (n == 48) {
            return this._parseNumber2(false, inputPtr);
        }
        n = 1;
        while (i < inputEnd) {
            final char[] inputBuffer = this._inputBuffer;
            final int inputPtr2 = i + 1;
            final char c = inputBuffer[i];
            if (c < '0' || c > '9') {
                if (c == '.' || c == 'e' || c == 'E') {
                    this._inputPtr = inputPtr2;
                    return this._parseFloat(c, inputPtr, inputPtr2, false, n);
                }
                final int inputPtr3 = inputPtr2 - 1;
                this._inputPtr = inputPtr3;
                if (this._parsingContext.inRoot()) {
                    this._verifyRootSpace(c);
                }
                this._textBuffer.resetWithShared(this._inputBuffer, inputPtr, inputPtr3 - inputPtr);
                return this.resetInt(false, n);
            }
            else {
                ++n;
                i = inputPtr2;
            }
        }
        this._inputPtr = inputPtr;
        return this._parseNumber2(false, inputPtr);
    }
    
    @Override
    protected void _releaseBuffers() {
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable) {
            final char[] inputBuffer = this._inputBuffer;
            if (inputBuffer != null) {
                this._inputBuffer = null;
                this._ioContext.releaseTokenBuffer(inputBuffer);
            }
        }
    }
    
    protected void _reportInvalidToken(final String s) {
        this._reportInvalidToken(s, "'null', 'true', 'false' or NaN");
    }
    
    protected void _reportInvalidToken(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder(s);
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            final char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            ++this._inputPtr;
            sb.append(c);
        }
        this._reportError("Unrecognized token '" + sb.toString() + "': was expecting " + s2);
    }
    
    protected final void _skipCR() {
        if ((this._inputPtr < this._inputEnd || this.loadMore()) && this._inputBuffer[this._inputPtr] == '\n') {
            ++this._inputPtr;
        }
        ++this._currInputRow;
        this._currInputRowStart = this._inputPtr;
    }
    
    protected final void _skipString() {
        this._tokenIncomplete = false;
        int n = this._inputPtr;
        int n2 = this._inputEnd;
        final char[] inputBuffer = this._inputBuffer;
        while (true) {
            int inputEnd = n2;
            int inputPtr = n;
            if (n >= n2) {
                this._inputPtr = n;
                if (!this.loadMore()) {
                    this._reportInvalidEOF(": was expecting closing quote for a string value");
                }
                inputPtr = this._inputPtr;
                inputEnd = this._inputEnd;
            }
            n = inputPtr + 1;
            final char c = inputBuffer[inputPtr];
            if (c <= '\\') {
                if (c == '\\') {
                    this._inputPtr = n;
                    this._decodeEscaped();
                    n = this._inputPtr;
                    n2 = this._inputEnd;
                    continue;
                }
                if (c <= '\"') {
                    if (c == '\"') {
                        break;
                    }
                    if (c < ' ') {
                        this._inputPtr = n;
                        this._throwUnquotedSpace(c, "string value");
                    }
                }
            }
            n2 = inputEnd;
        }
        this._inputPtr = n;
    }
    
    @Override
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1L, this._currInputProcessed + this._inputPtr, this._currInputRow, this._inputPtr - this._currInputRowStart + 1);
    }
    
    protected char getNextChar(final String s) {
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            this._reportInvalidEOF(s);
        }
        return this._inputBuffer[this._inputPtr++];
    }
    
    @Override
    public final String getText() {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return this._getText2(currToken);
    }
    
    @Override
    public final String getValueAsString() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return super.getValueAsString(null);
    }
    
    @Override
    public final String getValueAsString(final String s) {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return super.getValueAsString(s);
    }
    
    @Override
    protected boolean loadMore() {
        final boolean b = false;
        final int inputEnd = this._inputEnd;
        this._currInputProcessed += inputEnd;
        this._currInputRowStart -= inputEnd;
        this._nameStartOffset -= inputEnd;
        boolean b2 = b;
        if (this._reader != null) {
            final int read = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                b2 = true;
            }
            else {
                this._closeInput();
                b2 = b;
                if (read == 0) {
                    throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
                }
            }
        }
        return b2;
    }
    
    @Override
    public final JsonToken nextToken() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            this._skipString();
        }
        final int skipWSOrEnd = this._skipWSOrEnd();
        if (skipWSOrEnd < 0) {
            this.close();
            return this._currToken = null;
        }
        this._binaryValue = null;
        if (skipWSOrEnd == 93) {
            this._updateLocation();
            if (!this._parsingContext.inArray()) {
                this._reportMismatchedEndMarker(skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            return this._currToken = JsonToken.END_ARRAY;
        }
        if (skipWSOrEnd == 125) {
            this._updateLocation();
            if (!this._parsingContext.inObject()) {
                this._reportMismatchedEndMarker(skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            return this._currToken = JsonToken.END_OBJECT;
        }
        int skipComma = skipWSOrEnd;
        if (this._parsingContext.expectComma()) {
            skipComma = this._skipComma(skipWSOrEnd);
        }
        final boolean inObject = this._parsingContext.inObject();
        int skipColon = skipComma;
        if (inObject) {
            this._updateNameLocation();
            String currentName;
            if (skipComma == 34) {
                currentName = this._parseName();
            }
            else {
                currentName = this._handleOddName(skipComma);
            }
            this._parsingContext.setCurrentName(currentName);
            this._currToken = JsonToken.FIELD_NAME;
            skipColon = this._skipColon();
        }
        this._updateLocation();
        JsonToken jsonToken = null;
        switch (skipColon) {
            default: {
                jsonToken = this._handleOddValue(skipColon);
                break;
            }
            case 34: {
                this._tokenIncomplete = true;
                jsonToken = JsonToken.VALUE_STRING;
                break;
            }
            case 91: {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                }
                jsonToken = JsonToken.START_ARRAY;
                break;
            }
            case 123: {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                }
                jsonToken = JsonToken.START_OBJECT;
                break;
            }
            case 93:
            case 125: {
                this._reportUnexpectedChar(skipColon, "expected a value");
            }
            case 116: {
                this._matchTrue();
                jsonToken = JsonToken.VALUE_TRUE;
                break;
            }
            case 102: {
                this._matchFalse();
                jsonToken = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchNull();
                jsonToken = JsonToken.VALUE_NULL;
                break;
            }
            case 45: {
                jsonToken = this._parseNegNumber();
                break;
            }
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57: {
                jsonToken = this._parsePosNumber(skipColon);
                break;
            }
        }
        if (inObject) {
            this._nextToken = jsonToken;
            return this._currToken;
        }
        return this._currToken = jsonToken;
    }
}
