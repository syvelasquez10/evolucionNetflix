// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.generic;

import java.util.Iterator;
import com.facebook.common.internal.Preconditions;
import android.content.res.Resources;
import java.util.List;
import android.graphics.drawable.Drawable;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.ColorFilter;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;

public class GenericDraweeHierarchyBuilder
{
    public static final ScalingUtils$ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
    public static final ScalingUtils$ScaleType DEFAULT_SCALE_TYPE;
    private ColorFilter mActualImageColorFilter;
    private PointF mActualImageFocusPoint;
    private Matrix mActualImageMatrix;
    private ScalingUtils$ScaleType mActualImageScaleType;
    private Drawable mBackground;
    private float mDesiredAspectRatio;
    private int mFadeDuration;
    private Drawable mFailureImage;
    private ScalingUtils$ScaleType mFailureImageScaleType;
    private List<Drawable> mOverlays;
    private Drawable mPlaceholderImage;
    private ScalingUtils$ScaleType mPlaceholderImageScaleType;
    private Drawable mPressedStateOverlay;
    private Drawable mProgressBarImage;
    private ScalingUtils$ScaleType mProgressBarImageScaleType;
    private Resources mResources;
    private Drawable mRetryImage;
    private ScalingUtils$ScaleType mRetryImageScaleType;
    private RoundingParams mRoundingParams;
    
    static {
        DEFAULT_SCALE_TYPE = ScalingUtils$ScaleType.CENTER_INSIDE;
        DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScalingUtils$ScaleType.CENTER_CROP;
    }
    
    public GenericDraweeHierarchyBuilder(final Resources mResources) {
        this.mResources = mResources;
        this.init();
    }
    
    private void init() {
        this.mFadeDuration = 300;
        this.mDesiredAspectRatio = 0.0f;
        this.mPlaceholderImage = null;
        this.mPlaceholderImageScaleType = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        this.mRetryImage = null;
        this.mRetryImageScaleType = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        this.mFailureImage = null;
        this.mFailureImageScaleType = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        this.mActualImageScaleType = GenericDraweeHierarchyBuilder.DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mActualImageColorFilter = null;
        this.mBackground = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
    }
    
    public static GenericDraweeHierarchyBuilder newInstance(final Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }
    
    private void validate() {
        if (this.mOverlays != null) {
            final Iterator<Drawable> iterator = this.mOverlays.iterator();
            while (iterator.hasNext()) {
                Preconditions.checkNotNull(iterator.next());
            }
        }
    }
    
    public GenericDraweeHierarchy build() {
        this.validate();
        return new GenericDraweeHierarchy(this);
    }
    
    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }
    
    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }
    
    public Matrix getActualImageMatrix() {
        return this.mActualImageMatrix;
    }
    
    public ScalingUtils$ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }
    
    public Drawable getBackground() {
        return this.mBackground;
    }
    
    public int getFadeDuration() {
        return this.mFadeDuration;
    }
    
    public Drawable getFailureImage() {
        return this.mFailureImage;
    }
    
    public ScalingUtils$ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }
    
    public List<Drawable> getOverlays() {
        return this.mOverlays;
    }
    
    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }
    
    public ScalingUtils$ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }
    
    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }
    
    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }
    
    public ScalingUtils$ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }
    
    public Resources getResources() {
        return this.mResources;
    }
    
    public Drawable getRetryImage() {
        return this.mRetryImage;
    }
    
    public ScalingUtils$ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }
    
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }
    
    public GenericDraweeHierarchyBuilder setActualImageScaleType(final ScalingUtils$ScaleType mActualImageScaleType) {
        this.mActualImageScaleType = mActualImageScaleType;
        this.mActualImageMatrix = null;
        return this;
    }
    
    public GenericDraweeHierarchyBuilder setFadeDuration(final int mFadeDuration) {
        this.mFadeDuration = mFadeDuration;
        return this;
    }
    
    public GenericDraweeHierarchyBuilder setRoundingParams(final RoundingParams mRoundingParams) {
        this.mRoundingParams = mRoundingParams;
        return this;
    }
}
