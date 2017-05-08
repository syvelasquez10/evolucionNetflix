// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.android.internal.util.Predicate;
import com.facebook.common.references.CloseableReference;

public interface MemoryCache<K, V>
{
    CloseableReference<V> cache(final K p0, final CloseableReference<V> p1);
    
    boolean contains(final Predicate<K> p0);
    
    CloseableReference<V> get(final K p0);
    
    int removeAll(final Predicate<K> p0);
}
