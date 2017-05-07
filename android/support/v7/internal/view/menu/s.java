// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class s extends f<MenuItem$OnMenuItemClickListener> implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ o a;
    
    s(final o a, final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        this.a = a;
        super(menuItem$OnMenuItemClickListener);
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        return ((MenuItem$OnMenuItemClickListener)this.b).onMenuItemClick(this.a.a(menuItem));
    }
}
