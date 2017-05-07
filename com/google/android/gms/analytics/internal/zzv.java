// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import com.google.android.gms.analytics.AnalyticsReceiver;
import android.app.PendingIntent;
import android.app.AlarmManager;

public class zzv extends zzd
{
    private boolean zzMV;
    private boolean zzMW;
    private AlarmManager zzMX;
    
    protected zzv(final zzf zzf) {
        super(zzf);
        this.zzMX = (AlarmManager)this.getContext().getSystemService("alarm");
    }
    
    private PendingIntent zzjW() {
        final Intent intent = new Intent(this.getContext(), (Class)AnalyticsReceiver.class);
        intent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        return PendingIntent.getBroadcast(this.getContext(), 0, intent, 0);
    }
    
    public void cancel() {
        this.zzio();
        this.zzMW = false;
        this.zzMX.cancel(this.zzjW());
    }
    
    public boolean zzbr() {
        return this.zzMW;
    }
    
    @Override
    protected void zzhB() {
        try {
            this.zzMX.cancel(this.zzjW());
            if (this.zzif().zzjt() > 0L) {
                final ActivityInfo receiverInfo = this.getContext().getPackageManager().getReceiverInfo(new ComponentName(this.getContext(), (Class)AnalyticsReceiver.class), 2);
                if (receiverInfo != null && receiverInfo.enabled) {
                    this.zzaY("Receiver registered. Using alarm for local dispatch.");
                    this.zzMV = true;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
    }
    
    public boolean zzjU() {
        return this.zzMV;
    }
    
    public void zzjV() {
        this.zzio();
        zzx.zza(this.zzjU(), "Receiver not registered");
        final long zzjt = this.zzif().zzjt();
        if (zzjt > 0L) {
            this.cancel();
            final long elapsedRealtime = this.zzid().elapsedRealtime();
            this.zzMW = true;
            this.zzMX.setInexactRepeating(2, elapsedRealtime + zzjt, 0L, this.zzjW());
        }
    }
}
