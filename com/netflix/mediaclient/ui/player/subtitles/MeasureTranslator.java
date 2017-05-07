// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import android.view.View;
import com.netflix.mediaclient.Log;

class MeasureTranslator
{
    protected static final String TAG = "nf_subtitles_render";
    private int mDeviceHeight;
    private int mDeviceWidth;
    private int mHorizontalOffset;
    private int mRootContainerHeight;
    private int mRootContainerWidth;
    private float mScaleFactor;
    private int mVerticalOffset;
    
    private MeasureTranslator(final int mRootContainerWidth, final int mRootContainerHeight, final int mDeviceWidth, final int mDeviceHeight) {
        this.mScaleFactor = 1.0f;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Device display area w/h: " + mDeviceWidth + "/" + mDeviceHeight + " and root container for images w/h: " + mRootContainerWidth + "/" + mRootContainerHeight);
        }
        this.mRootContainerHeight = mRootContainerHeight;
        this.mRootContainerWidth = mRootContainerWidth;
        this.mDeviceHeight = mDeviceHeight;
        this.mDeviceWidth = mDeviceWidth;
        final float mScaleFactor = mDeviceWidth / mRootContainerWidth;
        final float mScaleFactor2 = mDeviceHeight / mRootContainerHeight;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Scale factors w/h: " + mScaleFactor + "/" + mScaleFactor2);
        }
        if (!isAspectRationFourByThree(mRootContainerWidth, mRootContainerHeight)) {
            Log.d("nf_subtitles_render", "Movie gots whole width");
            this.mHorizontalOffset = 0;
            this.mScaleFactor = mScaleFactor;
            this.mVerticalOffset = (mDeviceHeight - (int)(mRootContainerHeight * this.mScaleFactor)) / 2;
            if (this.mVerticalOffset < 0) {
                Log.e("nf_subtitles_render", "Offset can not be negative!");
                this.mVerticalOffset = 0;
            }
        }
        else {
            Log.d("nf_subtitles_render", "Movie gots whole height");
            this.mVerticalOffset = 0;
            this.mScaleFactor = mScaleFactor2;
            this.mHorizontalOffset = (mDeviceWidth - (int)(mRootContainerWidth * this.mScaleFactor)) / 2;
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
    
    public int getDeviceHeight() {
        return this.mDeviceHeight;
    }
    
    public int getDeviceWidth() {
        return this.mDeviceWidth;
    }
    
    public int getHorizontalOffset() {
        return this.mHorizontalOffset;
    }
    
    public int getRootContainerHeight() {
        return this.mRootContainerHeight;
    }
    
    public int getRootContainerWidth() {
        return this.mRootContainerWidth;
    }
    
    public float getScaleFactor() {
        return this.mScaleFactor;
    }
    
    public int getVerticalOffset() {
        return this.mVerticalOffset;
    }
    
    @Override
    public String toString() {
        return "MeasureTranslator [mScaleFactor=" + this.mScaleFactor + ", mVerticalOffset=" + this.mVerticalOffset + ", mHorizontalOffset=" + this.mHorizontalOffset + ", mRootContainerWidth=" + this.mRootContainerWidth + ", mRootContainerHeight=" + this.mRootContainerHeight + ", mDeviceWidth=" + this.mDeviceWidth + ", mDeviceHeight=" + this.mDeviceHeight + "]";
    }
}
