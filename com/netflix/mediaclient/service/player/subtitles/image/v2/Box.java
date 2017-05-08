// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

public abstract class Box
{
    protected static final String TAG = "nf_subtitles_imv2";
    protected BoxHeader boxHeader;
    
    public Box(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalArgumentException("Box header is null!");
        }
        this.boxHeader = boxHeader;
    }
    
    public BoxHeader getBoxHeader() {
        return this.boxHeader;
    }
    
    @Override
    public String toString() {
        return "Box{boxHeader=" + this.boxHeader + '}';
    }
}
