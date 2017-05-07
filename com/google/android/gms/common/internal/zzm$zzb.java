// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Message;
import android.content.Context;
import com.google.android.gms.common.stats.zzb;
import java.util.HashMap;
import android.os.Handler;
import android.os.Handler$Callback;
import java.util.HashSet;
import android.content.ServiceConnection;
import java.util.Set;
import android.content.ComponentName;
import android.os.IBinder;

final class zzm$zzb
{
    private int mState;
    private IBinder zzaeJ;
    private ComponentName zzagb;
    private final zzm$zzb$zza zzagc;
    private final Set<ServiceConnection> zzagd;
    private boolean zzage;
    private final zzm$zza zzagf;
    final /* synthetic */ zzm zzagg;
    
    public zzm$zzb(final zzm zzagg, final zzm$zza zzagf) {
        this.zzagg = zzagg;
        this.zzagf = zzagf;
        this.zzagc = new zzm$zzb$zza(this);
        this.zzagd = new HashSet<ServiceConnection>();
        this.mState = 2;
    }
    
    public IBinder getBinder() {
        return this.zzaeJ;
    }
    
    public ComponentName getComponentName() {
        return this.zzagb;
    }
    
    public int getState() {
        return this.mState;
    }
    
    public boolean isBound() {
        return this.zzage;
    }
    
    public void zza(final ServiceConnection serviceConnection, final String s) {
        this.zzagg.zzafZ.zza(this.zzagg.zzqZ, serviceConnection, s, this.zzagf.zzpm());
        this.zzagd.add(serviceConnection);
    }
    
    public boolean zza(final ServiceConnection serviceConnection) {
        return this.zzagd.contains(serviceConnection);
    }
    
    public void zzb(final ServiceConnection serviceConnection, final String s) {
        this.zzagg.zzafZ.zzb(this.zzagg.zzqZ, serviceConnection);
        this.zzagd.remove(serviceConnection);
    }
    
    public void zzcm(final String s) {
        this.mState = 3;
        if (this.zzage = this.zzagg.zzafZ.zza(this.zzagg.zzqZ, s, this.zzagf.zzpm(), (ServiceConnection)this.zzagc, 129)) {
            return;
        }
        this.mState = 2;
        try {
            this.zzagg.zzafZ.zza(this.zzagg.zzqZ, (ServiceConnection)this.zzagc);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    public void zzcn(final String s) {
        this.zzagg.zzafZ.zza(this.zzagg.zzqZ, (ServiceConnection)this.zzagc);
        this.zzage = false;
        this.mState = 2;
    }
    
    public boolean zzpn() {
        return this.zzagd.isEmpty();
    }
}
