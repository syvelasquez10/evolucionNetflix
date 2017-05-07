// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;

class BrowseAgent$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent this$0;
    final /* synthetic */ ShowDetails val$showDetails;
    
    BrowseAgent$1(final BrowseAgent this$0, final ShowDetails val$showDetails) {
        this.this$0 = this$0;
        this.val$showDetails = val$showDetails;
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
        if (status.isSucces()) {
            for (final SeasonDetails seasonDetails : list) {
                if (seasonDetails.getSeasonNumber() == this.val$showDetails.getCurrentSeasonNumber()) {
                    final com.netflix.mediaclient.service.webclient.model.SeasonDetails seasonDetails2 = (com.netflix.mediaclient.service.webclient.model.SeasonDetails)seasonDetails;
                    if (seasonDetails2.detail == null) {
                        continue;
                    }
                    if (Log.isLoggable("nf_service_browseagent", 3)) {
                        Log.d("nf_service_browseagent", String.format("currentepisode being overwitten from %d to %d", seasonDetails.getCurrentEpisodeNumber(), this.val$showDetails.getCurrentEpisodeNumber()));
                    }
                    seasonDetails2.detail.currentEpisodeNumber = this.val$showDetails.getCurrentEpisodeNumber();
                }
            }
        }
    }
}
