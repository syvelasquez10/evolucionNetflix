// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.ah;

public final class InterstitialAd
{
    private final ah ea;
    
    public InterstitialAd(final Context context) {
        this.ea = new ah(context);
    }
    
    public AdListener getAdListener() {
        return this.ea.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.ea.getAdUnitId();
    }
    
    public boolean isLoaded() {
        return this.ea.isLoaded();
    }
    
    public void loadAd(final AdRequest adRequest) {
        this.ea.a(adRequest.v());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.ea.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.ea.setAdUnitId(adUnitId);
    }
    
    public void show() {
        this.ea.show();
    }
}
