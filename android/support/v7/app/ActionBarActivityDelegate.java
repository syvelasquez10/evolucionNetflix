// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v4.app.ActionBarDrawerToggle$Delegate;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.os.Build$VERSION;
import android.view.MenuInflater;
import android.support.v7.internal.app.WindowCallback;

abstract class ActionBarActivityDelegate
{
    private ActionBar mActionBar;
    final ActionBarActivity mActivity;
    final WindowCallback mDefaultWindowCallback;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private WindowCallback mWindowCallback;
    
    ActionBarActivityDelegate(final ActionBarActivity mActivity) {
        this.mDefaultWindowCallback = new ActionBarActivityDelegate$1(this);
        this.mActivity = mActivity;
        this.mWindowCallback = this.mDefaultWindowCallback;
    }
    
    static ActionBarActivityDelegate createDelegate(final ActionBarActivity actionBarActivity) {
        if (Build$VERSION.SDK_INT >= 11) {
            return new ActionBarActivityDelegateHC(actionBarActivity);
        }
        return new ActionBarActivityDelegateBase(actionBarActivity);
    }
    
    abstract void addContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    abstract ActionBar createSupportActionBar();
    
    abstract View createView(final String p0, final AttributeSet p1);
    
    final void destroy() {
        this.mIsDestroyed = true;
    }
    
    protected final Context getActionBarThemedContext() {
        Context themedContext = null;
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            themedContext = supportActionBar.getThemedContext();
        }
        Object mActivity;
        if ((mActivity = themedContext) == null) {
            mActivity = this.mActivity;
        }
        return (Context)mActivity;
    }
    
    final ActionBarDrawerToggle$Delegate getDrawerToggleDelegate() {
        return new ActionBarActivityDelegate$ActionBarDrawableToggleImpl(this, null);
    }
    
    MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(this.getActionBarThemedContext());
        }
        return this.mMenuInflater;
    }
    
    final ActionBar getSupportActionBar() {
        if (this.mHasActionBar && this.mActionBar == null) {
            this.mActionBar = this.createSupportActionBar();
        }
        return this.mActionBar;
    }
    
    final android.support.v7.app.ActionBarDrawerToggle$Delegate getV7DrawerToggleDelegate() {
        return new ActionBarActivityDelegate$ActionBarDrawableToggleImpl(this, null);
    }
    
    final WindowCallback getWindowCallback() {
        return this.mWindowCallback;
    }
    
    final boolean isDestroyed() {
        return this.mIsDestroyed;
    }
    
    abstract boolean onBackPressed();
    
    abstract void onConfigurationChanged(final Configuration p0);
    
    abstract void onContentChanged();
    
    void onCreate(final Bundle bundle) {
        final TypedArray obtainStyledAttributes = this.mActivity.obtainStyledAttributes(R$styleable.Theme);
        if (!obtainStyledAttributes.hasValue(R$styleable.Theme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        this.mHasActionBar = obtainStyledAttributes.getBoolean(R$styleable.Theme_windowActionBar, false);
        this.mOverlayActionBar = obtainStyledAttributes.getBoolean(R$styleable.Theme_windowActionBarOverlay, false);
        this.mOverlayActionMode = obtainStyledAttributes.getBoolean(R$styleable.Theme_windowActionModeOverlay, false);
        this.mIsFloating = obtainStyledAttributes.getBoolean(R$styleable.Theme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
    }
    
    abstract boolean onCreatePanelMenu(final int p0, final Menu p1);
    
    abstract View onCreatePanelView(final int p0);
    
    boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return false;
    }
    
    abstract boolean onKeyShortcut(final int p0, final KeyEvent p1);
    
    abstract boolean onMenuOpened(final int p0, final Menu p1);
    
    abstract void onPanelClosed(final int p0, final Menu p1);
    
    abstract void onPostResume();
    
    boolean onPrepareOptionsPanel(final View view, final Menu menu) {
        if (Build$VERSION.SDK_INT < 16) {
            return this.mActivity.onPrepareOptionsMenu(menu);
        }
        return this.mActivity.superOnPrepareOptionsPanel(view, menu);
    }
    
    abstract boolean onPreparePanel(final int p0, final View p1, final Menu p2);
    
    abstract void onStop();
    
    abstract void onTitleChanged(final CharSequence p0);
    
    abstract void setContentView(final int p0);
    
    abstract void setContentView(final View p0);
    
    abstract void setContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    protected final void setSupportActionBar(final ActionBar mActionBar) {
        this.mActionBar = mActionBar;
    }
    
    abstract void setSupportActionBar(final Toolbar p0);
    
    abstract void setSupportProgress(final int p0);
    
    abstract void setSupportProgressBarIndeterminate(final boolean p0);
    
    abstract void setSupportProgressBarIndeterminateVisibility(final boolean p0);
    
    abstract void setSupportProgressBarVisibility(final boolean p0);
    
    final void setWindowCallback(final WindowCallback mWindowCallback) {
        if (mWindowCallback == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        this.mWindowCallback = mWindowCallback;
    }
    
    abstract ActionMode startSupportActionMode(final ActionMode$Callback p0);
    
    abstract ActionMode startSupportActionModeFromWindow(final ActionMode$Callback p0);
    
    abstract void supportInvalidateOptionsMenu();
    
    abstract boolean supportRequestWindowFeature(final int p0);
}
