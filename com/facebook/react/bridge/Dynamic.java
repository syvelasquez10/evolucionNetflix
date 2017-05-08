// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface Dynamic
{
    double asDouble();
    
    String asString();
    
    ReadableType getType();
    
    boolean isNull();
    
    void recycle();
}
