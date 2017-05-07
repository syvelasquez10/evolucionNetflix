// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.widget.WindowCallbackWrapper;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.widget.SpinnerAdapter;
import android.view.ViewGroup$LayoutParams;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.view.ViewGroup;
import android.view.View;
import android.view.Menu;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.view.MenuItem;
import android.view.Window;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.app.ActionBar;

public class ToolbarActionBar extends ActionBar
{
    private DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private ListMenuPresenter mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<OnMenuVisibilityListener> mMenuVisibilityListeners;
    private Toolbar mToolbar;
    private boolean mToolbarMenuPrepared;
    private Window mWindow;
    private WindowCallback mWindowCallback;
    
    public ToolbarActionBar(final Toolbar mToolbar, final CharSequence windowTitle, final Window mWindow, final WindowCallback windowCallback) {
        this.mMenuVisibilityListeners = new ArrayList<OnMenuVisibilityListener>();
        this.mMenuInvalidator = new Runnable() {
            @Override
            public void run() {
                ToolbarActionBar.this.populateOptionsMenu();
            }
        };
        this.mMenuClicker = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem menuItem) {
                return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, menuItem);
            }
        };
        this.mToolbar = mToolbar;
        this.mDecorToolbar = new ToolbarWidgetWrapper(mToolbar, false);
        this.mWindowCallback = new ToolbarCallbackWrapper(windowCallback);
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        mToolbar.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(windowTitle);
        this.mWindow = mWindow;
    }
    
    private View getListMenuView(final Menu menu) {
        if (menu != null && this.mListMenuPresenter != null && this.mListMenuPresenter.getAdapter().getCount() > 0) {
            return (View)this.mListMenuPresenter.getMenuView(this.mToolbar);
        }
        return null;
    }
    
    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.mToolbar.getMenu();
    }
    
    @Override
    public void addOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }
    
    @Override
    public void addTab(final Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void addTab(final Tab tab, final int n) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void addTab(final Tab tab, final int n, final boolean b) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void addTab(final Tab tab, final boolean b) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public boolean collapseActionView() {
        if (this.mToolbar.hasExpandedActionView()) {
            this.mToolbar.collapseActionView();
            return true;
        }
        return false;
    }
    
    @Override
    public void dispatchMenuVisibilityChanged(final boolean mLastMenuVisibility) {
        if (mLastMenuVisibility != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = mLastMenuVisibility;
            for (int size = this.mMenuVisibilityListeners.size(), i = 0; i < size; ++i) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(mLastMenuVisibility);
            }
        }
    }
    
    @Override
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }
    
    @Override
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }
    
    @Override
    public float getElevation() {
        return ViewCompat.getElevation((View)this.mToolbar);
    }
    
    @Override
    public int getHeight() {
        return this.mToolbar.getHeight();
    }
    
    @Override
    public int getNavigationItemCount() {
        return 0;
    }
    
    @Override
    public int getNavigationMode() {
        return 0;
    }
    
    @Override
    public int getSelectedNavigationIndex() {
        return -1;
    }
    
    @Override
    public Tab getSelectedTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }
    
    @Override
    public Tab getTabAt(final int n) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public int getTabCount() {
        return 0;
    }
    
    @Override
    public Context getThemedContext() {
        return this.mToolbar.getContext();
    }
    
    @Override
    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }
    
    public WindowCallback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }
    
    @Override
    public void hide() {
        this.mToolbar.setVisibility(8);
    }
    
    @Override
    public boolean invalidateOptionsMenu() {
        this.mToolbar.removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation((View)this.mToolbar, this.mMenuInvalidator);
        return true;
    }
    
    @Override
    public boolean isShowing() {
        return this.mToolbar.getVisibility() == 0;
    }
    
    @Override
    public boolean isTitleTruncated() {
        return super.isTitleTruncated();
    }
    
    @Override
    public Tab newTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
    
    @Override
    public boolean onMenuKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            this.openOptionsMenu();
        }
        return true;
    }
    
    @Override
    public boolean openOptionsMenu() {
        return this.mToolbar.showOverflowMenu();
    }
    
    void populateOptionsMenu() {
        MenuBuilder menuBuilder = null;
        final Menu menu = this.getMenu();
        if (menu instanceof MenuBuilder) {
            menuBuilder = (MenuBuilder)menu;
        }
        if (menuBuilder != null) {
            menuBuilder.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!this.mWindowCallback.onCreatePanelMenu(0, menu) || !this.mWindowCallback.onPreparePanel(0, null, menu)) {
                menu.clear();
            }
        }
        finally {
            if (menuBuilder != null) {
                menuBuilder.startDispatchingItemsChanged();
            }
        }
    }
    
    @Override
    public void removeAllTabs() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void removeOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }
    
    @Override
    public void removeTab(final Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void removeTabAt(final int n) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void selectTab(final Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void setBackgroundDrawable(@Nullable final Drawable backgroundDrawable) {
        this.mToolbar.setBackgroundDrawable(backgroundDrawable);
    }
    
    @Override
    public void setCustomView(final int n) {
        this.setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(n, (ViewGroup)this.mToolbar, false));
    }
    
    @Override
    public void setCustomView(final View view) {
        this.setCustomView(view, new LayoutParams(-2, -2));
    }
    
    @Override
    public void setCustomView(final View customView, final LayoutParams layoutParams) {
        customView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mDecorToolbar.setCustomView(customView);
    }
    
    @Override
    public void setDefaultDisplayHomeAsUpEnabled(final boolean b) {
    }
    
    @Override
    public void setDisplayHomeAsUpEnabled(final boolean b) {
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 4);
    }
    
    @Override
    public void setDisplayOptions(final int n) {
        this.setDisplayOptions(n, -1);
    }
    
    @Override
    public void setDisplayOptions(final int n, final int n2) {
        this.mDecorToolbar.setDisplayOptions((n & n2) | (~n2 & this.mDecorToolbar.getDisplayOptions()));
    }
    
    @Override
    public void setDisplayShowCustomEnabled(final boolean b) {
        int n;
        if (b) {
            n = 16;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 16);
    }
    
    @Override
    public void setDisplayShowHomeEnabled(final boolean b) {
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 2);
    }
    
    @Override
    public void setDisplayShowTitleEnabled(final boolean b) {
        int n;
        if (b) {
            n = 8;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 8);
    }
    
    @Override
    public void setDisplayUseLogoEnabled(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 1);
    }
    
    @Override
    public void setElevation(final float n) {
        ViewCompat.setElevation((View)this.mToolbar, n);
    }
    
    @Override
    public void setHomeActionContentDescription(final int navigationContentDescription) {
        this.mDecorToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setHomeActionContentDescription(final CharSequence navigationContentDescription) {
        this.mDecorToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setHomeAsUpIndicator(final int navigationIcon) {
        this.mToolbar.setNavigationIcon(navigationIcon);
    }
    
    @Override
    public void setHomeAsUpIndicator(final Drawable navigationIcon) {
        this.mToolbar.setNavigationIcon(navigationIcon);
    }
    
    @Override
    public void setHomeButtonEnabled(final boolean b) {
    }
    
    @Override
    public void setIcon(final int icon) {
        this.mDecorToolbar.setIcon(icon);
    }
    
    @Override
    public void setIcon(final Drawable icon) {
        this.mDecorToolbar.setIcon(icon);
    }
    
    public void setListMenuPresenter(final ListMenuPresenter mListMenuPresenter) {
        final Menu menu = this.getMenu();
        if (menu instanceof MenuBuilder) {
            final MenuBuilder menuBuilder = (MenuBuilder)menu;
            if (this.mListMenuPresenter != null) {
                this.mListMenuPresenter.setCallback(null);
                menuBuilder.removeMenuPresenter(this.mListMenuPresenter);
            }
            if ((this.mListMenuPresenter = mListMenuPresenter) != null) {
                mListMenuPresenter.setCallback(new PanelMenuPresenterCallback());
                menuBuilder.addMenuPresenter(mListMenuPresenter);
            }
        }
    }
    
    @Override
    public void setListNavigationCallbacks(final SpinnerAdapter spinnerAdapter, final OnNavigationListener onNavigationListener) {
        this.mDecorToolbar.setDropdownParams(spinnerAdapter, new NavItemSelectedListener(onNavigationListener));
    }
    
    @Override
    public void setLogo(final int logo) {
        this.mDecorToolbar.setLogo(logo);
    }
    
    @Override
    public void setLogo(final Drawable logo) {
        this.mDecorToolbar.setLogo(logo);
    }
    
    @Override
    public void setNavigationMode(final int navigationMode) {
        if (navigationMode == 2) {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        }
        this.mDecorToolbar.setNavigationMode(navigationMode);
    }
    
    @Override
    public void setSelectedNavigationItem(final int dropdownSelectedPosition) {
        switch (this.mDecorToolbar.getNavigationMode()) {
            default: {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            case 1: {
                this.mDecorToolbar.setDropdownSelectedPosition(dropdownSelectedPosition);
            }
        }
    }
    
    @Override
    public void setShowHideAnimationEnabled(final boolean b) {
    }
    
    @Override
    public void setSplitBackgroundDrawable(final Drawable drawable) {
    }
    
    @Override
    public void setStackedBackgroundDrawable(final Drawable drawable) {
    }
    
    @Override
    public void setSubtitle(final int n) {
        final DecorToolbar mDecorToolbar = this.mDecorToolbar;
        CharSequence text;
        if (n != 0) {
            text = this.mDecorToolbar.getContext().getText(n);
        }
        else {
            text = null;
        }
        mDecorToolbar.setSubtitle(text);
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.mDecorToolbar.setSubtitle(subtitle);
    }
    
    @Override
    public void setTitle(final int n) {
        final DecorToolbar mDecorToolbar = this.mDecorToolbar;
        CharSequence text;
        if (n != 0) {
            text = this.mDecorToolbar.getContext().getText(n);
        }
        else {
            text = null;
        }
        mDecorToolbar.setTitle(text);
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }
    
    @Override
    public void setWindowTitle(final CharSequence windowTitle) {
        this.mDecorToolbar.setWindowTitle(windowTitle);
    }
    
    @Override
    public void show() {
        this.mToolbar.setVisibility(0);
    }
    
    @Override
    public ActionMode startActionMode(final ActionMode.Callback callback) {
        return this.mWindowCallback.startActionMode(callback);
    }
    
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback
    {
        private boolean mClosingActionMenu;
        
        @Override
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
            if (this.mClosingActionMenu) {
                return;
            }
            this.mClosingActionMenu = true;
            ToolbarActionBar.this.mToolbar.dismissPopupMenus();
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(8, (Menu)menuBuilder);
            }
            this.mClosingActionMenu = false;
        }
        
        @Override
        public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(8, (Menu)menuBuilder);
                return true;
            }
            return false;
        }
    }
    
    private final class MenuBuilderCallback implements MenuBuilder.Callback
    {
        @Override
        public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public void onMenuModeChange(final MenuBuilder menuBuilder) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                if (ToolbarActionBar.this.mToolbar.isOverflowMenuShowing()) {
                    ToolbarActionBar.this.mWindowCallback.onPanelClosed(8, (Menu)menuBuilder);
                }
                else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, (Menu)menuBuilder)) {
                    ToolbarActionBar.this.mWindowCallback.onMenuOpened(8, (Menu)menuBuilder);
                }
            }
        }
    }
    
    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback
    {
        @Override
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(0, (Menu)menuBuilder);
            }
            ToolbarActionBar.this.mWindow.closePanel(0);
        }
        
        @Override
        public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
            if (menuBuilder == null && ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(0, (Menu)menuBuilder);
            }
            return true;
        }
    }
    
    private class ToolbarCallbackWrapper extends WindowCallbackWrapper
    {
        public ToolbarCallbackWrapper(final WindowCallback windowCallback) {
            super(windowCallback);
        }
        
        @Override
        public View onCreatePanelView(final int n) {
            switch (n) {
                case 0: {
                    if (!ToolbarActionBar.this.mToolbarMenuPrepared) {
                        ToolbarActionBar.this.populateOptionsMenu();
                        ToolbarActionBar.this.mToolbar.removeCallbacks(ToolbarActionBar.this.mMenuInvalidator);
                    }
                    if (!ToolbarActionBar.this.mToolbarMenuPrepared || ToolbarActionBar.this.mWindowCallback == null) {
                        break;
                    }
                    final Menu access$500 = ToolbarActionBar.this.getMenu();
                    if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(n, null, access$500) && ToolbarActionBar.this.mWindowCallback.onMenuOpened(n, access$500)) {
                        return ToolbarActionBar.this.getListMenuView(access$500);
                    }
                    break;
                }
            }
            return super.onCreatePanelView(n);
        }
        
        @Override
        public boolean onPreparePanel(final int n, final View view, final Menu menu) {
            final boolean onPreparePanel = super.onPreparePanel(n, view, menu);
            if (onPreparePanel && !ToolbarActionBar.this.mToolbarMenuPrepared) {
                ToolbarActionBar.this.mDecorToolbar.setMenuPrepared();
                ToolbarActionBar.this.mToolbarMenuPrepared = true;
            }
            return onPreparePanel;
        }
    }
}
