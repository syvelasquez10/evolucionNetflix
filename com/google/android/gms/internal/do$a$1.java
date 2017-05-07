// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

class do$a$1 implements Runnable
{
    private final WeakReference<do> sd;
    final /* synthetic */ do se;
    final /* synthetic */ do$a sf;
    
    do$a$1(final do$a sf, final do se) {
        this.sf = sf;
        this.se = se;
        this.sd = new WeakReference<do>(this.se);
    }
    
    @Override
    public void run() {
        final do do1 = this.sd.get();
        if (!this.sf.sc && do1 != null) {
            do1.cj();
            this.sf.ck();
        }
    }
}
