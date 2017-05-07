// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.view.SubMenu;

public class ad extends i implements SubMenu
{
    private i d;
    private m e;
    
    public ad(final Context context, final i d, final m e) {
        super(context);
        this.d = d;
        this.e = e;
    }
    
    public String a() {
        int itemId;
        if (this.e != null) {
            itemId = this.e.getItemId();
        }
        else {
            itemId = 0;
        }
        if (itemId == 0) {
            return null;
        }
        return super.a() + ":" + itemId;
    }
    
    @Override
    public void a(final j j) {
        this.d.a(j);
    }
    
    @Override
    boolean a(final i i, final MenuItem menuItem) {
        return super.a(i, menuItem) || this.d.a(i, menuItem);
    }
    
    public boolean b() {
        return this.d.b();
    }
    
    @Override
    public boolean c() {
        return this.d.c();
    }
    
    @Override
    public boolean c(final m m) {
        return this.d.c(m);
    }
    
    @Override
    public boolean d(final m m) {
        return this.d.d(m);
    }
    
    public MenuItem getItem() {
        return (MenuItem)this.e;
    }
    
    @Override
    public i p() {
        return this.d;
    }
    
    public Menu s() {
        return (Menu)this.d;
    }
    
    public SubMenu setHeaderIcon(final int n) {
        super.a(ContextCompat.getDrawable(this.e(), n));
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderIcon(final Drawable drawable) {
        super.a(drawable);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final int n) {
        super.a(this.e().getResources().getString(n));
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final CharSequence charSequence) {
        super.a(charSequence);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderView(final View view) {
        super.a(view);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final int icon) {
        this.e.setIcon(icon);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final Drawable icon) {
        this.e.setIcon(icon);
        return (SubMenu)this;
    }
    
    @Override
    public void setQwertyMode(final boolean qwertyMode) {
        this.d.setQwertyMode(qwertyMode);
    }
}
