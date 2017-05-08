// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

public interface Url
{
    Url$Connection openConnection();
    
    void setTimeout(final int p0);
}
