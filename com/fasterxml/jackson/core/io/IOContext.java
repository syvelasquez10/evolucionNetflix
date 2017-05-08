// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.TextBuffer;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContext
{
    protected final BufferRecycler _bufferRecycler;
    protected final boolean _managedResource;
    protected char[] _nameCopyBuffer;
    protected final Object _sourceRef;
    protected char[] _tokenCBuffer;
    
    public IOContext(final BufferRecycler bufferRecycler, final Object sourceRef, final boolean managedResource) {
        this._bufferRecycler = bufferRecycler;
        this._sourceRef = sourceRef;
        this._managedResource = managedResource;
    }
    
    private IllegalArgumentException wrongBuf() {
        return new IllegalArgumentException("Trying to release buffer not owned by the context");
    }
    
    protected final void _verifyAlloc(final Object o) {
        if (o != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
    }
    
    protected final void _verifyRelease(final char[] array, final char[] array2) {
        if (array != array2 && array.length <= array2.length) {
            throw this.wrongBuf();
        }
    }
    
    public char[] allocTokenBuffer() {
        this._verifyAlloc(this._tokenCBuffer);
        return this._tokenCBuffer = this._bufferRecycler.allocCharBuffer(0);
    }
    
    public TextBuffer constructTextBuffer() {
        return new TextBuffer(this._bufferRecycler);
    }
    
    public Object getSourceReference() {
        return this._sourceRef;
    }
    
    public boolean isResourceManaged() {
        return this._managedResource;
    }
    
    public void releaseNameCopyBuffer(final char[] array) {
        if (array != null) {
            this._verifyRelease(array, this._nameCopyBuffer);
            this._nameCopyBuffer = null;
            this._bufferRecycler.releaseCharBuffer(3, array);
        }
    }
    
    public void releaseTokenBuffer(final char[] array) {
        if (array != null) {
            this._verifyRelease(array, this._tokenCBuffer);
            this._tokenCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(0, array);
        }
    }
}
