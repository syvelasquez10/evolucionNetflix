// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.admob;

import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.AdListener;

final class AdMobAdapter$a extends AdListener
{
    private final AdMobAdapter k;
    private final MediationBannerListener l;
    
    public AdMobAdapter$a(final AdMobAdapter k, final MediationBannerListener l) {
        this.k = k;
        this.l = l;
    }
    
    @Override
    public void onAdClosed() {
        this.l.onAdClosed(this.k);
    }
    
    @Override
    public void onAdFailedToLoad(final int n) {
        this.l.onAdFailedToLoad(this.k, n);
    }
    
    @Override
    public void onAdLeftApplication() {
        this.l.onAdLeftApplication(this.k);
    }
    
    @Override
    public void onAdLoaded() {
        this.l.onAdLoaded(this.k);
    }
    
    @Override
    public void onAdOpened() {
        this.l.onAdClicked(this.k);
        this.l.onAdOpened(this.k);
    }
}
