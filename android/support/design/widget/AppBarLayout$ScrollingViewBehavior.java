// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import android.graphics.Rect;
import java.util.List;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.content.res.TypedArray;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;

public class AppBarLayout$ScrollingViewBehavior extends HeaderScrollingViewBehavior
{
    public AppBarLayout$ScrollingViewBehavior() {
    }
    
    public AppBarLayout$ScrollingViewBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ScrollingViewBehavior_Layout);
        this.setOverlayTop(obtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
        obtainStyledAttributes.recycle();
    }
    
    private static int getAppBarLayoutOffset(final AppBarLayout appBarLayout) {
        final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)appBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout$Behavior) {
            return ((AppBarLayout$Behavior)behavior).getTopBottomOffsetForScrollingSibling();
        }
        return 0;
    }
    
    private void offsetChildAsNeeded(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
        final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)view2.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout$Behavior) {
            ViewCompat.offsetTopAndBottom(view, ((AppBarLayout$Behavior)behavior).mOffsetDelta + (view2.getBottom() - view.getTop()) + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(view2));
        }
    }
    
    AppBarLayout findFirstDependency(final List<View> list) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            final View view = list.get(i);
            if (view instanceof AppBarLayout) {
                return (AppBarLayout)view;
            }
        }
        return null;
    }
    
    @Override
    float getOverlapRatioForOffset(final View view) {
        if (view instanceof AppBarLayout) {
            final AppBarLayout appBarLayout = (AppBarLayout)view;
            final int totalScrollRange = appBarLayout.getTotalScrollRange();
            final int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
            final int appBarLayoutOffset = getAppBarLayoutOffset(appBarLayout);
            if (downNestedPreScrollRange == 0 || totalScrollRange + appBarLayoutOffset > downNestedPreScrollRange) {
                final int n = totalScrollRange - downNestedPreScrollRange;
                if (n != 0) {
                    return 1.0f + appBarLayoutOffset / n;
                }
            }
        }
        return 0.0f;
    }
    
    @Override
    int getScrollRange(final View view) {
        if (view instanceof AppBarLayout) {
            return ((AppBarLayout)view).getTotalScrollRange();
        }
        return super.getScrollRange(view);
    }
    
    @Override
    public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
        return view2 instanceof AppBarLayout;
    }
    
    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
        this.offsetChildAsNeeded(coordinatorLayout, view, view2);
        return false;
    }
    
    @Override
    public boolean onRequestChildRectangleOnScreen(final CoordinatorLayout coordinatorLayout, final View view, final Rect rect, final boolean b) {
        final AppBarLayout firstDependency = this.findFirstDependency(coordinatorLayout.getDependencies(view));
        if (firstDependency != null) {
            rect.offset(view.getLeft(), view.getTop());
            final Rect mTempRect1 = this.mTempRect1;
            mTempRect1.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
            if (!mTempRect1.contains(rect)) {
                firstDependency.setExpanded(false, !b);
                return true;
            }
        }
        return false;
    }
}
