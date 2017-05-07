// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ViewGroup$MarginLayoutParams;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.widget.FrameLayout$LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.view.CollapsibleActionView;
import android.widget.LinearLayout$LayoutParams;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.view.ViewParent;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.text.TextUtils;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.util.AttributeSet;
import android.view.Window$Callback;
import android.widget.TextView;
import android.support.v7.internal.widget.SpinnerICS;
import android.support.v7.internal.widget.AdapterViewICS;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.widget.LinearLayout;
import android.support.v7.internal.widget.ProgressBarICS;
import android.view.View$OnClickListener;
import android.support.v7.internal.widget.AbsActionBarView;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.view.Menu;
import java.lang.ref.WeakReference;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.ViewGroup$LayoutParams;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.widget.SpinnerAdapter;
import android.view.animation.AnimationUtils;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.appcompat.R;
import android.view.ViewGroup;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import java.util.ArrayList;
import android.os.Handler;
import android.app.Dialog;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.widget.ActionBarContextView;
import android.content.Context;
import android.view.View;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarView;

class ActionBarImplBase extends ActionBar
{
    private static final int CONTEXT_DISPLAY_NORMAL = 0;
    private static final int CONTEXT_DISPLAY_SPLIT = 1;
    private static final int INVALID_POSITION = -1;
    ActionModeImpl mActionMode;
    private ActionBarView mActionView;
    private ActionBarActivity mActivity;
    private Callback mCallback;
    private ActionBarContainer mContainerView;
    private View mContentView;
    private Context mContext;
    private int mContextDisplayMode;
    private ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    final Handler mHandler;
    private boolean mHasEmbeddedTabs;
    private boolean mHiddenByApp;
    private boolean mHiddenBySystem;
    private boolean mLastMenuVisibility;
    private ArrayList<OnMenuVisibilityListener> mMenuVisibilityListeners;
    private boolean mNowShowing;
    private ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    private boolean mShowingForMode;
    private ActionBarContainer mSplitView;
    private ScrollingTabContainerView mTabScrollView;
    Runnable mTabSelector;
    private ArrayList<TabImpl> mTabs;
    private Context mThemedContext;
    private ViewGroup mTopVisibilityView;
    
    public ActionBarImplBase(final ActionBarActivity actionBarActivity, final Callback mCallback) {
        this.mTabs = new ArrayList<TabImpl>();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList<OnMenuVisibilityListener>();
        this.mHandler = new Handler();
        this.mCurWindowVisibility = 0;
        this.mNowShowing = true;
        this.mActivity = actionBarActivity;
        this.mContext = (Context)actionBarActivity;
        this.mCallback = mCallback;
        this.init(this.mActivity);
    }
    
    private static boolean checkShowingFlags(final boolean b, final boolean b2, final boolean b3) {
        return b3 || (!b && !b2);
    }
    
    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            this.selectTab(null);
        }
        this.mTabs.clear();
        if (this.mTabScrollView != null) {
            this.mTabScrollView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }
    
    private void configureTab(final Tab tab, int i) {
        final TabImpl tabImpl = (TabImpl)tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(i);
        this.mTabs.add(i, tabImpl);
        int size;
        for (size = this.mTabs.size(), ++i; i < size; ++i) {
            this.mTabs.get(i).setPosition(i);
        }
    }
    
    private void ensureTabsExist() {
        if (this.mTabScrollView != null) {
            return;
        }
        final ScrollingTabContainerView mTabScrollView = new ScrollingTabContainerView(this.mContext);
        if (this.mHasEmbeddedTabs) {
            mTabScrollView.setVisibility(0);
            this.mActionView.setEmbeddedTabView(mTabScrollView);
        }
        else {
            if (this.getNavigationMode() == 2) {
                mTabScrollView.setVisibility(0);
            }
            else {
                mTabScrollView.setVisibility(8);
            }
            this.mContainerView.setTabContainer(mTabScrollView);
        }
        this.mTabScrollView = mTabScrollView;
    }
    
    private void init(final ActionBarActivity actionBarActivity) {
        boolean homeButtonEnabled = false;
        this.mOverlayLayout = (ActionBarOverlayLayout)actionBarActivity.findViewById(R.id.action_bar_overlay_layout);
        if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setActionBar(this);
        }
        this.mActionView = (ActionBarView)actionBarActivity.findViewById(R.id.action_bar);
        this.mContextView = (ActionBarContextView)actionBarActivity.findViewById(R.id.action_context_bar);
        this.mContainerView = (ActionBarContainer)actionBarActivity.findViewById(R.id.action_bar_container);
        this.mTopVisibilityView = (ViewGroup)actionBarActivity.findViewById(R.id.top_action_bar);
        if (this.mTopVisibilityView == null) {
            this.mTopVisibilityView = (ViewGroup)this.mContainerView;
        }
        this.mSplitView = (ActionBarContainer)actionBarActivity.findViewById(R.id.split_action_bar);
        if (this.mActionView == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.mActionView.setContextView(this.mContextView);
        int mContextDisplayMode;
        if (this.mActionView.isSplitActionBar()) {
            mContextDisplayMode = 1;
        }
        else {
            mContextDisplayMode = 0;
        }
        this.mContextDisplayMode = mContextDisplayMode;
        boolean b;
        if ((this.mActionView.getDisplayOptions() & 0x4) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.mDisplayHomeAsUpSet = true;
        }
        final ActionBarPolicy value = ActionBarPolicy.get(this.mContext);
        if (value.enableHomeButtonByDefault() || b) {
            homeButtonEnabled = true;
        }
        this.setHomeButtonEnabled(homeButtonEnabled);
        this.setHasEmbeddedTabs(value.hasEmbeddedTabs());
        this.setTitle(this.mActivity.getTitle());
    }
    
    private void setHasEmbeddedTabs(final boolean mHasEmbeddedTabs) {
        final boolean b = true;
        if (!(this.mHasEmbeddedTabs = mHasEmbeddedTabs)) {
            this.mActionView.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        }
        else {
            this.mContainerView.setTabContainer(null);
            this.mActionView.setEmbeddedTabView(this.mTabScrollView);
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
            }
            else {
                this.mTabScrollView.setVisibility(8);
            }
        }
        this.mActionView.setCollapsable(!this.mHasEmbeddedTabs && b2 && b);
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
    
    @Override
    public void addOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }
    
    @Override
    public void addTab(final Tab tab) {
        this.addTab(tab, this.mTabs.isEmpty());
    }
    
    @Override
    public void addTab(final Tab tab, final int n) {
        this.addTab(tab, n, this.mTabs.isEmpty());
    }
    
    @Override
    public void addTab(final Tab tab, final int n, final boolean b) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(tab, n, b);
        this.configureTab(tab, n);
        if (b) {
            this.selectTab(tab);
        }
    }
    
    @Override
    public void addTab(final Tab tab, final boolean b) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(tab, b);
        this.configureTab(tab, this.mTabs.size());
        if (b) {
            this.selectTab(tab);
        }
    }
    
    void animateToMode(final boolean b) {
        final int n = 8;
        if (b) {
            this.showForActionMode();
        }
        else {
            this.hideForActionMode();
        }
        final ActionBarView mActionView = this.mActionView;
        int n2;
        if (b) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        mActionView.animateToVisibility(n2);
        final ActionBarContextView mContextView = this.mContextView;
        int n3;
        if (b) {
            n3 = 0;
        }
        else {
            n3 = 8;
        }
        mContextView.animateToVisibility(n3);
        if (this.mTabScrollView != null && !this.mActionView.hasEmbeddedTabs() && this.mActionView.isCollapsed()) {
            final ScrollingTabContainerView mTabScrollView = this.mTabScrollView;
            int visibility;
            if (b) {
                visibility = n;
            }
            else {
                visibility = 0;
            }
            mTabScrollView.setVisibility(visibility);
        }
    }
    
    public void doHide(final boolean b) {
        this.mTopVisibilityView.clearAnimation();
        if (this.mTopVisibilityView.getVisibility() != 8) {
            boolean b2;
            if (this.isShowHideAnimationEnabled() || b) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b2) {
                this.mTopVisibilityView.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.abc_slide_out_top));
            }
            this.mTopVisibilityView.setVisibility(8);
            if (this.mSplitView != null && this.mSplitView.getVisibility() != 8) {
                if (b2) {
                    this.mSplitView.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.abc_slide_out_bottom));
                }
                this.mSplitView.setVisibility(8);
            }
        }
    }
    
    public void doShow(final boolean b) {
        this.mTopVisibilityView.clearAnimation();
        if (this.mTopVisibilityView.getVisibility() != 0) {
            boolean b2;
            if (this.isShowHideAnimationEnabled() || b) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b2) {
                this.mTopVisibilityView.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.abc_slide_in_top));
            }
            this.mTopVisibilityView.setVisibility(0);
            if (this.mSplitView != null && this.mSplitView.getVisibility() != 0) {
                if (b2) {
                    this.mSplitView.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.abc_slide_in_bottom));
                }
                this.mSplitView.setVisibility(0);
            }
        }
    }
    
    @Override
    public View getCustomView() {
        return this.mActionView.getCustomNavigationView();
    }
    
    @Override
    public int getDisplayOptions() {
        return this.mActionView.getDisplayOptions();
    }
    
    @Override
    public int getHeight() {
        return this.mContainerView.getHeight();
    }
    
    @Override
    public int getNavigationItemCount() {
        switch (this.mActionView.getNavigationMode()) {
            case 2: {
                return this.mTabs.size();
            }
            case 1: {
                final SpinnerAdapter dropdownAdapter = this.mActionView.getDropdownAdapter();
                if (dropdownAdapter != null) {
                    return dropdownAdapter.getCount();
                }
                break;
            }
        }
        return 0;
    }
    
    @Override
    public int getNavigationMode() {
        return this.mActionView.getNavigationMode();
    }
    
    @Override
    public int getSelectedNavigationIndex() {
        switch (this.mActionView.getNavigationMode()) {
            case 2: {
                if (this.mSelectedTab != null) {
                    return this.mSelectedTab.getPosition();
                }
                break;
            }
            case 1: {
                return this.mActionView.getDropdownSelectedPosition();
            }
        }
        return -1;
    }
    
    @Override
    public Tab getSelectedTab() {
        return this.mSelectedTab;
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.mActionView.getSubtitle();
    }
    
    @Override
    public Tab getTabAt(final int n) {
        return this.mTabs.get(n);
    }
    
    @Override
    public int getTabCount() {
        return this.mTabs.size();
    }
    
    @Override
    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            final TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
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
    public CharSequence getTitle() {
        return this.mActionView.getTitle();
    }
    
    public boolean hasNonEmbeddedTabs() {
        return !this.mHasEmbeddedTabs && this.getNavigationMode() == 2;
    }
    
    @Override
    public void hide() {
        if (!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            this.updateVisibility(false);
        }
    }
    
    void hideForActionMode() {
        if (this.mShowingForMode) {
            this.updateVisibility(this.mShowingForMode = false);
        }
    }
    
    boolean isShowHideAnimationEnabled() {
        return this.mShowHideAnimationEnabled;
    }
    
    @Override
    public boolean isShowing() {
        return this.mNowShowing;
    }
    
    @Override
    public Tab newTab() {
        return new TabImpl();
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }
    
    @Override
    public void removeAllTabs() {
        this.cleanupTabs();
    }
    
    @Override
    public void removeOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }
    
    @Override
    public void removeTab(final Tab tab) {
        this.removeTabAt(tab.getPosition());
    }
    
    @Override
    public void removeTabAt(final int n) {
        if (this.mTabScrollView != null) {
            int n2;
            if (this.mSelectedTab != null) {
                n2 = this.mSelectedTab.getPosition();
            }
            else {
                n2 = this.mSavedTabPosition;
            }
            this.mTabScrollView.removeTabAt(n);
            final TabImpl tabImpl = this.mTabs.remove(n);
            if (tabImpl != null) {
                tabImpl.setPosition(-1);
            }
            for (int size = this.mTabs.size(), i = n; i < size; ++i) {
                this.mTabs.get(i).setPosition(i);
            }
            if (n2 == n) {
                Tab tab;
                if (this.mTabs.isEmpty()) {
                    tab = null;
                }
                else {
                    tab = this.mTabs.get(Math.max(0, n - 1));
                }
                this.selectTab(tab);
            }
        }
    }
    
    @Override
    public void selectTab(final Tab tab) {
        int n = -1;
        if (this.getNavigationMode() != 2) {
            if (tab != null) {
                n = tab.getPosition();
            }
            this.mSavedTabPosition = n;
        }
        else {
            final FragmentTransaction disallowAddToBackStack = this.mActivity.getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
            if (this.mSelectedTab == tab) {
                if (this.mSelectedTab != null) {
                    this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, disallowAddToBackStack);
                    this.mTabScrollView.animateToTab(tab.getPosition());
                }
            }
            else {
                final ScrollingTabContainerView mTabScrollView = this.mTabScrollView;
                if (tab != null) {
                    n = tab.getPosition();
                }
                mTabScrollView.setTabSelected(n);
                if (this.mSelectedTab != null) {
                    this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, disallowAddToBackStack);
                }
                this.mSelectedTab = (TabImpl)tab;
                if (this.mSelectedTab != null) {
                    this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, disallowAddToBackStack);
                }
            }
            if (!disallowAddToBackStack.isEmpty()) {
                disallowAddToBackStack.commit();
            }
        }
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable primaryBackground) {
        this.mContainerView.setPrimaryBackground(primaryBackground);
    }
    
    @Override
    public void setCustomView(final int n) {
        this.setCustomView(LayoutInflater.from(this.getThemedContext()).inflate(n, (ViewGroup)this.mActionView, false));
    }
    
    @Override
    public void setCustomView(final View customNavigationView) {
        this.mActionView.setCustomNavigationView(customNavigationView);
    }
    
    @Override
    public void setCustomView(final View customNavigationView, final LayoutParams layoutParams) {
        customNavigationView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mActionView.setCustomNavigationView(customNavigationView);
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
    
    @Override
    public void setDisplayOptions(final int displayOptions) {
        if ((displayOptions & 0x4) != 0x0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mActionView.setDisplayOptions(displayOptions);
    }
    
    @Override
    public void setDisplayOptions(final int n, final int n2) {
        final int displayOptions = this.mActionView.getDisplayOptions();
        if ((n2 & 0x4) != 0x0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mActionView.setDisplayOptions((n & n2) | (~n2 & displayOptions));
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
    public void setHomeButtonEnabled(final boolean homeButtonEnabled) {
        this.mActionView.setHomeButtonEnabled(homeButtonEnabled);
    }
    
    @Override
    public void setIcon(final int icon) {
        this.mActionView.setIcon(icon);
    }
    
    @Override
    public void setIcon(final Drawable icon) {
        this.mActionView.setIcon(icon);
    }
    
    @Override
    public void setListNavigationCallbacks(final SpinnerAdapter dropdownAdapter, final OnNavigationListener callback) {
        this.mActionView.setDropdownAdapter(dropdownAdapter);
        this.mActionView.setCallback(callback);
    }
    
    @Override
    public void setLogo(final int logo) {
        this.mActionView.setLogo(logo);
    }
    
    @Override
    public void setLogo(final Drawable logo) {
        this.mActionView.setLogo(logo);
    }
    
    @Override
    public void setNavigationMode(final int navigationMode) {
        final boolean b = false;
        switch (this.mActionView.getNavigationMode()) {
            case 2: {
                this.mSavedTabPosition = this.getSelectedNavigationIndex();
                this.selectTab(null);
                this.mTabScrollView.setVisibility(8);
                break;
            }
        }
        this.mActionView.setNavigationMode(navigationMode);
        switch (navigationMode) {
            case 2: {
                this.ensureTabsExist();
                this.mTabScrollView.setVisibility(0);
                if (this.mSavedTabPosition != -1) {
                    this.setSelectedNavigationItem(this.mSavedTabPosition);
                    this.mSavedTabPosition = -1;
                    break;
                }
                break;
            }
        }
        final ActionBarView mActionView = this.mActionView;
        boolean collapsable = b;
        if (navigationMode == 2) {
            collapsable = b;
            if (!this.mHasEmbeddedTabs) {
                collapsable = true;
            }
        }
        mActionView.setCollapsable(collapsable);
    }
    
    @Override
    public void setSelectedNavigationItem(final int dropdownSelectedPosition) {
        switch (this.mActionView.getNavigationMode()) {
            default: {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            case 2: {
                this.selectTab(this.mTabs.get(dropdownSelectedPosition));
            }
            case 1: {
                this.mActionView.setDropdownSelectedPosition(dropdownSelectedPosition);
            }
        }
    }
    
    public void setShowHideAnimationEnabled(final boolean mShowHideAnimationEnabled) {
        if (!(this.mShowHideAnimationEnabled = mShowHideAnimationEnabled)) {
            this.mTopVisibilityView.clearAnimation();
            if (this.mSplitView != null) {
                this.mSplitView.clearAnimation();
            }
        }
    }
    
    @Override
    public void setSubtitle(final int n) {
        this.setSubtitle(this.mContext.getString(n));
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.mActionView.setSubtitle(subtitle);
    }
    
    @Override
    public void setTitle(final int n) {
        this.setTitle(this.mContext.getString(n));
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.mActionView.setTitle(title);
    }
    
    @Override
    public void show() {
        if (this.mHiddenByApp) {
            this.updateVisibility(this.mHiddenByApp = false);
        }
    }
    
    void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            this.updateVisibility(false);
        }
    }
    
    public ActionMode startActionMode(final ActionMode.Callback callback) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mContextView.killMode();
        final ActionModeImpl mActionMode = new ActionModeImpl(callback);
        if (mActionMode.dispatchOnCreate()) {
            mActionMode.invalidate();
            this.mContextView.initForMode(mActionMode);
            this.animateToMode(true);
            if (this.mSplitView != null && this.mContextDisplayMode == 1 && this.mSplitView.getVisibility() != 0) {
                this.mSplitView.setVisibility(0);
            }
            this.mContextView.sendAccessibilityEvent(32);
            return this.mActionMode = mActionMode;
        }
        return null;
    }
    
    class ActionModeImpl extends ActionMode implements MenuBuilder.Callback
    {
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private MenuBuilder mMenu;
        
        public ActionModeImpl(final ActionMode.Callback mCallback) {
            this.mCallback = mCallback;
            (this.mMenu = new MenuBuilder(ActionBarImplBase.this.getThemedContext()).setDefaultShowAsAction(1)).setCallback((MenuBuilder.Callback)this);
        }
        
        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, (Menu)this.mMenu);
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }
        
        @Override
        public void finish() {
            if (ActionBarImplBase.this.mActionMode != this) {
                return;
            }
            if (!checkShowingFlags(ActionBarImplBase.this.mHiddenByApp, ActionBarImplBase.this.mHiddenBySystem, false)) {
                ActionBarImplBase.this.mDeferredDestroyActionMode = this;
                ActionBarImplBase.this.mDeferredModeDestroyCallback = this.mCallback;
            }
            else {
                this.mCallback.onDestroyActionMode(this);
            }
            this.mCallback = null;
            ActionBarImplBase.this.animateToMode(false);
            ActionBarImplBase.this.mContextView.closeMode();
            ActionBarImplBase.this.mActionView.sendAccessibilityEvent(32);
            ActionBarImplBase.this.mActionMode = null;
        }
        
        @Override
        public View getCustomView() {
            if (this.mCustomView != null) {
                return this.mCustomView.get();
            }
            return null;
        }
        
        @Override
        public Menu getMenu() {
            return (Menu)this.mMenu;
        }
        
        @Override
        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(ActionBarImplBase.this.getThemedContext());
        }
        
        @Override
        public CharSequence getSubtitle() {
            return ActionBarImplBase.this.mContextView.getSubtitle();
        }
        
        @Override
        public CharSequence getTitle() {
            return ActionBarImplBase.this.mContextView.getTitle();
        }
        
        @Override
        public void invalidate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(this, (Menu)this.mMenu);
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }
        
        @Override
        public boolean isTitleOptional() {
            return ActionBarImplBase.this.mContextView.isTitleOptional();
        }
        
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        }
        
        public void onCloseSubMenu(final SubMenuBuilder subMenuBuilder) {
        }
        
        @Override
        public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            return this.mCallback != null && this.mCallback.onActionItemClicked(this, menuItem);
        }
        
        @Override
        public void onMenuModeChange(final MenuBuilder menuBuilder) {
            if (this.mCallback == null) {
                return;
            }
            this.invalidate();
            ActionBarImplBase.this.mContextView.showOverflowMenu();
        }
        
        public void onMenuModeChange(final Menu menu) {
            if (this.mCallback == null) {
                return;
            }
            this.invalidate();
            ActionBarImplBase.this.mContextView.showOverflowMenu();
        }
        
        public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
            boolean b = true;
            if (this.mCallback == null) {
                b = false;
            }
            else if (!subMenuBuilder.hasVisibleItems()) {
                return true;
            }
            return b;
        }
        
        @Override
        public void setCustomView(final View customView) {
            ActionBarImplBase.this.mContextView.setCustomView(customView);
            this.mCustomView = new WeakReference<View>(customView);
        }
        
        @Override
        public void setSubtitle(final int n) {
            this.setSubtitle(ActionBarImplBase.this.mContext.getResources().getString(n));
        }
        
        @Override
        public void setSubtitle(final CharSequence subtitle) {
            ActionBarImplBase.this.mContextView.setSubtitle(subtitle);
        }
        
        @Override
        public void setTitle(final int n) {
            this.setTitle(ActionBarImplBase.this.mContext.getResources().getString(n));
        }
        
        @Override
        public void setTitle(final CharSequence title) {
            ActionBarImplBase.this.mContextView.setTitle(title);
        }
        
        @Override
        public void setTitleOptionalHint(final boolean b) {
            super.setTitleOptionalHint(b);
            ActionBarImplBase.this.mContextView.setTitleOptional(b);
        }
    }
    
    public class TabImpl extends Tab
    {
        private TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        
        public TabImpl() {
            this.mPosition = -1;
        }
        
        public TabListener getCallback() {
            return this.mCallback;
        }
        
        @Override
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }
        
        @Override
        public View getCustomView() {
            return this.mCustomView;
        }
        
        @Override
        public Drawable getIcon() {
            return this.mIcon;
        }
        
        @Override
        public int getPosition() {
            return this.mPosition;
        }
        
        @Override
        public Object getTag() {
            return this.mTag;
        }
        
        @Override
        public CharSequence getText() {
            return this.mText;
        }
        
        @Override
        public void select() {
            ActionBarImplBase.this.selectTab(this);
        }
        
        @Override
        public Tab setContentDescription(final int n) {
            return this.setContentDescription(ActionBarImplBase.this.mContext.getResources().getText(n));
        }
        
        @Override
        public Tab setContentDescription(final CharSequence mContentDesc) {
            this.mContentDesc = mContentDesc;
            if (this.mPosition >= 0) {
                ActionBarImplBase.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
        
        @Override
        public Tab setCustomView(final int n) {
            return this.setCustomView(LayoutInflater.from(ActionBarImplBase.this.getThemedContext()).inflate(n, (ViewGroup)null));
        }
        
        @Override
        public Tab setCustomView(final View mCustomView) {
            this.mCustomView = mCustomView;
            if (this.mPosition >= 0) {
                ActionBarImplBase.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
        
        @Override
        public Tab setIcon(final int n) {
            return this.setIcon(ActionBarImplBase.this.mContext.getResources().getDrawable(n));
        }
        
        @Override
        public Tab setIcon(final Drawable mIcon) {
            this.mIcon = mIcon;
            if (this.mPosition >= 0) {
                ActionBarImplBase.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
        
        public void setPosition(final int mPosition) {
            this.mPosition = mPosition;
        }
        
        @Override
        public Tab setTabListener(final TabListener mCallback) {
            this.mCallback = mCallback;
            return this;
        }
        
        @Override
        public Tab setTag(final Object mTag) {
            this.mTag = mTag;
            return this;
        }
        
        @Override
        public Tab setText(final int n) {
            return this.setText(ActionBarImplBase.this.mContext.getResources().getText(n));
        }
        
        @Override
        public Tab setText(final CharSequence mText) {
            this.mText = mText;
            if (this.mPosition >= 0) {
                ActionBarImplBase.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
    }
}
