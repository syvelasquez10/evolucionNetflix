// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.transition.ActionBarTransition;
import android.support.v7.view.menu.SubMenuBuilder;
import android.os.Parcelable;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.view.ActionBarPolicy;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.view.View$MeasureSpec;
import android.support.v7.view.menu.ActionMenuItemView$PopupCallback;
import android.support.v7.view.menu.MenuBuilder$ItemInvoker;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView$ItemView;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.appcompat.R$layout;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.view.menu.BaseMenuPresenter;

class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider$SubUiVisibilityListener
{
    private final SparseBooleanArray mActionButtonGroups;
    ActionMenuPresenter$ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    ActionMenuPresenter$OverflowMenuButton mOverflowButton;
    ActionMenuPresenter$OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPresenter$ActionMenuPopupCallback mPopupCallback;
    final ActionMenuPresenter$PopupPresenterCallback mPopupPresenterCallback;
    ActionMenuPresenter$OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;
    
    public ActionMenuPresenter(final Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new ActionMenuPresenter$PopupPresenterCallback(this);
    }
    
    private View findViewForItem(final MenuItem menuItem) {
        final ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        if (viewGroup != null) {
            for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = viewGroup.getChildAt(i);
                if (child instanceof MenuView$ItemView) {
                    final View view = child;
                    if (((MenuView$ItemView)child).getItemData() == menuItem) {
                        return view;
                    }
                }
            }
            return null;
        }
        return null;
    }
    
    @Override
    public void bindItemView(final MenuItemImpl menuItemImpl, final MenuView$ItemView menuView$ItemView) {
        menuView$ItemView.initialize(menuItemImpl, 0);
        final ActionMenuView itemInvoker = (ActionMenuView)this.mMenuView;
        final ActionMenuItemView actionMenuItemView = (ActionMenuItemView)menuView$ItemView;
        actionMenuItemView.setItemInvoker(itemInvoker);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPresenter$ActionMenuPopupCallback(this);
        }
        actionMenuItemView.setPopupCallback(this.mPopupCallback);
    }
    
    public boolean dismissPopupMenus() {
        return this.hideOverflowMenu() | this.hideSubMenus();
    }
    
    public boolean filterLeftoverView(final ViewGroup viewGroup, final int n) {
        return viewGroup.getChildAt(n) != this.mOverflowButton && super.filterLeftoverView(viewGroup, n);
    }
    
    @Override
    public boolean flagActionItems() {
        ArrayList<MenuItemImpl> visibleItems;
        int size;
        if (this.mMenu != null) {
            visibleItems = this.mMenu.getVisibleItems();
            size = visibleItems.size();
        }
        else {
            size = 0;
            visibleItems = null;
        }
        int mMaxItems = this.mMaxItems;
        final int mActionItemWidthLimit = this.mActionItemWidthLimit;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        int n = 0;
        int n2 = 0;
        boolean b = false;
        for (int i = 0; i < size; ++i) {
            final MenuItemImpl menuItemImpl = visibleItems.get(i);
            if (menuItemImpl.requiresActionButton()) {
                ++n;
            }
            else if (menuItemImpl.requestsActionButton()) {
                ++n2;
            }
            else {
                b = true;
            }
            if (this.mExpandedActionViewsExclusive && menuItemImpl.isActionViewExpanded()) {
                mMaxItems = 0;
            }
        }
        int n3 = mMaxItems;
        if (this.mReserveOverflow && (b || n + n2 > (n3 = mMaxItems))) {
            n3 = mMaxItems - 1;
        }
        final int n4 = n3 - n;
        final SparseBooleanArray mActionButtonGroups = this.mActionButtonGroups;
        mActionButtonGroups.clear();
        int n5 = 0;
        int n6;
        if (this.mStrictWidthLimit) {
            n5 = mActionItemWidthLimit / this.mMinCellSize;
            n6 = mActionItemWidthLimit % this.mMinCellSize / n5 + this.mMinCellSize;
        }
        else {
            n6 = 0;
        }
        int j = 0;
        final boolean b2 = false;
        int n7 = n5;
        int n8 = n4;
        int n9 = mActionItemWidthLimit;
        int n10 = b2 ? 1 : 0;
        while (j < size) {
            final MenuItemImpl menuItemImpl2 = visibleItems.get(j);
            int n11;
            int n13;
            int n14;
            if (menuItemImpl2.requiresActionButton()) {
                final View itemView = this.getItemView(menuItemImpl2, this.mScrapActionButtonView, viewGroup);
                if (this.mScrapActionButtonView == null) {
                    this.mScrapActionButtonView = itemView;
                }
                if (this.mStrictWidthLimit) {
                    n11 = n7 - ActionMenuView.measureChildForCells(itemView, n6, n7, measureSpec, 0);
                }
                else {
                    itemView.measure(measureSpec, measureSpec);
                    n11 = n7;
                }
                final int measuredWidth = itemView.getMeasuredWidth();
                if (n10 == 0) {
                    n10 = measuredWidth;
                }
                final int groupId = menuItemImpl2.getGroupId();
                if (groupId != 0) {
                    mActionButtonGroups.put(groupId, true);
                }
                menuItemImpl2.setIsActionButton(true);
                final int n12 = n9 - measuredWidth;
                n13 = n8;
                n14 = n12;
            }
            else if (menuItemImpl2.requestsActionButton()) {
                final int groupId2 = menuItemImpl2.getGroupId();
                final boolean value = mActionButtonGroups.get(groupId2);
                boolean isActionButton = (n8 > 0 || value) && n9 > 0 && (!this.mStrictWidthLimit || n7 > 0);
                int n19;
                int n20;
                if (isActionButton) {
                    final View itemView2 = this.getItemView(menuItemImpl2, this.mScrapActionButtonView, viewGroup);
                    if (this.mScrapActionButtonView == null) {
                        this.mScrapActionButtonView = itemView2;
                    }
                    if (this.mStrictWidthLimit) {
                        final int measureChildForCells = ActionMenuView.measureChildForCells(itemView2, n6, n7, measureSpec, 0);
                        if (measureChildForCells == 0) {
                            isActionButton = false;
                        }
                        n7 -= measureChildForCells;
                    }
                    else {
                        itemView2.measure(measureSpec, measureSpec);
                    }
                    final int measuredWidth2 = itemView2.getMeasuredWidth();
                    final int n15 = n9 - measuredWidth2;
                    int n16 = n10;
                    if (n10 == 0) {
                        n16 = measuredWidth2;
                    }
                    if (this.mStrictWidthLimit) {
                        isActionButton &= (n15 >= 0);
                        final int n17 = n16;
                        final int n18 = n7;
                        n9 = n15;
                        n19 = n17;
                        n20 = n18;
                    }
                    else {
                        isActionButton &= (n15 + n16 > 0);
                        n20 = n7;
                        n19 = n16;
                        n9 = n15;
                    }
                }
                else {
                    final int n21 = n10;
                    n20 = n7;
                    n19 = n21;
                }
                if (isActionButton && groupId2 != 0) {
                    mActionButtonGroups.put(groupId2, true);
                }
                else if (value) {
                    mActionButtonGroups.put(groupId2, false);
                    int n22;
                    for (int k = 0; k < j; ++k, n8 = n22) {
                        final MenuItemImpl menuItemImpl3 = visibleItems.get(k);
                        n22 = n8;
                        if (menuItemImpl3.getGroupId() == groupId2) {
                            n22 = n8;
                            if (menuItemImpl3.isActionButton()) {
                                n22 = n8 + 1;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                    }
                }
                int n23 = n8;
                if (isActionButton) {
                    n23 = n8 - 1;
                }
                menuItemImpl2.setIsActionButton(isActionButton);
                n14 = n9;
                final int n24 = n23;
                n11 = n20;
                n10 = n19;
                n13 = n24;
            }
            else {
                menuItemImpl2.setIsActionButton(false);
                final int n25 = n9;
                final int n26 = n8;
                n14 = n25;
                n11 = n7;
                n13 = n26;
            }
            final int n27 = j + 1;
            final int n28 = n13;
            n7 = n11;
            n9 = n14;
            n8 = n28;
            j = n27;
        }
        return true;
    }
    
    @Override
    public View getItemView(final MenuItemImpl menuItemImpl, final View view, final ViewGroup viewGroup) {
        View view2 = menuItemImpl.getActionView();
        if (view2 == null || menuItemImpl.hasCollapsibleActionView()) {
            view2 = super.getItemView(menuItemImpl, view, viewGroup);
        }
        int visibility;
        if (menuItemImpl.isActionViewExpanded()) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        view2.setVisibility(visibility);
        final ActionMenuView actionMenuView = (ActionMenuView)viewGroup;
        final ViewGroup$LayoutParams layoutParams = view2.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            view2.setLayoutParams((ViewGroup$LayoutParams)actionMenuView.generateLayoutParams(layoutParams));
        }
        return view2;
    }
    
    @Override
    public MenuView getMenuView(final ViewGroup viewGroup) {
        final MenuView mMenuView = this.mMenuView;
        final MenuView menuView = super.getMenuView(viewGroup);
        if (mMenuView != menuView) {
            ((ActionMenuView)menuView).setPresenter(this);
        }
        return menuView;
    }
    
    public Drawable getOverflowIcon() {
        if (this.mOverflowButton != null) {
            return this.mOverflowButton.getDrawable();
        }
        if (this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }
        return null;
    }
    
    public boolean hideOverflowMenu() {
        if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
            ((View)this.mMenuView).removeCallbacks((Runnable)this.mPostedOpenRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        final ActionMenuPresenter$OverflowPopup mOverflowPopup = this.mOverflowPopup;
        if (mOverflowPopup != null) {
            mOverflowPopup.dismiss();
            return true;
        }
        return false;
    }
    
    public boolean hideSubMenus() {
        if (this.mActionButtonPopup != null) {
            this.mActionButtonPopup.dismiss();
            return true;
        }
        return false;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        final Resources resources = context.getResources();
        final ActionBarPolicy value = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = value.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = value.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = value.getMaxActionButtons();
        }
        int mWidthLimit = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new ActionMenuPresenter$OverflowMenuButton(this, this.mSystemContext);
                if (this.mPendingOverflowIconSet) {
                    this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(measureSpec, measureSpec);
            }
            mWidthLimit -= this.mOverflowButton.getMeasuredWidth();
        }
        else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = mWidthLimit;
        this.mMinCellSize = (int)(56.0f * resources.getDisplayMetrics().density);
        this.mScrapActionButtonView = null;
    }
    
    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || this.isOverflowMenuShowing();
    }
    
    public boolean isOverflowMenuShowing() {
        return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        this.dismissPopupMenus();
        super.onCloseMenu(menuBuilder, b);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }
        if (this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof ActionMenuPresenter$SavedState) {
            final ActionMenuPresenter$SavedState actionMenuPresenter$SavedState = (ActionMenuPresenter$SavedState)parcelable;
            if (actionMenuPresenter$SavedState.openSubMenuId > 0) {
                final MenuItem item = this.mMenu.findItem(actionMenuPresenter$SavedState.openSubMenuId);
                if (item != null) {
                    this.onSubMenuSelected((SubMenuBuilder)item.getSubMenu());
                }
            }
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        final ActionMenuPresenter$SavedState actionMenuPresenter$SavedState = new ActionMenuPresenter$SavedState();
        actionMenuPresenter$SavedState.openSubMenuId = this.mOpenSubMenuId;
        return (Parcelable)actionMenuPresenter$SavedState;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            SubMenuBuilder subMenuBuilder2;
            for (subMenuBuilder2 = subMenuBuilder; subMenuBuilder2.getParentMenu() != this.mMenu; subMenuBuilder2 = (SubMenuBuilder)subMenuBuilder2.getParentMenu()) {}
            final View viewForItem = this.findViewForItem(subMenuBuilder2.getItem());
            if (viewForItem != null) {
                this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
                while (true) {
                    for (int size = subMenuBuilder.size(), i = 0; i < size; ++i) {
                        final MenuItem item = subMenuBuilder.getItem(i);
                        if (item.isVisible() && item.getIcon() != null) {
                            final boolean forceShowIcon = true;
                            (this.mActionButtonPopup = new ActionMenuPresenter$ActionButtonSubmenu(this, this.mContext, subMenuBuilder, viewForItem)).setForceShowIcon(forceShowIcon);
                            this.mActionButtonPopup.show();
                            super.onSubMenuSelected(subMenuBuilder);
                            return true;
                        }
                    }
                    final boolean forceShowIcon = false;
                    continue;
                }
            }
        }
        return false;
    }
    
    @Override
    public void onSubUiVisibilityChanged(final boolean b) {
        if (b) {
            super.onSubMenuSelected(null);
        }
        else if (this.mMenu != null) {
            this.mMenu.close(false);
        }
    }
    
    public void setExpandedActionViewsExclusive(final boolean mExpandedActionViewsExclusive) {
        this.mExpandedActionViewsExclusive = mExpandedActionViewsExclusive;
    }
    
    public void setMenuView(final ActionMenuView mMenuView) {
        ((ActionMenuView)(this.mMenuView = mMenuView)).initialize(this.mMenu);
    }
    
    public void setOverflowIcon(final Drawable drawable) {
        if (this.mOverflowButton != null) {
            this.mOverflowButton.setImageDrawable(drawable);
            return;
        }
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = drawable;
    }
    
    public void setReserveOverflow(final boolean mReserveOverflow) {
        this.mReserveOverflow = mReserveOverflow;
        this.mReserveOverflowSet = true;
    }
    
    @Override
    public boolean shouldIncludeItem(final int n, final MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }
    
    public boolean showOverflowMenu() {
        if (this.mReserveOverflow && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
            this.mPostedOpenRunnable = new ActionMenuPresenter$OpenOverflowRunnable(this, new ActionMenuPresenter$OverflowPopup(this, this.mContext, this.mMenu, (View)this.mOverflowButton, true));
            ((View)this.mMenuView).post((Runnable)this.mPostedOpenRunnable);
            super.onSubMenuSelected(null);
            return true;
        }
        return false;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        final boolean b2 = true;
        final boolean b3 = false;
        final ViewGroup viewGroup = (ViewGroup)((View)this.mMenuView).getParent();
        if (viewGroup != null) {
            ActionBarTransition.beginDelayedTransition(viewGroup);
        }
        super.updateMenuView(b);
        ((View)this.mMenuView).requestLayout();
        if (this.mMenu != null) {
            final ArrayList<MenuItemImpl> actionItems = this.mMenu.getActionItems();
            for (int size = actionItems.size(), i = 0; i < size; ++i) {
                final ActionProvider supportActionProvider = actionItems.get(i).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList<MenuItemImpl> nonActionItems;
        if (this.mMenu != null) {
            nonActionItems = this.mMenu.getNonActionItems();
        }
        else {
            nonActionItems = null;
        }
        int n = b3 ? 1 : 0;
        if (this.mReserveOverflow) {
            n = (b3 ? 1 : 0);
            if (nonActionItems != null) {
                final int size2 = nonActionItems.size();
                if (size2 == 1) {
                    if (!nonActionItems.get(0).isActionViewExpanded()) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                }
                else {
                    n = ((size2 > 0 && b2) ? 1 : 0);
                }
            }
        }
        if (n != 0) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new ActionMenuPresenter$OverflowMenuButton(this, this.mSystemContext);
            }
            final ViewGroup viewGroup2 = (ViewGroup)this.mOverflowButton.getParent();
            if (viewGroup2 != this.mMenuView) {
                if (viewGroup2 != null) {
                    viewGroup2.removeView((View)this.mOverflowButton);
                }
                final ActionMenuView actionMenuView = (ActionMenuView)this.mMenuView;
                actionMenuView.addView((View)this.mOverflowButton, (ViewGroup$LayoutParams)actionMenuView.generateOverflowButtonLayoutParams());
            }
        }
        else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup)this.mMenuView).removeView((View)this.mOverflowButton);
        }
        ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}
