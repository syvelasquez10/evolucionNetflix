// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;

public class EpisodesAdapter extends RecyclerViewHeaderAdapter implements AdapterView$OnItemClickListener, LoadingStatus
{
    private static final String TAG = "EpisodesAdapter";
    private final NetflixActivity activity;
    protected SeasonDetails currSeasonDetails;
    protected final EpisodesFrag episodeListFrag;
    protected int episodeStartIndex;
    protected boolean hasMoreData;
    protected boolean isLoading;
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    protected long requestId;
    
    public EpisodesAdapter(final NetflixActivity activity, final EpisodesFrag episodeListFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        super(recyclerViewHeaderAdapter$IViewCreator);
        ThreadUtils.assertOnMain();
        this.activity = activity;
        this.episodeListFrag = episodeListFrag;
        this.initToLoadingState();
    }
    
    private void initToLoadingState() {
        Log.v("EpisodesAdapter", "initToLoadingState");
        this.isLoading = true;
        this.hasMoreData = true;
        this.requestId = -1L;
        this.episodeStartIndex = 0;
        this.fetchMoreData();
    }
    
    private void logEmptySeasonId(final SeasonDetails seasonDetails) {
        if (seasonDetails == null) {
            Log.v("EpisodesAdapter", "No season details");
            return;
        }
        this.activity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(String.format("For Show Id %s, the Current Season Details Id is empty - %s, see SPY-7455", this.episodeListFrag.getShowId(), seasonDetails.toString()));
    }
    
    private void onFetchError() {
        Log.d("EpisodesAdapter", "onFetchError()");
        this.notifyDataSetChanged();
        this.episodeListFrag.showErrorView();
    }
    
    protected void fetchMoreData() {
        ServiceManager serviceManager = null;
        if (this.episodeListFrag != null) {
            serviceManager = this.episodeListFrag.getServiceManager();
        }
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.d("EpisodesAdapter", "Manager is not ready");
            return;
        }
        if (this.currSeasonDetails == null) {
            Log.v("EpisodesAdapter", "No season details yet");
            return;
        }
        if (Log.isLoggable()) {
            Log.v("EpisodesAdapter", "curr season number of episodes: " + this.currSeasonDetails.getNumOfEpisodes());
        }
        this.requestId = System.nanoTime();
        final int n = this.episodeStartIndex + 40 - 1;
        final String id = this.currSeasonDetails.getId();
        if (Log.isLoggable()) {
            Log.v("EpisodesAdapter", "Fetching data for: " + id + ", start: " + this.episodeStartIndex + ", end: " + n);
        }
        if (StringUtils.isEmpty(id)) {
            this.logEmptySeasonId(this.currSeasonDetails);
            return;
        }
        serviceManager.getBrowse().fetchEpisodes(id, VideoType.SEASON, this.episodeStartIndex, n, new EpisodesAdapter$FetchEpisodesCallback(this, this.requestId, this.episodeStartIndex, n));
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
        if (this.hasMoreData && n == this.getItemCount() - 20) {
            if (Log.isLoggable()) {
                Log.v("EpisodesAdapter", "Fetching more episodes data, at position: " + n);
            }
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
    
    protected void updateEpisodesData(final List<EpisodeDetails> list, final int n) {
        Log.v("EpisodesAdapter", "Adding episodes, count: " + list.size());
        this.updateItems(list, n);
        this.episodeStartIndex += list.size();
    }
    
    public void updateSeasonDetails(final SeasonDetails currSeasonDetails) {
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("Updating season details: ");
            String title;
            if (currSeasonDetails == null) {
                title = "n/a";
            }
            else {
                title = currSeasonDetails.getTitle();
            }
            Log.v("EpisodesAdapter", append.append(title).toString());
        }
        if (currSeasonDetails == null) {
            return;
        }
        this.currSeasonDetails = currSeasonDetails;
        this.initToLoadingState();
    }
}
