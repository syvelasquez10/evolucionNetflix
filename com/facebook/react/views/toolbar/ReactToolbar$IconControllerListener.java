// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Animatable;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.drawee.controller.BaseControllerListener;

abstract class ReactToolbar$IconControllerListener extends BaseControllerListener<ImageInfo>
{
    private final DraweeHolder mHolder;
    private ReactToolbar$IconImageInfo mIconImageInfo;
    final /* synthetic */ ReactToolbar this$0;
    
    public ReactToolbar$IconControllerListener(final ReactToolbar this$0, final DraweeHolder mHolder) {
        this.this$0 = this$0;
        this.mHolder = mHolder;
    }
    
    @Override
    public void onFinalImageSet(final String s, ImageInfo mIconImageInfo, final Animatable animatable) {
        super.onFinalImageSet(s, mIconImageInfo, animatable);
        if (this.mIconImageInfo != null) {
            mIconImageInfo = this.mIconImageInfo;
        }
        this.setDrawable(new DrawableWithIntrinsicSize(this.mHolder.getTopLevelDrawable(), mIconImageInfo));
    }
    
    protected abstract void setDrawable(final Drawable p0);
    
    public void setIconImageInfo(final ReactToolbar$IconImageInfo mIconImageInfo) {
        this.mIconImageInfo = mIconImageInfo;
    }
}
