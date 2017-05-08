// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedLargeVideoAdapter;
import com.netflix.mediaclient.ui.kubrick_kids.lomo.KubrickKidsPaginatedTopTenAdapter;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedGalleryAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsPaginatedCharacterAdapter;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class RowAdapterProvider$KubrickKidsRowAdapterProvider extends RowAdapterProvider$DefaultRowAdapterProvider
{
    private final RowAdapter character;
    private final RowAdapter kubrickGallery;
    private final RowAdapter kubrickKidsPopular;
    private final RowAdapter kubrickKidsTopTen;
    
    public RowAdapterProvider$KubrickKidsRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        super(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        this.character = new ProgressiveStandardAdapter<Object>(new KubrickKidsPaginatedCharacterAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickGallery = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedGalleryAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsTopTen = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickKidsPaginatedTopTenAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsPopular = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedLargeVideoAdapter((Context)netflixActivity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    public RowAdapter getCharacterAdapter() {
        return this.character;
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
