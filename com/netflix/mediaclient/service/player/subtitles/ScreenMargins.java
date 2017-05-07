// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import android.util.Pair;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;

public class ScreenMargins
{
    private static final String TAG = "nf_subtitles";
    private float mHorizontalMargin;
    private float mHorizontalScale;
    private float mVerticalMargin;
    private float mVerticalScale;
    
    public ScreenMargins() {
        this.mHorizontalMargin = 0.0f;
        this.mHorizontalScale = 1.0f;
        this.mVerticalMargin = 0.0f;
        this.mVerticalScale = 1.0f;
    }
    
    public static ScreenMargins getScreenMargins(final String s, final String s2, float n) {
        final ScreenMargins screenMargins = new ScreenMargins();
        if (!StringUtils.isEmpty(s)) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Calculate AspectDiff using extent " + s);
            }
            final Pair<Integer, Integer> numberPair = StringUtils.extractNumberPair(s);
            float n2;
            float n3;
            if (numberPair != null) {
                final Pair<Integer, Integer> numberPair2 = StringUtils.extractNumberPair(s2);
                if (numberPair2 != null) {
                    n2 = (int)numberPair2.first * (int)numberPair.first;
                    n3 = (int)numberPair2.first * (int)numberPair.second;
                }
                else {
                    n2 = (int)numberPair.first;
                    n3 = (int)numberPair.second;
                }
            }
            else {
                n3 = 0.0f;
                n2 = 0.0f;
            }
            float n4;
            if (n2 > 0.0f && n3 > 0.0f) {
                n4 = n2 / n3;
            }
            else {
                n4 = 1.0f;
            }
            if (n <= 0.0f) {
                n = 1.0f;
            }
            final float mHorizontalScale = n4 / n;
            if (Math.abs(n - n4) > 0.01) {
                if (n < n4) {
                    Log.e("nf_subtitles", "not implemented when videoAspect < aspect");
                    return screenMargins;
                }
                screenMargins.mHorizontalMargin = (1.0f - mHorizontalScale) / 2.0f;
                screenMargins.mHorizontalScale = mHorizontalScale;
            }
            return screenMargins;
        }
        Log.d("nf_subtitles", "Default AspectDiff");
        return screenMargins;
    }
    
    public float getHorizontalMargin() {
        return this.mHorizontalMargin;
    }
    
    public float getHorizontalScale() {
        return this.mHorizontalScale;
    }
    
    public float getVerticalMargin() {
        return this.mVerticalMargin;
    }
    
    public float getVerticalScale() {
        return this.mVerticalScale;
    }
}
