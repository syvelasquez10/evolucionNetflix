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

public class zzl$zzb$zza implements ServiceConnection
{
    final /* synthetic */ zzl$zzb zzaaT;
    
    public zzl$zzb$zza(final zzl$zzb zzaaT) {
        this.zzaaT = zzaaT;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this.zzaaT.zzaaS.zzaaK) {
            this.zzaaT.zzZP = binder;
            this.zzaaT.zzaaN = componentName;
            final Iterator<ServiceConnection> iterator = this.zzaaT.zzaaP.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceConnected(componentName, binder);
            }
        }
        this.zzaaT.mState = 1;
    }
    // monitorexit(hashMap)
    
    public void onServiceDisconnected(final ComponentName componentName) {
        synchronized (this.zzaaT.zzaaS.zzaaK) {
            this.zzaaT.zzZP = null;
            this.zzaaT.zzaaN = componentName;
            final Iterator<ServiceConnection> iterator = this.zzaaT.zzaaP.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceDisconnected(componentName);
            }
        }
        this.zzaaT.mState = 2;
    }
    // monitorexit(hashMap)
}
