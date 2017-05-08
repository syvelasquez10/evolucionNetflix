// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader;
import com.facebook.common.internal.DoNotStrip;
import java.io.Closeable;

@DoNotStrip
public class NativeMemoryChunk implements Closeable
{
    private boolean mClosed;
    private final long mNativePtr;
    private final int mSize;
    
    static {
        ImagePipelineNativeLoader.load();
    }
    
    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0L;
        this.mClosed = true;
    }
    
    public NativeMemoryChunk(final int mSize) {
        Preconditions.checkArgument(mSize > 0);
        this.mSize = mSize;
        this.mNativePtr = nativeAllocate(this.mSize);
        this.mClosed = false;
    }
    
    private int adjustByteCount(final int n, final int n2) {
        return Math.min(Math.max(0, this.mSize - n), n2);
    }
    
    private void checkBounds(final int n, final int n2, final int n3, final int n4) {
        final boolean b = true;
        Preconditions.checkArgument(n4 >= 0);
        Preconditions.checkArgument(n >= 0);
        Preconditions.checkArgument(n3 >= 0);
        Preconditions.checkArgument(n + n4 <= this.mSize);
        Preconditions.checkArgument(n3 + n4 <= n2 && b);
    }
    
    private void doCopy(final int n, final NativeMemoryChunk nativeMemoryChunk, final int n2, final int n3) {
        final boolean b = true;
        Preconditions.checkState(!this.isClosed());
        Preconditions.checkState(!nativeMemoryChunk.isClosed() && b);
        this.checkBounds(n, nativeMemoryChunk.mSize, n2, n3);
        nativeMemcpy(nativeMemoryChunk.mNativePtr + n2, this.mNativePtr + n, n3);
    }
    
    @DoNotStrip
    private static native long nativeAllocate(final int p0);
    
    @DoNotStrip
    private static native void nativeCopyFromByteArray(final long p0, final byte[] p1, final int p2, final int p3);
    
    @DoNotStrip
    private static native void nativeCopyToByteArray(final long p0, final byte[] p1, final int p2, final int p3);
    
    @DoNotStrip
    private static native void nativeFree(final long p0);
    
    @DoNotStrip
    private static native void nativeMemcpy(final long p0, final long p1, final int p2);
    
    @DoNotStrip
    private static native byte nativeReadByte(final long p0);
    
    @Override
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                nativeFree(this.mNativePtr);
            }
        }
    }
    
    public void copy(final int n, NativeMemoryChunk nativeMemoryChunk, final int n2, final int n3) {
        Preconditions.checkNotNull(nativeMemoryChunk);
        if (nativeMemoryChunk.mNativePtr == this.mNativePtr) {
            Log.w("NativeMemoryChunk", "Copying from NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(this)) + " to NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(nativeMemoryChunk)) + " which share the same address " + Long.toHexString(this.mNativePtr));
            Preconditions.checkArgument(false);
        }
        if (nativeMemoryChunk.mNativePtr < this.mNativePtr) {
            synchronized (nativeMemoryChunk) {
                synchronized (this) {
                    this.doCopy(n, nativeMemoryChunk, n2, n3);
                    return;
                }
            }
        }
        // monitorenter(this)
        try {
            // monitorenter(nativeMemoryChunk)
            final NativeMemoryChunk nativeMemoryChunk2 = this;
            final int n4 = n;
            final NativeMemoryChunk nativeMemoryChunk3 = nativeMemoryChunk;
            final int n5 = n2;
            final int n6 = n3;
            nativeMemoryChunk2.doCopy(n4, nativeMemoryChunk3, n5, n6);
            return;
        }
        finally {
            final NativeMemoryChunk nativeMemoryChunk4;
            nativeMemoryChunk = nativeMemoryChunk4;
        }
        // monitorexit(this)
        try {
            final NativeMemoryChunk nativeMemoryChunk2 = this;
            final int n4 = n;
            final NativeMemoryChunk nativeMemoryChunk3 = nativeMemoryChunk;
            final int n5 = n2;
            final int n6 = n3;
            nativeMemoryChunk2.doCopy(n4, nativeMemoryChunk3, n5, n6);
        }
        finally {
        }
        // monitorexit(nativeMemoryChunk)
    }
    
    @Override
    protected void finalize() {
        if (this.isClosed()) {
            return;
        }
        Log.w("NativeMemoryChunk", "finalize: Chunk " + Integer.toHexString(System.identityHashCode(this)) + " still active. Underlying address = " + Long.toHexString(this.mNativePtr));
        try {
            this.close();
        }
        finally {
            super.finalize();
        }
    }
    
    public int getSize() {
        return this.mSize;
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.mClosed;
        }
    }
    
    public byte read(final int n) {
        final boolean b = true;
        synchronized (this) {
            Preconditions.checkState(!this.isClosed());
            Preconditions.checkArgument(n >= 0);
            Preconditions.checkArgument(n < this.mSize && b);
            return nativeReadByte(this.mNativePtr + n);
        }
    }
    
    public int read(final int n, final byte[] array, final int n2, int adjustByteCount) {
        synchronized (this) {
            Preconditions.checkNotNull(array);
            Preconditions.checkState(!this.isClosed());
            adjustByteCount = this.adjustByteCount(n, adjustByteCount);
            this.checkBounds(n, array.length, n2, adjustByteCount);
            nativeCopyToByteArray(this.mNativePtr + n, array, n2, adjustByteCount);
            return adjustByteCount;
        }
    }
    
    public int write(final int n, final byte[] array, final int n2, int adjustByteCount) {
        synchronized (this) {
            Preconditions.checkNotNull(array);
            Preconditions.checkState(!this.isClosed());
            adjustByteCount = this.adjustByteCount(n, adjustByteCount);
            this.checkBounds(n, array.length, n2, adjustByteCount);
            nativeCopyFromByteArray(this.mNativePtr + n, array, n2, adjustByteCount);
            return adjustByteCount;
        }
    }
}
