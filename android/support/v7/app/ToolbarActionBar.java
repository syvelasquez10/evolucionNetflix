// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ViewGroup$LayoutParams;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.content.res.Resources$Theme;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.content.Context;
import android.support.v7.appcompat.R$layout;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Window$Callback;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.widget.DecorToolbar;

class ToolbarActionBar extends ActionBar
{
    DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private ListMenuPresenter mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final Toolbar$OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<ActionBar$OnMenuVisibilityListener> mMenuVisibilityListeners;
    boolean mToolbarMenuPrepared;
    Window$Callback mWindowCallback;
    
    public ToolbarActionBar(final Toolbar toolbar, final CharSequence windowTitle, final Window$Callback window$Callback) {
        this.mMenuVisibilityListeners = new ArrayList<ActionBar$OnMenuVisibilityListener>();
        this.mMenuInvalidator = new ToolbarActionBar$1(this);
        this.mMenuClicker = new ToolbarActionBar$2(this);
        this.mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        this.mWindowCallback = (Window$Callback)new ToolbarActionBar$ToolbarCallbackWrapper(this, window$Callback);
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        toolbar.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(windowTitle);
    }
    
    private void ensureListMenuPresenter(final Menu menu) {
        if (this.mListMenuPresenter == null && menu instanceof MenuBuilder) {
            final MenuBuilder menuBuilder = (MenuBuilder)menu;
            final Context context = this.mDecorToolbar.getContext();
            final TypedValue typedValue = new TypedValue();
            final Resources$Theme theme = context.getResources().newTheme();
            theme.setTo(context.getTheme());
            theme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            theme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            else {
                theme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            final ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
            ((Context)contextThemeWrapper).getTheme().setTo(theme);
            (this.mListMenuPresenter = new ListMenuPresenter((Context)contextThemeWrapper, R$layout.abc_list_menu_item_layout)).setCallback(new ToolbarActionBar$PanelMenuPresenterCallback(this));
            menuBuilder.addMenuPresenter(this.mListMenuPresenter);
        }
    }
    
    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mDecorToolbar.setMenuCallbacks(new ToolbarActionBar$ActionMenuPresenterCallback(this), new ToolbarActionBar$MenuBuilderCallback(this));
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }
    
    @Override
    public boolean collapseActionView() {
        if (this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
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
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }
    
    View getListMenuView(final Menu menu) {
        this.ensureListMenuPresenter(menu);
        if (menu != null && this.mListMenuPresenter != null && this.mListMenuPresenter.getAdapter().getCount() > 0) {
            return (View)this.mListMenuPresenter.getMenuView(this.mDecorToolbar.getViewGroup());
        }
        return null;
    }
    
    @Override
    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }
    
    public Window$Callback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }
    
    @Override
    public void hide() {
        this.mDecorToolbar.setVisibility(8);
    }
    
    @Override
    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation((View)this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }
    
    @Override
    public boolean isShowing() {
        return this.mDecorToolbar.getVisibility() == 0;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
    
    @Override
    void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }
    
    @Override
    public boolean onKeyShortcut(final int n, final KeyEvent keyEvent) {
        final Menu menu = this.getMenu();
        if (menu != null) {
            int deviceId;
            if (keyEvent != null) {
                deviceId = keyEvent.getDeviceId();
            }
            else {
                deviceId = -1;
            }
            menu.setQwertyMode(KeyCharacterMap.load(deviceId).getKeyboardType() != 1);
            menu.performShortcut(n, keyEvent, 0);
        }
        return true;
    }
    
    void populateOptionsMenu() {
        final Menu menu = this.getMenu();
        Label_0075: {
            if (!(menu instanceof MenuBuilder)) {
                break Label_0075;
            }
            MenuBuilder menuBuilder = (MenuBuilder)menu;
            while (true) {
                if (menuBuilder != null) {
                    menuBuilder.stopDispatchingItemsChanged();
                }
                try {
                    menu.clear();
                    if (!this.mWindowCallback.onCreatePanelMenu(0, menu) || !this.mWindowCallback.onPreparePanel(0, (View)null, menu)) {
                        menu.clear();
                    }
                    return;
                    menuBuilder = null;
                    continue;
                }
                finally {
                    if (menuBuilder != null) {
                        menuBuilder.startDispatchingItemsChanged();
                    }
                }
                break;
            }
        }
    }
    
    public boolean requestFocus() {
        final ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
        if (viewGroup != null && !viewGroup.hasFocus()) {
            viewGroup.requestFocus();
            return true;
        }
        return false;
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        this.mDecorToolbar.setBackgroundDrawable(backgroundDrawable);
    }
    
    @Override
    public void setCustomView(final View customView, final ActionBar$LayoutParams layoutParams) {
        if (customView != null) {
            customView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
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
        ViewCompat.setElevation((View)this.mDecorToolbar.getViewGroup(), n);
    }
    
    @Override
    public void setHomeActionContentDescription(final int navigationContentDescription) {
        this.mDecorToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setHomeButtonEnabled(final boolean b) {
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
    public void setShowHideAnimationEnabled(final boolean b) {
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.mDecorToolbar.setSubtitle(subtitle);
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
        this.mDecorToolbar.setVisibility(0);
    }
}
