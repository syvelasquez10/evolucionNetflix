// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import android.content.Context;
import java.util.concurrent.LinkedBlockingQueue;

class as$1 implements Runnable
{
    final /* synthetic */ ar apg;
    final /* synthetic */ long aph;
    final /* synthetic */ as api;
    final /* synthetic */ String wz;
    
    as$1(final as api, final ar apg, final long aph, final String wz) {
        this.api = api;
        this.apg = apg;
        this.aph = aph;
        this.wz = wz;
    }
    
    @Override
    public void run() {
        if (this.api.apf == null) {
            final cy pu = cy.pu();
            pu.a(this.api.mContext, this.apg);
            this.api.apf = pu.pv();
        }
        this.api.apf.f(this.aph, this.wz);
    }
}
