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

final class zzl extends zzk implements Handler$Callback
{
    private final Handler mHandler;
    private final HashMap<zzl$zza, zzl$zzb> zzaaK;
    private final zzb zzaaL;
    private final long zzaaM;
    private final Context zzqw;
    
    zzl(final Context context) {
        this.zzaaK = new HashMap<zzl$zza, zzl$zzb>();
        this.zzqw = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.zzaaL = zzb.zzoM();
        this.zzaaM = 5000L;
    }
    
    private boolean zza(final zzl$zza zzl$zza, final ServiceConnection serviceConnection, final String s) {
        while (true) {
            zzu.zzb(serviceConnection, "ServiceConnection must not be null");
            while (true) {
                zzl$zzb zzl$zzb;
                synchronized (this.zzaaK) {
                    zzl$zzb = this.zzaaK.get(zzl$zza);
                    if (zzl$zzb == null) {
                        zzl$zzb = new zzl$zzb(this, zzl$zza);
                        zzl$zzb.zza(serviceConnection, s);
                        zzl$zzb.zzcc(s);
                        this.zzaaK.put(zzl$zza, zzl$zzb);
                        final zzl$zzb zzl$zzb2 = zzl$zzb;
                        return zzl$zzb2.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)zzl$zzb);
                    if (zzl$zzb.zza(serviceConnection)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zzl$zza);
                    }
                }
                zzl$zzb.zza(serviceConnection, s);
                switch (zzl$zzb.getState()) {
                    case 1: {
                        serviceConnection.onServiceConnected(zzl$zzb.getComponentName(), zzl$zzb.getBinder());
                        final zzl$zzb zzl$zzb2 = zzl$zzb;
                        continue;
                    }
                    case 2: {
                        zzl$zzb.zzcc(s);
                        final zzl$zzb zzl$zzb2 = zzl$zzb;
                        continue;
                    }
                    default: {
                        final zzl$zzb zzl$zzb2 = zzl$zzb;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    private void zzb(final zzl$zza zzl$zza, final ServiceConnection serviceConnection, final String s) {
        zzu.zzb(serviceConnection, "ServiceConnection must not be null");
        final zzl$zzb zzl$zzb;
        synchronized (this.zzaaK) {
            zzl$zzb = this.zzaaK.get(zzl$zza);
            if (zzl$zzb == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + zzl$zza);
            }
        }
        if (!zzl$zzb.zza(serviceConnection)) {
            final Throwable t;
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + t);
        }
        zzl$zzb.zzb(serviceConnection, s);
        if (zzl$zzb.zznU()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)zzl$zzb), this.zzaaM);
        }
    }
    // monitorexit(hashMap)
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                final zzl$zzb zzl$zzb = (zzl$zzb)message.obj;
                synchronized (this.zzaaK) {
                    if (zzl$zzb.zznU()) {
                        if (zzl$zzb.isBound()) {
                            zzl$zzb.zzcd("GmsClientSupervisor");
                        }
                        this.zzaaK.remove(zzl$zzb.zzaaR);
                    }
                    return true;
                }
                break;
            }
        }
    }
    
    @Override
    public boolean zza(final String s, final ServiceConnection serviceConnection, final String s2) {
        return this.zza(new zzl$zza(s), serviceConnection, s2);
    }
    
    @Override
    public void zzb(final String s, final ServiceConnection serviceConnection, final String s2) {
        this.zzb(new zzl$zza(s), serviceConnection, s2);
    }
}
