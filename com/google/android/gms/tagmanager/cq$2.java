// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ok$a;

class cq$2 implements Runnable
{
    final /* synthetic */ cq aqq;
    final /* synthetic */ ok$a aqr;
    
    cq$2(final cq aqq, final ok$a aqr) {
        this.aqq = aqq;
        this.aqr = aqr;
    }
    
    @Override
    public void run() {
        this.aqq.c(this.aqr);
    }
}
