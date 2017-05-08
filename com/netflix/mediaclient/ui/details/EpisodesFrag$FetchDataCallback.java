// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.content.Context;
import android.os.Build$VERSION;
import android.widget.AdapterView$OnItemSelectedListener;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.util.DataUtil;
import android.os.Bundle;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
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
        }
        else {
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
            if (!showDetails.isPreRelease() && !this.this$0.hasSeasons(list)) {
                Log.w("EpisodesFrag", "No seasons in response!");
                this.this$0.showErrorView();
                return;
            }
            this.this$0.updateShowDetails(showDetails);
            DPPrefetchABTestUtils.reportDPMetadataFetchedEvent(status);
            if (this.this$0.hasSeasons(list)) {
                this.this$0.updateSeasonData(list);
                return;
            }
            if (!this.this$0.isListVisible()) {
                Log.v("EpisodesFrag", "Showing recycler view");
                AnimationUtils.showView((View)this.this$0.recyclerView, true);
                this.this$0.leWrapper.hide(false);
            }
        }
    }
}
