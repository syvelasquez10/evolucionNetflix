// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.graphics.Paint$FontMetricsInt;
import android.text.style.LineHeightSpan;

public class CustomLineHeightSpan implements LineHeightSpan
{
    private final int mHeight;
    
    CustomLineHeightSpan(final float n) {
        this.mHeight = (int)Math.ceil(n);
    }
    
    public void chooseHeight(final CharSequence charSequence, int n, final int n2, final int n3, final int n4, final Paint$FontMetricsInt paint$FontMetricsInt) {
        if (-paint$FontMetricsInt.ascent > this.mHeight) {
            n = -this.mHeight;
            paint$FontMetricsInt.ascent = n;
            paint$FontMetricsInt.top = n;
            paint$FontMetricsInt.descent = 0;
            paint$FontMetricsInt.bottom = 0;
            return;
        }
        if (-paint$FontMetricsInt.ascent + paint$FontMetricsInt.descent > this.mHeight) {
            paint$FontMetricsInt.top = paint$FontMetricsInt.ascent;
            n = this.mHeight + paint$FontMetricsInt.ascent;
            paint$FontMetricsInt.descent = n;
            paint$FontMetricsInt.bottom = n;
            return;
        }
        if (-paint$FontMetricsInt.ascent + paint$FontMetricsInt.bottom > this.mHeight) {
            paint$FontMetricsInt.top = paint$FontMetricsInt.ascent;
            paint$FontMetricsInt.bottom = paint$FontMetricsInt.ascent + this.mHeight;
            return;
        }
        if (-paint$FontMetricsInt.top + paint$FontMetricsInt.bottom > this.mHeight) {
            paint$FontMetricsInt.top = paint$FontMetricsInt.bottom - this.mHeight;
            return;
        }
        n = this.mHeight - (-paint$FontMetricsInt.top + paint$FontMetricsInt.bottom);
        paint$FontMetricsInt.top -= n;
        paint$FontMetricsInt.ascent -= n;
    }
}
