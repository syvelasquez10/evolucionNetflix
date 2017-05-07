// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.ads.AdListener;
import android.content.Context;
import com.google.android.gms.internal.bi;

public final class PublisherInterstitialAd
{
    private final bi lj;
    
    public PublisherInterstitialAd(final Context context) {
        this.lj = new bi(context, this);
    }
    
    public AdListener getAdListener() {
        return this.lj.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.lj.getAdUnitId();
    }
    
    public AppEventListener getAppEventListener() {
        return this.lj.getAppEventListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.lj.getMediationAdapterClassName();
    }
    
    public boolean isLoaded() {
        return this.lj.isLoaded();
    }
    
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.lj.a(publisherAdRequest.V());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.lj.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.lj.setAdUnitId(adUnitId);
    }
    
    public void setAppEventListener(final AppEventListener appEventListener) {
        this.lj.setAppEventListener(appEventListener);
    }
    
    public void show() {
        this.lj.show();
    }
}
