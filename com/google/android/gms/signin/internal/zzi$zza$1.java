// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.internal.zzqx;
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
    final /* synthetic */ List zzaVn;
    final /* synthetic */ String zzaVo;
    final /* synthetic */ zzf zzaVp;
    final /* synthetic */ zzi$zza zzaVq;
    
    zzi$zza$1(final zzi$zza zzaVq, final List zzaVn, final String zzaVo, final zzf zzaVp) {
        this.zzaVq = zzaVq;
        this.zzaVn = zzaVn;
        this.zzaVo = zzaVo;
        this.zzaVp = zzaVp;
    }
    
    @Override
    public void run() {
        try {
            final GoogleApiClient$ServerAuthCodeCallbacks$CheckResult onCheckServerAuthorization = this.zzaVq.zzCg().onCheckServerAuthorization(this.zzaVo, Collections.unmodifiableSet((Set<? extends Scope>)new HashSet<Scope>(this.zzaVn)));
            this.zzaVp.zza(new CheckServerAuthResult(onCheckServerAuthorization.zznD(), onCheckServerAuthorization.zznE()));
        }
        catch (RemoteException ex) {
            Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", (Throwable)ex);
        }
    }
}
