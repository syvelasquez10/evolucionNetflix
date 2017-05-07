// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.internal.gs;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

class CustomEventAdapter$b implements CustomEventInterstitialListener
{
    private final MediationInterstitialListener m;
    private final CustomEventAdapter xh;
    final /* synthetic */ CustomEventAdapter xi;
    
    public CustomEventAdapter$b(final CustomEventAdapter xi, final CustomEventAdapter xh, final MediationInterstitialListener m) {
        this.xi = xi;
        this.xh = xh;
        this.m = m;
    }
    
    @Override
    public void onAdClicked() {
        gs.S("Custom event adapter called onAdClicked.");
        this.m.onAdClicked(this.xh);
    }
    
    @Override
    public void onAdClosed() {
        gs.S("Custom event adapter called onAdClosed.");
        this.m.onAdClosed(this.xh);
    }
    
    @Override
    public void onAdFailedToLoad(final int n) {
        gs.S("Custom event adapter called onFailedToReceiveAd.");
        this.m.onAdFailedToLoad(this.xh, n);
    }
    
    @Override
    public void onAdLeftApplication() {
        gs.S("Custom event adapter called onAdLeftApplication.");
        this.m.onAdLeftApplication(this.xh);
    }
    
    @Override
    public void onAdLoaded() {
        gs.S("Custom event adapter called onReceivedAd.");
        this.m.onAdLoaded(this.xi);
    }
    
    @Override
    public void onAdOpened() {
        gs.S("Custom event adapter called onAdOpened.");
        this.m.onAdOpened(this.xh);
    }
}
