// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.view.MenuItem$OnActionExpandListener;

class r extends f<MenuItem$OnActionExpandListener> implements MenuItemCompat$OnActionExpandListener
{
    final /* synthetic */ o a;
    
    r(final o a, final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        this.a = a;
        super(menuItem$OnActionExpandListener);
    }
    
    @Override
    public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
        return ((MenuItem$OnActionExpandListener)this.b).onMenuItemActionCollapse(this.a.a(menuItem));
    }
    
    @Override
    public boolean onMenuItemActionExpand(final MenuItem menuItem) {
        return ((MenuItem$OnActionExpandListener)this.b).onMenuItemActionExpand(this.a.a(menuItem));
    }
}
