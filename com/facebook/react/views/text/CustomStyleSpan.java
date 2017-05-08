// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.text.TextPaint;
import android.graphics.Typeface;
import android.graphics.Paint;
import android.content.res.AssetManager;
import android.text.style.MetricAffectingSpan;

public class CustomStyleSpan extends MetricAffectingSpan
{
    private final AssetManager mAssetManager;
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;
    
    public CustomStyleSpan(final int mStyle, final int mWeight, final String mFontFamily, final AssetManager mAssetManager) {
        this.mStyle = mStyle;
        this.mWeight = mWeight;
        this.mFontFamily = mFontFamily;
        this.mAssetManager = mAssetManager;
    }
    
    private static void apply(final Paint paint, final int n, int n2, final String s, final AssetManager assetManager) {
        final boolean b = false;
        final Typeface typeface = paint.getTypeface();
        int style;
        if (typeface == null) {
            style = 0;
        }
        else {
            style = typeface.getStyle();
        }
        int n3 = 0;
        Label_0045: {
            if (n2 != 1) {
                n3 = (b ? 1 : 0);
                if ((style & 0x1) == 0x0) {
                    break Label_0045;
                }
                n3 = (b ? 1 : 0);
                if (n2 != -1) {
                    break Label_0045;
                }
            }
            n3 = 1;
        }
        Label_0073: {
            if (n != 2) {
                n2 = n3;
                if ((style & 0x2) == 0x0) {
                    break Label_0073;
                }
                n2 = n3;
                if (n != -1) {
                    break Label_0073;
                }
            }
            n2 = (n3 | 0x2);
        }
        Typeface typeface2;
        if (s != null) {
            typeface2 = ReactFontManager.getInstance().getTypeface(s, n2, assetManager);
        }
        else if ((typeface2 = typeface) != null) {
            typeface2 = Typeface.create(typeface, n2);
        }
        if (typeface2 != null) {
            paint.setTypeface(typeface2);
            return;
        }
        paint.setTypeface(Typeface.defaultFromStyle(n2));
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        apply((Paint)textPaint, this.mStyle, this.mWeight, this.mFontFamily, this.mAssetManager);
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
        apply((Paint)textPaint, this.mStyle, this.mWeight, this.mFontFamily, this.mAssetManager);
    }
}
