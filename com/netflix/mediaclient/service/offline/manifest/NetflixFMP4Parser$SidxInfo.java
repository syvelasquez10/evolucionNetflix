// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

public class NetflixFMP4Parser$SidxInfo
{
    private final long mSidxOffset;
    private final long mSidxlength;
    
    NetflixFMP4Parser$SidxInfo(final long mSidxlength, final long mSidxOffset) {
        this.mSidxlength = mSidxlength;
        this.mSidxOffset = mSidxOffset;
    }
    
    long getSidxOffset() {
        return this.mSidxOffset;
    }
    
    long getSidxlength() {
        return this.mSidxlength;
    }
}
