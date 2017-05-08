// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.android.internal.util.Predicate;
import com.facebook.common.references.CloseableReference;

public class InstrumentedMemoryCache<K, V> implements MemoryCache<K, V>
{
    private final MemoryCache<K, V> mDelegate;
    private final MemoryCacheTracker mTracker;
    
    public InstrumentedMemoryCache(final MemoryCache<K, V> mDelegate, final MemoryCacheTracker mTracker) {
        this.mDelegate = mDelegate;
        this.mTracker = mTracker;
    }
    
    @Override
    public CloseableReference<V> cache(final K k, final CloseableReference<V> closeableReference) {
        this.mTracker.onCachePut();
        return this.mDelegate.cache(k, closeableReference);
    }
    
    @Override
    public boolean contains(final Predicate<K> predicate) {
        return this.mDelegate.contains(predicate);
    }
    
    @Override
    public CloseableReference<V> get(final K k) {
        final CloseableReference<V> value = this.mDelegate.get(k);
        if (value == null) {
            this.mTracker.onCacheMiss();
            return value;
        }
        this.mTracker.onCacheHit();
        return value;
    }
    
    @Override
    public int removeAll(final Predicate<K> predicate) {
        return this.mDelegate.removeAll(predicate);
    }
}
