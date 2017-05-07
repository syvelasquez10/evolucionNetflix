// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.view.Menu;
import android.content.res.TypedArray;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v7.appcompat.R;
import android.os.Bundle;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v4.app.ActionBarDrawerToggle;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.os.Build$VERSION;
import android.view.MenuInflater;

abstract class ActionBarActivityDelegate
{
    static final String METADATA_UI_OPTIONS = "android.support.UI_OPTIONS";
    private static final String TAG = "ActionBarActivityDelegate";
    static final String UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW = "splitActionBarWhenNarrow";
    private ActionBar mActionBar;
    final ActionBarActivity mActivity;
    private boolean mEnableDefaultActionBarUp;
    boolean mHasActionBar;
    private MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    
    ActionBarActivityDelegate(final ActionBarActivity mActivity) {
        this.mActivity = mActivity;
    }
    
    static ActionBarActivityDelegate createDelegate(final ActionBarActivity actionBarActivity) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 16) {
            return new ActionBarActivityDelegateJB(actionBarActivity);
        }
        if (sdk_INT >= 14) {
            return new ActionBarActivityDelegateICS(actionBarActivity);
        }
        if (sdk_INT >= 11) {
            return new ActionBarActivityDelegateHC(actionBarActivity);
        }
        return new ActionBarActivityDelegateBase(actionBarActivity);
    }
    
    abstract void addContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    abstract ActionBar createSupportActionBar();
    
    protected final Context getActionBarThemedContext() {
        Object o = this.mActivity;
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            o = supportActionBar.getThemedContext();
        }
        return (Context)o;
    }
    
    abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
    
    MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                this.mMenuInflater = new SupportMenuInflater(supportActionBar.getThemedContext());
            }
            else {
                this.mMenuInflater = new SupportMenuInflater((Context)this.mActivity);
            }
        }
        return this.mMenuInflater;
    }
    
    final ActionBar getSupportActionBar() {
        if (this.mHasActionBar || this.mOverlayActionBar) {
            if (this.mActionBar == null) {
                this.mActionBar = this.createSupportActionBar();
                if (this.mEnableDefaultActionBarUp) {
                    this.mActionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        else {
            this.mActionBar = null;
        }
        return this.mActionBar;
    }
    
    protected final String getUiOptionsFromMetadata() {
        try {
            final ActivityInfo activityInfo = this.mActivity.getPackageManager().getActivityInfo(this.mActivity.getComponentName(), 128);
            String string = null;
            if (activityInfo.metaData != null) {
                string = activityInfo.metaData.getString("android.support.UI_OPTIONS");
            }
            return string;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("ActionBarActivityDelegate", "getUiOptionsFromMetadata: Activity '" + this.mActivity.getClass().getSimpleName() + "' not in manifest");
            return null;
        }
    }
    
    abstract boolean onBackPressed();
    
    abstract void onConfigurationChanged(final Configuration p0);
    
    abstract void onContentChanged();
    
    void onCreate(final Bundle bundle) {
        final TypedArray obtainStyledAttributes = this.mActivity.obtainStyledAttributes(R.styleable.ActionBarWindow);
        if (!obtainStyledAttributes.hasValue(0)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        this.mHasActionBar = obtainStyledAttributes.getBoolean(0, false);
        this.mOverlayActionBar = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        if (NavUtils.getParentActivityName(this.mActivity) != null) {
            if (this.mActionBar != null) {
                this.mActionBar.setDisplayHomeAsUpEnabled(true);
                return;
            }
            this.mEnableDefaultActionBarUp = true;
        }
    }
    
    abstract boolean onCreatePanelMenu(final int p0, final Menu p1);
    
    abstract View onCreatePanelView(final int p0);
    
    abstract boolean onMenuItemSelected(final int p0, final MenuItem p1);
    
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
    
    abstract void setSupportProgress(final int p0);
    
    abstract void setSupportProgressBarIndeterminate(final boolean p0);
    
    abstract void setSupportProgressBarIndeterminateVisibility(final boolean p0);
    
    abstract void setSupportProgressBarVisibility(final boolean p0);
    
    abstract ActionMode startSupportActionMode(final ActionMode.Callback p0);
    
    abstract void supportInvalidateOptionsMenu();
    
    abstract boolean supportRequestWindowFeature(final int p0);
}
