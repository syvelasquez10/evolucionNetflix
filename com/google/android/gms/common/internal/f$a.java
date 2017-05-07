// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.IBinder;
import java.util.HashSet;

final class f$a
{
    private final String LN;
    private final f$a$a LO;
    private final HashSet<d$f> LP;
    private boolean LQ;
    private IBinder LR;
    private ComponentName LS;
    final /* synthetic */ f LT;
    private int mState;
    
    public f$a(final f lt, final String ln) {
        this.LT = lt;
        this.LN = ln;
        this.LO = new f$a$a(this);
        this.LP = new HashSet<d$f>();
        this.mState = 0;
    }
    
    public void J(final boolean lq) {
        this.LQ = lq;
    }
    
    public void a(final d$f d$f) {
        this.LP.add(d$f);
    }
    
    public void b(final d$f d$f) {
        this.LP.remove(d$f);
    }
    
    public boolean c(final d$f d$f) {
        return this.LP.contains(d$f);
    }
    
    public f$a$a gW() {
        return this.LO;
    }
    
    public String gX() {
        return this.LN;
    }
    
    public boolean gY() {
        return this.LP.isEmpty();
    }
    
    public IBinder getBinder() {
        return this.LR;
    }
    
    public ComponentName getComponentName() {
        return this.LS;
    }
    
    public int getState() {
        return this.mState;
    }
    
    public boolean isBound() {
        return this.LQ;
    }
}
