// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.util.AttributeSet;
import android.os.Parcelable;
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
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnKeyListener;

public class w implements y, View$OnKeyListener, ViewTreeObserver$OnGlobalLayoutListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener
{
    static final int ITEM_LAYOUT;
    private static final String TAG = "MenuPopupHelper";
    private final x mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    boolean mForceShowIcon;
    private boolean mHasContentWidth;
    private final LayoutInflater mInflater;
    private ViewGroup mMeasureParent;
    private final i mMenu;
    private final boolean mOverflowOnly;
    private ListPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private z mPresenterCallback;
    private ViewTreeObserver mTreeObserver;
    
    static {
        ITEM_LAYOUT = R$layout.abc_popup_menu_item_layout;
    }
    
    public w(final Context context, final i i) {
        this(context, i, null, false, R$attr.popupMenuStyle);
    }
    
    public w(final Context context, final i i, final View view) {
        this(context, i, view, false, R$attr.popupMenuStyle);
    }
    
    public w(final Context mContext, final i mMenu, final View mAnchorView, final boolean mOverflowOnly, final int mPopupStyleAttr) {
        this.mDropDownGravity = 0;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mMenu = mMenu;
        this.mAdapter = new x(this, this.mMenu);
        this.mOverflowOnly = mOverflowOnly;
        this.mPopupStyleAttr = mPopupStyleAttr;
        final Resources resources = mContext.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.mAnchorView = mAnchorView;
        mMenu.a(this, mContext);
    }
    
    private int measureContentWidth() {
        final x mAdapter = this.mAdapter;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int count = ((ListAdapter)mAdapter).getCount();
        int n = 0;
        int n2 = 0;
        View view = null;
        int n3 = 0;
        int mPopupMaxWidth;
        while (true) {
            mPopupMaxWidth = n3;
            if (n >= count) {
                break;
            }
            final int itemViewType = ((ListAdapter)mAdapter).getItemViewType(n);
            if (itemViewType != n2) {
                n2 = itemViewType;
                view = null;
            }
            if (this.mMeasureParent == null) {
                this.mMeasureParent = (ViewGroup)new FrameLayout(this.mContext);
            }
            view = ((ListAdapter)mAdapter).getView(n, view, this.mMeasureParent);
            view.measure(measureSpec, measureSpec2);
            final int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= this.mPopupMaxWidth) {
                mPopupMaxWidth = this.mPopupMaxWidth;
                break;
            }
            if (measuredWidth > n3) {
                n3 = measuredWidth;
            }
            ++n;
        }
        return mPopupMaxWidth;
    }
    
    @Override
    public boolean collapseItemActionView(final i i, final m m) {
        return false;
    }
    
    public void dismiss() {
        if (this.isShowing()) {
            this.mPopup.dismiss();
        }
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
        return 0;
    }
    
    @Override
    public aa getMenuView(final ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }
    
    public ListPopupWindow getPopup() {
        return this.mPopup;
    }
    
    @Override
    public void initForMenu(final Context context, final i i) {
    }
    
    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (i == this.mMenu) {
            this.dismiss();
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(i, b);
            }
        }
    }
    
    public void onDismiss() {
        this.mPopup = null;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            this.mTreeObserver = null;
        }
    }
    
    public void onGlobalLayout() {
        if (this.isShowing()) {
            final View mAnchorView = this.mAnchorView;
            if (mAnchorView == null || !mAnchorView.isShown()) {
                this.dismiss();
            }
            else if (this.isShowing()) {
                this.mPopup.show();
            }
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final x mAdapter = this.mAdapter;
        mAdapter.b.a((MenuItem)mAdapter.a(n), 0);
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n == 82) {
            this.dismiss();
            return true;
        }
        return false;
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final ae ae) {
        if (ae.hasVisibleItems()) {
            final w w = new w(this.mContext, ae, this.mAnchorView);
            w.setCallback(this.mPresenterCallback);
            final int size = ae.size();
            int i = 0;
            while (true) {
                while (i < size) {
                    final MenuItem item = ae.getItem(i);
                    if (item.isVisible() && item.getIcon() != null) {
                        final boolean forceShowIcon = true;
                        w.setForceShowIcon(forceShowIcon);
                        if (w.tryShow()) {
                            if (this.mPresenterCallback != null) {
                                this.mPresenterCallback.onOpenSubMenu(ae);
                            }
                            return true;
                        }
                        return false;
                    }
                    else {
                        ++i;
                    }
                }
                final boolean forceShowIcon = false;
                continue;
            }
        }
        return false;
    }
    
    public void setAnchorView(final View mAnchorView) {
        this.mAnchorView = mAnchorView;
    }
    
    @Override
    public void setCallback(final z mPresenterCallback) {
        this.mPresenterCallback = mPresenterCallback;
    }
    
    public void setForceShowIcon(final boolean mForceShowIcon) {
        this.mForceShowIcon = mForceShowIcon;
    }
    
    public void setGravity(final int mDropDownGravity) {
        this.mDropDownGravity = mDropDownGravity;
    }
    
    public void show() {
        if (!this.tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }
    
    public boolean tryShow() {
        boolean b = false;
        (this.mPopup = new ListPopupWindow(this.mContext, null, this.mPopupStyleAttr)).setOnDismissListener((PopupWindow$OnDismissListener)this);
        this.mPopup.setOnItemClickListener((AdapterView$OnItemClickListener)this);
        this.mPopup.setAdapter((ListAdapter)this.mAdapter);
        this.mPopup.setModal(true);
        final View mAnchorView = this.mAnchorView;
        if (mAnchorView != null) {
            if (this.mTreeObserver == null) {
                b = true;
            }
            this.mTreeObserver = mAnchorView.getViewTreeObserver();
            if (b) {
                this.mTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            }
            this.mPopup.setAnchorView(mAnchorView);
            this.mPopup.setDropDownGravity(this.mDropDownGravity);
            if (!this.mHasContentWidth) {
                this.mContentWidth = this.measureContentWidth();
                this.mHasContentWidth = true;
            }
            this.mPopup.setContentWidth(this.mContentWidth);
            this.mPopup.setInputMethodMode(2);
            this.mPopup.show();
            this.mPopup.getListView().setOnKeyListener((View$OnKeyListener)this);
            return true;
        }
        return false;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        this.mHasContentWidth = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
