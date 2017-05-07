// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzu;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zza$zza<R extends Result, A extends Api$Client> extends AbstractPendingResult<R> implements zza$zzb<R>, zzg$zze<A>
{
    private final Api$ClientKey<A> zzVt;
    private AtomicReference<zzg$zzc> zzWl;
    
    protected zza$zza(final Api$ClientKey<A> api$ClientKey, final GoogleApiClient googleApiClient) {
        super(zzu.zzb(googleApiClient, "GoogleApiClient must not be null").getLooper());
        this.zzWl = new AtomicReference<zzg$zzc>();
        this.zzVt = zzu.zzu(api$ClientKey);
    }
    
    private void zza(final RemoteException ex) {
        this.zzr(new Status(8, ex.getLocalizedMessage(), null));
    }
    
    @Override
    protected void onResultConsumed() {
        final zzg$zzc zzg$zzc = this.zzWl.getAndSet(null);
        if (zzg$zzc != null) {
            zzg$zzc.zzc(this);
        }
    }
    
    protected abstract void zza(final A p0);
    
    @Override
    public void zza(final zzg$zzc zzg$zzc) {
        this.zzWl.set(zzg$zzc);
    }
    
    @Override
    public final void zzb(final A a) {
        try {
            this.zza(a);
        }
        catch (DeadObjectException ex) {
            this.zza((RemoteException)ex);
            throw ex;
        }
        catch (RemoteException ex2) {
            this.zza(ex2);
        }
    }
    
    @Override
    public final Api$ClientKey<A> zzmq() {
        return this.zzVt;
    }
    
    @Override
    public int zzmt() {
        return 0;
    }
    
    @Override
    public final void zzr(final Status status) {
        zzu.zzb(!status.isSuccess(), "Failed result must not be success");
        this.setResult(this.createFailedResult(status));
    }
}
