// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.Collection;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.BaseAdapter;

public class EpisodeListAdapter extends BaseAdapter implements AdapterView$OnItemClickListener, LoadingStatus
{
    private static final String TAG = "EpisodeListAdapter";
    private final NetflixActivity activity;
    private SeasonDetails currSeasonDetails;
    protected final NetflixDialogFrag episodeListFrag;
    protected int episodeStartIndex;
    private final List<EpisodeDetails> episodes;
    private boolean hasError;
    private boolean hasMoreData;
    private boolean isLoading;
    private final ViewGroup leViewGroup;
    private final LoadingAndErrorWrapper leWrapper;
    private final View loadingRow;
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    private int prevCount;
    protected long requestId;
    private int selectedEpisodeIndex;
    
    public EpisodeListAdapter(final NetflixActivity activity, final NetflixDialogFrag episodeListFrag) {
        this.episodes = new ArrayList<EpisodeDetails>();
        this.prevCount = -1;
        ThreadUtils.assertOnMain();
        this.activity = activity;
        this.episodeListFrag = episodeListFrag;
        this.leViewGroup = (ViewGroup)new FrameLayout((Context)activity);
        activity.getLayoutInflater().inflate(2130903132, this.leViewGroup);
        (this.leWrapper = new LoadingAndErrorWrapper((View)this.leViewGroup, new EpisodeListAdapter$1(this))).hide(false);
        (this.loadingRow = (View)new LoadingView((Context)activity)).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.initToLoadingState();
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
    
    protected EpisodeRowView createView() {
        return new EpisodeRowView((Context)this.activity, 2130903092);
    }
    
    protected void fetchMoreData() {
        ServiceManager serviceManager = null;
        if (this.episodeListFrag instanceof ServiceManagerProvider) {
            serviceManager = ((ServiceManagerProvider)this.episodeListFrag).getServiceManager();
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
        serviceManager.getBrowse().fetchEpisodes(id, VideoType.SEASON, this.episodeStartIndex, n, new EpisodeListAdapter$FetchEpisodesCallback(this, this.requestId, n - this.episodeStartIndex + 1));
    }
    
    public int getCount() {
        int n = 1;
        final int n2 = 1;
        int prevCount;
        if (this.hasError) {
            prevCount = n2;
        }
        else {
            final int size = this.episodes.size();
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
    
    public EpisodeDetails getItem(final int n) {
        return this.episodes.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
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
        if (view == null || !(view instanceof EpisodeRowView)) {
            view = (View)this.createView();
        }
        final EpisodeDetails episodeDetails = this.episodes.get(n);
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            Log.v("EpisodeListAdapter", "updating: " + episodeDetails.getTitle() + ", pos/curr: " + n + "/" + this.selectedEpisodeIndex);
        }
        final EpisodeRowView episodeRowView = (EpisodeRowView)view;
        if (n == this.selectedEpisodeIndex) {
            b = true;
        }
        episodeRowView.updateToEpisode(episodeDetails, b);
        return view;
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
    
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback mLoadingStatusCallback) {
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
}
