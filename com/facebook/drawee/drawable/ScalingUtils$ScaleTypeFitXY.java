// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

class ScalingUtils$ScaleTypeFitXY extends ScalingUtils$AbstractScaleType
{
    public static final ScalingUtils$ScaleType INSTANCE;
    
    static {
        INSTANCE = new ScalingUtils$ScaleTypeFitXY();
    }
    
    @Override
    public void getTransformImpl(final Matrix matrix, final Rect rect, final int n, final int n2, float n3, float n4, final float n5, final float n6) {
        n3 = rect.left;
        n4 = rect.top;
        matrix.setScale(n5, n6);
        matrix.postTranslate((float)(int)(n3 + 0.5f), (float)(int)(n4 + 0.5f));
    }
}
