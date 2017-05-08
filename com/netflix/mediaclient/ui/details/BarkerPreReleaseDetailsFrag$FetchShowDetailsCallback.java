// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.ui.barker.details.BarkerMovieDetailsFrag;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class BarkerPreReleaseDetailsFrag$FetchShowDetailsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ BarkerPreReleaseDetailsFrag this$0;
    
    public BarkerPreReleaseDetailsFrag$FetchShowDetailsCallback(final BarkerPreReleaseDetailsFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("BarkerPreReleaseDetailsFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity())) {
            Log.v("BarkerPreReleaseDetailsFrag", "Activity state is invalid");
            return;
        }
        if (this.requestId != this.this$0.requestId || this.this$0.isDestroyed()) {
            Log.v("BarkerPreReleaseDetailsFrag", "Ignoring stale callback");
            return;
        }
        this.this$0.isLoading = false;
        if (status.isError()) {
            Log.w("BarkerPreReleaseDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.v("BarkerPreReleaseDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.showDetailsView((MovieDetails)showDetails);
    }
}
