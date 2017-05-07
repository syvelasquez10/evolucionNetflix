// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.Status;
import android.util.Log;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.common.api.Result;

public class zzlq<R extends Result> extends zze<R> implements ResultCallback<R>
{
    private final Object zzabh;
    private zzb<? super R, ? extends Result> zzacY;
    private zzlq<? extends Result> zzacZ;
    private ResultCallbacks<? super R> zzada;
    private PendingResult<R> zzadb;
    
    private void zzd(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            Log.w("TransformedResultImpl", "Unable to release " + result, (Throwable)ex);
        }
    }
    
    private void zzon() {
        if (this.zzadb == null || (this.zzacY == null && this.zzada == null)) {
            return;
        }
        this.zzadb.setResultCallback(this);
    }
    
    @Override
    public void onResult(final R r) {
        while (true) {
            final Result result;
            Label_0096: {
                synchronized (this.zzabh) {
                    if (!r.getStatus().isSuccess()) {
                        break Label_0096;
                    }
                    if (this.zzacY != null) {
                        final PendingResult<? extends Result> zza = this.zzacY.zza((Object)r);
                        if (zza == null) {
                            this.zzx(new Status(13, "Transform returned null"));
                        }
                        else {
                            this.zzacZ.zza(zza);
                        }
                        this.zzd(r);
                        return;
                    }
                }
                if (this.zzada != null) {
                    this.zzada.onSuccess((Object)result);
                    return;
                }
                return;
            }
            this.zzx(result.getStatus());
            this.zzd(result);
        }
    }
    
    public void zza(final PendingResult<?> zzadb) {
        synchronized (this.zzabh) {
            this.zzadb = (PendingResult<R>)zzadb;
            this.zzon();
        }
    }
    
    public void zzx(Status zzu) {
        synchronized (this.zzabh) {
            if (this.zzacY != null) {
                zzu = this.zzacY.zzu(zzu);
                zzx.zzb(zzu, "onFailure must not return null");
                this.zzacZ.zzx(zzu);
            }
            else if (this.zzada != null) {
                this.zzada.onFailure(zzu);
            }
        }
    }
}
