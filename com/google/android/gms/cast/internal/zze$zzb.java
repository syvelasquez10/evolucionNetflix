// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.text.TextUtils;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import android.os.RemoteException;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza$zzb;
import java.util.concurrent.atomic.AtomicReference;
import android.os.Handler;

class zze$zzb extends zzj$zza
{
    private final Handler mHandler;
    private final AtomicReference<zze> zzUH;
    
    public zze$zzb(final zze zze) {
        this.zzUH = new AtomicReference<zze>(zze);
        this.mHandler = new Handler(zze.getLooper());
    }
    
    private void zza(final zze zze, final long n, final int n2) {
        synchronized (zze.zzUz) {
            final zza$zzb<Status> zza$zzb = zze.zzUz.remove(n);
            // monitorexit(zze.zzg(zze))
            if (zza$zzb != null) {
                zza$zzb.zzm(new Status(n2));
            }
        }
    }
    
    private boolean zza(final zze zze, final int n) {
        synchronized (zze.zzUD) {
            if (zze.zzUB != null) {
                zze.zzUB.zzm(new Status(n));
                zze.zzUB = null;
                return true;
            }
            return false;
        }
    }
    
    public boolean isDisposed() {
        return this.zzUH.get() == null;
    }
    
    public void onApplicationDisconnected(final int n) {
        final zze zze = this.zzUH.get();
        if (zze != null) {
            zze.zzUw = null;
            zze.zzUx = null;
            this.zza(zze, n);
            if (zze.zzQH != null) {
                this.mHandler.post((Runnable)new zze$zzb$1(this, zze, n));
            }
        }
    }
    
    public void zza(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        zze.zzUk = applicationMetadata;
        zze.zzUw = applicationMetadata.getApplicationId();
        zze.zzUx = s2;
        synchronized (com.google.android.gms.cast.internal.zze.zzUC) {
            if (zze.zzUA != null) {
                zze.zzUA.zzm(new zze$zza(new Status(0), applicationMetadata, s, s2, b));
                zze.zzUA = null;
            }
        }
    }
    
    public void zza(final String s, final double n, final boolean b) {
        zze.zzQV.zzb("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }
    
    public void zza(final String s, final long n, final int n2) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n, n2);
    }
    
    public void zzaM(final int n) {
        final zze zzlU = this.zzlU();
        if (zzlU != null) {
            zze.zzQV.zzb("ICastDeviceControllerListener.onDisconnected: %d", n);
            if (n != 0) {
                zzlU.zzbs(2);
            }
        }
    }
    
    public void zzaN(final int n) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        synchronized (com.google.android.gms.cast.internal.zze.zzUC) {
            if (zze.zzUA != null) {
                zze.zzUA.zzm(new zze$zza(new Status(n)));
                zze.zzUA = null;
            }
        }
    }
    
    public void zzaO(final int n) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n);
    }
    
    public void zzaP(final int n) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n);
    }
    
    public void zzb(final ApplicationStatus applicationStatus) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzQV.zzb("onApplicationStatusChanged", new Object[0]);
        this.mHandler.post((Runnable)new zze$zzb$3(this, zze, applicationStatus));
    }
    
    public void zzb(final DeviceStatus deviceStatus) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzQV.zzb("onDeviceStatusChanged", new Object[0]);
        this.mHandler.post((Runnable)new zze$zzb$2(this, zze, deviceStatus));
    }
    
    public void zzb(final String s, final byte[] array) {
        if (this.zzUH.get() == null) {
            return;
        }
        zze.zzQV.zzb("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", s, array.length);
    }
    
    public void zzd(final String s, final long n) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n, 0);
    }
    
    public zze zzlU() {
        final zze zze = this.zzUH.getAndSet(null);
        if (zze == null) {
            return null;
        }
        zze.zzlJ();
        return zze;
    }
    
    public void zzq(final String s, final String s2) {
        final zze zze = this.zzUH.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzQV.zzb("Receive (type=text, ns=%s) %s", s, s2);
        this.mHandler.post((Runnable)new zze$zzb$4(this, zze, s, s2));
    }
}
