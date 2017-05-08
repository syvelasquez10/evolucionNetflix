// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.design.R$attr;
import android.view.ViewGroup$MarginLayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.design.R$styleable;
import android.support.design.R$style;
import android.view.View;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import java.util.List;
import android.support.v4.view.WindowInsetsCompat;
import android.widget.LinearLayout;

@CoordinatorLayout$DefaultBehavior(AppBarLayout$Behavior.class)
public class AppBarLayout extends LinearLayout
{
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_NONE = 0;
    private boolean mCollapsed;
    private boolean mCollapsible;
    private int mDownPreScrollRange;
    private int mDownScrollRange;
    private boolean mHaveChildWithInterpolator;
    private WindowInsetsCompat mLastInsets;
    private List<AppBarLayout$OnOffsetChangedListener> mListeners;
    private int mPendingAction;
    private final int[] mTmpStatesArray;
    private int mTotalScrollRange;
    
    public AppBarLayout(final Context context) {
        this(context, null);
    }
    
    public AppBarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownScrollRange = -1;
        this.mPendingAction = 0;
        this.mTmpStatesArray = new int[2];
        this.setOrientation(1);
        ThemeUtils.checkAppCompatTheme(context);
        if (Build$VERSION.SDK_INT >= 21) {
            ViewUtilsLollipop.setBoundsViewOutlineProvider((View)this);
            ViewUtilsLollipop.setStateListAnimatorFromAttrs((View)this, set, 0, R$style.Widget_Design_AppBarLayout);
        }
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.AppBarLayout, 0, R$style.Widget_Design_AppBarLayout);
        ViewCompat.setBackground((View)this, obtainStyledAttributes.getDrawable(R$styleable.AppBarLayout_android_background));
        if (obtainStyledAttributes.hasValue(R$styleable.AppBarLayout_expanded)) {
            this.setExpanded(obtainStyledAttributes.getBoolean(R$styleable.AppBarLayout_expanded, false));
        }
        if (Build$VERSION.SDK_INT >= 21 && obtainStyledAttributes.hasValue(R$styleable.AppBarLayout_elevation)) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator((View)this, obtainStyledAttributes.getDimensionPixelSize(R$styleable.AppBarLayout_elevation, 0));
        }
        obtainStyledAttributes.recycle();
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new AppBarLayout$1(this));
    }
    
    private void invalidateScrollRanges() {
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownScrollRange = -1;
    }
    
    private boolean setCollapsibleState(final boolean mCollapsible) {
        if (this.mCollapsible != mCollapsible) {
            this.mCollapsible = mCollapsible;
            this.refreshDrawableState();
            return true;
        }
        return false;
    }
    
    private void updateCollapsible() {
        while (true) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                if (((AppBarLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).isCollapsible()) {
                    final boolean collapsibleState = true;
                    this.setCollapsibleState(collapsibleState);
                    return;
                }
            }
            final boolean collapsibleState = false;
            continue;
        }
    }
    
    public void addOnOffsetChangedListener(final AppBarLayout$OnOffsetChangedListener appBarLayout$OnOffsetChangedListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<AppBarLayout$OnOffsetChangedListener>();
        }
        if (appBarLayout$OnOffsetChangedListener != null && !this.mListeners.contains(appBarLayout$OnOffsetChangedListener)) {
            this.mListeners.add(appBarLayout$OnOffsetChangedListener);
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof AppBarLayout$LayoutParams;
    }
    
    void dispatchOffsetUpdates(final int n) {
        if (this.mListeners != null) {
            for (int size = this.mListeners.size(), i = 0; i < size; ++i) {
                final AppBarLayout$OnOffsetChangedListener appBarLayout$OnOffsetChangedListener = this.mListeners.get(i);
                if (appBarLayout$OnOffsetChangedListener != null) {
                    appBarLayout$OnOffsetChangedListener.onOffsetChanged(this, n);
                }
            }
        }
    }
    
    protected AppBarLayout$LayoutParams generateDefaultLayoutParams() {
        return new AppBarLayout$LayoutParams(-1, -2);
    }
    
    public AppBarLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new AppBarLayout$LayoutParams(this.getContext(), set);
    }
    
    protected AppBarLayout$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (Build$VERSION.SDK_INT >= 19 && viewGroup$LayoutParams instanceof LinearLayout$LayoutParams) {
            return new AppBarLayout$LayoutParams((LinearLayout$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new AppBarLayout$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new AppBarLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    int getDownNestedPreScrollRange() {
        if (this.mDownPreScrollRange != -1) {
            return this.mDownPreScrollRange;
        }
        int i = this.getChildCount() - 1;
        int n = 0;
        while (i >= 0) {
            final View child = this.getChildAt(i);
            final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
            if ((mScrollFlags & 0x5) == 0x5) {
                final int n2 = appBarLayout$LayoutParams.bottomMargin + appBarLayout$LayoutParams.topMargin + n;
                if ((mScrollFlags & 0x8) != 0x0) {
                    n = n2 + ViewCompat.getMinimumHeight(child);
                }
                else if ((mScrollFlags & 0x2) != 0x0) {
                    n = n2 + (measuredHeight - ViewCompat.getMinimumHeight(child));
                }
                else {
                    n = n2 + measuredHeight;
                }
            }
            else if (n > 0) {
                break;
            }
            --i;
        }
        return this.mDownPreScrollRange = Math.max(0, n);
    }
    
    int getDownNestedScrollRange() {
        if (this.mDownScrollRange != -1) {
            return this.mDownScrollRange;
        }
        final int childCount = this.getChildCount();
        int i = 0;
        int n = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int topMargin = appBarLayout$LayoutParams.topMargin;
            final int bottomMargin = appBarLayout$LayoutParams.bottomMargin;
            final int mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            n += measuredHeight + (topMargin + bottomMargin);
            if ((mScrollFlags & 0x2) != 0x0) {
                n -= ViewCompat.getMinimumHeight(child) + this.getTopInset();
                return this.mDownScrollRange = Math.max(0, n);
            }
            ++i;
        }
        return this.mDownScrollRange = Math.max(0, n);
    }
    
    final int getMinimumHeightForVisibleOverlappingContent() {
        final int topInset = this.getTopInset();
        final int minimumHeight = ViewCompat.getMinimumHeight((View)this);
        if (minimumHeight != 0) {
            return minimumHeight * 2 + topInset;
        }
        final int childCount = this.getChildCount();
        int minimumHeight2;
        if (childCount >= 1) {
            minimumHeight2 = ViewCompat.getMinimumHeight(this.getChildAt(childCount - 1));
        }
        else {
            minimumHeight2 = 0;
        }
        if (minimumHeight2 != 0) {
            return minimumHeight2 * 2 + topInset;
        }
        return this.getHeight() / 3;
    }
    
    int getPendingAction() {
        return this.mPendingAction;
    }
    
    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }
    
    final int getTopInset() {
        if (this.mLastInsets != null) {
            return this.mLastInsets.getSystemWindowInsetTop();
        }
        return 0;
    }
    
    public final int getTotalScrollRange() {
        if (this.mTotalScrollRange != -1) {
            return this.mTotalScrollRange;
        }
        final int childCount = this.getChildCount();
        int i = 0;
        int n = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            n += appBarLayout$LayoutParams.bottomMargin + (measuredHeight + appBarLayout$LayoutParams.topMargin);
            if ((mScrollFlags & 0x2) != 0x0) {
                n -= ViewCompat.getMinimumHeight(child);
                return this.mTotalScrollRange = Math.max(0, n - this.getTopInset());
            }
            ++i;
        }
        return this.mTotalScrollRange = Math.max(0, n - this.getTopInset());
    }
    
    int getUpNestedPreScrollRange() {
        return this.getTotalScrollRange();
    }
    
    boolean hasChildWithInterpolator() {
        return this.mHaveChildWithInterpolator;
    }
    
    boolean hasScrollableChildren() {
        return this.getTotalScrollRange() != 0;
    }
    
    protected int[] onCreateDrawableState(int n) {
        final int[] mTmpStatesArray = this.mTmpStatesArray;
        final int[] onCreateDrawableState = super.onCreateDrawableState(mTmpStatesArray.length + n);
        if (this.mCollapsible) {
            n = R$attr.state_collapsible;
        }
        else {
            n = -R$attr.state_collapsible;
        }
        mTmpStatesArray[0] = n;
        if (this.mCollapsible && this.mCollapsed) {
            n = R$attr.state_collapsed;
        }
        else {
            n = -R$attr.state_collapsed;
        }
        mTmpStatesArray[1] = n;
        return mergeDrawableStates(onCreateDrawableState, mTmpStatesArray);
    }
    
    protected void onLayout(final boolean b, int i, int childCount, final int n, final int n2) {
        super.onLayout(b, i, childCount, n, n2);
        this.invalidateScrollRanges();
        this.mHaveChildWithInterpolator = false;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            if (((AppBarLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).getScrollInterpolator() != null) {
                this.mHaveChildWithInterpolator = true;
                break;
            }
        }
        this.updateCollapsible();
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.invalidateScrollRanges();
    }
    
    WindowInsetsCompat onWindowInsetChanged(final WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat mLastInsets = null;
        if (ViewCompat.getFitsSystemWindows((View)this)) {
            mLastInsets = windowInsetsCompat;
        }
        if (!ViewUtils.objectEquals(this.mLastInsets, mLastInsets)) {
            this.mLastInsets = mLastInsets;
            this.invalidateScrollRanges();
        }
        return windowInsetsCompat;
    }
    
    public void removeOnOffsetChangedListener(final AppBarLayout$OnOffsetChangedListener appBarLayout$OnOffsetChangedListener) {
        if (this.mListeners != null && appBarLayout$OnOffsetChangedListener != null) {
            this.mListeners.remove(appBarLayout$OnOffsetChangedListener);
        }
    }
    
    void resetPendingAction() {
        this.mPendingAction = 0;
    }
    
    boolean setCollapsedState(final boolean mCollapsed) {
        if (this.mCollapsed != mCollapsed) {
            this.mCollapsed = mCollapsed;
            this.refreshDrawableState();
            return true;
        }
        return false;
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
    
    @Deprecated
    public void setTargetElevation(final float n) {
        if (Build$VERSION.SDK_INT >= 21) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator((View)this, n);
        }
    }
}
