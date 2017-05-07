// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;

public class zza
{
    private static Object zzaOF;
    private static zza zzaOG;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread zzHt;
    private volatile AdvertisingIdClient$Info zzLl;
    private volatile long zzaOB;
    private volatile long zzaOC;
    private volatile long zzaOD;
    private zza$zza zzaOE;
    private final zzlm zzpO;
    
    static {
        zza.zzaOF = new Object();
    }
    
    private zza(final Context context) {
        this(context, null, zzlo.zzpN());
    }
    
    public zza(final Context mContext, final zza$zza zzaOE, final zzlm zzpO) {
        this.zzaOB = 900000L;
        this.zzaOC = 30000L;
        this.mClosed = false;
        this.zzaOE = new zza$1(this);
        this.zzpO = zzpO;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        if (zzaOE != null) {
            this.zzaOE = zzaOE;
        }
        this.zzHt = new Thread(new zza$2(this));
    }
    
    public static zza zzaL(final Context context) {
        Label_0037: {
            if (zza.zzaOG != null) {
                break Label_0037;
            }
            synchronized (zza.zzaOF) {
                if (zza.zzaOG == null) {
                    (zza.zzaOG = new zza(context)).start();
                }
                return zza.zzaOG;
            }
        }
    }
    
    private void zzzu() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.zzLl = this.zzaOE.zzzw();
                Thread.sleep(this.zzaOB);
            }
            catch (InterruptedException ex) {
                zzbg.zzaD("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }
    
    private void zzzv() {
        if (this.zzpO.currentTimeMillis() - this.zzaOD < this.zzaOC) {
            return;
        }
        this.interrupt();
        this.zzaOD = this.zzpO.currentTimeMillis();
    }
    
    public void interrupt() {
        this.zzHt.interrupt();
    }
    
    public boolean isLimitAdTrackingEnabled() {
        this.zzzv();
        return this.zzLl == null || this.zzLl.isLimitAdTrackingEnabled();
    }
    
    public void start() {
        this.zzHt.start();
    }
    
    public String zzzt() {
        this.zzzv();
        if (this.zzLl == null) {
            return null;
        }
        return this.zzLl.getId();
    }
}
