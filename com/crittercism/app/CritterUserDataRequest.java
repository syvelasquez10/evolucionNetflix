// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import crittercism.android.aa;
import crittercism.android.ac;
import crittercism.android.ab;
import crittercism.android.y;
import crittercism.android.z;
import java.util.HashMap;
import crittercism.android.l;
import java.util.ArrayList;
import java.util.Map;
import crittercism.android.ai;
import android.os.ConditionVariable;
import crittercism.android.at;
import java.util.List;

public class CritterUserDataRequest
{
    private List a;
    private final CritterCallback b;
    private at c;
    private ConditionVariable d;
    private ai e;
    private Map f;
    
    public CritterUserDataRequest(final CritterCallback b) {
        this.a = new ArrayList();
        this.b = b;
        this.e = l.i().k;
        this.d = new ConditionVariable(this.e.a());
        this.c = l.i().e;
        this.f = new HashMap();
    }
    
    private void a(final z z) {
        synchronized (this) {
            this.a.add(z);
        }
    }
    
    private void a(final Map map) {
        synchronized (this) {
            this.f.putAll(map);
        }
    }
    
    public void makeRequest() {
        synchronized (this) {
            new Thread(new CritterUserDataRequest$1(this)).start();
        }
    }
    
    public CritterUserDataRequest requestDidCrashOnLastLoad() {
        final y y = new y(this.d, this.c);
        this.e.a(y);
        this.a(y);
        return this;
    }
    
    public CritterUserDataRequest requestOptOutStatus() {
        final ab ab = new ab(this.d, this.c);
        this.e.a(ab);
        this.a(ab);
        return this;
    }
    
    public CritterUserDataRequest requestRateMyAppInfo() {
        final ac ac = new ac(this.d, this.c);
        this.e.a(ac);
        this.a(ac);
        return this;
    }
    
    public CritterUserDataRequest requestUserUUID() {
        final aa aa = new aa(this.d, this.c);
        this.e.a(aa);
        this.a(aa);
        return this;
    }
}
