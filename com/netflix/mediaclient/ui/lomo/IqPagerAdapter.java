// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class IqPagerAdapter extends ProgressiveLoMoPagerAdapter
{
    public static final String IQ_CACHE_TAG = "IQ";
    
    public IqPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, rowAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        Log.v("BaseProgressivePagerAdapter", "fetching for instant queue, start: " + n + ", end: " + n2);
        this.getManager().fetchIQVideos(n, n2, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler.FetchCallback<?>)this, "IQ", n, n2));
    }
}
