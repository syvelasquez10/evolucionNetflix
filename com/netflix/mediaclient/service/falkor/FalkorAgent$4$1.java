// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$4$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent$4 this$1;
    final /* synthetic */ int val$episodeNumber;
    
    FalkorAgent$4$1(final FalkorAgent$4 this$1, final int val$episodeNumber) {
        this.this$1 = this$1;
        this.val$episodeNumber = val$episodeNumber;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        if (episodeDetails == null) {
            if (Log.isLoggable()) {
                Log.w("FalkorAgent", String.format("fetchEpisodesForSeason - onEpisodeDetailsFetched res %d", status.getStatusCode().getValue()));
            }
            return;
        }
        final String seasonId = episodeDetails.getSeasonId();
        final int n = this.val$episodeNumber / 40 * 40;
        this.this$1.this$0.fetchEpisodes(seasonId, VideoType.SEASON, n, n + 40 - 1, new FalkorAgent$4$1$1(this));
    }
}
