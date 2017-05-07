// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

class AnalyticsService$1$1 implements Runnable
{
    final /* synthetic */ AnalyticsService$1 zzKj;
    
    AnalyticsService$1$1(final AnalyticsService$1 zzKj) {
        this.zzKj = zzKj;
    }
    
    @Override
    public void run() {
        if (this.zzKj.zzKi.stopSelfResult(this.zzKj.zzKf)) {
            if (!this.zzKj.zzKg.zzif().zzjk()) {
                this.zzKj.zzKh.zzaY("Local AnalyticsService processed last dispatch request");
                return;
            }
            this.zzKj.zzKh.zzaY("Device AnalyticsService processed last dispatch request");
        }
    }
}
