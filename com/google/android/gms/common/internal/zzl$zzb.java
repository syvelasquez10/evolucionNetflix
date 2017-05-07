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

final class zzl$zzb
{
    private int mState;
    private IBinder zzZP;
    private ComponentName zzaaN;
    private final zzl$zzb$zza zzaaO;
    private final Set<ServiceConnection> zzaaP;
    private boolean zzaaQ;
    private final zzl$zza zzaaR;
    final /* synthetic */ zzl zzaaS;
    
    public zzl$zzb(final zzl zzaaS, final zzl$zza zzaaR) {
        this.zzaaS = zzaaS;
        this.zzaaR = zzaaR;
        this.zzaaO = new zzl$zzb$zza(this);
        this.zzaaP = new HashSet<ServiceConnection>();
        this.mState = 2;
    }
    
    public IBinder getBinder() {
        return this.zzZP;
    }
    
    public ComponentName getComponentName() {
        return this.zzaaN;
    }
    
    public int getState() {
        return this.mState;
    }
    
    public boolean isBound() {
        return this.zzaaQ;
    }
    
    public void zza(final ServiceConnection serviceConnection, final String s) {
        this.zzaaS.zzaaL.zza(this.zzaaS.zzqw, serviceConnection, s, this.zzaaR.zznT());
        this.zzaaP.add(serviceConnection);
    }
    
    public boolean zza(final ServiceConnection serviceConnection) {
        return this.zzaaP.contains(serviceConnection);
    }
    
    public void zzb(final ServiceConnection serviceConnection, final String s) {
        this.zzaaS.zzaaL.zzb(this.zzaaS.zzqw, serviceConnection);
        this.zzaaP.remove(serviceConnection);
    }
    
    public void zzcc(final String s) {
        this.zzaaQ = this.zzaaS.zzaaL.zza(this.zzaaS.zzqw, s, this.zzaaR.zznT(), (ServiceConnection)this.zzaaO, 129);
        if (this.zzaaQ) {
            this.mState = 3;
            return;
        }
        this.zzaaS.zzaaL.zza(this.zzaaS.zzqw, (ServiceConnection)this.zzaaO);
    }
    
    public void zzcd(final String s) {
        this.zzaaS.zzaaL.zza(this.zzaaS.zzqw, (ServiceConnection)this.zzaaO);
        this.zzaaQ = false;
        this.mState = 2;
    }
    
    public boolean zznU() {
        return this.zzaaP.isEmpty();
    }
}
