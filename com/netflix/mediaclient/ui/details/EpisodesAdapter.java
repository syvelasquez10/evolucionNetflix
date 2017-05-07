// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;

public class EpisodesAdapter extends RecyclerViewHeaderAdapter implements AdapterView$OnItemClickListener, LoadingStatus
{
    private static final String TAG = "EpisodeListAdapter";
    private final NetflixActivity activity;
    protected SeasonDetails currSeasonDetails;
    protected final EpisodesFrag episodeListFrag;
    protected int episodeStartIndex;
    private boolean hasError;
    private boolean hasMoreData;
    private boolean isLoading;
    private final ViewGroup leViewGroup;
    private final LoadingAndErrorWrapper leWrapper;
    private final View loadingRow;
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    private int prevCount;
    protected long requestId;
    private String selectedEpisodeId;
    
    public EpisodesAdapter(final NetflixActivity activity, final EpisodesFrag episodeListFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        super(recyclerViewHeaderAdapter$IViewCreator);
        this.prevCount = -1;
        ThreadUtils.assertOnMain();
        this.activity = activity;
        this.episodeListFrag = episodeListFrag;
        this.leViewGroup = (ViewGroup)new FrameLayout((Context)activity);
        activity.getLayoutInflater().inflate(2130903131, this.leViewGroup);
        (this.leWrapper = new LoadingAndErrorWrapper((View)this.leViewGroup, new EpisodesAdapter$1(this))).hide(false);
        (this.loadingRow = (View)new LoadingView((Context)activity)).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.initToLoadingState();
    }
    
    private int getCount() {
        int n = 1;
        final int n2 = 1;
        int prevCount;
        if (this.hasError) {
            prevCount = n2;
        }
        else {
            final int size = this.data.size();
            if (!this.hasMoreData) {
                n = 0;
            }
            prevCount = n + size;
        }
        if (Log.isLoggable("EpisodeListAdapter", 2) && this.prevCount != prevCount) {
            this.prevCount = prevCount;
            Log.v("EpisodeListAdapter", "getCount() result changed to: " + prevCount);
        }
        return prevCount;
    }
    
    private void initToLoadingState() {
        Log.v("EpisodeListAdapter", "initToLoadingState");
        this.isLoading = true;
        this.hasMoreData = true;
        this.hasError = false;
        this.requestId = -1L;
        this.episodeStartIndex = 0;
        this.fetchMoreData();
    }
    
    private void logEmptySeasonId(final SeasonDetails seasonDetails) {
        if (seasonDetails == null) {
            Log.v("EpisodeListAdapter", "No season details");
            return;
        }
        this.activity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(String.format("For Show Id %s, the Current Season Details Id is empty - %s, see SPY-7455", this.episodeListFrag.getShowId(), seasonDetails.toString()));
    }
    
    private void onFetchError() {
        Log.d("EpisodeListAdapter", "Fetch error");
        this.hasError = true;
        this.notifyDataSetChanged();
    }
    
    protected void fetchMoreData() {
        ServiceManager serviceManager = null;
        if (this.episodeListFrag instanceof ServiceManagerProvider) {
            serviceManager = this.episodeListFrag.getServiceManager();
        }
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.d("EpisodeListAdapter", "Manager is not ready");
            return;
        }
        if (this.currSeasonDetails == null) {
            Log.v("EpisodeListAdapter", "No season details yet");
            return;
        }
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            Log.v("EpisodeListAdapter", "curr season number of episodes: " + this.currSeasonDetails.getNumOfEpisodes());
        }
        this.requestId = System.nanoTime();
        final int n = this.episodeStartIndex + 40 - 1;
        final String id = this.currSeasonDetails.getId();
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            Log.v("EpisodeListAdapter", "Fetching data for: " + id + ", start: " + this.episodeStartIndex + ", end: " + n);
        }
        if (StringUtils.isEmpty(id)) {
            this.logEmptySeasonId(this.currSeasonDetails);
            return;
        }
        serviceManager.getBrowse().fetchEpisodes(id, VideoType.SEASON, this.episodeStartIndex, n, new EpisodesAdapter$FetchEpisodesCallback(this, this.requestId, n - this.episodeStartIndex + 1));
    }
    
    public View getActiveLoadingView(final int n) {
        final View view = null;
        View loadingRow;
        if (this.activity.destroyed()) {
            loadingRow = this.loadingRow;
        }
        else {
            if (Log.isLoggable("EpisodeListAdapter", 2)) {
                Log.v("EpisodeListAdapter", "getting View, hasError: " + this.hasError + ", hasMoreData: " + this.hasMoreData + ", eps: " + this.data.size());
            }
            if (this.hasError) {
                this.leWrapper.showErrorView(false);
                return (View)this.leViewGroup;
            }
            this.leWrapper.hide(false);
            loadingRow = view;
            if (this.hasMoreData) {
                loadingRow = view;
                if (n == this.getCount() - 1) {
                    return this.loadingRow;
                }
            }
        }
        return loadingRow;
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
    }
    
    protected void onLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    @Override
    protected void onPostItemViewBind(final int n) {
        if (this.hasMoreData && n == this.getCount() - 20) {
            this.fetchMoreData();
        }
    }
    
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    protected void updateEpisodesData(final List<EpisodeDetails> items) {
        Log.v("EpisodeListAdapter", "Adding episodes, count: " + items.size());
        this.hasError = false;
        this.setItems(items);
        this.episodeStartIndex += items.size();
    }
    
    public void updateShowAndSeasonDetails(final ShowDetails showDetails, final SeasonDetails currSeasonDetails) {
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            final StringBuilder append = new StringBuilder().append("Updating season details: ");
            String title;
            if (currSeasonDetails == null) {
                title = "n/a";
            }
            else {
                title = currSeasonDetails.getTitle();
            }
            Log.v("EpisodeListAdapter", append.append(title).toString());
        }
        if (currSeasonDetails == null) {
            return;
        }
        this.currSeasonDetails = currSeasonDetails;
        this.selectedEpisodeId = showDetails.getCurrentEpisodeId();
        this.initToLoadingState();
    }
}
