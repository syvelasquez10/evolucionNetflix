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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc$zzb;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;

class zze$zzb$4 implements Runnable
{
    final /* synthetic */ String zzTb;
    final /* synthetic */ zze zzXl;
    final /* synthetic */ zze$zzb zzXn;
    final /* synthetic */ String zzXq;
    
    zze$zzb$4(final zze$zzb zzXn, final zze zzXl, final String zzTb, final String zzXq) {
        this.zzXn = zzXn;
        this.zzXl = zzXl;
        this.zzTb = zzTb;
        this.zzXq = zzXq;
    }
    
    @Override
    public void run() {
        synchronized (this.zzXl.zzWP) {
            final Cast$MessageReceivedCallback cast$MessageReceivedCallback = this.zzXl.zzWP.get(this.zzTb);
            // monitorexit(zze.zze(this.zzXl))
            if (cast$MessageReceivedCallback != null) {
                cast$MessageReceivedCallback.onMessageReceived(this.zzXl.zzWO, this.zzTb, this.zzXq);
                return;
            }
        }
        zze.zzTy.zzb("Discarded message for unknown namespace '%s'", this.zzTb);
    }
}
