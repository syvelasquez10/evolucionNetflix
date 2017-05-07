// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MenuItem$OnMenuItemClickListener;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.view.MenuItem$OnActionExpandListener;
import android.view.CollapsibleActionView;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.util.Log;
import android.view.ActionProvider;
import android.content.Context;
import java.lang.reflect.Method;
import android.annotation.TargetApi;
import android.view.MenuItem;
import android.support.v4.internal.view.SupportMenuItem;

@TargetApi(14)
public class o extends e<SupportMenuItem> implements MenuItem
{
    private Method c;
    
    o(final Context context, final SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }
    
    p a(final ActionProvider actionProvider) {
        return new p(this, this.a, actionProvider);
    }
    
    public void a(final boolean b) {
        try {
            if (this.c == null) {
                this.c = ((SupportMenuItem)this.b).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.c.invoke(this.b, b);
        }
        catch (Exception ex) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", (Throwable)ex);
        }
    }
    
    public boolean collapseActionView() {
        return ((SupportMenuItem)this.b).collapseActionView();
    }
    
    public boolean expandActionView() {
        return ((SupportMenuItem)this.b).expandActionView();
    }
    
    public ActionProvider getActionProvider() {
        final android.support.v4.view.ActionProvider supportActionProvider = ((SupportMenuItem)this.b).getSupportActionProvider();
        if (supportActionProvider instanceof p) {
            return ((p)supportActionProvider).a;
        }
        return null;
    }
    
    public View getActionView() {
        View view2;
        final View view = view2 = ((SupportMenuItem)this.b).getActionView();
        if (view instanceof q) {
            view2 = ((q)view).a();
        }
        return view2;
    }
    
    public char getAlphabeticShortcut() {
        return ((SupportMenuItem)this.b).getAlphabeticShortcut();
    }
    
    public int getGroupId() {
        return ((SupportMenuItem)this.b).getGroupId();
    }
    
    public Drawable getIcon() {
        return ((SupportMenuItem)this.b).getIcon();
    }
    
    public Intent getIntent() {
        return ((SupportMenuItem)this.b).getIntent();
    }
    
    public int getItemId() {
        return ((SupportMenuItem)this.b).getItemId();
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem)this.b).getMenuInfo();
    }
    
    public char getNumericShortcut() {
        return ((SupportMenuItem)this.b).getNumericShortcut();
    }
    
    public int getOrder() {
        return ((SupportMenuItem)this.b).getOrder();
    }
    
    public SubMenu getSubMenu() {
        return this.a(((SupportMenuItem)this.b).getSubMenu());
    }
    
    public CharSequence getTitle() {
        return ((SupportMenuItem)this.b).getTitle();
    }
    
    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem)this.b).getTitleCondensed();
    }
    
    public boolean hasSubMenu() {
        return ((SupportMenuItem)this.b).hasSubMenu();
    }
    
    public boolean isActionViewExpanded() {
        return ((SupportMenuItem)this.b).isActionViewExpanded();
    }
    
    public boolean isCheckable() {
        return ((SupportMenuItem)this.b).isCheckable();
    }
    
    public boolean isChecked() {
        return ((SupportMenuItem)this.b).isChecked();
    }
    
    public boolean isEnabled() {
        return ((SupportMenuItem)this.b).isEnabled();
    }
    
    public boolean isVisible() {
        return ((SupportMenuItem)this.b).isVisible();
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        final SupportMenuItem supportMenuItem = (SupportMenuItem)this.b;
        p a;
        if (actionProvider != null) {
            a = this.a(actionProvider);
        }
        else {
            a = null;
        }
        supportMenuItem.setSupportActionProvider(a);
        return (MenuItem)this;
    }
    
    public MenuItem setActionView(final int actionView) {
        ((SupportMenuItem)this.b).setActionView(actionView);
        final View actionView2 = ((SupportMenuItem)this.b).getActionView();
        if (actionView2 instanceof CollapsibleActionView) {
            ((SupportMenuItem)this.b).setActionView((View)new q(actionView2));
        }
        return (MenuItem)this;
    }
    
    public MenuItem setActionView(final View view) {
        Object actionView = view;
        if (view instanceof CollapsibleActionView) {
            actionView = new q(view);
        }
        ((SupportMenuItem)this.b).setActionView((View)actionView);
        return (MenuItem)this;
    }
    
    public MenuItem setAlphabeticShortcut(final char alphabeticShortcut) {
        ((SupportMenuItem)this.b).setAlphabeticShortcut(alphabeticShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean checkable) {
        ((SupportMenuItem)this.b).setCheckable(checkable);
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean checked) {
        ((SupportMenuItem)this.b).setChecked(checked);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean enabled) {
        ((SupportMenuItem)this.b).setEnabled(enabled);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int icon) {
        ((SupportMenuItem)this.b).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable icon) {
        ((SupportMenuItem)this.b).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent intent) {
        ((SupportMenuItem)this.b).setIntent(intent);
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char numericShortcut) {
        ((SupportMenuItem)this.b).setNumericShortcut(numericShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        final SupportMenuItem supportMenuItem = (SupportMenuItem)this.b;
        r supportOnActionExpandListener;
        if (menuItem$OnActionExpandListener != null) {
            supportOnActionExpandListener = new r(this, menuItem$OnActionExpandListener);
        }
        else {
            supportOnActionExpandListener = null;
        }
        supportMenuItem.setSupportOnActionExpandListener(supportOnActionExpandListener);
        return (MenuItem)this;
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        final SupportMenuItem supportMenuItem = (SupportMenuItem)this.b;
        Object onMenuItemClickListener;
        if (menuItem$OnMenuItemClickListener != null) {
            onMenuItemClickListener = new s(this, menuItem$OnMenuItemClickListener);
        }
        else {
            onMenuItemClickListener = null;
        }
        supportMenuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)onMenuItemClickListener);
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char c, final char c2) {
        ((SupportMenuItem)this.b).setShortcut(c, c2);
        return (MenuItem)this;
    }
    
    public void setShowAsAction(final int showAsAction) {
        ((SupportMenuItem)this.b).setShowAsAction(showAsAction);
    }
    
    public MenuItem setShowAsActionFlags(final int showAsActionFlags) {
        ((SupportMenuItem)this.b).setShowAsActionFlags(showAsActionFlags);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final int title) {
        ((SupportMenuItem)this.b).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence title) {
        ((SupportMenuItem)this.b).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence titleCondensed) {
        ((SupportMenuItem)this.b).setTitleCondensed(titleCondensed);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean visible) {
        return ((SupportMenuItem)this.b).setVisible(visible);
    }
}
