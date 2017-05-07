// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.analytics.HitBuilders$HitBuilder;
import android.app.Activity;
import java.util.Map;
import com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;

class zzqe$zza implements zzoj$zza
{
    private final Tracker zzKq;
    
    zzqe$zza(final Tracker zzKq) {
        this.zzKq = zzKq;
    }
    
    @Override
    public void zza(final zzoq zzoq) {
        this.zzKq.setScreenName(zzoq.zzxT());
        final HitBuilders$ScreenViewBuilder hitBuilders$ScreenViewBuilder = new HitBuilders$ScreenViewBuilder();
        hitBuilders$ScreenViewBuilder.set("&a", String.valueOf(zzoq.zzbp()));
        this.zzKq.send(hitBuilders$ScreenViewBuilder.build());
    }
    
    @Override
    public void zza(final zzoq zzoq, final Activity activity) {
    }
}
