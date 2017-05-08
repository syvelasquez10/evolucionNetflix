// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.support.v4.util.Pools$SimplePool;

public class DynamicFromArray implements Dynamic
{
    private static final Pools$SimplePool<DynamicFromArray> sPool;
    private ReadableArray mArray;
    private int mIndex;
    
    static {
        sPool = new Pools$SimplePool<DynamicFromArray>(10);
    }
    
    private DynamicFromArray() {
        this.mIndex = -1;
    }
    
    public static DynamicFromArray create(final ReadableArray mArray, final int mIndex) {
        DynamicFromArray dynamicFromArray;
        if ((dynamicFromArray = DynamicFromArray.sPool.acquire()) == null) {
            dynamicFromArray = new DynamicFromArray();
        }
        dynamicFromArray.mArray = mArray;
        dynamicFromArray.mIndex = mIndex;
        return dynamicFromArray;
    }
    
    @Override
    public double asDouble() {
        if (this.mArray == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mArray.getDouble(this.mIndex);
    }
    
    @Override
    public String asString() {
        if (this.mArray == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mArray.getString(this.mIndex);
    }
    
    @Override
    public ReadableType getType() {
        if (this.mArray == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mArray.getType(this.mIndex);
    }
    
    @Override
    public boolean isNull() {
        if (this.mArray == null) {
            throw new IllegalStateException("This dynamic value has been recycled");
        }
        return this.mArray.isNull(this.mIndex);
    }
    
    @Override
    public void recycle() {
        this.mArray = null;
        this.mIndex = -1;
        DynamicFromArray.sPool.release(this);
    }
}
