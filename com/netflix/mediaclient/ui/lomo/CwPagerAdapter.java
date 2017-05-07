// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class CwPagerAdapter extends ProgressiveCwPagerAdapter
{
    public CwPagerAdapter(final ServiceManager serviceManager, final PagerAdapterCallbacks pagerAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, pagerAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        Log.v("BaseProgressivePagerAdapter", "fetching for continue watching, start: " + n + ", end: " + n2);
        this.getManager().fetchCWVideos(n, n2, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler.FetchCallback<?>)this, "CW", n, n2));
    }
}
