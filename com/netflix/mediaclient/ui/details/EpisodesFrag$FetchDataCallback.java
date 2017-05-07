// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class EpisodesFrag$FetchDataCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ EpisodesFrag this$0;
    
    public EpisodesFrag$FetchDataCallback(final EpisodesFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("EpisodesFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        super.onShowDetailsAndSeasonsFetched(showDetails, list, status);
        if (this.requestId != this.this$0.requestId) {
            Log.v("EpisodesFrag", "Stale response - ignoring");
            return;
        }
        this.this$0.isLoading = false;
        if (status.isError()) {
            Log.w("EpisodesFrag", "Error status code fetching data - showing errors view");
            this.this$0.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.w("EpisodesFrag", "No details in response!");
            this.this$0.showErrorView();
            return;
        }
        if (list == null || list.size() == 0) {
            Log.w("EpisodesFrag", "No seasons in response!");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.updateShowDetails(showDetails);
        this.this$0.updateSeasonData(list);
    }
}
