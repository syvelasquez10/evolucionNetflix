// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.view.MenuItem;
import android.view.View;
import android.view.View$OnClickListener;

class NavigationMenuPresenter$1 implements View$OnClickListener
{
    final /* synthetic */ NavigationMenuPresenter this$0;
    
    NavigationMenuPresenter$1(final NavigationMenuPresenter this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)view;
        this.this$0.setUpdateSuspended(true);
        final MenuItemImpl itemData = navigationMenuItemView.getItemData();
        final boolean performItemAction = this.this$0.mMenu.performItemAction((MenuItem)itemData, this.this$0, 0);
        if (itemData != null && itemData.isCheckable() && performItemAction) {
            this.this$0.mAdapter.setCheckedItem(itemData);
        }
        this.this$0.setUpdateSuspended(false);
        this.this$0.updateMenuView(false);
    }
}
