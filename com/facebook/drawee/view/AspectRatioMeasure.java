// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.view;

import android.view.View;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;

public class AspectRatioMeasure
{
    private static boolean shouldAdjust(final int n) {
        return n == 0 || n == -2;
    }
    
    public static void updateMeasureSpec(final AspectRatioMeasure$Spec aspectRatioMeasure$Spec, final float n, final ViewGroup$LayoutParams viewGroup$LayoutParams, final int n2, final int n3) {
        if (n > 0.0f && viewGroup$LayoutParams != null) {
            if (shouldAdjust(viewGroup$LayoutParams.height)) {
                aspectRatioMeasure$Spec.height = View$MeasureSpec.makeMeasureSpec(View.resolveSize((int)((View$MeasureSpec.getSize(aspectRatioMeasure$Spec.width) - n2) / n + n3), aspectRatioMeasure$Spec.height), 1073741824);
                return;
            }
            if (shouldAdjust(viewGroup$LayoutParams.width)) {
                aspectRatioMeasure$Spec.width = View$MeasureSpec.makeMeasureSpec(View.resolveSize((int)((View$MeasureSpec.getSize(aspectRatioMeasure$Spec.height) - n3) * n + n2), aspectRatioMeasure$Spec.width), 1073741824);
            }
        }
    }
}
