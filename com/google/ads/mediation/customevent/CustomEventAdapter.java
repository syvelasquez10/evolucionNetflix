// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.AdRequest;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationServerParameters;
import android.app.Activity;
import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.internal.ct;
import android.view.View;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.ads.mediation.MediationBannerAdapter;

public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters>
{
    private View m;
    private CustomEventBanner n;
    private CustomEventInterstitial o;
    
    private static <T> T a(final String s) {
        try {
            return (T)Class.forName(s).newInstance();
        }
        catch (Throwable t) {
            ct.v("Could not instantiate custom event adapter: " + s + ". " + t.getMessage());
            return null;
        }
    }
    
    private void a(final View m) {
        this.m = m;
    }
    
    @Override
    public void destroy() {
        if (this.n != null) {
            this.n.destroy();
        }
        if (this.o != null) {
            this.o.destroy();
        }
    }
    
    @Override
    public Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }
    
    @Override
    public View getBannerView() {
        return this.m;
    }
    
    @Override
    public Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }
    
    @Override
    public void requestBannerAd(final MediationBannerListener mediationBannerListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final AdSize adSize, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.n = a(customEventServerParameters.className);
        if (this.n == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object extra;
        if (customEventExtras == null) {
            extra = null;
        }
        else {
            extra = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.n.requestBannerAd(new a(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, extra);
    }
    
    @Override
    public void requestInterstitialAd(final MediationInterstitialListener mediationInterstitialListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.o = a(customEventServerParameters.className);
        if (this.o == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object extra;
        if (customEventExtras == null) {
            extra = null;
        }
        else {
            extra = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.o.requestInterstitialAd(new b(this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, extra);
    }
    
    @Override
    public void showInterstitial() {
        this.o.showInterstitial();
    }
    
    private static final class a implements CustomEventBannerListener
    {
        private final MediationBannerListener k;
        private final CustomEventAdapter p;
        
        public a(final CustomEventAdapter p2, final MediationBannerListener k) {
            this.p = p2;
            this.k = k;
        }
        
        @Override
        public void onClick() {
            ct.r("Custom event adapter called onFailedToReceiveAd.");
            this.k.onClick(this.p);
        }
        
        @Override
        public void onDismissScreen() {
            ct.r("Custom event adapter called onFailedToReceiveAd.");
            this.k.onDismissScreen(this.p);
        }
        
        @Override
        public void onFailedToReceiveAd() {
            ct.r("Custom event adapter called onFailedToReceiveAd.");
            this.k.onFailedToReceiveAd(this.p, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public void onLeaveApplication() {
            ct.r("Custom event adapter called onFailedToReceiveAd.");
            this.k.onLeaveApplication(this.p);
        }
        
        @Override
        public void onPresentScreen() {
            ct.r("Custom event adapter called onFailedToReceiveAd.");
            this.k.onPresentScreen(this.p);
        }
        
        @Override
        public void onReceivedAd(final View view) {
            ct.r("Custom event adapter called onReceivedAd.");
            this.p.a(view);
            this.k.onReceivedAd(this.p);
        }
    }
    
    private class b implements CustomEventInterstitialListener
    {
        private final MediationInterstitialListener l;
        private final CustomEventAdapter p;
        
        public b(final CustomEventAdapter p3, final MediationInterstitialListener l) {
            this.p = p3;
            this.l = l;
        }
        
        @Override
        public void onDismissScreen() {
            ct.r("Custom event adapter called onDismissScreen.");
            this.l.onDismissScreen(this.p);
        }
        
        @Override
        public void onFailedToReceiveAd() {
            ct.r("Custom event adapter called onFailedToReceiveAd.");
            this.l.onFailedToReceiveAd(this.p, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public void onLeaveApplication() {
            ct.r("Custom event adapter called onLeaveApplication.");
            this.l.onLeaveApplication(this.p);
        }
        
        @Override
        public void onPresentScreen() {
            ct.r("Custom event adapter called onPresentScreen.");
            this.l.onPresentScreen(this.p);
        }
        
        @Override
        public void onReceivedAd() {
            ct.r("Custom event adapter called onReceivedAd.");
            this.l.onReceivedAd(CustomEventAdapter.this);
        }
    }
}
