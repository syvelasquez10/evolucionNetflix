// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.content.Context;
import com.netflix.mediaclient.ui.barker_kids.lolomo.BarkerKidsPaginatedCharacterAdapter;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class RowAdapterProvider$KidsTabletRowAdapterProvider extends RowAdapterProvider$DefaultRowAdapterProvider
{
    private final RowAdapter character;
    private final RowAdapter cw;
    private final RowAdapter iq;
    private final RowAdapter kubrickGallery;
    private final RowAdapter kubrickKidsPopular;
    private final RowAdapter kubrickKidsTopTen;
    private final ServiceManager manager;
    
    public RowAdapterProvider$KidsTabletRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        super(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.manager = netflixActivity.getServiceManager();
        this.character = new ProgressiveStandardAdapter<Object>(new BarkerKidsPaginatedCharacterAdapter((Context)netflixActivity), this.manager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.cw = new ProgressiveCwAdapter(new PaginatedCwAdapter((Context)netflixActivity), this.manager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickGallery = new ProgressiveStandardAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), this.manager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.iq = new ProgressiveIqAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), this.manager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsTopTen = new ProgressiveStandardAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), this.manager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsPopular = new ProgressiveStandardAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), this.manager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    public RowAdapter getCharacterAdapter() {
        return this.character;
    }
    
    @Override
    public RowAdapter getCwAdapter() {
        return this.cw;
    }
    
    @Override
    public RowAdapter getIqAdapter() {
        ErrorLoggingManager.logHandledException("SPY-10196: My List is not supported in Kids profile, but handle request to avoid crash. LOLOMO_ID - " + this.manager.getBrowse().getLolomoId());
        return this.iq;
    }
    
    @Override
    public RowAdapter getKubrickKidsPopularAdapter() {
        return this.kubrickKidsPopular;
    }
    
    @Override
    public RowAdapter getKubrickKidsTopTenAdapter() {
        return this.kubrickKidsTopTen;
    }
    
    @Override
    public RowAdapter getStandardAdapter() {
        return this.kubrickGallery;
    }
}
