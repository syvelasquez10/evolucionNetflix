// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import java.util.List;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class zzqk
{
    private final Context mContext;
    private String zzaPw;
    private final Map<String, zzqz> zzaTA;
    private final zzqr zzaTy;
    Map<String, zzqk$zzc<zzqp$zzc>> zzaTz;
    private final zzlm zzpO;
    
    public zzqk(final Context context) {
        this(context, new HashMap<String, zzqz>(), new zzqr(context), zzlo.zzpN());
    }
    
    zzqk(final Context mContext, final Map<String, zzqz> zzaTA, final zzqr zzaTy, final zzlm zzpO) {
        this.zzaPw = null;
        this.zzaTz = new HashMap<String, zzqk$zzc<zzqp$zzc>>();
        this.mContext = mContext;
        this.zzpO = zzpO;
        this.zzaTy = zzaTy;
        this.zzaTA = zzaTA;
    }
    
    private void zza(final zzqn zzqn, final zzqk$zza zzqk$zza) {
        boolean b = true;
        final List<zzqi> zzBv = zzqn.zzBv();
        if (zzBv.size() != 1) {
            b = false;
        }
        zzx.zzZ(b);
        this.zza(zzBv.get(0), zzqk$zza);
    }
    
    void zza(final zzqi zzqi, final zzqk$zza zzqk$zza) {
        this.zzaTy.zza(zzqi.zzBr(), zzqi.zzBp(), zzqm.zzaTG, new zzqk$1(this, zzqi, zzqk$zza));
    }
    
    void zza(final zzqn zzqn, final zzqk$zza zzqk$zza, final zzqx zzqx) {
        final Iterator<zzqi> iterator = zzqn.zzBv().iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            final zzqi zzqi = iterator.next();
            final zzqk$zzc<zzqp$zzc> zzqk$zzc = this.zzaTz.get(zzqi.getContainerId());
            long n;
            if (zzqk$zzc != null) {
                n = zzqk$zzc.zzBu();
            }
            else {
                n = this.zzaTy.zzfp(zzqi.getContainerId());
            }
            if (n + 900000L < this.zzpO.currentTimeMillis()) {
                b = true;
            }
        }
        if (b) {
            zzqz zzqz = this.zzaTA.get(zzqn.getId());
            if (zzqz == null) {
                if (this.zzaPw == null) {
                    zzqz = new zzqz();
                }
                else {
                    zzqz = new zzqz(this.zzaPw);
                }
                this.zzaTA.put(zzqn.getId(), zzqz);
            }
            zzqz.zza(this.mContext, zzqn, 0L, zzqx);
            return;
        }
        this.zza(zzqn, zzqk$zza);
    }
    
    void zza(final zzqo$zza zzqo$zza) {
        final String containerId = zzqo$zza.zzBz().getContainerId();
        final Status status = zzqo$zza.getStatus();
        final zzqp$zzc zzBA = zzqo$zza.zzBA();
        if (this.zzaTz.containsKey(containerId)) {
            final zzqk$zzc<zzqp$zzc> zzqk$zzc = this.zzaTz.get(containerId);
            zzqk$zzc.zzU(this.zzpO.currentTimeMillis());
            if (status == Status.zzaaD) {
                zzqk$zzc.zzbh(status);
                zzqk$zzc.zzQ(zzBA);
            }
            return;
        }
        this.zzaTz.put(containerId, new zzqk$zzc<zzqp$zzc>(status, zzBA, this.zzpO.currentTimeMillis()));
    }
    
    public void zza(final String s, final Integer n, final String s2, final zzqk$zza zzqk$zza) {
        final zzqn zzb = new zzqn().zzb(new zzqi(s, n, s2, false));
        this.zza(zzb, zzqk$zza, new zzqk$zzb(this, zzb, zzqm.zzaTG, zzqk$zza));
    }
    
    public void zzfj(final String zzaPw) {
        this.zzaPw = zzaPw;
    }
}
