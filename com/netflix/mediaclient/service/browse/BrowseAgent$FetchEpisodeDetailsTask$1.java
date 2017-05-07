// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;

class BrowseAgent$FetchEpisodeDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchEpisodeDetailsTask this$1;
    
    BrowseAgent$FetchEpisodeDetailsTask$1(final BrowseAgent$FetchEpisodeDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        this.this$1.updateCacheIfNecessary(episodeDetails, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchEpisodeDetailsTask$1$1(this, status, episodeDetails));
    }
}
