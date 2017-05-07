// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportMenu;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Build$VERSION;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.SubMenu;

public final class ac
{
    public static SupportSubMenu a(final SubMenu subMenu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return new af(subMenu);
        }
        throw new UnsupportedOperationException();
    }
    
    public static Menu a(final Menu menu) {
        Object o = menu;
        if (Build$VERSION.SDK_INT >= 14) {
            o = new ad(menu);
        }
        return (Menu)o;
    }
    
    public static MenuItem a(final MenuItem menuItem) {
        Object o;
        if (Build$VERSION.SDK_INT >= 16) {
            o = new u(menuItem);
        }
        else {
            o = menuItem;
            if (Build$VERSION.SDK_INT >= 14) {
                return (MenuItem)new o(menuItem);
            }
        }
        return (MenuItem)o;
    }
    
    public static SupportMenu b(final Menu menu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return new ad(menu);
        }
        throw new UnsupportedOperationException();
    }
    
    public static SupportMenuItem b(final MenuItem menuItem) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new u(menuItem);
        }
        if (Build$VERSION.SDK_INT >= 14) {
            return new o(menuItem);
        }
        throw new UnsupportedOperationException();
    }
}
