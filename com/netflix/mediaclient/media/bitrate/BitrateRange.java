// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.bitrate;

public abstract class BitrateRange
{
    protected int maximal;
    protected int minimal;
    
    public BitrateRange(final int minimal, final int maximal) {
        this.minimal = minimal;
        this.maximal = maximal;
    }
    
    public int getMaximal() {
        return this.maximal;
    }
    
    public int getMinimal() {
        return this.minimal;
    }
}
