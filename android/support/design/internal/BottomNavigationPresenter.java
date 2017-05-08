// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.SubMenuBuilder;
import android.os.Parcelable;
import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.view.ViewGroup;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;

public class BottomNavigationPresenter implements MenuPresenter
{
    private MenuBuilder mMenu;
    private BottomNavigationMenuView mMenuView;
    private boolean mUpdateSuspended;
    
    public BottomNavigationPresenter() {
        this.mUpdateSuspended = false;
    }
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    @Override
    public int getId() {
        return -1;
    }
    
    public MenuView getMenuView(final ViewGroup viewGroup) {
        return this.mMenuView;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder mMenu) {
        this.mMenuView.initialize(this.mMenu);
        this.mMenu = mMenu;
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        return false;
    }
    
    public void setBottomNavigationMenuView(final BottomNavigationMenuView mMenuView) {
        this.mMenuView = mMenuView;
    }
    
    @Override
    public void setCallback(final MenuPresenter$Callback menuPresenter$Callback) {
    }
    
    public void setUpdateSuspended(final boolean mUpdateSuspended) {
        this.mUpdateSuspended = mUpdateSuspended;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        if (this.mUpdateSuspended) {
            return;
        }
        if (b) {
            this.mMenuView.buildMenuView();
            return;
        }
        this.mMenuView.updateMenuView();
    }
}
