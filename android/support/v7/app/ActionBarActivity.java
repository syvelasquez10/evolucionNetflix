// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.os.Build$VERSION;
import android.support.v4.app.ActivityCompat;
import android.content.Context;
import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Bundle;
import android.content.res.Configuration;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.FragmentActivity;

public class ActionBarActivity extends FragmentActivity implements Callback, SupportParentable, DelegateProvider
{
    ActionBarActivityDelegate mImpl;
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.mImpl.addContentView(view, viewGroup$LayoutParams);
    }
    
    @Override
    public final Delegate getDrawerToggleDelegate() {
        return this.mImpl.getDrawerToggleDelegate();
    }
    
    public MenuInflater getMenuInflater() {
        return this.mImpl.getMenuInflater();
    }
    
    public ActionBar getSupportActionBar() {
        return this.mImpl.getSupportActionBar();
    }
    
    @Override
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }
    
    @Override
    public void onBackPressed() {
        if (!this.mImpl.onBackPressed()) {
            super.onBackPressed();
        }
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mImpl.onConfigurationChanged(configuration);
    }
    
    public final void onContentChanged() {
        this.mImpl.onContentChanged();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.mImpl = ActionBarActivityDelegate.createDelegate(this);
        super.onCreate(bundle);
        this.mImpl.onCreate(bundle);
    }
    
    @Override
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return this.mImpl.onCreatePanelMenu(n, menu);
    }
    
    public View onCreatePanelView(final int n) {
        if (n == 0) {
            return this.mImpl.onCreatePanelView(n);
        }
        return super.onCreatePanelView(n);
    }
    
    public void onCreateSupportNavigateUpTaskStack(final TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack(this);
    }
    
    @Override
    public final boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (this.mImpl.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        final ActionBar supportActionBar = this.getSupportActionBar();
        return menuItem.getItemId() == 16908332 && supportActionBar != null && (supportActionBar.getDisplayOptions() & 0x4) != 0x0 && this.onSupportNavigateUp();
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.mImpl.onPostResume();
    }
    
    @Override
    protected boolean onPrepareOptionsPanel(final View view, final Menu menu) {
        return this.mImpl.onPrepareOptionsPanel(view, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        return this.mImpl.onPreparePanel(n, view, menu);
    }
    
    public void onPrepareSupportNavigateUpTaskStack(final TaskStackBuilder taskStackBuilder) {
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.mImpl.onStop();
    }
    
    public void onSupportActionModeFinished(final ActionMode actionMode) {
    }
    
    public void onSupportActionModeStarted(final ActionMode actionMode) {
    }
    
    public void onSupportContentChanged() {
    }
    
    public boolean onSupportNavigateUp() {
        final Intent supportParentActivityIntent = this.getSupportParentActivityIntent();
        if (supportParentActivityIntent != null) {
            Label_0050: {
                if (!this.supportShouldUpRecreateTask(supportParentActivityIntent)) {
                    break Label_0050;
                }
                final TaskStackBuilder create = TaskStackBuilder.create((Context)this);
                this.onCreateSupportNavigateUpTaskStack(create);
                this.onPrepareSupportNavigateUpTaskStack(create);
                create.startActivities();
                try {
                    ActivityCompat.finishAffinity(this);
                    return true;
                }
                catch (IllegalStateException ex) {
                    this.finish();
                    return true;
                }
            }
            this.supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        return false;
    }
    
    protected void onTitleChanged(final CharSequence charSequence, final int n) {
        super.onTitleChanged(charSequence, n);
        this.mImpl.onTitleChanged(charSequence);
    }
    
    public void setContentView(final int contentView) {
        this.mImpl.setContentView(contentView);
    }
    
    public void setContentView(final View contentView) {
        this.mImpl.setContentView(contentView);
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.mImpl.setContentView(view, viewGroup$LayoutParams);
    }
    
    public void setSupportProgress(final int supportProgress) {
        this.mImpl.setSupportProgress(supportProgress);
    }
    
    public void setSupportProgressBarIndeterminate(final boolean supportProgressBarIndeterminate) {
        this.mImpl.setSupportProgressBarIndeterminate(supportProgressBarIndeterminate);
    }
    
    public void setSupportProgressBarIndeterminateVisibility(final boolean supportProgressBarIndeterminateVisibility) {
        this.mImpl.setSupportProgressBarIndeterminateVisibility(supportProgressBarIndeterminateVisibility);
    }
    
    public void setSupportProgressBarVisibility(final boolean supportProgressBarVisibility) {
        this.mImpl.setSupportProgressBarVisibility(supportProgressBarVisibility);
    }
    
    public ActionMode startSupportActionMode(final ActionMode.Callback callback) {
        return this.mImpl.startSupportActionMode(callback);
    }
    
    void superAddContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.addContentView(view, viewGroup$LayoutParams);
    }
    
    boolean superOnCreatePanelMenu(final int n, final Menu menu) {
        return super.onCreatePanelMenu(n, menu);
    }
    
    boolean superOnMenuItemSelected(final int n, final MenuItem menuItem) {
        return super.onMenuItemSelected(n, menuItem);
    }
    
    boolean superOnPrepareOptionsPanel(final View view, final Menu menu) {
        return super.onPrepareOptionsPanel(view, menu);
    }
    
    boolean superOnPreparePanel(final int n, final View view, final Menu menu) {
        return super.onPreparePanel(n, view, menu);
    }
    
    void superSetContentView(final int contentView) {
        super.setContentView(contentView);
    }
    
    void superSetContentView(final View contentView) {
        super.setContentView(contentView);
    }
    
    void superSetContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(view, viewGroup$LayoutParams);
    }
    
    @Override
    public void supportInvalidateOptionsMenu() {
        if (Build$VERSION.SDK_INT >= 14) {
            super.supportInvalidateOptionsMenu();
        }
        this.mImpl.supportInvalidateOptionsMenu();
    }
    
    public void supportNavigateUpTo(final Intent intent) {
        NavUtils.navigateUpTo(this, intent);
    }
    
    public boolean supportRequestWindowFeature(final int n) {
        return this.mImpl.supportRequestWindowFeature(n);
    }
    
    public boolean supportShouldUpRecreateTask(final Intent intent) {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }
}
