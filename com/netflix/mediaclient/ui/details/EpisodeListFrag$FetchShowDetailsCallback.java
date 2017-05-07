// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class EpisodeListFrag$FetchShowDetailsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ EpisodeListFrag this$0;
    
    public EpisodeListFrag$FetchShowDetailsCallback(final EpisodeListFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("EpisodeListFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        if (this.requestId != this.this$0.getCurrRequestId()) {
            Log.v("EpisodeListFrag", "Ignoring stale callback");
            return;
        }
        if (status.isError()) {
            Log.w("EpisodeListFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.v("EpisodeListFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.updateShowDetails(showDetails, true);
    }
}
