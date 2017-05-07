// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Handler;
import android.content.Context;

class cx extends cw
{
    private static cx aam;
    private static final Object sF;
    private Context aac;
    private at aad;
    private volatile ar aae;
    private int aaf;
    private boolean aag;
    private boolean aah;
    private boolean aai;
    private au aaj;
    private bn aak;
    private boolean aal;
    private boolean connected;
    private Handler handler;
    
    static {
        sF = new Object();
    }
    
    private cx() {
        this.aaf = 1800000;
        this.aag = true;
        this.aah = false;
        this.connected = true;
        this.aai = true;
        this.aaj = new au() {
            @Override
            public void r(final boolean b) {
                cx.this.a(b, cx.this.connected);
            }
        };
        this.aal = false;
    }
    
    private void cj() {
        (this.aak = new bn(this)).o(this.aac);
    }
    
    private void ck() {
        this.handler = new Handler(this.aac.getMainLooper(), (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                if (1 == message.what && cx.sF.equals(message.obj)) {
                    cx.this.bW();
                    if (cx.this.aaf > 0 && !cx.this.aal) {
                        cx.this.handler.sendMessageDelayed(cx.this.handler.obtainMessage(1, cx.sF), (long)cx.this.aaf);
                    }
                }
                return true;
            }
        });
        if (this.aaf > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, cx.sF), (long)this.aaf);
        }
    }
    
    public static cx lG() {
        if (cx.aam == null) {
            cx.aam = new cx();
        }
        return cx.aam;
    }
    
    void a(final Context context, final ar aae) {
        synchronized (this) {
            if (this.aac == null) {
                this.aac = context.getApplicationContext();
                if (this.aae == null) {
                    this.aae = aae;
                }
            }
        }
    }
    
    void a(final boolean aal, final boolean connected) {
        while (true) {
            while (true) {
                Label_0153: {
                    Label_0146: {
                        synchronized (this) {
                            if (this.aal != aal || this.connected != connected) {
                                if ((aal || !connected) && this.aaf > 0) {
                                    this.handler.removeMessages(1, cx.sF);
                                }
                                if (!aal && connected && this.aaf > 0) {
                                    this.handler.sendMessageDelayed(this.handler.obtainMessage(1, cx.sF), (long)this.aaf);
                                }
                                final StringBuilder append = new StringBuilder().append("PowerSaveMode ");
                                if (!aal && connected) {
                                    break Label_0146;
                                }
                                break Label_0153;
                            }
                            return;
                            final StringBuilder append;
                            final String s;
                            bh.y(append.append(s).toString());
                            this.aal = aal;
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
    
    public void bW() {
        synchronized (this) {
            if (!this.aah) {
                bh.y("Dispatch call queued. Dispatch will run once initialization is complete.");
                this.aag = true;
            }
            else {
                this.aae.a(new Runnable() {
                    @Override
                    public void run() {
                        cx.this.aad.bW();
                    }
                });
            }
        }
    }
    
    @Override
    void cm() {
        synchronized (this) {
            if (!this.aal && this.connected && this.aaf > 0) {
                this.handler.removeMessages(1, cx.sF);
                this.handler.sendMessage(this.handler.obtainMessage(1, cx.sF));
            }
        }
    }
    
    at lH() {
        Label_0050: {
            synchronized (this) {
                if (this.aad != null) {
                    break Label_0050;
                }
                if (this.aac == null) {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            this.aad = new ca(this.aaj, this.aac);
        }
        if (this.handler == null) {
            this.ck();
        }
        this.aah = true;
        if (this.aag) {
            this.bW();
            this.aag = false;
        }
        if (this.aak == null && this.aai) {
            this.cj();
        }
        // monitorexit(this)
        return this.aad;
    }
    
    @Override
    void s(final boolean b) {
        synchronized (this) {
            this.a(this.aal, b);
        }
    }
}
