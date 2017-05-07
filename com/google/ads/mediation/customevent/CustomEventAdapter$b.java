// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.internal.gs;
import com.google.ads.mediation.MediationInterstitialListener;

class CustomEventAdapter$b implements CustomEventInterstitialListener
{
    private final CustomEventAdapter q;
    private final MediationInterstitialListener s;
    final /* synthetic */ CustomEventAdapter t;
    
    public CustomEventAdapter$b(final CustomEventAdapter t, final CustomEventAdapter q, final MediationInterstitialListener s) {
        this.t = t;
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
        this.s.onFailedToReceiveAd(this.q, AdRequest$ErrorCode.NO_FILL);
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
        this.s.onReceivedAd(this.t);
    }
}
