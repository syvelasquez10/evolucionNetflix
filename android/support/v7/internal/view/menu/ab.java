// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.SubMenu;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.support.v4.internal.view.SupportMenuItem;
import android.os.Build$VERSION;
import android.view.Menu;
import android.support.v4.internal.view.SupportMenu;
import android.content.Context;

public final class ab
{
    public static Menu a(final Context context, final SupportMenu supportMenu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return (Menu)new ac(context, supportMenu);
        }
        throw new UnsupportedOperationException();
    }
    
    public static MenuItem a(final Context context, final SupportMenuItem supportMenuItem) {
        if (Build$VERSION.SDK_INT >= 16) {
            return (MenuItem)new t(context, supportMenuItem);
        }
        if (Build$VERSION.SDK_INT >= 14) {
            return (MenuItem)new o(context, supportMenuItem);
        }
        throw new UnsupportedOperationException();
    }
    
    public static SubMenu a(final Context context, final SupportSubMenu supportSubMenu) {
        if (Build$VERSION.SDK_INT >= 14) {
            return (SubMenu)new ae(context, supportSubMenu);
        }
        throw new UnsupportedOperationException();
    }
}
