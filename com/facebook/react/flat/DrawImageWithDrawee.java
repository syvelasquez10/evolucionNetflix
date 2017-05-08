// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.PorterDuff$Mode;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.Canvas;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import android.graphics.ColorFilter;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.react.views.image.ImageResizeMode;
import java.util.LinkedList;
import com.facebook.react.views.imagehelper.ImageSource;
import java.util.List;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import android.graphics.PorterDuffColorFilter;
import com.facebook.drawee.controller.ControllerListener;

final class DrawImageWithDrawee extends AbstractDrawCommand implements ControllerListener, DrawImage
{
    private int mBorderColor;
    private float mBorderRadius;
    private float mBorderWidth;
    private FlatViewGroup$InvalidateCallback mCallback;
    private PorterDuffColorFilter mColorFilter;
    private int mFadeDuration;
    private boolean mProgressiveRenderingEnabled;
    private int mReactTag;
    private DraweeRequestHelper mRequestHelper;
    private ScalingUtils$ScaleType mScaleType;
    private final List<ImageSource> mSources;
    
    DrawImageWithDrawee() {
        this.mSources = new LinkedList<ImageSource>();
        this.mScaleType = ImageResizeMode.defaultValue();
        this.mFadeDuration = 300;
    }
    
    private boolean shouldDisplayBorder() {
        return this.mBorderColor != 0 || this.mBorderRadius >= 0.5f;
    }
    
    @Override
    public int getBorderColor() {
        return this.mBorderColor;
    }
    
    @Override
    public float getBorderRadius() {
        return this.mBorderRadius;
    }
    
    @Override
    public float getBorderWidth() {
        return this.mBorderWidth;
    }
    
    @Override
    public ScalingUtils$ScaleType getScaleType() {
        return this.mScaleType;
    }
    
    @Override
    public void onAttached(final FlatViewGroup$InvalidateCallback mCallback) {
        this.mCallback = mCallback;
        if (this.mRequestHelper == null) {
            throw new RuntimeException("No DraweeRequestHelper - width: " + (this.getRight() - this.getLeft()) + " - height: " + (this.getBottom() - this.getTop()) + " - number of sources: " + this.mSources.size());
        }
        final GenericDraweeHierarchy hierarchy = this.mRequestHelper.getHierarchy();
        final RoundingParams roundingParams = hierarchy.getRoundingParams();
        if (this.shouldDisplayBorder()) {
            RoundingParams roundingParams2;
            if ((roundingParams2 = roundingParams) == null) {
                roundingParams2 = new RoundingParams();
            }
            roundingParams2.setBorder(this.mBorderColor, this.mBorderWidth);
            roundingParams2.setCornersRadius(this.mBorderRadius);
            hierarchy.setRoundingParams(roundingParams2);
        }
        else if (roundingParams != null) {
            hierarchy.setRoundingParams(null);
        }
        hierarchy.setActualImageScaleType(this.mScaleType);
        hierarchy.setActualImageColorFilter((ColorFilter)this.mColorFilter);
        hierarchy.setFadeDuration(this.mFadeDuration);
        hierarchy.getTopLevelDrawable().setBounds(Math.round(this.getLeft()), Math.round(this.getTop()), Math.round(this.getRight()), Math.round(this.getBottom()));
        this.mRequestHelper.attach(mCallback);
    }
    
    @Override
    public void onDetached() {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.detach();
        }
    }
    
    public void onDraw(final Canvas canvas) {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.getDrawable().draw(canvas);
        }
    }
    
    @Override
    public void onFailure(final String s, final Throwable t) {
        if (this.mCallback != null && this.mReactTag != 0) {
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 1);
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 3);
        }
    }
    
    @Override
    public void onFinalImageSet(final String s, final Object o, final Animatable animatable) {
        if (this.mCallback != null && this.mReactTag != 0) {
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 2);
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 3);
        }
    }
    
    @Override
    public void onIntermediateImageFailed(final String s, final Throwable t) {
    }
    
    @Override
    public void onIntermediateImageSet(final String s, final Object o) {
    }
    
    @Override
    public void onRelease(final String s) {
    }
    
    @Override
    public void onSubmit(final String s, final Object o) {
        if (this.mCallback != null && this.mReactTag != 0) {
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 4);
        }
    }
    
    @Override
    public void setBorderColor(final int mBorderColor) {
        this.mBorderColor = mBorderColor;
    }
    
    @Override
    public void setBorderRadius(final float mBorderRadius) {
        this.mBorderRadius = mBorderRadius;
    }
    
    @Override
    public void setBorderWidth(final float mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
    }
    
    @Override
    public void setFadeDuration(final int mFadeDuration) {
        this.mFadeDuration = mFadeDuration;
    }
    
    @Override
    public void setProgressiveRenderingEnabled(final boolean mProgressiveRenderingEnabled) {
        this.mProgressiveRenderingEnabled = mProgressiveRenderingEnabled;
    }
    
    @Override
    public void setReactTag(final int mReactTag) {
        this.mReactTag = mReactTag;
    }
    
    @Override
    public void setScaleType(final ScalingUtils$ScaleType mScaleType) {
        this.mScaleType = mScaleType;
    }
    
    @Override
    public void setSource(final Context context, final ReadableArray readableArray) {
        int i = 0;
        this.mSources.clear();
        if (readableArray != null && readableArray.size() != 0) {
            if (readableArray.size() == 1) {
                this.mSources.add(new ImageSource(context, readableArray.getMap(0).getString("uri")));
            }
            else {
                while (i < readableArray.size()) {
                    final ReadableMap map = readableArray.getMap(i);
                    this.mSources.add(new ImageSource(context, map.getString("uri"), map.getDouble("width"), map.getDouble("height")));
                    ++i;
                }
            }
        }
    }
    
    @Override
    public void setTintColor(final int n) {
        if (n == 0) {
            this.mColorFilter = null;
            return;
        }
        this.mColorFilter = new PorterDuffColorFilter(n, PorterDuff$Mode.SRC_ATOP);
    }
}
