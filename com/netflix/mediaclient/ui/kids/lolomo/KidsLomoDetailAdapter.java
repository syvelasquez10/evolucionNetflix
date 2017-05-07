// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import android.widget.LinearLayout;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.widget.BaseAdapter;

public class KidsLomoDetailAdapter extends BaseAdapter implements ILoLoMoAdapter
{
    public static final int NUM_VIDEOS_TO_FETCH_PER_BATCH = 20;
    private static final String TAG = "KidsLomoDetailAdapter";
    private final NetflixActivity activity;
    private final LoLoMoFrag frag;
    private boolean hasMoreData;
    private boolean isLoading;
    private final LoMo lomo;
    private ServiceManager manager;
    private long requestId;
    private final List<Video> videoData;
    private int videoStartIndex;
    
    public KidsLomoDetailAdapter(final LoLoMoFrag frag, final LoMo lomo) {
        this.isLoading = true;
        this.videoData = new ArrayList<Video>();
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.lomo = lomo;
    }
    
    static /* synthetic */ int access$412(final KidsLomoDetailAdapter kidsLomoDetailAdapter, int videoStartIndex) {
        videoStartIndex += kidsLomoDetailAdapter.videoStartIndex;
        return kidsLomoDetailAdapter.videoStartIndex = videoStartIndex;
    }
    
    private void fetchMoreData() {
        this.isLoading = true;
        this.requestId = System.nanoTime();
        final int n = this.videoStartIndex + 20 - 1;
        if (Log.isLoggable("KidsLomoDetailAdapter", 2)) {
            Log.v("KidsLomoDetailAdapter", "fetching more data, starting at index: " + this.videoStartIndex);
            Log.v("KidsLomoDetailAdapter", "fetching from: " + this.videoStartIndex + " to: " + n + ", id: " + this.lomo.getId());
        }
        if (this.manager == null) {
            Log.w("KidsLomoDetailAdapter", "Manager is null - can't refresh data");
            return;
        }
        final FetchVideosCallback fetchVideosCallback = new FetchVideosCallback(this.requestId, n - this.videoStartIndex + 1);
        if (this.lomo.getType() == LoMoType.CONTINUE_WATCHING) {
            this.manager.fetchCWVideos(this.videoStartIndex, n, fetchVideosCallback);
            return;
        }
        if (this.lomo.getType() == LoMoType.FLAT_GENRE) {
            this.manager.fetchGenreVideos(this.lomo, this.videoStartIndex, n, fetchVideosCallback);
            return;
        }
        this.manager.fetchVideos(this.lomo, this.videoStartIndex, n, fetchVideosCallback);
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
    }
    
    private boolean shouldLoadMoreData(final int n) {
        return n >= this.videoStartIndex - 20;
    }
    
    private void showErrorView() {
        this.frag.showErrorView();
    }
    
    protected View createDummyView() {
        final View view = new View((Context)this.activity);
        view.setVisibility(8);
        return view;
    }
    
    public int getCount() {
        return this.videoData.size();
    }
    
    public long getHeaderId(final int n) {
        return -1L;
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        View dummyView = view;
        if (view == null) {
            dummyView = this.createDummyView();
        }
        return dummyView;
    }
    
    public Video getItem(final int n) {
        return this.videoData.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final boolean b = false;
        if (this.activity.destroyed()) {
            Log.d("KidsLomoDetailAdapter", "activity destroyed - can't getView");
            return this.createDummyView();
        }
        Object o;
        if ((o = view) == null) {
            Log.v("KidsLomoDetailAdapter", "Creating Kids video view, type: " + this.lomo.getType());
            LinearLayout linearLayout;
            if (this.lomo.getType() == LoMoType.CONTINUE_WATCHING) {
                linearLayout = new KidsCwViewGroup<Object>((Context)this.activity, false);
            }
            else if (this.lomo.getType() == LoMoType.CHARACTERS) {
                linearLayout = new KidsCharacterViewGroup((Context)this.activity, false);
            }
            else {
                linearLayout = new KidsLoMoViewGroup<Object>((Context)this.activity, false);
            }
            ((VideoViewGroup)linearLayout).init(1);
            ((VideoViewGroup)linearLayout).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeRowHeight(this.activity, false)));
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361835);
            ((VideoViewGroup)linearLayout).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            o = linearLayout;
        }
        final Video item = this.getItem(n);
        final ArrayList<Video> list = new ArrayList<Video>();
        list.add(item);
        ((VideoViewGroup<Video, V>)o).updateDataThenViews(list, 1, 1, n, this.lomo);
        boolean b2 = b;
        if (this.hasMoreData) {
            b2 = b;
            if (!this.isLoading) {
                b2 = b;
                if (this.shouldLoadMoreData(n)) {
                    b2 = true;
                }
            }
        }
        if (b2) {
            this.fetchMoreData();
        }
        return (View)o;
    }
    
    protected void initLoadingState() {
        ThreadUtils.assertOnMain();
        Log.v("KidsLomoDetailAdapter", "initLoadingState()");
        this.isLoading = true;
        this.requestId = -2147483648L;
        this.hasMoreData = true;
        this.videoStartIndex = 0;
        this.videoData.clear();
        this.notifyDataSetChanged();
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        ThreadUtils.assertOnMain();
        Log.v("KidsLomoDetailAdapter", "notifyDataSetChanged(), count: " + this.getCount());
        if (this.getCount() > 0) {
            this.hideLoadingAndErrorViews();
        }
        else if (!this.isLoading) {
            this.showErrorView();
        }
    }
    
    public void onDestroyView() {
    }
    
    public void onManagerReady(final ServiceManager manager, final int n) {
        this.manager = manager;
        this.refreshData();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        this.manager = null;
    }
    
    public void onPause() {
    }
    
    public void onResume() {
    }
    
    public void refreshData() {
        Log.v("KidsLomoDetailAdapter", "Refreshing data");
        this.isLoading = true;
        this.initLoadingState();
        this.fetchMoreData();
    }
    
    public void setLoadingStatusCallback(final LoadingStatusCallback loadingStatusCallback) {
    }
    
    private class FetchVideosCallback extends LoggingManagerCallback
    {
        private final int numItems;
        private final long requestId;
        
        public FetchVideosCallback(final long requestId, final int numItems) {
            super("KidsLomoDetailAdapter");
            this.requestId = requestId;
            this.numItems = numItems;
        }
        
        private void handleResponse(final List<? extends Video> list, final int n) {
            KidsLomoDetailAdapter.this.hasMoreData = true;
            if (this.requestId != KidsLomoDetailAdapter.this.requestId) {
                Log.v("KidsLomoDetailAdapter", "Ignoring stale callback");
                return;
            }
            KidsLomoDetailAdapter.this.isLoading = false;
            if (n != 0) {
                Log.w("KidsLomoDetailAdapter", "Invalid status code");
                KidsLomoDetailAdapter.this.hasMoreData = false;
                KidsLomoDetailAdapter.this.notifyDataSetChanged();
                return;
            }
            if (list == null || list.size() <= 0) {
                Log.v("KidsLomoDetailAdapter", "No videos in response");
                KidsLomoDetailAdapter.this.hasMoreData = false;
                KidsLomoDetailAdapter.this.notifyDataSetChanged();
                return;
            }
            if (list.size() < this.numItems) {
                KidsLomoDetailAdapter.this.hasMoreData = false;
            }
            if (Log.isLoggable("KidsLomoDetailAdapter", 2)) {
                Log.v("KidsLomoDetailAdapter", "Got " + list.size() + " items, expected " + this.numItems + ", hasMoreData: " + KidsLomoDetailAdapter.this.hasMoreData);
            }
            KidsLomoDetailAdapter.this.videoData.addAll(list);
            KidsLomoDetailAdapter.access$412(KidsLomoDetailAdapter.this, list.size());
            KidsLomoDetailAdapter.this.notifyDataSetChanged();
        }
        
        @Override
        public void onCWVideosFetched(final List<CWVideo> list, final int n) {
            super.onCWVideosFetched(list, n);
            this.handleResponse(list, n);
        }
        
        @Override
        public void onVideosFetched(final List<Video> list, final int n) {
            super.onVideosFetched(list, n);
            this.handleResponse(list, n);
        }
    }
}
