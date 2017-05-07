// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.signin.zze;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks$CheckResult;
import android.os.RemoteException;
import android.util.Log;
import java.util.Set;
import java.util.Collections;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.HashSet;
import java.util.List;

class zzi$zza$1 implements Runnable
{
    final /* synthetic */ List zzaOp;
    final /* synthetic */ String zzaOq;
    final /* synthetic */ zzf zzaOr;
    final /* synthetic */ zzi$zza zzaOs;
    
    zzi$zza$1(final zzi$zza zzaOs, final List zzaOp, final String zzaOq, final zzf zzaOr) {
        this.zzaOs = zzaOs;
        this.zzaOp = zzaOp;
        this.zzaOq = zzaOq;
        this.zzaOr = zzaOr;
    }
    
    @Override
    public void run() {
        try {
            final GoogleApiClient$ServerAuthCodeCallbacks$CheckResult onCheckServerAuthorization = this.zzaOs.zzzq().onCheckServerAuthorization(this.zzaOq, Collections.unmodifiableSet((Set<? extends Scope>)new HashSet<Scope>(this.zzaOp)));
            this.zzaOr.zza(new CheckServerAuthResult(onCheckServerAuthorization.zznl(), onCheckServerAuthorization.zznm()));
        }
        catch (RemoteException ex) {
            Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", (Throwable)ex);
        }
    }
}
