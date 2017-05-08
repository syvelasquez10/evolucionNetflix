// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

enum MediaUtils$VideoDecoderClassfier
{
    AVC(2, 2048, 512, 1), 
    HEVC(1, 1024, 256, 1), 
    VP9(1, 64, 32, 1);
    
    private int fhdLevel;
    private int hdLevel;
    private int interestedProfile;
    private int lowestLevel;
    
    private MediaUtils$VideoDecoderClassfier(final int interestedProfile, final int fhdLevel, final int hdLevel, final int lowestLevel) {
        this.interestedProfile = interestedProfile;
        this.fhdLevel = fhdLevel;
        this.hdLevel = hdLevel;
        this.lowestLevel = lowestLevel;
    }
    
    int getFhdLevel() {
        return this.fhdLevel;
    }
    
    int getHdLevel() {
        return this.hdLevel;
    }
    
    int getInterestedProfile() {
        return this.interestedProfile;
    }
    
    int getLowestLevel() {
        return this.lowestLevel;
    }
    
    String getName() {
        return this.name();
    }
}
