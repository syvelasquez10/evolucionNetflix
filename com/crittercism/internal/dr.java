// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONException;
import org.json.JSONObject;

public final class dr
{
    public volatile boolean a;
    public volatile boolean b;
    private volatile boolean c;
    
    public dr() {
        this.a = true;
        this.c = false;
        this.b = false;
    }
    
    private boolean c() {
        final dx dx = new dx(new dr$a(this));
        dx.start();
        try {
            dx.join();
            return true;
        }
        catch (InterruptedException ex) {
            dw.b(ex);
            return false;
        }
    }
    
    public final boolean a() {
        final ax c = ax.C();
        if (!this.c && !c.B()) {
            dw.c("Crittercism has not been initialized with a context and cannot load opt out status from disk.");
            return true;
        }
        Label_0057: {
            if (this.c) {
                break Label_0057;
            }
            synchronized (this) {
                if (!this.c) {
                    if (this.c()) {
                        this.c = true;
                    }
                    else {
                        this.c = false;
                    }
                }
                // monitorexit(this)
                return this.a;
            }
        }
    }
    
    public final boolean b() {
        JSONObject jsonObject = null;
        final ax c = ax.C();
        final String a = c.a(cq.i.m, cq.i.n);
    Label_0042:
        while (true) {
            if (a == null) {
                jsonObject = null;
                break Label_0042;
            }
            while (true) {
                while (true) {
                    Label_0105: {
                        while (true) {
                            try {
                                jsonObject = new JSONObject(a);
                                if (jsonObject == null) {
                                    break Label_0105;
                                }
                                final int optBoolean = jsonObject.optBoolean("optOutStatusSet", false) ? 1 : 0;
                                if (optBoolean != 0) {
                                    final boolean a2 = jsonObject.optBoolean("optOutStatus", false);
                                    return this.a = a2;
                                }
                            }
                            catch (JSONException ex) {
                                dw.d("JSONException in OptOutManager$loadStatusHelper(). Using null optOutStatusJSON.");
                                continue Label_0042;
                            }
                            final boolean a2 = c.c(cq.l.m, cq.l.n);
                            continue;
                        }
                    }
                    final int optBoolean = 0;
                    continue;
                }
            }
            break;
        }
    }
}
