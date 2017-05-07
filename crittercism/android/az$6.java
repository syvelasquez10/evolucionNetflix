// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

final class az$6 extends dj
{
    final /* synthetic */ Throwable a;
    final /* synthetic */ long b;
    final /* synthetic */ az c;
    
    az$6(final az c, final Throwable a, final long b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        if (!this.c.f.b()) {
            final bk bk = new bk(this.a, this.b);
            bk.a("current_session", this.c.k);
            bk.f = "he";
            if (this.c.h.a(bk)) {
                az.a.a(new by(bk.c, bk.d));
                if (this.c.p.a()) {
                    final dg dg = new dg(this.c.c);
                    dg.a(this.c.h, new db$a(), this.c.u.b(), "/android_v2/handle_exceptions", null, az.a, new cv$a());
                    dg.a(this.c.q, this.c.r);
                    this.c.p.b();
                }
            }
        }
    }
}
