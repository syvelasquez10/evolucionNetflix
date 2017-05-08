// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.support.v7.view.SupportMenuInflater;
import android.view.KeyEvent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.support.v7.widget.TintTypedArray;
import android.os.Build$VERSION;
import android.view.Window;
import android.view.MenuInflater;
import android.content.Context;
import android.view.Window$Callback;

abstract class AppCompatDelegateImplBase extends AppCompatDelegate
{
    private static final boolean SHOULD_INSTALL_EXCEPTION_HANDLER;
    private static boolean sInstalledExceptionHandler;
    private static final int[] sWindowBackgroundStyleable;
    ActionBar mActionBar;
    final AppCompatCallback mAppCompatCallback;
    final Window$Callback mAppCompatWindowCallback;
    final Context mContext;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private boolean mIsStarted;
    MenuInflater mMenuInflater;
    final Window$Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private CharSequence mTitle;
    final Window mWindow;
    boolean mWindowNoTitle;
    
    static {
        SHOULD_INSTALL_EXCEPTION_HANDLER = (Build$VERSION.SDK_INT < 21);
        if (AppCompatDelegateImplBase.SHOULD_INSTALL_EXCEPTION_HANDLER && !AppCompatDelegateImplBase.sInstalledExceptionHandler) {
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new AppCompatDelegateImplBase$1(Thread.getDefaultUncaughtExceptionHandler()));
            AppCompatDelegateImplBase.sInstalledExceptionHandler = true;
        }
        sWindowBackgroundStyleable = new int[] { 16842836 };
    }
    
    AppCompatDelegateImplBase(final Context mContext, final Window mWindow, final AppCompatCallback mAppCompatCallback) {
        this.mContext = mContext;
        this.mWindow = mWindow;
        this.mAppCompatCallback = mAppCompatCallback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        if (this.mOriginalWindowCallback instanceof AppCompatDelegateImplBase$AppCompatWindowCallbackBase) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.mAppCompatWindowCallback = this.wrapWindowCallback(this.mOriginalWindowCallback);
        this.mWindow.setCallback(this.mAppCompatWindowCallback);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(mContext, null, AppCompatDelegateImplBase.sWindowBackgroundStyleable);
        final Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
        if (drawableIfKnown != null) {
            this.mWindow.setBackgroundDrawable(drawableIfKnown);
        }
        obtainStyledAttributes.recycle();
    }
    
    @Override
    public boolean applyDayNight() {
        return false;
    }
    
    abstract boolean dispatchKeyEvent(final KeyEvent p0);
    
    final Context getActionBarThemedContext() {
        Context themedContext = null;
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            themedContext = supportActionBar.getThemedContext();
        }
        Context mContext;
        if ((mContext = themedContext) == null) {
            mContext = this.mContext;
        }
        return mContext;
    }
    
    @Override
    public final ActionBarDrawerToggle$Delegate getDrawerToggleDelegate() {
        return new AppCompatDelegateImplBase$ActionBarDrawableToggleImpl(this);
    }
    
    @Override
    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.initWindowDecorActionBar();
            Context context;
            if (this.mActionBar != null) {
                context = this.mActionBar.getThemedContext();
            }
            else {
                context = this.mContext;
            }
            this.mMenuInflater = new SupportMenuInflater(context);
        }
        return this.mMenuInflater;
    }
    
    @Override
    public ActionBar getSupportActionBar() {
        this.initWindowDecorActionBar();
        return this.mActionBar;
    }
    
    final CharSequence getTitle() {
        if (this.mOriginalWindowCallback instanceof Activity) {
            return ((Activity)this.mOriginalWindowCallback).getTitle();
        }
        return this.mTitle;
    }
    
    final Window$Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }
    
    abstract void initWindowDecorActionBar();
    
    final boolean isDestroyed() {
        return this.mIsDestroyed;
    }
    
    public boolean isHandleNativeActionModesEnabled() {
        return false;
    }
    
    @Override
    public void onDestroy() {
        this.mIsDestroyed = true;
    }
    
    abstract boolean onKeyShortcut(final int p0, final KeyEvent p1);
    
    abstract boolean onMenuOpened(final int p0, final Menu p1);
    
    abstract void onPanelClosed(final int p0, final Menu p1);
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
    }
    
    @Override
    public void onStart() {
        this.mIsStarted = true;
    }
    
    @Override
    public void onStop() {
        this.mIsStarted = false;
    }
    
    abstract void onTitleChanged(final CharSequence p0);
    
    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }
    
    @Override
    public final void setTitle(final CharSequence mTitle) {
        this.onTitleChanged(this.mTitle = mTitle);
    }
    
    abstract ActionMode startSupportActionModeFromWindow(final ActionMode$Callback p0);
    
    Window$Callback wrapWindowCallback(final Window$Callback window$Callback) {
        return (Window$Callback)new AppCompatDelegateImplBase$AppCompatWindowCallbackBase(this, window$Callback);
    }
}
