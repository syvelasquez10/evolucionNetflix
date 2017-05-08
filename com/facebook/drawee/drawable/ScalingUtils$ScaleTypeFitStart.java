// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

class ScalingUtils$ScaleTypeFitStart extends ScalingUtils$AbstractScaleType
{
    public static final ScalingUtils$ScaleType INSTANCE;
    
    static {
        INSTANCE = new ScalingUtils$ScaleTypeFitStart();
    }
    
    @Override
    public void getTransformImpl(final Matrix matrix, final Rect rect, final int n, final int n2, float min, float n3, float n4, final float n5) {
        min = Math.min(n4, n5);
        n3 = rect.left;
        n4 = rect.top;
        matrix.setScale(min, min);
        matrix.postTranslate((float)(int)(n3 + 0.5f), (float)(int)(n4 + 0.5f));
    }
}
