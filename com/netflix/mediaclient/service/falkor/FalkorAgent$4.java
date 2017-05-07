// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$4 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$4(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        if (showDetails == null) {
            if (Log.isLoggable()) {
                Log.w("FalkorAgent", String.format("fetchEpisodesForSeason - onShowDetailsFetched res %d", status.getStatusCode().getValue()));
            }
            return;
        }
        this.this$0.fetchEpisodeDetails(showDetails.getCurrentEpisodeId(), new FalkorAgent$4$1(this, showDetails.getCurrentEpisodeNumber()));
    }
}
