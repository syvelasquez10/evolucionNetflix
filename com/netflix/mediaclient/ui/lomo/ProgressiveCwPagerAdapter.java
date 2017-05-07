// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.kids.lolomo.KidsPaginatedCwAdapter;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.CWVideo;

public abstract class ProgressiveCwPagerAdapter extends BaseProgressivePagerAdapter<CWVideo>
{
    public ProgressiveCwPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<CWVideo> createPaginatedAdapter(final Context context) {
        if (this.getManager().getActivity().isForKids()) {
            return new KidsPaginatedCwAdapter(context);
        }
        return new PaginatedCwAdapter(context);
    }
}
