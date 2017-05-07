// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Collections;

public class zzh implements zzj
{
    private final zzi zzZq;
    
    public zzh(final zzi zzZq) {
        this.zzZq = zzZq;
    }
    
    @Override
    public void begin() {
        this.zzZq.zzny();
        this.zzZq.zzaah = Collections.emptySet();
    }
    
    @Override
    public void connect() {
        this.zzZq.zznz();
    }
    
    @Override
    public void disconnect() {
        final Iterator<zzi$zze> iterator = this.zzZq.zzZZ.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel();
        }
        this.zzZq.zzZZ.clear();
        this.zzZq.zzaag.clear();
        this.zzZq.zznx();
    }
    
    @Override
    public String getName() {
        return "DISCONNECTED";
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzc$zza<R, A>> T zza(final T t) {
        this.zzZq.zzZZ.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api$zzb, T extends zzc$zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
