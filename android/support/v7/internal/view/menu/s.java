// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem;
import android.view.MenuItem$OnActionExpandListener;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;

class s extends f<MenuItemCompat$OnActionExpandListener> implements MenuItem$OnActionExpandListener
{
    final /* synthetic */ o b;
    
    s(final o b, final MenuItemCompat$OnActionExpandListener menuItemCompat$OnActionExpandListener) {
        this.b = b;
        super(menuItemCompat$OnActionExpandListener);
    }
    
    public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
        return ((MenuItemCompat$OnActionExpandListener)this.a).onMenuItemActionCollapse((MenuItem)this.b.a(menuItem));
    }
    
    public boolean onMenuItemActionExpand(final MenuItem menuItem) {
        return ((MenuItemCompat$OnActionExpandListener)this.a).onMenuItemActionExpand((MenuItem)this.b.a(menuItem));
    }
}
