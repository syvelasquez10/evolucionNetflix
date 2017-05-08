// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;

final class ax$8 extends di
{
    final /* synthetic */ Throwable a;
    final /* synthetic */ long b;
    final /* synthetic */ ax c;
    
    ax$8(final ax c, final Throwable a, final long b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        if (!this.c.g.a()) {
            final bi bi = new bi(this.a, this.b);
            bi.a("current_session", this.c.i);
            bi.e = "he";
            if (this.a instanceof co) {
                bi.g = ((co)this.a).a;
            }
            else {
                bi.g = true;
            }
            if (this.c.h.a(bi)) {
                this.c.k.a(new bw(bi.b, bi.c));
                bi.a(this.c.j);
                bi.b(this.c.k);
                this.c.h.b(bi);
                if (this.c.n.a()) {
                    final df df = new df(this.c.c);
                    df.a(this.c.h, new da$a(), this.c.u.c.b, "/android_v2/handle_exceptions", null, ax.a, new cu$a());
                    final dg o = this.c.o;
                    final ExecutorService q = this.c.q;
                    for (final di di : df.a) {
                        if (!o.a(di)) {
                            q.execute(di);
                        }
                    }
                    this.c.n.b();
                }
            }
        }
    }
}
