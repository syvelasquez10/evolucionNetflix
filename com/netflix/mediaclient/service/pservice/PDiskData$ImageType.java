// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

public enum PDiskData$ImageType
{
    HORIZONTAL_ART("horizontalArt"), 
    STORY_ART("storyArt"), 
    TITLE_CARD("titleCard"), 
    TRICKPLAY("trickplay"), 
    UNKNOWN("");
    
    private String value;
    
    private PDiskData$ImageType(final String value) {
        this.value = value;
    }
    
    public static PDiskData$ImageType create(final String s) {
        final PDiskData$ImageType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PDiskData$ImageType pDiskData$ImageType = values[i];
            if (pDiskData$ImageType.value.equalsIgnoreCase(s)) {
                return pDiskData$ImageType;
            }
        }
        return PDiskData$ImageType.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
