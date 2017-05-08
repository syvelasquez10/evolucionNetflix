// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import android.graphics.Bitmap$Config;

public final class AdvancedImageView$ImageLoaderInfo
{
    public final Bitmap$Config bitmapConfig;
    public final String imageUrl;
    public final ImageLoader$StaticImgConfig imgViewConfig;
    public boolean loaded;
    
    public AdvancedImageView$ImageLoaderInfo(final String imageUrl, final ImageLoader$StaticImgConfig imgViewConfig, final Bitmap$Config bitmapConfig) {
        this.loaded = false;
        this.imageUrl = imageUrl;
        this.imgViewConfig = imgViewConfig;
        this.bitmapConfig = bitmapConfig;
    }
    
    public void setLoaded(final boolean loaded) {
        this.loaded = loaded;
    }
}
