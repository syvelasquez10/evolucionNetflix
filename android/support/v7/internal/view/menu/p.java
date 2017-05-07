// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.SubMenu;
import android.view.View;
import android.content.Context;
import android.support.v4.view.ActionProvider;

class p extends ActionProvider
{
    final android.view.ActionProvider a;
    final /* synthetic */ o b;
    
    public p(final o b, final Context context, final android.view.ActionProvider a) {
        this.b = b;
        super(context);
        this.a = a;
    }
    
    @Override
    public boolean hasSubMenu() {
        return this.a.hasSubMenu();
    }
    
    @Override
    public View onCreateActionView() {
        return this.a.onCreateActionView();
    }
    
    @Override
    public boolean onPerformDefaultAction() {
        return this.a.onPerformDefaultAction();
    }
    
    @Override
    public void onPrepareSubMenu(final SubMenu subMenu) {
        this.a.onPrepareSubMenu(this.b.a(subMenu));
    }
}
