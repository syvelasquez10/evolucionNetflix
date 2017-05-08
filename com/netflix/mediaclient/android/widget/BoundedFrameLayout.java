// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$MeasureSpec;
import android.content.res.TypedArray;
import com.netflix.android.widgetry.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

public class BoundedFrameLayout extends FrameLayout
{
    private final int mMaxHeight;
    private final int mMaxWidth;
    
    public BoundedFrameLayout(final Context context) {
        super(context);
        this.mMaxWidth = 0;
        this.mMaxHeight = 0;
    }
    
    public BoundedFrameLayout(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.BoundedFrameLayout);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.BoundedFrameLayout_bfl_maxWidth, 0);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R$styleable.BoundedFrameLayout_bfl_maxHeight, 0);
        obtainStyledAttributes.recycle();
    }
    
    protected void onMeasure(int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n);
        int measureSpec = n;
        if (this.mMaxWidth > 0) {
            measureSpec = n;
            if (this.mMaxWidth < size) {
                n = View$MeasureSpec.getMode(n);
                measureSpec = View$MeasureSpec.makeMeasureSpec(this.mMaxWidth, n);
            }
        }
        final int size2 = View$MeasureSpec.getSize(n2);
        n = n2;
        if (this.mMaxHeight > 0) {
            n = n2;
            if (this.mMaxHeight < size2) {
                n = View$MeasureSpec.getMode(n2);
                n = View$MeasureSpec.makeMeasureSpec(this.mMaxHeight, n);
            }
        }
        super.onMeasure(measureSpec, n);
    }
}
