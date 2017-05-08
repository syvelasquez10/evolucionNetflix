// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import com.crittercism.internal.dg;
import com.crittercism.internal.dx;
import com.crittercism.internal.dw;
import com.crittercism.internal.ar;
import java.util.HashMap;
import com.crittercism.internal.dk;
import java.util.Map;
import com.crittercism.internal.ax;

public class CritterUserDataRequest
{
    private final CritterCallback a;
    private ax b;
    private Map c;
    private dk d;
    
    public CritterUserDataRequest(final CritterCallback a) {
        this.a = a;
        this.b = ax.C();
        this.c = new HashMap();
        this.d = new dk(this.b);
    }
    
    public void makeRequest() {
        synchronized (this) {
            final dg o = this.b.o;
            if (o == null) {
                dw.a("Must initialize Crittercism before calling " + this.getClass().getName() + ".makeRequest()", new IllegalStateException());
            }
            else {
                final CritterUserDataRequest$1 critterUserDataRequest$1 = new CritterUserDataRequest$1(this);
                if (!o.a(critterUserDataRequest$1)) {
                    new dx(critterUserDataRequest$1).start();
                }
            }
        }
    }
    
    public CritterUserDataRequest requestDidCrashOnLastLoad() {
        this.d.c = true;
        return this;
    }
    
    public CritterUserDataRequest requestOptOutStatus() {
        this.d.b = true;
        return this;
    }
    
    public CritterUserDataRequest requestRateMyAppInfo() {
        this.d.e = true;
        return this;
    }
    
    public CritterUserDataRequest requestUserUUID() {
        this.d.d = true;
        return this;
    }
}
