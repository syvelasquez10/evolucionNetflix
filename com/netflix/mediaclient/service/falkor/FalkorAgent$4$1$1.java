// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$4$1$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent$4$1 this$2;
    
    FalkorAgent$4$1$1(final FalkorAgent$4$1 this$2) {
        this.this$2 = this$2;
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        if (Log.isLoggable()) {
            Log.d("FalkorAgent", String.format("fetchEpisodesForSeason - onEpisodesFetched res %d", status.getStatusCode().getValue()));
        }
    }
}
