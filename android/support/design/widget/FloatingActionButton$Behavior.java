// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.annotation.TargetApi;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.support.design.R$dimen;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.ImageView;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.ViewGroup;
import java.util.List;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.os.Build$VERSION;
import android.graphics.Rect;

public class FloatingActionButton$Behavior extends CoordinatorLayout$Behavior<FloatingActionButton>
{
    private static final boolean SNACKBAR_BEHAVIOR_ENABLED;
    private Rect mTmpRect;
    private float mTranslationY;
    
    static {
        SNACKBAR_BEHAVIOR_ENABLED = (Build$VERSION.SDK_INT >= 11);
    }
    
    private float getFabTranslationYForSnackbar(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
        float min = 0.0f;
        final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
        for (int size = dependencies.size(), i = 0; i < size; ++i) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar$SnackbarLayout && coordinatorLayout.doViewsOverlap((View)floatingActionButton, view)) {
                min = Math.min(min, ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }
        return min;
    }
    
    private void offsetIfNeeded(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
        int bottom = 0;
        final Rect access$000 = floatingActionButton.mShadowPadding;
        if (access$000 != null && access$000.centerX() > 0 && access$000.centerY() > 0) {
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)floatingActionButton.getLayoutParams();
            int right;
            if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - coordinatorLayout$LayoutParams.rightMargin) {
                right = access$000.right;
            }
            else if (floatingActionButton.getLeft() <= coordinatorLayout$LayoutParams.leftMargin) {
                right = -access$000.left;
            }
            else {
                right = 0;
            }
            if (floatingActionButton.getBottom() >= coordinatorLayout.getBottom() - coordinatorLayout$LayoutParams.bottomMargin) {
                bottom = access$000.bottom;
            }
            else if (floatingActionButton.getTop() <= coordinatorLayout$LayoutParams.topMargin) {
                bottom = -access$000.top;
            }
            floatingActionButton.offsetTopAndBottom(bottom);
            floatingActionButton.offsetLeftAndRight(right);
        }
    }
    
    private void updateFabTranslationForSnackbar(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
        if (floatingActionButton.getVisibility() == 0) {
            final float fabTranslationYForSnackbar = this.getFabTranslationYForSnackbar(coordinatorLayout, floatingActionButton);
            if (fabTranslationYForSnackbar != this.mTranslationY) {
                ViewCompat.animate((View)floatingActionButton).cancel();
                ViewCompat.setTranslationY((View)floatingActionButton, fabTranslationYForSnackbar);
                this.mTranslationY = fabTranslationYForSnackbar;
            }
        }
    }
    
    private boolean updateFabVisibility(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final FloatingActionButton floatingActionButton) {
        if (((CoordinatorLayout$LayoutParams)floatingActionButton.getLayoutParams()).getAnchorId() != appBarLayout.getId()) {
            return false;
        }
        if (this.mTmpRect == null) {
            this.mTmpRect = new Rect();
        }
        final Rect mTmpRect = this.mTmpRect;
        ViewGroupUtils.getDescendantRect(coordinatorLayout, (View)appBarLayout, mTmpRect);
        if (mTmpRect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
            floatingActionButton.hide();
        }
        else {
            floatingActionButton.show();
        }
        return true;
    }
    
    @Override
    public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
        return FloatingActionButton$Behavior.SNACKBAR_BEHAVIOR_ENABLED && view instanceof Snackbar$SnackbarLayout;
    }
    
    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
        if (view instanceof Snackbar$SnackbarLayout) {
            this.updateFabTranslationForSnackbar(coordinatorLayout, floatingActionButton, view);
        }
        else if (view instanceof AppBarLayout) {
            this.updateFabVisibility(coordinatorLayout, (AppBarLayout)view, floatingActionButton);
        }
        return false;
    }
    
    @Override
    public void onDependentViewRemoved(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
        if (view instanceof Snackbar$SnackbarLayout) {
            ViewCompat.animate((View)floatingActionButton).translationY(0.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(null);
        }
    }
    
    @Override
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final int n) {
        final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
        for (int size = dependencies.size(), i = 0; i < size; ++i) {
            final View view = dependencies.get(i);
            if (view instanceof AppBarLayout && this.updateFabVisibility(coordinatorLayout, (AppBarLayout)view, floatingActionButton)) {
                break;
            }
        }
        coordinatorLayout.onLayoutChild((View)floatingActionButton, n);
        this.offsetIfNeeded(coordinatorLayout, floatingActionButton);
        return true;
    }
}
