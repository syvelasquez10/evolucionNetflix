// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api$zzb;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Collections;

public class zzlh implements zzlj
{
    private final zzli zzabr;
    
    public zzlh(final zzli zzabr) {
        this.zzabr = zzabr;
    }
    
    @Override
    public void begin() {
        this.zzabr.zznZ();
        this.zzabr.zzaci = Collections.emptySet();
    }
    
    @Override
    public void connect() {
        this.zzabr.zzoa();
    }
    
    @Override
    public void disconnect() {
        for (final zzli$zzf zzli$zzf : this.zzabr.zzaca) {
            zzli$zzf.zza(null);
            zzli$zzf.cancel();
        }
        this.zzabr.zzaca.clear();
        this.zzabr.zzach.clear();
        this.zzabr.zznY();
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
    public <A extends Api$zzb, R extends Result, T extends zzlb$zza<R, A>> T zza(final T t) {
        this.zzabr.zzaca.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api$zzb, T extends zzlb$zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
