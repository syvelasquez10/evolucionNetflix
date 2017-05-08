// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

class ScalingUtils$ScaleTypeFocusCrop extends ScalingUtils$AbstractScaleType
{
    public static final ScalingUtils$ScaleType INSTANCE;
    
    static {
        INSTANCE = new ScalingUtils$ScaleTypeFocusCrop();
    }
    
    @Override
    public void getTransformImpl(final Matrix matrix, final Rect rect, final int n, final int n2, float n3, float n4, float n5, float n6) {
        if (n6 > n5) {
            n4 = rect.width();
            n5 = n;
            n4 = rect.left + Math.max(Math.min(n4 * 0.5f - n5 * n6 * n3, 0.0f), rect.width() - n * n6);
            n3 = rect.top;
        }
        else {
            n6 = rect.left;
            n3 = rect.height();
            n3 = Math.max(Math.min(n3 * 0.5f - n2 * n5 * n4, 0.0f), rect.height() - n2 * n5) + rect.top;
            n4 = n6;
            n6 = n5;
        }
        matrix.setScale(n6, n6);
        matrix.postTranslate((float)(int)(n4 + 0.5f), (float)(int)(n3 + 0.5f));
    }
}
