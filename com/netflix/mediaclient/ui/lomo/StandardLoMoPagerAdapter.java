// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class StandardLoMoPagerAdapter extends ProgressiveLoMoPagerAdapter
{
    public StandardLoMoPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, rowAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        if (this.getLoMo() == null) {
            Log.w("BaseProgressivePagerAdapter", "standard lomo pager - no lomo data to use for fetch request");
            return;
        }
        final LoMo loMo = (LoMo)this.getLoMo();
        if (Log.isLoggable("BaseProgressivePagerAdapter", 2)) {
            Log.v("BaseProgressivePagerAdapter", String.format("fetching videos for: Title: %s, Type: %s, Total Vids: %d, Id: %s, start: %d, end: %d", loMo.getTitle(), loMo.getType(), loMo.getNumVideos(), loMo.getId(), n, n2));
        }
        this.getManager().getBrowse().fetchVideos(loMo, n, n2, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler.FetchCallback<Object>)this, loMo.getTitle(), n, n2));
    }
}
