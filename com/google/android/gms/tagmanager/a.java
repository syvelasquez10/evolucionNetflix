// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.gn;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;
import com.google.android.gms.internal.gl;

class a
{
    private static a Wx;
    private static Object sf;
    private volatile long Ws;
    private volatile long Wt;
    private volatile long Wu;
    private final gl Wv;
    private a Ww;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread qY;
    private volatile AdvertisingIdClient.Info sh;
    
    static {
        a.sf = new Object();
    }
    
    private a(final Context context) {
        this(context, null, gn.ft());
    }
    
    a(final Context mContext, final a ww, final gl wv) {
        this.Ws = 900000L;
        this.Wt = 30000L;
        this.mClosed = false;
        this.Ww = (a)new a() {
            @Override
            public AdvertisingIdClient.Info jW() {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(a.this.mContext);
                }
                catch (IllegalStateException ex) {
                    bh.z("IllegalStateException getting Advertising Id Info");
                    return null;
                }
                catch (GooglePlayServicesRepairableException ex2) {
                    bh.z("GooglePlayServicesRepairableException getting Advertising Id Info");
                    return null;
                }
                catch (IOException ex3) {
                    bh.z("IOException getting Ad Id Info");
                    return null;
                }
                catch (GooglePlayServicesNotAvailableException ex4) {
                    bh.z("GooglePlayServicesNotAvailableException getting Advertising Id Info");
                    return null;
                }
                catch (Exception ex5) {
                    bh.z("Unknown exception. Could not get the Advertising Id Info.");
                    return null;
                }
            }
        };
        this.Wv = wv;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        if (ww != null) {
            this.Ww = ww;
        }
        this.qY = new Thread(new Runnable() {
            @Override
            public void run() {
                a.this.jU();
            }
        });
    }
    
    static a E(final Context context) {
        Label_0037: {
            if (a.Wx != null) {
                break Label_0037;
            }
            synchronized (a.sf) {
                if (a.Wx == null) {
                    (a.Wx = new a(context)).start();
                }
                return a.Wx;
            }
        }
    }
    
    private void jU() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.sh = this.Ww.jW();
                Thread.sleep(this.Ws);
            }
            catch (InterruptedException ex) {
                bh.x("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }
    
    private void jV() {
        if (this.Wv.currentTimeMillis() - this.Wu < this.Wt) {
            return;
        }
        this.interrupt();
        this.Wu = this.Wv.currentTimeMillis();
    }
    
    void interrupt() {
        this.qY.interrupt();
    }
    
    public boolean isLimitAdTrackingEnabled() {
        this.jV();
        return this.sh == null || this.sh.isLimitAdTrackingEnabled();
    }
    
    public String jT() {
        this.jV();
        if (this.sh == null) {
            return null;
        }
        return this.sh.getId();
    }
    
    void start() {
        this.qY.start();
    }
    
    public interface a
    {
        AdvertisingIdClient.Info jW();
    }
}
