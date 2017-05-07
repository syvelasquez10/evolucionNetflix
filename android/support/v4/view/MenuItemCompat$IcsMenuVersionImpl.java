// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl extends MenuItemCompat$HoneycombMenuVersionImpl
{
    @Override
    public boolean expandActionView(final MenuItem menuItem) {
        return MenuItemCompatIcs.expandActionView(menuItem);
    }
    
    @Override
    public boolean isActionViewExpanded(final MenuItem menuItem) {
        return MenuItemCompatIcs.isActionViewExpanded(menuItem);
    }
}
