// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

public final class bx<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener
{
    private final bs nG;
    
    public bx(final bs ng) {
        this.nG = ng;
    }
    
    @Override
    public void onClick(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        dw.v("Adapter called onClick.");
        if (!dv.bD()) {
            dw.z("onClick must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.P();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdClicked.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.P();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onDismissScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        dw.v("Adapter called onDismissScreen.");
        if (!dv.bD()) {
            dw.z("onDismissScreen must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdClosed();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdClosed.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdClosed();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onDismissScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        dw.v("Adapter called onDismissScreen.");
        if (!dv.bD()) {
            dw.z("onDismissScreen must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdClosed();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdClosed.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdClosed();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        dw.v("Adapter called onFailedToReceiveAd with error. " + errorCode);
        if (!dv.bD()) {
            dw.z("onFailedToReceiveAd must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdFailedToLoad(by.a(errorCode));
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdFailedToLoad.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdFailedToLoad(by.a(errorCode));
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        dw.v("Adapter called onFailedToReceiveAd with error " + errorCode + ".");
        if (!dv.bD()) {
            dw.z("onFailedToReceiveAd must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdFailedToLoad(by.a(errorCode));
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdFailedToLoad.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdFailedToLoad(by.a(errorCode));
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        dw.v("Adapter called onLeaveApplication.");
        if (!dv.bD()) {
            dw.z("onLeaveApplication must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdLeftApplication();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdLeftApplication.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        dw.v("Adapter called onLeaveApplication.");
        if (!dv.bD()) {
            dw.z("onLeaveApplication must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdLeftApplication();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdLeftApplication.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onPresentScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        dw.v("Adapter called onPresentScreen.");
        if (!dv.bD()) {
            dw.z("onPresentScreen must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdOpened();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdOpened.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdOpened();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onPresentScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        dw.v("Adapter called onPresentScreen.");
        if (!dv.bD()) {
            dw.z("onPresentScreen must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdOpened();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdOpened.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdOpened();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onReceivedAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        dw.v("Adapter called onReceivedAd.");
        if (!dv.bD()) {
            dw.z("onReceivedAd must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdLoaded();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdLoaded.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdLoaded();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onReceivedAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        dw.v("Adapter called onReceivedAd.");
        if (!dv.bD()) {
            dw.z("onReceivedAd must be called on the main UI thread.");
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bx.this.nG.onAdLoaded();
                    }
                    catch (RemoteException ex) {
                        dw.c("Could not call onAdLoaded.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.nG.onAdLoaded();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
}
