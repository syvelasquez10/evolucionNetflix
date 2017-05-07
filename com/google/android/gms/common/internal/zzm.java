// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.HashSet;
import java.util.Set;
import android.content.ComponentName;
import android.os.IBinder;
import android.os.Message;
import android.content.ServiceConnection;
import android.content.Context;
import com.google.android.gms.common.stats.zzb;
import java.util.HashMap;
import android.os.Handler;
import android.os.Handler$Callback;

final class zzm extends zzl implements Handler$Callback
{
    private final Handler mHandler;
    private final HashMap<zzm$zza, zzm$zzb> zzafY;
    private final zzb zzafZ;
    private final long zzaga;
    private final Context zzqZ;
    
    zzm(final Context context) {
        this.zzafY = new HashMap<zzm$zza, zzm$zzb>();
        this.zzqZ = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.zzafZ = zzb.zzqh();
        this.zzaga = 5000L;
    }
    
    private boolean zza(final zzm$zza zzm$zza, final ServiceConnection serviceConnection, final String s) {
        while (true) {
            zzx.zzb(serviceConnection, "ServiceConnection must not be null");
            while (true) {
                zzm$zzb zzm$zzb;
                synchronized (this.zzafY) {
                    zzm$zzb = this.zzafY.get(zzm$zza);
                    if (zzm$zzb == null) {
                        zzm$zzb = new zzm$zzb(this, zzm$zza);
                        zzm$zzb.zza(serviceConnection, s);
                        zzm$zzb.zzcm(s);
                        this.zzafY.put(zzm$zza, zzm$zzb);
                        final zzm$zzb zzm$zzb2 = zzm$zzb;
                        return zzm$zzb2.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)zzm$zzb);
                    if (zzm$zzb.zza(serviceConnection)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zzm$zza);
                    }
                }
                zzm$zzb.zza(serviceConnection, s);
                switch (zzm$zzb.getState()) {
                    case 1: {
                        serviceConnection.onServiceConnected(zzm$zzb.getComponentName(), zzm$zzb.getBinder());
                        final zzm$zzb zzm$zzb2 = zzm$zzb;
                        continue;
                    }
                    case 2: {
                        zzm$zzb.zzcm(s);
                        final zzm$zzb zzm$zzb2 = zzm$zzb;
                        continue;
                    }
                    default: {
                        final zzm$zzb zzm$zzb2 = zzm$zzb;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    private void zzb(final zzm$zza zzm$zza, final ServiceConnection serviceConnection, final String s) {
        zzx.zzb(serviceConnection, "ServiceConnection must not be null");
        final zzm$zzb zzm$zzb;
        synchronized (this.zzafY) {
            zzm$zzb = this.zzafY.get(zzm$zza);
            if (zzm$zzb == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + zzm$zza);
            }
        }
        if (!zzm$zzb.zza(serviceConnection)) {
            final Throwable t;
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + t);
        }
        zzm$zzb.zzb(serviceConnection, s);
        if (zzm$zzb.zzpn()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)zzm$zzb), this.zzaga);
        }
    }
    // monitorexit(hashMap)
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                final zzm$zzb zzm$zzb = (zzm$zzb)message.obj;
                synchronized (this.zzafY) {
                    if (zzm$zzb.zzpn()) {
                        if (zzm$zzb.isBound()) {
                            zzm$zzb.zzcn("GmsClientSupervisor");
                        }
                        this.zzafY.remove(zzm$zzb.zzagf);
                    }
                    return true;
                }
                break;
            }
        }
    }
    
    @Override
    public boolean zza(final String s, final ServiceConnection serviceConnection, final String s2) {
        return this.zza(new zzm$zza(s), serviceConnection, s2);
    }
    
    @Override
    public void zzb(final String s, final ServiceConnection serviceConnection, final String s2) {
        this.zzb(new zzm$zza(s), serviceConnection, s2);
    }
}
