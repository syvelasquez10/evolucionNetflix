// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.Matrix;
import android.util.AttributeSet;
import android.content.Context;

public class FocalPointImageView extends TopCropImageView
{
    private static final String TAG = "FocalPointImageView";
    private float cropPointXOffsetPercent;
    private float cropPointYOffsetPercent;
    
    public FocalPointImageView(final Context context) {
        super(context);
        this.cropPointXOffsetPercent = 0.0f;
        this.cropPointYOffsetPercent = 0.0f;
    }
    
    public FocalPointImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.cropPointXOffsetPercent = 0.0f;
        this.cropPointYOffsetPercent = 0.0f;
    }
    
    public FocalPointImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.cropPointXOffsetPercent = 0.0f;
        this.cropPointYOffsetPercent = 0.0f;
    }
    
    @Override
    public void recomputeImgMatrix() {
        final Matrix imageMatrix = new Matrix(this.getImageMatrix());
        final int n = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        final int n2 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int intrinsicWidth;
        if (this.getDrawable() == null) {
            intrinsicWidth = 0;
        }
        else {
            intrinsicWidth = this.getDrawable().getIntrinsicWidth();
        }
        int intrinsicHeight;
        if (this.getDrawable() == null) {
            intrinsicHeight = 0;
        }
        else {
            intrinsicHeight = this.getDrawable().getIntrinsicHeight();
        }
        float n3;
        if (intrinsicWidth * n2 > intrinsicHeight * n) {
            n3 = n2 / intrinsicHeight;
        }
        else {
            n3 = n / intrinsicWidth;
        }
        final float n4 = intrinsicWidth;
        final float cropPointXOffsetPercent = this.cropPointXOffsetPercent;
        final float cropPointXOffsetPercent2 = this.cropPointXOffsetPercent;
        final float n5 = this.getWidth();
        final float n6 = intrinsicWidth;
        final float n7 = intrinsicHeight;
        final float cropPointXOffsetPercent3 = this.cropPointXOffsetPercent;
        final float cropPointXOffsetPercent4 = this.cropPointXOffsetPercent;
        final float n8 = this.getHeight();
        final float n9 = intrinsicHeight;
        imageMatrix.reset();
        imageMatrix.postTranslate(-intrinsicWidth * this.cropPointXOffsetPercent, -intrinsicHeight * this.cropPointXOffsetPercent);
        imageMatrix.postScale(n3, n3);
        imageMatrix.postTranslate(n4 * n3 * cropPointXOffsetPercent + cropPointXOffsetPercent2 * (n5 - n6 * n3), n7 * n3 * cropPointXOffsetPercent3 + cropPointXOffsetPercent4 * (n8 - n9 * n3));
        this.setImageMatrix(imageMatrix);
    }
    
    public void setCropPointXOffsetPercent(final float cropPointXOffsetPercent) {
        this.cropPointXOffsetPercent = cropPointXOffsetPercent;
        this.recomputeImgMatrix();
    }
    
    public void setCropPointYOffsetPercent(final float cropPointYOffsetPercent) {
        this.cropPointYOffsetPercent = cropPointYOffsetPercent;
        this.recomputeImgMatrix();
    }
}
