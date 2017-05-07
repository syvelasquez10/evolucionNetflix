// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$FetchShowDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchShowDetailsTask this$1;
    
    BrowseAgent$FetchShowDetailsTask$1(final BrowseAgent$FetchShowDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    private void updateCache(final Status status, final ShowDetails showDetails) {
        if (status.isSucces() && StringUtils.isEmpty(this.this$1.mCurrentEpisodeId)) {
            this.this$1.updateCacheIfNecessary(showDetails, status);
        }
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        this.updateCache(status, showDetails);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchShowDetailsTask$1$2(this, showDetails, list, status));
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        this.updateCache(status, showDetails);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchShowDetailsTask$1$1(this, showDetails, status));
    }
}
