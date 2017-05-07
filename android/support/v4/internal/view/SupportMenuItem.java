// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.internal.view;

import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.view.MenuItem;

public interface SupportMenuItem extends MenuItem
{
    boolean collapseActionView();
    
    boolean expandActionView();
    
    View getActionView();
    
    ActionProvider getSupportActionProvider();
    
    boolean isActionViewExpanded();
    
    MenuItem setActionView(final int p0);
    
    MenuItem setActionView(final View p0);
    
    void setShowAsAction(final int p0);
    
    MenuItem setShowAsActionFlags(final int p0);
    
    SupportMenuItem setSupportActionProvider(final ActionProvider p0);
    
    SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat$OnActionExpandListener p0);
}
