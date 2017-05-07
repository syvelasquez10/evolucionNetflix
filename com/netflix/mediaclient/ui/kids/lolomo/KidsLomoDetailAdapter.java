// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.Trackable;
import java.util.Collections;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.widget.BaseAdapter;

public class KidsLomoDetailAdapter extends BaseAdapter implements ILoLoMoAdapter
{
    public static final int NUM_VIDEOS_TO_FETCH_PER_BATCH = 20;
    private static final String TAG = "KidsLomoDetailAdapter";
    protected final NetflixActivity activity;
    private final LoLoMoFrag frag;
    private boolean hasMoreData;
    private boolean isLoading;
    private LoadingStatusCallback loadingStatusCallback;
    private final BasicLoMo lomo;
    private ServiceManager manager;
    private long requestId;
    protected final List<Video> videoData;
    private int videoStartIndex;
    
    public KidsLomoDetailAdapter(final LoLoMoFrag frag, final BasicLoMo lomo) {
        this.isLoading = true;
        this.videoData = new ArrayList<Video>();
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.lomo = lomo;
    }
    
    static /* synthetic */ int access$312(final KidsLomoDetailAdapter kidsLomoDetailAdapter, int videoStartIndex) {
        videoStartIndex += kidsLomoDetailAdapter.videoStartIndex;
        return kidsLomoDetailAdapter.videoStartIndex = videoStartIndex;
    }
    
    private void fetchMoreData() {
        this.isLoading = true;
        this.requestId = System.nanoTime();
        final int n = this.videoStartIndex + 20 - 1;
        if (Log.isLoggable("KidsLomoDetailAdapter", 2)) {
            Log.v("KidsLomoDetailAdapter", "fetching data from: " + this.videoStartIndex + " to: " + n + ", id: " + this.lomo.getId());
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
            this.manager.fetchGenreVideos(new KidsLomoWrapper(this.lomo), this.videoStartIndex, n, fetchVideosCallback);
            return;
        }
        this.manager.fetchVideos(new KidsLomoWrapper(this.lomo), this.videoStartIndex, n, fetchVideosCallback);
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
    }
    
    private void onDataLoaded(final int n) {
        if (this.loadingStatusCallback != null) {
            this.loadingStatusCallback.onDataLoaded(n);
        }
        if (n == 0) {
            this.frag.onDataLoadSuccess();
        }
    }
    
    private boolean shouldLoadMoreData(final int n) {
        return n >= (this.videoStartIndex - 20) / this.getNumItemsPerPage();
    }
    
    private void showErrorView() {
        this.frag.showErrorView();
    }
    
    protected View createDummyView() {
        final View view = new View((Context)this.activity);
        view.setVisibility(8);
        return view;
    }
    
    protected VideoViewGroup<?, ?> createVideoViewGroup() {
        boolean b;
        if (this.lomo.getType() == LoMoType.CONTINUE_WATCHING) {
            b = true;
        }
        else {
            b = false;
        }
        LinearLayout linearLayout;
        if (b) {
            linearLayout = new KidsCwViewGroup<Object>((Context)this.activity, false);
        }
        else {
            linearLayout = new KidsLoMoViewGroup<Object>((Context)this.activity, false);
        }
        ((VideoViewGroup)linearLayout).init(1);
        final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361918);
        final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361919);
        ((VideoViewGroup)linearLayout).setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2);
        int n;
        if (b) {
            n = KidsUtils.computeHorizontalRowHeight(this.activity, false);
        }
        else {
            n = KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2, false);
        }
        ((VideoViewGroup)linearLayout).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, n));
        return (VideoViewGroup<?, ?>)linearLayout;
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
    
    public List<Video> getItem(final int n) {
        return Collections.singletonList(this.videoData.get(n));
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    protected int getNumItemsPerPage() {
        return 1;
    }
    
    public View getView(int n, final View view, final ViewGroup viewGroup) {
        if (this.activity.destroyed()) {
            Log.d("KidsLomoDetailAdapter", "activity destroyed - can't getView");
            return this.createDummyView();
        }
        Object videoViewGroup;
        if ((videoViewGroup = view) == null) {
            Log.v("KidsLomoDetailAdapter", "Creating Kids video view, type: " + this.lomo.getType());
            videoViewGroup = this.createVideoViewGroup();
        }
        final List<Video> item = this.getItem(n);
        ((VideoViewGroup<Video, V>)videoViewGroup).updateDataThenViews(item, this.getNumItemsPerPage(), 0, n, this.lomo);
        if (this.shouldReportPresentationTracking()) {
            LogUtils.reportPresentationTracking(this.manager, this.lomo, item.get(0), n);
        }
        if (this.hasMoreData && !this.isLoading && this.shouldLoadMoreData(n)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.fetchMoreData();
        }
        return (View)videoViewGroup;
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
        if (!this.isLoadingData() && loadingStatusCallback != null) {
            loadingStatusCallback.onDataLoaded(0);
            return;
        }
        this.loadingStatusCallback = loadingStatusCallback;
    }
    
    protected boolean shouldReportPresentationTracking() {
        return true;
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
            Label_0079: {
                if (n == 0) {
                    break Label_0079;
                }
                try {
                    Log.w("KidsLomoDetailAdapter", "Invalid status code");
                    KidsLomoDetailAdapter.this.hasMoreData = false;
                    KidsLomoDetailAdapter.this.notifyDataSetChanged();
                    return;
                    // iftrue(Label_0213:, !Log.isLoggable("KidsLomoDetailAdapter", 2))
                    // iftrue(Label_0147:, list.size() >= this.numItems)
                    // iftrue(Label_0125:, list != null && list.size() > 0)
                Label_0092:
                    while (true) {
                        Log.v("KidsLomoDetailAdapter", "Got " + list.size() + " items, expected " + this.numItems + ", hasMoreData: " + KidsLomoDetailAdapter.this.hasMoreData);
                        Label_0213: {
                            KidsLomoDetailAdapter.this.videoData.addAll(list);
                        }
                        KidsLomoDetailAdapter.access$312(KidsLomoDetailAdapter.this, list.size());
                        KidsLomoDetailAdapter.this.notifyDataSetChanged();
                        return;
                        Label_0125:
                        Block_5: {
                            break Block_5;
                            break Label_0092;
                        }
                        KidsLomoDetailAdapter.this.hasMoreData = false;
                        continue;
                    }
                    Log.v("KidsLomoDetailAdapter", "No videos in response");
                    KidsLomoDetailAdapter.this.hasMoreData = false;
                    KidsLomoDetailAdapter.this.notifyDataSetChanged();
                }
                finally {
                    KidsLomoDetailAdapter.this.onDataLoaded(n);
                }
            }
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
