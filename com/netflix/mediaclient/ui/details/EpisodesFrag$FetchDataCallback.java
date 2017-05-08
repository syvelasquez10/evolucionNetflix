// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab7994;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.view.ContextThemeWrapper;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.util.DataUtil;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.falkor.task.CmpTaskDetails;
import com.netflix.falkor.task.FetchVideoVolatileNodesTask;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
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
        boolean b = true;
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
            if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getActivity())) {
                this.this$0.updateShowDetails(showDetails);
                DPPrefetchABTestUtils.reportDPMetadataFetchedEvent(status);
                if (this.this$0.hasSeasons(list)) {
                    this.this$0.updateSeasonData(list);
                }
                else if (!this.this$0.isListVisible()) {
                    Log.v("EpisodesFrag", "Showing recycler view");
                    AnimationUtils.showView((View)this.this$0.recyclerView, true);
                    this.this$0.leWrapper.hide(false);
                }
                if (showDetails.shouldRefreshVolatileData() && ConnectivityUtils.isConnected((Context)this.this$0.getActivity())) {
                    final ServiceManager serviceManager = this.this$0.getServiceManager();
                    if (serviceManager == null || !serviceManager.isReady()) {
                        Log.w("EpisodesFrag", "Manager is null/notReady - can't reload data");
                        return;
                    }
                    final String id = showDetails.getId();
                    Log.v("EpisodesFrag", "Fetching volatile data for video ID: %s", id);
                    final IBrowseManager browse = serviceManager.getBrowse();
                    if (showDetails.getType() != VideoType.MOVIE) {
                        b = false;
                    }
                    browse.fetchTask(new FetchVideoVolatileNodesTask(id, b), new EpisodesFrag$FetchVideoVolatileNodesCallback(this.this$0, id, showDetails.getUserThumbRating(), showDetails.getMatchPercentage(), showDetails.isInQueue(), null));
                }
            }
        }
    }
}
