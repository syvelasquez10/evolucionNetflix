// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AbsListView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.widget.GridView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.View;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KubrickShowDetailsFrag$FetchShowDetailsAndSeasonsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    public KubrickShowDetailsFrag$FetchShowDetailsAndSeasonsCallback(final KubrickShowDetailsFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("KubrickShowDetailsFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        super.onShowDetailsAndSeasonsFetched(showDetails, list, status);
        if (this.requestId != this.this$0.getCurrRequestId()) {
            Log.v("KubrickShowDetailsFrag", "Ignoring stale callback");
            return;
        }
        if (status.isError()) {
            Log.w("KubrickShowDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.v("KubrickShowDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        if (list == null || list.size() <= 0) {
            Log.v("KubrickShowDetailsFrag", "No seasons in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.updateShowDetails(showDetails, false);
        this.this$0.updateSeasonData(list);
    }
}
