// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

import java.util.Map;

public final class MapBuilder$Builder<K, V>
{
    private Map mMap;
    private boolean mUnderConstruction;
    
    private MapBuilder$Builder() {
        this.mMap = MapBuilder.newHashMap();
        this.mUnderConstruction = true;
    }
    
    public Map<K, V> build() {
        if (!this.mUnderConstruction) {
            throw new IllegalStateException("Underlying map has already been built");
        }
        this.mUnderConstruction = false;
        return (Map<K, V>)this.mMap;
    }
    
    public MapBuilder$Builder<K, V> put(final K k, final V v) {
        if (!this.mUnderConstruction) {
            throw new IllegalStateException("Underlying map has already been built");
        }
        this.mMap.put(k, v);
        return this;
    }
}
