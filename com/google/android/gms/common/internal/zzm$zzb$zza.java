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
    final /* synthetic */ zzm$zzb zzagh;
    
    public zzm$zzb$zza(final zzm$zzb zzagh) {
        this.zzagh = zzagh;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this.zzagh.zzagg.zzafY) {
            this.zzagh.zzaeJ = binder;
            this.zzagh.zzagb = componentName;
            final Iterator<ServiceConnection> iterator = this.zzagh.zzagd.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceConnected(componentName, binder);
            }
        }
        this.zzagh.mState = 1;
    }
    // monitorexit(hashMap)
    
    public void onServiceDisconnected(final ComponentName componentName) {
        synchronized (this.zzagh.zzagg.zzafY) {
            this.zzagh.zzaeJ = null;
            this.zzagh.zzagb = componentName;
            final Iterator<ServiceConnection> iterator = this.zzagh.zzagd.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceDisconnected(componentName);
            }
        }
        this.zzagh.mState = 2;
    }
    // monitorexit(hashMap)
}
