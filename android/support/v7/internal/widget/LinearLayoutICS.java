// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

public class LinearLayoutICS extends LinearLayout
{
    private static final int SHOW_DIVIDER_BEGINNING = 1;
    private static final int SHOW_DIVIDER_END = 4;
    private static final int SHOW_DIVIDER_MIDDLE = 2;
    private static final int SHOW_DIVIDER_NONE = 0;
    private final Drawable mDivider;
    private final int mDividerHeight;
    private final int mDividerPadding;
    private final int mDividerWidth;
    private final int mShowDividers;
    
    public LinearLayoutICS(final Context context, final AttributeSet set) {
        boolean willNotDraw = true;
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.LinearLayoutICS);
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        if (this.mDivider != null) {
            this.mDividerWidth = this.mDivider.getIntrinsicWidth();
            this.mDividerHeight = this.mDivider.getIntrinsicHeight();
        }
        else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        this.mShowDividers = obtainStyledAttributes.getInt(1, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        obtainStyledAttributes.recycle();
        if (this.mDivider != null) {
            willNotDraw = false;
        }
        this.setWillNotDraw(willNotDraw);
    }
    
    void drawSupportDividersHorizontal(final Canvas canvas) {
        final int childCount = this.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != null && child.getVisibility() != 8 && this.hasSupportDividerBeforeChildAt(i)) {
                this.drawSupportVerticalDivider(canvas, child.getLeft() - ((LinearLayout$LayoutParams)child.getLayoutParams()).leftMargin);
            }
        }
        if (this.hasSupportDividerBeforeChildAt(childCount)) {
            final View child2 = this.getChildAt(childCount - 1);
            int right;
            if (child2 == null) {
                right = this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
            }
            else {
                right = child2.getRight();
            }
            this.drawSupportVerticalDivider(canvas, right);
        }
    }
    
    void drawSupportDividersVertical(final Canvas canvas) {
        final int childCount = this.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != null && child.getVisibility() != 8 && this.hasSupportDividerBeforeChildAt(i)) {
                this.drawSupportHorizontalDivider(canvas, child.getTop() - ((LinearLayout$LayoutParams)child.getLayoutParams()).topMargin);
            }
        }
        if (this.hasSupportDividerBeforeChildAt(childCount)) {
            final View child2 = this.getChildAt(childCount - 1);
            int bottom;
            if (child2 == null) {
                bottom = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
            }
            else {
                bottom = child2.getBottom();
            }
            this.drawSupportHorizontalDivider(canvas, bottom);
        }
    }
    
    void drawSupportHorizontalDivider(final Canvas canvas, final int n) {
        this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, n, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + n);
        this.mDivider.draw(canvas);
    }
    
    void drawSupportVerticalDivider(final Canvas canvas, final int n) {
        this.mDivider.setBounds(n, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + n, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }
    
    public int getSupportDividerWidth() {
        return this.mDividerWidth;
    }
    
    protected boolean hasSupportDividerBeforeChildAt(int n) {
        if (n == 0) {
            if ((this.mShowDividers & 0x1) == 0x0) {
                return false;
            }
        }
        else if (n == this.getChildCount()) {
            if ((this.mShowDividers & 0x4) == 0x0) {
                return false;
            }
        }
        else {
            if ((this.mShowDividers & 0x2) != 0x0) {
                final boolean b = false;
                --n;
                boolean b2;
                while (true) {
                    b2 = b;
                    if (n < 0) {
                        break;
                    }
                    if (this.getChildAt(n).getVisibility() != 8) {
                        b2 = true;
                        break;
                    }
                    --n;
                }
                return b2;
            }
            return false;
        }
        return true;
    }
    
    protected void measureChildWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
        if (this.mDivider != null) {
            final int indexOfChild = this.indexOfChild(view);
            final int childCount = this.getChildCount();
            final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)view.getLayoutParams();
            if (this.getOrientation() == 1) {
                if (this.hasSupportDividerBeforeChildAt(indexOfChild)) {
                    linearLayout$LayoutParams.topMargin = this.mDividerHeight;
                }
                else if (indexOfChild == childCount - 1 && this.hasSupportDividerBeforeChildAt(childCount)) {
                    linearLayout$LayoutParams.bottomMargin = this.mDividerHeight;
                }
            }
            else if (this.hasSupportDividerBeforeChildAt(indexOfChild)) {
                linearLayout$LayoutParams.leftMargin = this.mDividerWidth;
            }
            else if (indexOfChild == childCount - 1 && this.hasSupportDividerBeforeChildAt(childCount)) {
                linearLayout$LayoutParams.rightMargin = this.mDividerWidth;
            }
        }
        super.measureChildWithMargins(view, n, n2, n3, n4);
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.getOrientation() == 1) {
            this.drawSupportDividersVertical(canvas);
            return;
        }
        this.drawSupportDividersHorizontal(canvas);
    }
}
