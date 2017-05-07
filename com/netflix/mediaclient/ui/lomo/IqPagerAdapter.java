// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedIqAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;

public class IqPagerAdapter<T extends Video> extends ProgressiveLoMoPagerAdapter<T>
{
    public IqPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<T> createPaginatedAdapter(final NetflixActivity netflixActivity) {
        if (netflixActivity.isKubrick()) {
            return (BasePaginatedAdapter<T>)new KubrickPaginatedIqAdapter((Context)netflixActivity);
        }
        return super.createPaginatedAdapter(netflixActivity);
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
        final LoMo loMo = (LoMo)this.getLoMo();
        this.getManager().getBrowse().fetchIQVideos(loMo, n, n2, this.getManager().getActivity().isKubrick(), new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler$FetchCallback<Object>)this, loMo.getTitle(), n, n2));
    }
}
