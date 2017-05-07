// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode$Callback;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.os.Bundle;
import android.content.res.Configuration;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.view.MenuInflater;
import android.support.v4.app.ActionBarDrawerToggle$Delegate;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v4.app.TaskStackBuilder$SupportParentable;
import android.support.v4.app.FragmentActivity;

public class ActionBarActivity extends FragmentActivity implements TaskStackBuilder$SupportParentable, ActionBarDrawerToggle$TmpDelegateProvider
{
    private ActionBarActivityDelegate mDelegate;
    
    private ActionBarActivityDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = ActionBarActivityDelegate.createDelegate(this);
        }
        return this.mDelegate;
    }
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.getDelegate().addContentView(view, viewGroup$LayoutParams);
    }
    
    public final ActionBarDrawerToggle$Delegate getDrawerToggleDelegate() {
        return this.getDelegate().getDrawerToggleDelegate();
    }
    
    public MenuInflater getMenuInflater() {
        return this.getDelegate().getMenuInflater();
    }
    
    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }
    
    @Override
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }
    
    @Override
    public android.support.v7.app.ActionBarDrawerToggle$Delegate getV7DrawerToggleDelegate() {
        return this.getDelegate().getV7DrawerToggleDelegate();
    }
    
    public void invalidateOptionsMenu() {
        this.getDelegate().supportInvalidateOptionsMenu();
    }
    
    @Override
    public void onBackPressed() {
        if (!this.getDelegate().onBackPressed()) {
            super.onBackPressed();
        }
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.getDelegate().onConfigurationChanged(configuration);
    }
    
    public final void onContentChanged() {
        this.getDelegate().onContentChanged();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getDelegate().onCreate(bundle);
    }
    
    @Override
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return this.getDelegate().onCreatePanelMenu(n, menu);
    }
    
    public View onCreatePanelView(final int n) {
        if (n == 0) {
            return this.getDelegate().onCreatePanelView(n);
        }
        return super.onCreatePanelView(n);
    }
    
    public void onCreateSupportNavigateUpTaskStack(final TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack(this);
    }
    
    @Override
    public View onCreateView(final String s, final Context context, final AttributeSet set) {
        final View onCreateView = super.onCreateView(s, context, set);
        if (onCreateView != null) {
            return onCreateView;
        }
        return this.getDelegate().createView(s, set);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getDelegate().destroy();
    }
    
    @Override
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return super.onKeyDown(n, keyEvent) || this.getDelegate().onKeyDown(n, keyEvent);
    }
    
    public boolean onKeyShortcut(final int n, final KeyEvent keyEvent) {
        return this.getDelegate().onKeyShortcut(n, keyEvent);
    }
    
    @Override
    public final boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (super.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        final ActionBar supportActionBar = this.getSupportActionBar();
        return menuItem.getItemId() == 16908332 && supportActionBar != null && (supportActionBar.getDisplayOptions() & 0x4) != 0x0 && this.onSupportNavigateUp();
    }
    
    public boolean onMenuOpened(final int n, final Menu menu) {
        return this.getDelegate().onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        this.getDelegate().onPanelClosed(n, menu);
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.getDelegate().onPostResume();
    }
    
    @Override
    protected boolean onPrepareOptionsPanel(final View view, final Menu menu) {
        return this.getDelegate().onPrepareOptionsPanel(view, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        return this.getDelegate().onPreparePanel(n, view, menu);
    }
    
    public void onPrepareSupportNavigateUpTaskStack(final TaskStackBuilder taskStackBuilder) {
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.getDelegate().onStop();
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
        this.getDelegate().onTitleChanged(charSequence);
    }
    
    public void setContentView(final int contentView) {
        this.getDelegate().setContentView(contentView);
    }
    
    public void setContentView(final View contentView) {
        this.getDelegate().setContentView(contentView);
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.getDelegate().setContentView(view, viewGroup$LayoutParams);
    }
    
    public void setSupportActionBar(final Toolbar supportActionBar) {
        this.getDelegate().setSupportActionBar(supportActionBar);
    }
    
    public void setSupportProgress(final int supportProgress) {
        this.getDelegate().setSupportProgress(supportProgress);
    }
    
    public void setSupportProgressBarIndeterminate(final boolean supportProgressBarIndeterminate) {
        this.getDelegate().setSupportProgressBarIndeterminate(supportProgressBarIndeterminate);
    }
    
    public void setSupportProgressBarIndeterminateVisibility(final boolean supportProgressBarIndeterminateVisibility) {
        this.getDelegate().setSupportProgressBarIndeterminateVisibility(supportProgressBarIndeterminateVisibility);
    }
    
    public void setSupportProgressBarVisibility(final boolean supportProgressBarVisibility) {
        this.getDelegate().setSupportProgressBarVisibility(supportProgressBarVisibility);
    }
    
    public ActionMode startSupportActionMode(final ActionMode$Callback actionMode$Callback) {
        return this.getDelegate().startSupportActionMode(actionMode$Callback);
    }
    
    void superAddContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.addContentView(view, viewGroup$LayoutParams);
    }
    
    boolean superOnCreatePanelMenu(final int n, final Menu menu) {
        return super.onCreatePanelMenu(n, menu);
    }
    
    boolean superOnMenuOpened(final int n, final Menu menu) {
        return super.onMenuOpened(n, menu);
    }
    
    void superOnPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
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
        this.getDelegate().supportInvalidateOptionsMenu();
    }
    
    public void supportNavigateUpTo(final Intent intent) {
        NavUtils.navigateUpTo(this, intent);
    }
    
    public boolean supportRequestWindowFeature(final int n) {
        return this.getDelegate().supportRequestWindowFeature(n);
    }
    
    public boolean supportShouldUpRecreateTask(final Intent intent) {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }
}
