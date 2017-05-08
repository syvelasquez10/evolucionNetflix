// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.MenuItem$OnMenuItemClickListener;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.view.MenuItem$OnActionExpandListener;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ActionProvider;
import android.content.Context;
import java.lang.reflect.Method;
import android.annotation.TargetApi;
import android.view.MenuItem;
import android.support.v4.internal.view.SupportMenuItem;

@TargetApi(14)
public class MenuItemWrapperICS extends BaseMenuWrapper<SupportMenuItem> implements MenuItem
{
    private Method mSetExclusiveCheckableMethod;
    
    MenuItemWrapperICS(final Context context, final SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }
    
    public boolean collapseActionView() {
        return ((SupportMenuItem)this.mWrappedObject).collapseActionView();
    }
    
    MenuItemWrapperICS$ActionProviderWrapper createActionProviderWrapper(final ActionProvider actionProvider) {
        return new MenuItemWrapperICS$ActionProviderWrapper(this, this.mContext, actionProvider);
    }
    
    public boolean expandActionView() {
        return ((SupportMenuItem)this.mWrappedObject).expandActionView();
    }
    
    public ActionProvider getActionProvider() {
        final android.support.v4.view.ActionProvider supportActionProvider = ((SupportMenuItem)this.mWrappedObject).getSupportActionProvider();
        if (supportActionProvider instanceof MenuItemWrapperICS$ActionProviderWrapper) {
            return ((MenuItemWrapperICS$ActionProviderWrapper)supportActionProvider).mInner;
        }
        return null;
    }
    
    public View getActionView() {
        View view2;
        final View view = view2 = ((SupportMenuItem)this.mWrappedObject).getActionView();
        if (view instanceof MenuItemWrapperICS$CollapsibleActionViewWrapper) {
            view2 = ((MenuItemWrapperICS$CollapsibleActionViewWrapper)view).getWrappedView();
        }
        return view2;
    }
    
    public char getAlphabeticShortcut() {
        return ((SupportMenuItem)this.mWrappedObject).getAlphabeticShortcut();
    }
    
    public int getGroupId() {
        return ((SupportMenuItem)this.mWrappedObject).getGroupId();
    }
    
    public Drawable getIcon() {
        return ((SupportMenuItem)this.mWrappedObject).getIcon();
    }
    
    public Intent getIntent() {
        return ((SupportMenuItem)this.mWrappedObject).getIntent();
    }
    
    public int getItemId() {
        return ((SupportMenuItem)this.mWrappedObject).getItemId();
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem)this.mWrappedObject).getMenuInfo();
    }
    
    public char getNumericShortcut() {
        return ((SupportMenuItem)this.mWrappedObject).getNumericShortcut();
    }
    
    public int getOrder() {
        return ((SupportMenuItem)this.mWrappedObject).getOrder();
    }
    
    public SubMenu getSubMenu() {
        return this.getSubMenuWrapper(((SupportMenuItem)this.mWrappedObject).getSubMenu());
    }
    
    public CharSequence getTitle() {
        return ((SupportMenuItem)this.mWrappedObject).getTitle();
    }
    
    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem)this.mWrappedObject).getTitleCondensed();
    }
    
    public boolean hasSubMenu() {
        return ((SupportMenuItem)this.mWrappedObject).hasSubMenu();
    }
    
    public boolean isActionViewExpanded() {
        return ((SupportMenuItem)this.mWrappedObject).isActionViewExpanded();
    }
    
    public boolean isCheckable() {
        return ((SupportMenuItem)this.mWrappedObject).isCheckable();
    }
    
    public boolean isChecked() {
        return ((SupportMenuItem)this.mWrappedObject).isChecked();
    }
    
    public boolean isEnabled() {
        return ((SupportMenuItem)this.mWrappedObject).isEnabled();
    }
    
    public boolean isVisible() {
        return ((SupportMenuItem)this.mWrappedObject).isVisible();
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        final SupportMenuItem supportMenuItem = (SupportMenuItem)this.mWrappedObject;
        MenuItemWrapperICS$ActionProviderWrapper actionProviderWrapper;
        if (actionProvider != null) {
            actionProviderWrapper = this.createActionProviderWrapper(actionProvider);
        }
        else {
            actionProviderWrapper = null;
        }
        supportMenuItem.setSupportActionProvider(actionProviderWrapper);
        return (MenuItem)this;
    }
    
    public MenuItem setActionView(final int actionView) {
        ((SupportMenuItem)this.mWrappedObject).setActionView(actionView);
        final View actionView2 = ((SupportMenuItem)this.mWrappedObject).getActionView();
        if (actionView2 instanceof CollapsibleActionView) {
            ((SupportMenuItem)this.mWrappedObject).setActionView((View)new MenuItemWrapperICS$CollapsibleActionViewWrapper(actionView2));
        }
        return (MenuItem)this;
    }
    
    public MenuItem setActionView(final View view) {
        Object actionView = view;
        if (view instanceof CollapsibleActionView) {
            actionView = new MenuItemWrapperICS$CollapsibleActionViewWrapper(view);
        }
        ((SupportMenuItem)this.mWrappedObject).setActionView((View)actionView);
        return (MenuItem)this;
    }
    
    public MenuItem setAlphabeticShortcut(final char alphabeticShortcut) {
        ((SupportMenuItem)this.mWrappedObject).setAlphabeticShortcut(alphabeticShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean checkable) {
        ((SupportMenuItem)this.mWrappedObject).setCheckable(checkable);
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean checked) {
        ((SupportMenuItem)this.mWrappedObject).setChecked(checked);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean enabled) {
        ((SupportMenuItem)this.mWrappedObject).setEnabled(enabled);
        return (MenuItem)this;
    }
    
    public void setExclusiveCheckable(final boolean b) {
        try {
            if (this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = ((SupportMenuItem)this.mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, b);
        }
        catch (Exception ex) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", (Throwable)ex);
        }
    }
    
    public MenuItem setIcon(final int icon) {
        ((SupportMenuItem)this.mWrappedObject).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable icon) {
        ((SupportMenuItem)this.mWrappedObject).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent intent) {
        ((SupportMenuItem)this.mWrappedObject).setIntent(intent);
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char numericShortcut) {
        ((SupportMenuItem)this.mWrappedObject).setNumericShortcut(numericShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        final SupportMenuItem supportMenuItem = (SupportMenuItem)this.mWrappedObject;
        MenuItemWrapperICS$OnActionExpandListenerWrapper supportOnActionExpandListener;
        if (menuItem$OnActionExpandListener != null) {
            supportOnActionExpandListener = new MenuItemWrapperICS$OnActionExpandListenerWrapper(this, menuItem$OnActionExpandListener);
        }
        else {
            supportOnActionExpandListener = null;
        }
        supportMenuItem.setSupportOnActionExpandListener(supportOnActionExpandListener);
        return (MenuItem)this;
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        final SupportMenuItem supportMenuItem = (SupportMenuItem)this.mWrappedObject;
        Object onMenuItemClickListener;
        if (menuItem$OnMenuItemClickListener != null) {
            onMenuItemClickListener = new MenuItemWrapperICS$OnMenuItemClickListenerWrapper(this, menuItem$OnMenuItemClickListener);
        }
        else {
            onMenuItemClickListener = null;
        }
        supportMenuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)onMenuItemClickListener);
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char c, final char c2) {
        ((SupportMenuItem)this.mWrappedObject).setShortcut(c, c2);
        return (MenuItem)this;
    }
    
    public void setShowAsAction(final int showAsAction) {
        ((SupportMenuItem)this.mWrappedObject).setShowAsAction(showAsAction);
    }
    
    public MenuItem setShowAsActionFlags(final int showAsActionFlags) {
        ((SupportMenuItem)this.mWrappedObject).setShowAsActionFlags(showAsActionFlags);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final int title) {
        ((SupportMenuItem)this.mWrappedObject).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence title) {
        ((SupportMenuItem)this.mWrappedObject).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence titleCondensed) {
        ((SupportMenuItem)this.mWrappedObject).setTitleCondensed(titleCondensed);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean visible) {
        return ((SupportMenuItem)this.mWrappedObject).setVisible(visible);
    }
}
