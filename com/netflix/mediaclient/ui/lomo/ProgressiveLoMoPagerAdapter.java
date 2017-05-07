// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.kids.lolomo.KidsPaginatedLoMoAdapter;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.Video;

public abstract class ProgressiveLoMoPagerAdapter extends BaseProgressivePagerAdapter<Video>
{
    public ProgressiveLoMoPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, rowAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<Video> createPaginatedAdapter(final Context context) {
        if (this.getManager().getActivity().isForKids()) {
            return new KidsPaginatedLoMoAdapter(context);
        }
        return new PaginatedLoMoAdapter(context);
    }
}
