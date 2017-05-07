// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.UUID;
import android.telephony.TelephonyManager;
import android.content.Context;

public final class at
{
    private ao a;
    private an b;
    private ap c;
    private aq d;
    private ar e;
    
    private void a(final an b) {
        synchronized (this) {
            this.b = b;
        }
    }
    
    private void a(final ao a) {
        synchronized (this) {
            this.a = a;
        }
    }
    
    private void a(final ap c) {
        synchronized (this) {
            this.c = c;
        }
    }
    
    private void a(final ar e) {
        synchronized (this) {
            this.e = e;
        }
    }
    
    public final ao a() {
        synchronized (this) {
            return this.a;
        }
    }
    
    public final void a(final aq d) {
        synchronized (this) {
            this.d = d;
        }
    }
    
    public final boolean a(final h h) {
        synchronized (this) {
            this.c.a(h, ae.f.a(), ae.f.b());
            this.a.a(h, ae.g.a(), ae.g.b());
            this.b.a = true;
            if (!this.c.a()) {
                this.b.b(h, ae.e.a(), ae.e.b());
                this.d.a(h, ae.d.a(), ae.d.b());
                this.e.a(h, ae.h.a(), ae.h.b());
            }
            return true;
        }
    }
    
    public final boolean a(final h h, final Context context) {
        while (true) {
            final String s = null;
            while (true) {
                Label_0328: {
                    synchronized (this) {
                        new ap$a();
                        this.a(ap$a.a(h));
                        new ao$a();
                        String s2;
                        if ((s2 = h.a(ae.g.a(), ae.g.b(), null)) == null) {
                            s2 = h.a(ae.i.a(), ae.i.b(), null);
                        }
                        ao ao;
                        if (s2 == null) {
                            s2 = ao$a.a(context);
                            if (s2 != null) {
                                break Label_0328;
                            }
                            s2 = s;
                            if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
                                s2 = ao$a.b(((TelephonyManager)context.getSystemService("phone")).getDeviceId());
                            }
                            String string;
                            if ((string = s2) == null) {
                                string = UUID.randomUUID().toString();
                            }
                            ao = ao$a.a(string);
                        }
                        else {
                            ao = ao$a.a(s2);
                        }
                        this.a(ao);
                        if (!this.c.a()) {
                            this.a(new an(h.b(ae.e.a(), ae.e.b()), (byte)0));
                            final aq a = aq$a.a(h, ae.d.a(), ae.d.b());
                            ++a.c;
                            this.a(a);
                            new ar$a();
                            int n;
                            if ((n = h.a(ae.h.a(), ae.h.b())) == 0) {
                                n = h.a(ae.j.a(), ae.j.b());
                            }
                            final ar ar = new ar(n, (byte)0);
                            ++ar.a;
                            this.a(ar);
                        }
                        return true;
                    }
                }
                continue;
            }
        }
    }
    
    public final an b() {
        synchronized (this) {
            return this.b;
        }
    }
    
    public final ap c() {
        synchronized (this) {
            return this.c;
        }
    }
    
    public final boolean d() {
        synchronized (this) {
            return this.c == null || this.c.a();
        }
    }
    
    public final aq e() {
        synchronized (this) {
            return this.d;
        }
    }
}
