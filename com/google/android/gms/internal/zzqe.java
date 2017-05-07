// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.analytics.Tracker;
import android.text.TextUtils;
import com.google.android.gms.tagmanager.Container;
import android.content.Context;

class zzqe
{
    private final Context mContext;
    private final zzqf zzpo;
    
    public zzqe(final Context mContext, final Container container, final zzqf zzqf) {
        this.mContext = mContext;
        this.zzpo = zza(container, zzqf);
        this.zzBi();
    }
    
    private void zzBi() {
        if (this.zzpo.zzBk() && !TextUtils.isEmpty((CharSequence)this.zzpo.getTrackingId())) {
            final Tracker zzfg = this.zzfg(this.zzpo.getTrackingId());
            zzfg.enableAdvertisingIdCollection(this.zzpo.zzBl());
            this.zzb(new zzqe$zza(zzfg));
        }
    }
    
    static zzqf zza(final Container container, final zzqf zzqf) {
        if (container == null || container.isDefault()) {
            return zzqf;
        }
        final zzqf$zza zzqf$zza = new zzqf$zza(zzqf.zzBj());
        zzqf$zza.zzfh(container.getString("trackingId")).zzau(container.getBoolean("trackScreenViews")).zzav(container.getBoolean("collectAdIdentifiers"));
        return zzqf$zza.zzBm();
    }
    
    public zzqf zzBh() {
        return this.zzpo;
    }
    
    void zzb(final zzoj$zza zzoj$zza) {
        zzx.zzv(zzoj$zza);
        final zzoj zzaJ = zzoj.zzaJ(this.mContext);
        zzaJ.zzaj(true);
        zzaJ.zza(zzoj$zza);
    }
    
    Tracker zzfg(final String s) {
        return GoogleAnalytics.getInstance(this.mContext).newTracker(s);
    }
}
