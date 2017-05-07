// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.Context;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Billboard;

public abstract class ProgressiveBillboardPagerAdapter extends BaseProgressivePagerAdapter<Billboard>
{
    public ProgressiveBillboardPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, rowAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<Billboard> createPaginatedAdapter(final Context context) {
        return new PaginatedBillboardAdapter(context);
    }
}
