// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedLoMoAdapter;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.lolomo.KidsPaginatedLoMoAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;

public abstract class ProgressiveLoMoPagerAdapter<V extends Video> extends BaseProgressivePagerAdapter<V>
{
    public ProgressiveLoMoPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<V> createPaginatedAdapter(final NetflixActivity netflixActivity) {
        if (netflixActivity.isForKids()) {
            return (BasePaginatedAdapter<V>)new KidsPaginatedLoMoAdapter((Context)netflixActivity);
        }
        if (netflixActivity.isKubrick()) {
            return (BasePaginatedAdapter<V>)new KubrickPaginatedLoMoAdapter((Context)netflixActivity);
        }
        return (BasePaginatedAdapter<V>)new PaginatedLoMoAdapter((Context)netflixActivity);
    }
}
