// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.text.TextUtils;
import com.google.android.gms.cast.JoinOptions;
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
import com.google.android.gms.internal.zzlb$zzb;
import java.util.concurrent.atomic.AtomicReference;
import android.os.Handler;

class zze$zzb extends zzj$zza
{
    private final Handler mHandler;
    private final AtomicReference<zze> zzZc;
    
    public zze$zzb(final zze zze) {
        this.zzZc = new AtomicReference<zze>(zze);
        this.mHandler = new Handler(zze.getLooper());
    }
    
    private void zza(final zze zze, final long n, final int n2) {
        synchronized (zze.zzYU) {
            final zzlb$zzb<Status> zzlb$zzb = zze.zzYU.remove(n);
            // monitorexit(zze.zzg(zze))
            if (zzlb$zzb != null) {
                zzlb$zzb.zzp(new Status(n2));
            }
        }
    }
    
    private boolean zza(final zze zze, final int n) {
        synchronized (zze.zzYY) {
            if (zze.zzYW != null) {
                zze.zzYW.zzp(new Status(n));
                zze.zzYW = null;
                return true;
            }
            return false;
        }
    }
    
    public boolean isDisposed() {
        return this.zzZc.get() == null;
    }
    
    public void onApplicationDisconnected(final int n) {
        final zze zze = this.zzZc.get();
        if (zze != null) {
            zze.zzYR = null;
            zze.zzYS = null;
            this.zza(zze, n);
            if (zze.zzUZ != null) {
                this.mHandler.post((Runnable)new zze$zzb$1(this, zze, n));
            }
        }
    }
    
    public void zza(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        zze.zzYF = applicationMetadata;
        zze.zzYR = applicationMetadata.getApplicationId();
        zze.zzYS = s2;
        synchronized (com.google.android.gms.cast.internal.zze.zzYX) {
            if (zze.zzYV != null) {
                zze.zzYV.zzp(new zze$zza(new Status(0), applicationMetadata, s, s2, b));
                zze.zzYV = null;
            }
        }
    }
    
    public void zza(final String s, final double n, final boolean b) {
        zze.zzVo.zzb("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }
    
    public void zza(final String s, final long n, final int n2) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n, n2);
    }
    
    public void zzb(final ApplicationStatus applicationStatus) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzVo.zzb("onApplicationStatusChanged", new Object[0]);
        this.mHandler.post((Runnable)new zze$zzb$3(this, zze, applicationStatus));
    }
    
    public void zzb(final DeviceStatus deviceStatus) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzVo.zzb("onDeviceStatusChanged", new Object[0]);
        this.mHandler.post((Runnable)new zze$zzb$2(this, zze, deviceStatus));
    }
    
    public void zzb(final String s, final byte[] array) {
        if (this.zzZc.get() == null) {
            return;
        }
        zze.zzVo.zzb("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", s, array.length);
    }
    
    public void zzbb(final int n) {
        final zze zznc = this.zznc();
        if (zznc != null) {
            zze.zzVo.zzb("ICastDeviceControllerListener.onDisconnected: %d", n);
            if (n != 0) {
                zznc.zzbE(2);
            }
        }
    }
    
    public void zzbc(final int n) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        synchronized (com.google.android.gms.cast.internal.zze.zzYX) {
            if (zze.zzYV != null) {
                zze.zzYV.zzp(new zze$zza(new Status(n)));
                zze.zzYV = null;
            }
        }
    }
    
    public void zzbd(final int n) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n);
    }
    
    public void zzbe(final int n) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n);
    }
    
    public void zzd(final String s, final long n) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        this.zza(zze, n, 0);
    }
    
    public zze zznc() {
        final zze zze = this.zzZc.getAndSet(null);
        if (zze == null) {
            return null;
        }
        zze.zzmR();
        return zze;
    }
    
    public void zzs(final String s, final String s2) {
        final zze zze = this.zzZc.get();
        if (zze == null) {
            return;
        }
        com.google.android.gms.cast.internal.zze.zzVo.zzb("Receive (type=text, ns=%s) %s", s, s2);
        this.mHandler.post((Runnable)new zze$zzb$4(this, zze, s, s2));
    }
}
