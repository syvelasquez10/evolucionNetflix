// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedLoMoAdapter;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedCwAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class RowAdapterProvider$KubrickHighDensityRowAdapterProvider extends RowAdapterProvider$DefaultRowAdapterProvider
{
    private final RowAdapter billboard;
    private final RowAdapter cw;
    private final RowAdapter standard;
    
    public RowAdapterProvider$KubrickHighDensityRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        super(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        this.billboard = new ProgressiveBillboardAdapter(new PaginatedBillboardAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.cw = new ProgressiveCwAdapter(new KubrickPaginatedCwAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.standard = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedLoMoAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    public RowAdapter getBillboardAdapter() {
        return this.billboard;
    }
    
    @Override
    public RowAdapter getCwAdapter() {
        return this.cw;
    }
    
    @Override
    public RowAdapter getIqAdapter() {
        return this.standard;
    }
    
    @Override
    public RowAdapter getStandardAdapter() {
        return this.standard;
    }
}
