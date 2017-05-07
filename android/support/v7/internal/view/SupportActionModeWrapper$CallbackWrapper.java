// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.view.menu.ab;
import android.support.v4.internal.view.SupportMenu;
import android.view.Menu;
import android.support.v4.util.SimpleArrayMap;
import android.content.Context;
import java.util.ArrayList;
import android.support.v7.view.ActionMode$Callback;

public class SupportActionModeWrapper$CallbackWrapper implements ActionMode$Callback
{
    final ArrayList<SupportActionModeWrapper> mActionModes;
    final Context mContext;
    final SimpleArrayMap<Menu, Menu> mMenus;
    final android.view.ActionMode$Callback mWrappedCallback;
    
    public SupportActionModeWrapper$CallbackWrapper(final Context mContext, final android.view.ActionMode$Callback mWrappedCallback) {
        this.mContext = mContext;
        this.mWrappedCallback = mWrappedCallback;
        this.mActionModes = new ArrayList<SupportActionModeWrapper>();
        this.mMenus = new SimpleArrayMap<Menu, Menu>();
    }
    
    private Menu getMenuWrapper(final Menu menu) {
        Menu a;
        if ((a = this.mMenus.get(menu)) == null) {
            a = ab.a(this.mContext, (SupportMenu)menu);
            this.mMenus.put(menu, a);
        }
        return a;
    }
    
    public android.view.ActionMode getActionModeWrapper(final ActionMode actionMode) {
        for (int size = this.mActionModes.size(), i = 0; i < size; ++i) {
            final SupportActionModeWrapper supportActionModeWrapper = this.mActionModes.get(i);
            if (supportActionModeWrapper != null && supportActionModeWrapper.mWrappedObject == actionMode) {
                return supportActionModeWrapper;
            }
        }
        final SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.mContext, actionMode);
        this.mActionModes.add(supportActionModeWrapper2);
        return supportActionModeWrapper2;
    }
    
    @Override
    public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
        return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(actionMode), ab.a(this.mContext, (SupportMenuItem)menuItem));
    }
    
    @Override
    public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(actionMode), this.getMenuWrapper(menu));
    }
    
    @Override
    public void onDestroyActionMode(final ActionMode actionMode) {
        this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(actionMode));
    }
    
    @Override
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(actionMode), this.getMenuWrapper(menu));
    }
}
