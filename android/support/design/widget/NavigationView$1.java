// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.MenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder$Callback;

class NavigationView$1 implements MenuBuilder$Callback
{
    final /* synthetic */ NavigationView this$0;
    
    NavigationView$1(final NavigationView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return this.this$0.mListener != null && this.this$0.mListener.onNavigationItemSelected(menuItem);
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
    }
}
