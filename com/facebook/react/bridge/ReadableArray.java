// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface ReadableArray
{
    ReadableArray getArray(final int p0);
    
    boolean getBoolean(final int p0);
    
    double getDouble(final int p0);
    
    int getInt(final int p0);
    
    ReadableMap getMap(final int p0);
    
    String getString(final int p0);
    
    ReadableType getType(final int p0);
    
    boolean isNull(final int p0);
    
    int size();
}
