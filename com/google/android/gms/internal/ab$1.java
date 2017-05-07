// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

class ab$1 implements Runnable
{
    private final WeakReference<u> mp;
    final /* synthetic */ u mq;
    final /* synthetic */ ab mr;
    
    ab$1(final ab mr, final u mq) {
        this.mr = mr;
        this.mq = mq;
        this.mp = new WeakReference<u>(this.mq);
    }
    
    @Override
    public void run() {
        this.mr.mm = false;
        final u u = this.mp.get();
        if (u != null) {
            u.b(this.mr.ml);
        }
    }
}
