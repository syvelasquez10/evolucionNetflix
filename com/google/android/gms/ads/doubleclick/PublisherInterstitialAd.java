// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.ads.AdListener;
import android.content.Context;
import com.google.android.gms.internal.au;

public final class PublisherInterstitialAd
{
    private final au kv;
    
    public PublisherInterstitialAd(final Context context) {
        this.kv = new au(context);
    }
    
    public AdListener getAdListener() {
        return this.kv.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.kv.getAdUnitId();
    }
    
    public AppEventListener getAppEventListener() {
        return this.kv.getAppEventListener();
    }
    
    public boolean isLoaded() {
        return this.kv.isLoaded();
    }
    
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.kv.a(publisherAdRequest.O());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.kv.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.kv.setAdUnitId(adUnitId);
    }
    
    public void setAppEventListener(final AppEventListener appEventListener) {
        this.kv.setAppEventListener(appEventListener);
    }
    
    public void show() {
        this.kv.show();
    }
}
