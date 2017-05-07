// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.BaseAdapter;

public class EpisodeListAdapter extends BaseAdapter implements LoadingStatus, AdapterView$OnItemClickListener
{
    public static final int FETCH_REQUEST_BATCH_SIZE = 40;
    private static final String TAG = "EpisodeListAdapter";
    private final NetflixActivity activity;
    private SeasonDetails currSeasonDetails;
    private final EpisodeListFrag episodeListFrag;
    private int episodeStartIndex;
    private final List<EpisodeDetails> episodes;
    private boolean hasError;
    private boolean hasMoreData;
    private boolean isLoading;
    private final ViewGroup leViewGroup;
    private final LoadingAndErrorWrapper leWrapper;
    private final View loadingRow;
    protected LoadingStatusCallback mLoadingStatusCallback;
    private int prevCount;
    private long requestId;
    private int selectedEpisodeIndex;
    
    public EpisodeListAdapter(final NetflixActivity activity, final EpisodeListFrag episodeListFrag) {
        this.episodes = new ArrayList<EpisodeDetails>();
        this.prevCount = -1;
        ThreadUtils.assertOnMain();
        this.activity = activity;
        this.episodeListFrag = episodeListFrag;
        this.leViewGroup = (ViewGroup)new FrameLayout((Context)activity);
        activity.getLayoutInflater().inflate(2130903118, this.leViewGroup);
        (this.leWrapper = new LoadingAndErrorWrapper((View)this.leViewGroup, new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                EpisodeListAdapter.this.initToLoadingState();
            }
        })).hide(false);
        (this.loadingRow = (View)new LoadingView((Context)activity)).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.initToLoadingState();
    }
    
    private void fetchMoreData() {
        final ServiceManager serviceManager = this.episodeListFrag.getServiceManager();
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
        serviceManager.getBrowse().fetchEpisodes(id, this.episodeStartIndex, n, new FetchEpisodesCallback(this.requestId, n - this.episodeStartIndex + 1));
    }
    
    private void initToLoadingState() {
        Log.v("EpisodeListAdapter", "initToLoadingState");
        this.isLoading = true;
        this.hasMoreData = true;
        this.hasError = false;
        this.requestId = -1L;
        this.episodeStartIndex = 0;
        this.episodes.clear();
        this.notifyDataSetChanged();
        this.fetchMoreData();
    }
    
    private void onFetchError() {
        Log.d("EpisodeListAdapter", "Fetch error");
        this.hasError = true;
        this.notifyDataSetChanged();
    }
    
    private void updateEpisodesData(final List<EpisodeDetails> list) {
        Log.v("EpisodeListAdapter", "Adding episodes, count: " + list.size());
        this.hasError = false;
        this.episodes.addAll(list);
        this.episodeStartIndex += list.size();
        this.notifyDataSetChanged();
    }
    
    public int getCount() {
        int prevCount;
        if (this.hasError) {
            prevCount = 1;
        }
        else {
            final int size = this.episodes.size();
            int n;
            if (this.hasMoreData) {
                n = 1;
            }
            else {
                n = 0;
            }
            prevCount = size + n;
        }
        if (Log.isLoggable("EpisodeListAdapter", 2) && this.prevCount != prevCount) {
            this.prevCount = prevCount;
            Log.v("EpisodeListAdapter", "getCount() result changed to: " + prevCount);
        }
        return prevCount;
    }
    
    public EpisodeDetails getItem(final int n) {
        return this.episodes.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        boolean b = false;
        if (this.activity.destroyed()) {
            return this.loadingRow;
        }
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            Log.v("EpisodeListAdapter", "getting View, hasError: " + this.hasError + ", hasMoreData: " + this.hasMoreData + ", eps: " + this.episodes.size());
        }
        if (this.hasError) {
            this.leWrapper.showErrorView(false);
            return (View)this.leViewGroup;
        }
        this.leWrapper.hide(false);
        if (this.hasMoreData && n == this.getCount() - 1) {
            return this.loadingRow;
        }
        if (this.hasMoreData && n == this.getCount() - 20) {
            this.fetchMoreData();
        }
        Object o = null;
        Label_0184: {
            if (view != null) {
                o = view;
                if (view instanceof EpisodeRowView) {
                    break Label_0184;
                }
            }
            o = new EpisodeRowView((Context)this.activity);
        }
        final EpisodeDetails episodeDetails = this.episodes.get(n);
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            Log.v("EpisodeListAdapter", "updating: " + episodeDetails.getTitle() + ", pos/curr: " + n + "/" + this.selectedEpisodeIndex);
        }
        final EpisodeRowView episodeRowView = (EpisodeRowView)o;
        if (n == this.selectedEpisodeIndex) {
            b = true;
        }
        episodeRowView.updateToEpisode(episodeDetails, b);
        return (View)o;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    public void notifyDataSetChanged() {
        Log.v("EpisodeListAdapter", "Notifying data set changed...");
        super.notifyDataSetChanged();
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (!view.isInTouchMode()) {
            ((EpisodeRowView)view).handleItemClick();
        }
    }
    
    protected void onLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    public void setLoadingStatusCallback(final LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    public void updateSeasonDetails(final SeasonDetails currSeasonDetails) {
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
        this.selectedEpisodeIndex = this.currSeasonDetails.getCurrentEpisodeNumber() - 1;
        this.initToLoadingState();
    }
    
    private class FetchEpisodesCallback extends LoggingManagerCallback
    {
        private final int numItems;
        private final long requestId;
        
        public FetchEpisodesCallback(final long requestId, final int numItems) {
            super("EpisodeListAdapter");
            this.requestId = requestId;
            this.numItems = numItems;
        }
        
        @Override
        public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
            super.onEpisodesFetched(list, status);
            if (EpisodeListAdapter.this.activity.destroyed()) {
                return;
            }
            if (this.requestId != EpisodeListAdapter.this.requestId) {
                Log.v("EpisodeListAdapter", "Ignoring stale request");
                return;
            }
            EpisodeListAdapter.this.hasMoreData = true;
            EpisodeListAdapter.this.isLoading = false;
            EpisodeListAdapter.this.onLoaded(status);
            if (status.isError()) {
                Log.w("EpisodeListAdapter", "Invalid status code");
                EpisodeListAdapter.this.hasMoreData = false;
                EpisodeListAdapter.this.onFetchError();
                return;
            }
            if (list == null || list.size() == 0) {
                Log.v("EpisodeListAdapter", "No details in response");
                EpisodeListAdapter.this.hasMoreData = false;
                EpisodeListAdapter.this.notifyDataSetChanged();
                return;
            }
            if (Log.isLoggable("EpisodeListAdapter", 2)) {
                Log.v("EpisodeListAdapter", "Got " + list.size() + " items, expected " + this.numItems);
            }
            if (list.size() < this.numItems) {
                EpisodeListAdapter.this.hasMoreData = false;
            }
            EpisodeListAdapter.this.updateEpisodesData(list);
        }
    }
}
