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
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc$zzb;
import java.util.concurrent.atomic.AtomicReference;
import android.os.Handler;

class zze$zzb extends zzj$zza
{
    private final Handler mHandler;
    private final AtomicReference<zze> zzXk;
    
    public zze$zzb(final zze zze) {
        this.zzXk = new AtomicReference<zze>(zze);
        this.mHandler = new Handler(zze.getLooper());
    }
    
    private void zza(final zze zze, final long n, final int n2) {
        synchronized (zze.zzXc) {
            final zzc$zzb<Status> zzc$zzb = zze.zzXc.remove(n);
            // monitorexit(zze.zzg(zze))
            if (zzc$zzb != null) {
                zzc$zzb.zzn(new Status(n2));
            }
        }
    }
    
    private boolean zza(final zze zze, final int n) {
        synchronized (zze.zzXg) {
            if (zze.zzXe != null) {
                zze.zzXe.zzn(new Status(n));
                zze.zzXe = null;
                return true;
            }
            return false;
        }
    }
    
    public boolean isDisposed() {
        return this.zzXk.get() == null;
    }
    
    public void onApplicationDisconnected(final int n) {
        final zze zze = this.zzXk.get();
        if (zze != null) {
            zze.zzWZ = null;
            zze.zzXa = null;
            this.zza(zze, n);
            if (zze.zzTk != null) {
                this.mHandler.post((Runnable)new zze$zzb$1(this, zze, n));
            }
        }
    }
    
    public void zza(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        zze.zzWN = applicationMetadata;
        zze.zzWZ = applicationMetadata.getApplicationId();
        zze.zzXa = s2;
        synchronized (com.google.android.gms.cast.internal.zze.zzXf) {
            if (zze.zzXd != null) {
                zze.zzXd.zzn(new zze$zza(new Status(0), applicationMetadata, s, s2, b));
                zze.zzXd = null;
            }
        }
    }
    
    public void zza(final String s, final double n, final boolean b) {
        zze.zzTy.zzb("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }
    
    public void zza(final String s, final long n, final int n2) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n, n2);
    }
    
    public void zzaU(final int n) {
        final zze zzmG = this.zzmG();
        if (zzmG != null) {
            zze.zzTy.zzb("ICastDeviceControllerListener.onDisconnected: %d", n);
            if (n != 0) {
                zzmG.zzbz(2);
            }
        }
    }
    
    public void zzaV(final int n) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        synchronized (com.google.android.gms.cast.internal.zze.zzXf) {
            if (zze.zzXd != null) {
                zze.zzXd.zzn(new zze$zza(new Status(n)));
                zze.zzXd = null;
            }
        }
    }
    
    public void zzaW(final int n) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n);
    }
    
    public void zzaX(final int n) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n);
    }
    
    public void zzb(final ApplicationStatus applicationStatus) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzTy.zzb("onApplicationStatusChanged", new Object[0]);
        this.mHandler.post((Runnable)new zze$zzb$3(this, zze, applicationStatus));
    }
    
    public void zzb(final DeviceStatus deviceStatus) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzTy.zzb("onDeviceStatusChanged", new Object[0]);
        this.mHandler.post((Runnable)new zze$zzb$2(this, zze, deviceStatus));
    }
    
    public void zzb(final String s, final byte[] array) {
        if (this.zzXk.get() == null) {
            return;
        }
        zze.zzTy.zzb("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", s, array.length);
    }
    
    public void zzd(final String s, final long n) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n, 0);
    }
    
    public zze zzmG() {
        final zze zze = this.zzXk.getAndSet(null);
        if (zze == null) {
            return null;
        }
        zze.zzmv();
        return zze;
    }
    
    public void zzs(final String s, final String s2) {
        final zze zze = this.zzXk.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzTy.zzb("Receive (type=text, ns=%s) %s", s, s2);
        this.mHandler.post((Runnable)new zze$zzb$4(this, zze, s, s2));
    }
}
