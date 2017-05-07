// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationBannerListener;
import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

class da$3 implements Runnable
{
    final /* synthetic */ da qI;
    
    da$3(final da qi) {
        this.qI = qi;
    }
    
    @Override
    public void run() {
        try {
            this.qI.qF.onAdLoaded();
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
}
