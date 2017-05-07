// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.search.SearchMenu;
import android.view.Menu;
import android.view.MenuItem;

public class DetailsMenu
{
    private static final String TAG = "DetailsMenu";
    private final DetailsActivity activity;
    private final MenuItem shareItem;
    
    public DetailsMenu(final DetailsActivity activity, final Menu menu, final String s) {
        SearchMenu.addSearchNavigation(this.activity = activity, menu);
        this.shareItem = this.createShareItem(menu);
    }
    
    private MenuItem createShareItem(final Menu menu) {
        final MenuItem add = menu.add((CharSequence)"");
        add.setShowAsAction(0);
        add.setVisible(false);
        return add;
    }
    
    private void updateShareItemAsUnshared() {
        this.shareItem.setTitle(2131493131).setEnabled(false).setVisible(true);
    }
    
    private void updateShareItemToUnshare(final ServiceManager serviceManager, final String s) {
        this.shareItem.setTitle(2131493130).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DetailsMenu$UnshareClickHandler(this, serviceManager, s)).setEnabled(true).setVisible(true);
    }
    
    public void updateShareItem(final ServiceManager serviceManager, final VideoDetails videoDetails) {
        if (serviceManager == null) {
            Log.w("DetailsMenu", "Service manager is null");
            return;
        }
        if (videoDetails.getPlayable().canBeSharedOnFacebook()) {
            this.updateShareItemToUnshare(serviceManager, videoDetails.getId());
            return;
        }
        this.updateShareItemAsUnshared();
    }
}
