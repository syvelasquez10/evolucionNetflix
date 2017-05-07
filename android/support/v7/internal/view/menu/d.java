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

public abstract class d implements x
{
    private y mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected i mMenu;
    private int mMenuLayoutRes;
    protected z mMenuView;
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
    
    public abstract void bindItemView(final m p0, final aa p1);
    
    @Override
    public boolean collapseItemActionView(final i i, final m m) {
        return false;
    }
    
    public aa createItemView(final ViewGroup viewGroup) {
        return (aa)this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
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
    
    public y getCallback() {
        return this.mCallback;
    }
    
    public View getItemView(final m m, final View view, final ViewGroup viewGroup) {
        aa itemView;
        if (view instanceof aa) {
            itemView = (aa)view;
        }
        else {
            itemView = this.createItemView(viewGroup);
        }
        this.bindItemView(m, itemView);
        return (View)itemView;
    }
    
    public z getMenuView(final ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            (this.mMenuView = (z)this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false)).initialize(this.mMenu);
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
    public boolean onSubMenuSelected(final ad ad) {
        return this.mCallback != null && this.mCallback.onOpenSubMenu(ad);
    }
    
    public void setCallback(final y mCallback) {
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
            int j;
            if (this.mMenu != null) {
                this.mMenu.j();
                final ArrayList<m> i = this.mMenu.i();
                final int size = i.size();
                int n = 0;
                int n2 = 0;
                while (true) {
                    j = n2;
                    if (n >= size) {
                        break;
                    }
                    final m m = i.get(n);
                    if (this.shouldIncludeItem(n2, m)) {
                        final View child = viewGroup.getChildAt(n2);
                        m itemData;
                        if (child instanceof aa) {
                            itemData = ((aa)child).getItemData();
                        }
                        else {
                            itemData = null;
                        }
                        final View itemView = this.getItemView(m, child, viewGroup);
                        if (m != itemData) {
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
                j = 0;
            }
            while (j < viewGroup.getChildCount()) {
                if (!this.filterLeftoverView(viewGroup, j)) {
                    ++j;
                }
            }
        }
    }
}
