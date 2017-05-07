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
    private IBinder zzacE;
    private ComponentName zzadY;
    private final zzm$zzb$zza zzadZ;
    private final Set<ServiceConnection> zzaea;
    private boolean zzaeb;
    private final zzm$zza zzaec;
    final /* synthetic */ zzm zzaed;
    
    public zzm$zzb(final zzm zzaed, final zzm$zza zzaec) {
        this.zzaed = zzaed;
        this.zzaec = zzaec;
        this.zzadZ = new zzm$zzb$zza(this);
        this.zzaea = new HashSet<ServiceConnection>();
        this.mState = 2;
    }
    
    public IBinder getBinder() {
        return this.zzacE;
    }
    
    public ComponentName getComponentName() {
        return this.zzadY;
    }
    
    public int getState() {
        return this.mState;
    }
    
    public boolean isBound() {
        return this.zzaeb;
    }
    
    public void zza(final ServiceConnection serviceConnection, final String s) {
        this.zzaed.zzadW.zza(this.zzaed.zzqO, serviceConnection, s, this.zzaec.zzoK());
        this.zzaea.add(serviceConnection);
    }
    
    public boolean zza(final ServiceConnection serviceConnection) {
        return this.zzaea.contains(serviceConnection);
    }
    
    public void zzb(final ServiceConnection serviceConnection, final String s) {
        this.zzaed.zzadW.zzb(this.zzaed.zzqO, serviceConnection);
        this.zzaea.remove(serviceConnection);
    }
    
    public void zzcl(final String s) {
        this.zzaeb = this.zzaed.zzadW.zza(this.zzaed.zzqO, s, this.zzaec.zzoK(), (ServiceConnection)this.zzadZ, 129);
        if (this.zzaeb) {
            this.mState = 3;
            return;
        }
        try {
            this.zzaed.zzadW.zza(this.zzaed.zzqO, (ServiceConnection)this.zzadZ);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    public void zzcm(final String s) {
        this.zzaed.zzadW.zza(this.zzaed.zzqO, (ServiceConnection)this.zzadZ);
        this.zzaeb = false;
        this.mState = 2;
    }
    
    public boolean zzoL() {
        return this.zzaea.isEmpty();
    }
}
