// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.LinearLayout$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.view.View$MeasureSpec;
import android.view.View;
import android.support.v7.appcompat.R$id;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v4.content.res.ConfigurationHelper;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{
    private boolean mAllowStacking;
    private int mLastWidthSize;
    
    public ButtonBarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mLastWidthSize = -1;
        final boolean b = ConfigurationHelper.getScreenHeightDp(this.getResources()) >= 320;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ButtonBarLayout);
        this.mAllowStacking = obtainStyledAttributes.getBoolean(R$styleable.ButtonBarLayout_allowStacking, b);
        obtainStyledAttributes.recycle();
    }
    
    private int getNextVisibleChildIndex(int i) {
        while (i < this.getChildCount()) {
            if (this.getChildAt(i).getVisibility() == 0) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    private boolean isStacked() {
        return this.getOrientation() == 1;
    }
    
    private void setStacked(final boolean b) {
        int orientation;
        if (b) {
            orientation = 1;
        }
        else {
            orientation = 0;
        }
        this.setOrientation(orientation);
        int gravity;
        if (b) {
            gravity = 5;
        }
        else {
            gravity = 80;
        }
        this.setGravity(gravity);
        final View viewById = this.findViewById(R$id.spacer);
        if (viewById != null) {
            int visibility;
            if (b) {
                visibility = 8;
            }
            else {
                visibility = 4;
            }
            viewById.setVisibility(visibility);
        }
        for (int i = this.getChildCount() - 2; i >= 0; --i) {
            this.bringChildToFront(this.getChildAt(i));
        }
    }
    
    protected void onMeasure(int nextVisibleChildIndex, int paddingTop) {
        final int size = View$MeasureSpec.getSize(nextVisibleChildIndex);
        if (this.mAllowStacking) {
            if (size > this.mLastWidthSize && this.isStacked()) {
                this.setStacked(false);
            }
            this.mLastWidthSize = size;
        }
        int measureSpec;
        boolean b;
        if (!this.isStacked() && View$MeasureSpec.getMode(nextVisibleChildIndex) == 1073741824) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            b = true;
        }
        else {
            measureSpec = nextVisibleChildIndex;
            b = false;
        }
        super.onMeasure(measureSpec, paddingTop);
        int n = b ? 1 : 0;
        if (this.mAllowStacking) {
            n = (b ? 1 : 0);
            if (!this.isStacked()) {
                int n2;
                if (Build$VERSION.SDK_INT >= 11) {
                    if ((ViewCompat.getMeasuredWidthAndState((View)this) & 0xFF000000) == 0x1000000) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                else {
                    final int childCount = this.getChildCount();
                    int i = 0;
                    int n3 = 0;
                    while (i < childCount) {
                        n3 += this.getChildAt(i).getMeasuredWidth();
                        ++i;
                    }
                    if (this.getPaddingLeft() + n3 + this.getPaddingRight() > size) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                n = (b ? 1 : 0);
                if (n2 != 0) {
                    this.setStacked(true);
                    n = 1;
                }
            }
        }
        if (n != 0) {
            super.onMeasure(nextVisibleChildIndex, paddingTop);
        }
        nextVisibleChildIndex = this.getNextVisibleChildIndex(0);
        if (nextVisibleChildIndex >= 0) {
            final View child = this.getChildAt(nextVisibleChildIndex);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)child.getLayoutParams();
            paddingTop = this.getPaddingTop();
            paddingTop = linearLayout$LayoutParams.bottomMargin + (child.getMeasuredHeight() + paddingTop + linearLayout$LayoutParams.topMargin) + 0;
            if (this.isStacked()) {
                final int nextVisibleChildIndex2 = this.getNextVisibleChildIndex(nextVisibleChildIndex + 1);
                nextVisibleChildIndex = paddingTop;
                if (nextVisibleChildIndex2 >= 0) {
                    nextVisibleChildIndex = (int)(paddingTop + (this.getChildAt(nextVisibleChildIndex2).getPaddingTop() + 16.0f * this.getResources().getDisplayMetrics().density));
                }
            }
            else {
                nextVisibleChildIndex = paddingTop + this.getPaddingBottom();
            }
        }
        else {
            nextVisibleChildIndex = 0;
        }
        if (ViewCompat.getMinimumHeight((View)this) != nextVisibleChildIndex) {
            this.setMinimumHeight(nextVisibleChildIndex);
        }
    }
    
    public void setAllowStacking(final boolean mAllowStacking) {
        if (this.mAllowStacking != mAllowStacking) {
            this.mAllowStacking = mAllowStacking;
            if (!this.mAllowStacking && this.getOrientation() == 1) {
                this.setStacked(false);
            }
            this.requestLayout();
        }
    }
}
