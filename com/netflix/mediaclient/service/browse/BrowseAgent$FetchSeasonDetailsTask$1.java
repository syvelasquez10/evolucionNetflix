// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;

class BrowseAgent$FetchSeasonDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchSeasonDetailsTask this$1;
    
    BrowseAgent$FetchSeasonDetailsTask$1(final BrowseAgent$FetchSeasonDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
        this.this$1.updateCacheIfNecessary(seasonDetails, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchSeasonDetailsTask$1$1(this, seasonDetails, status));
    }
}
