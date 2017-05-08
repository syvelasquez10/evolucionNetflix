// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import java.util.List;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.Log;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.ui.lomo.BaseProgressiveRowAdapter;

public class ProgressiveDiscoveryAdapter extends BaseProgressiveRowAdapter<Discovery>
{
    protected static final String TAG = "ProgressiveDiscoveryAdapter";
    
    public ProgressiveDiscoveryAdapter(final BasePaginatedAdapter<Discovery> basePaginatedAdapter, final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(basePaginatedAdapter, serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        ((NetflixActivity)basePaginatedAdapter.getActivity()).registerReceiverLocallyWithAutoUnregister(new ProgressiveDiscoveryAdapter$1(this), "com.netflix.mediaclient.intent.action.DISCOVERY_LIST_UPDATED");
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        if (this.getLoMo() == null) {
            Log.w("ProgressiveDiscoveryAdapter", "discovery lomo pager - no lomo data to use for fetch request");
            return;
        }
        final LoMo loMo = (LoMo)this.getLoMo();
        if (Log.isLoggable()) {
            Log.v("ProgressiveDiscoveryAdapter", String.format("fetching discovery videos for: Title: %s, Type: %s, Total Vids: %d, Id: %s, start: %d, end: %d", loMo.getTitle(), loMo.getType(), loMo.getNumVideos(), loMo.getId(), n, n2));
        }
        this.getManager().getBrowse().fetchVideos(loMo, n, n2, false, false, false, new FetchVideosHandler<Object>("ProgressiveDiscoveryAdapter", (FetchVideosHandler$FetchCallback<Video>)this, loMo.getTitle(), n, n2));
    }
    
    @Override
    public boolean shouldOverlapPages() {
        return false;
    }
    
    @Override
    public void updateDataSet(final List<Discovery> list, final String s, final int n, final int n2) {
        super.updateDataSet(list, s, n, n2);
        if (Log.isLoggable() && list != null && !list.isEmpty()) {
            Log.v("ProgressiveDiscoveryAdapter", String.format("first title in dataSet: %s", list.get(0).getTitle()));
        }
        ((PaginatedDiscoveryAdapter)this.paginatedAdapter).updateAnimatorData(list);
    }
}
