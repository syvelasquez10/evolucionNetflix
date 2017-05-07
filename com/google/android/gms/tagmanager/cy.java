// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Handler;
import android.content.Context;

class cy extends cx
{
    private static cy arp;
    private static final Object yc;
    private Context arf;
    private at arg;
    private volatile ar arh;
    private int ari;
    private boolean arj;
    private boolean ark;
    private boolean arl;
    private au arm;
    private bo arn;
    private boolean aro;
    private boolean connected;
    private Handler handler;
    
    static {
        yc = new Object();
    }
    
    private cy() {
        this.ari = 1800000;
        this.arj = true;
        this.ark = false;
        this.connected = true;
        this.arl = true;
        this.arm = new au() {
            @Override
            public void z(final boolean b) {
                cy.this.a(b, cy.this.connected);
            }
        };
        this.aro = false;
    }
    
    private void eb() {
        (this.arn = new bo(this)).z(this.arf);
    }
    
    private void ec() {
        this.handler = new Handler(this.arf.getMainLooper(), (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                if (1 == message.what && cy.yc.equals(message.obj)) {
                    cy.this.dispatch();
                    if (cy.this.ari > 0 && !cy.this.aro) {
                        cy.this.handler.sendMessageDelayed(cy.this.handler.obtainMessage(1, cy.yc), (long)cy.this.ari);
                    }
                }
                return true;
            }
        });
        if (this.ari > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, cy.yc), (long)this.ari);
        }
    }
    
    public static cy pu() {
        if (cy.arp == null) {
            cy.arp = new cy();
        }
        return cy.arp;
    }
    
    @Override
    void A(final boolean b) {
        synchronized (this) {
            this.a(this.aro, b);
        }
    }
    
    void a(final Context context, final ar arh) {
        synchronized (this) {
            if (this.arf == null) {
                this.arf = context.getApplicationContext();
                if (this.arh == null) {
                    this.arh = arh;
                }
            }
        }
    }
    
    void a(final boolean aro, final boolean connected) {
        while (true) {
            while (true) {
                Label_0153: {
                    Label_0146: {
                        synchronized (this) {
                            if (this.aro != aro || this.connected != connected) {
                                if ((aro || !connected) && this.ari > 0) {
                                    this.handler.removeMessages(1, cy.yc);
                                }
                                if (!aro && connected && this.ari > 0) {
                                    this.handler.sendMessageDelayed(this.handler.obtainMessage(1, cy.yc), (long)this.ari);
                                }
                                final StringBuilder append = new StringBuilder().append("PowerSaveMode ");
                                if (!aro && connected) {
                                    break Label_0146;
                                }
                                break Label_0153;
                            }
                            return;
                            final StringBuilder append;
                            final String s;
                            bh.V(append.append(s).toString());
                            this.aro = aro;
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
    
    public void dispatch() {
        synchronized (this) {
            if (!this.ark) {
                bh.V("Dispatch call queued. Dispatch will run once initialization is complete.");
                this.arj = true;
            }
            else {
                this.arh.b(new Runnable() {
                    @Override
                    public void run() {
                        cy.this.arg.dispatch();
                    }
                });
            }
        }
    }
    
    @Override
    void ee() {
        synchronized (this) {
            if (!this.aro && this.connected && this.ari > 0) {
                this.handler.removeMessages(1, cy.yc);
                this.handler.sendMessage(this.handler.obtainMessage(1, cy.yc));
            }
        }
    }
    
    at pv() {
        Label_0050: {
            synchronized (this) {
                if (this.arg != null) {
                    break Label_0050;
                }
                if (this.arf == null) {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            this.arg = new cb(this.arm, this.arf);
        }
        if (this.handler == null) {
            this.ec();
        }
        this.ark = true;
        if (this.arj) {
            this.dispatch();
            this.arj = false;
        }
        if (this.arn == null && this.arl) {
            this.eb();
        }
        // monitorexit(this)
        return this.arg;
    }
}
