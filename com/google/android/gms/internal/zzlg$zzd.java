// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.common.api.Api$zzb;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.api.Api$zzc;
import java.util.Set;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import com.google.android.gms.common.internal.zzx;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.GoogleApiClient$zza;

class zzlg$zzd implements GoogleApiClient$zza
{
    private final WeakReference<zzlg> zzabM;
    private final Api<?> zzabS;
    private final int zzabT;
    
    public zzlg$zzd(final zzlg zzlg, final Api<?> zzabS, final int zzabT) {
        this.zzabM = new WeakReference<zzlg>(zzlg);
        this.zzabS = zzabS;
        this.zzabT = zzabT;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult) {
        boolean b = false;
        final zzlg zzlg = this.zzabM.get();
        if (zzlg == null) {
            return;
        }
        if (Looper.myLooper() == zzlg.zzabr.getLooper()) {
            b = true;
        }
        zzx.zza(b, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zzlg.zzabt.lock();
        try {
            if (!zzlg.zzbn(0)) {
                return;
            }
            if (!connectionResult.isSuccess()) {
                zzlg.zzb(connectionResult, this.zzabS, this.zzabT);
            }
            if (zzlg.zznP()) {
                zzlg.zznQ();
            }
        }
        finally {
            zzlg.zzabt.unlock();
        }
    }
    
    @Override
    public void zzb(final ConnectionResult connectionResult) {
        boolean zza = true;
        final zzlg zzlg = this.zzabM.get();
        if (zzlg == null) {
            return;
        }
        Label_0066: {
            if (Looper.myLooper() != zzlg.zzabr.getLooper()) {
                break Label_0066;
            }
            while (true) {
                zzx.zza(zza, "onReportAccountValidation must be called on the GoogleApiClient handler thread");
                zzlg.zzabt.lock();
                try {
                    zza = zzlg.zzbn(1);
                    if (!zza) {
                        return;
                    }
                    if (!connectionResult.isSuccess()) {
                        zzlg.zzb(connectionResult, this.zzabS, this.zzabT);
                    }
                    if (zzlg.zznP()) {
                        zzlg.zznS();
                    }
                    return;
                    zza = false;
                }
                finally {
                    zzlg.zzabt.unlock();
                }
            }
        }
    }
}
