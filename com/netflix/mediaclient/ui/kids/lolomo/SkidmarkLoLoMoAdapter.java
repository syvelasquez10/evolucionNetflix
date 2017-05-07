// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.util.Triple;
import android.widget.TextView;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.MathUtils;
import java.util.Map;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.view.View;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.widget.BaseAdapter;

public class SkidmarkLoLoMoAdapter extends BaseAdapter implements ILoLoMoAdapter
{
    private static final int ITEM_TYPE_CHARACTER = 3;
    private static final int ITEM_TYPE_CW = 0;
    private static final int ITEM_TYPE_MORE_BUTTON = 2;
    private static final int ITEM_TYPE_SPACER = 4;
    private static final int ITEM_TYPE_STANDARD = 1;
    private static final String TAG = "SkidmarkLoLoMoAdapter";
    protected final NetflixActivity activity;
    private final BroadcastReceiver browseReceiver;
    private int callbackCount;
    private final View dummyView;
    protected final LoLoMoFrag frag;
    private boolean isLoading;
    private LoadingStatusCallback loadingStatusCallback;
    private final LinkedHashMap<LoMo, List<Video>> lomoData;
    private long lomoRequestId;
    protected ServiceManager manager;
    private final Video moreButtonPlaceholder;
    private long prefetchRequestId;
    private final Video spacerPlaceholder;
    
    public SkidmarkLoLoMoAdapter(final LoLoMoFrag frag) {
        this.isLoading = true;
        this.lomoData = new LinkedHashMap<LoMo, List<Video>>();
        this.moreButtonPlaceholder = new MoreButtonPlaceholderVideo();
        this.spacerPlaceholder = new ListSpacerPlaceholderVideo();
        this.browseReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null) {
                    Log.w("SkidmarkLoLoMoAdapter", "Received null intent");
                }
                else {
                    final String action = intent.getAction();
                    if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                        Log.v("SkidmarkLoLoMoAdapter", "browseReceiver inovoked with Action: " + action);
                    }
                    if ("com.netflix.mediaclient.intent.action.BA_CW_REFRESH".equals(action)) {
                        SkidmarkLoLoMoAdapter.this.refreshCwData();
                    }
                }
            }
        };
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.dummyView = new View((Context)this.activity);
        this.registerBrowseNotificationReceiver();
    }
    
    private LoMo getLomo(final int n) {
        return this.getItem(n).first;
    }
    
    private LoMo getLomoByType(final LoMoType loMoType) {
        for (final LoMo loMo : this.lomoData.keySet()) {
            if (loMo.getType() == loMoType) {
                return loMo;
            }
        }
        return null;
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
        this.manager.getBrowse().fetchLoMos(0, 19, new FetchLoMoCallback(this.lomoRequestId));
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
    }
    
    private void onDataLoaded(final Status status) {
        if (this.loadingStatusCallback != null) {
            this.loadingStatusCallback.onDataLoaded(status);
        }
    }
    
    private void refreshCwData() {
        if (this.manager == null) {
            Log.d("SkidmarkLoLoMoAdapter", "Service man is null - can't refresh CW data");
            return;
        }
        if (this.getLomoByType(LoMoType.CONTINUE_WATCHING) == null) {
            Log.v("SkidmarkLoLoMoAdapter", "No CW lomo in data - skipping refresh");
            return;
        }
        Log.v("SkidmarkLoLoMoAdapter", "Refreshing CW data...");
        this.manager.getBrowse().fetchCWVideos(0, 2, new RefreshCwCallback());
    }
    
    private void registerBrowseNotificationReceiver() {
        if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
            Log.v("SkidmarkLoLoMoAdapter", "Registering browse notification receiver");
        }
        this.activity.registerReceiver(this.browseReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.BA_CW_REFRESH"));
    }
    
    private boolean shouldAddMoreButton(final LoMo loMo, final List<Video> list) {
        boolean b = true;
        Log.v("SkidmarkLoLoMoAdapter", "Lomo " + loMo.getType() + " has " + loMo.getNumVideos() + " videos, current count: " + list.size());
        if (loMo.getType() == LoMoType.CONTINUE_WATCHING) {
            b = false;
        }
        else if (loMo.getType() == LoMoType.CHARACTERS) {
            if (list.size() <= 7) {
                return false;
            }
        }
        else if (loMo.getNumVideos() <= 5) {
            return false;
        }
        return b;
    }
    
    private void showErrorView() {
        this.frag.showErrorView();
    }
    
    private void unregisterBrowseNotificationReceiver() {
        if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
            Log.v("SkidmarkLoLoMoAdapter", "Unregistering browse notification receiver");
        }
        this.activity.unregisterReceiver(this.browseReceiver);
    }
    
    public int getCount() {
        int n = 0;
        for (final Map.Entry<LoMo, List<Video>> entry : this.lomoData.entrySet()) {
            final List<Video> list = entry.getValue();
            int n2;
            if (entry.getKey().getType() == LoMoType.CHARACTERS) {
                n2 = MathUtils.ceiling(list.size(), 2);
            }
            else {
                n2 = list.size();
            }
            n += n2;
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
            inflate = this.activity.getLayoutInflater().inflate(2130903110, (ViewGroup)null);
        }
        ((TextView)inflate).setText((CharSequence)this.getLomo(n).getTitle());
        return inflate;
    }
    
    public Triple<LoMo, List<Video>, Integer> getItem(int n) {
        int n2 = n;
        for (final Map.Entry<LoMo, List<Video>> entry : this.lomoData.entrySet()) {
            final List<Video> list = entry.getValue();
            boolean b;
            if (entry.getKey().getType() == LoMoType.CHARACTERS) {
                b = true;
            }
            else {
                b = false;
            }
            int n3;
            if (b) {
                n3 = MathUtils.ceiling(list.size(), 2);
            }
            else {
                n3 = list.size();
            }
            if (n2 < n3) {
                if (b) {
                    n = n2 * 2;
                }
                else {
                    n = n2;
                }
                int n4;
                if (b) {
                    n4 = n + 2;
                }
                else {
                    n4 = n + 1;
                }
                return new Triple<LoMo, List<Video>, Integer>(entry.getKey(), list.subList(n, Math.min(n4, list.size())), n2);
            }
            n2 -= n3;
        }
        throw new IllegalStateException("Could not find item for position: " + n + ", curr pos: " + n2);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        final Video video = this.getItem(n).second.get(0);
        if (video instanceof MoreButtonPlaceholderVideo) {
            return 2;
        }
        if (video instanceof ListSpacerPlaceholderVideo) {
            return 4;
        }
        if (video instanceof CWVideo) {
            return 0;
        }
        if (video.getType() == VideoType.CHARACTERS) {
            return 3;
        }
        return 1;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (this.activity.destroyed()) {
            Log.d("SkidmarkLoLoMoAdapter", "activity destroyed - can't getView");
            return this.dummyView;
        }
        final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361933);
        final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361932);
        final int itemViewType = this.getItemViewType(n);
        Object o;
        if ((o = view) == null) {
            switch (itemViewType) {
                default: {
                    throw new IllegalStateException("Unknown view type");
                }
                case 0: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating Kids CW view");
                    o = new KidsCwViewGroup((Context)this.activity, false);
                    ((VideoViewGroup)o).init(1);
                    ((KidsCwViewGroup)o).setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize);
                    ((KidsCwViewGroup)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize, true)));
                    break;
                }
                case 1: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating Kids video view");
                    o = new KidsLoMoViewGroup((Context)this.activity, false);
                    ((VideoViewGroup)o).init(1);
                    ((VideoViewGroup)o).setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize);
                    ((VideoViewGroup)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize, false)));
                    break;
                }
                case 2: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating more button view");
                    o = new SkidmarkMoreButton(this.activity);
                    break;
                }
                case 3: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating character view");
                    o = new KidsCharacterViewGroup((Context)this.activity, false);
                    ((VideoViewGroup)o).init(2);
                    ((KidsCharacterViewGroup)o).setPadding(dimensionPixelSize2, 0, 0, 0);
                    ((KidsCharacterViewGroup)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkCharacterViewSize(this.activity)));
                    break;
                }
                case 4: {
                    Log.v("SkidmarkLoLoMoAdapter", "Creating spacer view");
                    o = new View((Context)this.activity);
                    ((View)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361934)));
                    break;
                }
            }
        }
        final Triple<LoMo, List<Video>, Integer> item = this.getItem(n);
        if (o instanceof KidsCharacterViewGroup) {
            ((KidsCwViewGroup)o).updateDataThenViews(item.second, 2, item.third, n, item.first);
        }
        else if (o instanceof VideoViewGroup) {
            ((VideoViewGroup<Video, V>)o).updateDataThenViews(item.second, 1, item.third, n, item.first);
        }
        else if (o instanceof SkidmarkMoreButton) {
            ((SkidmarkMoreButton)o).update(item.first);
        }
        if (itemViewType == 0 || itemViewType == 1) {
            LogUtils.reportPresentationTracking(this.manager, item.first, item.second.get(0), item.third);
        }
        return (View)o;
    }
    
    public int getViewTypeCount() {
        return 5;
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
        if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
            Log.v("SkidmarkLoLoMoAdapter", "notifyDataSetChanged(), count: " + this.getCount());
        }
        if (this.getCount() > 0) {
            this.hideLoadingAndErrorViews();
        }
        else if (!this.isLoading) {
            this.showErrorView();
        }
    }
    
    public void onDestroyView() {
        Log.v("SkidmarkLoLoMoAdapter", "Destroying adapter");
        this.unregisterBrowseNotificationReceiver();
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
    
    public void refreshData() {
        if (this.manager == null) {
            Log.w("SkidmarkLoLoMoAdapter", "Service man is null");
            return;
        }
        Log.v("SkidmarkLoLoMoAdapter", "refreshData()");
        this.initLoadingState();
        this.prefetchRequestId = System.nanoTime();
        this.manager.getBrowse().prefetchLoLoMo(0, 19, 0, 4, 0, 2, this.activity.isForKids(), false, new LoggingManagerCallback("SkidmarkLoLoMoAdapter") {
            final /* synthetic */ long val$requestIdClone = SkidmarkLoLoMoAdapter.this.prefetchRequestId;
            
            @Override
            public void onLoLoMoPrefetched(final Status status) {
                super.onLoLoMoPrefetched(status);
                if (!status.isSucces()) {
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
        if (!this.isLoadingData() && loadingStatusCallback != null) {
            loadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.loadingStatusCallback = loadingStatusCallback;
    }
    
    private class FetchLoMoCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchLoMoCallback(final long requestId) {
            super("SkidmarkLoLoMoAdapter");
            this.requestId = requestId;
        }
        
        private void handleResult(final List<LoMo> list, final Status status) {
            if (this.requestId != SkidmarkLoLoMoAdapter.this.lomoRequestId) {
                Log.v("SkidmarkLoLoMoAdapter", "Ignoring stale onLoMosFetched callback");
            }
            else {
                if (status.isError()) {
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
                    SkidmarkLoLoMoAdapter.this.lomoData.put(loMo, new ArrayList(10));
                    Log.v("SkidmarkLoLoMoAdapter", "Fetching videos for lomo: " + loMo.getId() + ", type: " + loMo.getType());
                    if (loMo.getType() == LoMoType.CONTINUE_WATCHING) {
                        SkidmarkLoLoMoAdapter.this.manager.getBrowse().fetchCWVideos(0, 2, new FetchVideosCallback(loMo));
                    }
                    else {
                        SkidmarkLoLoMoAdapter.this.manager.getBrowse().fetchVideos(loMo, 0, 4, new FetchVideosCallback(loMo));
                    }
                    if (loMo.getType() == LoMoType.CHARACTERS) {
                        SkidmarkLoLoMoAdapter.this.callbackCount++;
                        SkidmarkLoLoMoAdapter.this.manager.getBrowse().fetchVideos(loMo, 5, 10 - 1, new FetchVideosCallback(loMo));
                    }
                }
            }
        }
        
        @Override
        public void onLoMosFetched(final List<LoMo> list, final Status status) {
            super.onLoMosFetched(list, status);
            this.handleResult(list, status);
        }
    }
    
    private class FetchVideosCallback extends LoggingManagerCallback
    {
        private final LoMo lomo;
        
        public FetchVideosCallback(final LoMo lomo) {
            super("SkidmarkLoLoMoAdapter");
            this.lomo = lomo;
        }
        
        private void handleResponse(final List<? extends Video> list, final Status status) {
            SkidmarkLoLoMoAdapter.this.callbackCount--;
            if (status.isError()) {
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
                if (list2.size() == 0) {
                    list2.add(SkidmarkLoLoMoAdapter.this.spacerPlaceholder);
                    if (this.lomo.getType() == LoMoType.CHARACTERS) {
                        list2.add(SkidmarkLoLoMoAdapter.this.spacerPlaceholder);
                    }
                }
                list2.addAll(list);
                if (SkidmarkLoLoMoAdapter.this.shouldAddMoreButton(this.lomo, list2)) {
                    list2.add(SkidmarkLoLoMoAdapter.this.moreButtonPlaceholder);
                }
                if (SkidmarkLoLoMoAdapter.this.callbackCount <= 0) {
                    SkidmarkLoLoMoAdapter.this.isLoading = false;
                    SkidmarkLoLoMoAdapter.this.onDataLoaded(status);
                    SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
                }
            }
        }
        
        @Override
        public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
            super.onCWVideosFetched(list, status);
            this.handleResponse(list, status);
        }
        
        @Override
        public void onVideosFetched(final List<Video> list, final Status status) {
            super.onVideosFetched(list, status);
            this.handleResponse(list, status);
        }
    }
    
    private static class ListSpacerPlaceholderVideo extends Summary
    {
    }
    
    private static class MoreButtonPlaceholderVideo extends Summary
    {
    }
    
    private class RefreshCwCallback extends LoggingManagerCallback
    {
        public RefreshCwCallback() {
            super("SkidmarkLoLoMoAdapter");
        }
        
        @Override
        public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
            super.onCWVideosFetched(list, status);
            if (status.isError()) {
                Log.w("SkidmarkLoLoMoAdapter", "Invalid status code for CW refresh");
                return;
            }
            if (list == null) {
                Log.d("SkidmarkLoLoMoAdapter", "CW videos list is null");
                return;
            }
            final LoMo access$1200 = SkidmarkLoLoMoAdapter.this.getLomoByType(LoMoType.CONTINUE_WATCHING);
            if (access$1200 == null) {
                Log.d("SkidmarkLoLoMoAdapter", "CW lomo is now null - aborting refresh operation");
                return;
            }
            if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                Log.v("SkidmarkLoLoMoAdapter", "Got " + list.size() + " CW videos - adding to existing lomo data");
            }
            final List<Video> list2 = SkidmarkLoLoMoAdapter.this.lomoData.get(access$1200);
            list2.clear();
            list2.add(SkidmarkLoLoMoAdapter.this.spacerPlaceholder);
            list2.addAll(list);
            if (SkidmarkLoLoMoAdapter.this.shouldAddMoreButton(access$1200, list2)) {
                list2.add(SkidmarkLoLoMoAdapter.this.moreButtonPlaceholder);
            }
            SkidmarkLoLoMoAdapter.this.notifyDataSetChanged();
            SkidmarkLoLoMoAdapter.this.onDataLoaded(status);
        }
    }
}
