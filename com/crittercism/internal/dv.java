// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class dv
{
    private dt a;
    
    public final dt a() {
        synchronized (this) {
            return this.a;
        }
    }
    
    public final void a(final av av) {
        synchronized (this) {
            if (!ax.C().g.a()) {
                final dt a = new dt(av.b(cq.j.m, cq.j.n));
                ++a.a;
                this.a = a;
            }
        }
    }
}
