// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Billboard;

public abstract class ProgressiveBillboardPagerAdapter extends BaseProgressivePagerAdapter<Billboard>
{
    public ProgressiveBillboardPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<Billboard> createPaginatedAdapter(final NetflixActivity netflixActivity) {
        return new PaginatedBillboardAdapter((Context)netflixActivity);
    }
}
