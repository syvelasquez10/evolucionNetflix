// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface WritableMap extends ReadableMap
{
    void putArray(final String p0, final WritableArray p1);
    
    void putBoolean(final String p0, final boolean p1);
    
    void putDouble(final String p0, final double p1);
    
    void putInt(final String p0, final int p1);
    
    void putMap(final String p0, final WritableMap p1);
    
    void putString(final String p0, final String p1);
}
