// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

@ez
public final class fd
{
    public static gg a(final Context context, final u u, final fz$a fz$a, final gv gv, final ct ct, final fd$a fd$a) {
        gg gg;
        if (fz$a.vw.tS) {
            gg = new fn(context, u, new ai(), fz$a, fd$a);
        }
        else {
            gg = new fe(context, fz$a, gv, ct, fd$a);
        }
        gg.start();
        return gg;
    }
}
