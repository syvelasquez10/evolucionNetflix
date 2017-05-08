// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.content.Context;
import android.support.v7.view.menu.SubMenuBuilder;

public class NavigationSubMenu extends SubMenuBuilder
{
    public NavigationSubMenu(final Context context, final NavigationMenu navigationMenu, final MenuItemImpl menuItemImpl) {
        super(context, navigationMenu, menuItemImpl);
    }
    
    @Override
    public void onItemsChanged(final boolean b) {
        super.onItemsChanged(b);
        ((MenuBuilder)this.getParentMenu()).onItemsChanged(b);
    }
}
