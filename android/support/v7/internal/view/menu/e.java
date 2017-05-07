// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.Iterator;
import android.view.SubMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import java.util.HashMap;

abstract class e<T> extends f<T>
{
    private HashMap<MenuItem, SupportMenuItem> b;
    private HashMap<SubMenu, SubMenu> c;
    
    e(final T t) {
        super(t);
    }
    
    final SupportMenuItem a(final MenuItem menuItem) {
        if (menuItem != null) {
            if (this.b == null) {
                this.b = new HashMap<MenuItem, SupportMenuItem>();
            }
            SupportMenuItem b;
            if ((b = this.b.get(menuItem)) == null) {
                b = ab.b(menuItem);
                this.b.put(menuItem, b);
            }
            return b;
        }
        return null;
    }
    
    final SubMenu a(final SubMenu subMenu) {
        if (subMenu != null) {
            if (this.c == null) {
                this.c = new HashMap<SubMenu, SubMenu>();
            }
            Object a;
            if ((a = this.c.get(subMenu)) == null) {
                a = ab.a(subMenu);
                this.c.put(subMenu, (SubMenu)a);
            }
            return (SubMenu)a;
        }
        return null;
    }
    
    final void a() {
        if (this.b != null) {
            this.b.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
    }
    
    final void a(final int n) {
        if (this.b != null) {
            final Iterator<MenuItem> iterator = this.b.keySet().iterator();
            while (iterator.hasNext()) {
                if (n == iterator.next().getGroupId()) {
                    iterator.remove();
                }
            }
        }
    }
    
    final void b(final int n) {
        if (this.b != null) {
            final Iterator<MenuItem> iterator = this.b.keySet().iterator();
            while (iterator.hasNext()) {
                if (n == iterator.next().getItemId()) {
                    iterator.remove();
                }
            }
        }
    }
}
