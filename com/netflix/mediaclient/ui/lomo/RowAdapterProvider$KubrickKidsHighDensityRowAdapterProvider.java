// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedLoMoAdapter;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsPaginatedCharacterAdapter;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class RowAdapterProvider$KubrickKidsHighDensityRowAdapterProvider extends RowAdapterProvider$DefaultRowAdapterProvider
{
    private final RowAdapter character;
    private final RowAdapter kubrickGallery;
    private final RowAdapter kubrickKidsPopular;
    private final RowAdapter kubrickKidsTopTen;
    
    public RowAdapterProvider$KubrickKidsHighDensityRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        super(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        final NetflixActivity activity = serviceManager.getActivity();
        this.character = new ProgressiveStandardAdapter<Object>(new KubrickKidsPaginatedCharacterAdapter((Context)activity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickGallery = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedLoMoAdapter((Context)activity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsTopTen = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedLoMoAdapter((Context)activity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.kubrickKidsPopular = new ProgressiveStandardAdapter<Object>((BasePaginatedAdapter<Video>)new KubrickPaginatedLoMoAdapter((Context)activity), serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
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
