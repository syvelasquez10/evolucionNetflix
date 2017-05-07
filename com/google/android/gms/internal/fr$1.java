// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

final class fr$1 implements Runnable
{
    final /* synthetic */ Context mV;
    final /* synthetic */ fi uk;
    final /* synthetic */ ft ul;
    final /* synthetic */ gw$a um;
    final /* synthetic */ String un;
    
    fr$1(final Context mv, final fi uk, final ft ul, final gw$a um, final String un) {
        this.mV = mv;
        this.uk = uk;
        this.ul = ul;
        this.um = um;
        this.un = un;
    }
    
    @Override
    public void run() {
        final gv a = gv.a(this.mV, new ay(), false, false, null, this.uk.lD);
        a.setWillNotDraw(true);
        this.ul.b(a);
        final gw dv = a.dv();
        dv.a("/invalidRequest", this.ul.us);
        dv.a("/loadAdURL", this.ul.ut);
        dv.a("/log", bx.pG);
        dv.a(this.um);
        gs.S("Loading the JS library.");
        a.loadUrl(this.un);
    }
}
