// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.widget.ViewUtils;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup
{
    private int mBaseline;
    
    public BaselineLayout(final Context context) {
        super(context, (AttributeSet)null, 0);
        this.mBaseline = -1;
    }
    
    public BaselineLayout(final Context context, final AttributeSet set) {
        super(context, set, 0);
        this.mBaseline = -1;
    }
    
    public BaselineLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mBaseline = -1;
    }
    
    public int getBaseline() {
        return this.mBaseline;
    }
    
    protected void onLayout(final boolean b, final int n, int i, final int n2, int n3) {
        final int childCount = this.getChildCount();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int paddingTop = this.getPaddingTop();
        View child;
        int measuredWidth;
        int measuredHeight;
        int n4;
        for (i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                measuredWidth = child.getMeasuredWidth();
                measuredHeight = child.getMeasuredHeight();
                n4 = paddingLeft + (n2 - n - paddingRight - paddingLeft - measuredWidth) / 2;
                if (this.mBaseline != -1 && child.getBaseline() != -1) {
                    n3 = this.mBaseline + paddingTop - child.getBaseline();
                }
                else {
                    n3 = paddingTop;
                }
                child.layout(n4, n3, measuredWidth + n4, measuredHeight + n3);
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int childCount = this.getChildCount();
        int i = 0;
        int n3 = 0;
        int mBaseline = -1;
        int max = 0;
        int max2 = 0;
        int n4 = -1;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            int n6;
            int combineMeasuredStates;
            if (child.getVisibility() == 8) {
                final int n5 = n3;
                n6 = n4;
                combineMeasuredStates = n5;
            }
            else {
                this.measureChild(child, n, n2);
                final int baseline = child.getBaseline();
                int max3 = n4;
                int max4 = mBaseline;
                if (baseline != -1) {
                    max4 = Math.max(mBaseline, baseline);
                    max3 = Math.max(n4, child.getMeasuredHeight() - baseline);
                }
                max2 = Math.max(max2, child.getMeasuredWidth());
                max = Math.max(max, child.getMeasuredHeight());
                combineMeasuredStates = ViewUtils.combineMeasuredStates(n3, ViewCompat.getMeasuredState(child));
                mBaseline = max4;
                n6 = max3;
            }
            ++i;
            final int n7 = combineMeasuredStates;
            n4 = n6;
            n3 = n7;
        }
        int max5 = max;
        if (mBaseline != -1) {
            max5 = Math.max(max, n4 + mBaseline);
            this.mBaseline = mBaseline;
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(max2, this.getSuggestedMinimumWidth()), n, n3), ViewCompat.resolveSizeAndState(Math.max(max5, this.getSuggestedMinimumHeight()), n2, n3 << 16));
    }
}
