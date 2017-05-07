// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

final class bx$6 implements by
{
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final String s = map.get("u");
        if (s == null) {
            gs.W("URL missing from httpTrack GMSG.");
            return;
        }
        new gq(gv.getContext(), gv.dy().wD, s).start();
    }
}
