// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View$MeasureSpec;
import android.support.v4.view.ViewCompat;
import java.util.List;
import android.content.res.TypedArray;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class AppBarLayout$ScrollingViewBehavior extends ViewOffsetBehavior<View>
{
    private int mOverlayTop;
    
    public AppBarLayout$ScrollingViewBehavior() {
    }
    
    public AppBarLayout$ScrollingViewBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ScrollingViewBehavior_Params);
        this.mOverlayTop = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Params_behavior_overlapTop, 0);
        obtainStyledAttributes.recycle();
    }
    
    private static AppBarLayout findFirstAppBarLayout(final List<View> list) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            final View view = list.get(i);
            if (view instanceof AppBarLayout) {
                return (AppBarLayout)view;
            }
        }
        return null;
    }
    
    @Override
    public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
        return view2 instanceof AppBarLayout;
    }
    
    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
        final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)view2.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout$Behavior) {
            final int topBottomOffsetForScrollingSibling = ((AppBarLayout$Behavior)behavior).getTopBottomOffsetForScrollingSibling();
            final int height = view2.getHeight();
            final int mOverlayTop = this.mOverlayTop;
            final int height2 = coordinatorLayout.getHeight();
            final int height3 = view.getHeight();
            if (this.mOverlayTop != 0 && view2 instanceof AppBarLayout) {
                this.setTopAndBottomOffset(AnimationUtils.lerp(height - mOverlayTop, height2 - height3, Math.abs(topBottomOffsetForScrollingSibling) / ((AppBarLayout)view2).getTotalScrollRange()));
            }
            else {
                this.setTopAndBottomOffset(topBottomOffsetForScrollingSibling + (view2.getHeight() - this.mOverlayTop));
            }
        }
        return false;
    }
    
    @Override
    public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final View view, final int n, final int n2, int n3, final int n4) {
        final int height = view.getLayoutParams().height;
        if (height == -1 || height == -2) {
            final List<View> dependencies = coordinatorLayout.getDependencies(view);
            if (!dependencies.isEmpty()) {
                final AppBarLayout firstAppBarLayout = findFirstAppBarLayout(dependencies);
                if (firstAppBarLayout != null && ViewCompat.isLaidOut((View)firstAppBarLayout)) {
                    if (ViewCompat.getFitsSystemWindows((View)firstAppBarLayout)) {
                        ViewCompat.setFitsSystemWindows(view, true);
                    }
                    if ((n3 = View$MeasureSpec.getSize(n3)) == 0) {
                        n3 = coordinatorLayout.getHeight();
                    }
                    final int measuredHeight = firstAppBarLayout.getMeasuredHeight();
                    final int totalScrollRange = firstAppBarLayout.getTotalScrollRange();
                    int n5;
                    if (height == -1) {
                        n5 = 1073741824;
                    }
                    else {
                        n5 = Integer.MIN_VALUE;
                    }
                    coordinatorLayout.onMeasureChild(view, n, n2, View$MeasureSpec.makeMeasureSpec(totalScrollRange + (n3 - measuredHeight), n5), n4);
                    return true;
                }
            }
        }
        return false;
    }
}
