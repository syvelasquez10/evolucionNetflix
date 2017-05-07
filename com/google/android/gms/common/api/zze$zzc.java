// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Map;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import com.google.android.gms.common.internal.zzu;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;

class zze$zzc implements GoogleApiClient$ConnectionProgressReportCallbacks
{
    private final WeakReference<zze> zzXd;
    private final Api<?> zzXj;
    private final int zzXk;
    
    public zze$zzc(final zze zze, final Api<?> zzXj, final int zzXk) {
        this.zzXd = new WeakReference<zze>(zze);
        this.zzXj = zzXj;
        this.zzXk = zzXk;
    }
    
    @Override
    public void onReportAccountValidation(final ConnectionResult connectionResult) {
        boolean zza = true;
        final zze zze = this.zzXd.get();
        if (zze == null) {
            return;
        }
        Label_0066: {
            if (Looper.myLooper() != zze.zzWJ.getLooper()) {
                break Label_0066;
            }
            while (true) {
                zzu.zza(zza, "onReportAccountValidation must be called on the GoogleApiClient handler thread");
                zze.zzWK.lock();
                try {
                    zza = zze.zzaW(1);
                    if (!zza) {
                        return;
                    }
                    if (!connectionResult.isSuccess()) {
                        zze.zzb(connectionResult, this.zzXj, this.zzXk);
                    }
                    if (zze.zzmA()) {
                        zze.zzmD();
                    }
                    return;
                    zza = false;
                }
                finally {
                    zze.zzWK.unlock();
                }
            }
        }
    }
    
    @Override
    public void onReportServiceBinding(final ConnectionResult connectionResult) {
        boolean b = false;
        final zze zze = this.zzXd.get();
        if (zze == null) {
            return;
        }
        if (Looper.myLooper() == zze.zzWJ.getLooper()) {
            b = true;
        }
        zzu.zza(b, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zze.zzWK.lock();
        try {
            if (!zze.zzaW(0)) {
                return;
            }
            if (!connectionResult.isSuccess()) {
                zze.zzb(connectionResult, this.zzXj, this.zzXk);
            }
            if (zze.zzmA()) {
                zze.zzmB();
            }
        }
        finally {
            zze.zzWK.unlock();
        }
    }
}
