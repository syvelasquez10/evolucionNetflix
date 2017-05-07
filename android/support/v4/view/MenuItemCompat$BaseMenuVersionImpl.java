// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.view.MenuItem;

class MenuItemCompat$BaseMenuVersionImpl implements MenuItemCompat$MenuVersionImpl
{
    @Override
    public boolean collapseActionView(final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public boolean expandActionView(final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public View getActionView(final MenuItem menuItem) {
        return null;
    }
    
    @Override
    public boolean isActionViewExpanded(final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public MenuItem setActionView(final MenuItem menuItem, final int n) {
        return menuItem;
    }
    
    @Override
    public MenuItem setActionView(final MenuItem menuItem, final View view) {
        return menuItem;
    }
    
    @Override
    public MenuItem setOnActionExpandListener(final MenuItem menuItem, final MenuItemCompat$OnActionExpandListener menuItemCompat$OnActionExpandListener) {
        return menuItem;
    }
    
    @Override
    public void setShowAsAction(final MenuItem menuItem, final int n) {
    }
}
