// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

public interface ScalingUtils$ScaleType
{
    public static final ScalingUtils$ScaleType CENTER = ScalingUtils$ScaleTypeCenter.INSTANCE;
    public static final ScalingUtils$ScaleType CENTER_CROP = ScalingUtils$ScaleTypeCenterCrop.INSTANCE;
    public static final ScalingUtils$ScaleType CENTER_INSIDE = ScalingUtils$ScaleTypeCenterInside.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_CENTER = ScalingUtils$ScaleTypeFitCenter.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_END = ScalingUtils$ScaleTypeFitEnd.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_START = ScalingUtils$ScaleTypeFitStart.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_XY = ScalingUtils$ScaleTypeFitXY.INSTANCE;
    public static final ScalingUtils$ScaleType FOCUS_CROP = ScalingUtils$ScaleTypeFocusCrop.INSTANCE;
    
    Matrix getTransform(final Matrix p0, final Rect p1, final int p2, final int p3, final float p4, final float p5);
}
