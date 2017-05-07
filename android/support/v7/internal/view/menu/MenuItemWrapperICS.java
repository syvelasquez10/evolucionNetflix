// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.widget.FrameLayout;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem$OnActionExpandListener;
import android.util.Log;
import android.support.v7.view.CollapsibleActionView;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v4.view.ActionProvider;
import java.lang.reflect.Method;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;

public class MenuItemWrapperICS extends BaseMenuWrapper<MenuItem> implements SupportMenuItem
{
    static final String LOG_TAG = "MenuItemWrapper";
    private final boolean mEmulateProviderVisibilityOverride;
    private boolean mLastRequestVisible;
    private Method mSetExclusiveCheckableMethod;
    
    MenuItemWrapperICS(final MenuItem menuItem) {
        this(menuItem, true);
    }
    
    MenuItemWrapperICS(final MenuItem menuItem, final boolean mEmulateProviderVisibilityOverride) {
        super(menuItem);
        this.mLastRequestVisible = menuItem.isVisible();
        this.mEmulateProviderVisibilityOverride = mEmulateProviderVisibilityOverride;
    }
    
    final boolean checkActionProviderOverrideVisibility() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.mLastRequestVisible) {
            final ActionProvider supportActionProvider = this.getSupportActionProvider();
            b2 = b;
            if (supportActionProvider != null) {
                b2 = b;
                if (supportActionProvider.overridesItemVisibility()) {
                    b2 = b;
                    if (!supportActionProvider.isVisible()) {
                        this.wrappedSetVisible(false);
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public boolean collapseActionView() {
        return ((MenuItem)this.mWrappedObject).collapseActionView();
    }
    
    ActionProviderWrapper createActionProviderWrapper(final ActionProvider actionProvider) {
        return new ActionProviderWrapper(actionProvider);
    }
    
    @Override
    public boolean expandActionView() {
        return ((MenuItem)this.mWrappedObject).expandActionView();
    }
    
    public android.view.ActionProvider getActionProvider() {
        return ((MenuItem)this.mWrappedObject).getActionProvider();
    }
    
    @Override
    public View getActionView() {
        View view2;
        final View view = view2 = ((MenuItem)this.mWrappedObject).getActionView();
        if (view instanceof CollapsibleActionViewWrapper) {
            view2 = ((CollapsibleActionViewWrapper)view).getWrappedView();
        }
        return view2;
    }
    
    public char getAlphabeticShortcut() {
        return ((MenuItem)this.mWrappedObject).getAlphabeticShortcut();
    }
    
    public int getGroupId() {
        return ((MenuItem)this.mWrappedObject).getGroupId();
    }
    
    public Drawable getIcon() {
        return ((MenuItem)this.mWrappedObject).getIcon();
    }
    
    public Intent getIntent() {
        return ((MenuItem)this.mWrappedObject).getIntent();
    }
    
    public int getItemId() {
        return ((MenuItem)this.mWrappedObject).getItemId();
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return ((MenuItem)this.mWrappedObject).getMenuInfo();
    }
    
    public char getNumericShortcut() {
        return ((MenuItem)this.mWrappedObject).getNumericShortcut();
    }
    
    public int getOrder() {
        return ((MenuItem)this.mWrappedObject).getOrder();
    }
    
    public SubMenu getSubMenu() {
        return this.getSubMenuWrapper(((MenuItem)this.mWrappedObject).getSubMenu());
    }
    
    @Override
    public ActionProvider getSupportActionProvider() {
        final ActionProviderWrapper actionProviderWrapper = (ActionProviderWrapper)((MenuItem)this.mWrappedObject).getActionProvider();
        if (actionProviderWrapper != null) {
            return actionProviderWrapper.mInner;
        }
        return null;
    }
    
    public CharSequence getTitle() {
        return ((MenuItem)this.mWrappedObject).getTitle();
    }
    
    public CharSequence getTitleCondensed() {
        return ((MenuItem)this.mWrappedObject).getTitleCondensed();
    }
    
    public boolean hasSubMenu() {
        return ((MenuItem)this.mWrappedObject).hasSubMenu();
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return ((MenuItem)this.mWrappedObject).isActionViewExpanded();
    }
    
    public boolean isCheckable() {
        return ((MenuItem)this.mWrappedObject).isCheckable();
    }
    
    public boolean isChecked() {
        return ((MenuItem)this.mWrappedObject).isChecked();
    }
    
    public boolean isEnabled() {
        return ((MenuItem)this.mWrappedObject).isEnabled();
    }
    
    public boolean isVisible() {
        return ((MenuItem)this.mWrappedObject).isVisible();
    }
    
    public MenuItem setActionProvider(final android.view.ActionProvider actionProvider) {
        ((MenuItem)this.mWrappedObject).setActionProvider(actionProvider);
        if (actionProvider != null && this.mEmulateProviderVisibilityOverride) {
            this.checkActionProviderOverrideVisibility();
        }
        return (MenuItem)this;
    }
    
    @Override
    public MenuItem setActionView(final int actionView) {
        ((MenuItem)this.mWrappedObject).setActionView(actionView);
        final View actionView2 = ((MenuItem)this.mWrappedObject).getActionView();
        if (actionView2 instanceof CollapsibleActionView) {
            ((MenuItem)this.mWrappedObject).setActionView((View)new CollapsibleActionViewWrapper(actionView2));
        }
        return (MenuItem)this;
    }
    
    @Override
    public MenuItem setActionView(final View view) {
        Object actionView = view;
        if (view instanceof CollapsibleActionView) {
            actionView = new CollapsibleActionViewWrapper(view);
        }
        ((MenuItem)this.mWrappedObject).setActionView((View)actionView);
        return (MenuItem)this;
    }
    
    public MenuItem setAlphabeticShortcut(final char alphabeticShortcut) {
        ((MenuItem)this.mWrappedObject).setAlphabeticShortcut(alphabeticShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean checkable) {
        ((MenuItem)this.mWrappedObject).setCheckable(checkable);
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean checked) {
        ((MenuItem)this.mWrappedObject).setChecked(checked);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean enabled) {
        ((MenuItem)this.mWrappedObject).setEnabled(enabled);
        return (MenuItem)this;
    }
    
    public void setExclusiveCheckable(final boolean b) {
        try {
            if (this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = ((MenuItem)this.mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, b);
        }
        catch (Exception ex) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", (Throwable)ex);
        }
    }
    
    public MenuItem setIcon(final int icon) {
        ((MenuItem)this.mWrappedObject).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable icon) {
        ((MenuItem)this.mWrappedObject).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent intent) {
        ((MenuItem)this.mWrappedObject).setIntent(intent);
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char numericShortcut) {
        ((MenuItem)this.mWrappedObject).setNumericShortcut(numericShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener onActionExpandListener) {
        ((MenuItem)this.mWrappedObject).setOnActionExpandListener(onActionExpandListener);
        return (MenuItem)this;
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        final MenuItem menuItem = (MenuItem)this.mWrappedObject;
        Object onMenuItemClickListener;
        if (menuItem$OnMenuItemClickListener != null) {
            onMenuItemClickListener = new OnMenuItemClickListenerWrapper(menuItem$OnMenuItemClickListener);
        }
        else {
            onMenuItemClickListener = null;
        }
        menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)onMenuItemClickListener);
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char c, final char c2) {
        ((MenuItem)this.mWrappedObject).setShortcut(c, c2);
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int showAsAction) {
        ((MenuItem)this.mWrappedObject).setShowAsAction(showAsAction);
    }
    
    @Override
    public MenuItem setShowAsActionFlags(final int showAsActionFlags) {
        ((MenuItem)this.mWrappedObject).setShowAsActionFlags(showAsActionFlags);
        return (MenuItem)this;
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final ActionProvider actionProvider) {
        final MenuItem menuItem = (MenuItem)this.mWrappedObject;
        ActionProviderWrapper actionProviderWrapper;
        if (actionProvider != null) {
            actionProviderWrapper = this.createActionProviderWrapper(actionProvider);
        }
        else {
            actionProviderWrapper = null;
        }
        menuItem.setActionProvider((android.view.ActionProvider)actionProviderWrapper);
        return this;
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        final MenuItem menuItem = (MenuItem)this.mWrappedObject;
        Object onActionExpandListener2;
        if (onActionExpandListener != null) {
            onActionExpandListener2 = new OnActionExpandListenerWrapper(onActionExpandListener);
        }
        else {
            onActionExpandListener2 = null;
        }
        menuItem.setOnActionExpandListener((MenuItem$OnActionExpandListener)onActionExpandListener2);
        return null;
    }
    
    public MenuItem setTitle(final int title) {
        ((MenuItem)this.mWrappedObject).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence title) {
        ((MenuItem)this.mWrappedObject).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence titleCondensed) {
        ((MenuItem)this.mWrappedObject).setTitleCondensed(titleCondensed);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean mLastRequestVisible) {
        if (this.mEmulateProviderVisibilityOverride) {
            this.mLastRequestVisible = mLastRequestVisible;
            if (this.checkActionProviderOverrideVisibility()) {
                return (MenuItem)this;
            }
        }
        return this.wrappedSetVisible(mLastRequestVisible);
    }
    
    final MenuItem wrappedSetVisible(final boolean visible) {
        return ((MenuItem)this.mWrappedObject).setVisible(visible);
    }
    
    class ActionProviderWrapper extends android.view.ActionProvider
    {
        final ActionProvider mInner;
        
        public ActionProviderWrapper(final ActionProvider mInner) {
            super(mInner.getContext());
            this.mInner = mInner;
            if (MenuItemWrapperICS.this.mEmulateProviderVisibilityOverride) {
                this.mInner.setVisibilityListener((ActionProvider.VisibilityListener)new ActionProvider.VisibilityListener() {
                    @Override
                    public void onActionProviderVisibilityChanged(final boolean b) {
                        if (ActionProviderWrapper.this.mInner.overridesItemVisibility() && MenuItemWrapperICS.this.mLastRequestVisible) {
                            MenuItemWrapperICS.this.wrappedSetVisible(b);
                        }
                    }
                });
            }
        }
        
        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }
        
        public View onCreateActionView() {
            if (MenuItemWrapperICS.this.mEmulateProviderVisibilityOverride) {
                MenuItemWrapperICS.this.checkActionProviderOverrideVisibility();
            }
            return this.mInner.onCreateActionView();
        }
        
        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }
        
        public void onPrepareSubMenu(final SubMenu subMenu) {
            this.mInner.onPrepareSubMenu(MenuItemWrapperICS.this.getSubMenuWrapper(subMenu));
        }
    }
    
    static class CollapsibleActionViewWrapper extends FrameLayout implements android.view.CollapsibleActionView
    {
        final CollapsibleActionView mWrappedView;
        
        CollapsibleActionViewWrapper(final View view) {
            super(view.getContext());
            this.mWrappedView = (CollapsibleActionView)view;
            this.addView(view);
        }
        
        View getWrappedView() {
            return (View)this.mWrappedView;
        }
        
        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }
        
        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }
    
    private class OnActionExpandListenerWrapper extends BaseWrapper<MenuItemCompat.OnActionExpandListener> implements MenuItem$OnActionExpandListener
    {
        OnActionExpandListenerWrapper(final MenuItemCompat.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }
        
        public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
            return ((MenuItemCompat.OnActionExpandListener)this.mWrappedObject).onMenuItemActionCollapse((MenuItem)MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
        
        public boolean onMenuItemActionExpand(final MenuItem menuItem) {
            return ((MenuItemCompat.OnActionExpandListener)this.mWrappedObject).onMenuItemActionExpand((MenuItem)MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
    }
    
    private class OnMenuItemClickListenerWrapper extends BaseWrapper<MenuItem$OnMenuItemClickListener> implements MenuItem$OnMenuItemClickListener
    {
        OnMenuItemClickListenerWrapper(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
            super(menuItem$OnMenuItemClickListener);
        }
        
        public boolean onMenuItemClick(final MenuItem menuItem) {
            return ((MenuItem$OnMenuItemClickListener)this.mWrappedObject).onMenuItemClick((MenuItem)MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
    }
}
