// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.internal.widget.AdapterViewCompat$OnItemSelectedListener;
import android.support.v7.app.ActionBar$OnNavigationListener;
import android.widget.SpinnerAdapter;
import android.support.v7.internal.view.menu.y;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.i;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar$Tab;
import android.support.v7.internal.view.menu.j;
import android.support.v7.internal.view.menu.z;
import android.view.ViewGroup;
import android.view.View;
import android.view.Menu;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.view.Window;
import android.support.v7.widget.Toolbar;
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
