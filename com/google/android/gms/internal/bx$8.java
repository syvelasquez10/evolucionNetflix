// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

final class bx$8 implements by
{
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final String s = map.get("tx");
        final String s2 = map.get("ty");
        final String s3 = map.get("td");
        try {
            final int int1 = Integer.parseInt(s);
            final int int2 = Integer.parseInt(s2);
            final int int3 = Integer.parseInt(s3);
            final k dx = gv.dx();
            if (dx != null) {
                dx.z().a(int1, int2, int3);
            }
        }
        catch (NumberFormatException ex) {
            gs.W("Could not parse touch parameters from gmsg.");
        }
    }
}
