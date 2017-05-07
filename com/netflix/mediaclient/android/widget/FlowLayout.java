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
        return viewGroup$LayoutParams instanceof LayoutParams;
    }
    
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new LayoutParams(viewGroup$LayoutParams.width, viewGroup$LayoutParams.height);
    }
    
    protected void onLayout(final boolean b, int i, int childCount, final int n, final int n2) {
        View child;
        LayoutParams layoutParams;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            layoutParams = (LayoutParams)child.getLayoutParams();
            child.layout(layoutParams.x, layoutParams.y, layoutParams.x + child.getMeasuredWidth(), layoutParams.y + child.getMeasuredHeight());
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
        int max = 0;
        int paddingTop = this.getPaddingTop();
        int x = this.getPaddingLeft();
        int max2 = 0;
        boolean breakLine = false;
        boolean b2 = false;
        int n3 = 0;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            this.measureChild(child, n, n2);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            n3 = this.mHorizontalSpacing;
            if (layoutParams.horizontalSpacing >= 0) {
                n3 = layoutParams.horizontalSpacing;
            }
            if (b && (breakLine || child.getMeasuredWidth() + x > size - paddingRight)) {
                paddingTop += this.mVerticalSpacing + max2;
                max2 = 0;
                max = Math.max(max, x - n3);
                x = this.getPaddingLeft();
                b2 = true;
            }
            else {
                b2 = false;
            }
            layoutParams.x = x;
            layoutParams.y = paddingTop;
            x += child.getMeasuredWidth() + n3;
            max2 = Math.max(max2, child.getMeasuredHeight());
            breakLine = layoutParams.breakLine;
        }
        int n4 = paddingTop;
        int max3 = max;
        if (!b2) {
            n4 = paddingTop + max2;
            max3 = Math.max(max, x - n3);
        }
        this.setMeasuredDimension(resolveSize(max3 + this.getPaddingRight(), n), resolveSize(n4 + this.getPaddingBottom(), n2));
    }
    
    public static class LayoutParams extends ViewGroup$LayoutParams
    {
        public boolean breakLine;
        public int horizontalSpacing;
        int x;
        int y;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
        }
    }
}
