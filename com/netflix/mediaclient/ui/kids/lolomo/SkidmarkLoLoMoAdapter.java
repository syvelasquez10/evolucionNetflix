// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
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
import com.netflix.mediaclient.Log;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.android.app.Status;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.view.View;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;

public class SkidmarkLoLoMoAdapter extends BaseAdapter implements LoLoMoFrag$ILoLoMoAdapter
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
    private LoadingStatus$LoadingStatusCallback loadingStatusCallback;
    private final LinkedHashMap<LoMo, List<Video>> lomoData;
    private long lomoRequestId;
    protected ServiceManager manager;
    private final Video moreButtonPlaceholder;
    private long prefetchRequestId;
    private final Video spacerPlaceholder;
    
    public SkidmarkLoLoMoAdapter(final LoLoMoFrag frag) {
        this.isLoading = true;
        this.lomoData = new LinkedHashMap<LoMo, List<Video>>();
        this.moreButtonPlaceholder = new SkidmarkLoLoMoAdapter$MoreButtonPlaceholderVideo();
        this.spacerPlaceholder = new SkidmarkLoLoMoAdapter$ListSpacerPlaceholderVideo();
        this.browseReceiver = new SkidmarkLoLoMoAdapter$2(this);
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
        this.manager.getBrowse().fetchLoMos(0, 19, new SkidmarkLoLoMoAdapter$FetchLoMoCallback(this, this.lomoRequestId));
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
        this.manager.getBrowse().fetchCWVideos(0, 2, new SkidmarkLoLoMoAdapter$RefreshCwCallback(this));
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
        final Iterator<Map.Entry<LoMo, List<Video>>> iterator = this.lomoData.entrySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final Map.Entry<LoMo, List<Video>> entry = iterator.next();
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
    
    public View getHeaderView(final int n, View inflate, final ViewGroup viewGroup) {
        if (inflate == null) {
            Log.v("SkidmarkLoLoMoAdapter", "Creating header view");
            inflate = this.activity.getLayoutInflater().inflate(2130903111, (ViewGroup)null);
        }
        ((TextView)inflate).setText((CharSequence)this.getLomo(n).getTitle());
        return inflate;
    }
    
    public Triple<LoMo, List<Video>, Integer> getItem(int n) {
        final Iterator<Map.Entry<LoMo, List<Video>>> iterator = this.lomoData.entrySet().iterator();
        int n2 = n;
        while (iterator.hasNext()) {
            final Map.Entry<LoMo, List<Video>> entry = iterator.next();
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
        if (video instanceof SkidmarkLoLoMoAdapter$MoreButtonPlaceholderVideo) {
            return 2;
        }
        if (video instanceof SkidmarkLoLoMoAdapter$ListSpacerPlaceholderVideo) {
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
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        if (!this.activity.destroyed()) {
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361974);
            final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361973);
            final int itemViewType = this.getItemViewType(n);
            if (view == null) {
                switch (itemViewType) {
                    default: {
                        throw new IllegalStateException("Unknown view type");
                    }
                    case 0: {
                        Log.v("SkidmarkLoLoMoAdapter", "Creating Kids CW view");
                        view = (View)new KidsCwViewGroup((Context)this.activity, false);
                        ((VideoViewGroup)view).init(1);
                        ((KidsCwViewGroup)view).setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize);
                        ((KidsCwViewGroup)view).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize, true)));
                        break;
                    }
                    case 1: {
                        Log.v("SkidmarkLoLoMoAdapter", "Creating Kids video view");
                        view = (View)new KidsLoMoViewGroup((Context)this.activity, false);
                        ((VideoViewGroup)view).init(1);
                        ((VideoViewGroup)view).setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize);
                        ((VideoViewGroup)view).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize, false)));
                        break;
                    }
                    case 2: {
                        Log.v("SkidmarkLoLoMoAdapter", "Creating more button view");
                        view = (View)new SkidmarkMoreButton(this.activity);
                        break;
                    }
                    case 3: {
                        Log.v("SkidmarkLoLoMoAdapter", "Creating character view");
                        view = (View)new KidsCharacterViewGroup((Context)this.activity, false);
                        ((VideoViewGroup)view).init(2);
                        ((KidsCharacterViewGroup)view).setPadding(dimensionPixelSize2, 0, 0, 0);
                        ((KidsCharacterViewGroup)view).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkCharacterViewSize(this.activity)));
                        break;
                    }
                    case 4: {
                        Log.v("SkidmarkLoLoMoAdapter", "Creating spacer view");
                        view = new View((Context)this.activity);
                        view.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361975)));
                        break;
                    }
                }
            }
            final Triple<LoMo, List<Video>, Integer> item = this.getItem(n);
            if (view instanceof KidsCharacterViewGroup) {
                ((VideoViewGroup)view).updateDataThenViews(item.second, 2, item.third, n, item.first);
            }
            else if (view instanceof VideoViewGroup) {
                ((VideoViewGroup)view).updateDataThenViews(item.second, 1, item.third, n, item.first);
            }
            else if (view instanceof SkidmarkMoreButton) {
                ((SkidmarkMoreButton)view).update(item.first);
            }
            if (itemViewType != 0) {
                final View dummyView = view;
                if (itemViewType != 1) {
                    return dummyView;
                }
            }
            LogUtils.reportPresentationTracking(this.manager, item.first, item.second.get(0), item.third);
            return view;
        }
        Log.d("SkidmarkLoLoMoAdapter", "activity destroyed - can't getView");
        return this.dummyView;
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
        this.manager.getBrowse().prefetchLoLoMo(0, 19, 0, 4, 0, 2, this.activity.isForKids(), false, new SkidmarkLoLoMoAdapter$1(this, "SkidmarkLoLoMoAdapter", this.prefetchRequestId));
    }
    
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback loadingStatusCallback) {
        if (!this.isLoadingData() && loadingStatusCallback != null) {
            loadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.loadingStatusCallback = loadingStatusCallback;
    }
}
