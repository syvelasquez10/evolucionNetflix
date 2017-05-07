// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.internal.view.menu.x;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.i;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.content.Context;
import android.support.v7.internal.view.menu.j;
import android.support.v7.internal.view.menu.y;
import android.view.ViewGroup;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.view.Window;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.support.v7.internal.view.menu.g;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.View;
import android.support.v7.widget.WindowCallbackWrapper;

class ToolbarActionBar$ToolbarCallbackWrapper extends WindowCallbackWrapper
{
    final /* synthetic */ ToolbarActionBar this$0;
    
    public ToolbarActionBar$ToolbarCallbackWrapper(final ToolbarActionBar this$0, final WindowCallback windowCallback) {
        this.this$0 = this$0;
        super(windowCallback);
    }
    
    @Override
    public View onCreatePanelView(final int n) {
        switch (n) {
            case 0: {
                if (!this.this$0.mToolbarMenuPrepared) {
                    this.this$0.populateOptionsMenu();
                    this.this$0.mToolbar.removeCallbacks(this.this$0.mMenuInvalidator);
                }
                if (!this.this$0.mToolbarMenuPrepared || this.this$0.mWindowCallback == null) {
                    break;
                }
                final Menu access$500 = this.this$0.getMenu();
                if (this.this$0.mWindowCallback.onPreparePanel(n, null, access$500) && this.this$0.mWindowCallback.onMenuOpened(n, access$500)) {
                    return this.this$0.getListMenuView(access$500);
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
