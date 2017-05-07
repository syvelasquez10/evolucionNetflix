// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class t extends f<MenuItem$OnMenuItemClickListener> implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ o b;
    
    t(final o b, final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        this.b = b;
        super(menuItem$OnMenuItemClickListener);
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        return ((MenuItem$OnMenuItemClickListener)this.a).onMenuItemClick((MenuItem)this.b.a(menuItem));
    }
}
