// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Matrix;

class ScalingUtils$ScaleTypeCenter extends ScalingUtils$AbstractScaleType
{
    public static final ScalingUtils$ScaleType INSTANCE;
    
    static {
        INSTANCE = new ScalingUtils$ScaleTypeCenter();
    }
    
    @Override
    public void getTransformImpl(final Matrix matrix, final Rect rect, final int n, final int n2, float n3, float n4, float n5, float n6) {
        n3 = rect.left;
        n4 = rect.width() - n;
        n5 = rect.top;
        n6 = rect.height() - n2;
        matrix.setTranslate((float)(int)(n3 + n4 * 0.5f + 0.5f), (float)(int)(n5 + n6 * 0.5f + 0.5f));
    }
}
