// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider;
import android.view.MenuItem;

class u extends o
{
    u(final MenuItem menuItem) {
        super(menuItem, false);
    }
    
    @Override
    p a(final ActionProvider actionProvider) {
        return new v(this, actionProvider);
    }
}
