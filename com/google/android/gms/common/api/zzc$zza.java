// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzc$zza<R extends Result, A extends Api$zzb> extends zza<R> implements zzc$zzb<R>, zzi$zze<A>
{
    private final Api$zzc<A> zzXW;
    private AtomicReference<zzi$zzd> zzYO;
    
    protected zzc$zza(final Api$zzc<A> api$zzc, final GoogleApiClient googleApiClient) {
        super(zzx.zzb(googleApiClient, "GoogleApiClient must not be null").getLooper());
        this.zzYO = new AtomicReference<zzi$zzd>();
        this.zzXW = zzx.zzv(api$zzc);
    }
    
    private void zza(final RemoteException ex) {
        this.zzx(new Status(8, ex.getLocalizedMessage(), null));
    }
    
    protected abstract void zza(final A p0);
    
    @Override
    public void zza(final zzi$zzd zzi$zzd) {
        this.zzYO.set(zzi$zzd);
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
    protected void zzmZ() {
        final zzi$zzd zzi$zzd = this.zzYO.getAndSet(null);
        if (zzi$zzd != null) {
            zzi$zzd.zzc(this);
        }
    }
    
    @Override
    public final Api$zzc<A> zznd() {
        return this.zzXW;
    }
    
    @Override
    public int zzng() {
        return 0;
    }
    
    @Override
    public final void zzx(final Status status) {
        zzx.zzb(!status.isSuccess(), "Failed result must not be success");
        this.zza(this.zzb(status));
    }
}
