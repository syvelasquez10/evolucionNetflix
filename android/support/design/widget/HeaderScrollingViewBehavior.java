// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View$MeasureSpec;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import java.util.List;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View>
{
    private int mOverlayTop;
    final Rect mTempRect1;
    final Rect mTempRect2;
    private int mVerticalLayoutGap;
    
    public HeaderScrollingViewBehavior() {
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mVerticalLayoutGap = 0;
    }
    
    public HeaderScrollingViewBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mVerticalLayoutGap = 0;
    }
    
    private static int resolveGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 8388659;
        }
        return n2;
    }
    
    abstract View findFirstDependency(final List<View> p0);
    
    final int getOverlapPixelsForOffset(final View view) {
        if (this.mOverlayTop == 0) {
            return 0;
        }
        return MathUtils.constrain((int)(this.getOverlapRatioForOffset(view) * this.mOverlayTop), 0, this.mOverlayTop);
    }
    
    float getOverlapRatioForOffset(final View view) {
        return 1.0f;
    }
    
    public final int getOverlayTop() {
        return this.mOverlayTop;
    }
    
    int getScrollRange(final View view) {
        return view.getMeasuredHeight();
    }
    
    final int getVerticalLayoutGap() {
        return this.mVerticalLayoutGap;
    }
    
    @Override
    protected void layoutChild(final CoordinatorLayout coordinatorLayout, final View view, int overlapPixelsForOffset) {
        final View firstDependency = this.findFirstDependency(coordinatorLayout.getDependencies(view));
        if (firstDependency != null) {
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
            final Rect mTempRect1 = this.mTempRect1;
            mTempRect1.set(coordinatorLayout.getPaddingLeft() + coordinatorLayout$LayoutParams.leftMargin, firstDependency.getBottom() + coordinatorLayout$LayoutParams.topMargin, coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight() - coordinatorLayout$LayoutParams.rightMargin, coordinatorLayout.getHeight() + firstDependency.getBottom() - coordinatorLayout.getPaddingBottom() - coordinatorLayout$LayoutParams.bottomMargin);
            final WindowInsetsCompat lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null && ViewCompat.getFitsSystemWindows((View)coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
                mTempRect1.left += lastWindowInsets.getSystemWindowInsetLeft();
                mTempRect1.right -= lastWindowInsets.getSystemWindowInsetRight();
            }
            final Rect mTempRect2 = this.mTempRect2;
            GravityCompat.apply(resolveGravity(coordinatorLayout$LayoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), mTempRect1, mTempRect2, overlapPixelsForOffset);
            overlapPixelsForOffset = this.getOverlapPixelsForOffset(firstDependency);
            view.layout(mTempRect2.left, mTempRect2.top - overlapPixelsForOffset, mTempRect2.right, mTempRect2.bottom - overlapPixelsForOffset);
            this.mVerticalLayoutGap = mTempRect2.top - firstDependency.getBottom();
            return;
        }
        super.layoutChild(coordinatorLayout, view, overlapPixelsForOffset);
        this.mVerticalLayoutGap = 0;
    }
    
    @Override
    public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final View view, final int n, final int n2, int n3, final int n4) {
        final int height = view.getLayoutParams().height;
        if (height == -1 || height == -2) {
            final View firstDependency = this.findFirstDependency(coordinatorLayout.getDependencies(view));
            if (firstDependency != null) {
                if (ViewCompat.getFitsSystemWindows(firstDependency) && !ViewCompat.getFitsSystemWindows(view)) {
                    ViewCompat.setFitsSystemWindows(view, true);
                    if (ViewCompat.getFitsSystemWindows(view)) {
                        view.requestLayout();
                        return true;
                    }
                }
                if ((n3 = View$MeasureSpec.getSize(n3)) == 0) {
                    n3 = coordinatorLayout.getHeight();
                }
                final int measuredHeight = firstDependency.getMeasuredHeight();
                final int scrollRange = this.getScrollRange(firstDependency);
                int n5;
                if (height == -1) {
                    n5 = 1073741824;
                }
                else {
                    n5 = Integer.MIN_VALUE;
                }
                coordinatorLayout.onMeasureChild(view, n, n2, View$MeasureSpec.makeMeasureSpec(scrollRange + (n3 - measuredHeight), n5), n4);
                return true;
            }
        }
        return false;
    }
    
    public final void setOverlayTop(final int mOverlayTop) {
        this.mOverlayTop = mOverlayTop;
    }
}
