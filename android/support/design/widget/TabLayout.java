// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.content.res.Resources;
import java.util.Iterator;
import android.view.View$MeasureSpec;
import android.view.ViewParent;
import android.support.v4.view.ViewPager$OnAdapterChangeListener;
import android.support.v4.view.ViewPager$OnPageChangeListener;
import android.widget.LinearLayout$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.content.res.TypedArray;
import android.support.design.R$dimen;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.support.v4.util.Pools$SimplePool;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.util.Pools$SynchronizedPool;
import android.support.v4.view.ViewPager;
import android.content.res.ColorStateList;
import java.util.ArrayList;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.util.Pools$Pool;
import android.support.v4.view.ViewPager$DecorView;
import android.widget.HorizontalScrollView;

@ViewPager$DecorView
public class TabLayout extends HorizontalScrollView
{
    private static final int ANIMATION_DURATION = 300;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    private static final int INVALID_WIDTH = -1;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final Pools$Pool<TabLayout$Tab> sTabPool;
    private TabLayout$AdapterChangeListener mAdapterChangeListener;
    private int mContentInsetStart;
    private TabLayout$OnTabSelectedListener mCurrentVpSelectedListener;
    int mMode;
    private TabLayout$TabLayoutOnPageChangeListener mPageChangeListener;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private final int mRequestedTabMaxWidth;
    private final int mRequestedTabMinWidth;
    private ValueAnimatorCompat mScrollAnimator;
    private final int mScrollableTabMinWidth;
    private TabLayout$OnTabSelectedListener mSelectedListener;
    private final ArrayList<TabLayout$OnTabSelectedListener> mSelectedListeners;
    private TabLayout$Tab mSelectedTab;
    private boolean mSetupViewPagerImplicitly;
    final int mTabBackgroundResId;
    int mTabGravity;
    int mTabMaxWidth;
    int mTabPaddingBottom;
    int mTabPaddingEnd;
    int mTabPaddingStart;
    int mTabPaddingTop;
    private final TabLayout$SlidingTabStrip mTabStrip;
    int mTabTextAppearance;
    ColorStateList mTabTextColors;
    float mTabTextMultiLineSize;
    float mTabTextSize;
    private final Pools$Pool<TabLayout$TabView> mTabViewPool;
    private final ArrayList<TabLayout$Tab> mTabs;
    ViewPager mViewPager;
    
    static {
        sTabPool = new Pools$SynchronizedPool<TabLayout$Tab>(16);
    }
    
    public TabLayout(final Context context) {
        this(context, null);
    }
    
    public TabLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TabLayout(Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.mTabs = new ArrayList<TabLayout$Tab>();
        this.mTabMaxWidth = Integer.MAX_VALUE;
        this.mSelectedListeners = new ArrayList<TabLayout$OnTabSelectedListener>();
        this.mTabViewPool = new Pools$SimplePool<TabLayout$TabView>(12);
        ThemeUtils.checkAppCompatTheme(context);
        this.setHorizontalScrollBarEnabled(false);
        super.addView((View)(this.mTabStrip = new TabLayout$SlidingTabStrip(this, context)), 0, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -1));
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.TabLayout, n, R$style.Widget_Design_TabLayout);
        this.mTabStrip.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, 0));
        this.mTabStrip.setSelectedIndicatorColor(obtainStyledAttributes.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        n = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.mTabPaddingBottom = n;
        this.mTabPaddingEnd = n;
        this.mTabPaddingTop = n;
        this.mTabPaddingStart = n;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, this.mTabPaddingStart);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, this.mTabPaddingBottom);
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.TabLayout_tabTextAppearance, R$style.TextAppearance_Design_Tab);
        context = (Context)context.obtainStyledAttributes(this.mTabTextAppearance, android.support.v7.appcompat.R$styleable.TextAppearance);
        try {
            this.mTabTextSize = ((TypedArray)context).getDimensionPixelSize(android.support.v7.appcompat.R$styleable.TextAppearance_android_textSize, 0);
            this.mTabTextColors = ((TypedArray)context).getColorStateList(android.support.v7.appcompat.R$styleable.TextAppearance_android_textColor);
            ((TypedArray)context).recycle();
            if (obtainStyledAttributes.hasValue(R$styleable.TabLayout_tabTextColor)) {
                this.mTabTextColors = obtainStyledAttributes.getColorStateList(R$styleable.TabLayout_tabTextColor);
            }
            if (obtainStyledAttributes.hasValue(R$styleable.TabLayout_tabSelectedTextColor)) {
                n = obtainStyledAttributes.getColor(R$styleable.TabLayout_tabSelectedTextColor, 0);
                this.mTabTextColors = createColorStateList(this.mTabTextColors.getDefaultColor(), n);
            }
            this.mRequestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, -1);
            this.mRequestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, -1);
            this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(R$styleable.TabLayout_tabBackground, 0);
            this.mContentInsetStart = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
            this.mMode = obtainStyledAttributes.getInt(R$styleable.TabLayout_tabMode, 1);
            this.mTabGravity = obtainStyledAttributes.getInt(R$styleable.TabLayout_tabGravity, 0);
            obtainStyledAttributes.recycle();
            context = (Context)this.getResources();
            this.mTabTextMultiLineSize = ((Resources)context).getDimensionPixelSize(R$dimen.design_tab_text_size_2line);
            this.mScrollableTabMinWidth = ((Resources)context).getDimensionPixelSize(R$dimen.design_tab_scrollable_min_width);
            this.applyModeAndGravity();
        }
        finally {
            ((TypedArray)context).recycle();
        }
    }
    
    private void addTabFromItemView(final TabItem tabItem) {
        final TabLayout$Tab tab = this.newTab();
        if (tabItem.mText != null) {
            tab.setText(tabItem.mText);
        }
        if (tabItem.mIcon != null) {
            tab.setIcon(tabItem.mIcon);
        }
        if (tabItem.mCustomLayout != 0) {
            tab.setCustomView(tabItem.mCustomLayout);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            tab.setContentDescription(tabItem.getContentDescription());
        }
        this.addTab(tab);
    }
    
    private void addTabView(final TabLayout$Tab tabLayout$Tab) {
        this.mTabStrip.addView((View)tabLayout$Tab.mView, tabLayout$Tab.getPosition(), (ViewGroup$LayoutParams)this.createLayoutParamsForTabs());
    }
    
    private void addViewInternal(final View view) {
        if (view instanceof TabItem) {
            this.addTabFromItemView((TabItem)view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }
    
    private void animateToTab(final int n) {
        if (n == -1) {
            return;
        }
        if (this.getWindowToken() == null || !ViewCompat.isLaidOut((View)this) || this.mTabStrip.childrenNeedLayout()) {
            this.setScrollPosition(n, 0.0f, true);
            return;
        }
        final int scrollX = this.getScrollX();
        final int calculateScrollXForTab = this.calculateScrollXForTab(n, 0.0f);
        if (scrollX != calculateScrollXForTab) {
            if (this.mScrollAnimator == null) {
                (this.mScrollAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.mScrollAnimator.setDuration(300L);
                this.mScrollAnimator.addUpdateListener(new TabLayout$1(this));
            }
            this.mScrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
            this.mScrollAnimator.start();
        }
        this.mTabStrip.animateIndicatorToPosition(n, 300);
    }
    
    private void applyModeAndGravity() {
        int max;
        if (this.mMode == 0) {
            max = Math.max(0, this.mContentInsetStart - this.mTabPaddingStart);
        }
        else {
            max = 0;
        }
        ViewCompat.setPaddingRelative((View)this.mTabStrip, max, 0, 0, 0);
        switch (this.mMode) {
            case 1: {
                this.mTabStrip.setGravity(1);
                break;
            }
            case 0: {
                this.mTabStrip.setGravity(8388611);
                break;
            }
        }
        this.updateTabViews(true);
    }
    
    private int calculateScrollXForTab(int width, final float n) {
        int n2 = 0;
        final int n3 = 0;
        if (this.mMode == 0) {
            final View child = this.mTabStrip.getChildAt(width);
            View child2;
            if (width + 1 < this.mTabStrip.getChildCount()) {
                child2 = this.mTabStrip.getChildAt(width + 1);
            }
            else {
                child2 = null;
            }
            if (child != null) {
                width = child.getWidth();
            }
            else {
                width = 0;
            }
            int width2 = n3;
            if (child2 != null) {
                width2 = child2.getWidth();
            }
            n2 = (int)((width2 + width) * n * 0.5f) + child.getLeft() + child.getWidth() / 2 - this.getWidth() / 2;
        }
        return n2;
    }
    
    private void configureTab(final TabLayout$Tab tabLayout$Tab, int i) {
        tabLayout$Tab.setPosition(i);
        this.mTabs.add(i, tabLayout$Tab);
        int size;
        for (size = this.mTabs.size(), ++i; i < size; ++i) {
            this.mTabs.get(i).setPosition(i);
        }
    }
    
    private static ColorStateList createColorStateList(final int n, final int n2) {
        return new ColorStateList(new int[][] { TabLayout.SELECTED_STATE_SET, TabLayout.EMPTY_STATE_SET }, new int[] { n2, n });
    }
    
    private LinearLayout$LayoutParams createLayoutParamsForTabs() {
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -1);
        this.updateTabViewLayoutParams(linearLayout$LayoutParams);
        return linearLayout$LayoutParams;
    }
    
    private TabLayout$TabView createTabView(final TabLayout$Tab tab) {
        TabLayout$TabView tabLayout$TabView;
        if (this.mTabViewPool != null) {
            tabLayout$TabView = this.mTabViewPool.acquire();
        }
        else {
            tabLayout$TabView = null;
        }
        TabLayout$TabView tabLayout$TabView2 = tabLayout$TabView;
        if (tabLayout$TabView == null) {
            tabLayout$TabView2 = new TabLayout$TabView(this, this.getContext());
        }
        tabLayout$TabView2.setTab(tab);
        tabLayout$TabView2.setFocusable(true);
        tabLayout$TabView2.setMinimumWidth(this.getTabMinWidth());
        return tabLayout$TabView2;
    }
    
    private void dispatchTabReselected(final TabLayout$Tab tabLayout$Tab) {
        for (int i = this.mSelectedListeners.size() - 1; i >= 0; --i) {
            this.mSelectedListeners.get(i).onTabReselected(tabLayout$Tab);
        }
    }
    
    private void dispatchTabSelected(final TabLayout$Tab tabLayout$Tab) {
        for (int i = this.mSelectedListeners.size() - 1; i >= 0; --i) {
            this.mSelectedListeners.get(i).onTabSelected(tabLayout$Tab);
        }
    }
    
    private void dispatchTabUnselected(final TabLayout$Tab tabLayout$Tab) {
        for (int i = this.mSelectedListeners.size() - 1; i >= 0; --i) {
            this.mSelectedListeners.get(i).onTabUnselected(tabLayout$Tab);
        }
    }
    
    private int getDefaultHeight() {
        final int size = this.mTabs.size();
        int i = 0;
        while (true) {
            while (i < size) {
                final TabLayout$Tab tabLayout$Tab = this.mTabs.get(i);
                if (tabLayout$Tab != null && tabLayout$Tab.getIcon() != null && !TextUtils.isEmpty(tabLayout$Tab.getText())) {
                    final int n = 1;
                    if (n != 0) {
                        return 72;
                    }
                    return 48;
                }
                else {
                    ++i;
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    private float getScrollPosition() {
        return this.mTabStrip.getIndicatorPosition();
    }
    
    private int getTabMinWidth() {
        if (this.mRequestedTabMinWidth != -1) {
            return this.mRequestedTabMinWidth;
        }
        if (this.mMode == 0) {
            return this.mScrollableTabMinWidth;
        }
        return 0;
    }
    
    private int getTabScrollRange() {
        return Math.max(0, this.mTabStrip.getWidth() - this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
    }
    
    private void removeTabViewAt(final int n) {
        final TabLayout$TabView tabLayout$TabView = (TabLayout$TabView)this.mTabStrip.getChildAt(n);
        this.mTabStrip.removeViewAt(n);
        if (tabLayout$TabView != null) {
            tabLayout$TabView.reset();
            this.mTabViewPool.release(tabLayout$TabView);
        }
        this.requestLayout();
    }
    
    private void setSelectedTabView(final int n) {
        final int childCount = this.mTabStrip.getChildCount();
        if (n < childCount) {
            for (int i = 0; i < childCount; ++i) {
                this.mTabStrip.getChildAt(i).setSelected(i == n);
            }
        }
    }
    
    private void setupWithViewPager(final ViewPager mViewPager, final boolean autoRefresh, final boolean mSetupViewPagerImplicitly) {
        if (this.mViewPager != null) {
            if (this.mPageChangeListener != null) {
                this.mViewPager.removeOnPageChangeListener(this.mPageChangeListener);
            }
            if (this.mAdapterChangeListener != null) {
                this.mViewPager.removeOnAdapterChangeListener(this.mAdapterChangeListener);
            }
        }
        if (this.mCurrentVpSelectedListener != null) {
            this.removeOnTabSelectedListener(this.mCurrentVpSelectedListener);
            this.mCurrentVpSelectedListener = null;
        }
        if (mViewPager != null) {
            this.mViewPager = mViewPager;
            if (this.mPageChangeListener == null) {
                this.mPageChangeListener = new TabLayout$TabLayoutOnPageChangeListener(this);
            }
            this.mPageChangeListener.reset();
            mViewPager.addOnPageChangeListener(this.mPageChangeListener);
            this.addOnTabSelectedListener(this.mCurrentVpSelectedListener = new TabLayout$ViewPagerOnTabSelectedListener(mViewPager));
            final PagerAdapter adapter = mViewPager.getAdapter();
            if (adapter != null) {
                this.setPagerAdapter(adapter, autoRefresh);
            }
            if (this.mAdapterChangeListener == null) {
                this.mAdapterChangeListener = new TabLayout$AdapterChangeListener(this);
            }
            this.mAdapterChangeListener.setAutoRefresh(autoRefresh);
            mViewPager.addOnAdapterChangeListener(this.mAdapterChangeListener);
            this.setScrollPosition(mViewPager.getCurrentItem(), 0.0f, true);
        }
        else {
            this.mViewPager = null;
            this.setPagerAdapter(null, false);
        }
        this.mSetupViewPagerImplicitly = mSetupViewPagerImplicitly;
    }
    
    private void updateAllTabs() {
        for (int size = this.mTabs.size(), i = 0; i < size; ++i) {
            this.mTabs.get(i).updateView();
        }
    }
    
    private void updateTabViewLayoutParams(final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        if (this.mMode == 1 && this.mTabGravity == 0) {
            linearLayout$LayoutParams.width = 0;
            linearLayout$LayoutParams.weight = 1.0f;
            return;
        }
        linearLayout$LayoutParams.width = -2;
        linearLayout$LayoutParams.weight = 0.0f;
    }
    
    public void addOnTabSelectedListener(final TabLayout$OnTabSelectedListener tabLayout$OnTabSelectedListener) {
        if (!this.mSelectedListeners.contains(tabLayout$OnTabSelectedListener)) {
            this.mSelectedListeners.add(tabLayout$OnTabSelectedListener);
        }
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab) {
        this.addTab(tabLayout$Tab, this.mTabs.isEmpty());
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab, final int n) {
        this.addTab(tabLayout$Tab, n, this.mTabs.isEmpty());
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab, final int n, final boolean b) {
        if (tabLayout$Tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        this.configureTab(tabLayout$Tab, n);
        this.addTabView(tabLayout$Tab);
        if (b) {
            tabLayout$Tab.select();
        }
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab, final boolean b) {
        this.addTab(tabLayout$Tab, this.mTabs.size(), b);
    }
    
    public void addView(final View view) {
        this.addViewInternal(view);
    }
    
    public void addView(final View view, final int n) {
        this.addViewInternal(view);
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.addViewInternal(view);
    }
    
    public void addView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.addViewInternal(view);
    }
    
    public void clearOnTabSelectedListeners() {
        this.mSelectedListeners.clear();
    }
    
    int dpToPx(final int n) {
        return Math.round(this.getResources().getDisplayMetrics().density * n);
    }
    
    public FrameLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return this.generateDefaultLayoutParams();
    }
    
    public int getSelectedTabPosition() {
        if (this.mSelectedTab != null) {
            return this.mSelectedTab.getPosition();
        }
        return -1;
    }
    
    public TabLayout$Tab getTabAt(final int n) {
        if (n < 0 || n >= this.getTabCount()) {
            return null;
        }
        return this.mTabs.get(n);
    }
    
    public int getTabCount() {
        return this.mTabs.size();
    }
    
    public int getTabGravity() {
        return this.mTabGravity;
    }
    
    int getTabMaxWidth() {
        return this.mTabMaxWidth;
    }
    
    public int getTabMode() {
        return this.mMode;
    }
    
    public ColorStateList getTabTextColors() {
        return this.mTabTextColors;
    }
    
    public TabLayout$Tab newTab() {
        TabLayout$Tab tabLayout$Tab;
        if ((tabLayout$Tab = TabLayout.sTabPool.acquire()) == null) {
            tabLayout$Tab = new TabLayout$Tab();
        }
        tabLayout$Tab.mParent = this;
        tabLayout$Tab.mView = this.createTabView(tabLayout$Tab);
        return tabLayout$Tab;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mViewPager == null) {
            final ViewParent parent = this.getParent();
            if (parent instanceof ViewPager) {
                this.setupWithViewPager((ViewPager)parent, true, true);
            }
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mSetupViewPagerImplicitly) {
            this.setupWithViewPager(null);
            this.mSetupViewPagerImplicitly = false;
        }
    }
    
    protected void onMeasure(int childMeasureSpec, int n) {
        final int n2 = 1;
        final int n3 = this.dpToPx(this.getDefaultHeight()) + this.getPaddingTop() + this.getPaddingBottom();
        switch (View$MeasureSpec.getMode(n)) {
            case Integer.MIN_VALUE: {
                n = View$MeasureSpec.makeMeasureSpec(Math.min(n3, View$MeasureSpec.getSize(n)), 1073741824);
                break;
            }
            case 0: {
                n = View$MeasureSpec.makeMeasureSpec(n3, 1073741824);
                break;
            }
        }
        final int size = View$MeasureSpec.getSize(childMeasureSpec);
        if (View$MeasureSpec.getMode(childMeasureSpec) != 0) {
            int mRequestedTabMaxWidth;
            if (this.mRequestedTabMaxWidth > 0) {
                mRequestedTabMaxWidth = this.mRequestedTabMaxWidth;
            }
            else {
                mRequestedTabMaxWidth = size - this.dpToPx(56);
            }
            this.mTabMaxWidth = mRequestedTabMaxWidth;
        }
        super.onMeasure(childMeasureSpec, n);
        if (this.getChildCount() == 1) {
            final View child = this.getChildAt(0);
            switch (this.mMode) {
                default: {
                    childMeasureSpec = 0;
                    break;
                }
                case 0: {
                    if (child.getMeasuredWidth() < this.getMeasuredWidth()) {
                        childMeasureSpec = 1;
                        break;
                    }
                    childMeasureSpec = 0;
                    break;
                }
                case 1: {
                    if (child.getMeasuredWidth() != this.getMeasuredWidth()) {
                        childMeasureSpec = n2;
                    }
                    else {
                        childMeasureSpec = 0;
                    }
                    break;
                }
            }
            if (childMeasureSpec != 0) {
                childMeasureSpec = getChildMeasureSpec(n, this.getPaddingTop() + this.getPaddingBottom(), child.getLayoutParams().height);
                child.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), childMeasureSpec);
            }
        }
    }
    
    void populateFromPagerAdapter() {
        this.removeAllTabs();
        if (this.mPagerAdapter != null) {
            final int count = this.mPagerAdapter.getCount();
            for (int i = 0; i < count; ++i) {
                this.addTab(this.newTab().setText(this.mPagerAdapter.getPageTitle(i)), false);
            }
            if (this.mViewPager != null && count > 0) {
                final int currentItem = this.mViewPager.getCurrentItem();
                if (currentItem != this.getSelectedTabPosition() && currentItem < this.getTabCount()) {
                    this.selectTab(this.getTabAt(currentItem));
                }
            }
        }
    }
    
    public void removeAllTabs() {
        for (int i = this.mTabStrip.getChildCount() - 1; i >= 0; --i) {
            this.removeTabViewAt(i);
        }
        final Iterator<TabLayout$Tab> iterator = this.mTabs.iterator();
        while (iterator.hasNext()) {
            final TabLayout$Tab tabLayout$Tab = iterator.next();
            iterator.remove();
            tabLayout$Tab.reset();
            TabLayout.sTabPool.release(tabLayout$Tab);
        }
        this.mSelectedTab = null;
    }
    
    public void removeOnTabSelectedListener(final TabLayout$OnTabSelectedListener tabLayout$OnTabSelectedListener) {
        this.mSelectedListeners.remove(tabLayout$OnTabSelectedListener);
    }
    
    public void removeTab(final TabLayout$Tab tabLayout$Tab) {
        if (tabLayout$Tab.mParent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        this.removeTabAt(tabLayout$Tab.getPosition());
    }
    
    public void removeTabAt(final int n) {
        int position;
        if (this.mSelectedTab != null) {
            position = this.mSelectedTab.getPosition();
        }
        else {
            position = 0;
        }
        this.removeTabViewAt(n);
        final TabLayout$Tab tabLayout$Tab = this.mTabs.remove(n);
        if (tabLayout$Tab != null) {
            tabLayout$Tab.reset();
            TabLayout.sTabPool.release(tabLayout$Tab);
        }
        for (int size = this.mTabs.size(), i = n; i < size; ++i) {
            this.mTabs.get(i).setPosition(i);
        }
        if (position == n) {
            TabLayout$Tab tabLayout$Tab2;
            if (this.mTabs.isEmpty()) {
                tabLayout$Tab2 = null;
            }
            else {
                tabLayout$Tab2 = this.mTabs.get(Math.max(0, n - 1));
            }
            this.selectTab(tabLayout$Tab2);
        }
    }
    
    void selectTab(final TabLayout$Tab tabLayout$Tab) {
        this.selectTab(tabLayout$Tab, true);
    }
    
    void selectTab(final TabLayout$Tab mSelectedTab, final boolean b) {
        final TabLayout$Tab mSelectedTab2 = this.mSelectedTab;
        if (mSelectedTab2 == mSelectedTab) {
            if (mSelectedTab2 != null) {
                this.dispatchTabReselected(mSelectedTab);
                this.animateToTab(mSelectedTab.getPosition());
            }
        }
        else {
            int position;
            if (mSelectedTab != null) {
                position = mSelectedTab.getPosition();
            }
            else {
                position = -1;
            }
            if (b) {
                if ((mSelectedTab2 == null || mSelectedTab2.getPosition() == -1) && position != -1) {
                    this.setScrollPosition(position, 0.0f, true);
                }
                else {
                    this.animateToTab(position);
                }
                if (position != -1) {
                    this.setSelectedTabView(position);
                }
            }
            if (mSelectedTab2 != null) {
                this.dispatchTabUnselected(mSelectedTab2);
            }
            if ((this.mSelectedTab = mSelectedTab) != null) {
                this.dispatchTabSelected(mSelectedTab);
            }
        }
    }
    
    @Deprecated
    public void setOnTabSelectedListener(final TabLayout$OnTabSelectedListener mSelectedListener) {
        if (this.mSelectedListener != null) {
            this.removeOnTabSelectedListener(this.mSelectedListener);
        }
        if ((this.mSelectedListener = mSelectedListener) != null) {
            this.addOnTabSelectedListener(mSelectedListener);
        }
    }
    
    void setPagerAdapter(final PagerAdapter mPagerAdapter, final boolean b) {
        if (this.mPagerAdapter != null && this.mPagerAdapterObserver != null) {
            this.mPagerAdapter.unregisterDataSetObserver(this.mPagerAdapterObserver);
        }
        this.mPagerAdapter = mPagerAdapter;
        if (b && mPagerAdapter != null) {
            if (this.mPagerAdapterObserver == null) {
                this.mPagerAdapterObserver = new TabLayout$PagerAdapterObserver(this);
            }
            mPagerAdapter.registerDataSetObserver(this.mPagerAdapterObserver);
        }
        this.populateFromPagerAdapter();
    }
    
    public void setScrollPosition(final int n, final float n2, final boolean b) {
        this.setScrollPosition(n, n2, b, true);
    }
    
    void setScrollPosition(final int n, final float n2, final boolean b, final boolean b2) {
        final int round = Math.round(n + n2);
        if (round >= 0 && round < this.mTabStrip.getChildCount()) {
            if (b2) {
                this.mTabStrip.setIndicatorPositionFromTabPosition(n, n2);
            }
            if (this.mScrollAnimator != null && this.mScrollAnimator.isRunning()) {
                this.mScrollAnimator.cancel();
            }
            this.scrollTo(this.calculateScrollXForTab(n, n2), 0);
            if (b) {
                this.setSelectedTabView(round);
            }
        }
    }
    
    public void setSelectedTabIndicatorColor(final int selectedIndicatorColor) {
        this.mTabStrip.setSelectedIndicatorColor(selectedIndicatorColor);
    }
    
    public void setSelectedTabIndicatorHeight(final int selectedIndicatorHeight) {
        this.mTabStrip.setSelectedIndicatorHeight(selectedIndicatorHeight);
    }
    
    public void setTabGravity(final int mTabGravity) {
        if (this.mTabGravity != mTabGravity) {
            this.mTabGravity = mTabGravity;
            this.applyModeAndGravity();
        }
    }
    
    public void setTabMode(final int mMode) {
        if (mMode != this.mMode) {
            this.mMode = mMode;
            this.applyModeAndGravity();
        }
    }
    
    public void setTabTextColors(final int n, final int n2) {
        this.setTabTextColors(createColorStateList(n, n2));
    }
    
    public void setTabTextColors(final ColorStateList mTabTextColors) {
        if (this.mTabTextColors != mTabTextColors) {
            this.mTabTextColors = mTabTextColors;
            this.updateAllTabs();
        }
    }
    
    @Deprecated
    public void setTabsFromPagerAdapter(final PagerAdapter pagerAdapter) {
        this.setPagerAdapter(pagerAdapter, false);
    }
    
    public void setupWithViewPager(final ViewPager viewPager) {
        this.setupWithViewPager(viewPager, true);
    }
    
    public void setupWithViewPager(final ViewPager viewPager, final boolean b) {
        this.setupWithViewPager(viewPager, b, false);
    }
    
    public boolean shouldDelayChildPressedState() {
        return this.getTabScrollRange() > 0;
    }
    
    void updateTabViews(final boolean b) {
        for (int i = 0; i < this.mTabStrip.getChildCount(); ++i) {
            final View child = this.mTabStrip.getChildAt(i);
            child.setMinimumWidth(this.getTabMinWidth());
            this.updateTabViewLayoutParams((LinearLayout$LayoutParams)child.getLayoutParams());
            if (b) {
                child.requestLayout();
            }
        }
    }
}
