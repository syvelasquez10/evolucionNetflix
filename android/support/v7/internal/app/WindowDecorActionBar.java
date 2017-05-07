// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.view.View$MeasureSpec;
import android.support.v7.internal.widget.ViewUtils;
import android.view.accessibility.AccessibilityEvent;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.internal.view.menu.x;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.internal.view.menu.i;
import android.view.View$OnClickListener;
import android.support.v7.internal.widget.ActionBarContextView$1;
import android.view.animation.Interpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.appcompat.R$layout;
import android.support.v7.internal.widget.TintTypedArray;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.internal.widget.AbsActionBarView;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.AnimationUtils;
import android.support.v4.view.ViewCompat;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.appcompat.R$id;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Build$VERSION;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.widget.ActionBarContextView;
import android.content.Context;
import android.view.View;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.internal.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback;
import android.support.v7.app.ActionBar;

public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout$ActionBarVisibilityCallback
{
    private static final boolean ALLOW_SHOW_HIDE_ANIMATIONS;
    WindowDecorActionBar$ActionModeImpl mActionMode;
    private FragmentActivity mActivity;
    private ActionBarContainer mContainerView;
    private boolean mContentAnimations;
    private View mContentView;
    private Context mContext;
    private int mContextDisplayMode;
    private ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    private ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    private DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode$Callback mDeferredModeDestroyCallback;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    private boolean mHiddenByApp;
    private boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<ActionBar$OnMenuVisibilityListener> mMenuVisibilityListeners;
    private boolean mNowShowing;
    private ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    private ActionBarContainer mSplitView;
    private ScrollingTabContainerView mTabScrollView;
    private ArrayList<WindowDecorActionBar$TabImpl> mTabs;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;
    
    static {
        final boolean b = true;
        ALLOW_SHOW_HIDE_ANIMATIONS = (Build$VERSION.SDK_INT >= 14 && b);
    }
    
    public WindowDecorActionBar(final ActionBarActivity mActivity, final boolean b) {
        this.mTabs = new ArrayList<WindowDecorActionBar$TabImpl>();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList<ActionBar$OnMenuVisibilityListener>();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new WindowDecorActionBar$1(this);
        this.mShowListener = new WindowDecorActionBar$2(this);
        this.mUpdateListener = new WindowDecorActionBar$3(this);
        this.mActivity = mActivity;
        final View decorView = mActivity.getWindow().getDecorView();
        this.init(decorView);
        if (!b) {
            this.mContentView = decorView.findViewById(16908290);
        }
    }
    
    private static boolean checkShowingFlags(final boolean b, final boolean b2, final boolean b3) {
        return b3 || (!b && !b2);
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
    
    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(false);
            }
            this.updateVisibility(false);
        }
    }
    
    private void init(final View view) {
        this.mOverlayLayout = (ActionBarOverlayLayout)view.findViewById(R$id.decor_content_parent);
        if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.mDecorToolbar = this.getDecorToolbar(view.findViewById(R$id.action_bar));
        this.mContextView = (ActionBarContextView)view.findViewById(R$id.action_context_bar);
        this.mContainerView = (ActionBarContainer)view.findViewById(R$id.action_bar_container);
        this.mSplitView = (ActionBarContainer)view.findViewById(R$id.split_action_bar);
        if (this.mDecorToolbar == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.mContext = this.mDecorToolbar.getContext();
        int mContextDisplayMode;
        if (this.mDecorToolbar.isSplit()) {
            mContextDisplayMode = 1;
        }
        else {
            mContextDisplayMode = 0;
        }
        this.mContextDisplayMode = mContextDisplayMode;
        boolean b;
        if ((this.mDecorToolbar.getDisplayOptions() & 0x4) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.mDisplayHomeAsUpSet = true;
        }
        final ActionBarPolicy value = ActionBarPolicy.get(this.mContext);
        this.setHomeButtonEnabled(value.enableHomeButtonByDefault() || b);
        this.setHasEmbeddedTabs(value.hasEmbeddedTabs());
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes((AttributeSet)null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            this.setHideOnContentScrollEnabled(true);
        }
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            this.setElevation(dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }
    
    private void setHasEmbeddedTabs(final boolean mHasEmbeddedTabs) {
        final boolean b = true;
        if (!(this.mHasEmbeddedTabs = mHasEmbeddedTabs)) {
            this.mDecorToolbar.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        }
        else {
            this.mContainerView.setTabContainer(null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        }
        boolean b2;
        if (this.getNavigationMode() == 2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.mTabScrollView != null) {
            if (b2) {
                this.mTabScrollView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            }
            else {
                this.mTabScrollView.setVisibility(8);
            }
        }
        this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && b2);
        this.mOverlayLayout.setHasNonEmbeddedTabs(!this.mHasEmbeddedTabs && b2 && b);
    }
    
    private void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(true);
            }
            this.updateVisibility(false);
        }
    }
    
    private void updateVisibility(final boolean b) {
        if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (!this.mNowShowing) {
                this.mNowShowing = true;
                this.doShow(b);
            }
        }
        else if (this.mNowShowing) {
            this.mNowShowing = false;
            this.doHide(b);
        }
    }
    
    public void animateToMode(final boolean b) {
        final boolean b2 = false;
        if (b) {
            this.showForActionMode();
        }
        else {
            this.hideForActionMode();
        }
        final DecorToolbar mDecorToolbar = this.mDecorToolbar;
        int n;
        if (b) {
            n = 8;
        }
        else {
            n = 0;
        }
        mDecorToolbar.animateToVisibility(n);
        final ActionBarContextView mContextView = this.mContextView;
        int n2;
        if (b) {
            n2 = (b2 ? 1 : 0);
        }
        else {
            n2 = 8;
        }
        mContextView.animateToVisibility(n2);
    }
    
    @Override
    public boolean collapseActionView() {
        if (this.mDecorToolbar != null && this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            return true;
        }
        return false;
    }
    
    void completeDeferredDestroyActionMode() {
        if (this.mDeferredModeDestroyCallback != null) {
            this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }
    
    @Override
    public void dispatchMenuVisibilityChanged(final boolean mLastMenuVisibility) {
        if (mLastMenuVisibility != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = mLastMenuVisibility;
            for (int size = this.mMenuVisibilityListeners.size(), i = 0; i < size; ++i) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(mLastMenuVisibility);
            }
        }
    }
    
    public void doHide(final boolean b) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        if (this.mCurWindowVisibility == 0 && WindowDecorActionBar.ALLOW_SHOW_HIDE_ANIMATIONS && (this.mShowHideAnimationEnabled || b)) {
            ViewCompat.setAlpha((View)this.mContainerView, 1.0f);
            this.mContainerView.setTransitioning(true);
            final ViewPropertyAnimatorCompatSet mCurrentShowAnim = new ViewPropertyAnimatorCompatSet();
            float n2;
            final float n = n2 = -this.mContainerView.getHeight();
            if (b) {
                final int[] array2;
                final int[] array = array2 = new int[2];
                array2[1] = (array2[0] = 0);
                this.mContainerView.getLocationInWindow(array);
                n2 = n - array[1];
            }
            final ViewPropertyAnimatorCompat translationY = ViewCompat.animate((View)this.mContainerView).translationY(n2);
            translationY.setUpdateListener(this.mUpdateListener);
            mCurrentShowAnim.play(translationY);
            if (this.mContentAnimations && this.mContentView != null) {
                mCurrentShowAnim.play(ViewCompat.animate(this.mContentView).translationY(n2));
            }
            if (this.mSplitView != null && this.mSplitView.getVisibility() == 0) {
                ViewCompat.setAlpha((View)this.mSplitView, 1.0f);
                mCurrentShowAnim.play(ViewCompat.animate((View)this.mSplitView).translationY(this.mSplitView.getHeight()));
            }
            mCurrentShowAnim.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, 17432581));
            mCurrentShowAnim.setDuration(250L);
            mCurrentShowAnim.setListener(this.mHideListener);
            (this.mCurrentShowAnim = mCurrentShowAnim).start();
            return;
        }
        this.mHideListener.onAnimationEnd(null);
    }
    
    public void doShow(final boolean b) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility == 0 && WindowDecorActionBar.ALLOW_SHOW_HIDE_ANIMATIONS && (this.mShowHideAnimationEnabled || b)) {
            ViewCompat.setTranslationY((View)this.mContainerView, 0.0f);
            float n2;
            final float n = n2 = -this.mContainerView.getHeight();
            if (b) {
                final int[] array2;
                final int[] array = array2 = new int[2];
                array2[1] = (array2[0] = 0);
                this.mContainerView.getLocationInWindow(array);
                n2 = n - array[1];
            }
            ViewCompat.setTranslationY((View)this.mContainerView, n2);
            final ViewPropertyAnimatorCompatSet mCurrentShowAnim = new ViewPropertyAnimatorCompatSet();
            final ViewPropertyAnimatorCompat translationY = ViewCompat.animate((View)this.mContainerView).translationY(0.0f);
            translationY.setUpdateListener(this.mUpdateListener);
            mCurrentShowAnim.play(translationY);
            if (this.mContentAnimations && this.mContentView != null) {
                ViewCompat.setTranslationY(this.mContentView, n2);
                mCurrentShowAnim.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
            }
            if (this.mSplitView != null && this.mContextDisplayMode == 1) {
                ViewCompat.setTranslationY((View)this.mSplitView, this.mSplitView.getHeight());
                this.mSplitView.setVisibility(0);
                mCurrentShowAnim.play(ViewCompat.animate((View)this.mSplitView).translationY(0.0f));
            }
            mCurrentShowAnim.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, 17432582));
            mCurrentShowAnim.setDuration(250L);
            mCurrentShowAnim.setListener(this.mShowListener);
            (this.mCurrentShowAnim = mCurrentShowAnim).start();
        }
        else {
            ViewCompat.setAlpha((View)this.mContainerView, 1.0f);
            ViewCompat.setTranslationY((View)this.mContainerView, 0.0f);
            if (this.mContentAnimations && this.mContentView != null) {
                ViewCompat.setTranslationY(this.mContentView, 0.0f);
            }
            if (this.mSplitView != null && this.mContextDisplayMode == 1) {
                ViewCompat.setAlpha((View)this.mSplitView, 1.0f);
                ViewCompat.setTranslationY((View)this.mSplitView, 0.0f);
                this.mSplitView.setVisibility(0);
            }
            this.mShowListener.onAnimationEnd(null);
        }
        if (this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
        }
    }
    
    @Override
    public void enableContentAnimations(final boolean mContentAnimations) {
        this.mContentAnimations = mContentAnimations;
    }
    
    @Override
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }
    
    public int getHeight() {
        return this.mContainerView.getHeight();
    }
    
    @Override
    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }
    
    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }
    
    @Override
    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            final TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            final int resourceId = typedValue.resourceId;
            if (resourceId != 0) {
                this.mThemedContext = (Context)new ContextThemeWrapper(this.mContext, resourceId);
            }
            else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }
    
    @Override
    public void hide() {
        if (!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            this.updateVisibility(false);
        }
    }
    
    @Override
    public void hideForSystem() {
        if (!this.mHiddenBySystem) {
            this.updateVisibility(this.mHiddenBySystem = true);
        }
    }
    
    @Override
    public boolean isShowing() {
        final int height = this.getHeight();
        return this.mNowShowing && (height == 0 || this.getHideOffset() < height);
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        this.setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }
    
    @Override
    public void onContentScrollStarted() {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
            this.mCurrentShowAnim = null;
        }
    }
    
    @Override
    public void onContentScrollStopped() {
    }
    
    @Override
    public void onWindowVisibilityChanged(final int mCurWindowVisibility) {
        this.mCurWindowVisibility = mCurWindowVisibility;
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable primaryBackground) {
        this.mContainerView.setPrimaryBackground(primaryBackground);
    }
    
    @Override
    public void setCustomView(final View customView, final ActionBar$LayoutParams layoutParams) {
        customView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mDecorToolbar.setCustomView(customView);
    }
    
    @Override
    public void setDefaultDisplayHomeAsUpEnabled(final boolean displayHomeAsUpEnabled) {
        if (!this.mDisplayHomeAsUpSet) {
            this.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
        }
    }
    
    @Override
    public void setDisplayHomeAsUpEnabled(final boolean b) {
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 4);
    }
    
    public void setDisplayOptions(final int n, final int n2) {
        final int displayOptions = this.mDecorToolbar.getDisplayOptions();
        if ((n2 & 0x4) != 0x0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions((displayOptions & ~n2) | (n & n2));
    }
    
    @Override
    public void setDisplayShowCustomEnabled(final boolean b) {
        int n;
        if (b) {
            n = 16;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 16);
    }
    
    @Override
    public void setDisplayShowHomeEnabled(final boolean b) {
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 2);
    }
    
    @Override
    public void setDisplayShowTitleEnabled(final boolean b) {
        int n;
        if (b) {
            n = 8;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 8);
    }
    
    @Override
    public void setDisplayUseLogoEnabled(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 1);
    }
    
    @Override
    public void setElevation(final float n) {
        ViewCompat.setElevation((View)this.mContainerView, n);
        if (this.mSplitView != null) {
            ViewCompat.setElevation((View)this.mSplitView, n);
        }
    }
    
    @Override
    public void setHideOnContentScrollEnabled(final boolean b) {
        if (b && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.mHideOnContentScroll = b;
        this.mOverlayLayout.setHideOnContentScrollEnabled(b);
    }
    
    @Override
    public void setHomeActionContentDescription(final int navigationContentDescription) {
        this.mDecorToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setHomeAsUpIndicator(final Drawable navigationIcon) {
        this.mDecorToolbar.setNavigationIcon(navigationIcon);
    }
    
    @Override
    public void setHomeButtonEnabled(final boolean homeButtonEnabled) {
        this.mDecorToolbar.setHomeButtonEnabled(homeButtonEnabled);
    }
    
    @Override
    public void setLogo(final int logo) {
        this.mDecorToolbar.setLogo(logo);
    }
    
    @Override
    public void setShowHideAnimationEnabled(final boolean mShowHideAnimationEnabled) {
        this.mShowHideAnimationEnabled = mShowHideAnimationEnabled;
        if (!mShowHideAnimationEnabled && this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }
    
    @Override
    public void setWindowTitle(final CharSequence windowTitle) {
        this.mDecorToolbar.setWindowTitle(windowTitle);
    }
    
    @Override
    public void show() {
        if (this.mHiddenByApp) {
            this.updateVisibility(this.mHiddenByApp = false);
        }
    }
    
    @Override
    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            this.updateVisibility(true);
        }
    }
    
    @Override
    public ActionMode startActionMode(final ActionMode$Callback actionMode$Callback) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        final WindowDecorActionBar$ActionModeImpl mActionMode = new WindowDecorActionBar$ActionModeImpl(this, actionMode$Callback);
        if (mActionMode.dispatchOnCreate()) {
            mActionMode.invalidate();
            this.mContextView.initForMode(mActionMode);
            this.animateToMode(true);
            if (this.mSplitView != null && this.mContextDisplayMode == 1 && this.mSplitView.getVisibility() != 0) {
                this.mSplitView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            }
            this.mContextView.sendAccessibilityEvent(32);
            return this.mActionMode = mActionMode;
        }
        return null;
    }
}
