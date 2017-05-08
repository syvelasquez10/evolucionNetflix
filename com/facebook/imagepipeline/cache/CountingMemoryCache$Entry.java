// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;

class CountingMemoryCache$Entry<K, V>
{
    public int clientCount;
    public boolean isOrphan;
    public final K key;
    public final CountingMemoryCache$EntryStateObserver<K> observer;
    public final CloseableReference<V> valueRef;
    
    private CountingMemoryCache$Entry(final K k, final CloseableReference<V> closeableReference, final CountingMemoryCache$EntryStateObserver<K> observer) {
        this.key = Preconditions.checkNotNull(k);
        this.valueRef = Preconditions.checkNotNull(CloseableReference.cloneOrNull(closeableReference));
        this.clientCount = 0;
        this.isOrphan = false;
        this.observer = observer;
    }
    
    static <K, V> CountingMemoryCache$Entry<K, V> of(final K k, final CloseableReference<V> closeableReference, final CountingMemoryCache$EntryStateObserver<K> countingMemoryCache$EntryStateObserver) {
        return new CountingMemoryCache$Entry<K, V>(k, closeableReference, countingMemoryCache$EntryStateObserver);
    }
}
