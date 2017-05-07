// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.AdSize;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import android.content.Context;
import com.google.android.gms.internal.gs;
import android.view.View;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;

public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter
{
    private View n;
    private CustomEventBanner xf;
    private CustomEventInterstitial xg;
    
    private static <T> T a(final String s) {
        try {
            return (T)Class.forName(s).newInstance();
        }
        catch (Throwable t) {
            gs.W("Could not instantiate custom event adapter: " + s + ". " + t.getMessage());
            return null;
        }
    }
    
    private void a(final View n) {
        this.n = n;
    }
    
    @Override
    public View getBannerView() {
        return this.n;
    }
    
    @Override
    public void onDestroy() {
        if (this.xf != null) {
            this.xf.onDestroy();
        }
        if (this.xg != null) {
            this.xg.onDestroy();
        }
    }
    
    @Override
    public void onPause() {
        if (this.xf != null) {
            this.xf.onPause();
        }
        if (this.xg != null) {
            this.xg.onPause();
        }
    }
    
    @Override
    public void onResume() {
        if (this.xf != null) {
            this.xf.onResume();
        }
        if (this.xg != null) {
            this.xg.onResume();
        }
    }
    
    @Override
    public void requestBannerAd(final Context context, final MediationBannerListener mediationBannerListener, final Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.xf = a(bundle.getString("class_name"));
        if (this.xf == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
            return;
        }
        if (bundle2 == null) {
            bundle2 = null;
        }
        else {
            bundle2 = bundle2.getBundle(bundle.getString("class_name"));
        }
        this.xf.requestBannerAd(context, new a(this, mediationBannerListener), bundle.getString("parameter"), adSize, mediationAdRequest, bundle2);
    }
    
    @Override
    public void requestInterstitialAd(final Context context, final MediationInterstitialListener mediationInterstitialListener, final Bundle bundle, final MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.xg = a(bundle.getString("class_name"));
        if (this.xg == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
            return;
        }
        if (bundle2 == null) {
            bundle2 = null;
        }
        else {
            bundle2 = bundle2.getBundle(bundle.getString("class_name"));
        }
        this.xg.requestInterstitialAd(context, new b(this, mediationInterstitialListener), bundle.getString("parameter"), mediationAdRequest, bundle2);
    }
    
    @Override
    public void showInterstitial() {
        this.xg.showInterstitial();
    }
    
    private static final class a implements CustomEventBannerListener
    {
        private final MediationBannerListener l;
        private final CustomEventAdapter xh;
        
        public a(final CustomEventAdapter xh, final MediationBannerListener l) {
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
    
    private class b implements CustomEventInterstitialListener
    {
        private final MediationInterstitialListener m;
        private final CustomEventAdapter xh;
        
        public b(final CustomEventAdapter xh, final MediationInterstitialListener m) {
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
            this.m.onAdLoaded(CustomEventAdapter.this);
        }
        
        @Override
        public void onAdOpened() {
            gs.S("Custom event adapter called onAdOpened.");
            this.m.onAdOpened(this.xh);
        }
    }
}
