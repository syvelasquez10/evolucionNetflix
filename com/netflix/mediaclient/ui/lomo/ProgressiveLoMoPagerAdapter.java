// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.Context;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.Video;

public abstract class ProgressiveLoMoPagerAdapter extends BaseProgressivePagerAdapter<Video>
{
    public ProgressiveLoMoPagerAdapter(final ServiceManager serviceManager, final PagerAdapterCallbacks pagerAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, pagerAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<Video> createPaginatedAdapter(final Context context) {
        return new PaginatedLoMoAdapter(context);
    }
}
