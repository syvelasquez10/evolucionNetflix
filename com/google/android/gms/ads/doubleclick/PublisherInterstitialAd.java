// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.ads.AdListener;
import android.content.Context;
import com.google.android.gms.internal.ah;

public final class PublisherInterstitialAd
{
    private final ah ea;
    
    public PublisherInterstitialAd(final Context context) {
        this.ea = new ah(context);
    }
    
    public AdListener getAdListener() {
        return this.ea.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.ea.getAdUnitId();
    }
    
    public AppEventListener getAppEventListener() {
        return this.ea.getAppEventListener();
    }
    
    public boolean isLoaded() {
        return this.ea.isLoaded();
    }
    
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.ea.a(publisherAdRequest.v());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.ea.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.ea.setAdUnitId(adUnitId);
    }
    
    public void setAppEventListener(final AppEventListener appEventListener) {
        this.ea.setAppEventListener(appEventListener);
    }
    
    public void show() {
        this.ea.show();
    }
}
