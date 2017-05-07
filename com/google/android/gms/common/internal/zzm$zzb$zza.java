// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.HashSet;
import java.util.Set;
import android.os.Message;
import android.content.Context;
import com.google.android.gms.common.stats.zzb;
import android.os.Handler;
import android.os.Handler$Callback;
import java.util.HashMap;
import java.util.Iterator;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public class zzm$zzb$zza implements ServiceConnection
{
    final /* synthetic */ zzm$zzb zzaee;
    
    public zzm$zzb$zza(final zzm$zzb zzaee) {
        this.zzaee = zzaee;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this.zzaee.zzaed.zzadV) {
            this.zzaee.zzacE = binder;
            this.zzaee.zzadY = componentName;
            final Iterator<ServiceConnection> iterator = this.zzaee.zzaea.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceConnected(componentName, binder);
            }
        }
        this.zzaee.mState = 1;
    }
    // monitorexit(hashMap)
    
    public void onServiceDisconnected(final ComponentName componentName) {
        synchronized (this.zzaee.zzaed.zzadV) {
            this.zzaee.zzacE = null;
            this.zzaee.zzadY = componentName;
            final Iterator<ServiceConnection> iterator = this.zzaee.zzaea.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceDisconnected(componentName);
            }
        }
        this.zzaee.mState = 2;
    }
    // monitorexit(hashMap)
}
