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
import android.content.Context;
import android.support.v7.appcompat.R$layout;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.view.View;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Window$Callback;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.support.v7.internal.view.menu.g;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.y;

final class ToolbarActionBar$ActionMenuPresenterCallback implements y
{
    private boolean mClosingActionMenu;
    final /* synthetic */ ToolbarActionBar this$0;
    
    private ToolbarActionBar$ActionMenuPresenterCallback(final ToolbarActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        this.this$0.mDecorToolbar.dismissPopupMenus();
        if (this.this$0.mWindowCallback != null) {
            this.this$0.mWindowCallback.onPanelClosed(108, (Menu)i);
        }
        this.mClosingActionMenu = false;
    }
    
    @Override
    public boolean onOpenSubMenu(final i i) {
        if (this.this$0.mWindowCallback != null) {
            this.this$0.mWindowCallback.onMenuOpened(108, (Menu)i);
            return true;
        }
        return false;
    }
}
