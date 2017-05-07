// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$zzb;

public class zzlf implements zzlj
{
    private final zzli zzabr;
    
    public zzlf(final zzli zzabr) {
        this.zzabr = zzabr;
    }
    
    private <A extends Api$zzb> void zza(final zzli$zzf<A> zzli$zzf) {
        this.zzabr.zzb(zzli$zzf);
        final Api$zzb zza = this.zzabr.zza(zzli$zzf.zznx());
        if (!zza.isConnected() && this.zzabr.zzach.containsKey(zzli$zzf.zznx())) {
            zzli$zzf.zzv(new Status(17));
            return;
        }
        zzli$zzf.zzb((A)zza);
    }
    
    @Override
    public void begin() {
        while (!this.zzabr.zzaca.isEmpty()) {
            try {
                this.zza((zzli$zzf<?>)this.zzabr.zzaca.remove());
            }
            catch (DeadObjectException ex) {
                Log.w("GACConnected", "Service died while flushing queue", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void connect() {
    }
    
    @Override
    public void disconnect() {
        this.zzabr.zzach.clear();
        this.zzabr.zznY();
        this.zzabr.zzg(null);
        this.zzabr.zzabZ.zzpk();
    }
    
    @Override
    public String getName() {
        return "CONNECTED";
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        if (n == 1) {
            this.zzabr.zzoe();
        }
        final Iterator<zzli$zzf<?>> iterator = this.zzabr.zzacm.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzw(new Status(8, "The connection to Google Play services was lost"));
        }
        this.zzabr.zzg(null);
        this.zzabr.zzabZ.zzbG(n);
        this.zzabr.zzabZ.zzpk();
        if (n == 2) {
            this.zzabr.connect();
        }
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzlb$zza<R, A>> T zza(final T t) {
        return this.zzb(t);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api$zzb, T extends zzlb$zza<? extends Result, A>> T zzb(final T t) {
        try {
            this.zza((zzli$zzf<Api$zzb>)t);
            return t;
        }
        catch (DeadObjectException ex) {
            this.zzabr.zza((zzli$zzb)new zzlf$1(this, this));
            return t;
        }
    }
}
