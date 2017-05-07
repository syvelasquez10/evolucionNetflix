// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.internal.widget.AdapterViewCompat$OnItemSelectedListener;
import android.support.v7.app.ActionBar$OnNavigationListener;
import android.widget.SpinnerAdapter;
import android.support.v7.internal.view.menu.y;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.i;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar$Tab;
import android.support.v7.internal.view.menu.j;
import android.support.v7.internal.view.menu.z;
import android.view.ViewGroup;
import android.view.View;
import android.view.Menu;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.view.Window;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.support.v7.internal.view.menu.g;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.app.ActionBar;

public class ToolbarActionBar extends ActionBar
{
    private DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private g mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final Toolbar$OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<ActionBar$OnMenuVisibilityListener> mMenuVisibilityListeners;
    private Toolbar mToolbar;
    private boolean mToolbarMenuPrepared;
    private Window mWindow;
    private WindowCallback mWindowCallback;
    
    public ToolbarActionBar(final Toolbar mToolbar, final CharSequence windowTitle, final Window mWindow, final WindowCallback windowCallback) {
        this.mMenuVisibilityListeners = new ArrayList<ActionBar$OnMenuVisibilityListener>();
        this.mMenuInvalidator = new ToolbarActionBar$1(this);
        this.mMenuClicker = new ToolbarActionBar$2(this);
        this.mToolbar = mToolbar;
        this.mDecorToolbar = new ToolbarWidgetWrapper(mToolbar, false);
        this.mWindowCallback = new ToolbarActionBar$ToolbarCallbackWrapper(this, windowCallback);
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        mToolbar.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(windowTitle);
        this.mWindow = mWindow;
    }
    
    private View getListMenuView(final Menu menu) {
        if (menu != null && this.mListMenuPresenter != null && this.mListMenuPresenter.a().getCount() > 0) {
            return (View)this.mListMenuPresenter.getMenuView(this.mToolbar);
        }
        return null;
    }
    
    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mToolbar.setMenuCallbacks(new ToolbarActionBar$ActionMenuPresenterCallback(this, null), new ToolbarActionBar$MenuBuilderCallback(this, null));
            this.mMenuCallbackSet = true;
        }
        return this.mToolbar.getMenu();
    }
    
    @Override
    public void addOnMenuVisibilityListener(final ActionBar$OnMenuVisibilityListener actionBar$OnMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(actionBar$OnMenuVisibilityListener);
    }
    
    @Override
    public void addTab(final ActionBar$Tab actionBar$Tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void addTab(final ActionBar$Tab actionBar$Tab, final int n) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void addTab(final ActionBar$Tab actionBar$Tab, final int n, final boolean b) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void addTab(final ActionBar$Tab actionBar$Tab, final boolean b) {
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
    public ActionBar$Tab getSelectedTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }
    
    @Override
    public ActionBar$Tab getTabAt(final int n) {
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
    public ActionBar$Tab newTab() {
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
        final Menu menu = this.getMenu();
        Label_0075: {
            if (!(menu instanceof i)) {
                break Label_0075;
            }
            i i = (i)menu;
            while (true) {
                if (i != null) {
                    i.h();
                }
                try {
                    menu.clear();
                    if (!this.mWindowCallback.onCreatePanelMenu(0, menu) || !this.mWindowCallback.onPreparePanel(0, null, menu)) {
                        menu.clear();
                    }
                    return;
                    i = null;
                    continue;
                }
                finally {
                    if (i != null) {
                        i.i();
                    }
                }
                break;
            }
        }
    }
    
    @Override
    public void removeAllTabs() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void removeOnMenuVisibilityListener(final ActionBar$OnMenuVisibilityListener actionBar$OnMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(actionBar$OnMenuVisibilityListener);
    }
    
    @Override
    public void removeTab(final ActionBar$Tab actionBar$Tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void removeTabAt(final int n) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }
    
    @Override
    public void selectTab(final ActionBar$Tab actionBar$Tab) {
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
        this.setCustomView(view, new ActionBar$LayoutParams(-2, -2));
    }
    
    @Override
    public void setCustomView(final View customView, final ActionBar$LayoutParams layoutParams) {
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
        this.mDecorToolbar.setDisplayOptions((this.mDecorToolbar.getDisplayOptions() & ~n2) | (n & n2));
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
    
    public void setListMenuPresenter(final g mListMenuPresenter) {
        final Menu menu = this.getMenu();
        if (menu instanceof i) {
            final i i = (i)menu;
            if (this.mListMenuPresenter != null) {
                this.mListMenuPresenter.setCallback(null);
                i.b(this.mListMenuPresenter);
            }
            if ((this.mListMenuPresenter = mListMenuPresenter) != null) {
                mListMenuPresenter.setCallback(new ToolbarActionBar$PanelMenuPresenterCallback(this, null));
                i.a(mListMenuPresenter);
            }
        }
    }
    
    @Override
    public void setListNavigationCallbacks(final SpinnerAdapter spinnerAdapter, final ActionBar$OnNavigationListener actionBar$OnNavigationListener) {
        this.mDecorToolbar.setDropdownParams(spinnerAdapter, new NavItemSelectedListener(actionBar$OnNavigationListener));
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
    public ActionMode startActionMode(final ActionMode$Callback actionMode$Callback) {
        return this.mWindowCallback.startActionMode(actionMode$Callback);
    }
}
