// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Menu;
import android.view.View;
import android.view.Window$Callback;
import android.support.v7.view.WindowCallbackWrapper;

class ToolbarActionBar$ToolbarCallbackWrapper extends WindowCallbackWrapper
{
    final /* synthetic */ ToolbarActionBar this$0;
    
    public ToolbarActionBar$ToolbarCallbackWrapper(final ToolbarActionBar this$0, final Window$Callback window$Callback) {
        this.this$0 = this$0;
        super(window$Callback);
    }
    
    @Override
    public View onCreatePanelView(final int n) {
        switch (n) {
            case 0: {
                final Menu menu = this.this$0.mDecorToolbar.getMenu();
                if (this.onPreparePanel(n, null, menu) && this.onMenuOpened(n, menu)) {
                    return this.this$0.getListMenuView(menu);
                }
                break;
            }
        }
        return super.onCreatePanelView(n);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        final boolean onPreparePanel = super.onPreparePanel(n, view, menu);
        if (onPreparePanel && !this.this$0.mToolbarMenuPrepared) {
            this.this$0.mDecorToolbar.setMenuPrepared();
            this.this$0.mToolbarMenuPrepared = true;
        }
        return onPreparePanel;
    }
}
