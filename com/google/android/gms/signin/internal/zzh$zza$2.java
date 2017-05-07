// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.zzpt;
import android.os.RemoteException;
import android.util.Log;

class zzh$zza$2 implements Runnable
{
    final /* synthetic */ String zzaKc;
    final /* synthetic */ zzf zzaKd;
    final /* synthetic */ zzh$zza zzaKe;
    final /* synthetic */ String zzaKf;
    
    zzh$zza$2(final zzh$zza zzaKe, final String zzaKc, final String zzaKf, final zzf zzaKd) {
        this.zzaKe = zzaKe;
        this.zzaKc = zzaKc;
        this.zzaKf = zzaKf;
        this.zzaKd = zzaKd;
    }
    
    @Override
    public void run() {
        try {
            this.zzaKd.zzal(this.zzaKe.zzxZ().onUploadServerAuthCode(this.zzaKc, this.zzaKf));
        }
        catch (RemoteException ex) {
            Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", (Throwable)ex);
        }
    }
}
