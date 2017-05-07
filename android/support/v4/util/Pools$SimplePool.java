// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public class Pools$SimplePool<T> implements Pools$Pool<T>
{
    private final Object[] mPool;
    private int mPoolSize;
    
    public Pools$SimplePool(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.mPool = new Object[n];
    }
    
    private boolean isInPool(final T t) {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.mPoolSize) {
                break;
            }
            if (this.mPool[n] == t) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @Override
    public T acquire() {
        if (this.mPoolSize > 0) {
            final int n = this.mPoolSize - 1;
            final Object o = this.mPool[n];
            this.mPool[n] = null;
            --this.mPoolSize;
            return (T)o;
        }
        return null;
    }
    
    @Override
    public boolean release(final T t) {
        if (this.isInPool(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        if (this.mPoolSize < this.mPool.length) {
            this.mPool[this.mPoolSize] = t;
            ++this.mPoolSize;
            return true;
        }
        return false;
    }
}
