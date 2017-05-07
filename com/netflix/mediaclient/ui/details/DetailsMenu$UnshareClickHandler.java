// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.view.MenuItem;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.MenuItem$OnMenuItemClickListener;

class DetailsMenu$UnshareClickHandler implements MenuItem$OnMenuItemClickListener
{
    private final ServiceManager serviceMan;
    final /* synthetic */ DetailsMenu this$0;
    private final String videoId;
    
    public DetailsMenu$UnshareClickHandler(final DetailsMenu this$0, final ServiceManager serviceMan, final String videoId) {
        this.this$0 = this$0;
        this.serviceMan = serviceMan;
        this.videoId = videoId;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        menuItem.setEnabled(false);
        if (this.serviceMan != null) {
            this.serviceMan.getBrowse().hideVideo(this.videoId, new DetailsMenu$OnVideoHideCallback(this.this$0));
        }
        return true;
    }
}
