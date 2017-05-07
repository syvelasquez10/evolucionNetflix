// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Iterator;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.util.Log;

public class zzd implements zzh
{
    private final zzg zzWJ;
    
    public zzd(final zzg zzWJ) {
        this.zzWJ = zzWJ;
    }
    
    private <A extends Api$Client> void zza(final zzg$zze<A> zzg$zze) {
        this.zzWJ.zzb(zzg$zze);
        final Api$Client zza = this.zzWJ.zza(zzg$zze.zzmq());
        if (!zza.isConnected() && this.zzWJ.zzXu.containsKey(zzg$zze.zzmq())) {
            zzg$zze.zzr(new Status(17));
            return;
        }
        zzg$zze.zzb((A)zza);
    }
    
    @Override
    public void begin() {
        while (!this.zzWJ.zzXn.isEmpty()) {
            try {
                this.zza((zzg$zze<?>)this.zzWJ.zzXn.remove());
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
    public String getName() {
        return "CONNECTED";
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        switch (n) {
            default: {}
            case 2: {
                this.zzaV(n);
                this.zzWJ.connect();
            }
            case 1: {
                this.zzWJ.zzmO();
                this.zzaV(n);
            }
        }
    }
    
    @Override
    public <A extends Api$Client, R extends Result, T extends zza$zza<R, A>> T zza(final T t) {
        return this.zzb(t);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public void zzaV(final int n) {
        boolean b;
        if (n == -1) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.zzWJ.zzmI();
            this.zzWJ.zzXu.clear();
        }
        else {
            final Iterator<zzg$zze<?>> iterator = this.zzWJ.zzXz.iterator();
            while (iterator.hasNext()) {
                iterator.next().forceFailureUnlessReady(new Status(8, "The connection to Google Play services was lost"));
            }
        }
        this.zzWJ.zze(null);
        if (!b) {
            this.zzWJ.zzXm.zzbu(n);
        }
        this.zzWJ.zzXm.zznR();
    }
    
    @Override
    public <A extends Api$Client, T extends zza$zza<? extends Result, A>> T zzb(final T t) {
        try {
            this.zza((zzg$zze<Api$Client>)t);
            return t;
        }
        catch (DeadObjectException ex) {
            this.zzaV(1);
            return t;
        }
    }
}
