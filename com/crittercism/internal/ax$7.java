// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONArray;

final class ax$7 extends di
{
    final /* synthetic */ Throwable a;
    final /* synthetic */ long b;
    final /* synthetic */ ax c;
    
    ax$7(final ax c, final Throwable a, final long b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        if (this.c.g.a()) {
            return;
        }
        synchronized (this.c.n) {
            if (this.c.B < 10) {
                final bi bi = new bi(this.a, this.b);
                bi.a("current_session", this.c.i);
                bi.a(this.c.j);
                bi.e = "he";
                bi.g = true;
                if (this.c.n.a()) {
                    new dj(new cu(ax.a).a(bp.b.q, new JSONArray().put((Object)bi.a())), new dc(new db(this.c.u.c.b, "/android_v2/handle_exceptions").a()), null).run();
                    final ax c = this.c;
                    ++c.B;
                    this.c.n.b();
                }
            }
        }
    }
}
