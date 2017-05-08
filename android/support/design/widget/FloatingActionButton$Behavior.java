// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import java.util.List;
import android.view.ViewGroup;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.res.TypedArray;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;

public class FloatingActionButton$Behavior extends CoordinatorLayout$Behavior<FloatingActionButton>
{
    private static final boolean AUTO_HIDE_DEFAULT = true;
    private boolean mAutoHideEnabled;
    private FloatingActionButton$OnVisibilityChangedListener mInternalAutoHideListener;
    private Rect mTmpRect;
    
    public FloatingActionButton$Behavior() {
        this.mAutoHideEnabled = true;
    }
    
    public FloatingActionButton$Behavior(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.FloatingActionButton_Behavior_Layout);
        this.mAutoHideEnabled = obtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
        obtainStyledAttributes.recycle();
    }
    
    private static boolean isBottomSheet(final View view) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof CoordinatorLayout$LayoutParams && ((CoordinatorLayout$LayoutParams)layoutParams).getBehavior() instanceof BottomSheetBehavior;
    }
    
    private void offsetIfNeeded(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
        int bottom = 0;
        final Rect mShadowPadding = floatingActionButton.mShadowPadding;
        if (mShadowPadding != null && mShadowPadding.centerX() > 0 && mShadowPadding.centerY() > 0) {
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)floatingActionButton.getLayoutParams();
            int right;
            if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - coordinatorLayout$LayoutParams.rightMargin) {
                right = mShadowPadding.right;
            }
            else if (floatingActionButton.getLeft() <= coordinatorLayout$LayoutParams.leftMargin) {
                right = -mShadowPadding.left;
            }
            else {
                right = 0;
            }
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - coordinatorLayout$LayoutParams.bottomMargin) {
                bottom = mShadowPadding.bottom;
            }
            else if (floatingActionButton.getTop() <= coordinatorLayout$LayoutParams.topMargin) {
                bottom = -mShadowPadding.top;
            }
            if (bottom != 0) {
                ViewCompat.offsetTopAndBottom((View)floatingActionButton, bottom);
            }
            if (right != 0) {
                ViewCompat.offsetLeftAndRight((View)floatingActionButton, right);
            }
        }
    }
    
    private boolean shouldUpdateVisibility(final View view, final FloatingActionButton floatingActionButton) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)floatingActionButton.getLayoutParams();
        return this.mAutoHideEnabled && coordinatorLayout$LayoutParams.getAnchorId() == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
    }
    
    private boolean updateFabVisibilityForAppBarLayout(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final FloatingActionButton floatingActionButton) {
        if (!this.shouldUpdateVisibility((View)appBarLayout, floatingActionButton)) {
            return false;
        }
        if (this.mTmpRect == null) {
            this.mTmpRect = new Rect();
        }
        final Rect mTmpRect = this.mTmpRect;
        ViewGroupUtils.getDescendantRect(coordinatorLayout, (View)appBarLayout, mTmpRect);
        if (mTmpRect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
            floatingActionButton.hide(this.mInternalAutoHideListener, false);
        }
        else {
            floatingActionButton.show(this.mInternalAutoHideListener, false);
        }
        return true;
    }
    
    private boolean updateFabVisibilityForBottomSheet(final View view, final FloatingActionButton floatingActionButton) {
        if (!this.shouldUpdateVisibility(view, floatingActionButton)) {
            return false;
        }
        if (view.getTop() < ((CoordinatorLayout$LayoutParams)floatingActionButton.getLayoutParams()).topMargin + floatingActionButton.getHeight() / 2) {
            floatingActionButton.hide(this.mInternalAutoHideListener, false);
        }
        else {
            floatingActionButton.show(this.mInternalAutoHideListener, false);
        }
        return true;
    }
    
    @Override
    public boolean getInsetDodgeRect(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final Rect rect) {
        final Rect mShadowPadding = floatingActionButton.mShadowPadding;
        rect.set(floatingActionButton.getLeft() + mShadowPadding.left, floatingActionButton.getTop() + mShadowPadding.top, floatingActionButton.getRight() - mShadowPadding.right, floatingActionButton.getBottom() - mShadowPadding.bottom);
        return true;
    }
    
    public boolean isAutoHideEnabled() {
        return this.mAutoHideEnabled;
    }
    
    @Override
    public void onAttachedToLayoutParams(final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams) {
        if (coordinatorLayout$LayoutParams.dodgeInsetEdges == 0) {
            coordinatorLayout$LayoutParams.dodgeInsetEdges = 80;
        }
    }
    
    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
        if (view instanceof AppBarLayout) {
            this.updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout)view, floatingActionButton);
        }
        else if (isBottomSheet(view)) {
            this.updateFabVisibilityForBottomSheet(view, floatingActionButton);
        }
        return false;
    }
    
    @Override
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final int n) {
        final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
        for (int size = dependencies.size(), i = 0; i < size; ++i) {
            final View view = dependencies.get(i);
            if (view instanceof AppBarLayout) {
                if (this.updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout)view, floatingActionButton)) {
                    break;
                }
            }
            else if (isBottomSheet(view) && this.updateFabVisibilityForBottomSheet(view, floatingActionButton)) {
                break;
            }
        }
        coordinatorLayout.onLayoutChild((View)floatingActionButton, n);
        this.offsetIfNeeded(coordinatorLayout, floatingActionButton);
        return true;
    }
    
    public void setAutoHideEnabled(final boolean mAutoHideEnabled) {
        this.mAutoHideEnabled = mAutoHideEnabled;
    }
    
    void setInternalAutoHideListener(final FloatingActionButton$OnVisibilityChangedListener mInternalAutoHideListener) {
        this.mInternalAutoHideListener = mInternalAutoHideListener;
    }
}
