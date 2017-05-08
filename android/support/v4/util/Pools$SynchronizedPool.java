// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public class Pools$SynchronizedPool<T> extends Pools$SimplePool<T>
{
    private final Object mLock;
    
    public Pools$SynchronizedPool(final int n) {
        super(n);
        this.mLock = new Object();
    }
    
    @Override
    public T acquire() {
        synchronized (this.mLock) {
            return super.acquire();
        }
    }
    
    @Override
    public boolean release(final T t) {
        synchronized (this.mLock) {
            return super.release(t);
        }
    }
}
