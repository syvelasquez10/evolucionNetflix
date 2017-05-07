// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.tagmanager.zzbg;
import com.google.android.gms.common.api.Status;

class zzqk$zzb extends zzqx
{
    final /* synthetic */ zzqk zzaTD;
    private final zzqk$zza zzaTE;
    
    zzqk$zzb(final zzqk zzaTD, final zzqn zzqn, final zzql zzql, final zzqk$zza zzaTE) {
        this.zzaTD = zzaTD;
        super(zzqn, zzql);
        this.zzaTE = zzaTE;
    }
    
    @Override
    protected zzqx$zzb zza(final zzqi zzqi) {
        return null;
    }
    
    @Override
    protected void zza(final zzqo zzqo) {
        final zzqo$zza zzBw = zzqo.zzBw();
        this.zzaTD.zza(zzBw);
        if (zzBw.getStatus() == Status.zzaaD && zzBw.zzBx() == zzqo$zza$zza.zzaTO && zzBw.zzBy() != null && zzBw.zzBy().length > 0) {
            this.zzaTD.zzaTy.zze(zzBw.zzBz().zzBr(), zzBw.zzBy());
            zzbg.v("Resource successfully load from Network.");
            this.zzaTE.zza(zzqo);
            return;
        }
        final StringBuilder append = new StringBuilder().append("Response status: ");
        String s;
        if (zzBw.getStatus().isSuccess()) {
            s = "SUCCESS";
        }
        else {
            s = "FAILURE";
        }
        zzbg.v(append.append(s).toString());
        if (zzBw.getStatus().isSuccess()) {
            zzbg.v("Response source: " + zzBw.zzBx().toString());
            zzbg.v("Response size: " + zzBw.zzBy().length);
        }
        this.zzaTD.zza(zzBw.zzBz(), this.zzaTE);
    }
}
