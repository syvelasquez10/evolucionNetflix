// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.Log;
import android.graphics.Matrix;
import android.widget.ImageView$ScaleType;
import android.util.AttributeSet;
import android.content.Context;

public class TopCropImageView extends AdvancedImageView
{
    private static final String TAG = "TopCropImageView";
    
    public TopCropImageView(final Context context) {
        super(context);
        this.init();
    }
    
    public TopCropImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public TopCropImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.setScaleType(ImageView$ScaleType.MATRIX);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.recomputeImgMatrix();
    }
    
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
        if (Log.isLoggable("TopCropImageView", 2)) {
            Log.v("TopCropImageView", "Matrix scale: " + n3 + ", drawable height: " + intrinsicHeight + ", drawable width: " + intrinsicWidth + ", view height: " + n2 + ", view width: " + n);
        }
        imageMatrix.setScale(n3, n3);
        this.setImageMatrix(imageMatrix);
    }
    
    protected boolean setFrame(final int n, final int n2, final int n3, final int n4) {
        this.recomputeImgMatrix();
        return super.setFrame(n, n2, n3, n4);
    }
}
