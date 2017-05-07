// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

public interface Cache
{
    Cache$Entry get(final String p0);
    
    void initialize();
    
    void put(final String p0, final Cache$Entry p1);
}
