// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

class IcsLinearLayout extends LinearLayout
{
    private static final int[] LL;
    private static final int LL_DIVIDER = 0;
    private static final int LL_DIVIDER_PADDING = 2;
    private static final int LL_SHOW_DIVIDER = 1;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mShowDividers;
    
    static {
        LL = new int[] { 16843049, 16843561, 16843562 };
    }
    
    public IcsLinearLayout(final Context context, final int n) {
        super(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet)null, IcsLinearLayout.LL, n, 0);
        this.setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.mShowDividers = obtainStyledAttributes.getInteger(1, 0);
        obtainStyledAttributes.recycle();
    }
    
    private void drawDividersHorizontal(final Canvas canvas) {
        final int childCount = this.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != null && child.getVisibility() != 8 && this.hasDividerBeforeChildAt(i)) {
                this.drawVerticalDivider(canvas, child.getLeft() - ((LinearLayout$LayoutParams)child.getLayoutParams()).leftMargin);
            }
        }
        if (this.hasDividerBeforeChildAt(childCount)) {
            final View child2 = this.getChildAt(childCount - 1);
            int right;
            if (child2 == null) {
                right = this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
            }
            else {
                right = child2.getRight();
            }
            this.drawVerticalDivider(canvas, right);
        }
    }
    
    private void drawDividersVertical(final Canvas canvas) {
        final int childCount = this.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != null && child.getVisibility() != 8 && this.hasDividerBeforeChildAt(i)) {
                this.drawHorizontalDivider(canvas, child.getTop() - ((LinearLayout$LayoutParams)child.getLayoutParams()).topMargin);
            }
        }
        if (this.hasDividerBeforeChildAt(childCount)) {
            final View child2 = this.getChildAt(childCount - 1);
            int bottom;
            if (child2 == null) {
                bottom = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
            }
            else {
                bottom = child2.getBottom();
            }
            this.drawHorizontalDivider(canvas, bottom);
        }
    }
    
    private void drawHorizontalDivider(final Canvas canvas, final int n) {
        this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, n, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + n);
        this.mDivider.draw(canvas);
    }
    
    private void drawVerticalDivider(final Canvas canvas, final int n) {
        this.mDivider.setBounds(n, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + n, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }
    
    private boolean hasDividerBeforeChildAt(int i) {
        if (i != 0 && i != this.getChildCount() && (this.mShowDividers & 0x2) != 0x0) {
            for (--i; i >= 0; --i) {
                if (this.getChildAt(i).getVisibility() != 8) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void measureChildWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
        final int indexOfChild = this.indexOfChild(view);
        final int orientation = this.getOrientation();
        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)view.getLayoutParams();
        if (this.hasDividerBeforeChildAt(indexOfChild)) {
            if (orientation == 1) {
                linearLayout$LayoutParams.topMargin = this.mDividerHeight;
            }
            else {
                linearLayout$LayoutParams.leftMargin = this.mDividerWidth;
            }
        }
        final int childCount = this.getChildCount();
        if (indexOfChild == childCount - 1 && this.hasDividerBeforeChildAt(childCount)) {
            if (orientation == 1) {
                linearLayout$LayoutParams.bottomMargin = this.mDividerHeight;
            }
            else {
                linearLayout$LayoutParams.rightMargin = this.mDividerWidth;
            }
        }
        super.measureChildWithMargins(view, n, n2, n3, n4);
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.mDivider != null) {
            if (this.getOrientation() == 1) {
                this.drawDividersVertical(canvas);
            }
            else {
                this.drawDividersHorizontal(canvas);
            }
        }
        super.onDraw(canvas);
    }
    
    public void setDividerDrawable(final Drawable mDivider) {
        boolean willNotDraw = false;
        if (mDivider == this.mDivider) {
            return;
        }
        if ((this.mDivider = mDivider) != null) {
            this.mDividerWidth = mDivider.getIntrinsicWidth();
            this.mDividerHeight = mDivider.getIntrinsicHeight();
        }
        else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        if (mDivider == null) {
            willNotDraw = true;
        }
        this.setWillNotDraw(willNotDraw);
        this.requestLayout();
    }
}
