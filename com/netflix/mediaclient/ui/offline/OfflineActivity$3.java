// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class OfflineActivity$3 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ OfflineActivity this$0;
    
    OfflineActivity$3(final OfflineActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (this.this$0.getPrimaryFrag() instanceof OfflineFragment) {
            ((OfflineFragment)this.this$0.getPrimaryFrag()).switchToEditMode(true);
            this.this$0.invalidateOptionsMenu();
        }
        return true;
    }
}
