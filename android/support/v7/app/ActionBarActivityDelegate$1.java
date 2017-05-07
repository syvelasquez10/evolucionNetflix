// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.v7.internal.app.WindowCallback;

class ActionBarActivityDelegate$1 implements WindowCallback
{
    final /* synthetic */ ActionBarActivityDelegate this$0;
    
    ActionBarActivityDelegate$1(final ActionBarActivityDelegate this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return this.this$0.mActivity.superOnCreatePanelMenu(n, menu);
    }
    
    @Override
    public View onCreatePanelView(final int n) {
        return null;
    }
    
    @Override
    public boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        return this.this$0.mActivity.onMenuItemSelected(n, menuItem);
    }
    
    @Override
    public boolean onMenuOpened(final int n, final Menu menu) {
        return this.this$0.mActivity.onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        this.this$0.mActivity.onPanelClosed(n, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        return this.this$0.mActivity.superOnPreparePanel(n, view, menu);
    }
    
    @Override
    public ActionMode startActionMode(final ActionMode$Callback actionMode$Callback) {
        return this.this$0.startSupportActionModeFromWindow(actionMode$Callback);
    }
}
