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
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.common.api.zza$zzb;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzi;

class zze$zzb$1 implements Runnable
{
    final /* synthetic */ zze zzUI;
    final /* synthetic */ int zzUJ;
    final /* synthetic */ zze$zzb zzUK;
    
    zze$zzb$1(final zze$zzb zzUK, final zze zzUI, final int zzUJ) {
        this.zzUK = zzUK;
        this.zzUI = zzUI;
        this.zzUJ = zzUJ;
    }
    
    @Override
    public void run() {
        if (this.zzUI.zzQH != null) {
            this.zzUI.zzQH.onApplicationDisconnected(this.zzUJ);
        }
    }
}
