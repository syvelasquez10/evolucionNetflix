// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import java.util.Map;

final class bx$3 implements by
{
    @Override
    public void a(final gv gv, Map<String, String> o) {
        final String s = ((Map<String, String>)o).get("u");
        if (s == null) {
            gs.W("URL missing from click GMSG.");
            return;
        }
        while (true) {
            o = Uri.parse(s);
            while (true) {
                Label_0111: {
                    try {
                        final k dx = gv.dx();
                        if (dx != null && dx.b((Uri)o)) {
                            o = dx.a((Uri)o, gv.getContext());
                            o = ((Uri)o).toString();
                            new gq(gv.getContext(), gv.dy().wD, (String)o).start();
                            return;
                        }
                        break Label_0111;
                    }
                    catch (l l) {
                        gs.W("Unable to append parameter to URL: " + s);
                    }
                }
                continue;
            }
        }
    }
}
