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
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.common.api.zza$zzb;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;

class zze$zzb$4 implements Runnable
{
    final /* synthetic */ String zzQy;
    final /* synthetic */ zze zzUI;
    final /* synthetic */ zze$zzb zzUK;
    final /* synthetic */ String zzUN;
    
    zze$zzb$4(final zze$zzb zzUK, final zze zzUI, final String zzQy, final String zzUN) {
        this.zzUK = zzUK;
        this.zzUI = zzUI;
        this.zzQy = zzQy;
        this.zzUN = zzUN;
    }
    
    @Override
    public void run() {
        synchronized (this.zzUI.zzUm) {
            final Cast$MessageReceivedCallback cast$MessageReceivedCallback = this.zzUI.zzUm.get(this.zzQy);
            // monitorexit(zze.zze(this.zzUI))
            if (cast$MessageReceivedCallback != null) {
                cast$MessageReceivedCallback.onMessageReceived(this.zzUI.zzUl, this.zzQy, this.zzUN);
                return;
            }
        }
        zze.zzQV.zzb("Discarded message for unknown namespace '%s'", this.zzQy);
    }
}
