// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks$CheckResult;
import android.os.RemoteException;
import android.util.Log;
import java.util.Set;
import java.util.Collections;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.HashSet;
import java.util.List;

class zzh$zza$1 implements Runnable
{
    final /* synthetic */ List zzaKb;
    final /* synthetic */ String zzaKc;
    final /* synthetic */ zzf zzaKd;
    final /* synthetic */ zzh$zza zzaKe;
    
    zzh$zza$1(final zzh$zza zzaKe, final List zzaKb, final String zzaKc, final zzf zzaKd) {
        this.zzaKe = zzaKe;
        this.zzaKb = zzaKb;
        this.zzaKc = zzaKc;
        this.zzaKd = zzaKd;
    }
    
    @Override
    public void run() {
        try {
            final GoogleApiClient$ServerAuthCodeCallbacks$CheckResult onCheckServerAuthorization = this.zzaKe.zzxZ().onCheckServerAuthorization(this.zzaKc, Collections.unmodifiableSet((Set<? extends Scope>)new HashSet<Scope>(this.zzaKb)));
            this.zzaKd.zza(new CheckServerAuthResult(onCheckServerAuthorization.zzmy(), onCheckServerAuthorization.zzmz()));
        }
        catch (RemoteException ex) {
            Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", (Throwable)ex);
        }
    }
}
