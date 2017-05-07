// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzaf$zzj;
import com.google.android.gms.internal.zzqk;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.internal.zzqp$zzc;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzqo;
import com.google.android.gms.internal.zzqk$zza;

class zzp$1 implements zzqk$zza
{
    final /* synthetic */ String zzaPs;
    final /* synthetic */ zzp zzaPt;
    
    zzp$1(final zzp zzaPt, final String zzaPs) {
        this.zzaPt = zzaPt;
        this.zzaPs = zzaPs;
    }
    
    @Override
    public void zza(final zzqo zzqo) {
        if (zzqo.getStatus() != Status.zzaaD) {
            zzbg.e("Load request failed for the container " + this.zzaPt.zzaOS);
            this.zzaPt.zza(this.zzaPt.zzbg(Status.zzaaF));
            return;
        }
        final zzqp$zzc zzBA = zzqo.zzBw().zzBA();
        if (zzBA == null) {
            zzbg.e("Response doesn't have the requested container");
            this.zzaPt.zza(this.zzaPt.zzbg(new Status(8, "Response doesn't have the requested container", null)));
            return;
        }
        this.zzaPt.zzaPm = new zzo(this.zzaPt.zzaPe, this.zzaPt.zzYV, new Container(this.zzaPt.mContext, this.zzaPt.zzaPe.getDataLayer(), this.zzaPt.zzaOS, zzqo.zzBw().zzBB(), zzBA), new zzp$1$1(this));
        ((zzb<zzo>)this.zzaPt).zza(this.zzaPt.zzaPm);
    }
}
