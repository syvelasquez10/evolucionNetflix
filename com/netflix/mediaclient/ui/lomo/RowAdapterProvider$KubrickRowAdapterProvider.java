// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedLoMoAdapter;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.kubrick.lomo.ProgressiveKubrickHeroAdapter;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedHeroAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedGalleryAdapter;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedCwGalleryAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class RowAdapterProvider$KubrickRowAdapterProvider extends RowAdapterProvider$DefaultRowAdapterProvider
{
    private final RowAdapter billboard;
    private final RowAdapter cw;
    private final RowAdapter iq;
    private final RowAdapter kubrickGallery;
    private final RowAdapter kubrickHero;
    private final RowAdapter kubrickHeroDuplicate;
    
    public RowAdapterProvider$KubrickRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        super(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        this.billboard = new ProgressiveBillboardAdapter(new PaginatedBillboardAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.cw = new ProgressiveCwAdapter(new KubrickPaginatedCwGalleryAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.iq = new ProgressiveIqAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedGalleryAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickHero = new ProgressiveKubrickHeroAdapter(new KubrickPaginatedHeroAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickHeroDuplicate = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedLoMoAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickGallery = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedGalleryAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
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
        return this.iq;
    }
    
    @Override
    public RowAdapter getKubrickHeroAdapter() {
        return this.kubrickHero;
    }
    
    @Override
    public RowAdapter getKubrickHeroDuplicateAdapter() {
        return this.kubrickHeroDuplicate;
    }
    
    @Override
    public RowAdapter getStandardAdapter() {
        return this.kubrickGallery;
    }
}
