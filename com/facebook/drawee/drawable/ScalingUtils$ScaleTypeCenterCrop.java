// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

class ScalingUtils$ScaleTypeCenterCrop extends ScalingUtils$AbstractScaleType
{
    public static final ScalingUtils$ScaleType INSTANCE;
    
    static {
        INSTANCE = new ScalingUtils$ScaleTypeCenterCrop();
    }
    
    @Override
    public void getTransformImpl(final Matrix matrix, final Rect rect, final int n, final int n2, float n3, float n4, final float n5, float n6) {
        if (n6 > n5) {
            n3 = rect.left;
            n4 = (rect.width() - n * n6) * 0.5f + n3;
            n3 = rect.top;
        }
        else {
            n4 = rect.left;
            n3 = rect.top + (rect.height() - n2 * n5) * 0.5f;
            n6 = n5;
        }
        matrix.setScale(n6, n6);
        matrix.postTranslate((float)(int)(n4 + 0.5f), (float)(int)(n3 + 0.5f));
    }
}
