// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Handler;
import android.content.Context;

class r extends af
{
    private static final Object sF;
    private static r sR;
    private Context mContext;
    private Handler mHandler;
    private d sG;
    private volatile f sH;
    private int sI;
    private boolean sJ;
    private boolean sK;
    private String sL;
    private boolean sM;
    private boolean sN;
    private e sO;
    private q sP;
    private boolean sQ;
    
    static {
        sF = new Object();
    }
    
    private r() {
        this.sI = 1800;
        this.sJ = true;
        this.sM = true;
        this.sN = true;
        this.sO = new e() {
            @Override
            public void r(final boolean b) {
                r.this.a(b, r.this.sM);
            }
        };
        this.sQ = false;
    }
    
    public static r ci() {
        if (r.sR == null) {
            r.sR = new r();
        }
        return r.sR;
    }
    
    private void cj() {
        (this.sP = new q(this)).o(this.mContext);
    }
    
    private void ck() {
        this.mHandler = new Handler(this.mContext.getMainLooper(), (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                if (1 == message.what && r.sF.equals(message.obj)) {
                    u.cy().t(true);
                    r.this.dispatchLocalHits();
                    u.cy().t(false);
                    if (r.this.sI > 0 && !r.this.sQ) {
                        r.this.mHandler.sendMessageDelayed(r.this.mHandler.obtainMessage(1, r.sF), (long)(r.this.sI * 1000));
                    }
                }
                return true;
            }
        });
        if (this.sI > 0) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, r.sF), (long)(this.sI * 1000));
        }
    }
    
    void a(final Context context, final f sh) {
        synchronized (this) {
            if (this.mContext == null) {
                this.mContext = context.getApplicationContext();
                if (this.sH == null) {
                    this.sH = sh;
                    if (this.sJ) {
                        this.dispatchLocalHits();
                        this.sJ = false;
                    }
                    if (this.sK) {
                        this.bY();
                        this.sK = false;
                    }
                }
            }
        }
    }
    
    void a(final boolean sq, final boolean sm) {
        while (true) {
            while (true) {
                Label_0157: {
                    Label_0150: {
                        synchronized (this) {
                            if (this.sQ != sq || this.sM != sm) {
                                if ((sq || !sm) && this.sI > 0) {
                                    this.mHandler.removeMessages(1, r.sF);
                                }
                                if (!sq && sm && this.sI > 0) {
                                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, r.sF), (long)(this.sI * 1000));
                                }
                                final StringBuilder append = new StringBuilder().append("PowerSaveMode ");
                                if (!sq && sm) {
                                    break Label_0150;
                                }
                                break Label_0157;
                            }
                            return;
                            final StringBuilder append;
                            final String s;
                            aa.y(append.append(s).toString());
                            this.sQ = sq;
                            this.sM = sm;
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
    
    void bY() {
        if (this.sH == null) {
            aa.y("setForceLocalDispatch() queued. It will be called once initialization is complete.");
            this.sK = true;
            return;
        }
        u.cy().a(u.a.uN);
        this.sH.bY();
    }
    
    d cl() {
        Label_0080: {
            synchronized (this) {
                if (this.sG != null) {
                    break Label_0080;
                }
                if (this.mContext == null) {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            this.sG = new ac(this.sO, this.mContext);
            if (this.sL != null) {
                this.sG.bX().F(this.sL);
                this.sL = null;
            }
        }
        if (this.mHandler == null) {
            this.ck();
        }
        if (this.sP == null && this.sN) {
            this.cj();
        }
        // monitorexit(this)
        return this.sG;
    }
    
    @Override
    void cm() {
        synchronized (this) {
            if (!this.sQ && this.sM && this.sI > 0) {
                this.mHandler.removeMessages(1, r.sF);
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, r.sF));
            }
        }
    }
    
    @Override
    void dispatchLocalHits() {
        synchronized (this) {
            if (this.sH == null) {
                aa.y("Dispatch call queued. Dispatch will run once initialization is complete.");
                this.sJ = true;
            }
            else {
                u.cy().a(u.a.uA);
                this.sH.bW();
            }
        }
    }
    
    @Override
    void s(final boolean b) {
        synchronized (this) {
            this.a(this.sQ, b);
        }
    }
    
    @Override
    void setLocalDispatchPeriod(final int n) {
        synchronized (this) {
            if (this.mHandler == null) {
                aa.y("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
                this.sI = n;
            }
            else {
                u.cy().a(u.a.uB);
                if (!this.sQ && this.sM && this.sI > 0) {
                    this.mHandler.removeMessages(1, r.sF);
                }
                if ((this.sI = n) > 0 && !this.sQ && this.sM) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, r.sF), (long)(n * 1000));
                }
            }
        }
    }
}
