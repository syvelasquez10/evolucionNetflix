// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager$OnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import java.util.Iterator;
import android.view.View$MeasureSpec;
import android.widget.LinearLayout$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import java.util.ArrayList;
import android.content.res.ColorStateList;
import android.view.View$OnClickListener;
import android.widget.HorizontalScrollView;

public class TabLayout extends HorizontalScrollView
{
    private int mContentInsetStart;
    private ValueAnimatorCompat mIndicatorAnimator;
    private int mMode;
    private TabLayout$OnTabSelectedListener mOnTabSelectedListener;
    private final int mRequestedTabMaxWidth;
    private ValueAnimatorCompat mScrollAnimator;
    private TabLayout$Tab mSelectedTab;
    private final int mTabBackgroundResId;
    private View$OnClickListener mTabClickListener;
    private int mTabGravity;
    private int mTabMaxWidth;
    private final int mTabMinWidth;
    private int mTabPaddingBottom;
    private int mTabPaddingEnd;
    private int mTabPaddingStart;
    private int mTabPaddingTop;
    private final TabLayout$SlidingTabStrip mTabStrip;
    private int mTabTextAppearance;
    private ColorStateList mTabTextColors;
    private final ArrayList<TabLayout$Tab> mTabs;
    
    public TabLayout(final Context context) {
        this(context, null);
    }
    
    public TabLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TabLayout(final Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.mTabs = new ArrayList<TabLayout$Tab>();
        this.mTabMaxWidth = Integer.MAX_VALUE;
        this.setHorizontalScrollBarEnabled(false);
        this.setFillViewport(true);
        this.addView((View)(this.mTabStrip = new TabLayout$SlidingTabStrip(this, context)), -2, -1);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.TabLayout, n, R$style.Widget_Design_TabLayout);
        this.mTabStrip.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, 0));
        this.mTabStrip.setSelectedIndicatorColor(obtainStyledAttributes.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.TabLayout_tabTextAppearance, R$style.TextAppearance_Design_Tab);
        n = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.mTabPaddingBottom = n;
        this.mTabPaddingEnd = n;
        this.mTabPaddingTop = n;
        this.mTabPaddingStart = n;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, this.mTabPaddingStart);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, this.mTabPaddingBottom);
        this.mTabTextColors = this.loadTextColorFromTextAppearance(this.mTabTextAppearance);
        if (obtainStyledAttributes.hasValue(R$styleable.TabLayout_tabTextColor)) {
            this.mTabTextColors = obtainStyledAttributes.getColorStateList(R$styleable.TabLayout_tabTextColor);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.TabLayout_tabSelectedTextColor)) {
            n = obtainStyledAttributes.getColor(R$styleable.TabLayout_tabSelectedTextColor, 0);
            this.mTabTextColors = createColorStateList(this.mTabTextColors.getDefaultColor(), n);
        }
        this.mTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, 0);
        this.mRequestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, 0);
        this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(R$styleable.TabLayout_tabBackground, 0);
        this.mContentInsetStart = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
        this.mMode = obtainStyledAttributes.getInt(R$styleable.TabLayout_tabMode, 1);
        this.mTabGravity = obtainStyledAttributes.getInt(R$styleable.TabLayout_tabGravity, 0);
        obtainStyledAttributes.recycle();
        this.applyModeAndGravity();
    }
    
    private void addTabView(final TabLayout$Tab tabLayout$Tab, final int n, final boolean b) {
        final TabLayout$TabView tabView = this.createTabView(tabLayout$Tab);
        this.mTabStrip.addView((View)tabView, n, (ViewGroup$LayoutParams)this.createLayoutParamsForTabs());
        if (b) {
            tabView.setSelected(true);
        }
    }
    
    private void addTabView(final TabLayout$Tab tabLayout$Tab, final boolean b) {
        final TabLayout$TabView tabView = this.createTabView(tabLayout$Tab);
        this.mTabStrip.addView((View)tabView, (ViewGroup$LayoutParams)this.createLayoutParamsForTabs());
        if (b) {
            tabView.setSelected(true);
        }
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
                this.mScrollAnimator.setDuration(300);
                this.mScrollAnimator.setUpdateListener(new TabLayout$2(this));
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
        this.updateTabViewsLayoutParams();
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
    
    private TabLayout$TabView createTabView(final TabLayout$Tab tabLayout$Tab) {
        final TabLayout$TabView tabLayout$TabView = new TabLayout$TabView(this, this.getContext(), tabLayout$Tab);
        tabLayout$TabView.setFocusable(true);
        if (this.mTabClickListener == null) {
            this.mTabClickListener = (View$OnClickListener)new TabLayout$1(this);
        }
        tabLayout$TabView.setOnClickListener(this.mTabClickListener);
        return tabLayout$TabView;
    }
    
    private int dpToPx(final int n) {
        return Math.round(this.getResources().getDisplayMetrics().density * n);
    }
    
    private float getScrollPosition() {
        return this.mTabStrip.getIndicatorPosition();
    }
    
    private ColorStateList loadTextColorFromTextAppearance(final int n) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(n, R$styleable.TextAppearance);
        try {
            return obtainStyledAttributes.getColorStateList(R$styleable.TextAppearance_android_textColor);
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    private void setSelectedTabView(final int n) {
        final int childCount = this.mTabStrip.getChildCount();
        if (n < childCount && !this.mTabStrip.getChildAt(n).isSelected()) {
            for (int i = 0; i < childCount; ++i) {
                this.mTabStrip.getChildAt(i).setSelected(i == n);
            }
        }
    }
    
    private void updateAllTabs() {
        for (int i = 0; i < this.mTabStrip.getChildCount(); ++i) {
            this.updateTab(i);
        }
    }
    
    private void updateTab(final int n) {
        final TabLayout$TabView tabLayout$TabView = (TabLayout$TabView)this.mTabStrip.getChildAt(n);
        if (tabLayout$TabView != null) {
            tabLayout$TabView.update();
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
    
    private void updateTabViewsLayoutParams() {
        for (int i = 0; i < this.mTabStrip.getChildCount(); ++i) {
            final View child = this.mTabStrip.getChildAt(i);
            this.updateTabViewLayoutParams((LinearLayout$LayoutParams)child.getLayoutParams());
            child.requestLayout();
        }
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab) {
        this.addTab(tabLayout$Tab, this.mTabs.isEmpty());
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab, final int n, final boolean b) {
        if (tabLayout$Tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        this.addTabView(tabLayout$Tab, n, b);
        this.configureTab(tabLayout$Tab, n);
        if (b) {
            tabLayout$Tab.select();
        }
    }
    
    public void addTab(final TabLayout$Tab tabLayout$Tab, final boolean b) {
        if (tabLayout$Tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        this.addTabView(tabLayout$Tab, b);
        this.configureTab(tabLayout$Tab, this.mTabs.size());
        if (b) {
            tabLayout$Tab.select();
        }
    }
    
    public int getSelectedTabPosition() {
        if (this.mSelectedTab != null) {
            return this.mSelectedTab.getPosition();
        }
        return -1;
    }
    
    public TabLayout$Tab getTabAt(final int n) {
        return this.mTabs.get(n);
    }
    
    public int getTabCount() {
        return this.mTabs.size();
    }
    
    public int getTabGravity() {
        return this.mTabGravity;
    }
    
    public int getTabMode() {
        return this.mMode;
    }
    
    public ColorStateList getTabTextColors() {
        return this.mTabTextColors;
    }
    
    public TabLayout$Tab newTab() {
        return new TabLayout$Tab(this);
    }
    
    protected void onMeasure(final int n, int n2) {
        final int n3 = this.dpToPx(48) + this.getPaddingTop() + this.getPaddingBottom();
        switch (View$MeasureSpec.getMode(n2)) {
            case Integer.MIN_VALUE: {
                n2 = View$MeasureSpec.makeMeasureSpec(Math.min(n3, View$MeasureSpec.getSize(n2)), 1073741824);
                break;
            }
            case 0: {
                n2 = View$MeasureSpec.makeMeasureSpec(n3, 1073741824);
                break;
            }
        }
        super.onMeasure(n, n2);
        if (this.mMode == 1 && this.getChildCount() == 1) {
            final View child = this.getChildAt(0);
            final int measuredWidth = this.getMeasuredWidth();
            if (child.getMeasuredWidth() > measuredWidth) {
                child.measure(View$MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), getChildMeasureSpec(n2, this.getPaddingTop() + this.getPaddingBottom(), child.getLayoutParams().height));
            }
        }
        final int mRequestedTabMaxWidth = this.mRequestedTabMaxWidth;
        final int n4 = this.getMeasuredWidth() - this.dpToPx(56);
        int mTabMaxWidth;
        if (mRequestedTabMaxWidth == 0 || (mTabMaxWidth = mRequestedTabMaxWidth) > n4) {
            mTabMaxWidth = n4;
        }
        if (this.mTabMaxWidth != mTabMaxWidth) {
            this.mTabMaxWidth = mTabMaxWidth;
            super.onMeasure(n, n2);
        }
    }
    
    public void removeAllTabs() {
        this.mTabStrip.removeAllViews();
        final Iterator<TabLayout$Tab> iterator = this.mTabs.iterator();
        while (iterator.hasNext()) {
            iterator.next().setPosition(-1);
            iterator.remove();
        }
        this.mSelectedTab = null;
    }
    
    void selectTab(final TabLayout$Tab tabLayout$Tab) {
        this.selectTab(tabLayout$Tab, true);
    }
    
    void selectTab(final TabLayout$Tab mSelectedTab, final boolean b) {
        if (this.mSelectedTab == mSelectedTab) {
            if (this.mSelectedTab != null) {
                if (this.mOnTabSelectedListener != null) {
                    this.mOnTabSelectedListener.onTabReselected(this.mSelectedTab);
                }
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
            this.setSelectedTabView(position);
            if (b) {
                if ((this.mSelectedTab == null || this.mSelectedTab.getPosition() == -1) && position != -1) {
                    this.setScrollPosition(position, 0.0f, true);
                }
                else {
                    this.animateToTab(position);
                }
            }
            if (this.mSelectedTab != null && this.mOnTabSelectedListener != null) {
                this.mOnTabSelectedListener.onTabUnselected(this.mSelectedTab);
            }
            this.mSelectedTab = mSelectedTab;
            if (this.mSelectedTab != null && this.mOnTabSelectedListener != null) {
                this.mOnTabSelectedListener.onTabSelected(this.mSelectedTab);
            }
        }
    }
    
    public void setOnTabSelectedListener(final TabLayout$OnTabSelectedListener mOnTabSelectedListener) {
        this.mOnTabSelectedListener = mOnTabSelectedListener;
    }
    
    public void setScrollPosition(final int n, final float n2, final boolean b) {
        if ((this.mIndicatorAnimator == null || !this.mIndicatorAnimator.isRunning()) && n >= 0 && n < this.mTabStrip.getChildCount()) {
            this.mTabStrip.setIndicatorPositionFromTabPosition(n, n2);
            this.scrollTo(this.calculateScrollXForTab(n, n2), 0);
            if (b) {
                this.setSelectedTabView(Math.round(n + n2));
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
    
    public void setTabTextColors(final ColorStateList mTabTextColors) {
        if (this.mTabTextColors != mTabTextColors) {
            this.mTabTextColors = mTabTextColors;
            this.updateAllTabs();
        }
    }
    
    public void setTabsFromPagerAdapter(final PagerAdapter pagerAdapter) {
        this.removeAllTabs();
        for (int i = 0; i < pagerAdapter.getCount(); ++i) {
            this.addTab(this.newTab().setText(pagerAdapter.getPageTitle(i)));
        }
    }
    
    public void setupWithViewPager(final ViewPager viewPager) {
        final PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }
        this.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout$TabLayoutOnPageChangeListener(this));
        this.setOnTabSelectedListener(new TabLayout$ViewPagerOnTabSelectedListener(viewPager));
        if (adapter.getCount() > 0) {
            final int currentItem = viewPager.getCurrentItem();
            if (this.getSelectedTabPosition() != currentItem) {
                this.selectTab(this.getTabAt(currentItem));
            }
        }
    }
}
