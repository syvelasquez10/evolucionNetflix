// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.transition.ActionBarTransition;
import android.support.v7.internal.view.menu.ad;
import android.support.v7.appcompat.R$integer;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.internal.view.ActionBarPolicy;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.view.View$MeasureSpec;
import android.support.v7.internal.view.menu.c;
import android.support.v7.internal.view.menu.k;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.m;
import android.support.v7.internal.view.menu.aa;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.i;
import android.support.v7.appcompat.R$layout;
import android.content.Context;
import android.view.View;
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.internal.view.menu.d;

public class ActionMenuPresenter extends d implements ActionProvider$SubUiVisibilityListener
{
    private final SparseBooleanArray mActionButtonGroups;
    private ActionMenuPresenter$ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    private View mOverflowButton;
    private ActionMenuPresenter$OverflowPopup mOverflowPopup;
    private ActionMenuPresenter$ActionMenuPopupCallback mPopupCallback;
    final ActionMenuPresenter$PopupPresenterCallback mPopupPresenterCallback;
    private ActionMenuPresenter$OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;
    
    public ActionMenuPresenter(final Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new ActionMenuPresenter$PopupPresenterCallback(this, null);
    }
    
    private View findViewForItem(final MenuItem menuItem) {
        final ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        if (viewGroup != null) {
            for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = viewGroup.getChildAt(i);
                if (child instanceof aa) {
                    final View view = child;
                    if (((aa)child).a() == menuItem) {
                        return view;
                    }
                }
            }
            return null;
        }
        return null;
    }
    
    @Override
    public void bindItemView(final m m, final aa aa) {
        aa.a(m, 0);
        final ActionMenuView actionMenuView = (ActionMenuView)this.mMenuView;
        final ActionMenuItemView actionMenuItemView = (ActionMenuItemView)aa;
        actionMenuItemView.a(actionMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPresenter$ActionMenuPopupCallback(this, null);
        }
        actionMenuItemView.a(this.mPopupCallback);
    }
    
    public boolean dismissPopupMenus() {
        return this.hideOverflowMenu() | this.hideSubMenus();
    }
    
    public boolean filterLeftoverView(final ViewGroup viewGroup, final int n) {
        return viewGroup.getChildAt(n) != this.mOverflowButton && super.filterLeftoverView(viewGroup, n);
    }
    
    @Override
    public boolean flagActionItems() {
        final ArrayList<m> i = this.mMenu.i();
        final int size = i.size();
        int mMaxItems = this.mMaxItems;
        final int mActionItemWidthLimit = this.mActionItemWidthLimit;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        int n = 0;
        int n2 = 0;
        boolean b = false;
        for (int j = 0; j < size; ++j) {
            final m m = i.get(j);
            if (m.j()) {
                ++n;
            }
            else if (m.i()) {
                ++n2;
            }
            else {
                b = true;
            }
            if (this.mExpandedActionViewsExclusive && m.isActionViewExpanded()) {
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
        int k = 0;
        final boolean b2 = false;
        int n7 = n5;
        int n8 = n4;
        int n9 = mActionItemWidthLimit;
        int n10 = b2 ? 1 : 0;
        while (k < size) {
            final m l = i.get(k);
            int n11;
            int n13;
            int n14;
            if (l.j()) {
                final View itemView = this.getItemView(l, this.mScrapActionButtonView, viewGroup);
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
                final int groupId = l.getGroupId();
                if (groupId != 0) {
                    mActionButtonGroups.put(groupId, true);
                }
                l.d(true);
                final int n12 = n9 - measuredWidth;
                n13 = n8;
                n14 = n12;
            }
            else if (l.i()) {
                final int groupId2 = l.getGroupId();
                final boolean value = mActionButtonGroups.get(groupId2);
                boolean b3 = (n8 > 0 || value) && n9 > 0 && (!this.mStrictWidthLimit || n7 > 0);
                int n19;
                int n20;
                if (b3) {
                    final View itemView2 = this.getItemView(l, this.mScrapActionButtonView, viewGroup);
                    if (this.mScrapActionButtonView == null) {
                        this.mScrapActionButtonView = itemView2;
                    }
                    if (this.mStrictWidthLimit) {
                        final int measureChildForCells = ActionMenuView.measureChildForCells(itemView2, n6, n7, measureSpec, 0);
                        if (measureChildForCells == 0) {
                            b3 = false;
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
                        b3 &= (n15 >= 0);
                        final int n17 = n16;
                        final int n18 = n7;
                        n9 = n15;
                        n19 = n17;
                        n20 = n18;
                    }
                    else {
                        b3 &= (n15 + n16 > 0);
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
                if (b3 && groupId2 != 0) {
                    mActionButtonGroups.put(groupId2, true);
                }
                else if (value) {
                    mActionButtonGroups.put(groupId2, false);
                    int n23;
                    for (int n22 = 0; n22 < k; ++n22, n8 = n23) {
                        final m m2 = i.get(n22);
                        n23 = n8;
                        if (m2.getGroupId() == groupId2) {
                            n23 = n8;
                            if (m2.h()) {
                                n23 = n8 + 1;
                            }
                            m2.d(false);
                        }
                    }
                }
                int n24 = n8;
                if (b3) {
                    n24 = n8 - 1;
                }
                l.d(b3);
                n14 = n9;
                final int n25 = n24;
                n11 = n20;
                n10 = n19;
                n13 = n25;
            }
            else {
                l.d(false);
                final int n26 = n9;
                final int n27 = n8;
                n14 = n26;
                n11 = n7;
                n13 = n27;
            }
            final int n28 = k + 1;
            final int n29 = n13;
            n7 = n11;
            n9 = n14;
            n8 = n29;
            k = n28;
        }
        return true;
    }
    
    @Override
    public View getItemView(final m m, final View view, final ViewGroup viewGroup) {
        View view2 = m.getActionView();
        if (view2 == null || m.m()) {
            view2 = super.getItemView(m, view, viewGroup);
        }
        int visibility;
        if (m.isActionViewExpanded()) {
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
    public z getMenuView(final ViewGroup viewGroup) {
        final z menuView = super.getMenuView(viewGroup);
        ((ActionMenuView)menuView).setPresenter(this);
        return menuView;
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
    public void initForMenu(final Context context, final i i) {
        super.initForMenu(context, i);
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
                this.mOverflowButton = (View)new ActionMenuPresenter$OverflowMenuButton(this, this.mSystemContext);
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
    public void onCloseMenu(final i i, final boolean b) {
        this.dismissPopupMenus();
        super.onCloseMenu(i, b);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = this.mContext.getResources().getInteger(R$integer.abc_max_action_buttons);
        }
        if (this.mMenu != null) {
            this.mMenu.b(true);
        }
    }
    
    @Override
    public boolean onSubMenuSelected(final ad ad) {
        if (!ad.hasVisibleItems()) {
            return false;
        }
        ad ad2;
        for (ad2 = ad; ad2.s() != this.mMenu; ad2 = (ad)ad2.s()) {}
        View anchorView;
        if ((anchorView = this.findViewForItem(ad2.getItem())) == null) {
            if (this.mOverflowButton == null) {
                return false;
            }
            anchorView = this.mOverflowButton;
        }
        this.mOpenSubMenuId = ad.getItem().getItemId();
        (this.mActionButtonPopup = new ActionMenuPresenter$ActionButtonSubmenu(this, this.mContext, ad)).setAnchorView(anchorView);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(ad);
        return true;
    }
    
    public void setExpandedActionViewsExclusive(final boolean mExpandedActionViewsExclusive) {
        this.mExpandedActionViewsExclusive = mExpandedActionViewsExclusive;
    }
    
    public void setItemLimit(final int mMaxItems) {
        this.mMaxItems = mMaxItems;
        this.mMaxItemsSet = true;
    }
    
    public void setMenuView(final ActionMenuView mMenuView) {
        ((ActionMenuView)(this.mMenuView = mMenuView)).initialize(this.mMenu);
    }
    
    public void setReserveOverflow(final boolean mReserveOverflow) {
        this.mReserveOverflow = mReserveOverflow;
        this.mReserveOverflowSet = true;
    }
    
    public void setWidthLimit(final int mWidthLimit, final boolean mStrictWidthLimit) {
        this.mWidthLimit = mWidthLimit;
        this.mStrictWidthLimit = mStrictWidthLimit;
        this.mWidthLimitSet = true;
    }
    
    @Override
    public boolean shouldIncludeItem(final int n, final m m) {
        return m.h();
    }
    
    public boolean showOverflowMenu() {
        if (this.mReserveOverflow && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.l().isEmpty()) {
            this.mPostedOpenRunnable = new ActionMenuPresenter$OpenOverflowRunnable(this, new ActionMenuPresenter$OverflowPopup(this, this.mContext, this.mMenu, this.mOverflowButton, true));
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
            final ArrayList<m> k = this.mMenu.k();
            for (int size = k.size(), i = 0; i < size; ++i) {
                final ActionProvider l = k.get(i).l();
                if (l != null) {
                    l.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList<m> j;
        if (this.mMenu != null) {
            j = this.mMenu.l();
        }
        else {
            j = null;
        }
        int n = b3 ? 1 : 0;
        if (this.mReserveOverflow) {
            n = (b3 ? 1 : 0);
            if (j != null) {
                final int size2 = j.size();
                if (size2 == 1) {
                    if (!j.get(0).isActionViewExpanded()) {
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
                this.mOverflowButton = (View)new ActionMenuPresenter$OverflowMenuButton(this, this.mSystemContext);
            }
            final ViewGroup viewGroup2 = (ViewGroup)this.mOverflowButton.getParent();
            if (viewGroup2 != this.mMenuView) {
                if (viewGroup2 != null) {
                    viewGroup2.removeView(this.mOverflowButton);
                }
                final ActionMenuView actionMenuView = (ActionMenuView)this.mMenuView;
                actionMenuView.addView(this.mOverflowButton, (ViewGroup$LayoutParams)actionMenuView.generateOverflowButtonLayoutParams());
            }
        }
        else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
        }
        ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}
