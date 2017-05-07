// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.signin.zze;
import java.util.concurrent.ExecutorService;
import android.os.RemoteException;
import android.util.Log;

class zzi$zza$2 implements Runnable
{
    final /* synthetic */ String zzaOq;
    final /* synthetic */ zzf zzaOr;
    final /* synthetic */ zzi$zza zzaOs;
    final /* synthetic */ String zzaOt;
    
    zzi$zza$2(final zzi$zza zzaOs, final String zzaOq, final String zzaOt, final zzf zzaOr) {
        this.zzaOs = zzaOs;
        this.zzaOq = zzaOq;
        this.zzaOt = zzaOt;
        this.zzaOr = zzaOr;
    }
    
    @Override
    public void run() {
        try {
            this.zzaOr.zzaq(this.zzaOs.zzzq().onUploadServerAuthCode(this.zzaOq, this.zzaOt));
        }
        catch (RemoteException ex) {
            Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", (Throwable)ex);
        }
    }
}
