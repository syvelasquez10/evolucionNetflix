// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

public final class s
{
    private final Runnable ep;
    private v eq;
    private boolean er;
    
    public s(final r r) {
        this.er = false;
        this.ep = new Runnable() {
            private final WeakReference<r> es = new WeakReference<r>(r);
            
            @Override
            public void run() {
                s.this.er = false;
                final r r = this.es.get();
                if (r != null) {
                    r.b(s.this.eq);
                }
            }
        };
    }
    
    public void a(final v eq, final long n) {
        if (this.er) {
            ct.v("An ad refresh is already scheduled.");
            return;
        }
        ct.t("Scheduling ad refresh " + n + " milliseconds from now.");
        this.eq = eq;
        this.er = true;
        cs.iI.postDelayed(this.ep, n);
    }
    
    public void cancel() {
        cs.iI.removeCallbacks(this.ep);
    }
    
    public void d(final v v) {
        this.a(v, 60000L);
    }
}
