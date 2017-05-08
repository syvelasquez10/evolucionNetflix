// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.View;
import android.support.design.widget.Snackbar;
import com.netflix.mediaclient.util.LogUtils;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class OfflineActivity$2 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ OfflineActivity this$0;
    
    OfflineActivity$2(final OfflineActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (this.this$0.getPrimaryFrag() instanceof OfflineFragment) {
            final OfflineFragment offlineFragment = (OfflineFragment)this.this$0.getPrimaryFrag();
            final int selectedItemsCount = offlineFragment.getSelectedItemsCount();
            final String selectedItemsDiskSpace = offlineFragment.getSelectedItemsDiskSpace();
            offlineFragment.deleteSelected();
            offlineFragment.switchToEditMode(false);
            final View viewById = this.this$0.findViewById(2131755332);
            if (viewById == null) {
                LogUtils.reportErrorSafely("Expected a R.id.coordinatorLayout here");
                return true;
            }
            Snackbar.make(viewById, this.this$0.getResources().getQuantityString(2131361803, selectedItemsCount, new Object[] { selectedItemsCount, selectedItemsDiskSpace, "" }), 0).show();
        }
        return true;
    }
}
