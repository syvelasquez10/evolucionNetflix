// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

class ScalingUtils$ScaleTypeFitCenter extends ScalingUtils$AbstractScaleType
{
    public static final ScalingUtils$ScaleType INSTANCE;
    
    static {
        INSTANCE = new ScalingUtils$ScaleTypeFitCenter();
    }
    
    @Override
    public void getTransformImpl(final Matrix matrix, final Rect rect, final int n, final int n2, float min, float n3, float n4, float n5) {
        min = Math.min(n4, n5);
        n3 = rect.left;
        n4 = rect.width();
        n5 = n;
        final float n6 = rect.top;
        final float n7 = rect.height();
        final float n8 = n2;
        matrix.setScale(min, min);
        matrix.postTranslate((float)(int)(n3 + (n4 - n5 * min) * 0.5f + 0.5f), (float)(int)(n6 + (n7 - n8 * min) * 0.5f + 0.5f));
    }
}
