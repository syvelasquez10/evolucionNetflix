// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

public abstract class ScalingUtils$AbstractScaleType implements ScalingUtils$ScaleType
{
    @Override
    public Matrix getTransform(final Matrix matrix, final Rect rect, final int n, final int n2, final float n3, final float n4) {
        this.getTransformImpl(matrix, rect, n, n2, n3, n4, rect.width() / n, rect.height() / n2);
        return matrix;
    }
    
    public abstract void getTransformImpl(final Matrix p0, final Rect p1, final int p2, final int p3, final float p4, final float p5, final float p6, final float p7);
}
