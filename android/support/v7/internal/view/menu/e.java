// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.Iterator;
import android.support.v4.util.ArrayMap;
import android.view.SubMenu;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.support.v4.internal.view.SupportMenuItem;
import java.util.Map;
import android.content.Context;

abstract class e<T> extends f<T>
{
    final Context a;
    private Map<SupportMenuItem, MenuItem> c;
    private Map<SupportSubMenu, SubMenu> d;
    
    e(final Context a, final T t) {
        super(t);
        this.a = a;
    }
    
    final MenuItem a(MenuItem a) {
        if (a instanceof SupportMenuItem) {
            final SupportMenuItem supportMenuItem = (SupportMenuItem)a;
            if (this.c == null) {
                this.c = new ArrayMap<SupportMenuItem, MenuItem>();
            }
            if ((a = this.c.get(a)) == null) {
                a = ab.a(this.a, supportMenuItem);
                this.c.put(supportMenuItem, a);
            }
            return a;
        }
        return a;
    }
    
    final SubMenu a(SubMenu a) {
        if (a instanceof SupportSubMenu) {
            final SupportSubMenu supportSubMenu = (SupportSubMenu)a;
            if (this.d == null) {
                this.d = new ArrayMap<SupportSubMenu, SubMenu>();
            }
            if ((a = this.d.get(supportSubMenu)) == null) {
                a = ab.a(this.a, supportSubMenu);
                this.d.put(supportSubMenu, a);
            }
            return a;
        }
        return a;
    }
    
    final void a() {
        if (this.c != null) {
            this.c.clear();
        }
        if (this.d != null) {
            this.d.clear();
        }
    }
    
    final void a(final int n) {
        if (this.c != null) {
            final Iterator<SupportMenuItem> iterator = this.c.keySet().iterator();
            while (iterator.hasNext()) {
                if (n == ((MenuItem)iterator.next()).getGroupId()) {
                    iterator.remove();
                }
            }
        }
    }
    
    final void b(final int n) {
        if (this.c != null) {
            final Iterator<SupportMenuItem> iterator = this.c.keySet().iterator();
            while (iterator.hasNext()) {
                if (n == ((MenuItem)iterator.next()).getItemId()) {
                    iterator.remove();
                }
            }
        }
    }
}
