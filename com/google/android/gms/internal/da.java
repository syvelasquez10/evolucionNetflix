// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@ez
public final class da<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener
{
    private final cv qF;
    
    public da(final cv qf) {
        this.qF = qf;
    }
    
    @Override
    public void onClick(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        gs.S("Adapter called onClick.");
        if (!gr.dt()) {
            gs.W("onClick must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$1(this));
            return;
        }
        try {
            this.qF.onAdClicked();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onDismissScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        gs.S("Adapter called onDismissScreen.");
        if (!gr.dt()) {
            gs.W("onDismissScreen must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$4(this));
            return;
        }
        try {
            this.qF.onAdClosed();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onDismissScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        gs.S("Adapter called onDismissScreen.");
        if (!gr.dt()) {
            gs.W("onDismissScreen must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$9(this));
            return;
        }
        try {
            this.qF.onAdClosed();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest$ErrorCode adRequest$ErrorCode) {
        gs.S("Adapter called onFailedToReceiveAd with error. " + adRequest$ErrorCode);
        if (!gr.dt()) {
            gs.W("onFailedToReceiveAd must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$5(this, adRequest$ErrorCode));
            return;
        }
        try {
            this.qF.onAdFailedToLoad(db.a(adRequest$ErrorCode));
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest$ErrorCode adRequest$ErrorCode) {
        gs.S("Adapter called onFailedToReceiveAd with error " + adRequest$ErrorCode + ".");
        if (!gr.dt()) {
            gs.W("onFailedToReceiveAd must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$10(this, adRequest$ErrorCode));
            return;
        }
        try {
            this.qF.onAdFailedToLoad(db.a(adRequest$ErrorCode));
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        gs.S("Adapter called onLeaveApplication.");
        if (!gr.dt()) {
            gs.W("onLeaveApplication must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$6(this));
            return;
        }
        try {
            this.qF.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        gs.S("Adapter called onLeaveApplication.");
        if (!gr.dt()) {
            gs.W("onLeaveApplication must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$11(this));
            return;
        }
        try {
            this.qF.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onPresentScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        gs.S("Adapter called onPresentScreen.");
        if (!gr.dt()) {
            gs.W("onPresentScreen must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$7(this));
            return;
        }
        try {
            this.qF.onAdOpened();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onPresentScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        gs.S("Adapter called onPresentScreen.");
        if (!gr.dt()) {
            gs.W("onPresentScreen must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$2(this));
            return;
        }
        try {
            this.qF.onAdOpened();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onReceivedAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        gs.S("Adapter called onReceivedAd.");
        if (!gr.dt()) {
            gs.W("onReceivedAd must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$8(this));
            return;
        }
        try {
            this.qF.onAdLoaded();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onReceivedAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        gs.S("Adapter called onReceivedAd.");
        if (!gr.dt()) {
            gs.W("onReceivedAd must be called on the main UI thread.");
            gr.wC.post((Runnable)new da$3(this));
            return;
        }
        try {
            this.qF.onAdLoaded();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
}
