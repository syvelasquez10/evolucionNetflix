// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl extends MenuItemCompat$HoneycombMenuVersionImpl
{
    @Override
    public boolean collapseActionView(final MenuItem menuItem) {
        return MenuItemCompatIcs.collapseActionView(menuItem);
    }
    
    @Override
    public boolean expandActionView(final MenuItem menuItem) {
        return MenuItemCompatIcs.expandActionView(menuItem);
    }
    
    @Override
    public boolean isActionViewExpanded(final MenuItem menuItem) {
        return MenuItemCompatIcs.isActionViewExpanded(menuItem);
    }
    
    @Override
    public MenuItem setOnActionExpandListener(final MenuItem menuItem, final MenuItemCompat$OnActionExpandListener menuItemCompat$OnActionExpandListener) {
        if (menuItemCompat$OnActionExpandListener == null) {
            return MenuItemCompatIcs.setOnActionExpandListener(menuItem, null);
        }
        return MenuItemCompatIcs.setOnActionExpandListener(menuItem, new MenuItemCompat$IcsMenuVersionImpl$1(this, menuItemCompat$OnActionExpandListener));
    }
}
