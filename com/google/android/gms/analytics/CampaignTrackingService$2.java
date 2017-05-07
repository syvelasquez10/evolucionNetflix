// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.Handler;
import com.google.android.gms.analytics.internal.zzaf;

class CampaignTrackingService$2 implements Runnable
{
    final /* synthetic */ int zzKf;
    final /* synthetic */ zzaf zzKh;
    final /* synthetic */ CampaignTrackingService zzKo;
    final /* synthetic */ Handler zzt;
    
    CampaignTrackingService$2(final CampaignTrackingService zzKo, final zzaf zzKh, final Handler zzt, final int zzKf) {
        this.zzKo = zzKo;
        this.zzKh = zzKh;
        this.zzt = zzt;
        this.zzKf = zzKf;
    }
    
    @Override
    public void run() {
        this.zzKo.zza(this.zzKh, this.zzt, this.zzKf);
    }
}
