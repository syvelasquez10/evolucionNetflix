// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.res.TypedArray;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup
{
    private int mHorizontalSpacing;
    private int mMaxWidth;
    private int mVerticalSpacing;
    
    public FlowLayout(Context obtainStyledAttributes, final AttributeSet set) {
        super(obtainStyledAttributes, set);
        this.mMaxWidth = 0;
        obtainStyledAttributes = (Context)obtainStyledAttributes.obtainStyledAttributes(set, R$styleable.FlowLayout);
        try {
            this.mHorizontalSpacing = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(0, 0);
            this.mVerticalSpacing = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(1, 0);
            this.mMaxWidth = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(2, this.mMaxWidth);
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof FlowLayout$LayoutParams;
    }
    
    public FlowLayout$LayoutParams generateDefaultLayoutParams() {
        return new FlowLayout$LayoutParams(-2, -2);
    }
    
    public FlowLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new FlowLayout$LayoutParams(this.getContext(), set);
    }
    
    protected FlowLayout$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new FlowLayout$LayoutParams(viewGroup$LayoutParams.width, viewGroup$LayoutParams.height);
    }
    
    protected void onLayout(final boolean b, int i, int childCount, final int n, final int n2) {
        View child;
        FlowLayout$LayoutParams flowLayout$LayoutParams;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            flowLayout$LayoutParams = (FlowLayout$LayoutParams)child.getLayoutParams();
            child.layout(flowLayout$LayoutParams.x, flowLayout$LayoutParams.y, flowLayout$LayoutParams.x + child.getMeasuredWidth(), flowLayout$LayoutParams.y + child.getMeasuredHeight());
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int n3 = View$MeasureSpec.getSize(n) - this.getPaddingRight() - this.getPaddingLeft();
        final int mode = View$MeasureSpec.getMode(n);
        final int layoutDirection = this.getLayoutDirection();
        final boolean b = mode != 0;
        int min = n3;
        if (this.mMaxWidth > 0) {
            min = Math.min(n3, this.mMaxWidth);
        }
        int paddingTop = this.getPaddingTop();
        int paddingLeft = this.getPaddingLeft();
        final int childCount = this.getChildCount();
        int i = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int max = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                this.measureChild(child, n, n2);
                final FlowLayout$LayoutParams flowLayout$LayoutParams = (FlowLayout$LayoutParams)child.getLayoutParams();
                n4 = this.mHorizontalSpacing;
                if (flowLayout$LayoutParams.horizontalSpacing >= 0) {
                    n4 = flowLayout$LayoutParams.horizontalSpacing;
                }
                int paddingLeft2;
                int n7;
                int n8;
                if (b && child.getMeasuredWidth() + paddingLeft > min) {
                    final int mVerticalSpacing = this.mVerticalSpacing;
                    max = Math.max(max, paddingLeft - n4);
                    paddingLeft2 = this.getPaddingLeft();
                    final boolean b2 = true;
                    paddingTop += n6 + mVerticalSpacing;
                    n7 = 0;
                    n8 = (b2 ? 1 : 0);
                }
                else {
                    final int n9 = n6;
                    n8 = 0;
                    paddingLeft2 = paddingLeft;
                    n7 = n9;
                }
                if (layoutDirection == 1) {
                    flowLayout$LayoutParams.x = min - paddingLeft2 - child.getMeasuredWidth();
                }
                else {
                    flowLayout$LayoutParams.x = paddingLeft2;
                }
                flowLayout$LayoutParams.y = paddingTop;
                final int n10 = paddingLeft2 + (child.getMeasuredWidth() + n4);
                final int max2 = Math.max(n7, child.getMeasuredHeight());
                n5 = n8;
                n6 = max2;
                paddingLeft = n10;
            }
            ++i;
        }
        int max3 = max;
        if (n5 == 0) {
            max3 = Math.max(max, paddingLeft - n4);
        }
        final int n11 = max3 + this.getPaddingRight();
        final int paddingBottom = this.getPaddingBottom();
        if (layoutDirection == 1 && this.getLayoutParams().width == -2 && min != n11) {
            for (int j = 0; j < childCount; ++j) {
                final FlowLayout$LayoutParams flowLayout$LayoutParams2 = (FlowLayout$LayoutParams)this.getChildAt(j).getLayoutParams();
                flowLayout$LayoutParams2.x -= min - n11;
            }
        }
        this.setMeasuredDimension(resolveSize(n11, n), resolveSize(paddingTop + (paddingBottom + n6), n2));
    }
}
