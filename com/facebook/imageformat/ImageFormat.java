// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageformat;

public enum ImageFormat
{
    BMP, 
    GIF, 
    JPEG, 
    PNG, 
    UNKNOWN, 
    WEBP_ANIMATED, 
    WEBP_EXTENDED, 
    WEBP_EXTENDED_WITH_ALPHA, 
    WEBP_LOSSLESS, 
    WEBP_SIMPLE;
    
    public static boolean isWebpFormat(final ImageFormat imageFormat) {
        return imageFormat == ImageFormat.WEBP_SIMPLE || imageFormat == ImageFormat.WEBP_LOSSLESS || imageFormat == ImageFormat.WEBP_EXTENDED || imageFormat == ImageFormat.WEBP_EXTENDED_WITH_ALPHA || imageFormat == ImageFormat.WEBP_ANIMATED;
    }
}
