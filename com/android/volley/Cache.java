// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

public interface Cache
{
    void clear();
    
    Cache$Entry get(final String p0);
    
    void initialize();
    
    void invalidate(final String p0, final boolean p1);
    
    void put(final String p0, final Cache$Entry p1);
    
    void remove(final String p0);
}
