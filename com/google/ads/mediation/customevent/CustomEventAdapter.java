// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.AdRequest$ErrorCode;
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
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest$ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object extra;
        if (customEventExtras == null) {
            extra = null;
        }
        else {
            extra = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.o.requestBannerAd(new CustomEventAdapter$a(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, extra);
    }
    
    @Override
    public void requestInterstitialAd(final MediationInterstitialListener mediationInterstitialListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.p = a(customEventServerParameters.className);
        if (this.p == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest$ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object extra;
        if (customEventExtras == null) {
            extra = null;
        }
        else {
            extra = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.p.requestInterstitialAd(new CustomEventAdapter$b(this, this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, extra);
    }
    
    @Override
    public void showInterstitial() {
        this.p.showInterstitial();
    }
}
