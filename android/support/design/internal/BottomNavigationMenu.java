// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.view.SubMenu;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.MenuItem;
import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;

public final class BottomNavigationMenu extends MenuBuilder
{
    public static final int MAX_ITEM_COUNT = 5;
    
    public BottomNavigationMenu(final Context context) {
        super(context);
    }
    
    @Override
    protected MenuItem addInternal(final int n, final int n2, final int n3, final CharSequence charSequence) {
        if (this.size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        this.stopDispatchingItemsChanged();
        final MenuItem addInternal = super.addInternal(n, n2, n3, charSequence);
        if (addInternal instanceof MenuItemImpl) {
            ((MenuItemImpl)addInternal).setExclusiveCheckable(true);
        }
        this.startDispatchingItemsChanged();
        return addInternal;
    }
    
    @Override
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
