// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.generic;

import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Animatable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.DrawableParent;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import java.util.Iterator;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import android.graphics.drawable.ColorDrawable;
import android.content.res.Resources;
import com.facebook.drawee.drawable.FadeDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;

public class GenericDraweeHierarchy implements SettableDraweeHierarchy
{
    private final ForwardingDrawable mActualImageWrapper;
    private final Drawable mEmptyActualImageDrawable;
    private final FadeDrawable mFadeDrawable;
    private final Resources mResources;
    private RoundingParams mRoundingParams;
    private final RootDrawable mTopLevelDrawable;
    
    GenericDraweeHierarchy(final GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        final int n = 0;
        this.mEmptyActualImageDrawable = (Drawable)new ColorDrawable(0);
        this.mResources = genericDraweeHierarchyBuilder.getResources();
        this.mRoundingParams = genericDraweeHierarchyBuilder.getRoundingParams();
        this.mActualImageWrapper = new ForwardingDrawable(this.mEmptyActualImageDrawable);
        int size;
        if (genericDraweeHierarchyBuilder.getOverlays() != null) {
            size = genericDraweeHierarchyBuilder.getOverlays().size();
        }
        else {
            size = 1;
        }
        int n2;
        if (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final int n3 = size + n2;
        final Drawable[] array = new Drawable[n3 + 6];
        array[0] = this.buildBranch(genericDraweeHierarchyBuilder.getBackground(), null);
        array[1] = this.buildBranch(genericDraweeHierarchyBuilder.getPlaceholderImage(), genericDraweeHierarchyBuilder.getPlaceholderImageScaleType());
        array[2] = this.buildActualImageBranch(this.mActualImageWrapper, genericDraweeHierarchyBuilder.getActualImageScaleType(), genericDraweeHierarchyBuilder.getActualImageFocusPoint(), genericDraweeHierarchyBuilder.getActualImageMatrix(), genericDraweeHierarchyBuilder.getActualImageColorFilter());
        array[3] = this.buildBranch(genericDraweeHierarchyBuilder.getProgressBarImage(), genericDraweeHierarchyBuilder.getProgressBarImageScaleType());
        array[4] = this.buildBranch(genericDraweeHierarchyBuilder.getRetryImage(), genericDraweeHierarchyBuilder.getRetryImageScaleType());
        array[5] = this.buildBranch(genericDraweeHierarchyBuilder.getFailureImage(), genericDraweeHierarchyBuilder.getFailureImageScaleType());
        if (n3 > 0) {
            int n5;
            if (genericDraweeHierarchyBuilder.getOverlays() != null) {
                final Iterator<Drawable> iterator = genericDraweeHierarchyBuilder.getOverlays().iterator();
                int n4 = n;
                while (true) {
                    n5 = n4;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    array[n4 + 6] = this.buildBranch(iterator.next(), null);
                    ++n4;
                }
            }
            else {
                n5 = 1;
            }
            if (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null) {
                array[n5 + 6] = this.buildBranch(genericDraweeHierarchyBuilder.getPressedStateOverlay(), null);
            }
        }
        (this.mFadeDrawable = new FadeDrawable(array)).setTransitionDuration(genericDraweeHierarchyBuilder.getFadeDuration());
        (this.mTopLevelDrawable = new RootDrawable(WrappingUtils.maybeWrapWithRoundedOverlayColor(this.mFadeDrawable, this.mRoundingParams))).mutate();
        this.resetFade();
    }
    
    private Drawable buildActualImageBranch(final Drawable drawable, final ScalingUtils$ScaleType scalingUtils$ScaleType, final PointF pointF, final Matrix matrix, final ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
        return WrappingUtils.maybeWrapWithMatrix(WrappingUtils.maybeWrapWithScaleType(drawable, scalingUtils$ScaleType, pointF), matrix);
    }
    
    private Drawable buildBranch(final Drawable drawable, final ScalingUtils$ScaleType scalingUtils$ScaleType) {
        return WrappingUtils.maybeWrapWithScaleType(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources), scalingUtils$ScaleType);
    }
    
    private void fadeInLayer(final int n) {
        if (n >= 0) {
            this.mFadeDrawable.fadeInLayer(n);
        }
    }
    
    private void fadeOutBranches() {
        this.fadeOutLayer(1);
        this.fadeOutLayer(2);
        this.fadeOutLayer(3);
        this.fadeOutLayer(4);
        this.fadeOutLayer(5);
    }
    
    private void fadeOutLayer(final int n) {
        if (n >= 0) {
            this.mFadeDrawable.fadeOutLayer(n);
        }
    }
    
    private DrawableParent getParentDrawableAtIndex(final int n) {
        MatrixDrawable drawableParentForIndex;
        final DrawableParent drawableParent = drawableParentForIndex = (MatrixDrawable)this.mFadeDrawable.getDrawableParentForIndex(n);
        if (drawableParent.getDrawable() instanceof MatrixDrawable) {
            drawableParentForIndex = (MatrixDrawable)drawableParent.getDrawable();
        }
        ForwardingDrawable forwardingDrawable = drawableParentForIndex;
        if (drawableParentForIndex.getDrawable() instanceof ScaleTypeDrawable) {
            forwardingDrawable = (ScaleTypeDrawable)drawableParentForIndex.getDrawable();
        }
        return forwardingDrawable;
    }
    
    private ScaleTypeDrawable getScaleTypeDrawableAtIndex(final int n) {
        final DrawableParent parentDrawableAtIndex = this.getParentDrawableAtIndex(n);
        if (parentDrawableAtIndex instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable)parentDrawableAtIndex;
        }
        return WrappingUtils.wrapChildWithScaleType(parentDrawableAtIndex, ScalingUtils$ScaleType.FIT_XY);
    }
    
    private void resetActualImages() {
        this.mActualImageWrapper.setDrawable(this.mEmptyActualImageDrawable);
    }
    
    private void resetFade() {
        if (this.mFadeDrawable != null) {
            this.mFadeDrawable.beginBatchMode();
            this.mFadeDrawable.fadeInAllLayers();
            this.fadeOutBranches();
            this.fadeInLayer(1);
            this.mFadeDrawable.finishTransitionImmediately();
            this.mFadeDrawable.endBatchMode();
        }
    }
    
    private void setChildDrawableAtIndex(final int n, Drawable maybeApplyLeafRounding) {
        if (maybeApplyLeafRounding == null) {
            this.mFadeDrawable.setDrawable(n, null);
            return;
        }
        maybeApplyLeafRounding = WrappingUtils.maybeApplyLeafRounding(maybeApplyLeafRounding, this.mRoundingParams, this.mResources);
        this.getParentDrawableAtIndex(n).setDrawable(maybeApplyLeafRounding);
    }
    
    private void setProgress(final float n) {
        final Drawable drawable = this.getParentDrawableAtIndex(3).getDrawable();
        if (drawable == null) {
            return;
        }
        if (n >= 0.999f) {
            if (drawable instanceof Animatable) {
                ((Animatable)drawable).stop();
            }
            this.fadeOutLayer(3);
        }
        else {
            if (drawable instanceof Animatable) {
                ((Animatable)drawable).start();
            }
            this.fadeInLayer(3);
        }
        drawable.setLevel(Math.round(10000.0f * n));
    }
    
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }
    
    @Override
    public Drawable getTopLevelDrawable() {
        return this.mTopLevelDrawable;
    }
    
    @Override
    public void reset() {
        this.resetActualImages();
        this.resetFade();
    }
    
    public void setActualImageColorFilter(final ColorFilter colorFilter) {
        this.mActualImageWrapper.setColorFilter(colorFilter);
    }
    
    public void setActualImageScaleType(final ScalingUtils$ScaleType scaleType) {
        Preconditions.checkNotNull(scaleType);
        this.getScaleTypeDrawableAtIndex(2).setScaleType(scaleType);
    }
    
    @Override
    public void setControllerOverlay(final Drawable controllerOverlay) {
        this.mTopLevelDrawable.setControllerOverlay(controllerOverlay);
    }
    
    public void setFadeDuration(final int transitionDuration) {
        this.mFadeDrawable.setTransitionDuration(transitionDuration);
    }
    
    @Override
    public void setFailure(final Throwable t) {
        this.mFadeDrawable.beginBatchMode();
        this.fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(5) != null) {
            this.fadeInLayer(5);
        }
        else {
            this.fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }
    
    @Override
    public void setImage(Drawable maybeApplyLeafRounding, final float progress, final boolean b) {
        maybeApplyLeafRounding = WrappingUtils.maybeApplyLeafRounding(maybeApplyLeafRounding, this.mRoundingParams, this.mResources);
        maybeApplyLeafRounding.mutate();
        this.mActualImageWrapper.setDrawable(maybeApplyLeafRounding);
        this.mFadeDrawable.beginBatchMode();
        this.fadeOutBranches();
        this.fadeInLayer(2);
        this.setProgress(progress);
        if (b) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }
    
    public void setPlaceholderImage(final Drawable drawable, final ScalingUtils$ScaleType scaleType) {
        this.setChildDrawableAtIndex(1, drawable);
        this.getScaleTypeDrawableAtIndex(1).setScaleType(scaleType);
    }
    
    @Override
    public void setProgress(final float progress, final boolean b) {
        this.mFadeDrawable.beginBatchMode();
        this.setProgress(progress);
        if (b) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }
    
    @Override
    public void setRetry(final Throwable t) {
        this.mFadeDrawable.beginBatchMode();
        this.fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(4) != null) {
            this.fadeInLayer(4);
        }
        else {
            this.fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }
    
    public void setRoundingParams(final RoundingParams mRoundingParams) {
        this.mRoundingParams = mRoundingParams;
        WrappingUtils.updateOverlayColorRounding(this.mTopLevelDrawable, this.mRoundingParams);
        for (int i = 0; i < this.mFadeDrawable.getNumberOfLayers(); ++i) {
            WrappingUtils.updateLeafRounding(this.getParentDrawableAtIndex(i), this.mRoundingParams, this.mResources);
        }
    }
}
