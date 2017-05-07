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
import com.google.android.gms.internal.gs;
import android.view.View;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.ads.mediation.MediationBannerAdapter;

public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters>
{
    private View n;
    private CustomEventBanner o;
    private CustomEventInterstitial p;
    
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
    public void destroy() {
        if (this.o != null) {
            this.o.destroy();
        }
        if (this.p != null) {
            this.p.destroy();
        }
    }
    
    @Override
    public Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }
    
    @Override
    public View getBannerView() {
        return this.n;
    }
    
    @Override
    public Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }
    
    @Override
    public void requestBannerAd(final MediationBannerListener mediationBannerListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final AdSize adSize, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.o = a(customEventServerParameters.className);
        if (this.o == null) {
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
        this.o.requestBannerAd(new a(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, extra);
    }
    
    @Override
    public void requestInterstitialAd(final MediationInterstitialListener mediationInterstitialListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.p = a(customEventServerParameters.className);
        if (this.p == null) {
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
        this.p.requestInterstitialAd(new b(this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, extra);
    }
    
    @Override
    public void showInterstitial() {
        this.p.showInterstitial();
    }
    
    private static final class a implements CustomEventBannerListener
    {
        private final CustomEventAdapter q;
        private final MediationBannerListener r;
        
        public a(final CustomEventAdapter q, final MediationBannerListener r) {
            this.q = q;
            this.r = r;
        }
        
        @Override
        public void onClick() {
            gs.S("Custom event adapter called onFailedToReceiveAd.");
            this.r.onClick(this.q);
        }
        
        @Override
        public void onDismissScreen() {
            gs.S("Custom event adapter called onFailedToReceiveAd.");
            this.r.onDismissScreen(this.q);
        }
        
        @Override
        public void onFailedToReceiveAd() {
            gs.S("Custom event adapter called onFailedToReceiveAd.");
            this.r.onFailedToReceiveAd(this.q, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public void onLeaveApplication() {
            gs.S("Custom event adapter called onFailedToReceiveAd.");
            this.r.onLeaveApplication(this.q);
        }
        
        @Override
        public void onPresentScreen() {
            gs.S("Custom event adapter called onFailedToReceiveAd.");
            this.r.onPresentScreen(this.q);
        }
        
        @Override
        public void onReceivedAd(final View view) {
            gs.S("Custom event adapter called onReceivedAd.");
            this.q.a(view);
            this.r.onReceivedAd(this.q);
        }
    }
    
    private class b implements CustomEventInterstitialListener
    {
        private final CustomEventAdapter q;
        private final MediationInterstitialListener s;
        
        public b(final CustomEventAdapter q, final MediationInterstitialListener s) {
            this.q = q;
            this.s = s;
        }
        
        @Override
        public void onDismissScreen() {
            gs.S("Custom event adapter called onDismissScreen.");
            this.s.onDismissScreen(this.q);
        }
        
        @Override
        public void onFailedToReceiveAd() {
            gs.S("Custom event adapter called onFailedToReceiveAd.");
            this.s.onFailedToReceiveAd(this.q, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public void onLeaveApplication() {
            gs.S("Custom event adapter called onLeaveApplication.");
            this.s.onLeaveApplication(this.q);
        }
        
        @Override
        public void onPresentScreen() {
            gs.S("Custom event adapter called onPresentScreen.");
            this.s.onPresentScreen(this.q);
        }
        
        @Override
        public void onReceivedAd() {
            gs.S("Custom event adapter called onReceivedAd.");
            this.s.onReceivedAd(CustomEventAdapter.this);
        }
    }
}
