// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.view.View;
import java.util.Iterator;
import android.util.SparseArray;
import android.os.Bundle;
import android.view.SubMenu;
import java.util.ArrayList;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.RecyclerView$Adapter;

class NavigationMenuPresenter$NavigationMenuAdapter extends RecyclerView$Adapter<NavigationMenuPresenter$ViewHolder>
{
    private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
    private static final String STATE_CHECKED_ITEM = "android:menu:checked";
    private static final int VIEW_TYPE_HEADER = 3;
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_SEPARATOR = 2;
    private static final int VIEW_TYPE_SUBHEADER = 1;
    private MenuItemImpl mCheckedItem;
    private final ArrayList<NavigationMenuPresenter$NavigationMenuItem> mItems;
    private boolean mUpdateSuspended;
    final /* synthetic */ NavigationMenuPresenter this$0;
    
    NavigationMenuPresenter$NavigationMenuAdapter(final NavigationMenuPresenter this$0) {
        this.this$0 = this$0;
        this.mItems = new ArrayList<NavigationMenuPresenter$NavigationMenuItem>();
        this.prepareMenuItems();
    }
    
    private void appendTransparentIconIfMissing(int i, final int n) {
        while (i < n) {
            this.mItems.get(i).needsEmptyIcon = true;
            ++i;
        }
    }
    
    private void prepareMenuItems() {
        if (this.mUpdateSuspended) {
            return;
        }
        this.mUpdateSuspended = true;
        this.mItems.clear();
        this.mItems.add(new NavigationMenuPresenter$NavigationMenuHeaderItem());
        int n = -1;
        int n2 = 0;
        boolean needsEmptyIcon = false;
        int n6;
        int n7;
        int n8;
        for (int size = this.this$0.mMenu.getVisibleItems().size(), i = 0; i < size; ++i, n8 = n7, n2 = n6, n = n8) {
            final MenuItemImpl menuItemImpl = this.this$0.mMenu.getVisibleItems().get(i);
            if (menuItemImpl.isChecked()) {
                this.setCheckedItem(menuItemImpl);
            }
            if (menuItemImpl.isCheckable()) {
                menuItemImpl.setExclusiveCheckable(false);
            }
            if (menuItemImpl.hasSubMenu()) {
                final SubMenu subMenu = menuItemImpl.getSubMenu();
                if (subMenu.hasVisibleItems()) {
                    if (i != 0) {
                        this.mItems.add(new NavigationMenuPresenter$NavigationMenuSeparatorItem(this.this$0.mPaddingSeparator, 0));
                    }
                    this.mItems.add(new NavigationMenuPresenter$NavigationMenuTextItem(menuItemImpl));
                    int n3 = 0;
                    final int size2 = this.mItems.size();
                    int n4;
                    for (int size3 = subMenu.size(), j = 0; j < size3; ++j, n3 = n4) {
                        final MenuItemImpl menuItemImpl2 = (MenuItemImpl)subMenu.getItem(j);
                        n4 = n3;
                        if (menuItemImpl2.isVisible()) {
                            if ((n4 = n3) == 0) {
                                n4 = n3;
                                if (menuItemImpl2.getIcon() != null) {
                                    n4 = 1;
                                }
                            }
                            if (menuItemImpl2.isCheckable()) {
                                menuItemImpl2.setExclusiveCheckable(false);
                            }
                            if (menuItemImpl.isChecked()) {
                                this.setCheckedItem(menuItemImpl);
                            }
                            this.mItems.add(new NavigationMenuPresenter$NavigationMenuTextItem(menuItemImpl2));
                        }
                    }
                    if (n3 != 0) {
                        this.appendTransparentIconIfMissing(size2, this.mItems.size());
                    }
                }
                final int n5 = n;
                n6 = n2;
                n7 = n5;
            }
            else {
                final int groupId = menuItemImpl.getGroupId();
                if (groupId != n) {
                    final int size4 = this.mItems.size();
                    final boolean b = needsEmptyIcon = (menuItemImpl.getIcon() != null);
                    n2 = size4;
                    if (i != 0) {
                        n2 = size4 + 1;
                        this.mItems.add(new NavigationMenuPresenter$NavigationMenuSeparatorItem(this.this$0.mPaddingSeparator, this.this$0.mPaddingSeparator));
                        needsEmptyIcon = b;
                    }
                }
                else if (!needsEmptyIcon && menuItemImpl.getIcon() != null) {
                    needsEmptyIcon = true;
                    this.appendTransparentIconIfMissing(n2, this.mItems.size());
                }
                final NavigationMenuPresenter$NavigationMenuTextItem navigationMenuPresenter$NavigationMenuTextItem = new NavigationMenuPresenter$NavigationMenuTextItem(menuItemImpl);
                navigationMenuPresenter$NavigationMenuTextItem.needsEmptyIcon = needsEmptyIcon;
                this.mItems.add(navigationMenuPresenter$NavigationMenuTextItem);
                n6 = n2;
                n7 = groupId;
            }
        }
        this.mUpdateSuspended = false;
    }
    
    public Bundle createInstanceState() {
        final Bundle bundle = new Bundle();
        if (this.mCheckedItem != null) {
            bundle.putInt("android:menu:checked", this.mCheckedItem.getItemId());
        }
        final SparseArray sparseArray = new SparseArray();
        for (final NavigationMenuPresenter$NavigationMenuItem navigationMenuPresenter$NavigationMenuItem : this.mItems) {
            if (navigationMenuPresenter$NavigationMenuItem instanceof NavigationMenuPresenter$NavigationMenuTextItem) {
                final MenuItemImpl menuItem = ((NavigationMenuPresenter$NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem).getMenuItem();
                View actionView;
                if (menuItem != null) {
                    actionView = menuItem.getActionView();
                }
                else {
                    actionView = null;
                }
                if (actionView == null) {
                    continue;
                }
                final ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                actionView.saveHierarchyState((SparseArray)parcelableSparseArray);
                sparseArray.put(menuItem.getItemId(), (Object)parcelableSparseArray);
            }
        }
        bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
        return bundle;
    }
    
    @Override
    public int getItemCount() {
        return this.mItems.size();
    }
    
    @Override
    public long getItemId(final int n) {
        return n;
    }
    
    @Override
    public int getItemViewType(final int n) {
        final NavigationMenuPresenter$NavigationMenuItem navigationMenuPresenter$NavigationMenuItem = this.mItems.get(n);
        if (navigationMenuPresenter$NavigationMenuItem instanceof NavigationMenuPresenter$NavigationMenuSeparatorItem) {
            return 2;
        }
        if (navigationMenuPresenter$NavigationMenuItem instanceof NavigationMenuPresenter$NavigationMenuHeaderItem) {
            return 3;
        }
        if (!(navigationMenuPresenter$NavigationMenuItem instanceof NavigationMenuPresenter$NavigationMenuTextItem)) {
            throw new RuntimeException("Unknown item type.");
        }
        if (((NavigationMenuPresenter$NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem).getMenuItem().hasSubMenu()) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(final NavigationMenuPresenter$ViewHolder navigationMenuPresenter$ViewHolder, final int n) {
        switch (this.getItemViewType(n)) {
            default: {}
            case 0: {
                final NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)navigationMenuPresenter$ViewHolder.itemView;
                navigationMenuItemView.setIconTintList(this.this$0.mIconTintList);
                if (this.this$0.mTextAppearanceSet) {
                    navigationMenuItemView.setTextAppearance(navigationMenuItemView.getContext(), this.this$0.mTextAppearance);
                }
                if (this.this$0.mTextColor != null) {
                    navigationMenuItemView.setTextColor(this.this$0.mTextColor);
                }
                Drawable drawable;
                if (this.this$0.mItemBackground != null) {
                    drawable = this.this$0.mItemBackground.getConstantState().newDrawable();
                }
                else {
                    drawable = null;
                }
                navigationMenuItemView.setBackgroundDrawable(drawable);
                final NavigationMenuPresenter$NavigationMenuTextItem navigationMenuPresenter$NavigationMenuTextItem = this.mItems.get(n);
                navigationMenuItemView.setNeedsEmptyIcon(navigationMenuPresenter$NavigationMenuTextItem.needsEmptyIcon);
                navigationMenuItemView.initialize(navigationMenuPresenter$NavigationMenuTextItem.getMenuItem(), 0);
            }
            case 1: {
                ((TextView)navigationMenuPresenter$ViewHolder.itemView).setText(this.mItems.get(n).getMenuItem().getTitle());
            }
            case 2: {
                final NavigationMenuPresenter$NavigationMenuSeparatorItem navigationMenuPresenter$NavigationMenuSeparatorItem = this.mItems.get(n);
                navigationMenuPresenter$ViewHolder.itemView.setPadding(0, navigationMenuPresenter$NavigationMenuSeparatorItem.getPaddingTop(), 0, navigationMenuPresenter$NavigationMenuSeparatorItem.getPaddingBottom());
            }
        }
    }
    
    @Override
    public NavigationMenuPresenter$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return new NavigationMenuPresenter$NormalViewHolder(this.this$0.mLayoutInflater, viewGroup, this.this$0.mOnClickListener);
            }
            case 1: {
                return new NavigationMenuPresenter$SubheaderViewHolder(this.this$0.mLayoutInflater, viewGroup);
            }
            case 2: {
                return new NavigationMenuPresenter$SeparatorViewHolder(this.this$0.mLayoutInflater, viewGroup);
            }
            case 3: {
                return new NavigationMenuPresenter$HeaderViewHolder((View)this.this$0.mHeaderLayout);
            }
        }
    }
    
    @Override
    public void onViewRecycled(final NavigationMenuPresenter$ViewHolder navigationMenuPresenter$ViewHolder) {
        if (navigationMenuPresenter$ViewHolder instanceof NavigationMenuPresenter$NormalViewHolder) {
            ((NavigationMenuItemView)navigationMenuPresenter$ViewHolder.itemView).recycle();
        }
    }
    
    public void restoreInstanceState(final Bundle bundle) {
        final int int1 = bundle.getInt("android:menu:checked", 0);
        if (int1 != 0) {
            this.mUpdateSuspended = true;
            for (final NavigationMenuPresenter$NavigationMenuItem navigationMenuPresenter$NavigationMenuItem : this.mItems) {
                if (navigationMenuPresenter$NavigationMenuItem instanceof NavigationMenuPresenter$NavigationMenuTextItem) {
                    final MenuItemImpl menuItem = ((NavigationMenuPresenter$NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem).getMenuItem();
                    if (menuItem != null && menuItem.getItemId() == int1) {
                        this.setCheckedItem(menuItem);
                        break;
                    }
                    continue;
                }
            }
            this.mUpdateSuspended = false;
            this.prepareMenuItems();
        }
        final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
        for (final NavigationMenuPresenter$NavigationMenuItem navigationMenuPresenter$NavigationMenuItem2 : this.mItems) {
            if (navigationMenuPresenter$NavigationMenuItem2 instanceof NavigationMenuPresenter$NavigationMenuTextItem) {
                final MenuItemImpl menuItem2 = ((NavigationMenuPresenter$NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem2).getMenuItem();
                View actionView;
                if (menuItem2 != null) {
                    actionView = menuItem2.getActionView();
                }
                else {
                    actionView = null;
                }
                if (actionView == null) {
                    continue;
                }
                actionView.restoreHierarchyState((SparseArray)sparseParcelableArray.get(menuItem2.getItemId()));
            }
        }
    }
    
    public void setCheckedItem(final MenuItemImpl mCheckedItem) {
        if (this.mCheckedItem == mCheckedItem || !mCheckedItem.isCheckable()) {
            return;
        }
        if (this.mCheckedItem != null) {
            this.mCheckedItem.setChecked(false);
        }
        (this.mCheckedItem = mCheckedItem).setChecked(true);
    }
    
    public void setUpdateSuspended(final boolean mUpdateSuspended) {
        this.mUpdateSuspended = mUpdateSuspended;
    }
    
    public void update() {
        this.prepareMenuItems();
        this.notifyDataSetChanged();
    }
}
