// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.support.v7.internal.view.menu.j;
import android.content.res.Resources$Theme;
import android.support.v7.internal.view.menu.x;
import android.support.v7.internal.view.menu.y;
import android.content.Context;
import android.support.v7.appcompat.R$layout;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.support.v7.internal.view.menu.g;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.View;
import android.view.Window$Callback;
import android.support.v7.internal.view.WindowCallbackWrapper;

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
