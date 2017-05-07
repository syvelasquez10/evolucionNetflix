// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Context;

class zzcm implements zzp$zze
{
    private boolean mClosed;
    private final Context mContext;
    private final String zzaOS;
    private String zzaPp;
    private zzs zzaRp;
    private final ScheduledExecutorService zzaRr;
    private final zzcm$zza zzaRs;
    private ScheduledFuture<?> zzaRt;
    
    public zzcm(final Context context, final String s, final zzs zzs) {
        this(context, s, zzs, null, null);
    }
    
    zzcm(final Context mContext, final String zzaOS, final zzs zzaRp, final zzcm$zzb zzcm$zzb, final zzcm$zza zzaRs) {
        this.zzaRp = zzaRp;
        this.mContext = mContext;
        this.zzaOS = zzaOS;
        zzcm$zzb zzcm$zzb2 = zzcm$zzb;
        if (zzcm$zzb == null) {
            zzcm$zzb2 = new zzcm$1(this);
        }
        this.zzaRr = zzcm$zzb2.zzAC();
        if (zzaRs == null) {
            this.zzaRs = new zzcm$2(this);
            return;
        }
        this.zzaRs = zzaRs;
    }
    
    private void zzAB() {
        synchronized (this) {
            if (this.mClosed) {
                throw new IllegalStateException("called method after closed");
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void release() {
        synchronized (this) {
            this.zzAB();
            if (this.zzaRt != null) {
                this.zzaRt.cancel(false);
            }
            this.zzaRr.shutdown();
            this.mClosed = true;
        }
    }
    
    @Override
    public void zzeB(final String zzaPp) {
        synchronized (this) {
            this.zzAB();
            this.zzaPp = zzaPp;
        }
    }
}
