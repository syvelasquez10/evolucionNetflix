// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

@ez
public final class fd
{
    public static gg a(final Context context, final u u, final fz.a a, final gv gv, final ct ct, final a a2) {
        gg gg;
        if (a.vw.tS) {
            gg = new fn(context, u, new ai(), a, a2);
        }
        else {
            gg = new fe(context, a, gv, ct, a2);
        }
        gg.start();
        return gg;
    }
    
    public interface a
    {
        void a(final fz p0);
    }
}
