// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.internal.zzqx;
import java.util.concurrent.ExecutorService;
import android.os.RemoteException;
import android.util.Log;

class zzi$zza$2 implements Runnable
{
    final /* synthetic */ String zzaVo;
    final /* synthetic */ zzf zzaVp;
    final /* synthetic */ zzi$zza zzaVq;
    final /* synthetic */ String zzaVr;
    
    zzi$zza$2(final zzi$zza zzaVq, final String zzaVo, final String zzaVr, final zzf zzaVp) {
        this.zzaVq = zzaVq;
        this.zzaVo = zzaVo;
        this.zzaVr = zzaVr;
        this.zzaVp = zzaVp;
    }
    
    @Override
    public void run() {
        try {
            this.zzaVp.zzaq(this.zzaVq.zzCg().onUploadServerAuthCode(this.zzaVo, this.zzaVr));
        }
        catch (RemoteException ex) {
            Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", (Throwable)ex);
        }
    }
}
