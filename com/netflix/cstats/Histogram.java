// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.cstats;

interface Histogram<Sample>
{
    void addCount(final Sample p0, final int p1);
    
    void reset();
}
