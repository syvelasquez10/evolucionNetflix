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

public final class bf<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener
{
    private final bd gi;
    
    public bf(final bd gi) {
        this.gi = gi;
    }
    
    @Override
    public void onClick(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ct.r("Adapter called onClick.");
        if (!cs.ay()) {
            ct.v("onClick must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.w();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdClicked.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.w();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onDismissScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ct.r("Adapter called onDismissScreen.");
        if (!cs.ay()) {
            ct.v("onDismissScreen must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdClosed();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdClosed.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdClosed();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onDismissScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ct.r("Adapter called onDismissScreen.");
        if (!cs.ay()) {
            ct.v("onDismissScreen must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdClosed();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdClosed.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdClosed();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        ct.r("Adapter called onFailedToReceiveAd with error. " + errorCode);
        if (!cs.ay()) {
            ct.v("onFailedToReceiveAd must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdFailedToLoad(bg.a(errorCode));
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdFailedToLoad.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdFailedToLoad(bg.a(errorCode));
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        ct.r("Adapter called onFailedToReceiveAd with error " + errorCode + ".");
        if (!cs.ay()) {
            ct.v("onFailedToReceiveAd must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdFailedToLoad(bg.a(errorCode));
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdFailedToLoad.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdFailedToLoad(bg.a(errorCode));
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ct.r("Adapter called onLeaveApplication.");
        if (!cs.ay()) {
            ct.v("onLeaveApplication must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdLeftApplication();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdLeftApplication.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ct.r("Adapter called onLeaveApplication.");
        if (!cs.ay()) {
            ct.v("onLeaveApplication must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdLeftApplication();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdLeftApplication.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onPresentScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ct.r("Adapter called onPresentScreen.");
        if (!cs.ay()) {
            ct.v("onPresentScreen must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdOpened();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdOpened.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdOpened();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onPresentScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ct.r("Adapter called onPresentScreen.");
        if (!cs.ay()) {
            ct.v("onPresentScreen must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdOpened();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdOpened.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdOpened();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onReceivedAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ct.r("Adapter called onReceivedAd.");
        if (!cs.ay()) {
            ct.v("onReceivedAd must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdLoaded();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdLoaded.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdLoaded();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onReceivedAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ct.r("Adapter called onReceivedAd.");
        if (!cs.ay()) {
            ct.v("onReceivedAd must be called on the main UI thread.");
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        bf.this.gi.onAdLoaded();
                    }
                    catch (RemoteException ex) {
                        ct.b("Could not call onAdLoaded.", (Throwable)ex);
                    }
                }
            });
            return;
        }
        try {
            this.gi.onAdLoaded();
        }
        catch (RemoteException ex) {
            ct.b("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
}
