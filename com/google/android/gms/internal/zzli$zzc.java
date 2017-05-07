// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zza;
import java.lang.ref.WeakReference;
import android.os.IBinder$DeathRecipient;

class zzli$zzc implements IBinder$DeathRecipient, zzli$zze
{
    private final WeakReference<zza> zzacA;
    private final WeakReference<IBinder> zzacB;
    private final WeakReference<zzli$zzf<?>> zzacz;
    
    private zzli$zzc(final zzli$zzf zzli$zzf, final zza zza, final IBinder binder) {
        this.zzacA = new WeakReference<zza>(zza);
        this.zzacz = new WeakReference<zzli$zzf<?>>(zzli$zzf);
        this.zzacB = new WeakReference<IBinder>(binder);
    }
    
    private void zzoh() {
        final zzli$zzf zzli$zzf = this.zzacz.get();
        final zza zza = this.zzacA.get();
        if (zza != null && zzli$zzf != null) {
            zza.remove(zzli$zzf.zznF());
        }
        final IBinder binder = this.zzacB.get();
        if (this.zzacB != null) {
            binder.unlinkToDeath((IBinder$DeathRecipient)this, 0);
        }
    }
    
    public void binderDied() {
        this.zzoh();
    }
    
    public void zzc(final zzli$zzf<?> zzli$zzf) {
        this.zzoh();
    }
}
