// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class ShowDetailsFrag$FetchShowDetailsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ ShowDetailsFrag this$0;
    
    public ShowDetailsFrag$FetchShowDetailsCallback(final ShowDetailsFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("ShowDetailsFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity())) {
            Log.v("ShowDetailsFrag", "Activity state is invalid");
            return;
        }
        if (this.requestId != this.this$0.requestId || this.this$0.isDestroyed()) {
            Log.v("ShowDetailsFrag", "Ignoring stale callback");
            return;
        }
        this.this$0.isLoading = false;
        if (status.isError()) {
            Log.w("ShowDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.v("ShowDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.showDetailsView(showDetails);
    }
}
