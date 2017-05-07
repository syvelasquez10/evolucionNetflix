// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;

class BrowseAgent$6 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent this$0;
    
    BrowseAgent$6(final BrowseAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            Log.d("nf_bookmark", String.format("populate cache - onEpisodesFetched res %d", status.getStatusCode().getValue()));
        }
    }
}
