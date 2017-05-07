// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.Video;

public class ProgressiveIqAdapter<V extends Video> extends BaseProgressiveRowAdapter<V>
{
    public ProgressiveIqAdapter(final BasePaginatedAdapter<V> basePaginatedAdapter, final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(basePaginatedAdapter, serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        if (this.getLoMo() == null) {
            Log.w("BaseProgressiveRowAdapter", "IQ lomo pager - no lomo data to use for fetch request");
            return;
        }
        if (Log.isLoggable()) {
            Log.v("BaseProgressiveRowAdapter", "fetching for instant queue, start: " + n + ", end: " + n2);
        }
        final LoMo loMo = (LoMo)this.getLoMo();
        this.getManager().getBrowse().fetchIQVideos(loMo, n, n2, BrowseExperience.shouldLoadKubrickLeavesInLolomo(), new FetchVideosHandler<Object>("BaseProgressiveRowAdapter", (FetchVideosHandler$FetchCallback<Video>)this, loMo.getTitle(), n, n2));
    }
}
