// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.cstatssamurai;

import java.util.Map;
import java.util.LinkedHashMap;

public class LruCache<K, V> extends LinkedHashMap<K, V>
{
    private final int mMaxSize;
    
    public LruCache(final int mMaxSize) {
        super(mMaxSize, 0.75f, true);
        this.mMaxSize = mMaxSize;
    }
    
    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> entry) {
        return this.size() > this.mMaxSize;
    }
}
