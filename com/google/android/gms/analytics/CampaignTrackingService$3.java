// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzaf;

class CampaignTrackingService$3 implements Runnable
{
    final /* synthetic */ int zzKf;
    final /* synthetic */ zzaf zzKh;
    final /* synthetic */ CampaignTrackingService zzKo;
    
    CampaignTrackingService$3(final CampaignTrackingService zzKo, final int zzKf, final zzaf zzKh) {
        this.zzKo = zzKo;
        this.zzKf = zzKf;
        this.zzKh = zzKh;
    }
    
    @Override
    public void run() {
        final boolean stopSelfResult = this.zzKo.stopSelfResult(this.zzKf);
        if (stopSelfResult) {
            this.zzKh.zza("Install campaign broadcast processed", stopSelfResult);
        }
    }
}
