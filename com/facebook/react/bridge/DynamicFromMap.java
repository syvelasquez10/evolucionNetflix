// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.support.v4.util.Pools$SimplePool;

public class DynamicFromMap implements Dynamic
{
    private static final Pools$SimplePool<DynamicFromMap> sPool;
    private ReadableMap mMap;
    private String mName;
    
    static {
        sPool = new Pools$SimplePool<DynamicFromMap>(10);
    }
    
    public static DynamicFromMap create(final ReadableMap mMap, final String mName) {
        DynamicFromMap dynamicFromMap;
        if ((dynamicFromMap = DynamicFromMap.sPool.acquire()) == null) {
            dynamicFromMap = new DynamicFromMap();
        }
        dynamicFromMap.mMap = mMap;
        dynamicFromMap.mName = mName;
        return dynamicFromMap;
    }
    
    @Override
    public double asDouble() {
        if (this.mMap == null || this.mName == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mMap.getDouble(this.mName);
    }
    
    @Override
    public String asString() {
        if (this.mMap == null || this.mName == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mMap.getString(this.mName);
    }
    
    @Override
    public ReadableType getType() {
        if (this.mMap == null || this.mName == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mMap.getType(this.mName);
    }
    
    @Override
    public boolean isNull() {
        if (this.mMap == null || this.mName == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mMap.isNull(this.mName);
    }
    
    @Override
    public void recycle() {
        this.mMap = null;
        this.mName = null;
        DynamicFromMap.sPool.release(this);
    }
}
