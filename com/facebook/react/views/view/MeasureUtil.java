// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import android.view.View$MeasureSpec;
import com.facebook.yoga.YogaMeasureMode;

public class MeasureUtil
{
    public static int getMeasureSpec(final float n, final YogaMeasureMode yogaMeasureMode) {
        if (yogaMeasureMode == YogaMeasureMode.EXACTLY) {
            return View$MeasureSpec.makeMeasureSpec((int)n, 1073741824);
        }
        if (yogaMeasureMode == YogaMeasureMode.AT_MOST) {
            return View$MeasureSpec.makeMeasureSpec((int)n, Integer.MIN_VALUE);
        }
        return View$MeasureSpec.makeMeasureSpec(0, 0);
    }
}
