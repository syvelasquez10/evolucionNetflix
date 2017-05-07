// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem$OnActionExpandListener;
import android.support.v7.view.CollapsibleActionView;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Method;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.support.v4.view.ActionProvider$VisibilityListener;
import android.view.ActionProvider;

class p extends ActionProvider
{
    final android.support.v4.view.ActionProvider a;
    final /* synthetic */ o b;
    
    public p(final o b, final android.support.v4.view.ActionProvider a) {
        this.b = b;
        super(a.getContext());
        this.a = a;
        if (b.b) {
            this.a.setVisibilityListener(new q(this, b));
        }
    }
    
    public boolean hasSubMenu() {
        return this.a.hasSubMenu();
    }
    
    public View onCreateActionView() {
        if (this.b.b) {
            this.b.c();
        }
        return this.a.onCreateActionView();
    }
    
    public boolean onPerformDefaultAction() {
        return this.a.onPerformDefaultAction();
    }
    
    public void onPrepareSubMenu(final SubMenu subMenu) {
        this.a.onPrepareSubMenu(this.b.a(subMenu));
    }
}
