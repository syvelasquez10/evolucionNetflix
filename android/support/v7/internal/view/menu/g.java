// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.view.ContextThemeWrapper;
import android.widget.ListAdapter;
import android.support.v7.appcompat.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.AdapterView$OnItemClickListener;

public class g implements x, AdapterView$OnItemClickListener
{
    Context a;
    LayoutInflater b;
    i c;
    ExpandedMenuView d;
    int e;
    int f;
    h g;
    private int h;
    private y i;
    
    public g(final int f, final int e) {
        this.f = f;
        this.e = e;
    }
    
    public g(final Context a, final int n) {
        this(n, 0);
        this.a = a;
        this.b = LayoutInflater.from(this.a);
    }
    
    public z a(final ViewGroup viewGroup) {
        if (this.d == null) {
            this.d = (ExpandedMenuView)this.b.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.g == null) {
                this.g = new h(this);
            }
            this.d.setAdapter((ListAdapter)this.g);
            this.d.setOnItemClickListener((AdapterView$OnItemClickListener)this);
        }
        return this.d;
    }
    
    public ListAdapter a() {
        if (this.g == null) {
            this.g = new h(this);
        }
        return (ListAdapter)this.g;
    }
    
    public void a(final y i) {
        this.i = i;
    }
    
    @Override
    public boolean collapseItemActionView(final i i, final m m) {
        return false;
    }
    
    @Override
    public boolean expandItemActionView(final i i, final m m) {
        return false;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    @Override
    public void initForMenu(final Context a, final i c) {
        if (this.e != 0) {
            this.a = (Context)new ContextThemeWrapper(a, this.e);
            this.b = LayoutInflater.from(this.a);
        }
        else if (this.a != null) {
            this.a = a;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.a);
            }
        }
        this.c = c;
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (this.i != null) {
            this.i.onCloseMenu(i, b);
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.c.a((MenuItem)this.g.a(n), this, 0);
    }
    
    @Override
    public boolean onSubMenuSelected(final ad ad) {
        if (!ad.hasVisibleItems()) {
            return false;
        }
        new l(ad).a(null);
        if (this.i != null) {
            this.i.onOpenSubMenu(ad);
        }
        return true;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }
}
