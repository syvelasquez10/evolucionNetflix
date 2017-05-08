// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

public interface BitReader
{
    boolean bit();
    
    long nrBits();
    
    boolean pad(final int p0);
    
    int read(final int p0);
}
