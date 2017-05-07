// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.ProgressiveLoMoPagerAdapter;

public class KubrickHeroPagerAdapter extends ProgressiveLoMoPagerAdapter<KubrickVideo>
{
    public KubrickHeroPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<KubrickVideo> createPaginatedAdapter(final NetflixActivity netflixActivity) {
        return new KubrickPaginatedHeroAdapter((Context)netflixActivity);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        if (this.getLoMo() == null) {
            Log.w("BaseProgressivePagerAdapter", "lomo pager - no lomo data to use for fetch request");
            return;
        }
        final LoMo loMo = (LoMo)this.getLoMo();
        if (Log.isLoggable("BaseProgressivePagerAdapter", 2)) {
            Log.v("BaseProgressivePagerAdapter", String.format("fetching Kubrick videos: Title: %s, Type: %s, Total Vids: %d, Id: %s, start: %d, end: %d", loMo.getTitle(), loMo.getType(), loMo.getNumVideos(), loMo.getId(), n, n2));
        }
        this.getManager().getBrowse().fetchVideos(loMo, n, n2, false, true, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler$FetchCallback<Object>)this, loMo.getTitle(), n, n2));
    }
    
    @Override
    public boolean shouldOverlapPages() {
        return false;
    }
}
