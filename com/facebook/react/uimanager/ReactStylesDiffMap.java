// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

public class ReactStylesDiffMap
{
    final ReadableMap mBackingMap;
    
    public ReactStylesDiffMap(final ReadableMap mBackingMap) {
        this.mBackingMap = mBackingMap;
    }
    
    public ReadableArray getArray(final String s) {
        return this.mBackingMap.getArray(s);
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        if (this.mBackingMap.isNull(s)) {
            return b;
        }
        return this.mBackingMap.getBoolean(s);
    }
    
    public double getDouble(final String s, final double n) {
        if (this.mBackingMap.isNull(s)) {
            return n;
        }
        return this.mBackingMap.getDouble(s);
    }
    
    public Dynamic getDynamic(final String s) {
        return this.mBackingMap.getDynamic(s);
    }
    
    public float getFloat(final String s, final float n) {
        if (this.mBackingMap.isNull(s)) {
            return n;
        }
        return (float)this.mBackingMap.getDouble(s);
    }
    
    public int getInt(final String s, final int n) {
        if (this.mBackingMap.isNull(s)) {
            return n;
        }
        return this.mBackingMap.getInt(s);
    }
    
    public ReadableMap getMap(final String s) {
        return this.mBackingMap.getMap(s);
    }
    
    public String getString(final String s) {
        return this.mBackingMap.getString(s);
    }
    
    public boolean hasKey(final String s) {
        return this.mBackingMap.hasKey(s);
    }
    
    public boolean isNull(final String s) {
        return this.mBackingMap.isNull(s);
    }
    
    @Override
    public String toString() {
        return "{ " + this.getClass().getSimpleName() + ": " + this.mBackingMap.toString() + " }";
    }
}
