// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.R$id;
import android.view.ViewGroup;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;

public class AlertDialogLayout extends LinearLayoutCompat
{
    public AlertDialogLayout(final Context context) {
        super(context);
    }
    
    public AlertDialogLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    private void forceUniformWidth(final int n, final int n2) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        for (int i = 0; i < n; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)child.getLayoutParams();
                if (linearLayoutCompat$LayoutParams.width == -1) {
                    final int height = linearLayoutCompat$LayoutParams.height;
                    linearLayoutCompat$LayoutParams.height = child.getMeasuredHeight();
                    this.measureChildWithMargins(child, measureSpec, 0, n2, 0);
                    linearLayoutCompat$LayoutParams.height = height;
                }
            }
        }
    }
    
    private static int resolveMinimumHeight(final View view) {
        final int minimumHeight = ViewCompat.getMinimumHeight(view);
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            if (viewGroup.getChildCount() == 1) {
                return resolveMinimumHeight(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }
    
    private void setChildFrame(final View view, final int n, final int n2, final int n3, final int n4) {
        view.layout(n, n2, n + n3, n2 + n4);
    }
    
    private boolean tryOnMeasure(final int n, final int n2) {
        View view = null;
        View view2 = null;
        final int childCount = this.getChildCount();
        int i = 0;
        View view3 = null;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            View view5;
            View view6;
            if (child.getVisibility() == 8) {
                final View view4 = view2;
                view5 = view;
                view6 = view4;
            }
            else {
                final int id = child.getId();
                if (id == R$id.topPanel) {
                    view6 = view2;
                    view5 = child;
                }
                else if (id == R$id.buttonPanel) {
                    view5 = view;
                    view6 = child;
                }
                else {
                    if (id != R$id.contentPanel && id != R$id.customPanel) {
                        return false;
                    }
                    if (view3 != null) {
                        return false;
                    }
                    final View view7 = view;
                    view3 = child;
                    view6 = view2;
                    view5 = view7;
                }
            }
            ++i;
            final View view8 = view5;
            view2 = view6;
            view = view8;
        }
        final int mode = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n2);
        final int mode2 = View$MeasureSpec.getMode(n);
        int n3 = 0;
        int n5;
        final int n4 = n5 = this.getPaddingBottom() + this.getPaddingTop();
        if (view != null) {
            view.measure(n, 0);
            n5 = n4 + view.getMeasuredHeight();
            n3 = ViewCompat.combineMeasuredStates(0, ViewCompat.getMeasuredState(view));
        }
        int resolveMinimumHeight = 0;
        int n6;
        if (view2 != null) {
            view2.measure(n, 0);
            resolveMinimumHeight = resolveMinimumHeight(view2);
            final int measuredHeight = view2.getMeasuredHeight();
            n5 += resolveMinimumHeight;
            n3 = ViewCompat.combineMeasuredStates(n3, ViewCompat.getMeasuredState(view2));
            n6 = measuredHeight - resolveMinimumHeight;
        }
        else {
            n6 = 0;
        }
        int measuredHeight2;
        if (view3 != null) {
            int measureSpec;
            if (mode == 0) {
                measureSpec = 0;
            }
            else {
                measureSpec = View$MeasureSpec.makeMeasureSpec(Math.max(0, size - n5), mode);
            }
            view3.measure(n, measureSpec);
            measuredHeight2 = view3.getMeasuredHeight();
            n5 += measuredHeight2;
            n3 = ViewCompat.combineMeasuredStates(n3, ViewCompat.getMeasuredState(view3));
        }
        else {
            measuredHeight2 = 0;
        }
        int n7 = size - n5;
        int n10;
        int combineMeasuredStates2;
        if (view2 != null) {
            final int min = Math.min(n7, n6);
            int n8 = n7;
            int n9 = resolveMinimumHeight;
            if (min > 0) {
                n8 = n7 - min;
                n9 = resolveMinimumHeight + min;
            }
            view2.measure(n, View$MeasureSpec.makeMeasureSpec(n9, 1073741824));
            final int measuredHeight3 = view2.getMeasuredHeight();
            final int combineMeasuredStates = ViewCompat.combineMeasuredStates(n3, ViewCompat.getMeasuredState(view2));
            n10 = measuredHeight3 + (n5 - resolveMinimumHeight);
            n7 = n8;
            combineMeasuredStates2 = combineMeasuredStates;
        }
        else {
            n10 = n5;
            combineMeasuredStates2 = n3;
        }
        if (view3 != null && n7 > 0) {
            view3.measure(n, View$MeasureSpec.makeMeasureSpec(n7 + measuredHeight2, mode));
            final int measuredHeight4 = view3.getMeasuredHeight();
            combineMeasuredStates2 = ViewCompat.combineMeasuredStates(combineMeasuredStates2, ViewCompat.getMeasuredState(view3));
            n10 = n10 - measuredHeight2 + measuredHeight4;
        }
        int n11 = 0;
        int max;
        for (int j = 0; j < childCount; ++j, n11 = max) {
            final View child2 = this.getChildAt(j);
            max = n11;
            if (child2.getVisibility() != 8) {
                max = Math.max(n11, child2.getMeasuredWidth());
            }
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(n11 + (this.getPaddingLeft() + this.getPaddingRight()), n, combineMeasuredStates2), ViewCompat.resolveSizeAndState(n10, n2, 0));
        if (mode2 != 1073741824) {
            this.forceUniformWidth(childCount, n2);
        }
        return true;
    }
    
    @Override
    protected void onLayout(final boolean b, int n, int gravity, int n2, int i) {
        final int paddingLeft = this.getPaddingLeft();
        final int n3 = n2 - n;
        final int paddingRight = this.getPaddingRight();
        final int paddingRight2 = this.getPaddingRight();
        n = this.getMeasuredHeight();
        final int childCount = this.getChildCount();
        final int gravity2 = this.getGravity();
        switch (gravity2 & 0x70) {
            default: {
                n = this.getPaddingTop();
                break;
            }
            case 80: {
                n = this.getPaddingTop() + i - gravity - n;
                break;
            }
            case 16: {
                n2 = this.getPaddingTop();
                n = (i - gravity - n) / 2 + n2;
                break;
            }
        }
        final Drawable dividerDrawable = this.getDividerDrawable();
        if (dividerDrawable == null) {
            n2 = 0;
        }
        else {
            n2 = dividerDrawable.getIntrinsicHeight();
        }
        View child;
        int measuredWidth;
        int measuredHeight;
        LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams;
        for (i = 0; i < childCount; ++i, n = gravity) {
            child = this.getChildAt(i);
            gravity = n;
            if (child != null) {
                gravity = n;
                if (child.getVisibility() != 8) {
                    measuredWidth = child.getMeasuredWidth();
                    measuredHeight = child.getMeasuredHeight();
                    linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)child.getLayoutParams();
                    if ((gravity = linearLayoutCompat$LayoutParams.gravity) < 0) {
                        gravity = (gravity2 & 0x800007);
                    }
                    switch (GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection((View)this)) & 0x7) {
                        default: {
                            gravity = paddingLeft + linearLayoutCompat$LayoutParams.leftMargin;
                            break;
                        }
                        case 1: {
                            gravity = (n3 - paddingLeft - paddingRight2 - measuredWidth) / 2 + paddingLeft + linearLayoutCompat$LayoutParams.leftMargin - linearLayoutCompat$LayoutParams.rightMargin;
                            break;
                        }
                        case 5: {
                            gravity = n3 - paddingRight - measuredWidth - linearLayoutCompat$LayoutParams.rightMargin;
                            break;
                        }
                    }
                    if (this.hasDividerBeforeChildAt(i)) {
                        n += n2;
                    }
                    n += linearLayoutCompat$LayoutParams.topMargin;
                    this.setChildFrame(child, gravity, n, measuredWidth, measuredHeight);
                    gravity = n + (linearLayoutCompat$LayoutParams.bottomMargin + measuredHeight);
                }
            }
        }
    }
    
    @Override
    protected void onMeasure(final int n, final int n2) {
        if (!this.tryOnMeasure(n, n2)) {
            super.onMeasure(n, n2);
        }
    }
}
