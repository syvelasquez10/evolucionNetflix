// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.ViewGroup;
import java.util.Collections;
import android.widget.LinearLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;

public class KidsLomoDetailAdapter extends BaseAdapter implements LoLoMoFrag$ILoLoMoAdapter
{
    public static final int NUM_VIDEOS_TO_FETCH_PER_BATCH = 20;
    private static final String TAG = "KidsLomoDetailAdapter";
    protected final NetflixActivity activity;
    private final LoLoMoFrag frag;
    private boolean hasMoreData;
    private boolean isLoading;
    private LoadingStatus$LoadingStatusCallback loadingStatusCallback;
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
        final KidsLomoDetailAdapter$FetchVideosCallback kidsLomoDetailAdapter$FetchVideosCallback = new KidsLomoDetailAdapter$FetchVideosCallback(this, this.requestId, n - this.videoStartIndex + 1);
        if (this.lomo.getType() == LoMoType.CONTINUE_WATCHING) {
            this.manager.getBrowse().fetchCWVideos(this.videoStartIndex, n, kidsLomoDetailAdapter$FetchVideosCallback);
            return;
        }
        if (this.lomo.getType() == LoMoType.FLAT_GENRE) {
            this.manager.getBrowse().fetchGenreVideos(new KidsLomoWrapper(this.lomo), this.videoStartIndex, n, false, kidsLomoDetailAdapter$FetchVideosCallback);
            return;
        }
        this.manager.getBrowse().fetchVideos(new KidsLomoWrapper(this.lomo), this.videoStartIndex, n, false, false, kidsLomoDetailAdapter$FetchVideosCallback);
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
    }
    
    private void onDataLoaded(final Status status) {
        if (this.loadingStatusCallback != null) {
            this.loadingStatusCallback.onDataLoaded(status);
        }
        if (status.isSucces()) {
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
        final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361978);
        final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361979);
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
    
    public List<Video> getItem(final int n) {
        return Collections.singletonList(this.videoData.get(n));
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    protected int getNumItemsPerPage() {
        return 1;
    }
    
    public View getView(final int n, View videoViewGroup, final ViewGroup viewGroup) {
        final boolean b = false;
        View dummyView;
        if (this.activity.destroyed()) {
            Log.d("KidsLomoDetailAdapter", "activity destroyed - can't getView");
            dummyView = this.createDummyView();
        }
        else {
            if (videoViewGroup == null) {
                Log.v("KidsLomoDetailAdapter", "Creating Kids video view, type: " + this.lomo.getType());
                videoViewGroup = (View)this.createVideoViewGroup();
            }
            final List<Video> item = this.getItem(n);
            ((VideoViewGroup)videoViewGroup).updateDataThenViews(item, this.getNumItemsPerPage(), n, 0, this.lomo);
            if (this.shouldReportPresentationTracking()) {
                LogUtils.reportPresentationTracking(this.manager, this.lomo, item.get(0), n);
            }
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
            dummyView = videoViewGroup;
            if (b2) {
                this.fetchMoreData();
                return videoViewGroup;
            }
        }
        return dummyView;
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
    
    public void onManagerReady(final ServiceManager manager, final Status status) {
        this.manager = manager;
        this.refreshData();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        this.manager = null;
    }
    
    public void onPause() {
    }
    
    public void onResume() {
    }
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
    }
    
    public void refreshData() {
        Log.v("KidsLomoDetailAdapter", "Refreshing data");
        this.isLoading = true;
        this.initLoadingState();
        this.fetchMoreData();
    }
    
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback loadingStatusCallback) {
        if (!this.isLoadingData() && loadingStatusCallback != null) {
            loadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.loadingStatusCallback = loadingStatusCallback;
    }
    
    protected boolean shouldReportPresentationTracking() {
        return true;
    }
}
