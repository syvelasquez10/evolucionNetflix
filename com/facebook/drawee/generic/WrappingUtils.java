// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.generic;

import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable$Callback;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import android.graphics.PointF;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import android.graphics.Matrix;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import android.os.Build$VERSION;
import com.facebook.drawee.drawable.RoundedLightBitmapDrawable;
import com.facebook.drawee.drawable.LightBitmapDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class WrappingUtils
{
    private static final Drawable sEmptyDrawable;
    
    static {
        sEmptyDrawable = (Drawable)new ColorDrawable(0);
    }
    
    private static Drawable applyLeafRounding(final Drawable drawable, final RoundingParams roundingParams, final Resources resources) {
        Object o;
        if (drawable instanceof BitmapDrawable) {
            final BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
            o = new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            applyRoundingParams((Rounded)o, roundingParams);
        }
        else {
            if (drawable instanceof LightBitmapDrawable) {
                final LightBitmapDrawable lightBitmapDrawable = (LightBitmapDrawable)drawable;
                final RoundedLightBitmapDrawable roundedLightBitmapDrawable = new RoundedLightBitmapDrawable(resources, lightBitmapDrawable.getBitmap(), lightBitmapDrawable.getPaint());
                applyRoundingParams(roundedLightBitmapDrawable, roundingParams);
                return roundedLightBitmapDrawable;
            }
            o = drawable;
            if (drawable instanceof ColorDrawable) {
                o = drawable;
                if (Build$VERSION.SDK_INT >= 11) {
                    final RoundedColorDrawable fromColorDrawable = RoundedColorDrawable.fromColorDrawable((ColorDrawable)drawable);
                    applyRoundingParams(fromColorDrawable, roundingParams);
                    return fromColorDrawable;
                }
            }
        }
        return (Drawable)o;
    }
    
    static void applyRoundingParams(final Rounded rounded, final RoundingParams roundingParams) {
        rounded.setCircle(roundingParams.getRoundAsCircle());
        rounded.setRadii(roundingParams.getCornersRadii());
        rounded.setBorder(roundingParams.getBorderColor(), roundingParams.getBorderWidth());
        rounded.setPadding(roundingParams.getPadding());
    }
    
    static DrawableParent findDrawableParentForLeaf(DrawableParent drawableParent) {
        while (true) {
            final Drawable drawable = drawableParent.getDrawable();
            if (drawable == drawableParent || !(drawable instanceof DrawableParent)) {
                break;
            }
            drawableParent = (DrawableParent)drawable;
        }
        return drawableParent;
    }
    
    static Drawable maybeApplyLeafRounding(final Drawable drawable, final RoundingParams roundingParams, final Resources resources) {
        if (drawable == null || roundingParams == null || roundingParams.getRoundingMethod() != RoundingParams$RoundingMethod.BITMAP_ONLY) {
            return drawable;
        }
        if (drawable instanceof ForwardingDrawable) {
            final DrawableParent drawableParentForLeaf = findDrawableParentForLeaf((DrawableParent)drawable);
            drawableParentForLeaf.setDrawable(applyLeafRounding(drawableParentForLeaf.setDrawable(WrappingUtils.sEmptyDrawable), roundingParams, resources));
            return drawable;
        }
        return applyLeafRounding(drawable, roundingParams, resources);
    }
    
    static Drawable maybeWrapWithMatrix(final Drawable drawable, final Matrix matrix) {
        if (drawable == null || matrix == null) {
            return drawable;
        }
        return new MatrixDrawable(drawable, matrix);
    }
    
    static Drawable maybeWrapWithRoundedOverlayColor(final Drawable drawable, final RoundingParams roundingParams) {
        if (drawable == null || roundingParams == null || roundingParams.getRoundingMethod() != RoundingParams$RoundingMethod.OVERLAY_COLOR) {
            return drawable;
        }
        final RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable(drawable);
        applyRoundingParams(roundedCornersDrawable, roundingParams);
        roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
        return roundedCornersDrawable;
    }
    
    static Drawable maybeWrapWithScaleType(final Drawable drawable, final ScalingUtils$ScaleType scalingUtils$ScaleType) {
        return maybeWrapWithScaleType(drawable, scalingUtils$ScaleType, null);
    }
    
    static Drawable maybeWrapWithScaleType(final Drawable drawable, final ScalingUtils$ScaleType scalingUtils$ScaleType, final PointF focusPoint) {
        if (drawable == null || scalingUtils$ScaleType == null) {
            return drawable;
        }
        final ScaleTypeDrawable scaleTypeDrawable = new ScaleTypeDrawable(drawable, scalingUtils$ScaleType);
        if (focusPoint != null) {
            scaleTypeDrawable.setFocusPoint(focusPoint);
        }
        return scaleTypeDrawable;
    }
    
    static void resetRoundingParams(final Rounded rounded) {
        rounded.setCircle(false);
        rounded.setRadius(0.0f);
        rounded.setBorder(0, 0.0f);
        rounded.setPadding(0.0f);
    }
    
    static void updateLeafRounding(DrawableParent drawableParentForLeaf, final RoundingParams roundingParams, final Resources resources) {
        drawableParentForLeaf = findDrawableParentForLeaf(drawableParentForLeaf);
        final Drawable drawable = drawableParentForLeaf.getDrawable();
        if (roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams$RoundingMethod.BITMAP_ONLY) {
            if (drawable instanceof Rounded) {
                applyRoundingParams((Rounded)drawable, roundingParams);
            }
            else if (drawable != null) {
                drawableParentForLeaf.setDrawable(WrappingUtils.sEmptyDrawable);
                drawableParentForLeaf.setDrawable(applyLeafRounding(drawable, roundingParams, resources));
            }
        }
        else if (drawable instanceof Rounded) {
            resetRoundingParams((Rounded)drawable);
        }
    }
    
    static void updateOverlayColorRounding(final DrawableParent drawableParent, final RoundingParams roundingParams) {
        final Drawable drawable = drawableParent.getDrawable();
        if (roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams$RoundingMethod.OVERLAY_COLOR) {
            if (!(drawable instanceof RoundedCornersDrawable)) {
                drawableParent.setDrawable(maybeWrapWithRoundedOverlayColor(drawableParent.setDrawable(WrappingUtils.sEmptyDrawable), roundingParams));
                return;
            }
            final RoundedCornersDrawable roundedCornersDrawable = (RoundedCornersDrawable)drawable;
            applyRoundingParams(roundedCornersDrawable, roundingParams);
            roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
        }
        else if (drawable instanceof RoundedCornersDrawable) {
            drawableParent.setDrawable(((RoundedCornersDrawable)drawable).setCurrent(WrappingUtils.sEmptyDrawable));
            WrappingUtils.sEmptyDrawable.setCallback((Drawable$Callback)null);
        }
    }
    
    static ScaleTypeDrawable wrapChildWithScaleType(final DrawableParent drawableParent, final ScalingUtils$ScaleType scalingUtils$ScaleType) {
        final Drawable maybeWrapWithScaleType = maybeWrapWithScaleType(drawableParent.setDrawable(WrappingUtils.sEmptyDrawable), scalingUtils$ScaleType);
        drawableParent.setDrawable(maybeWrapWithScaleType);
        Preconditions.checkNotNull(maybeWrapWithScaleType, "Parent has no child drawable!");
        return (ScaleTypeDrawable)maybeWrapWithScaleType;
    }
}
