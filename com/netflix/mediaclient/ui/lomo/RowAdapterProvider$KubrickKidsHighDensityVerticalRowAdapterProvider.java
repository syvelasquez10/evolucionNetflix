// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.kubrick_kids.lomo.KubrickKidsPaginatedCwAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsPaginatedCharacterAdapter;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class RowAdapterProvider$KubrickKidsHighDensityVerticalRowAdapterProvider extends RowAdapterProvider$DefaultRowAdapterProvider
{
    private final RowAdapter character;
    private final RowAdapter cw;
    private final RowAdapter kubrickGallery;
    private final RowAdapter kubrickKidsPopular;
    private final RowAdapter kubrickKidsTopTen;
    
    public RowAdapterProvider$KubrickKidsHighDensityVerticalRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        super(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        this.character = new ProgressiveStandardAdapter<Object>(new KubrickKidsPaginatedCharacterAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.cw = new ProgressiveCwAdapter(new KubrickKidsPaginatedCwAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickGallery = new ProgressiveStandardAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsTopTen = new ProgressiveStandardAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsPopular = new ProgressiveStandardAdapter<Object>(new PaginatedLoMoAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
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
