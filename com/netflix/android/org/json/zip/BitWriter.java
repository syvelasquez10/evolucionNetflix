// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

public interface BitWriter
{
    long nrBits();
    
    void one();
    
    void pad(final int p0);
    
    void write(final int p0, final int p1);
    
    void zero();
}
