// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class dx
{
    private dt a;
    private dv b;
    
    public final dv a() {
        synchronized (this) {
            return this.b;
        }
    }
    
    public final void a(final ax ax) {
        synchronized (this) {
            this.a = dt$a.a(ax);
            if (!this.a.a()) {
                int n;
                if ((n = ax.b(cr.j.a(), cr.j.b())) == 0) {
                    n = ax.b(cr.k.a(), cr.k.b());
                }
                final dv b = new dv(n);
                ++b.a;
                this.b = b;
            }
        }
    }
    
    public final boolean b() {
        // monitorenter(this)
        boolean a = true;
        try {
            if (this.a != null) {
                a = this.a.a();
            }
            return a;
        }
        finally {
        }
        // monitorexit(this)
    }
}
