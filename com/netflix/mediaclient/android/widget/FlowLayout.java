// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup
{
    private int mHorizontalSpacing;
    private int mVerticalSpacing;
    
    public FlowLayout(final Context context, final AttributeSet set) {
        super(context, set);
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
        final int size = View$MeasureSpec.getSize(n);
        final int paddingRight = this.getPaddingRight();
        boolean b;
        if (View$MeasureSpec.getMode(n) != 0) {
            b = true;
        }
        else {
            b = false;
        }
        int paddingTop = this.getPaddingTop();
        int x = this.getPaddingLeft();
        boolean b2 = false;
        int n3 = 0;
        final int childCount = this.getChildCount();
        boolean breakLine = false;
        int max = 0;
        int max2 = 0;
        int measuredWidth;
        for (int i = 0; i < childCount; ++i, x += measuredWidth + n3) {
            final View child = this.getChildAt(i);
            this.measureChild(child, n, n2);
            final FlowLayout$LayoutParams flowLayout$LayoutParams = (FlowLayout$LayoutParams)child.getLayoutParams();
            n3 = this.mHorizontalSpacing;
            if (flowLayout$LayoutParams.horizontalSpacing >= 0) {
                n3 = flowLayout$LayoutParams.horizontalSpacing;
            }
            if (b && (breakLine || child.getMeasuredWidth() + x > size - paddingRight)) {
                paddingTop += this.mVerticalSpacing + max;
                max = 0;
                max2 = Math.max(max2, x - n3);
                x = this.getPaddingLeft();
                b2 = true;
            }
            else {
                b2 = false;
            }
            flowLayout$LayoutParams.x = x;
            flowLayout$LayoutParams.y = paddingTop;
            measuredWidth = child.getMeasuredWidth();
            max = Math.max(max, child.getMeasuredHeight());
            breakLine = flowLayout$LayoutParams.breakLine;
        }
        int n4 = paddingTop;
        int max3 = max2;
        if (!b2) {
            n4 = paddingTop + max;
            max3 = Math.max(max2, x - n3);
        }
        this.setMeasuredDimension(resolveSize(this.getPaddingRight() + max3, n), resolveSize(this.getPaddingBottom() + n4, n2));
    }
}
