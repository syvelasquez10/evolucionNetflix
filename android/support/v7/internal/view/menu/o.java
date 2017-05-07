// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem$OnActionExpandListener;
import android.support.v7.view.CollapsibleActionView;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.util.Log;
import android.support.v4.view.ActionProvider;
import java.lang.reflect.Method;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;

public class o extends e<MenuItem> implements SupportMenuItem
{
    static final String b = "MenuItemWrapper";
    private final boolean c;
    private boolean d;
    private Method e;
    
    o(final MenuItem menuItem) {
        this(menuItem, true);
    }
    
    o(final MenuItem menuItem, final boolean c) {
        super(menuItem);
        this.d = menuItem.isVisible();
        this.c = c;
    }
    
    p a(final ActionProvider actionProvider) {
        return new p(this, actionProvider);
    }
    
    public void a(final boolean b) {
        try {
            if (this.e == null) {
                this.e = ((MenuItem)this.a).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.e.invoke(this.a, b);
        }
        catch (Exception ex) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", (Throwable)ex);
        }
    }
    
    final MenuItem b(final boolean visible) {
        return ((MenuItem)this.a).setVisible(visible);
    }
    
    final boolean c() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.d) {
            final ActionProvider supportActionProvider = this.getSupportActionProvider();
            b2 = b;
            if (supportActionProvider != null) {
                b2 = b;
                if (supportActionProvider.overridesItemVisibility()) {
                    b2 = b;
                    if (!supportActionProvider.isVisible()) {
                        this.b(false);
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public boolean collapseActionView() {
        return ((MenuItem)this.a).collapseActionView();
    }
    
    @Override
    public boolean expandActionView() {
        return ((MenuItem)this.a).expandActionView();
    }
    
    public android.view.ActionProvider getActionProvider() {
        return ((MenuItem)this.a).getActionProvider();
    }
    
    @Override
    public View getActionView() {
        View view2;
        final View view = view2 = ((MenuItem)this.a).getActionView();
        if (view instanceof r) {
            view2 = ((r)view).a();
        }
        return view2;
    }
    
    public char getAlphabeticShortcut() {
        return ((MenuItem)this.a).getAlphabeticShortcut();
    }
    
    public int getGroupId() {
        return ((MenuItem)this.a).getGroupId();
    }
    
    public Drawable getIcon() {
        return ((MenuItem)this.a).getIcon();
    }
    
    public Intent getIntent() {
        return ((MenuItem)this.a).getIntent();
    }
    
    public int getItemId() {
        return ((MenuItem)this.a).getItemId();
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return ((MenuItem)this.a).getMenuInfo();
    }
    
    public char getNumericShortcut() {
        return ((MenuItem)this.a).getNumericShortcut();
    }
    
    public int getOrder() {
        return ((MenuItem)this.a).getOrder();
    }
    
    public SubMenu getSubMenu() {
        return this.a(((MenuItem)this.a).getSubMenu());
    }
    
    @Override
    public ActionProvider getSupportActionProvider() {
        final p p = (p)((MenuItem)this.a).getActionProvider();
        if (p != null) {
            return p.a;
        }
        return null;
    }
    
    public CharSequence getTitle() {
        return ((MenuItem)this.a).getTitle();
    }
    
    public CharSequence getTitleCondensed() {
        return ((MenuItem)this.a).getTitleCondensed();
    }
    
    public boolean hasSubMenu() {
        return ((MenuItem)this.a).hasSubMenu();
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return ((MenuItem)this.a).isActionViewExpanded();
    }
    
    public boolean isCheckable() {
        return ((MenuItem)this.a).isCheckable();
    }
    
    public boolean isChecked() {
        return ((MenuItem)this.a).isChecked();
    }
    
    public boolean isEnabled() {
        return ((MenuItem)this.a).isEnabled();
    }
    
    public boolean isVisible() {
        return ((MenuItem)this.a).isVisible();
    }
    
    public MenuItem setActionProvider(final android.view.ActionProvider actionProvider) {
        ((MenuItem)this.a).setActionProvider(actionProvider);
        if (actionProvider != null && this.c) {
            this.c();
        }
        return (MenuItem)this;
    }
    
    @Override
    public MenuItem setActionView(final int actionView) {
        ((MenuItem)this.a).setActionView(actionView);
        final View actionView2 = ((MenuItem)this.a).getActionView();
        if (actionView2 instanceof CollapsibleActionView) {
            ((MenuItem)this.a).setActionView((View)new r(actionView2));
        }
        return (MenuItem)this;
    }
    
    @Override
    public MenuItem setActionView(final View view) {
        Object actionView = view;
        if (view instanceof CollapsibleActionView) {
            actionView = new r(view);
        }
        ((MenuItem)this.a).setActionView((View)actionView);
        return (MenuItem)this;
    }
    
    public MenuItem setAlphabeticShortcut(final char alphabeticShortcut) {
        ((MenuItem)this.a).setAlphabeticShortcut(alphabeticShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean checkable) {
        ((MenuItem)this.a).setCheckable(checkable);
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean checked) {
        ((MenuItem)this.a).setChecked(checked);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean enabled) {
        ((MenuItem)this.a).setEnabled(enabled);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int icon) {
        ((MenuItem)this.a).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable icon) {
        ((MenuItem)this.a).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent intent) {
        ((MenuItem)this.a).setIntent(intent);
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char numericShortcut) {
        ((MenuItem)this.a).setNumericShortcut(numericShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener onActionExpandListener) {
        ((MenuItem)this.a).setOnActionExpandListener(onActionExpandListener);
        return (MenuItem)this;
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        final MenuItem menuItem = (MenuItem)this.a;
        Object onMenuItemClickListener;
        if (menuItem$OnMenuItemClickListener != null) {
            onMenuItemClickListener = new t(this, menuItem$OnMenuItemClickListener);
        }
        else {
            onMenuItemClickListener = null;
        }
        menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)onMenuItemClickListener);
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char c, final char c2) {
        ((MenuItem)this.a).setShortcut(c, c2);
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int showAsAction) {
        ((MenuItem)this.a).setShowAsAction(showAsAction);
    }
    
    @Override
    public MenuItem setShowAsActionFlags(final int showAsActionFlags) {
        ((MenuItem)this.a).setShowAsActionFlags(showAsActionFlags);
        return (MenuItem)this;
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final ActionProvider actionProvider) {
        final MenuItem menuItem = (MenuItem)this.a;
        p a;
        if (actionProvider != null) {
            a = this.a(actionProvider);
        }
        else {
            a = null;
        }
        menuItem.setActionProvider((android.view.ActionProvider)a);
        return this;
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat$OnActionExpandListener menuItemCompat$OnActionExpandListener) {
        final MenuItem menuItem = (MenuItem)this.a;
        Object onActionExpandListener;
        if (menuItemCompat$OnActionExpandListener != null) {
            onActionExpandListener = new s(this, menuItemCompat$OnActionExpandListener);
        }
        else {
            onActionExpandListener = null;
        }
        menuItem.setOnActionExpandListener((MenuItem$OnActionExpandListener)onActionExpandListener);
        return null;
    }
    
    public MenuItem setTitle(final int title) {
        ((MenuItem)this.a).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence title) {
        ((MenuItem)this.a).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence titleCondensed) {
        ((MenuItem)this.a).setTitleCondensed(titleCondensed);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean d) {
        if (this.c) {
            this.d = d;
            if (this.c()) {
                return (MenuItem)this;
            }
        }
        return this.b(d);
    }
}
