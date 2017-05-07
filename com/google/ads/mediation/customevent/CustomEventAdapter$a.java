// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationServerParameters;
import android.app.Activity;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import android.view.View;
import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.internal.gs;
import com.google.ads.mediation.MediationBannerListener;

final class CustomEventAdapter$a implements CustomEventBannerListener
{
    private final CustomEventAdapter q;
    private final MediationBannerListener r;
    
    public CustomEventAdapter$a(final CustomEventAdapter q, final MediationBannerListener r) {
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
        this.r.onFailedToReceiveAd(this.q, AdRequest$ErrorCode.NO_FILL);
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
