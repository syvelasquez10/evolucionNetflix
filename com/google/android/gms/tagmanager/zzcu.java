// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler$Callback;
import android.content.Context;
import android.os.Handler;

class zzcu extends zzct
{
    private static final Object zzaRX;
    private static zzcu zzaSi;
    private boolean connected;
    private Handler handler;
    private Context zzaRY;
    private zzau zzaRZ;
    private volatile zzas zzaSa;
    private int zzaSb;
    private boolean zzaSc;
    private boolean zzaSd;
    private boolean zzaSe;
    private zzav zzaSf;
    private zzbl zzaSg;
    private boolean zzaSh;
    
    static {
        zzaRX = new Object();
    }
    
    private zzcu() {
        this.zzaSb = 1800000;
        this.zzaSc = true;
        this.zzaSd = false;
        this.connected = true;
        this.zzaSe = true;
        this.zzaSf = new zzcu$1(this);
        this.zzaSh = false;
    }
    
    public static zzcu zzAP() {
        if (zzcu.zzaSi == null) {
            zzcu.zzaSi = new zzcu();
        }
        return zzcu.zzaSi;
    }
    
    private void zzAQ() {
        (this.zzaSg = new zzbl(this)).zzaP(this.zzaRY);
    }
    
    private void zzAR() {
        this.handler = new Handler(this.zzaRY.getMainLooper(), (Handler$Callback)new zzcu$2(this));
        if (this.zzaSb > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, zzcu.zzaRX), (long)this.zzaSb);
        }
    }
    
    public void dispatch() {
        synchronized (this) {
            if (!this.zzaSd) {
                zzbg.v("Dispatch call queued. Dispatch will run once initialization is complete.");
                this.zzaSc = true;
            }
            else {
                this.zzaSa.zzg(new zzcu$3(this));
            }
        }
    }
    
    zzau zzAS() {
        Label_0050: {
            synchronized (this) {
                if (this.zzaRZ != null) {
                    break Label_0050;
                }
                if (this.zzaRY == null) {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            this.zzaRZ = new zzby(this.zzaSf, this.zzaRY);
        }
        if (this.handler == null) {
            this.zzAR();
        }
        this.zzaSd = true;
        if (this.zzaSc) {
            this.dispatch();
            this.zzaSc = false;
        }
        if (this.zzaSg == null && this.zzaSe) {
            this.zzAQ();
        }
        // monitorexit(this)
        return this.zzaRZ;
    }
    
    void zza(final Context context, final zzas zzaSa) {
        synchronized (this) {
            if (this.zzaRY == null) {
                this.zzaRY = context.getApplicationContext();
                if (this.zzaSa == null) {
                    this.zzaSa = zzaSa;
                }
            }
        }
    }
    
    @Override
    void zzat(final boolean b) {
        synchronized (this) {
            this.zzd(this.zzaSh, b);
        }
    }
    
    void zzd(final boolean zzaSh, final boolean connected) {
        while (true) {
            while (true) {
                Label_0153: {
                    Label_0146: {
                        synchronized (this) {
                            if (this.zzaSh != zzaSh || this.connected != connected) {
                                if ((zzaSh || !connected) && this.zzaSb > 0) {
                                    this.handler.removeMessages(1, zzcu.zzaRX);
                                }
                                if (!zzaSh && connected && this.zzaSb > 0) {
                                    this.handler.sendMessageDelayed(this.handler.obtainMessage(1, zzcu.zzaRX), (long)this.zzaSb);
                                }
                                final StringBuilder append = new StringBuilder().append("PowerSaveMode ");
                                if (!zzaSh && connected) {
                                    break Label_0146;
                                }
                                break Label_0153;
                            }
                            return;
                            final StringBuilder append;
                            final String s;
                            zzbg.v(append.append(s).toString());
                            this.zzaSh = zzaSh;
                            this.connected = connected;
                            return;
                        }
                    }
                    final String s = "terminated.";
                    continue;
                }
                final String s = "initiated.";
                continue;
            }
        }
    }
    
    @Override
    void zzhY() {
        synchronized (this) {
            if (!this.zzaSh && this.connected && this.zzaSb > 0) {
                this.handler.removeMessages(1, zzcu.zzaRX);
                this.handler.sendMessage(this.handler.obtainMessage(1, zzcu.zzaRX));
            }
        }
    }
}
