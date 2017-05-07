// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import android.view.View;
import com.netflix.mediaclient.Log;

class MeasureTranslator
{
    protected static final String TAG = "nf_subtitles_render";
    private int mHorizontalOffset;
    private float mScaleFactor;
    private int mVerticalOffset;
    
    private MeasureTranslator(final int n, final int n2, final int n3, final int n4) {
        this.mScaleFactor = 1.0f;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Device display area w/h: " + n3 + "/" + n4 + " and root container for images w/h: " + n + "/" + n2);
        }
        if (!isAspectRationFourByThree(n, n2)) {
            Log.d("nf_subtitles_render", "Movie gots whole width");
            this.mHorizontalOffset = 0;
            this.mScaleFactor = n4 / n2;
            this.mVerticalOffset = (n4 - (int)(n2 * this.mScaleFactor)) / 2;
            if (this.mVerticalOffset < 0) {
                Log.e("nf_subtitles_render", "Offset can not be negative!");
                this.mVerticalOffset = 0;
            }
        }
        else {
            Log.d("nf_subtitles_render", "Movie gots whole height");
            this.mVerticalOffset = 0;
            this.mScaleFactor = n3 / n;
            this.mHorizontalOffset = (n3 - (int)(n * this.mScaleFactor)) / 2;
            if (this.mHorizontalOffset < 0) {
                Log.e("nf_subtitles_render", "Offset can not be negative!");
                this.mHorizontalOffset = 0;
            }
        }
    }
    
    public static MeasureTranslator createMeasureTranslator(final int n, final int n2, final View view) {
        final int width = view.getWidth();
        final int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            Log.e("nf_subtitles_render", "DisplayView measures not know!");
            return null;
        }
        return new MeasureTranslator(n, n2, width, height);
    }
    
    private static boolean isAspectRationFourByThree(final int n, final int n2) {
        return n * 4 == n2 * 3;
    }
    
    public int getHorizontalOffset() {
        return this.mHorizontalOffset;
    }
    
    public float getScaleFactor() {
        return this.mScaleFactor;
    }
    
    public int getVerticalOffset() {
        return this.mVerticalOffset;
    }
    
    @Override
    public String toString() {
        return "MeasureTranslator [mScaleFactor=" + this.mScaleFactor + ", mVerticalOffset=" + this.mVerticalOffset + ", mHorizontalOffset=" + this.mHorizontalOffset + "]";
    }
}
