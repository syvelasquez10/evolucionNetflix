// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.v7.internal.app.WindowCallback;

public class WindowCallbackWrapper implements WindowCallback
{
    private WindowCallback mWrapped;
    
    public WindowCallbackWrapper(final WindowCallback mWrapped) {
        if (mWrapped == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.mWrapped = mWrapped;
    }
    
    @Override
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return this.mWrapped.onCreatePanelMenu(n, menu);
    }
    
    @Override
    public View onCreatePanelView(final int n) {
        return this.mWrapped.onCreatePanelView(n);
    }
    
    @Override
    public boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        return this.mWrapped.onMenuItemSelected(n, menuItem);
    }
    
    @Override
    public boolean onMenuOpened(final int n, final Menu menu) {
        return this.mWrapped.onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        this.mWrapped.onPanelClosed(n, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        return this.mWrapped.onPreparePanel(n, view, menu);
    }
    
    @Override
    public ActionMode startActionMode(final ActionMode.Callback callback) {
        return this.mWrapped.startActionMode(callback);
    }
}
