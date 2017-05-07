// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
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
