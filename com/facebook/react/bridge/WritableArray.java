// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface WritableArray extends ReadableArray
{
    void pushArray(final WritableArray p0);
    
    void pushBoolean(final boolean p0);
    
    void pushInt(final int p0);
    
    void pushMap(final WritableMap p0);
    
    void pushNull();
    
    void pushString(final String p0);
}
