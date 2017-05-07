// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import java.util.Collection;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.Map;
import android.util.Pair;
import android.widget.TextView;
import android.view.ViewGroup;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoMo;
import java.util.LinkedHashMap;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.widget.BaseAdapter;

public class SkidmarkLoLoMoAdapter extends BaseAdapter implements ILoLoMoAdapter
{
    private static final int ITEM_TYPE_CHARACTER = 3;
    private static final int ITEM_TYPE_CW = 0;
    private static final int ITEM_TYPE_MORE_BUTTON = 2;
    private static final int ITEM_TYPE_STANDARD = 1;
    private static final int NUM_CW_VIDEOS_PER_BATCH = 3;
    public static final int NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    private static final int NUM_VIDEOS_PER_BATCH = 5;
    private static final String TAG = "SkidmarkLoLoMoAdapter";
    protected final NetflixActivity activity;
    private int callbackCount;
    private final View dummyView;
    protected final LoLoMoFrag frag;
    private boolean isLoading;
    private final LinkedHashMap<LoMo, List<Video>> lomoData;
    private long lomoRequestId;
    protected ServiceManager manager;
    private final Video moreButtonPlaceholder;
    private long prefetchRequestId;
    
    public SkidmarkLoLoMoAdapter(final LoLoMoFrag frag) {
        this.isLoading = true;
        this.lomoData = new LinkedHashMap<LoMo, List<Video>>();
        this.moreButtonPlaceholder = new MoreButtonPlaceholderVideo();
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.dummyView = new View((Context)this.activity);
    }
    
    private LoMo getLomo(final int n) {
        return (LoMo)this.getItem(n).first;
    }
    
    private void handlePrefetchComplete() {
        if (this.manager == null) {
            Log.w("SkidmarkLoLoMoAdapter", "Manager is null - can't refresh data");
            return;
        }
        if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
            Log.v("SkidmarkLoLoMoAdapter", "handlePrefetchComplete(), fetching lomos from: 0 to: 19");
        }
        this.lomoRequestId = System.nanoTime();
        this.manager.fetchLoMos("lolomo", 0, 19, new FetchLoMoCallbacks(this.lomoRequestId));
    }
    
    private boolean hasMoreVideos(final LoMo loMo) {
        if (loMo.getType() == LoMoType.CONTINUE_WATCHING) {
            if (loMo.getNumVideos() <= 3) {
                return false;
            }
        }
        else if (loMo.getNumVideos() <= 5) {
            return false;
        }
        return true;
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
    }
    
    private void showErrorView() {
        this.frag.showErrorView();
    }
    
    public int getCount() {
        int n = 0;
        final Iterator<List<Video>> iterator = this.lomoData.values().iterator();
        while (iterator.hasNext()) {
            n += iterator.next().size();
        }
        return n;
    }
    
    public long getHeaderId(final int n) {
        return this.getLomo(n).hashCode();
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            Log.v("SkidmarkLoLoMoAdapter", "Creating header view");
            inflate = this.activity.getLayoutInflater().inflate(2130903101, (ViewGroup)null);
        }
        ((TextView)inflate).setText((CharSequence)this.getLomo(n).getTitle());
        return inflate;
    }
    
    public Pair<LoMo, Video> getItem(final int n) {
        int n2 = n;
        for (final Map.Entry<LoMo, List<Video>> entry : this.lomoData.entrySet()) {
            final List<Video> list = entry.getValue();
            if (n2 < list.size()) {
                return (Pair<LoMo, Video>)new Pair((Object)entry.getKey(), (Object)list.get(n2));
            }
            n2 -= list.size();
        }
        throw new IllegalStateException("Could not find item for position: " + n + ", curr pos: " + n2);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        final Pair<LoMo, Video> item = this.getItem(n);
        if (item.second instanceof MoreButtonPlaceholderVideo) {
            return 2;
        }
        if (item.second instanceof CWVideo) {
            return 0;
        }
        if (((Video)item.second).getType() == VideoType.CHARACTERS) {
            return 3;
        }
        return 1;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (this.activity.destroyed()) {
            Log.d("SkidmarkLoLoMoAdapter", "activity destroyed - can't getView");
            return this.dummyView;
        }
        Object inflate;
        if ((inflate = view) == null) {
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361835);
            switch (this.getItemViewType(n)) {
                default: {
                    throw new IllegalStateException("Unknown view type");
                }
                case 0: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating Kids CW view");
                    if (KidsUtils.shouldShowHorizontalImages(this.activity)) {
                        inflate = new KidsHorizontalCwView((Context)this.activity, false);
                    }
                    else {
                        inflate = new KidsOneToOneCwView((Context)this.activity, false);
                    }
                    ((View)inflate).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                    break;
                }
                case 1: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating Kids video view");
                    inflate = new KidsLoMoViewGroup((Context)this.activity, false);
                    ((VideoViewGroup)inflate).init(1);
                    ((VideoViewGroup)inflate).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeRowHeight(this.activity, false)));
                    ((VideoViewGroup)inflate).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                    break;
                }
                case 2: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating more button view");
                    inflate = this.activity.getLayoutInflater().inflate(2130903100, (ViewGroup)null);
                    ((View)inflate).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            final LoMo loMo = (LoMo)view.getTag();
                            if (loMo == null) {
                                Log.w("SkidmarkLoLoMoAdapter", "Null lomo tag pulled from view: " + view);
                                return;
                            }
                            KidsLomoDetailActivity.show(SkidmarkLoLoMoAdapter.this.activity, loMo);
                        }
                    });
                    break;
                }
                case 3: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating character view");
                    inflate = new KidsCharacterView((Context)this.activity, false);
                    ((View)inflate).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                    break;
                }
            }
        }
        if (inflate instanceof VideoViewGroup.IVideoView) {
            final Pair<LoMo, Video> item = this.getItem(n);
            ((VideoViewGroup.IVideoView<Object>)inflate).update(item.second, (Trackable)item.first, n, true);
        }
        else if (inflate instanceof VideoViewGroup) {
            final Pair<LoMo, Video> item2 = this.getItem(n);
            final ArrayList<Object> list = new ArrayList<Object>();
            list.add(item2.second);
            ((VideoViewGroup<Object, V>)inflate).updateDataThenViews(list, 1, 1, n, (Trackable)item2.first);
        }
        else if (inflate instanceof TextView) {
            ((View)inflate).setTag((Object)this.getLomo(n));
        }
        return (View)inflate;
    }
    
    public int getViewTypeCount() {
        return 4;
    }
    
    protected void initLoadingState() {
        ThreadUtils.assertOnMain();
        Log.v("SkidmarkLoLoMoAdapter", "initLoadingState()");
        this.isLoading = true;
        this.lomoData.clear();
        this.notifyDataSetChanged();
        this.lomoRequestId = -2147483648L;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        ThreadUtils.assertOnMain();
        Log.v("SkidmarkLoLoMoAdapter", "notifyDataSetChanged(), count: " + this.getCount());
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
        if (this.manager == null) {
            Log.w("SkidmarkLoLoMoAdapter", "Service man is null");
            return;
        }
        Log.v("SkidmarkLoLoMoAdapter", "refreshData()");
        this.initLoadingState();
        this.prefetchRequestId = System.nanoTime();
        this.manager.prefetchLoLoMo(0, 19, 0, 4, 0, 2, false, new LoggingManagerCallback("SkidmarkLoLoMoAdapter") {
            final /* synthetic */ long val$requestIdClone = SkidmarkLoLoMoAdapter.this.prefetchRequestId;
            
            @Override
            public void onLoLoMoPrefetched(final int n) {
                super.onLoLoMoPrefetched(n);
                if (n != 0) {
                    Log.w("SkidmarkLoLoMoAdapter", "Prefetch failed");
                    SkidmarkLoLoMoAdapter.this.showErrorView();
                    return;
                }
                if (this.val$requestIdClone != SkidmarkLoLoMoAdapter.this.prefetchRequestId) {
                    Log.d("SkidmarkLoLoMoAdapter", "Ignoring stale prefetch request id");
                    return;
                }
                SkidmarkLoLoMoAdapter.this.handlePrefetchComplete();
            }
        });
    }
    
    public void setLoadingStatusCallback(final LoadingStatusCallback loadingStatusCallback) {
    }
    
    private class FetchLoMoCallbacks extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchLoMoCallbacks(final long requestId) {
            super("SkidmarkLoLoMoAdapter");
            this.requestId = requestId;
        }
        
        private void handleResult(final List<LoMo> list, final int n) {
            if (this.requestId != SkidmarkLoLoMoAdapter.this.lomoRequestId) {
                Log.v("SkidmarkLoLoMoAdapter", "Ignoring stale onLoMosFetched callback");
            }
            else {
                SkidmarkLoLoMoAdapter.this.isLoading = false;
                if (n != 0) {
                    Log.w("SkidmarkLoLoMoAdapter", "Invalid status code");
                    SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (list == null || list.size() <= 0) {
                    Log.v("SkidmarkLoLoMoAdapter", "No loMos in response");
                    SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                    Log.v("SkidmarkLoLoMoAdapter", "Got " + list.size() + " items");
                }
                SkidmarkLoLoMoAdapter.this.lomoData.clear();
                SkidmarkLoLoMoAdapter.this.callbackCount = list.size();
                for (final LoMo loMo : list) {
                    SkidmarkLoLoMoAdapter.this.lomoData.put(loMo, new ArrayList());
                    Log.v("SkidmarkLoLoMoAdapter", "Fetching videos for lomo: " + loMo.getId() + ", type: " + loMo.getType());
                    if (loMo.getType() == LoMoType.CONTINUE_WATCHING) {
                        SkidmarkLoLoMoAdapter.this.manager.fetchCWVideos(0, 2, new FetchVideosCallback(loMo));
                    }
                    else {
                        SkidmarkLoLoMoAdapter.this.manager.fetchVideos(loMo, 0, 4, new FetchVideosCallback(loMo));
                    }
                }
            }
        }
        
        @Override
        public void onLoMosFetched(final List<LoMo> list, final int n) {
            super.onLoMosFetched(list, n);
            this.handleResult(list, n);
        }
    }
    
    private class FetchVideosCallback extends LoggingManagerCallback
    {
        private final LoMo lomo;
        
        public FetchVideosCallback(final LoMo lomo) {
            super("SkidmarkLoLoMoAdapter");
            this.lomo = lomo;
        }
        
        private void handleResponse(final List<? extends Video> list, final int n) {
            SkidmarkLoLoMoAdapter.this.callbackCount--;
            if (n != 0) {
                Log.w("SkidmarkLoLoMoAdapter", "Invalid status code");
                SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
            }
            else {
                if (list == null || list.size() <= 0) {
                    Log.v("SkidmarkLoLoMoAdapter", "No videos in response");
                    SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                    Log.v("SkidmarkLoLoMoAdapter", "Got " + list.size() + " items, callback count: " + SkidmarkLoLoMoAdapter.this.callbackCount);
                }
                final List<Video> list2 = SkidmarkLoLoMoAdapter.this.lomoData.get(this.lomo);
                list2.clear();
                list2.addAll(list);
                if (SkidmarkLoLoMoAdapter.this.hasMoreVideos(this.lomo)) {
                    list2.add(SkidmarkLoLoMoAdapter.this.moreButtonPlaceholder);
                }
                if (SkidmarkLoLoMoAdapter.this.callbackCount <= 0) {
                    SkidmarkLoLoMoAdapter.this.isLoading = false;
                    SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
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
    
    private static class MoreButtonPlaceholderVideo implements Video
    {
        @Override
        public String getBoxshotURL() {
            return "dummyimage.com/600x338/000/fff.png&text=600+by+338+dummy+img";
        }
        
        @Override
        public VideoType getErrorType() {
            return null;
        }
        
        @Override
        public String getHorzDispUrl() {
            return null;
        }
        
        @Override
        public String getId() {
            return this.getTitle();
        }
        
        @Override
        public String getTitle() {
            return "more button placeholder";
        }
        
        @Override
        public String getTvCardUrl() {
            return null;
        }
        
        @Override
        public VideoType getType() {
            return VideoType.UNAVAILABLE;
        }
    }
}
