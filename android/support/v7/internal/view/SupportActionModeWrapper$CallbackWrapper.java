// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.view.Menu;
import android.support.v7.internal.view.menu.ac;
import android.view.MenuItem;
import android.content.Context;
import android.support.v7.view.ActionMode;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.view.ActionMode$Callback;

public class SupportActionModeWrapper$CallbackWrapper implements ActionMode$Callback
{
    final SimpleArrayMap<ActionMode, SupportActionModeWrapper> mActionModes;
    final Context mContext;
    final android.view.ActionMode$Callback mWrappedCallback;
    
    public SupportActionModeWrapper$CallbackWrapper(final Context mContext, final android.view.ActionMode$Callback mWrappedCallback) {
        this.mContext = mContext;
        this.mWrappedCallback = mWrappedCallback;
        this.mActionModes = new SimpleArrayMap<ActionMode, SupportActionModeWrapper>();
    }
    
    private android.view.ActionMode getActionModeWrapper(final ActionMode actionMode) {
        final SupportActionModeWrapper supportActionModeWrapper = this.mActionModes.get(actionMode);
        if (supportActionModeWrapper != null) {
            return supportActionModeWrapper;
        }
        final SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.mContext, actionMode);
        this.mActionModes.put(actionMode, supportActionModeWrapper2);
        return supportActionModeWrapper2;
    }
    
    @Override
    public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
        return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(actionMode), ac.a(menuItem));
    }
    
    @Override
    public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(actionMode), ac.a(menu));
    }
    
    @Override
    public void onDestroyActionMode(final ActionMode actionMode) {
        this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(actionMode));
    }
    
    @Override
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(actionMode), ac.a(menu));
    }
}
