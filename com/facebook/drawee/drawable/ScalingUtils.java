// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

public class ScalingUtils
{
    @Deprecated
    public static Matrix getTransform(final Matrix matrix, final Rect rect, final int n, final int n2, final float n3, final float n4, final ScalingUtils$ScaleType scalingUtils$ScaleType) {
        return scalingUtils$ScaleType.getTransform(matrix, rect, n, n2, n3, n4);
    }
}
