// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.Context;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;

public abstract class ProgressiveBillboardPagerAdapter extends BaseProgressivePagerAdapter<BillboardDetails>
{
    public ProgressiveBillboardPagerAdapter(final ServiceManager serviceManager, final PagerAdapterCallbacks pagerAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, pagerAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<BillboardDetails> createPaginatedAdapter(final Context context) {
        return new PaginatedBillboardAdapter(context);
    }
}
