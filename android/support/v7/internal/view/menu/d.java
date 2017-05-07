// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.ArrayList;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;

public abstract class d implements y
{
    private z mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected i mMenu;
    private int mMenuLayoutRes;
    protected aa mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;
    
    public d(final Context mSystemContext, final int mMenuLayoutRes, final int mItemLayoutRes) {
        this.mSystemContext = mSystemContext;
        this.mSystemInflater = LayoutInflater.from(mSystemContext);
        this.mMenuLayoutRes = mMenuLayoutRes;
        this.mItemLayoutRes = mItemLayoutRes;
    }
    
    protected void addItemView(final View view, final int n) {
        final ViewGroup viewGroup = (ViewGroup)view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup)this.mMenuView).addView(view, n);
    }
    
    public abstract void bindItemView(final m p0, final ab p1);
    
    @Override
    public boolean collapseItemActionView(final i i, final m m) {
        return false;
    }
    
    public ab createItemView(final ViewGroup viewGroup) {
        return (ab)this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
    }
    
    @Override
    public boolean expandItemActionView(final i i, final m m) {
        return false;
    }
    
    protected boolean filterLeftoverView(final ViewGroup viewGroup, final int n) {
        viewGroup.removeViewAt(n);
        return true;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    public z getCallback() {
        return this.mCallback;
    }
    
    @Override
    public int getId() {
        return this.mId;
    }
    
    public View getItemView(final m m, final View view, final ViewGroup viewGroup) {
        ab itemView;
        if (view instanceof ab) {
            itemView = (ab)view;
        }
        else {
            itemView = this.createItemView(viewGroup);
        }
        this.bindItemView(m, itemView);
        return (View)itemView;
    }
    
    @Override
    public aa getMenuView(final ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            (this.mMenuView = (aa)this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false)).initialize(this.mMenu);
            this.updateMenuView(true);
        }
        return this.mMenuView;
    }
    
    @Override
    public void initForMenu(final Context mContext, final i mMenu) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mMenu = mMenu;
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(i, b);
        }
    }
    
    @Override
    public boolean onSubMenuSelected(final ae ae) {
        return this.mCallback != null && this.mCallback.onOpenSubMenu(ae);
    }
    
    @Override
    public void setCallback(final z mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public boolean shouldIncludeItem(final int n, final m m) {
        return true;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        final ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        if (viewGroup != null) {
            int i;
            if (this.mMenu != null) {
                this.mMenu.k();
                final ArrayList<m> j = this.mMenu.j();
                final int size = j.size();
                int n = 0;
                int n2 = 0;
                while (true) {
                    i = n2;
                    if (n >= size) {
                        break;
                    }
                    final m m = j.get(n);
                    if (this.shouldIncludeItem(n2, m)) {
                        final View child = viewGroup.getChildAt(n2);
                        m a;
                        if (child instanceof ab) {
                            a = ((ab)child).a();
                        }
                        else {
                            a = null;
                        }
                        final View itemView = this.getItemView(m, child, viewGroup);
                        if (m != a) {
                            itemView.setPressed(false);
                            ViewCompat.jumpDrawablesToCurrentState(itemView);
                        }
                        if (itemView != child) {
                            this.addItemView(itemView, n2);
                        }
                        ++n2;
                    }
                    ++n;
                }
            }
            else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (!this.filterLeftoverView(viewGroup, i)) {
                    ++i;
                }
            }
        }
    }
}
