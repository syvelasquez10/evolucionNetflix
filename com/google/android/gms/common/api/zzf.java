// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import android.os.DeadObjectException;
import android.util.Log;

public class zzf implements zzj
{
    private final zzi zzZq;
    
    public zzf(final zzi zzZq) {
        this.zzZq = zzZq;
    }
    
    private <A extends Api$zzb> void zza(final zzi$zze<A> zzi$zze) {
        this.zzZq.zzb(zzi$zze);
        final Api$zzb zza = this.zzZq.zza(zzi$zze.zznd());
        if (!zza.isConnected() && this.zzZq.zzaag.containsKey(zzi$zze.zznd())) {
            zzi$zze.zzx(new Status(17));
            return;
        }
        zzi$zze.zzb((A)zza);
    }
    
    @Override
    public void begin() {
        while (!this.zzZq.zzZZ.isEmpty()) {
            try {
                this.zza((zzi$zze<?>)this.zzZq.zzZZ.remove());
            }
            catch (DeadObjectException ex) {
                Log.w("GoogleApiClientConnected", "Service died while flushing queue", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void connect() {
    }
    
    @Override
    public void disconnect() {
        this.zzZq.zzaag.clear();
        this.zzZq.zznx();
        this.zzZq.zzg(null);
        this.zzZq.zzZY.zzoI();
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
            this.zzZq.zznD();
        }
        final Iterator<zzi$zze<?>> iterator = this.zzZq.zzaal.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzw(new Status(8, "The connection to Google Play services was lost"));
        }
        this.zzZq.zzg(null);
        this.zzZq.zzZY.zzbB(n);
        this.zzZq.zzZY.zzoI();
        if (n == 2) {
            this.zzZq.connect();
        }
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzc$zza<R, A>> T zza(final T t) {
        return this.zzb(t);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api$zzb, T extends zzc$zza<? extends Result, A>> T zzb(final T t) {
        try {
            this.zza((zzi$zze<Api$zzb>)t);
            return t;
        }
        catch (DeadObjectException ex) {
            this.zzZq.zza((zzi$zzb)new zzf$1(this, this));
            return t;
        }
    }
}
