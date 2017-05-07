// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;

class BrowseAgent$FetchEpisodesTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchEpisodesTask this$1;
    
    BrowseAgent$FetchEpisodesTask$1(final BrowseAgent$FetchEpisodesTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchEpisodesTask$1$1(this, status, list));
    }
}
