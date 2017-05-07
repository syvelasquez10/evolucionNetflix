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
import android.view.View;
import android.view.Menu;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Window$Callback;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v7.internal.view.menu.g;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;

class ToolbarActionBar$2 implements Toolbar$OnMenuItemClickListener
{
    final /* synthetic */ ToolbarActionBar this$0;
    
    ToolbarActionBar$2(final ToolbarActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemClick(final MenuItem menuItem) {
        return this.this$0.mWindowCallback.onMenuItemSelected(0, menuItem);
    }
}
