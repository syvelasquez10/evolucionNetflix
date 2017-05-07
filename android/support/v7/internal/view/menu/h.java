// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.os.IBinder;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$layout;
import android.util.SparseArray;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.AdapterView$OnItemClickListener;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.widget.BaseAdapter;

class h extends BaseAdapter
{
    final /* synthetic */ g a;
    private int b;
    
    public h(final g a) {
        this.a = a;
        this.b = -1;
        this.a();
    }
    
    public m a(int n) {
        final ArrayList<m> m = this.a.c.m();
        final int n2 = n += this.a.j;
        if (this.b >= 0 && (n = n2) >= this.b) {
            n = n2 + 1;
        }
        return m.get(n);
    }
    
    void a() {
        final m s = this.a.c.s();
        if (s != null) {
            final ArrayList<m> m = this.a.c.m();
            for (int size = m.size(), i = 0; i < size; ++i) {
                if (m.get(i) == s) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }
    
    public int getCount() {
        final int n = this.a.c.m().size() - this.a.j;
        if (this.b < 0) {
            return n;
        }
        return n - 1;
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        if (inflate == null) {
            inflate = this.a.b.inflate(this.a.f, viewGroup, false);
        }
        ((ab)inflate).a(this.a(n), 0);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        this.a();
        super.notifyDataSetChanged();
    }
}
