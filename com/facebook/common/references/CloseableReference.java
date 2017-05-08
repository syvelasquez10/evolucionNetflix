// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.references;

import com.facebook.common.logging.FLog;
import com.facebook.common.internal.Preconditions;
import java.io.Closeable;

public final class CloseableReference<T> implements Closeable, Cloneable
{
    private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER;
    private static Class<CloseableReference> TAG;
    private boolean mIsClosed;
    private final SharedReference<T> mSharedReference;
    
    static {
        CloseableReference.TAG = CloseableReference.class;
        DEFAULT_CLOSEABLE_RELEASER = new CloseableReference$1();
    }
    
    private CloseableReference(final SharedReference<T> sharedReference) {
        this.mIsClosed = false;
        this.mSharedReference = Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
    }
    
    private CloseableReference(final T t, final ResourceReleaser<T> resourceReleaser) {
        this.mIsClosed = false;
        this.mSharedReference = new SharedReference<T>(t, resourceReleaser);
    }
    
    public static <T> CloseableReference<T> cloneOrNull(final CloseableReference<T> closeableReference) {
        if (closeableReference != null) {
            return closeableReference.cloneOrNull();
        }
        return null;
    }
    
    public static void closeSafely(final CloseableReference<?> closeableReference) {
        if (closeableReference != null) {
            closeableReference.close();
        }
    }
    
    public static boolean isValid(final CloseableReference<?> closeableReference) {
        return closeableReference != null && closeableReference.isValid();
    }
    
    public static <T extends Closeable> CloseableReference<T> of(final T t) {
        if (t == null) {
            return null;
        }
        return new CloseableReference<T>(t, (ResourceReleaser<T>)CloseableReference.DEFAULT_CLOSEABLE_RELEASER);
    }
    
    public static <T> CloseableReference<T> of(final T t, final ResourceReleaser<T> resourceReleaser) {
        if (t == null) {
            return null;
        }
        return new CloseableReference<T>(t, resourceReleaser);
    }
    
    public CloseableReference<T> clone() {
        synchronized (this) {
            Preconditions.checkState(this.isValid());
            return new CloseableReference<T>(this.mSharedReference);
        }
    }
    
    public CloseableReference<T> cloneOrNull() {
        synchronized (this) {
            CloseableReference<T> closeableReference;
            if (this.isValid()) {
                closeableReference = new CloseableReference<T>(this.mSharedReference);
            }
            else {
                closeableReference = null;
            }
            return closeableReference;
        }
    }
    
    @Override
    public void close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            this.mIsClosed = true;
            // monitorexit(this)
            this.mSharedReference.deleteReference();
        }
    }
    
    @Override
    protected void finalize() {
        try {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                // monitorexit(this)
                FLog.w(CloseableReference.TAG, "Finalized without closing: %x %x (type = %s)", System.identityHashCode(this), System.identityHashCode(this.mSharedReference), this.mSharedReference.get().getClass().getSimpleName());
                this.close();
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public T get() {
        synchronized (this) {
            Preconditions.checkState(!this.mIsClosed);
            return this.mSharedReference.get();
        }
    }
    
    public int getValueHash() {
        synchronized (this) {
            int identityHashCode;
            if (this.isValid()) {
                identityHashCode = System.identityHashCode(this.mSharedReference.get());
            }
            else {
                identityHashCode = 0;
            }
            return identityHashCode;
        }
    }
    
    public boolean isValid() {
        synchronized (this) {
            return !this.mIsClosed;
        }
    }
}
