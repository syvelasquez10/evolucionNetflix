// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.facebook.react.bridge.Callback;
import android.widget.PopupMenu$OnMenuItemClickListener;
import android.widget.PopupMenu$OnDismissListener;

class NativeViewHierarchyManager$PopupMenuCallbackHandler implements PopupMenu$OnDismissListener, PopupMenu$OnMenuItemClickListener
{
    boolean mConsumed;
    final Callback mSuccess;
    
    private NativeViewHierarchyManager$PopupMenuCallbackHandler(final Callback mSuccess) {
        this.mConsumed = false;
        this.mSuccess = mSuccess;
    }
    
    public void onDismiss(final PopupMenu popupMenu) {
        if (!this.mConsumed) {
            this.mSuccess.invoke("dismissed");
            this.mConsumed = true;
        }
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (!this.mConsumed) {
            this.mSuccess.invoke("itemSelected", menuItem.getOrder());
            return this.mConsumed = true;
        }
        return false;
    }
}
