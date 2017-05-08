// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.references;

import com.facebook.common.logging.FLog;
import com.facebook.common.internal.Preconditions;
import java.util.IdentityHashMap;
import java.util.Map;

public class SharedReference<T>
{
    private static final Map<Object, Integer> sLiveObjects;
    private int mRefCount;
    private final ResourceReleaser<T> mResourceReleaser;
    private T mValue;
    
    static {
        sLiveObjects = new IdentityHashMap<Object, Integer>();
    }
    
    public SharedReference(final T t, final ResourceReleaser<T> resourceReleaser) {
        this.mValue = Preconditions.checkNotNull(t);
        this.mResourceReleaser = Preconditions.checkNotNull(resourceReleaser);
        this.mRefCount = 1;
        addLiveReference(t);
    }
    
    private static void addLiveReference(final Object o) {
        synchronized (SharedReference.sLiveObjects) {
            final Integer n = SharedReference.sLiveObjects.get(o);
            if (n == null) {
                SharedReference.sLiveObjects.put(o, 1);
            }
            else {
                SharedReference.sLiveObjects.put(o, n + 1);
            }
        }
    }
    
    private int decreaseRefCount() {
        synchronized (this) {
            this.ensureValid();
            Preconditions.checkArgument(this.mRefCount > 0);
            return --this.mRefCount;
        }
    }
    
    private void ensureValid() {
        if (!isValid(this)) {
            throw new SharedReference$NullReferenceException();
        }
    }
    
    public static boolean isValid(final SharedReference<?> sharedReference) {
        return sharedReference != null && sharedReference.isValid();
    }
    
    private static void removeLiveReference(final Object o) {
        while (true) {
            final Integer n;
            Label_0070: {
                synchronized (SharedReference.sLiveObjects) {
                    n = SharedReference.sLiveObjects.get(o);
                    if (n == null) {
                        FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", o.getClass());
                    }
                    else {
                        if (n != 1) {
                            break Label_0070;
                        }
                        SharedReference.sLiveObjects.remove(o);
                    }
                    return;
                }
            }
            final Throwable t;
            SharedReference.sLiveObjects.put(t, n - 1);
        }
    }
    
    public void addReference() {
        synchronized (this) {
            this.ensureValid();
            ++this.mRefCount;
        }
    }
    
    public void deleteReference() {
        if (this.decreaseRefCount() != 0) {
            return;
        }
        synchronized (this) {
            final T mValue = this.mValue;
            this.mValue = null;
            // monitorexit(this)
            this.mResourceReleaser.release(mValue);
            removeLiveReference(mValue);
        }
    }
    
    public T get() {
        synchronized (this) {
            return this.mValue;
        }
    }
    
    public boolean isValid() {
        synchronized (this) {
            return this.mRefCount > 0;
        }
    }
}
