// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;

class RCTImageView<T extends AbstractDrawCommand> extends FlatShadowNode
{
    static Object sCallerContext;
    private T mDrawImage;
    
    static {
        RCTImageView.sCallerContext = RCTImageView.class;
    }
    
    RCTImageView(final T mDrawImage) {
        this.mDrawImage = (AbstractDrawCommand)mDrawImage;
    }
    
    static Object getCallerContext() {
        return RCTImageView.sCallerContext;
    }
    
    private T getMutableDrawImage() {
        if (this.mDrawImage.isFrozen()) {
            this.mDrawImage = this.mDrawImage.mutableCopy();
            this.invalidate();
        }
        return (T)this.mDrawImage;
    }
    
    @Override
    public void setBorder(final int n, final float borderWidth) {
        super.setBorder(n, borderWidth);
        if (n == 8 && ((DrawImage)this.mDrawImage).getBorderWidth() != borderWidth) {
            this.getMutableDrawImage().setBorderWidth(borderWidth);
        }
    }
    
    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(final int borderColor) {
        if (((DrawImage)this.mDrawImage).getBorderColor() != borderColor) {
            this.getMutableDrawImage().setBorderColor(borderColor);
        }
    }
    
    @ReactProp(name = "borderRadius")
    public void setBorderRadius(final float n) {
        if (((DrawImage)this.mDrawImage).getBorderRadius() != n) {
            this.getMutableDrawImage().setBorderRadius(PixelUtil.toPixelFromDIP(n));
        }
    }
    
    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(final int fadeDuration) {
        this.getMutableDrawImage().setFadeDuration(fadeDuration);
    }
    
    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(final boolean progressiveRenderingEnabled) {
        this.getMutableDrawImage().setProgressiveRenderingEnabled(progressiveRenderingEnabled);
    }
    
    @ReactProp(name = "resizeMode")
    public void setResizeMode(final String s) {
        final ScalingUtils$ScaleType scaleType = ImageResizeMode.toScaleType(s);
        if (((DrawImage)this.mDrawImage).getScaleType() != scaleType) {
            this.getMutableDrawImage().setScaleType(scaleType);
        }
    }
    
    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setShouldNotifyLoadEvents(final boolean b) {
        final DrawImage drawImage = this.getMutableDrawImage();
        int reactTag;
        if (b) {
            reactTag = this.getReactTag();
        }
        else {
            reactTag = 0;
        }
        drawImage.setReactTag(reactTag);
    }
    
    @ReactProp(name = "src")
    public void setSource(final ReadableArray readableArray) {
        this.getMutableDrawImage().setSource((Context)this.getThemedContext(), readableArray);
    }
    
    @ReactProp(name = "tintColor")
    public void setTintColor(final int tintColor) {
        this.getMutableDrawImage().setTintColor(tintColor);
    }
}
