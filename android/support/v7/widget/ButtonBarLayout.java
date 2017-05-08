// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

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
    
    protected void onMeasure(final int n, final int n2) {
        final boolean b = false;
        final int size = View$MeasureSpec.getSize(n);
        if (this.mAllowStacking) {
            if (size > this.mLastWidthSize && this.isStacked()) {
                this.setStacked(false);
            }
            this.mLastWidthSize = size;
        }
        int measureSpec;
        boolean b2;
        if (!this.isStacked() && View$MeasureSpec.getMode(n) == 1073741824) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            b2 = true;
        }
        else {
            measureSpec = n;
            b2 = false;
        }
        super.onMeasure(measureSpec, n2);
        int n3 = b2 ? 1 : 0;
        if (this.mAllowStacking) {
            n3 = (b2 ? 1 : 0);
            if (!this.isStacked()) {
                int n4;
                if (Build$VERSION.SDK_INT >= 11) {
                    n4 = (b ? 1 : 0);
                    if ((ViewCompat.getMeasuredWidthAndState((View)this) & 0xFF000000) == 0x1000000) {
                        n4 = 1;
                    }
                }
                else {
                    final int childCount = this.getChildCount();
                    int i = 0;
                    int n5 = 0;
                    while (i < childCount) {
                        n5 += this.getChildAt(i).getMeasuredWidth();
                        ++i;
                    }
                    n4 = (b ? 1 : 0);
                    if (this.getPaddingLeft() + n5 + this.getPaddingRight() > size) {
                        n4 = 1;
                    }
                }
                n3 = (b2 ? 1 : 0);
                if (n4 != 0) {
                    this.setStacked(true);
                    n3 = 1;
                }
            }
        }
        if (n3 != 0) {
            super.onMeasure(n, n2);
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
