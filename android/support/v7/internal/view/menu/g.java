// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.os.IBinder;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$layout;
import android.view.ViewGroup;
import android.util.SparseArray;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.AdapterView$OnItemClickListener;

public class g implements y, AdapterView$OnItemClickListener
{
    public static final String h = "android:menu:list";
    private static final String i = "ListMenuPresenter";
    Context a;
    LayoutInflater b;
    i c;
    ExpandedMenuView d;
    int e;
    int f;
    h g;
    private int j;
    private z k;
    private int l;
    
    public g(final int f, final int e) {
        this.f = f;
        this.e = e;
    }
    
    public g(final Context a, final int n) {
        this(n, 0);
        this.a = a;
        this.b = LayoutInflater.from(this.a);
    }
    
    public ListAdapter a() {
        if (this.g == null) {
            this.g = new h(this);
        }
        return (ListAdapter)this.g;
    }
    
    public void a(final int j) {
        this.j = j;
        if (this.d != null) {
            this.updateMenuView(false);
        }
    }
    
    public void a(final Bundle bundle) {
        final SparseArray sparseArray = new SparseArray();
        if (this.d != null) {
            ((View)this.d).saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }
    
    int b() {
        return this.j;
    }
    
    public void b(final int l) {
        this.l = l;
    }
    
    public void b(final Bundle bundle) {
        final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            ((View)this.d).restoreHierarchyState(sparseParcelableArray);
        }
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
    public int getId() {
        return this.l;
    }
    
    @Override
    public aa getMenuView(final ViewGroup viewGroup) {
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
        if (this.k != null) {
            this.k.onCloseMenu(i, b);
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.c.a((MenuItem)this.g.a(n), this, 0);
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        this.b((Bundle)parcelable);
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        if (this.d == null) {
            return null;
        }
        final Bundle bundle = new Bundle();
        this.a(bundle);
        return (Parcelable)bundle;
    }
    
    @Override
    public boolean onSubMenuSelected(final ae ae) {
        if (!ae.hasVisibleItems()) {
            return false;
        }
        new l(ae).a((IBinder)null);
        if (this.k != null) {
            this.k.onOpenSubMenu(ae);
        }
        return true;
    }
    
    @Override
    public void setCallback(final z k) {
        this.k = k;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }
}
