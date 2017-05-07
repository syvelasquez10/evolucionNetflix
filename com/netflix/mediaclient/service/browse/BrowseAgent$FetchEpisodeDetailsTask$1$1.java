// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;

class BrowseAgent$FetchEpisodeDetailsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchEpisodeDetailsTask$1 this$2;
    final /* synthetic */ EpisodeDetails val$episodeDetails;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchEpisodeDetailsTask$1$1(final BrowseAgent$FetchEpisodeDetailsTask$1 this$2, final Status val$res, final EpisodeDetails val$episodeDetails) {
        this.this$2 = this$2;
        this.val$res = val$res;
        this.val$episodeDetails = val$episodeDetails;
    }
    
    @Override
    public void run() {
        if (this.val$res.isSucces() && this.val$episodeDetails != null) {
            this.this$2.this$1.this$0.updateEpisodeWithLatestShowInformation(this.val$episodeDetails);
        }
        this.this$2.this$1.getCallback().onEpisodeDetailsFetched(this.val$episodeDetails, this.val$res);
    }
}
