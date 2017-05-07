// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

class zzqk$1 implements zzqq
{
    final /* synthetic */ zzqi zzaTB;
    final /* synthetic */ zzqk$zza zzaTC;
    final /* synthetic */ zzqk zzaTD;
    
    zzqk$1(final zzqk zzaTD, final zzqi zzaTB, final zzqk$zza zzaTC) {
        this.zzaTD = zzaTD;
        this.zzaTB = zzaTB;
        this.zzaTC = zzaTC;
    }
    
    @Override
    public void zza(final Status status, final Object o, final Integer n, final long n2) {
        zzqo$zza zzqo$zza;
        if (status.isSuccess()) {
            zzqo$zza$zza zzqo$zza$zza;
            if (n == zzqr.zzaUg) {
                zzqo$zza$zza = com.google.android.gms.internal.zzqo$zza$zza.zzaTQ;
            }
            else {
                zzqo$zza$zza = com.google.android.gms.internal.zzqo$zza$zza.zzaTP;
            }
            zzqo$zza = new zzqo$zza(Status.zzaaD, this.zzaTB, null, (zzqp$zzc)o, zzqo$zza$zza, n2);
        }
        else {
            zzqo$zza = new zzqo$zza(new Status(16, "There is no valid resource for the container: " + this.zzaTB.getContainerId()), null, zzqo$zza$zza.zzaTP);
        }
        this.zzaTC.zza(new zzqo(zzqo$zza));
    }
}
