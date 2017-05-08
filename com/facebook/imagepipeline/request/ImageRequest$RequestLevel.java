// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.request;

public enum ImageRequest$RequestLevel
{
    BITMAP_MEMORY_CACHE(4), 
    DISK_CACHE(2), 
    ENCODED_MEMORY_CACHE(3), 
    FULL_FETCH(1);
    
    private int mValue;
    
    private ImageRequest$RequestLevel(final int mValue) {
        this.mValue = mValue;
    }
    
    public static ImageRequest$RequestLevel getMax(final ImageRequest$RequestLevel imageRequest$RequestLevel, final ImageRequest$RequestLevel imageRequest$RequestLevel2) {
        if (imageRequest$RequestLevel.getValue() > imageRequest$RequestLevel2.getValue()) {
            return imageRequest$RequestLevel;
        }
        return imageRequest$RequestLevel2;
    }
    
    public int getValue() {
        return this.mValue;
    }
}
