// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Build$VERSION;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.SubMenu;

public final class ab
{
    public static SupportSubMenu a(final SubMenu subMenu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return new ae(subMenu);
        }
        throw new UnsupportedOperationException();
    }
    
    public static Menu a(final Menu menu) {
        Object o = menu;
        if (Build$VERSION.SDK_INT >= 14) {
            o = new ac(menu);
        }
        return (Menu)o;
    }
    
    public static MenuItem a(final MenuItem menuItem) {
        Object o;
        if (Build$VERSION.SDK_INT >= 16) {
            o = new t(menuItem);
        }
        else {
            o = menuItem;
            if (Build$VERSION.SDK_INT >= 14) {
                return (MenuItem)new o(menuItem);
            }
        }
        return (MenuItem)o;
    }
    
    public static SupportMenuItem b(final MenuItem menuItem) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new t(menuItem);
        }
        if (Build$VERSION.SDK_INT >= 14) {
            return new o(menuItem);
        }
        throw new UnsupportedOperationException();
    }
}
