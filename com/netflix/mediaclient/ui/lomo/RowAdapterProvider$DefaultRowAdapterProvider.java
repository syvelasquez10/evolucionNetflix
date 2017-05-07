// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;

abstract class RowAdapterProvider$DefaultRowAdapterProvider implements RowAdapterProvider$IRowAdapterProvider
{
    private final RowAdapter error;
    private final RowAdapter loading;
    
    protected RowAdapterProvider$DefaultRowAdapterProvider(final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        this.loading = new LoadingViewAdapter(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.error = new ErrorViewAdapter(rowAdapterCallbacks);
    }
    
    @Override
    public RowAdapter getBillboardAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getCharacterAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getCwAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getErrorAdapter() {
        return this.error;
    }
    
    @Override
    public RowAdapter getIqAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getKubrickHeroAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getKubrickHeroDuplicateAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getKubrickKidsPopularAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getKubrickKidsTopTenAdapter() {
        return null;
    }
    
    @Override
    public RowAdapter getLoadingAdapter() {
        return this.loading;
    }
    
    @Override
    public RowAdapter getStandardAdapter() {
        return null;
    }
}
