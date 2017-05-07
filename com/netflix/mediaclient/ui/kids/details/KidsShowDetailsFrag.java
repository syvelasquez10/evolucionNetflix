// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.IntentFilter;
import android.widget.AbsListView$OnScrollListener;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.content.BroadcastReceiver;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class KidsShowDetailsFrag extends NetflixFrag
{
    private static final String EXTRA_SHOW_ID = "extra_show_id";
    private static final String TAG = "KidsShowDetailsFrag";
    private KidsShowDetailsAdapter adapter;
    private View content;
    private KidsDetailsViewGroup detailsViewGroup;
    private final BroadcastReceiver episodeRefreshReceiver;
    private boolean isLoading;
    private LoadingAndErrorWrapper leWrapper;
    private StickyListHeadersListView listView;
    private ServiceManager manager;
    private long requestId;
    private ShowDetails showDetails;
    private String showId;
    
    public KidsShowDetailsFrag() {
        this.episodeRefreshReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (!KidsShowDetailsFrag.this.isDestroyed()) {
                    final String action = intent.getAction();
                    Log.v("KidsShowDetailsFrag", "receiver inovoked, action: " + action);
                    if ("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH".equals(action)) {
                        return;
                    }
                }
            }
        };
    }
    
    private void completeInitIfPossible() {
        if (this.getActivity() == null) {
            Log.v("KidsShowDetailsFrag", "Can't complete init yet - activity is null");
            return;
        }
        if (this.manager == null) {
            Log.v("KidsShowDetailsFrag", "Can't complete init yet - manager is null");
            return;
        }
        if (this.content == null) {
            Log.v("KidsShowDetailsFrag", "Can't complete init yet - details view is null");
            return;
        }
        if (this.showId == null) {
            Log.v("KidsShowDetailsFrag", "Can't complete init yet - showId is null");
            return;
        }
        Log.v("KidsShowDetailsFrag", "All clear - completing init process...");
        this.fetchShowDetails();
    }
    
    public static Fragment create(final String s) {
        final KidsShowDetailsFrag kidsShowDetailsFrag = new KidsShowDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_show_id", s);
        kidsShowDetailsFrag.setArguments(arguments);
        return kidsShowDetailsFrag;
    }
    
    private void fetchSeasonDetails() {
        if (this.manager == null) {
            Log.w("KidsShowDetailsFrag", "Manager is null - can't get season details");
            return;
        }
        this.requestId = System.nanoTime();
        final int n = this.showDetails.getNumOfSeasons() - 1;
        Log.v("KidsShowDetailsFrag", "Fetching seasons data from: " + 0 + " to " + n + ", id: " + this.requestId);
        this.manager.fetchSeasons(this.showDetails.getId(), 0, n, new FetchSeasonsCallback(this.requestId));
    }
    
    private void fetchShowDetails() {
        if (this.manager == null) {
            Log.w("KidsShowDetailsFrag", "Manager is null - can't get show details");
            return;
        }
        this.showLoadingView();
        this.isLoading = true;
        this.requestId = System.nanoTime();
        Log.v("KidsShowDetailsFrag", "Fetching data for show ID: " + this.showId);
        this.manager.fetchShowDetails(this.showId, null, new FetchShowDetailsCallback(this.requestId));
    }
    
    private void showErrorView() {
        this.leWrapper.showErrorView(true);
        AnimationUtils.hideView((View)this.listView, true);
    }
    
    private void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        AnimationUtils.hideView((View)this.listView, true);
    }
    
    private void showStandardViews() {
        this.leWrapper.hide(true);
        AnimationUtils.showView((View)this.listView, true);
    }
    
    private void updateSeasonData(final List<SeasonDetails> list) {
        this.adapter = new KidsShowDetailsAdapter(this, this.showDetails, list);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnScrollListener((AbsListView$OnScrollListener)this.adapter);
        this.showStandardViews();
    }
    
    private void updateShowDetails(final ShowDetails showDetails) {
        Log.v("KidsShowDetailsFrag", "Updating show details: " + showDetails.getTitle());
        this.showDetails = showDetails;
        this.detailsViewGroup.updateDetails(showDetails);
        this.fetchSeasonDetails();
    }
    
    public StickyListHeadersListView getListView() {
        return this.listView;
    }
    
    public boolean isLoadingData() {
        return this.isLoading || (this.adapter != null && this.adapter.isLoading());
    }
    
    public void onActivityCreated(final Bundle bundle) {
        Log.v("KidsShowDetailsFrag", "onActivityCreated");
        super.onActivityCreated(bundle);
        this.completeInitIfPossible();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.showId = this.getArguments().getString("extra_show_id");
        super.onCreate(bundle);
        this.getActivity().registerReceiver(this.episodeRefreshReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH"));
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.content = layoutInflater.inflate(2130903108, (ViewGroup)null);
        this.listView = (StickyListHeadersListView)this.content.findViewById(16908298);
        KidsUtils.configureListViewForKids(this.getNetflixActivity(), this.listView);
        this.detailsViewGroup = new KidsDetailsViewGroup((Context)this.getActivity());
        this.listView.addHeaderView((View)this.detailsViewGroup, null, false);
        this.leWrapper = new LoadingAndErrorWrapper(this.content, new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                KidsShowDetailsFrag.this.showLoadingView();
                KidsShowDetailsFrag.this.fetchShowDetails();
            }
        });
        return this.content;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getActivity().unregisterReceiver(this.episodeRefreshReceiver);
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final int n) {
        Log.v("KidsShowDetailsFrag", "onManagerReady");
        super.onManagerReady(manager, n);
        this.manager = manager;
        this.completeInitIfPossible();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        super.onManagerUnavailable(serviceManager, n);
        this.manager = null;
    }
    
    private class FetchSeasonsCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchSeasonsCallback(final long requestId) {
            super("KidsShowDetailsFrag");
            this.requestId = requestId;
        }
        
        @Override
        public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
            super.onSeasonsFetched(list, n);
            if (this.requestId != KidsShowDetailsFrag.this.requestId) {
                Log.v("KidsShowDetailsFrag", "Stale request - ignoring");
                return;
            }
            KidsShowDetailsFrag.this.isLoading = false;
            if (n != 0) {
                Log.w("KidsShowDetailsFrag", "Invalid status code");
                KidsShowDetailsFrag.this.showErrorView();
                return;
            }
            if (list == null) {
                Log.v("KidsShowDetailsFrag", "No details in response");
                KidsShowDetailsFrag.this.showErrorView();
                return;
            }
            KidsShowDetailsFrag.this.updateSeasonData(list);
        }
    }
    
    private class FetchShowDetailsCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchShowDetailsCallback(final long requestId) {
            super("KidsShowDetailsFrag");
            this.requestId = requestId;
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
            super.onShowDetailsFetched(showDetails, n);
            if (this.requestId != KidsShowDetailsFrag.this.requestId) {
                Log.v("KidsShowDetailsFrag", "Ignoring stale callback");
                return;
            }
            if (n != 0) {
                Log.w("KidsShowDetailsFrag", "Invalid status code");
                KidsShowDetailsFrag.this.showErrorView();
                return;
            }
            if (showDetails == null) {
                Log.v("KidsShowDetailsFrag", "No details in response");
                KidsShowDetailsFrag.this.showErrorView();
                return;
            }
            KidsShowDetailsFrag.this.updateShowDetails(showDetails);
        }
    }
}
