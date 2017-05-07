// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;

public class ProgressiveCwAdapter extends BaseProgressiveRowAdapter<CWVideo>
{
    public ProgressiveCwAdapter(final BasePaginatedAdapter<CWVideo> basePaginatedAdapter, final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(basePaginatedAdapter, serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        Log.v("BaseProgressiveRowAdapter", "fetching for continue watching, start: " + n + ", end: " + n2);
        this.getManager().getBrowse().fetchCWVideos(n, n2, new FetchVideosHandler<Object>("BaseProgressiveRowAdapter", this, "CW", n, n2));
    }
}
