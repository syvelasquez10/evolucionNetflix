// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.SupportSubMenu;
import android.view.SubMenu;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.Menu;
import android.os.Build$VERSION;
import android.view.MenuItem;

public final class MenuWrapperFactory
{
    public static MenuItem createMenuItemWrapper(final MenuItem menuItem) {
        Object o;
        if (Build$VERSION.SDK_INT >= 16) {
            o = new MenuItemWrapperJB(menuItem);
        }
        else {
            o = menuItem;
            if (Build$VERSION.SDK_INT >= 14) {
                return (MenuItem)new MenuItemWrapperICS(menuItem);
            }
        }
        return (MenuItem)o;
    }
    
    public static Menu createMenuWrapper(final Menu menu) {
        Object o = menu;
        if (Build$VERSION.SDK_INT >= 14) {
            o = new MenuWrapperICS(menu);
        }
        return (Menu)o;
    }
    
    public static SupportMenuItem createSupportMenuItemWrapper(final MenuItem menuItem) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new MenuItemWrapperJB(menuItem);
        }
        if (Build$VERSION.SDK_INT >= 14) {
            return new MenuItemWrapperICS(menuItem);
        }
        throw new UnsupportedOperationException();
    }
    
    public static SupportMenu createSupportMenuWrapper(final Menu menu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return new MenuWrapperICS(menu);
        }
        throw new UnsupportedOperationException();
    }
    
    public static SupportSubMenu createSupportSubMenuWrapper(final SubMenu subMenu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return new SubMenuWrapperICS(subMenu);
        }
        throw new UnsupportedOperationException();
    }
}
