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
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.content.Context;
import android.support.v7.internal.view.menu.j;
import android.view.ViewGroup;
import android.view.View;
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
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.y;

final class ToolbarActionBar$PanelMenuPresenterCallback implements y
{
    final /* synthetic */ ToolbarActionBar this$0;
    
    private ToolbarActionBar$PanelMenuPresenterCallback(final ToolbarActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (this.this$0.mWindowCallback != null) {
            this.this$0.mWindowCallback.onPanelClosed(0, (Menu)i);
        }
        this.this$0.mWindow.closePanel(0);
    }
    
    @Override
    public boolean onOpenSubMenu(final i i) {
        if (i == null && this.this$0.mWindowCallback != null) {
            this.this$0.mWindowCallback.onMenuOpened(0, (Menu)i);
        }
        return true;
    }
}
