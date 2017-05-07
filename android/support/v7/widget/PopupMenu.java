// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MenuItem;
import android.support.v7.internal.view.menu.ae;
import android.support.v7.internal.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.view.Menu;
import android.support.v7.internal.view.menu.w;
import android.support.v7.internal.view.menu.i;
import android.view.View$OnTouchListener;
import android.content.Context;
import android.view.View;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.j;

public class PopupMenu implements j, z
{
    private View mAnchor;
    private Context mContext;
    private PopupMenu$OnDismissListener mDismissListener;
    private View$OnTouchListener mDragListener;
    private i mMenu;
    private PopupMenu$OnMenuItemClickListener mMenuItemClickListener;
    private w mPopup;
    
    public PopupMenu(final Context context, final View view) {
        this(context, view, 0);
    }
    
    public PopupMenu(final Context mContext, final View mAnchor, final int gravity) {
        this.mContext = mContext;
        (this.mMenu = new i(mContext)).a(this);
        this.mAnchor = mAnchor;
        (this.mPopup = new w(mContext, this.mMenu, mAnchor)).setGravity(gravity);
        this.mPopup.setCallback(this);
    }
    
    public void dismiss() {
        this.mPopup.dismiss();
    }
    
    public View$OnTouchListener getDragToOpenListener() {
        if (this.mDragListener == null) {
            this.mDragListener = (View$OnTouchListener)new PopupMenu$1(this, this.mAnchor);
        }
        return this.mDragListener;
    }
    
    public Menu getMenu() {
        return (Menu)this.mMenu;
    }
    
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContext);
    }
    
    public void inflate(final int n) {
        this.getMenuInflater().inflate(n, (Menu)this.mMenu);
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (this.mDismissListener != null) {
            this.mDismissListener.onDismiss(this);
        }
    }
    
    public void onCloseSubMenu(final ae ae) {
    }
    
    @Override
    public boolean onMenuItemSelected(final i i, final MenuItem menuItem) {
        return this.mMenuItemClickListener != null && this.mMenuItemClickListener.onMenuItemClick(menuItem);
    }
    
    @Override
    public void onMenuModeChange(final i i) {
    }
    
    @Override
    public boolean onOpenSubMenu(final i i) {
        boolean b = true;
        if (i == null) {
            b = false;
        }
        else if (i.hasVisibleItems()) {
            new w(this.mContext, i, this.mAnchor).show();
            return true;
        }
        return b;
    }
    
    public void setOnDismissListener(final PopupMenu$OnDismissListener mDismissListener) {
        this.mDismissListener = mDismissListener;
    }
    
    public void setOnMenuItemClickListener(final PopupMenu$OnMenuItemClickListener mMenuItemClickListener) {
        this.mMenuItemClickListener = mMenuItemClickListener;
    }
    
    public void show() {
        this.mPopup.show();
    }
}
