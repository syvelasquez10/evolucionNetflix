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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.j;

final class ToolbarActionBar$MenuBuilderCallback implements j
{
    final /* synthetic */ ToolbarActionBar this$0;
    
    private ToolbarActionBar$MenuBuilderCallback(final ToolbarActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemSelected(final i i, final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public void onMenuModeChange(final i i) {
        if (this.this$0.mWindowCallback != null) {
            if (this.this$0.mToolbar.isOverflowMenuShowing()) {
                this.this$0.mWindowCallback.onPanelClosed(8, (Menu)i);
            }
            else if (this.this$0.mWindowCallback.onPreparePanel(0, null, (Menu)i)) {
                this.this$0.mWindowCallback.onMenuOpened(8, (Menu)i);
            }
        }
    }
}
