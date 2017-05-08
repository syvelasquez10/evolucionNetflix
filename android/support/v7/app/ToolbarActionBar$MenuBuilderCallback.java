// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder$Callback;

final class ToolbarActionBar$MenuBuilderCallback implements MenuBuilder$Callback
{
    final /* synthetic */ ToolbarActionBar this$0;
    
    ToolbarActionBar$MenuBuilderCallback(final ToolbarActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
        if (this.this$0.mWindowCallback != null) {
            if (this.this$0.mDecorToolbar.isOverflowMenuShowing()) {
                this.this$0.mWindowCallback.onPanelClosed(108, (Menu)menuBuilder);
            }
            else if (this.this$0.mWindowCallback.onPreparePanel(0, (View)null, (Menu)menuBuilder)) {
                this.this$0.mWindowCallback.onMenuOpened(108, (Menu)menuBuilder);
            }
        }
    }
}
