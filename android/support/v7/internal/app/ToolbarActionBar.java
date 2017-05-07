// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.internal.view.menu.x;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.i;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.content.Context;
import android.support.v7.internal.view.menu.j;
import android.support.v7.internal.view.menu.y;
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
            return (View)this.mListMenuPresenter.a(this.mToolbar);
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
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }
    
    @Override
    public Context getThemedContext() {
        return this.mToolbar.getContext();
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
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
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
                    i.g();
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
                        i.h();
                    }
                }
                break;
            }
        }
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        this.mToolbar.setBackgroundDrawable(backgroundDrawable);
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
    public void setHomeButtonEnabled(final boolean b) {
    }
    
    public void setListMenuPresenter(final g mListMenuPresenter) {
        final Menu menu = this.getMenu();
        if (menu instanceof i) {
            final i i = (i)menu;
            if (this.mListMenuPresenter != null) {
                this.mListMenuPresenter.a((y)null);
                i.b(this.mListMenuPresenter);
            }
            if ((this.mListMenuPresenter = mListMenuPresenter) != null) {
                mListMenuPresenter.a(new ToolbarActionBar$PanelMenuPresenterCallback(this, null));
                i.a(mListMenuPresenter);
            }
        }
    }
    
    @Override
    public void setLogo(final int logo) {
        this.mDecorToolbar.setLogo(logo);
    }
    
    @Override
    public void setShowHideAnimationEnabled(final boolean b) {
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
