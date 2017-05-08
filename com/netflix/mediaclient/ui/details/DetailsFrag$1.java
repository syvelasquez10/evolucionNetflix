// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;

class DetailsFrag$1 extends NetflixActivity$ServiceManagerRunnable
{
    final /* synthetic */ DetailsFrag this$0;
    
    DetailsFrag$1(final DetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run(final ServiceManager serviceManager) {
        if (serviceManager.isOfflineFeatureAvailable() && serviceManager.getOfflineAgent().getLatestOfflinePlayableList().getTitleCount() > 0) {
            this.this$0.leWrapper.showViewMyDownloadsButton();
        }
    }
}
