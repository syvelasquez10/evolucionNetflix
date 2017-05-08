// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;

class LoLoMoFrag$1 extends NetflixActivity$ServiceManagerRunnable
{
    final /* synthetic */ LoLoMoFrag this$0;
    
    LoLoMoFrag$1(final LoLoMoFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run(final ServiceManager serviceManager) {
        if (serviceManager.isOfflineFeatureAvailable() && serviceManager.getOfflineAgent().getLatestOfflinePlayableList().getTitleCount() > 0) {
            this.this$0.leWrapper.showViewMyDownloadsButton();
        }
    }
}
