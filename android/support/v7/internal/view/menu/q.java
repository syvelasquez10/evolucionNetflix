// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem$OnActionExpandListener;
import android.support.v7.view.CollapsibleActionView;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.util.Log;
import android.support.v4.view.ActionProvider;
import java.lang.reflect.Method;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.support.v4.view.ActionProvider$VisibilityListener;

class q implements ActionProvider$VisibilityListener
{
    final /* synthetic */ o a;
    final /* synthetic */ p b;
    
    q(final p b, final o a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void onActionProviderVisibilityChanged(final boolean b) {
        if (this.b.a.overridesItemVisibility() && this.b.b.c) {
            this.b.b.b(b);
        }
    }
}
