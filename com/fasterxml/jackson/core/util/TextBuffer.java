// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;

public final class TextBuffer
{
    static final char[] NO_CHARS;
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;
    
    static {
        NO_CHARS = new char[0];
    }
    
    public TextBuffer(final BufferRecycler allocator) {
        this._allocator = allocator;
    }
    
    private char[] buf(final int n) {
        if (this._allocator != null) {
            return this._allocator.allocCharBuffer(2, n);
        }
        return new char[Math.max(n, 1000)];
    }
    
    private char[] carr(final int n) {
        return new char[n];
    }
    
    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }
    
    private void expand(int n) {
        n = 1000;
        if (this._segments == null) {
            this._segments = new ArrayList<char[]>();
        }
        final char[] currentSegment = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(currentSegment);
        this._segmentSize += currentSegment.length;
        this._currentSize = 0;
        final int length = currentSegment.length;
        final int n2 = length + (length >> 1);
        if (n2 >= 1000) {
            if (n2 > 262144) {
                n = 262144;
            }
            else {
                n = n2;
            }
        }
        this._currentSegment = this.carr(n);
    }
    
    private char[] resultArray() {
        if (this._resultString != null) {
            return this._resultString.toCharArray();
        }
        if (this._inputStart >= 0) {
            final int inputLen = this._inputLen;
            if (inputLen < 1) {
                return TextBuffer.NO_CHARS;
            }
            final int inputStart = this._inputStart;
            if (inputStart == 0) {
                return Arrays.copyOf(this._inputBuffer, inputLen);
            }
            return Arrays.copyOfRange(this._inputBuffer, inputStart, inputLen + inputStart);
        }
        else {
            final int size = this.size();
            if (size < 1) {
                return TextBuffer.NO_CHARS;
            }
            final char[] carr = this.carr(size);
            int n;
            if (this._segments != null) {
                final int size2 = this._segments.size();
                int i = 0;
                n = 0;
                while (i < size2) {
                    final char[] array = this._segments.get(i);
                    final int length = array.length;
                    System.arraycopy(array, 0, carr, n, length);
                    n += length;
                    ++i;
                }
            }
            else {
                n = 0;
            }
            System.arraycopy(this._currentSegment, 0, carr, n, this._currentSize);
            return carr;
        }
    }
    
    private void unshare(int n) {
        final int inputLen = this._inputLen;
        this._inputLen = 0;
        final char[] inputBuffer = this._inputBuffer;
        this._inputBuffer = null;
        final int inputStart = this._inputStart;
        this._inputStart = -1;
        n += inputLen;
        if (this._currentSegment == null || n > this._currentSegment.length) {
            this._currentSegment = this.buf(n);
        }
        if (inputLen > 0) {
            System.arraycopy(inputBuffer, inputStart, this._currentSegment, 0, inputLen);
        }
        this._segmentSize = 0;
        this._currentSize = inputLen;
    }
    
    public void append(final char[] array, int min, final int n) {
        if (this._inputStart >= 0) {
            this.unshare(n);
        }
        this._resultString = null;
        this._resultArray = null;
        final char[] currentSegment = this._currentSegment;
        final int n2 = currentSegment.length - this._currentSize;
        if (n2 >= n) {
            System.arraycopy(array, min, currentSegment, this._currentSize, n);
            this._currentSize += n;
            return;
        }
        int n3 = min;
        int n4 = n;
        if (n2 > 0) {
            System.arraycopy(array, min, currentSegment, this._currentSize, n2);
            n3 = min + n2;
            n4 = n - n2;
        }
        do {
            this.expand(n4);
            min = Math.min(this._currentSegment.length, n4);
            System.arraycopy(array, n3, this._currentSegment, 0, min);
            this._currentSize += min;
            n3 += min;
            min = n4 - min;
        } while ((n4 = min) > 0);
    }
    
    public char[] contentsAsArray() {
        char[] resultArray;
        if ((resultArray = this._resultArray) == null) {
            resultArray = this.resultArray();
            this._resultArray = resultArray;
        }
        return resultArray;
    }
    
    public BigDecimal contentsAsDecimal() {
        if (this._resultArray != null) {
            return NumberInput.parseBigDecimal(this._resultArray);
        }
        if (this._inputStart >= 0 && this._inputBuffer != null) {
            return NumberInput.parseBigDecimal(this._inputBuffer, this._inputStart, this._inputLen);
        }
        if (this._segmentSize == 0 && this._currentSegment != null) {
            return NumberInput.parseBigDecimal(this._currentSegment, 0, this._currentSize);
        }
        return NumberInput.parseBigDecimal(this.contentsAsArray());
    }
    
    public double contentsAsDouble() {
        return NumberInput.parseDouble(this.contentsAsString());
    }
    
    public String contentsAsString() {
        if (this._resultString == null) {
            if (this._resultArray != null) {
                this._resultString = new String(this._resultArray);
            }
            else if (this._inputStart >= 0) {
                if (this._inputLen < 1) {
                    return this._resultString = "";
                }
                this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
            }
            else {
                final int segmentSize = this._segmentSize;
                final int currentSize = this._currentSize;
                if (segmentSize == 0) {
                    String resultString;
                    if (currentSize == 0) {
                        resultString = "";
                    }
                    else {
                        resultString = new String(this._currentSegment, 0, currentSize);
                    }
                    this._resultString = resultString;
                }
                else {
                    final StringBuilder sb = new StringBuilder(segmentSize + currentSize);
                    if (this._segments != null) {
                        for (int size = this._segments.size(), i = 0; i < size; ++i) {
                            final char[] array = this._segments.get(i);
                            sb.append(array, 0, array.length);
                        }
                    }
                    sb.append(this._currentSegment, 0, this._currentSize);
                    this._resultString = sb.toString();
                }
            }
        }
        return this._resultString;
    }
    
    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        char[] currentSegment;
        if ((currentSegment = this._currentSegment) == null) {
            currentSegment = this.buf(0);
            this._currentSegment = currentSegment;
        }
        return currentSegment;
    }
    
    public char[] finishCurrentSegment() {
        int n = 1000;
        if (this._segments == null) {
            this._segments = new ArrayList<char[]>();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        final int length = this._currentSegment.length;
        this._segmentSize += length;
        this._currentSize = 0;
        final int n2 = length + (length >> 1);
        if (n2 >= 1000) {
            if (n2 > 262144) {
                n = 262144;
            }
            else {
                n = n2;
            }
        }
        return this._currentSegment = this.carr(n);
    }
    
    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            this.unshare(1);
        }
        else {
            final char[] currentSegment = this._currentSegment;
            if (currentSegment == null) {
                this._currentSegment = this.buf(0);
            }
            else if (this._currentSize >= currentSegment.length) {
                this.expand(1);
            }
        }
        return this._currentSegment;
    }
    
    public int getCurrentSegmentSize() {
        return this._currentSize;
    }
    
    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        if (this._resultArray != null) {
            return this._resultArray;
        }
        if (this._resultString != null) {
            return this._resultArray = this._resultString.toCharArray();
        }
        if (this._hasSegments) {
            return this.contentsAsArray();
        }
        if (this._currentSegment == null) {
            return TextBuffer.NO_CHARS;
        }
        return this._currentSegment;
    }
    
    public int getTextOffset() {
        if (this._inputStart >= 0) {
            return this._inputStart;
        }
        return 0;
    }
    
    public void releaseBuffers() {
        if (this._allocator == null) {
            this.resetWithEmpty();
        }
        else if (this._currentSegment != null) {
            this.resetWithEmpty();
            final char[] currentSegment = this._currentSegment;
            this._currentSegment = null;
            this._allocator.releaseCharBuffer(2, currentSegment);
        }
    }
    
    public void resetWithCopy(final char[] array, final int n, final int n2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        else if (this._currentSegment == null) {
            this._currentSegment = this.buf(n2);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        this.append(array, n, n2);
    }
    
    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
    }
    
    public void resetWithShared(final char[] inputBuffer, final int inputStart, final int inputLen) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = inputBuffer;
        this._inputStart = inputStart;
        this._inputLen = inputLen;
        if (this._hasSegments) {
            this.clearSegments();
        }
    }
    
    public void resetWithString(final String resultString) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = resultString;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        this._currentSize = 0;
    }
    
    public void setCurrentLength(final int currentSize) {
        this._currentSize = currentSize;
    }
    
    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        if (this._resultArray != null) {
            return this._resultArray.length;
        }
        if (this._resultString != null) {
            return this._resultString.length();
        }
        return this._segmentSize + this._currentSize;
    }
    
    @Override
    public String toString() {
        return this.contentsAsString();
    }
}
