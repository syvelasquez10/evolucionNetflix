// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

final class fr$2 implements gw$a
{
    final /* synthetic */ String uo;
    
    fr$2(final String uo) {
        this.uo = uo;
    }
    
    @Override
    public void a(final gv gv) {
        final String format = String.format("javascript:%s(%s);", "AFMA_buildAdURL", this.uo);
        gs.V("About to execute: " + format);
        gv.loadUrl(format);
    }
}
