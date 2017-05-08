// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.image.ImageInfo;
import android.graphics.drawable.Drawable$Callback;
import com.facebook.drawee.drawable.ForwardingDrawable;

public class DrawableWithIntrinsicSize extends ForwardingDrawable implements Drawable$Callback
{
    private final ImageInfo mImageInfo;
    
    public DrawableWithIntrinsicSize(final Drawable drawable, final ImageInfo mImageInfo) {
        super(drawable);
        this.mImageInfo = mImageInfo;
    }
    
    @Override
    public int getIntrinsicHeight() {
        return this.mImageInfo.getHeight();
    }
    
    @Override
    public int getIntrinsicWidth() {
        return this.mImageInfo.getWidth();
    }
}
