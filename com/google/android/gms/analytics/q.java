// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Handler;
import android.content.Context;

class q extends ae
{
    private static final Object yc;
    private static q yo;
    private Context mContext;
    private Handler mHandler;
    private d yd;
    private volatile f ye;
    private int yf;
    private boolean yg;
    private boolean yh;
    private String yi;
    private boolean yj;
    private boolean yk;
    private e yl;
    private p ym;
    private boolean yn;
    
    static {
        yc = new Object();
    }
    
    private q() {
        this.yf = 1800;
        this.yg = true;
        this.yj = true;
        this.yk = true;
        this.yl = new e() {
            @Override
            public void z(final boolean b) {
                q.this.a(b, q.this.yj);
            }
        };
        this.yn = false;
    }
    
    public static q ea() {
        if (q.yo == null) {
            q.yo = new q();
        }
        return q.yo;
    }
    
    private void eb() {
        (this.ym = new p(this)).z(this.mContext);
    }
    
    private void ec() {
        this.mHandler = new Handler(this.mContext.getMainLooper(), (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                if (1 == message.what && q.yc.equals(message.obj)) {
                    t.eq().B(true);
                    q.this.dispatchLocalHits();
                    t.eq().B(false);
                    if (q.this.yf > 0 && !q.this.yn) {
                        q.this.mHandler.sendMessageDelayed(q.this.mHandler.obtainMessage(1, q.yc), (long)(q.this.yf * 1000));
                    }
                }
                return true;
            }
        });
        if (this.yf > 0) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, q.yc), (long)(this.yf * 1000));
        }
    }
    
    @Override
    void A(final boolean b) {
        synchronized (this) {
            this.a(this.yn, b);
        }
    }
    
    void a(final Context context, final f ye) {
        synchronized (this) {
            if (this.mContext == null) {
                this.mContext = context.getApplicationContext();
                if (this.ye == null) {
                    this.ye = ye;
                    if (this.yg) {
                        this.dispatchLocalHits();
                        this.yg = false;
                    }
                    if (this.yh) {
                        this.dO();
                        this.yh = false;
                    }
                }
            }
        }
    }
    
    void a(final boolean yn, final boolean yj) {
        while (true) {
            while (true) {
                Label_0157: {
                    Label_0150: {
                        synchronized (this) {
                            if (this.yn != yn || this.yj != yj) {
                                if ((yn || !yj) && this.yf > 0) {
                                    this.mHandler.removeMessages(1, q.yc);
                                }
                                if (!yn && yj && this.yf > 0) {
                                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, q.yc), (long)(this.yf * 1000));
                                }
                                final StringBuilder append = new StringBuilder().append("PowerSaveMode ");
                                if (!yn && yj) {
                                    break Label_0150;
                                }
                                break Label_0157;
                            }
                            return;
                            final StringBuilder append;
                            final String s;
                            z.V(append.append(s).toString());
                            this.yn = yn;
                            this.yj = yj;
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
    
    void dO() {
        if (this.ye == null) {
            z.V("setForceLocalDispatch() queued. It will be called once initialization is complete.");
            this.yh = true;
            return;
        }
        t.eq().a(t.a.Ak);
        this.ye.dO();
    }
    
    @Override
    void dispatchLocalHits() {
        synchronized (this) {
            if (this.ye == null) {
                z.V("Dispatch call queued. Dispatch will run once initialization is complete.");
                this.yg = true;
            }
            else {
                t.eq().a(t.a.zX);
                this.ye.dispatch();
            }
        }
    }
    
    d ed() {
        Label_0080: {
            synchronized (this) {
                if (this.yd != null) {
                    break Label_0080;
                }
                if (this.mContext == null) {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            this.yd = new ab(this.yl, this.mContext);
            if (this.yi != null) {
                this.yd.dN().af(this.yi);
                this.yi = null;
            }
        }
        if (this.mHandler == null) {
            this.ec();
        }
        if (this.ym == null && this.yk) {
            this.eb();
        }
        // monitorexit(this)
        return this.yd;
    }
    
    @Override
    void ee() {
        synchronized (this) {
            if (!this.yn && this.yj && this.yf > 0) {
                this.mHandler.removeMessages(1, q.yc);
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, q.yc));
            }
        }
    }
    
    @Override
    void setLocalDispatchPeriod(final int n) {
        synchronized (this) {
            if (this.mHandler == null) {
                z.V("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
                this.yf = n;
            }
            else {
                t.eq().a(t.a.zY);
                if (!this.yn && this.yj && this.yf > 0) {
                    this.mHandler.removeMessages(1, q.yc);
                }
                if ((this.yf = n) > 0 && !this.yn && this.yj) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, q.yc), (long)(n * 1000));
                }
            }
        }
    }
}
