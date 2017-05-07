// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.AdSize;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import android.view.View;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.internal.gs;
import com.google.android.gms.ads.mediation.MediationBannerListener;

final class CustomEventAdapter$a implements CustomEventBannerListener
{
    private final MediationBannerListener l;
    private final CustomEventAdapter xh;
    
    public CustomEventAdapter$a(final CustomEventAdapter xh, final MediationBannerListener l) {
        this.xh = xh;
        this.l = l;
    }
    
    @Override
    public void onAdClicked() {
        gs.S("Custom event adapter called onAdClicked.");
        this.l.onAdClicked(this.xh);
    }
    
    @Override
    public void onAdClosed() {
        gs.S("Custom event adapter called onAdClosed.");
        this.l.onAdClosed(this.xh);
    }
    
    @Override
    public void onAdFailedToLoad(final int n) {
        gs.S("Custom event adapter called onAdFailedToLoad.");
        this.l.onAdFailedToLoad(this.xh, n);
    }
    
    @Override
    public void onAdLeftApplication() {
        gs.S("Custom event adapter called onAdLeftApplication.");
        this.l.onAdLeftApplication(this.xh);
    }
    
    @Override
    public void onAdLoaded(final View view) {
        gs.S("Custom event adapter called onAdLoaded.");
        this.xh.a(view);
        this.l.onAdLoaded(this.xh);
    }
    
    @Override
    public void onAdOpened() {
        gs.S("Custom event adapter called onAdOpened.");
        this.l.onAdOpened(this.xh);
    }
}
