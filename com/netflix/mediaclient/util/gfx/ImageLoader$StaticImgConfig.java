// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

public enum ImageLoader$StaticImgConfig
{
    DARK(2130837647, 2130837647), 
    DARK_NO_PLACEHOLDER(0, 2130837647), 
    LIGHT(2130837834, 2130837834), 
    LIGHT_NO_PLACEHOLDER(0, 2130837834);
    
    public static final int NO_PLACEHOLDER = 0;
    private final int onFailResId;
    private final int placeholderResId;
    
    private ImageLoader$StaticImgConfig(final int placeholderResId, final int onFailResId) {
        this.placeholderResId = placeholderResId;
        this.onFailResId = onFailResId;
    }
    
    public int getOnFailResId() {
        return this.onFailResId;
    }
    
    public int getPlaceholderResId() {
        return this.placeholderResId;
    }
    
    public boolean shouldShowPlaceholder() {
        return this.placeholderResId != 0;
    }
}
