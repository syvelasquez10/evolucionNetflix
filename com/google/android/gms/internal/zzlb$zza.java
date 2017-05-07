// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.ResultCallback;
import android.os.DeadObjectException;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.api.Api$zzc;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.Result;

public abstract class zzlb$zza<R extends Result, A extends Api$zzb> extends zzlc<R> implements zzlb$zzb<R>, zzli$zzf<A>
{
    private final Api$zzc<A> zzZM;
    private AtomicReference<zzli$zze> zzabg;
    
    protected zzlb$zza(final Api$zzc<A> api$zzc, final GoogleApiClient googleApiClient) {
        super(zzx.zzb(googleApiClient, "GoogleApiClient must not be null").getLooper());
        this.zzabg = new AtomicReference<zzli$zze>();
        this.zzZM = zzx.zzw(api$zzc);
    }
    
    private void zza(final RemoteException ex) {
        this.zzv(new Status(8, ex.getLocalizedMessage(), null));
    }
    
    protected abstract void zza(final A p0);
    
    @Override
    public void zza(final zzli$zze zzli$zze) {
        this.zzabg.set(zzli$zze);
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
    public void zznJ() {
        this.setResultCallback(null);
    }
    
    @Override
    public int zznK() {
        return 0;
    }
    
    @Override
    protected void zznL() {
        final zzli$zze zzli$zze = this.zzabg.getAndSet(null);
        if (zzli$zze != null) {
            zzli$zze.zzc(this);
        }
    }
    
    @Override
    public final Api$zzc<A> zznx() {
        return this.zzZM;
    }
    
    @Override
    public final void zzv(final Status status) {
        zzx.zzb(!status.isSuccess(), "Failed result must not be success");
        this.zzb(this.zzb(status));
    }
}
