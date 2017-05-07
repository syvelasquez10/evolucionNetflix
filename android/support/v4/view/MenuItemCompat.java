// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.os.Build$VERSION;

public class MenuItemCompat
{
    static final MenuItemCompat$MenuVersionImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 14) {
            IMPL = new MenuItemCompat$IcsMenuVersionImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = new MenuItemCompat$HoneycombMenuVersionImpl();
            return;
        }
        IMPL = new MenuItemCompat$BaseMenuVersionImpl();
    }
    
    public static boolean expandActionView(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).expandActionView();
        }
        return MenuItemCompat.IMPL.expandActionView(menuItem);
    }
    
    public static View getActionView(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getActionView();
        }
        return MenuItemCompat.IMPL.getActionView(menuItem);
    }
    
    public static boolean isActionViewExpanded(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).isActionViewExpanded();
        }
        return MenuItemCompat.IMPL.isActionViewExpanded(menuItem);
    }
    
    public static MenuItem setActionProvider(final MenuItem menuItem, final ActionProvider supportActionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return (MenuItem)((SupportMenuItem)menuItem).setSupportActionProvider(supportActionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final int actionView) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setActionView(actionView);
        }
        return MenuItemCompat.IMPL.setActionView(menuItem, actionView);
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final View actionView) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setActionView(actionView);
        }
        return MenuItemCompat.IMPL.setActionView(menuItem, actionView);
    }
    
    public static void setShowAsAction(final MenuItem menuItem, final int showAsAction) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem).setShowAsAction(showAsAction);
            return;
        }
        MenuItemCompat.IMPL.setShowAsAction(menuItem, showAsAction);
    }
}
