// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickGalleryPagerAdapter;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHeroPagerAdapter;
import com.netflix.mediaclient.ui.kids.lolomo.KidsCharacterPagerAdapter;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

class RowAdapterProvider implements LoMoViewPagerAdapter$IRowAdapterProvider
{
    private final RowAdapter billboard;
    private final RowAdapter character;
    private final RowAdapter cw;
    private final RowAdapter error;
    private final RowAdapter iq;
    private final RowAdapter kubrickGallery;
    private final RowAdapter kubrickHero;
    private final RowAdapter loading;
    private final RowAdapter standard;
    
    private RowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        final NetflixActivity activity = serviceManager.getActivity();
        if (activity.isForKids()) {
            this.character = new KidsCharacterPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        }
        else {
            this.character = null;
        }
        if (activity.isKubrick()) {
            this.kubrickHero = new KubrickHeroPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
            this.kubrickGallery = new KubrickGalleryPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        }
        else {
            this.kubrickHero = null;
            this.kubrickGallery = null;
        }
        this.billboard = new BillboardPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.cw = new CwPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.iq = new IqPagerAdapter<Object>(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        RowAdapter standard;
        if (b) {
            standard = new GenreLoMoPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        }
        else {
            standard = new StandardLoMoPagerAdapter<Object>(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        }
        this.standard = standard;
        this.loading = new LoadingViewAdapter(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.error = new ErrorViewAdapter(rowAdapterCallbacks);
    }
    
    static LoMoViewPagerAdapter$IRowAdapterProvider create(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        return new RowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
    }
    
    @Override
    public RowAdapter getBillboardAdapter() {
        return this.billboard;
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
    public RowAdapter getErrorAdapter() {
        return this.error;
    }
    
    @Override
    public RowAdapter getIqAdapter() {
        return this.iq;
    }
    
    @Override
    public RowAdapter getKubrickGalleryAdapter() {
        return this.kubrickGallery;
    }
    
    @Override
    public RowAdapter getKubrickHeroAdapter() {
        return this.kubrickHero;
    }
    
    @Override
    public RowAdapter getLoadingAdapter() {
        return this.loading;
    }
    
    @Override
    public RowAdapter getStandardAdapter() {
        return this.standard;
    }
}
