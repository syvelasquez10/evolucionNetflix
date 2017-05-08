// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.view.menu.SubMenuBuilder;
import android.os.Build$VERSION;
import android.util.SparseArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.content.Context;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.design.R$layout;
import android.support.v7.view.menu.MenuView;
import android.view.ViewGroup;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.View;
import android.view.View$OnClickListener;
import android.support.v7.view.menu.MenuBuilder;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.widget.LinearLayout;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.MenuPresenter;

public class NavigationMenuPresenter implements MenuPresenter
{
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuPresenter$NavigationMenuAdapter mAdapter;
    private MenuPresenter$Callback mCallback;
    LinearLayout mHeaderLayout;
    ColorStateList mIconTintList;
    private int mId;
    Drawable mItemBackground;
    LayoutInflater mLayoutInflater;
    MenuBuilder mMenu;
    private NavigationMenuView mMenuView;
    final View$OnClickListener mOnClickListener;
    int mPaddingSeparator;
    private int mPaddingTopDefault;
    int mTextAppearance;
    boolean mTextAppearanceSet;
    ColorStateList mTextColor;
    
    public NavigationMenuPresenter() {
        this.mOnClickListener = (View$OnClickListener)new NavigationMenuPresenter$1(this);
    }
    
    public void addHeaderView(final View view) {
        this.mHeaderLayout.addView(view);
        this.mMenuView.setPadding(0, 0, 0, this.mMenuView.getPaddingBottom());
    }
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    public void dispatchApplyWindowInsets(final WindowInsetsCompat windowInsetsCompat) {
        final int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        if (this.mPaddingTopDefault != systemWindowInsetTop) {
            this.mPaddingTopDefault = systemWindowInsetTop;
            if (this.mHeaderLayout.getChildCount() == 0) {
                this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
            }
        }
        ViewCompat.dispatchApplyWindowInsets((View)this.mHeaderLayout, windowInsetsCompat);
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    public int getHeaderCount() {
        return this.mHeaderLayout.getChildCount();
    }
    
    public View getHeaderView(final int n) {
        return this.mHeaderLayout.getChildAt(n);
    }
    
    @Override
    public int getId() {
        return this.mId;
    }
    
    public Drawable getItemBackground() {
        return this.mItemBackground;
    }
    
    public ColorStateList getItemTextColor() {
        return this.mTextColor;
    }
    
    public ColorStateList getItemTintList() {
        return this.mIconTintList;
    }
    
    public MenuView getMenuView(final ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (NavigationMenuView)this.mLayoutInflater.inflate(R$layout.design_navigation_menu, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new NavigationMenuPresenter$NavigationMenuAdapter(this);
            }
            this.mHeaderLayout = (LinearLayout)this.mLayoutInflater.inflate(R$layout.design_navigation_item_header, (ViewGroup)this.mMenuView, false);
            this.mMenuView.setAdapter(this.mAdapter);
        }
        return this.mMenuView;
    }
    
    public View inflateHeaderView(final int n) {
        final View inflate = this.mLayoutInflater.inflate(n, (ViewGroup)this.mHeaderLayout, false);
        this.addHeaderView(inflate);
        return inflate;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder mMenu) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mMenu = mMenu;
        this.mPaddingSeparator = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menuBuilder, b);
        }
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            final Bundle bundle = (Bundle)parcelable;
            final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.mMenuView.restoreHierarchyState(sparseParcelableArray);
            }
            final Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.mAdapter.restoreInstanceState(bundle2);
            }
            final SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.mHeaderLayout.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        if (Build$VERSION.SDK_INT >= 11) {
            final Bundle bundle = new Bundle();
            if (this.mMenuView != null) {
                final SparseArray sparseArray = new SparseArray();
                this.mMenuView.saveHierarchyState(sparseArray);
                bundle.putSparseParcelableArray("android:menu:list", sparseArray);
            }
            if (this.mAdapter != null) {
                bundle.putBundle("android:menu:adapter", this.mAdapter.createInstanceState());
            }
            if (this.mHeaderLayout != null) {
                final SparseArray sparseArray2 = new SparseArray();
                this.mHeaderLayout.saveHierarchyState(sparseArray2);
                bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
            }
            return (Parcelable)bundle;
        }
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        return false;
    }
    
    public void removeHeaderView(final View view) {
        this.mHeaderLayout.removeView(view);
        if (this.mHeaderLayout.getChildCount() == 0) {
            this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
        }
    }
    
    @Override
    public void setCallback(final MenuPresenter$Callback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setCheckedItem(final MenuItemImpl checkedItem) {
        this.mAdapter.setCheckedItem(checkedItem);
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public void setItemBackground(final Drawable mItemBackground) {
        this.mItemBackground = mItemBackground;
        this.updateMenuView(false);
    }
    
    public void setItemIconTintList(final ColorStateList mIconTintList) {
        this.mIconTintList = mIconTintList;
        this.updateMenuView(false);
    }
    
    public void setItemTextAppearance(final int mTextAppearance) {
        this.mTextAppearance = mTextAppearance;
        this.mTextAppearanceSet = true;
        this.updateMenuView(false);
    }
    
    public void setItemTextColor(final ColorStateList mTextColor) {
        this.mTextColor = mTextColor;
        this.updateMenuView(false);
    }
    
    public void setUpdateSuspended(final boolean updateSuspended) {
        if (this.mAdapter != null) {
            this.mAdapter.setUpdateSuspended(updateSuspended);
        }
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        if (this.mAdapter != null) {
            this.mAdapter.update();
        }
    }
}
