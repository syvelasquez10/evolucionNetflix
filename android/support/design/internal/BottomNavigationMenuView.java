// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.view.View$MeasureSpec;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v7.view.menu.MenuItemImpl;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.design.R$dimen;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.util.Pools$SynchronizedPool;
import android.view.View$OnClickListener;
import android.support.v7.view.menu.MenuBuilder;
import android.content.res.ColorStateList;
import android.support.v4.util.Pools$Pool;
import android.support.v7.view.menu.MenuView;
import android.view.ViewGroup;

public class BottomNavigationMenuView extends ViewGroup implements MenuView
{
    private static final Pools$Pool<BottomNavigationItemView> sItemPool;
    private int mActiveButton;
    private final int mActiveItemMaxWidth;
    private final BottomNavigationAnimationHelperBase mAnimationHelper;
    private BottomNavigationItemView[] mButtons;
    private final int mInactiveItemMaxWidth;
    private final int mInactiveItemMinWidth;
    private int mItemBackgroundRes;
    private final int mItemHeight;
    private ColorStateList mItemIconTint;
    private ColorStateList mItemTextColor;
    private MenuBuilder mMenu;
    private final View$OnClickListener mOnClickListener;
    private BottomNavigationPresenter mPresenter;
    private boolean mShiftingMode;
    private int[] mTempChildWidths;
    
    static {
        sItemPool = new Pools$SynchronizedPool<BottomNavigationItemView>(5);
    }
    
    public BottomNavigationMenuView(final Context context) {
        this(context, null);
    }
    
    public BottomNavigationMenuView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mShiftingMode = true;
        this.mActiveButton = 0;
        final Resources resources = this.getResources();
        this.mInactiveItemMaxWidth = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_max_width);
        this.mInactiveItemMinWidth = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_min_width);
        this.mActiveItemMaxWidth = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_max_width);
        this.mItemHeight = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_height);
        if (Build$VERSION.SDK_INT >= 14) {
            this.mAnimationHelper = new BottomNavigationAnimationHelperIcs();
        }
        else {
            this.mAnimationHelper = new BottomNavigationAnimationHelperBase();
        }
        this.mOnClickListener = (View$OnClickListener)new BottomNavigationMenuView$1(this);
        this.mTempChildWidths = new int[5];
    }
    
    private void activateNewButton(final int mActiveButton) {
        if (this.mActiveButton == mActiveButton) {
            return;
        }
        this.mAnimationHelper.beginDelayedTransition(this);
        this.mMenu.getItem(mActiveButton).setChecked(true);
        this.mActiveButton = mActiveButton;
    }
    
    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemView;
        if ((bottomNavigationItemView = BottomNavigationMenuView.sItemPool.acquire()) == null) {
            bottomNavigationItemView = new BottomNavigationItemView(this.getContext());
        }
        return bottomNavigationItemView;
    }
    
    public void buildMenuView() {
        if (this.mButtons != null) {
            final BottomNavigationItemView[] mButtons = this.mButtons;
            for (int length = mButtons.length, i = 0; i < length; ++i) {
                BottomNavigationMenuView.sItemPool.release(mButtons[i]);
            }
        }
        this.removeAllViews();
        if (this.mMenu.size() == 0) {
            this.mButtons = null;
            return;
        }
        this.mButtons = new BottomNavigationItemView[this.mMenu.size()];
        this.mShiftingMode = (this.mMenu.size() > 3);
        for (int j = 0; j < this.mMenu.size(); ++j) {
            this.mPresenter.setUpdateSuspended(true);
            this.mMenu.getItem(j).setCheckable(true);
            this.mPresenter.setUpdateSuspended(false);
            final BottomNavigationItemView newItem = this.getNewItem();
            (this.mButtons[j] = newItem).setIconTintList(this.mItemIconTint);
            newItem.setTextColor(this.mItemTextColor);
            newItem.setItemBackground(this.mItemBackgroundRes);
            newItem.setShiftingMode(this.mShiftingMode);
            newItem.initialize((MenuItemImpl)this.mMenu.getItem(j), 0);
            newItem.setItemPosition(j);
            newItem.setOnClickListener(this.mOnClickListener);
            this.addView((View)newItem);
        }
        this.mActiveButton = Math.min(this.mMenu.size() - 1, this.mActiveButton);
        this.mMenu.getItem(this.mActiveButton).setChecked(true);
    }
    
    public ColorStateList getIconTintList() {
        return this.mItemIconTint;
    }
    
    public int getItemBackgroundRes() {
        return this.mItemBackgroundRes;
    }
    
    public ColorStateList getItemTextColor() {
        return this.mItemTextColor;
    }
    
    public int getWindowAnimations() {
        return 0;
    }
    
    public void initialize(final MenuBuilder mMenu) {
        this.mMenu = mMenu;
    }
    
    protected void onLayout(final boolean b, int i, int n, int n2, int n3) {
        final int childCount = this.getChildCount();
        n2 -= i;
        n3 -= n;
        i = 0;
        n = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection((View)this) == 1) {
                    child.layout(n2 - n - child.getMeasuredWidth(), 0, n2 - n, n3);
                }
                else {
                    child.layout(n, 0, child.getMeasuredWidth() + n, n3);
                }
                n += child.getMeasuredWidth();
            }
            ++i;
        }
    }
    
    protected void onMeasure(int i, int j) {
        j = View$MeasureSpec.getSize(i);
        final int childCount = this.getChildCount();
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824);
        if (this.mShiftingMode) {
            i = childCount - 1;
            final int min = Math.min(j - this.mInactiveItemMinWidth * i, this.mActiveItemMaxWidth);
            final int min2 = Math.min((j - min) / i, this.mInactiveItemMaxWidth);
            i = j - min - i * min2;
            int[] mTempChildWidths;
            int n;
            int[] mTempChildWidths2;
            for (j = 0; j < childCount; ++j) {
                mTempChildWidths = this.mTempChildWidths;
                if (j == this.mActiveButton) {
                    n = min;
                }
                else {
                    n = min2;
                }
                mTempChildWidths[j] = n;
                if (i > 0) {
                    mTempChildWidths2 = this.mTempChildWidths;
                    ++mTempChildWidths2[j];
                    --i;
                }
            }
        }
        else {
            if (childCount == 0) {
                i = 1;
            }
            else {
                i = childCount;
            }
            final int min3 = Math.min(j / i, this.mActiveItemMaxWidth);
            j -= min3 * childCount;
            int n2;
            int[] mTempChildWidths3;
            for (i = 0; i < childCount; ++i, j = n2) {
                this.mTempChildWidths[i] = min3;
                if ((n2 = j) > 0) {
                    mTempChildWidths3 = this.mTempChildWidths;
                    ++mTempChildWidths3[i];
                    n2 = j - 1;
                }
            }
        }
        i = 0;
        j = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                child.measure(View$MeasureSpec.makeMeasureSpec(this.mTempChildWidths[i], 1073741824), measureSpec);
                child.getLayoutParams().width = child.getMeasuredWidth();
                j += child.getMeasuredWidth();
            }
            ++i;
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(j, View$MeasureSpec.makeMeasureSpec(j, 1073741824), 0), ViewCompat.resolveSizeAndState(this.mItemHeight, measureSpec, 0));
    }
    
    public void setIconTintList(final ColorStateList list) {
        this.mItemIconTint = list;
        if (this.mButtons != null) {
            final BottomNavigationItemView[] mButtons = this.mButtons;
            for (int length = mButtons.length, i = 0; i < length; ++i) {
                mButtons[i].setIconTintList(list);
            }
        }
    }
    
    public void setItemBackgroundRes(final int n) {
        this.mItemBackgroundRes = n;
        if (this.mButtons != null) {
            final BottomNavigationItemView[] mButtons = this.mButtons;
            for (int length = mButtons.length, i = 0; i < length; ++i) {
                mButtons[i].setItemBackground(n);
            }
        }
    }
    
    public void setItemTextColor(final ColorStateList list) {
        this.mItemTextColor = list;
        if (this.mButtons != null) {
            final BottomNavigationItemView[] mButtons = this.mButtons;
            for (int length = mButtons.length, i = 0; i < length; ++i) {
                mButtons[i].setTextColor(list);
            }
        }
    }
    
    public void setPresenter(final BottomNavigationPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }
    
    public void updateMenuView() {
        final int size = this.mMenu.size();
        if (size != this.mButtons.length) {
            this.buildMenuView();
        }
        else {
            for (int i = 0; i < size; ++i) {
                this.mPresenter.setUpdateSuspended(true);
                if (this.mMenu.getItem(i).isChecked()) {
                    this.mActiveButton = i;
                }
                this.mButtons[i].initialize((MenuItemImpl)this.mMenu.getItem(i), 0);
                this.mPresenter.setUpdateSuspended(false);
            }
        }
    }
}
