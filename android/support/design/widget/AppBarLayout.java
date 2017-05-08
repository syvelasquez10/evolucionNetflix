// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.view.ViewGroup$MarginLayoutParams;
import android.util.AttributeSet;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
import java.util.List;
import android.support.v4.view.WindowInsetsCompat;
import android.widget.LinearLayout;

@CoordinatorLayout$DefaultBehavior(AppBarLayout$Behavior.class)
public class AppBarLayout extends LinearLayout
{
    private int mDownPreScrollRange;
    private int mDownScrollRange;
    boolean mHaveChildWithInterpolator;
    private WindowInsetsCompat mLastInsets;
    private final List<AppBarLayout$OnOffsetChangedListener> mListeners;
    private int mPendingAction;
    private float mTargetElevation;
    private int mTotalScrollRange;
    
    private void setWindowInsets(WindowInsetsCompat dispatchApplyWindowInsets) {
        this.mTotalScrollRange = -1;
        this.mLastInsets = dispatchApplyWindowInsets;
        for (int i = 0; i < this.getChildCount(); ++i) {
            dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(this.getChildAt(i), dispatchApplyWindowInsets);
            if (dispatchApplyWindowInsets.isConsumed()) {
                break;
            }
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof AppBarLayout$LayoutParams;
    }
    
    protected AppBarLayout$LayoutParams generateDefaultLayoutParams() {
        return new AppBarLayout$LayoutParams(-1, -2);
    }
    
    public AppBarLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new AppBarLayout$LayoutParams(this.getContext(), set);
    }
    
    protected AppBarLayout$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof LinearLayout$LayoutParams) {
            return new AppBarLayout$LayoutParams((LinearLayout$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new AppBarLayout$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new AppBarLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    final int getDownNestedPreScrollRange() {
        if (this.mDownPreScrollRange != -1) {
            return this.mDownPreScrollRange;
        }
        int mDownPreScrollRange = 0;
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            final View child = this.getChildAt(i);
            final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
            int n;
            if (ViewCompat.isLaidOut(child)) {
                n = child.getHeight();
            }
            else {
                n = child.getMeasuredHeight();
            }
            final int mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
            if ((mScrollFlags & 0x5) == 0x5) {
                final int n2 = appBarLayout$LayoutParams.bottomMargin + appBarLayout$LayoutParams.topMargin + mDownPreScrollRange;
                if ((mScrollFlags & 0x8) != 0x0) {
                    mDownPreScrollRange = n2 + ViewCompat.getMinimumHeight(child);
                }
                else {
                    mDownPreScrollRange = n2 + n;
                }
            }
            else if (mDownPreScrollRange > 0) {
                break;
            }
        }
        return this.mDownPreScrollRange = mDownPreScrollRange;
    }
    
    final int getDownNestedScrollRange() {
        if (this.mDownScrollRange != -1) {
            return this.mDownScrollRange;
        }
        final int childCount = this.getChildCount();
        int i = 0;
        int mDownScrollRange = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
            int n;
            if (ViewCompat.isLaidOut(child)) {
                n = child.getHeight();
            }
            else {
                n = child.getMeasuredHeight();
            }
            final int topMargin = appBarLayout$LayoutParams.topMargin;
            final int bottomMargin = appBarLayout$LayoutParams.bottomMargin;
            final int mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            mDownScrollRange += n + (topMargin + bottomMargin);
            if ((mScrollFlags & 0x2) != 0x0) {
                return mDownScrollRange - ViewCompat.getMinimumHeight(child);
            }
            ++i;
        }
        return this.mDownScrollRange = mDownScrollRange;
    }
    
    final int getMinimumHeightForVisibleOverlappingContent() {
        int n = 0;
        int systemWindowInsetTop;
        if (this.mLastInsets != null) {
            systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
        }
        else {
            systemWindowInsetTop = 0;
        }
        final int minimumHeight = ViewCompat.getMinimumHeight((View)this);
        if (minimumHeight != 0) {
            n = minimumHeight * 2 + systemWindowInsetTop;
        }
        else {
            final int childCount = this.getChildCount();
            if (childCount >= 1) {
                return ViewCompat.getMinimumHeight(this.getChildAt(childCount - 1)) * 2 + systemWindowInsetTop;
            }
        }
        return n;
    }
    
    int getPendingAction() {
        return this.mPendingAction;
    }
    
    public float getTargetElevation() {
        return this.mTargetElevation;
    }
    
    public final int getTotalScrollRange() {
        if (this.mTotalScrollRange != -1) {
            return this.mTotalScrollRange;
        }
        final int childCount = this.getChildCount();
        int i = 0;
        int n = 0;
        while (true) {
            while (i < childCount) {
                final View child = this.getChildAt(i);
                final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
                int n2;
                if (ViewCompat.isLaidOut(child)) {
                    n2 = child.getHeight();
                }
                else {
                    n2 = child.getMeasuredHeight();
                }
                final int mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
                if ((mScrollFlags & 0x1) == 0x0) {
                    break;
                }
                n += appBarLayout$LayoutParams.bottomMargin + (n2 + appBarLayout$LayoutParams.topMargin);
                if ((mScrollFlags & 0x2) != 0x0) {
                    n -= ViewCompat.getMinimumHeight(child);
                    int systemWindowInsetTop;
                    if (this.mLastInsets != null) {
                        systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
                    }
                    else {
                        systemWindowInsetTop = 0;
                    }
                    return this.mTotalScrollRange = n - systemWindowInsetTop;
                }
                ++i;
            }
            continue;
        }
    }
    
    final int getUpNestedPreScrollRange() {
        return this.getTotalScrollRange();
    }
    
    final boolean hasChildWithInterpolator() {
        return this.mHaveChildWithInterpolator;
    }
    
    final boolean hasScrollableChildren() {
        return this.getTotalScrollRange() != 0;
    }
    
    protected void onLayout(final boolean b, int i, int childCount, final int n, final int n2) {
        super.onLayout(b, i, childCount, n, n2);
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mHaveChildWithInterpolator = false;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            if (((AppBarLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).getScrollInterpolator() != null) {
                this.mHaveChildWithInterpolator = true;
                break;
            }
        }
    }
    
    void resetPendingAction() {
        this.mPendingAction = 0;
    }
    
    public void setExpanded(final boolean b) {
        this.setExpanded(b, ViewCompat.isLaidOut((View)this));
    }
    
    public void setExpanded(final boolean b, final boolean b2) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        int n2;
        if (b2) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        this.mPendingAction = (n2 | n);
        this.requestLayout();
    }
    
    public void setOrientation(final int orientation) {
        if (orientation != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(orientation);
    }
    
    public void setTargetElevation(final float mTargetElevation) {
        this.mTargetElevation = mTargetElevation;
    }
}
