// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem$OnActionExpandListener;
import android.view.SubMenu;
import android.view.ViewDebug$CapturedViewProperty;
import android.support.v7.internal.widget.TintManager;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.view.MenuItem;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.MenuItem$OnMenuItemClickListener;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.view.ContextMenu$ContextMenuInfo;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider$VisibilityListener;

class n implements ActionProvider$VisibilityListener
{
    final /* synthetic */ m a;
    
    n(final m a) {
        this.a = a;
    }
    
    @Override
    public void onActionProviderVisibilityChanged(final boolean b) {
        this.a.o.a(this.a);
    }
}
