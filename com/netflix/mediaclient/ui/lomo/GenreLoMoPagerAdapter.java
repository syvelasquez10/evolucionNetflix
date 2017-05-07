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
import com.netflix.mediaclient.servicemgr.model.Video;

public class GenreLoMoPagerAdapter extends ProgressiveLoMoPagerAdapter<Video>
{
    public GenreLoMoPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        if (this.getLoMo() == null) {
            Log.w("BaseProgressivePagerAdapter", "genre lomo pager - no lomo data to use for fetch request");
            return;
        }
        final LoMo loMo = (LoMo)this.getLoMo();
        Log.v("BaseProgressivePagerAdapter", String.format("fetching genre videos for: Title: %s, Total Vids: %d, Id: %s, start: %d, end: %d", loMo.getTitle(), loMo.getNumVideos(), loMo.getId(), n, n2));
        this.getManager().getBrowse().fetchGenreVideos(loMo, n, n2, this.getManager().getActivity().isKubrick(), new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler$FetchCallback<Object>)this, loMo.getTitle(), n, n2));
    }
}
