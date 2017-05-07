// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;

@ez
public final class cy implements MediationBannerListener, MediationInterstitialListener
{
    private final cv qF;
    
    public cy(final cv qf) {
        this.qF = qf;
    }
    
    @Override
    public void onAdClicked(final MediationBannerAdapter mediationBannerAdapter) {
        n.aT("onAdClicked must be called on the main UI thread.");
        gs.S("Adapter called onAdClicked.");
        try {
            this.qF.onAdClicked();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClicked(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        n.aT("onAdClicked must be called on the main UI thread.");
        gs.S("Adapter called onAdClicked.");
        try {
            this.qF.onAdClicked();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationBannerAdapter mediationBannerAdapter) {
        n.aT("onAdClosed must be called on the main UI thread.");
        gs.S("Adapter called onAdClosed.");
        try {
            this.qF.onAdClosed();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        n.aT("onAdClosed must be called on the main UI thread.");
        gs.S("Adapter called onAdClosed.");
        try {
            this.qF.onAdClosed();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationBannerAdapter mediationBannerAdapter, final int n) {
        n.aT("onAdFailedToLoad must be called on the main UI thread.");
        gs.S("Adapter called onAdFailedToLoad with error. " + n);
        try {
            this.qF.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationInterstitialAdapter mediationInterstitialAdapter, final int n) {
        n.aT("onAdFailedToLoad must be called on the main UI thread.");
        gs.S("Adapter called onAdFailedToLoad with error " + n + ".");
        try {
            this.qF.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationBannerAdapter mediationBannerAdapter) {
        n.aT("onAdLeftApplication must be called on the main UI thread.");
        gs.S("Adapter called onAdLeftApplication.");
        try {
            this.qF.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        n.aT("onAdLeftApplication must be called on the main UI thread.");
        gs.S("Adapter called onAdLeftApplication.");
        try {
            this.qF.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationBannerAdapter mediationBannerAdapter) {
        n.aT("onAdLoaded must be called on the main UI thread.");
        gs.S("Adapter called onAdLoaded.");
        try {
            this.qF.onAdLoaded();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        n.aT("onAdLoaded must be called on the main UI thread.");
        gs.S("Adapter called onAdLoaded.");
        try {
            this.qF.onAdLoaded();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationBannerAdapter mediationBannerAdapter) {
        n.aT("onAdOpened must be called on the main UI thread.");
        gs.S("Adapter called onAdOpened.");
        try {
            this.qF.onAdOpened();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        n.aT("onAdOpened must be called on the main UI thread.");
        gs.S("Adapter called onAdOpened.");
        try {
            this.qF.onAdOpened();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdOpened.", (Throwable)ex);
        }
    }
}
