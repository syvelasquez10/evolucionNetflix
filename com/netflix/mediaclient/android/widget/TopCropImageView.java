// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.res.TypedArray;
import com.netflix.mediaclient.Log;
import android.graphics.Matrix;
import com.netflix.mediaclient.R$styleable;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;

public class TopCropImageView extends AdvancedImageView
{
    private static final String TAG = "TopCropImageView";
    private boolean centerHorizontally;
    private int cropPointYOffset;
    private boolean isTopCropEnabled;
    private String lastScaleType;
    
    public TopCropImageView(final Context context) {
        super(context);
        this.isTopCropEnabled = true;
        this.centerHorizontally = false;
        this.init(null);
    }
    
    public TopCropImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.isTopCropEnabled = true;
        this.centerHorizontally = false;
        this.init(set);
    }
    
    public TopCropImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.isTopCropEnabled = true;
        this.centerHorizontally = false;
        this.init(set);
    }
    
    private void init(AttributeSet obtainStyledAttributes) {
        final String attributeValue = obtainStyledAttributes.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleType");
        Label_0059: {
            if (!StringUtils.isEmpty(attributeValue)) {
                break Label_0059;
            }
            this.setScaleType(ImageView$ScaleType.MATRIX);
            this.isTopCropEnabled = true;
            while (true) {
                obtainStyledAttributes = (AttributeSet)this.getContext().obtainStyledAttributes(obtainStyledAttributes, R$styleable.TopCropImageView, 0, 0);
                try {
                    this.cropPointYOffset = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(0, 0);
                    return;
                    this.lastScaleType = attributeValue;
                    this.isTopCropEnabled = false;
                }
                finally {
                    ((TypedArray)obtainStyledAttributes).recycle();
                }
            }
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.recomputeImgMatrix();
    }
    
    public void recomputeImgMatrix() {
        if (!this.isTopCropEnabled) {
            return;
        }
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
        if (Log.isLoggable()) {
            Log.v("TopCropImageView", "Matrix scale: " + n3 + ", drawable height: " + intrinsicHeight + ", drawable width: " + intrinsicWidth + ", view height: " + n2 + ", view width: " + n + ", crop y-offset: " + this.cropPointYOffset);
        }
        imageMatrix.setScale(n3, n3, 0.0f, (float)this.cropPointYOffset);
        if (this.centerHorizontally && intrinsicWidth * n3 > n) {
            imageMatrix.postTranslate(-((intrinsicWidth * n3 - n) / 2.0f), 0.0f);
        }
        this.setImageMatrix(imageMatrix);
    }
    
    public void setCenterHorizontally(final boolean centerHorizontally) {
        this.centerHorizontally = centerHorizontally;
        this.recomputeImgMatrix();
    }
    
    public void setCropPointYOffsetPx(final int cropPointYOffset) {
        this.cropPointYOffset = cropPointYOffset;
        this.recomputeImgMatrix();
    }
    
    public void setCutomCroppingEnabled(final boolean isTopCropEnabled) {
        this.isTopCropEnabled = isTopCropEnabled;
        if (isTopCropEnabled) {
            this.setScaleType(ImageView$ScaleType.MATRIX);
            this.recomputeImgMatrix();
        }
        else if (!StringUtils.isEmpty(this.lastScaleType)) {
            this.setScaleType(ImageView$ScaleType.values()[Integer.valueOf(this.lastScaleType)]);
        }
    }
    
    protected boolean setFrame(final int n, final int n2, final int n3, final int n4) {
        this.recomputeImgMatrix();
        return super.setFrame(n, n2, n3, n4);
    }
}
