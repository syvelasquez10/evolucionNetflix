// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.MenuItem;
import android.view.View;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout$DrawerListener;

public class ActionBarDrawerToggle implements DrawerLayout$DrawerListener
{
    private final ActionBarDrawerToggle$Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private ActionBarDrawerToggle$DrawerToggle mSlider;
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
            this.mActivityImpl = new ActionBarDrawerToggle$ToolbarCompatDelegate(toolbar);
            toolbar.setNavigationOnClickListener((View$OnClickListener)new ActionBarDrawerToggle$1(this));
        }
        else if (activity instanceof ActionBarDrawerToggle$DelegateProvider) {
            this.mActivityImpl = ((ActionBarDrawerToggle$DelegateProvider)activity).getDrawerToggleDelegate();
        }
        else if (activity instanceof ActionBarDrawerToggle$TmpDelegateProvider) {
            this.mActivityImpl = ((ActionBarDrawerToggle$TmpDelegateProvider)activity).getV7DrawerToggleDelegate();
        }
        else if (Build$VERSION.SDK_INT >= 18) {
            this.mActivityImpl = new ActionBarDrawerToggle$JellybeanMr2Delegate(activity, null);
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            this.mActivityImpl = new ActionBarDrawerToggle$HoneycombDelegate(activity, null);
        }
        else {
            this.mActivityImpl = new ActionBarDrawerToggle$DummyDelegate(activity);
        }
        this.mDrawerLayout = mDrawerLayout;
        this.mOpenDrawerContentDescRes = mOpenDrawerContentDescRes;
        this.mCloseDrawerContentDescRes = mCloseDrawerContentDescRes;
        if (t == null) {
            this.mSlider = new ActionBarDrawerToggle$DrawerArrowDrawableToggle(activity, this.mActivityImpl.getActionBarThemedContext());
        }
        else {
            this.mSlider = (ActionBarDrawerToggle$DrawerToggle)t;
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
}
