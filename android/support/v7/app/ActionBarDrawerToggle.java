// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.app.ActionBar;
import android.support.v4.view.ViewCompat;
import android.support.annotation.Nullable;
import android.content.Context;
import android.view.MenuItem;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;

public class ActionBarDrawerToggle implements DrawerListener
{
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private DrawerToggle mSlider;
    private View$OnClickListener mToolbarNavigationClickListener;
    
    public ActionBarDrawerToggle(final Activity activity, final DrawerLayout drawerLayout, final int n, final int n2) {
        this(activity, null, drawerLayout, (Drawable)null, n, n2);
    }
    
    public ActionBarDrawerToggle(final Activity activity, final DrawerLayout drawerLayout, final Toolbar toolbar, final int n, final int n2) {
        this(activity, toolbar, drawerLayout, (Drawable)null, n, n2);
    }
    
    ActionBarDrawerToggle(final Activity activity, final Toolbar toolbar, final DrawerLayout mDrawerLayout, final T t, final int mOpenDrawerContentDescRes, final int mCloseDrawerContentDescRes) {
        this.mDrawerIndicatorEnabled = true;
        if (toolbar != null) {
            this.mActivityImpl = (Delegate)new ToolbarCompatDelegate(toolbar);
            toolbar.setNavigationOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (ActionBarDrawerToggle.this.mDrawerIndicatorEnabled) {
                        ActionBarDrawerToggle.this.toggle();
                    }
                    else if (ActionBarDrawerToggle.this.mToolbarNavigationClickListener != null) {
                        ActionBarDrawerToggle.this.mToolbarNavigationClickListener.onClick(view);
                    }
                }
            });
        }
        else if (activity instanceof DelegateProvider) {
            this.mActivityImpl = ((DelegateProvider)activity).getDrawerToggleDelegate();
        }
        else if (activity instanceof TmpDelegateProvider) {
            this.mActivityImpl = ((TmpDelegateProvider)activity).getV7DrawerToggleDelegate();
        }
        else if (Build$VERSION.SDK_INT >= 18) {
            this.mActivityImpl = (Delegate)new JellybeanMr2Delegate(activity);
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            this.mActivityImpl = (Delegate)new HoneycombDelegate(activity);
        }
        else {
            this.mActivityImpl = (Delegate)new DummyDelegate(activity);
        }
        this.mDrawerLayout = mDrawerLayout;
        this.mOpenDrawerContentDescRes = mOpenDrawerContentDescRes;
        this.mCloseDrawerContentDescRes = mCloseDrawerContentDescRes;
        if (t == null) {
            this.mSlider = (DrawerToggle)new DrawerArrowDrawableToggle(activity, this.mActivityImpl.getActionBarThemedContext());
        }
        else {
            this.mSlider = (DrawerToggle)t;
        }
        this.mHomeAsUpIndicator = this.getThemeUpIndicator();
    }
    
    private void toggle() {
        if (this.mDrawerLayout.isDrawerVisible(8388611)) {
            this.mDrawerLayout.closeDrawer(8388611);
            return;
        }
        this.mDrawerLayout.openDrawer(8388611);
    }
    
    Drawable getThemeUpIndicator() {
        return this.mActivityImpl.getThemeUpIndicator();
    }
    
    public View$OnClickListener getToolbarNavigationClickListener() {
        return this.mToolbarNavigationClickListener;
    }
    
    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        }
        this.syncState();
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
        this.mSlider.setPosition(Math.min(1.0f, Math.max(0.0f, n)));
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
            this.toggle();
            return true;
        }
        return false;
    }
    
    void setActionBarDescription(final int actionBarDescription) {
        this.mActivityImpl.setActionBarDescription(actionBarDescription);
    }
    
    void setActionBarUpIndicator(final Drawable drawable, final int n) {
        this.mActivityImpl.setActionBarUpIndicator(drawable, n);
    }
    
    public void setDrawerIndicatorEnabled(final boolean mDrawerIndicatorEnabled) {
        if (mDrawerIndicatorEnabled != this.mDrawerIndicatorEnabled) {
            if (mDrawerIndicatorEnabled) {
                final Drawable drawable = (Drawable)this.mSlider;
                int n;
                if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                    n = this.mCloseDrawerContentDescRes;
                }
                else {
                    n = this.mOpenDrawerContentDescRes;
                }
                this.setActionBarUpIndicator(drawable, n);
            }
            else {
                this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = mDrawerIndicatorEnabled;
        }
    }
    
    public void setHomeAsUpIndicator(final int n) {
        Drawable drawable = null;
        if (n != 0) {
            drawable = this.mDrawerLayout.getResources().getDrawable(n);
        }
        this.setHomeAsUpIndicator(drawable);
    }
    
    public void setHomeAsUpIndicator(final Drawable mHomeAsUpIndicator) {
        if (mHomeAsUpIndicator == null) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        }
        else {
            this.mHomeAsUpIndicator = mHomeAsUpIndicator;
            this.mHasCustomUpIndicator = true;
        }
        if (!this.mDrawerIndicatorEnabled) {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }
    
    public void setToolbarNavigationClickListener(final View$OnClickListener mToolbarNavigationClickListener) {
        this.mToolbarNavigationClickListener = mToolbarNavigationClickListener;
    }
    
    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mSlider.setPosition(1.0f);
        }
        else {
            this.mSlider.setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            final Drawable drawable = (Drawable)this.mSlider;
            int n;
            if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                n = this.mCloseDrawerContentDescRes;
            }
            else {
                n = this.mOpenDrawerContentDescRes;
            }
            this.setActionBarUpIndicator(drawable, n);
        }
    }
    
    public interface Delegate
    {
        Context getActionBarThemedContext();
        
        Drawable getThemeUpIndicator();
        
        void setActionBarDescription(final int p0);
        
        void setActionBarUpIndicator(final Drawable p0, final int p1);
    }
    
    public interface DelegateProvider
    {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }
    
    static class DrawerArrowDrawableToggle extends DrawerArrowDrawable implements DrawerToggle
    {
        private final Activity mActivity;
        
        public DrawerArrowDrawableToggle(final Activity mActivity, final Context context) {
            super(context);
            this.mActivity = mActivity;
        }
        
        @Override
        public float getPosition() {
            return super.getProgress();
        }
        
        @Override
        boolean isLayoutRtl() {
            return ViewCompat.getLayoutDirection(this.mActivity.getWindow().getDecorView()) == 1;
        }
        
        @Override
        public void setPosition(final float progress) {
            if (progress == 1.0f) {
                this.setVerticalMirror(true);
            }
            else if (progress == 0.0f) {
                this.setVerticalMirror(false);
            }
            super.setProgress(progress);
        }
    }
    
    interface DrawerToggle
    {
        float getPosition();
        
        void setPosition(final float p0);
    }
    
    static class DummyDelegate implements Delegate
    {
        final Activity mActivity;
        
        DummyDelegate(final Activity mActivity) {
            this.mActivity = mActivity;
        }
        
        @Override
        public Context getActionBarThemedContext() {
            return (Context)this.mActivity;
        }
        
        @Override
        public Drawable getThemeUpIndicator() {
            return null;
        }
        
        @Override
        public void setActionBarDescription(final int n) {
        }
        
        @Override
        public void setActionBarUpIndicator(final Drawable drawable, final int n) {
        }
    }
    
    private static class HoneycombDelegate implements Delegate
    {
        final Activity mActivity;
        ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;
        
        private HoneycombDelegate(final Activity mActivity) {
            this.mActivity = mActivity;
        }
        
        @Override
        public Context getActionBarThemedContext() {
            final ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return (Context)this.mActivity;
        }
        
        @Override
        public Drawable getThemeUpIndicator() {
            return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(this.mActivity);
        }
        
        @Override
        public void setActionBarDescription(final int n) {
            this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, n);
        }
        
        @Override
        public void setActionBarUpIndicator(final Drawable drawable, final int n) {
            this.mActivity.getActionBar().setDisplayShowHomeEnabled(true);
            this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, drawable, n);
            this.mActivity.getActionBar().setDisplayShowHomeEnabled(false);
        }
    }
    
    private static class JellybeanMr2Delegate implements Delegate
    {
        final Activity mActivity;
        
        private JellybeanMr2Delegate(final Activity mActivity) {
            this.mActivity = mActivity;
        }
        
        @Override
        public Context getActionBarThemedContext() {
            final ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return (Context)this.mActivity;
        }
        
        @Override
        public Drawable getThemeUpIndicator() {
            final TypedArray obtainStyledAttributes = this.getActionBarThemedContext().obtainStyledAttributes((AttributeSet)null, new int[] { 16843531 }, 16843470, 0);
            final Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }
        
        @Override
        public void setActionBarDescription(final int homeActionContentDescription) {
            final ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(homeActionContentDescription);
            }
        }
        
        @Override
        public void setActionBarUpIndicator(final Drawable homeAsUpIndicator, final int homeActionContentDescription) {
            final ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(homeAsUpIndicator);
                actionBar.setHomeActionContentDescription(homeActionContentDescription);
            }
        }
    }
    
    interface TmpDelegateProvider
    {
        @Nullable
        Delegate getV7DrawerToggleDelegate();
    }
    
    static class ToolbarCompatDelegate implements Delegate
    {
        final Toolbar mToolbar;
        
        ToolbarCompatDelegate(final Toolbar mToolbar) {
            this.mToolbar = mToolbar;
        }
        
        @Override
        public Context getActionBarThemedContext() {
            return this.mToolbar.getContext();
        }
        
        @Override
        public Drawable getThemeUpIndicator() {
            final TypedArray obtainStyledAttributes = this.mToolbar.getContext().obtainStyledAttributes(new int[] { 16908332 });
            final Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }
        
        @Override
        public void setActionBarDescription(final int navigationContentDescription) {
            this.mToolbar.setNavigationContentDescription(navigationContentDescription);
        }
        
        @Override
        public void setActionBarUpIndicator(final Drawable navigationIcon, final int navigationContentDescription) {
            this.mToolbar.setNavigationIcon(navigationIcon);
            this.mToolbar.setNavigationContentDescription(navigationContentDescription);
        }
    }
}
