// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.view.menu.MenuItemImpl;

class NavigationMenuPresenter$NavigationMenuTextItem implements NavigationMenuPresenter$NavigationMenuItem
{
    private final MenuItemImpl mMenuItem;
    boolean needsEmptyIcon;
    
    NavigationMenuPresenter$NavigationMenuTextItem(final MenuItemImpl mMenuItem) {
        this.mMenuItem = mMenuItem;
    }
    
    public MenuItemImpl getMenuItem() {
        return this.mMenuItem;
    }
}
