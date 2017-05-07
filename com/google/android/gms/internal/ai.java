// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import android.content.Context;

@ez
public class ai
{
    protected ah a(final Context context, final gt gt, final gk<ah> gk) {
        final aj aj = new aj(context, gt);
        aj.a((ah.a)new ah.a() {
            @Override
            public void aM() {
                gk.a(aj);
            }
        });
        return aj;
    }
    
    public Future<ah> a(final Context context, final gt gt, final String s) {
        final gk<ah> gk = new gk<ah>();
        gr.wC.post((Runnable)new Runnable() {
            @Override
            public void run() {
                ai.this.a(context, gt, gk).f(s);
            }
        });
        return gk;
    }
}
