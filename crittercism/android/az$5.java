// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONArray;

final class az$5 extends dj
{
    final /* synthetic */ Throwable a;
    final /* synthetic */ long b;
    final /* synthetic */ az c;
    
    az$5(final az c, final Throwable a, final long b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        if (this.c.f.b()) {
            return;
        }
        synchronized (this.c.p) {
            if (this.c.B < 10) {
                final bk bk = new bk(this.a, this.b);
                bk.a("current_session", this.c.k);
                bk.a(this.c.l);
                bk.f = "he";
                if (this.c.p.a()) {
                    new dk(new cv(az.a).a(br.b.f(), new JSONArray().put((Object)bk.a())), new dd(new dc(this.c.u.b(), "/android_v2/handle_exceptions").a()), null).run();
                    final az c = this.c;
                    ++c.B;
                    this.c.p.b();
                }
            }
        }
    }
}
