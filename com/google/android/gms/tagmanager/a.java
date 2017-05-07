// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

class a
{
    private static a anF;
    private static Object xz;
    private volatile long anB;
    private volatile long anC;
    private volatile long anD;
    private a anE;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread wf;
    private volatile AdvertisingIdClient.Info xB;
    private final ju yD;
    
    static {
        a.xz = new Object();
    }
    
    private a(final Context context) {
        this(context, null, jw.hA());
    }
    
    a(final Context mContext, final a anE, final ju yd) {
        this.anB = 900000L;
        this.anC = 30000L;
        this.mClosed = false;
        this.anE = (a)new a() {
            @Override
            public AdvertisingIdClient.Info nK() {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(a.this.mContext);
                }
                catch (IllegalStateException ex) {
                    bh.W("IllegalStateException getting Advertising Id Info");
                    return null;
                }
                catch (GooglePlayServicesRepairableException ex2) {
                    bh.W("GooglePlayServicesRepairableException getting Advertising Id Info");
                    return null;
                }
                catch (IOException ex3) {
                    bh.W("IOException getting Ad Id Info");
                    return null;
                }
                catch (GooglePlayServicesNotAvailableException ex4) {
                    bh.W("GooglePlayServicesNotAvailableException getting Advertising Id Info");
                    return null;
                }
                catch (Exception ex5) {
                    bh.W("Unknown exception. Could not get the Advertising Id Info.");
                    return null;
                }
            }
        };
        this.yD = yd;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        if (anE != null) {
            this.anE = anE;
        }
        this.wf = new Thread(new Runnable() {
            @Override
            public void run() {
                a.this.nI();
            }
        });
    }
    
    static a V(final Context context) {
        Label_0037: {
            if (a.anF != null) {
                break Label_0037;
            }
            synchronized (a.xz) {
                if (a.anF == null) {
                    (a.anF = new a(context)).start();
                }
                return a.anF;
            }
        }
    }
    
    private void nI() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.xB = this.anE.nK();
                Thread.sleep(this.anB);
            }
            catch (InterruptedException ex) {
                bh.U("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }
    
    private void nJ() {
        if (this.yD.currentTimeMillis() - this.anD < this.anC) {
            return;
        }
        this.interrupt();
        this.anD = this.yD.currentTimeMillis();
    }
    
    void interrupt() {
        this.wf.interrupt();
    }
    
    public boolean isLimitAdTrackingEnabled() {
        this.nJ();
        return this.xB == null || this.xB.isLimitAdTrackingEnabled();
    }
    
    public String nH() {
        this.nJ();
        if (this.xB == null) {
            return null;
        }
        return this.xB.getId();
    }
    
    void start() {
        this.wf.start();
    }
    
    public interface a
    {
        AdvertisingIdClient.Info nK();
    }
}
