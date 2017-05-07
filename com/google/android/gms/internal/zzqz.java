// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.tagmanager.zzbg;
import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;

public class zzqz
{
    private boolean mClosed;
    private String zzaPw;
    private final ScheduledExecutorService zzaRr;
    private ScheduledFuture<?> zzaRt;
    
    public zzqz() {
        this(Executors.newSingleThreadScheduledExecutor());
    }
    
    public zzqz(final String zzaPw) {
        this(Executors.newSingleThreadScheduledExecutor());
        this.zzaPw = zzaPw;
    }
    
    zzqz(final ScheduledExecutorService zzaRr) {
        this.zzaRt = null;
        this.zzaPw = null;
        this.zzaRr = zzaRr;
        this.mClosed = false;
    }
    
    public void zza(final Context context, final zzqn zzqn, final long n, final zzqx zzqx) {
        while (true) {
            while (true) {
                synchronized (this) {
                    zzbg.v("ResourceLoaderScheduler: Loading new resource.");
                    if (this.zzaRt != null) {
                        return;
                    }
                    if (this.zzaPw != null) {
                        final zzqy zzqy = new zzqy(context, zzqn, zzqx, this.zzaPw);
                        this.zzaRt = this.zzaRr.schedule(zzqy, n, TimeUnit.MILLISECONDS);
                        return;
                    }
                }
                final Context context2;
                final zzqy zzqy = new zzqy(context2, zzqn, zzqx);
                continue;
            }
        }
    }
}
