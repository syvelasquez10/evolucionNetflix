// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.widget.ListAdapter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.view.View$MeasureSpec;
import android.content.res.Resources;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$layout;
import android.view.ViewTreeObserver;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnKeyListener;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.widget.BaseAdapter;

class w extends BaseAdapter
{
    final /* synthetic */ v a;
    private i b;
    private int c;
    
    public w(final v a, final i b) {
        this.a = a;
        this.c = -1;
        this.b = b;
        this.a();
    }
    
    public m a(final int n) {
        ArrayList<m> list;
        if (this.a.mOverflowOnly) {
            list = this.b.l();
        }
        else {
            list = this.b.i();
        }
        int n2 = n;
        if (this.c >= 0 && (n2 = n) >= this.c) {
            n2 = n + 1;
        }
        return list.get(n2);
    }
    
    void a() {
        final m r = this.a.mMenu.r();
        if (r != null) {
            final ArrayList<m> l = this.a.mMenu.l();
            for (int size = l.size(), i = 0; i < size; ++i) {
                if (l.get(i) == r) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }
    
    public int getCount() {
        ArrayList<m> list;
        if (this.a.mOverflowOnly) {
            list = this.b.l();
        }
        else {
            list = this.b.i();
        }
        if (this.c < 0) {
            return list.size();
        }
        return list.size() - 1;
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        if (inflate == null) {
            inflate = this.a.mInflater.inflate(v.ITEM_LAYOUT, viewGroup, false);
        }
        final aa aa = (aa)inflate;
        if (this.a.mForceShowIcon) {
            ((ListMenuItemView)inflate).a(true);
        }
        aa.a(this.a(n), 0);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        this.a();
        super.notifyDataSetChanged();
    }
}
