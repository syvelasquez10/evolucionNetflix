// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;

public class AspectRatioImageView extends ImageView
{
    public static final int MEASURE_BY_HEIGHT = 1;
    public static final int MEASURE_BY_WIDTH = 0;
    private int mMeasureBy;
    
    public AspectRatioImageView(final Context context) {
        super(context);
        this.mMeasureBy = 1;
    }
    
    public AspectRatioImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mMeasureBy = 1;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.AspectRatioImageView);
        this.mMeasureBy = obtainStyledAttributes.getInt(0, 1);
        obtainStyledAttributes.recycle();
    }
    
    public AspectRatioImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mMeasureBy = 1;
    }
    
    public int getMeasureBy() {
        return this.mMeasureBy;
    }
    
    protected void onMeasure(int n, int n2) {
        final Drawable drawable = this.getDrawable();
        if (drawable != null) {
            if (this.mMeasureBy == 0) {
                final int size = View$MeasureSpec.getSize(n);
                final int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth <= 0) {
                    super.onMeasure(n, n2);
                    return;
                }
                n = drawable.getIntrinsicHeight() * size / intrinsicWidth;
                n2 = size;
            }
            else {
                final int size2 = View$MeasureSpec.getSize(n2);
                final int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight <= 0) {
                    super.onMeasure(n, n2);
                    return;
                }
                n2 = drawable.getIntrinsicWidth() * size2 / intrinsicHeight;
                n = size2;
            }
            this.setMeasuredDimension(n2, n);
            return;
        }
        super.onMeasure(n, n2);
    }
    
    public void setMeasureBy(final int mMeasureBy) {
        if (mMeasureBy != 1 && mMeasureBy != 0) {
            throw new IllegalArgumentException("nvalid measureBy type");
        }
        this.mMeasureBy = mMeasureBy;
        this.requestLayout();
    }
}
