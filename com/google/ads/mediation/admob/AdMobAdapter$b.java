// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.admob;

import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.AdListener;

final class AdMobAdapter$b extends AdListener
{
    private final AdMobAdapter k;
    private final MediationInterstitialListener m;
    
    public AdMobAdapter$b(final AdMobAdapter k, final MediationInterstitialListener m) {
        this.k = k;
        this.m = m;
    }
    
    @Override
    public void onAdClosed() {
        this.m.onAdClosed(this.k);
    }
    
    @Override
    public void onAdFailedToLoad(final int n) {
        this.m.onAdFailedToLoad(this.k, n);
    }
    
    @Override
    public void onAdLeftApplication() {
        this.m.onAdLeftApplication(this.k);
    }
    
    @Override
    public void onAdLoaded() {
        this.m.onAdLoaded(this.k);
    }
    
    @Override
    public void onAdOpened() {
        this.m.onAdOpened(this.k);
    }
}
