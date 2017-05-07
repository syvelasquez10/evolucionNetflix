// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

public interface WindowCallback
{
    boolean onCreatePanelMenu(final int p0, final Menu p1);
    
    View onCreatePanelView(final int p0);
    
    boolean onMenuItemSelected(final int p0, final MenuItem p1);
    
    boolean onMenuOpened(final int p0, final Menu p1);
    
    void onPanelClosed(final int p0, final Menu p1);
    
    boolean onPreparePanel(final int p0, final View p1, final Menu p2);
    
    ActionMode startActionMode(final ActionMode.Callback p0);
}
