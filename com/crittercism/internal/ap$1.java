// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Iterator;
import android.content.Context;

final class ap$1 extends di
{
    final /* synthetic */ au a;
    final /* synthetic */ az b;
    final /* synthetic */ Context c;
    final /* synthetic */ ar d;
    final /* synthetic */ ax e;
    final /* synthetic */ ap f;
    
    ap$1(final ap f, final au a, final az b, final Context c, final ar d, final ax e) {
        this.f = f;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    @Override
    public final void a() {
        if (!this.f.d.a()) {
            final bq o = this.a.o();
            final String b = this.b.c.b;
            final bk a = this.f.a;
            if (a != null) {
                o.a(a);
            }
            final df df = new df(this.c);
            df.a(o, new ct$a(), this.b.c.d, "/v0/appload", this.b.c.b, this.d, new cs$b());
            final ax e = this.e;
            for (final di di : df.a) {
                if (!e.o.a(di)) {
                    e.q.execute(di);
                }
            }
        }
    }
}
