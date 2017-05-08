// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface ReadableMap
{
    ReadableArray getArray(final String p0);
    
    boolean getBoolean(final String p0);
    
    double getDouble(final String p0);
    
    int getInt(final String p0);
    
    ReadableMap getMap(final String p0);
    
    String getString(final String p0);
    
    ReadableType getType(final String p0);
    
    boolean hasKey(final String p0);
    
    boolean isNull(final String p0);
    
    ReadableMapKeySetIterator keySetIterator();
}
