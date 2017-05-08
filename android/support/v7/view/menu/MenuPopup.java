// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.view.View$MeasureSpec;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemClickListener;

abstract class MenuPopup implements MenuPresenter, ShowableListMenu, AdapterView$OnItemClickListener
{
    private Rect mEpicenterBounds;
    
    protected static int measureIndividualMenuWidth(final ListAdapter listAdapter, ViewGroup viewGroup, final Context context, final int n) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int count = listAdapter.getCount();
        int i = 0;
        int n2 = 0;
        View view = null;
        int n3 = 0;
        while (i < count) {
            final int itemViewType = listAdapter.getItemViewType(i);
            int n4;
            if (itemViewType != (n4 = n2)) {
                n4 = itemViewType;
                view = null;
            }
            if (viewGroup == null) {
                viewGroup = (ViewGroup)new FrameLayout(context);
            }
            view = listAdapter.getView(i, view, viewGroup);
            view.measure(measureSpec, measureSpec2);
            final int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= n) {
                return n;
            }
            if (measuredWidth > n3) {
                n3 = measuredWidth;
            }
            ++i;
            n2 = n4;
        }
        return n3;
    }
    
    protected static boolean shouldPreserveIconSpacing(final MenuBuilder menuBuilder) {
        final boolean b = false;
        final int size = menuBuilder.size();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= size) {
                break;
            }
            final MenuItem item = menuBuilder.getItem(n);
            if (item.isVisible() && item.getIcon() != null) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    protected static MenuAdapter toMenuAdapter(final ListAdapter listAdapter) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            return (MenuAdapter)((HeaderViewListAdapter)listAdapter).getWrappedAdapter();
        }
        return (MenuAdapter)listAdapter;
    }
    
    public abstract void addMenu(final MenuBuilder p0);
    
    protected boolean closeMenuOnSubMenuOpened() {
        return true;
    }
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }
    
    @Override
    public int getId() {
        return 0;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder menuBuilder) {
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, int n, final long n2) {
        final ListAdapter listAdapter = (ListAdapter)adapterView.getAdapter();
        final MenuBuilder mAdapterMenu = toMenuAdapter(listAdapter).mAdapterMenu;
        final MenuItem menuItem = (MenuItem)listAdapter.getItem(n);
        if (this.closeMenuOnSubMenuOpened()) {
            n = 0;
        }
        else {
            n = 4;
        }
        mAdapterMenu.performItemAction(menuItem, this, n);
    }
    
    public abstract void setAnchorView(final View p0);
    
    public void setEpicenterBounds(final Rect mEpicenterBounds) {
        this.mEpicenterBounds = mEpicenterBounds;
    }
    
    public abstract void setForceShowIcon(final boolean p0);
    
    public abstract void setGravity(final int p0);
    
    public abstract void setHorizontalOffset(final int p0);
    
    public abstract void setOnDismissListener(final PopupWindow$OnDismissListener p0);
    
    public abstract void setShowTitle(final boolean p0);
    
    public abstract void setVerticalOffset(final int p0);
}
