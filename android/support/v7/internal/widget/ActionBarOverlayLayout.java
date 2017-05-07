// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.y;
import android.view.Menu;
import android.support.v7.internal.VersionUtils;
import android.support.v7.appcompat.R$id;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.appcompat.R$attr;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ScrollerCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.graphics.Rect;
import android.view.ViewGroup;

public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent
{
    static final int[] ATTRS;
    private final int ACTION_BAR_ANIMATE_DELAY;
    private ActionBarContainer mActionBarBottom;
    private int mActionBarHeight;
    private ActionBarContainer mActionBarTop;
    private ActionBarOverlayLayout$ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    private boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private final ViewPropertyAnimatorListener mBottomAnimatorListener;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    private ViewPropertyAnimatorCompat mCurrentActionBarBottomAnimator;
    private ViewPropertyAnimatorCompat mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private ScrollerCompat mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastInnerInsets;
    private int mLastSystemUiVisibility;
    private boolean mOverlayMode;
    private final Runnable mRemoveActionBarHideOffset;
    private final ViewPropertyAnimatorListener mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;
    
    static {
        ATTRS = new int[] { R$attr.actionBarSize, 16842841 };
    }
    
    public ActionBarOverlayLayout(final Context context) {
        super(context);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.ACTION_BAR_ANIMATE_DELAY = 600;
        this.mTopAnimatorListener = new ActionBarOverlayLayout$1(this);
        this.mBottomAnimatorListener = new ActionBarOverlayLayout$2(this);
        this.mRemoveActionBarHideOffset = new ActionBarOverlayLayout$3(this);
        this.mAddActionBarHideOffset = new ActionBarOverlayLayout$4(this);
        this.init(context);
    }
    
    public ActionBarOverlayLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.ACTION_BAR_ANIMATE_DELAY = 600;
        this.mTopAnimatorListener = new ActionBarOverlayLayout$1(this);
        this.mBottomAnimatorListener = new ActionBarOverlayLayout$2(this);
        this.mRemoveActionBarHideOffset = new ActionBarOverlayLayout$3(this);
        this.mAddActionBarHideOffset = new ActionBarOverlayLayout$4(this);
        this.init(context);
    }
    
    private void addActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }
    
    private boolean applyInsets(final View view, final Rect rect, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final boolean b5 = false;
        final ActionBarOverlayLayout$LayoutParams actionBarOverlayLayout$LayoutParams = (ActionBarOverlayLayout$LayoutParams)view.getLayoutParams();
        boolean b6 = b5;
        if (b) {
            b6 = b5;
            if (actionBarOverlayLayout$LayoutParams.leftMargin != rect.left) {
                actionBarOverlayLayout$LayoutParams.leftMargin = rect.left;
                b6 = true;
            }
        }
        boolean b7 = b6;
        if (b2) {
            b7 = b6;
            if (actionBarOverlayLayout$LayoutParams.topMargin != rect.top) {
                actionBarOverlayLayout$LayoutParams.topMargin = rect.top;
                b7 = true;
            }
        }
        boolean b8 = b7;
        if (b4) {
            b8 = b7;
            if (actionBarOverlayLayout$LayoutParams.rightMargin != rect.right) {
                actionBarOverlayLayout$LayoutParams.rightMargin = rect.right;
                b8 = true;
            }
        }
        if (b3 && actionBarOverlayLayout$LayoutParams.bottomMargin != rect.bottom) {
            actionBarOverlayLayout$LayoutParams.bottomMargin = rect.bottom;
            return true;
        }
        return b8;
    }
    
    private DecorToolbar getDecorToolbar(final View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar)view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar)view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }
    
    private void haltActionBarHideOffsetAnimations() {
        this.removeCallbacks(this.mRemoveActionBarHideOffset);
        this.removeCallbacks(this.mAddActionBarHideOffset);
        if (this.mCurrentActionBarTopAnimator != null) {
            this.mCurrentActionBarTopAnimator.cancel();
        }
        if (this.mCurrentActionBarBottomAnimator != null) {
            this.mCurrentActionBarBottomAnimator.cancel();
        }
    }
    
    private void init(final Context context) {
        final boolean b = true;
        final TypedArray obtainStyledAttributes = this.getContext().getTheme().obtainStyledAttributes(ActionBarOverlayLayout.ATTRS);
        this.mActionBarHeight = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mWindowContentOverlay = obtainStyledAttributes.getDrawable(1);
        this.setWillNotDraw(this.mWindowContentOverlay == null);
        obtainStyledAttributes.recycle();
        this.mIgnoreWindowContentOverlay = (context.getApplicationInfo().targetSdkVersion < 19 && b);
        this.mFlingEstimator = ScrollerCompat.create(context);
    }
    
    private void postAddActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mAddActionBarHideOffset, 600L);
    }
    
    private void postRemoveActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mRemoveActionBarHideOffset, 600L);
    }
    
    private void removeActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }
    
    private boolean shouldHideActionBarOnFling(final float n, final float n2) {
        boolean b = false;
        this.mFlingEstimator.fling(0, 0, 0, (int)n2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight()) {
            b = true;
        }
        return b;
    }
    
    public boolean canShowOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof ActionBarOverlayLayout$LayoutParams;
    }
    
    public void dismissPopups() {
        this.pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
            int n;
            if (this.mActionBarTop.getVisibility() == 0) {
                n = (int)(this.mActionBarTop.getBottom() + ViewCompat.getTranslationY((View)this.mActionBarTop) + 0.5f);
            }
            else {
                n = 0;
            }
            this.mWindowContentOverlay.setBounds(0, n, this.getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + n);
            this.mWindowContentOverlay.draw(canvas);
        }
    }
    
    protected boolean fitSystemWindows(final Rect rect) {
        this.pullChildren();
        if ((ViewCompat.getWindowSystemUiVisibility((View)this) & 0x100) != 0x0) {}
        boolean applyInsets = this.applyInsets((View)this.mActionBarTop, rect, true, true, false, true);
        if (this.mActionBarBottom != null) {
            applyInsets |= this.applyInsets((View)this.mActionBarBottom, rect, true, false, true, true);
        }
        this.mBaseInnerInsets.set(rect);
        ViewUtils.computeFitSystemWindows((View)this, this.mBaseInnerInsets, this.mBaseContentInsets);
        if (!this.mLastBaseContentInsets.equals((Object)this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            applyInsets = true;
        }
        if (applyInsets) {
            this.requestLayout();
        }
        return true;
    }
    
    protected ActionBarOverlayLayout$LayoutParams generateDefaultLayoutParams() {
        return new ActionBarOverlayLayout$LayoutParams(-1, -1);
    }
    
    public ActionBarOverlayLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new ActionBarOverlayLayout$LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return (ViewGroup$LayoutParams)new ActionBarOverlayLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getActionBarHideOffset() {
        if (this.mActionBarTop != null) {
            return -(int)ViewCompat.getTranslationY((View)this.mActionBarTop);
        }
        return 0;
    }
    
    public CharSequence getTitle() {
        this.pullChildren();
        return this.mDecorToolbar.getTitle();
    }
    
    public boolean hideOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }
    
    public void initFeature(final int n) {
        this.pullChildren();
        switch (n) {
            default: {}
            case 2: {
                this.mDecorToolbar.initProgress();
            }
            case 5: {
                this.mDecorToolbar.initIndeterminateProgress();
            }
            case 9: {
                this.setOverlayMode(true);
            }
        }
    }
    
    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }
    
    public boolean isOverflowMenuShowPending() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }
    
    public boolean isOverflowMenuShowing() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        if (Build$VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.init(this.getContext());
        ViewCompat.requestApplyInsets((View)this);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.haltActionBarHideOffsetAnimations();
    }
    
    protected void onLayout(final boolean b, int i, final int n, int n2, final int n3) {
        final int childCount = this.getChildCount();
        final int paddingLeft = this.getPaddingLeft();
        this.getPaddingRight();
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        View child;
        ActionBarOverlayLayout$LayoutParams actionBarOverlayLayout$LayoutParams;
        int measuredWidth;
        int measuredHeight;
        int n4;
        for (i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                actionBarOverlayLayout$LayoutParams = (ActionBarOverlayLayout$LayoutParams)child.getLayoutParams();
                measuredWidth = child.getMeasuredWidth();
                measuredHeight = child.getMeasuredHeight();
                n4 = actionBarOverlayLayout$LayoutParams.leftMargin + paddingLeft;
                if (child == this.mActionBarBottom) {
                    n2 = n3 - n - paddingBottom - measuredHeight - actionBarOverlayLayout$LayoutParams.bottomMargin;
                }
                else {
                    n2 = actionBarOverlayLayout$LayoutParams.topMargin + paddingTop;
                }
                child.layout(n4, n2, measuredWidth + n4, measuredHeight + n2);
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.pullChildren();
        this.measureChildWithMargins((View)this.mActionBarTop, n, 0, n2, 0);
        final ActionBarOverlayLayout$LayoutParams actionBarOverlayLayout$LayoutParams = (ActionBarOverlayLayout$LayoutParams)this.mActionBarTop.getLayoutParams();
        int n3 = Math.max(0, this.mActionBarTop.getMeasuredWidth() + actionBarOverlayLayout$LayoutParams.leftMargin + actionBarOverlayLayout$LayoutParams.rightMargin);
        int n4 = Math.max(0, actionBarOverlayLayout$LayoutParams.bottomMargin + (this.mActionBarTop.getMeasuredHeight() + actionBarOverlayLayout$LayoutParams.topMargin));
        int n5 = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState((View)this.mActionBarTop));
        if (this.mActionBarBottom != null) {
            this.measureChildWithMargins((View)this.mActionBarBottom, n, 0, n2, 0);
            final ActionBarOverlayLayout$LayoutParams actionBarOverlayLayout$LayoutParams2 = (ActionBarOverlayLayout$LayoutParams)this.mActionBarBottom.getLayoutParams();
            n3 = Math.max(n3, this.mActionBarBottom.getMeasuredWidth() + actionBarOverlayLayout$LayoutParams2.leftMargin + actionBarOverlayLayout$LayoutParams2.rightMargin);
            n4 = Math.max(n4, actionBarOverlayLayout$LayoutParams2.bottomMargin + (this.mActionBarBottom.getMeasuredHeight() + actionBarOverlayLayout$LayoutParams2.topMargin));
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mActionBarBottom));
        }
        boolean b;
        if ((ViewCompat.getWindowSystemUiVisibility((View)this) & 0x100) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        int n7;
        if (b) {
            final int n6 = n7 = this.mActionBarHeight;
            if (this.mHasNonEmbeddedTabs) {
                n7 = n6;
                if (this.mActionBarTop.getTabContainer() != null) {
                    n7 = n6 + this.mActionBarHeight;
                }
            }
        }
        else if (this.mActionBarTop.getVisibility() != 8) {
            n7 = this.mActionBarTop.getMeasuredHeight();
        }
        else {
            n7 = 0;
        }
        int n8;
        if (this.mDecorToolbar.isSplit() && this.mActionBarBottom != null) {
            if (b) {
                n8 = this.mActionBarHeight;
            }
            else {
                n8 = this.mActionBarBottom.getMeasuredHeight();
            }
        }
        else {
            n8 = 0;
        }
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets.set(this.mBaseInnerInsets);
        if (!this.mOverlayMode && !b) {
            final Rect mContentInsets = this.mContentInsets;
            mContentInsets.top += n7;
            final Rect mContentInsets2 = this.mContentInsets;
            mContentInsets2.bottom += n8;
        }
        else {
            final Rect mInnerInsets = this.mInnerInsets;
            mInnerInsets.top += n7;
            final Rect mInnerInsets2 = this.mInnerInsets;
            mInnerInsets2.bottom += n8;
        }
        this.applyInsets((View)this.mContent, this.mContentInsets, true, true, true, true);
        if (!this.mLastInnerInsets.equals((Object)this.mInnerInsets)) {
            this.mLastInnerInsets.set(this.mInnerInsets);
            this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
        }
        this.measureChildWithMargins((View)this.mContent, n, 0, n2, 0);
        final ActionBarOverlayLayout$LayoutParams actionBarOverlayLayout$LayoutParams3 = (ActionBarOverlayLayout$LayoutParams)this.mContent.getLayoutParams();
        final int max = Math.max(n3, this.mContent.getMeasuredWidth() + actionBarOverlayLayout$LayoutParams3.leftMargin + actionBarOverlayLayout$LayoutParams3.rightMargin);
        final int max2 = Math.max(n4, actionBarOverlayLayout$LayoutParams3.bottomMargin + (this.mContent.getMeasuredHeight() + actionBarOverlayLayout$LayoutParams3.topMargin));
        final int combineMeasuredStates = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mContent));
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(max + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), n, combineMeasuredStates), ViewCompat.resolveSizeAndState(Math.max(max2 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), n2, combineMeasuredStates << 16));
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        if (!this.mHideOnContentScroll || !b) {
            return false;
        }
        if (this.shouldHideActionBarOnFling(n, n2)) {
            this.addActionBarHideOffset();
        }
        else {
            this.removeActionBarHideOffset();
        }
        return this.mAnimatingForFling = true;
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, final int n4) {
        this.setActionBarHideOffset(this.mHideOnContentScrollReference += n2);
    }
    
    public void onNestedScrollAccepted(final View view, final View view2, final int n) {
        super.onNestedScrollAccepted(view, view2, n);
        this.mHideOnContentScrollReference = this.getActionBarHideOffset();
        this.haltActionBarHideOffsetAnimations();
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStarted();
        }
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        return (n & 0x2) != 0x0 && this.mActionBarTop.getVisibility() == 0 && this.mHideOnContentScroll;
    }
    
    public void onStopNestedScroll(final View view) {
        super.onStopNestedScroll(view);
        if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                this.postRemoveActionBarHideOffset();
            }
            else {
                this.postAddActionBarHideOffset();
            }
        }
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStopped();
        }
    }
    
    public void onWindowSystemUiVisibilityChanged(final int mLastSystemUiVisibility) {
        boolean b = true;
        if (Build$VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(mLastSystemUiVisibility);
        }
        this.pullChildren();
        final int mLastSystemUiVisibility2 = this.mLastSystemUiVisibility;
        this.mLastSystemUiVisibility = mLastSystemUiVisibility;
        boolean b2;
        if ((mLastSystemUiVisibility & 0x4) == 0x0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if ((mLastSystemUiVisibility & 0x100) != 0x0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if (this.mActionBarVisibilityCallback != null) {
            final ActionBarOverlayLayout$ActionBarVisibilityCallback mActionBarVisibilityCallback = this.mActionBarVisibilityCallback;
            if (b3) {
                b = false;
            }
            mActionBarVisibilityCallback.enableContentAnimations(b);
            if (b2 || !b3) {
                this.mActionBarVisibilityCallback.showForSystem();
            }
            else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if (((mLastSystemUiVisibility2 ^ mLastSystemUiVisibility) & 0x100) != 0x0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets((View)this);
        }
    }
    
    protected void onWindowVisibilityChanged(final int mWindowVisibility) {
        super.onWindowVisibilityChanged(mWindowVisibility);
        this.mWindowVisibility = mWindowVisibility;
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(mWindowVisibility);
        }
    }
    
    void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout)this.findViewById(R$id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer)this.findViewById(R$id.action_bar_container);
            this.mDecorToolbar = this.getDecorToolbar(this.findViewById(R$id.action_bar));
            this.mActionBarBottom = (ActionBarContainer)this.findViewById(R$id.split_action_bar);
        }
    }
    
    public void setActionBarHideOffset(int max) {
        this.haltActionBarHideOffsetAnimations();
        final int height = this.mActionBarTop.getHeight();
        max = Math.max(0, Math.min(max, height));
        ViewCompat.setTranslationY((View)this.mActionBarTop, -max);
        if (this.mActionBarBottom != null && this.mActionBarBottom.getVisibility() != 8) {
            max = max / height * this.mActionBarBottom.getHeight();
            ViewCompat.setTranslationY((View)this.mActionBarBottom, max);
        }
    }
    
    public void setActionBarVisibilityCallback(final ActionBarOverlayLayout$ActionBarVisibilityCallback mActionBarVisibilityCallback) {
        this.mActionBarVisibilityCallback = mActionBarVisibilityCallback;
        if (this.getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            if (this.mLastSystemUiVisibility != 0) {
                this.onWindowSystemUiVisibilityChanged(this.mLastSystemUiVisibility);
                ViewCompat.requestApplyInsets((View)this);
            }
        }
    }
    
    public void setHasNonEmbeddedTabs(final boolean mHasNonEmbeddedTabs) {
        this.mHasNonEmbeddedTabs = mHasNonEmbeddedTabs;
    }
    
    public void setHideOnContentScrollEnabled(final boolean mHideOnContentScroll) {
        if (mHideOnContentScroll != this.mHideOnContentScroll && !(this.mHideOnContentScroll = mHideOnContentScroll)) {
            if (VersionUtils.isAtLeastL()) {
                this.stopNestedScroll();
            }
            this.haltActionBarHideOffsetAnimations();
            this.setActionBarHideOffset(0);
        }
    }
    
    public void setIcon(final int icon) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(icon);
    }
    
    public void setIcon(final Drawable icon) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(icon);
    }
    
    public void setLogo(final int logo) {
        this.pullChildren();
        this.mDecorToolbar.setLogo(logo);
    }
    
    public void setMenu(final Menu menu, final y y) {
        this.pullChildren();
        this.mDecorToolbar.setMenu(menu, y);
    }
    
    public void setMenuPrepared() {
        this.pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }
    
    public void setOverlayMode(final boolean mOverlayMode) {
        this.mOverlayMode = mOverlayMode;
        this.mIgnoreWindowContentOverlay = (mOverlayMode && this.getContext().getApplicationInfo().targetSdkVersion < 19);
    }
    
    public void setShowingForActionMode(final boolean b) {
    }
    
    public void setUiOptions(final int n) {
    }
    
    public void setWindowCallback(final WindowCallback windowCallback) {
        this.pullChildren();
        this.mDecorToolbar.setWindowCallback(windowCallback);
    }
    
    public void setWindowTitle(final CharSequence windowTitle) {
        this.pullChildren();
        this.mDecorToolbar.setWindowTitle(windowTitle);
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    
    public boolean showOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }
}
