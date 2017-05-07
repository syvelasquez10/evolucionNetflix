// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

final class bx$4 implements by
{
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final dk du = gv.du();
        if (du == null) {
            gs.W("A GMSG tried to close something that wasn't an overlay.");
            return;
        }
        du.close();
    }
}
