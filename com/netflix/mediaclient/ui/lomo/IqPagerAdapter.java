// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class IqPagerAdapter extends ProgressiveLoMoPagerAdapter
{
    public static final String IQ_CACHE_TAG = "IQ";
    
    public IqPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        if (this.getLoMo() == null) {
            Log.w("BaseProgressivePagerAdapter", "IQ lomo pager - no lomo data to use for fetch request");
            return;
        }
        if (Log.isLoggable("BaseProgressivePagerAdapter", 2)) {
            Log.v("BaseProgressivePagerAdapter", "fetching for instant queue, start: " + n + ", end: " + n2);
        }
        this.getManager().getBrowse().fetchIQVideos((LoMo)this.getLoMo(), n, n2, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", this, "IQ", n, n2));
    }
}
