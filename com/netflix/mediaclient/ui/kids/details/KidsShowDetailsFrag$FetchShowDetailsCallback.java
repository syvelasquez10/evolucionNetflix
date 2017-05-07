// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.IntentFilter;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.content.BroadcastReceiver;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KidsShowDetailsFrag$FetchShowDetailsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ KidsShowDetailsFrag this$0;
    
    public KidsShowDetailsFrag$FetchShowDetailsCallback(final KidsShowDetailsFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("KidsShowDetailsFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        if (this.requestId != this.this$0.requestId) {
            Log.v("KidsShowDetailsFrag", "Ignoring stale callback");
            return;
        }
        if (status.isError()) {
            Log.w("KidsShowDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.v("KidsShowDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.updateShowDetails(showDetails);
    }
}
