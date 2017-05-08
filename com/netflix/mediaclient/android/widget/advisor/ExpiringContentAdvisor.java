// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.player.PlayScreen;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.player.PlayerFragment;

public class ExpiringContentAdvisor extends TwoLineAdvisor
{
    private PlayerFragment controller;
    
    protected ExpiringContentAdvisor(final NetflixActivity netflixActivity, final Advisory advisory) {
        super(netflixActivity, advisory);
    }
    
    @Override
    public void dismiss() {
        super.dismiss();
        if (PlayScreen.isBrowseValid(this.controller)) {
            this.controller.getServiceManager().getBrowse().updateExpiredContentAdvisoryStatus(this.controller.getCurrentAsset().getPlayableId(), ExpiringContentAdvisory$ContentAction.NEVER_SHOW_AGAIN);
        }
    }
    
    public void setController(final PlayerFragment controller) {
        this.controller = controller;
    }
    
    @Override
    protected void showInternal() {
        super.showInternal();
        if (PlayScreen.isBrowseValid(this.controller)) {
            this.controller.getServiceManager().getBrowse().updateExpiredContentAdvisoryStatus(this.controller.getCurrentAsset().getPlayableId(), ExpiringContentAdvisory$ContentAction.LOG_WHEN_SHOWN);
        }
    }
}
