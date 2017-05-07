// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;

public final class bv implements MediationBannerListener, MediationInterstitialListener
{
    private final bs nG;
    
    public bv(final bs ng) {
        this.nG = ng;
    }
    
    @Override
    public void onAdClicked(final MediationBannerAdapter mediationBannerAdapter) {
        fq.aj("onClick must be called on the main UI thread.");
        dw.v("Adapter called onClick.");
        try {
            this.nG.P();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationBannerAdapter mediationBannerAdapter) {
        fq.aj("onAdClosed must be called on the main UI thread.");
        dw.v("Adapter called onAdClosed.");
        try {
            this.nG.onAdClosed();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        fq.aj("onAdClosed must be called on the main UI thread.");
        dw.v("Adapter called onAdClosed.");
        try {
            this.nG.onAdClosed();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationBannerAdapter mediationBannerAdapter, final int n) {
        fq.aj("onAdFailedToLoad must be called on the main UI thread.");
        dw.v("Adapter called onAdFailedToLoad with error. " + n);
        try {
            this.nG.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationInterstitialAdapter mediationInterstitialAdapter, final int n) {
        fq.aj("onAdFailedToLoad must be called on the main UI thread.");
        dw.v("Adapter called onAdFailedToLoad with error " + n + ".");
        try {
            this.nG.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationBannerAdapter mediationBannerAdapter) {
        fq.aj("onAdLeftApplication must be called on the main UI thread.");
        dw.v("Adapter called onAdLeftApplication.");
        try {
            this.nG.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        fq.aj("onAdLeftApplication must be called on the main UI thread.");
        dw.v("Adapter called onAdLeftApplication.");
        try {
            this.nG.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationBannerAdapter mediationBannerAdapter) {
        fq.aj("onAdLoaded must be called on the main UI thread.");
        dw.v("Adapter called onAdLoaded.");
        try {
            this.nG.onAdLoaded();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        fq.aj("onAdLoaded must be called on the main UI thread.");
        dw.v("Adapter called onAdLoaded.");
        try {
            this.nG.onAdLoaded();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationBannerAdapter mediationBannerAdapter) {
        fq.aj("onAdOpened must be called on the main UI thread.");
        dw.v("Adapter called onAdOpened.");
        try {
            this.nG.onAdOpened();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        fq.aj("onAdOpened must be called on the main UI thread.");
        dw.v("Adapter called onAdOpened.");
        try {
            this.nG.onAdOpened();
        }
        catch (RemoteException ex) {
            dw.c("Could not call onAdOpened.", (Throwable)ex);
        }
    }
}
