// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.AnimationUtils;
import android.support.v4.view.ViewCompat;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.appcompat.R$id;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Build$VERSION;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.widget.ActionBarContextView;
import android.content.Context;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.internal.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.Menu;
import android.support.v7.internal.view.menu.i;
import android.view.View;
import java.lang.ref.WeakReference;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.internal.view.menu.j;
import android.support.v7.view.ActionMode;

public class WindowDecorActionBar$ActionModeImpl extends ActionMode implements j
{
    private ActionMode$Callback mCallback;
    private WeakReference<View> mCustomView;
    private i mMenu;
    final /* synthetic */ WindowDecorActionBar this$0;
    
    public WindowDecorActionBar$ActionModeImpl(final WindowDecorActionBar this$0, final ActionMode$Callback mCallback) {
        this.this$0 = this$0;
        this.mCallback = mCallback;
        (this.mMenu = new i(this$0.getThemedContext()).a(1)).a(this);
    }
    
    public boolean dispatchOnCreate() {
        this.mMenu.g();
        try {
            return this.mCallback.onCreateActionMode(this, (Menu)this.mMenu);
        }
        finally {
            this.mMenu.h();
        }
    }
    
    @Override
    public void finish() {
        if (this.this$0.mActionMode != this) {
            return;
        }
        if (!checkShowingFlags(this.this$0.mHiddenByApp, this.this$0.mHiddenBySystem, false)) {
            this.this$0.mDeferredDestroyActionMode = this;
            this.this$0.mDeferredModeDestroyCallback = this.mCallback;
        }
        else {
            this.mCallback.onDestroyActionMode(this);
        }
        this.mCallback = null;
        this.this$0.animateToMode(false);
        this.this$0.mContextView.closeMode();
        this.this$0.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
        this.this$0.mOverlayLayout.setHideOnContentScrollEnabled(this.this$0.mHideOnContentScroll);
        this.this$0.mActionMode = null;
    }
    
    @Override
    public View getCustomView() {
        if (this.mCustomView != null) {
            return this.mCustomView.get();
        }
        return null;
    }
    
    @Override
    public Menu getMenu() {
        return (Menu)this.mMenu;
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.this$0.mContextView.getSubtitle();
    }
    
    @Override
    public CharSequence getTitle() {
        return this.this$0.mContextView.getTitle();
    }
    
    @Override
    public void invalidate() {
        this.mMenu.g();
        try {
            this.mCallback.onPrepareActionMode(this, (Menu)this.mMenu);
        }
        finally {
            this.mMenu.h();
        }
    }
    
    @Override
    public boolean isTitleOptional() {
        return this.this$0.mContextView.isTitleOptional();
    }
    
    @Override
    public boolean onMenuItemSelected(final i i, final MenuItem menuItem) {
        return this.mCallback != null && this.mCallback.onActionItemClicked(this, menuItem);
    }
    
    @Override
    public void onMenuModeChange(final i i) {
        if (this.mCallback == null) {
            return;
        }
        this.invalidate();
        this.this$0.mContextView.showOverflowMenu();
    }
    
    @Override
    public void setCustomView(final View customView) {
        this.this$0.mContextView.setCustomView(customView);
        this.mCustomView = new WeakReference<View>(customView);
    }
    
    @Override
    public void setSubtitle(final int n) {
        this.setSubtitle(this.this$0.mContext.getResources().getString(n));
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.this$0.mContextView.setSubtitle(subtitle);
    }
    
    @Override
    public void setTitle(final int n) {
        this.setTitle(this.this$0.mContext.getResources().getString(n));
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.this$0.mContextView.setTitle(title);
    }
    
    @Override
    public void setTitleOptionalHint(final boolean b) {
        super.setTitleOptionalHint(b);
        this.this$0.mContextView.setTitleOptional(b);
    }
}
