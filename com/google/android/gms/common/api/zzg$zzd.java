// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Set;
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;
import com.google.android.gms.common.internal.zzx;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;

class zzg$zzd implements GoogleApiClient$zza
{
    private final WeakReference<zzg> zzZL;
    private final Api<?> zzZR;
    private final int zzZS;
    
    public zzg$zzd(final zzg zzg, final Api<?> zzZR, final int zzZS) {
        this.zzZL = new WeakReference<zzg>(zzg);
        this.zzZR = zzZR;
        this.zzZS = zzZS;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult) {
        boolean b = false;
        final zzg zzg = this.zzZL.get();
        if (zzg == null) {
            return;
        }
        if (Looper.myLooper() == zzg.zzZq.getLooper()) {
            b = true;
        }
        zzx.zza(b, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zzg.zzZs.lock();
        try {
            if (!zzg.zzbe(0)) {
                return;
            }
            if (!connectionResult.isSuccess()) {
                zzg.zzb(connectionResult, this.zzZR, this.zzZS);
            }
            if (zzg.zzno()) {
                zzg.zznp();
            }
        }
        finally {
            zzg.zzZs.unlock();
        }
    }
    
    @Override
    public void zzb(final ConnectionResult connectionResult) {
        boolean zza = true;
        final zzg zzg = this.zzZL.get();
        if (zzg == null) {
            return;
        }
        Label_0066: {
            if (Looper.myLooper() != zzg.zzZq.getLooper()) {
                break Label_0066;
            }
            while (true) {
                zzx.zza(zza, "onReportAccountValidation must be called on the GoogleApiClient handler thread");
                zzg.zzZs.lock();
                try {
                    zza = zzg.zzbe(1);
                    if (!zza) {
                        return;
                    }
                    if (!connectionResult.isSuccess()) {
                        zzg.zzb(connectionResult, this.zzZR, this.zzZS);
                    }
                    if (zzg.zzno()) {
                        zzg.zznr();
                    }
                    return;
                    zza = false;
                }
                finally {
                    zzg.zzZs.unlock();
                }
            }
        }
    }
}
