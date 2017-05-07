// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationBannerListener;
import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.AdRequest$ErrorCode;

class da$10 implements Runnable
{
    final /* synthetic */ da qI;
    final /* synthetic */ AdRequest$ErrorCode qJ;
    
    da$10(final da qi, final AdRequest$ErrorCode qj) {
        this.qI = qi;
        this.qJ = qj;
    }
    
    @Override
    public void run() {
        try {
            this.qI.qF.onAdFailedToLoad(db.a(this.qJ));
        }
        catch (RemoteException ex) {
            gs.d("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
}
