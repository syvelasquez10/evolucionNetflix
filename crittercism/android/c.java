// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Arrays;
import java.util.Date;
import org.json.JSONArray;
import java.net.InetAddress;
import java.io.OutputStream;
import android.location.Location;
import java.net.URL;

public final class c extends bp
{
    public long a;
    public boolean b;
    c$a c;
    public long d;
    public int e;
    public String f;
    public cn g;
    public k h;
    public String i;
    public b j;
    private long k;
    private boolean l;
    private boolean m;
    private String n;
    private long o;
    private boolean p;
    private boolean q;
    private double[] r;
    
    public c() {
        this.a = Long.MAX_VALUE;
        this.k = Long.MAX_VALUE;
        this.l = false;
        this.m = false;
        this.b = false;
        this.c = c$a.a;
        this.o = 0L;
        this.d = 0L;
        this.p = false;
        this.q = false;
        this.e = 0;
        this.f = "";
        this.g = new cn(null);
        this.h = new k();
        this.j = crittercism.android.b.a;
        this.n = cg.a.a();
    }
    
    public c(final String i) {
        this.a = Long.MAX_VALUE;
        this.k = Long.MAX_VALUE;
        this.l = false;
        this.m = false;
        this.b = false;
        this.c = c$a.a;
        this.o = 0L;
        this.d = 0L;
        this.p = false;
        this.q = false;
        this.e = 0;
        this.f = "";
        this.g = new cn(null);
        this.h = new k();
        this.j = crittercism.android.b.a;
        this.n = cg.a.a();
        if (i != null) {
            this.i = i;
        }
    }
    
    public c(final URL url) {
        this.a = Long.MAX_VALUE;
        this.k = Long.MAX_VALUE;
        this.l = false;
        this.m = false;
        this.b = false;
        this.c = c$a.a;
        this.o = 0L;
        this.d = 0L;
        this.p = false;
        this.q = false;
        this.e = 0;
        this.f = "";
        this.g = new cn(null);
        this.h = new k();
        this.j = crittercism.android.b.a;
        this.n = cg.a.a();
        if (url != null) {
            this.i = url.toExternalForm();
        }
    }
    
    private long g() {
        long n = Long.MAX_VALUE;
        if (this.a != Long.MAX_VALUE) {
            n = n;
            if (this.k != Long.MAX_VALUE) {
                n = this.k - this.a;
            }
        }
        return n;
    }
    
    public final String a() {
        final boolean b = true;
        String i;
        if ((i = this.i) == null) {
            final k h = this.h;
            String s;
            if (h.b != null) {
                s = h.b;
            }
            else if (h.a != null) {
                s = h.a.getHostName();
            }
            else {
                s = "unknown-host";
            }
            Label_0110: {
                if (!h.f) {
                    final String c = h.c;
                    while (true) {
                        Label_0193: {
                            if (c == null) {
                                break Label_0193;
                            }
                            int n = b ? 1 : 0;
                            if (!c.regionMatches(true, 0, "http:", 0, 5)) {
                                if (!c.regionMatches(true, 0, "https:", 0, 6)) {
                                    break Label_0193;
                                }
                                n = (b ? 1 : 0);
                            }
                            if (n != 0) {
                                i = c;
                                break Label_0110;
                            }
                            String string;
                            if (h.d != null) {
                                string = "" + h.d.c + ":";
                            }
                            else {
                                string = "";
                            }
                            if (c.startsWith("//")) {
                                i = string + c;
                                break Label_0110;
                            }
                            final String string2 = string + "//";
                            if (c.startsWith(s)) {
                                i = string2 + c;
                                break Label_0110;
                            }
                            String s3;
                            final String s2 = s3 = "";
                            Label_0406: {
                                if (h.e > 0) {
                                    if (h.d != null) {
                                        s3 = s2;
                                        if (h.d.d == h.e) {
                                            break Label_0406;
                                        }
                                    }
                                    final String string3 = ":" + h.e;
                                    s3 = s2;
                                    if (!s.endsWith(string3)) {
                                        s3 = string3;
                                    }
                                }
                            }
                            i = string2 + s + s3 + c;
                            break Label_0110;
                        }
                        int n = 0;
                        continue;
                    }
                }
                final int e = h.e;
                i = s;
                if (e > 0) {
                    final String string4 = ":" + e;
                    i = s;
                    if (!s.endsWith(string4)) {
                        i = s + string4;
                    }
                }
            }
            this.i = i;
        }
        return i;
    }
    
    public final void a(final int e) {
        final k h = this.h;
        if (e > 0) {
            h.e = e;
        }
    }
    
    public final void a(final long n) {
        if (!this.p) {
            this.o += n;
        }
    }
    
    public final void a(final Location location) {
        this.r = new double[] { location.getLatitude(), location.getLongitude() };
    }
    
    public final void a(final k$a d) {
        this.h.d = d;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        outputStream.write(this.d().toString().getBytes());
    }
    
    public final void a(final String i) {
        if (i == null) {
            throw new NullPointerException();
        }
        this.i = i;
    }
    
    public final void a(final Throwable t) {
        this.g = new cn(t);
    }
    
    public final void a(final InetAddress a) {
        this.i = null;
        this.h.a = a;
    }
    
    public final void b() {
        if (!this.l && this.a == Long.MAX_VALUE) {
            this.a = System.currentTimeMillis();
        }
    }
    
    public final void b(final long o) {
        this.p = true;
        this.o = o;
    }
    
    public final void b(final String b) {
        this.i = null;
        this.h.b = b;
    }
    
    public final void c() {
        if (!this.m && this.k == Long.MAX_VALUE) {
            this.k = System.currentTimeMillis();
        }
    }
    
    public final void c(final long n) {
        if (!this.q) {
            this.d += n;
        }
    }
    
    public final JSONArray d() {
        final JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put((Object)this.f);
            jsonArray.put((Object)this.a());
            jsonArray.put((Object)ee.a.a(new Date(this.a)));
            jsonArray.put(this.g());
            jsonArray.put(this.j.a());
            jsonArray.put(this.o);
            jsonArray.put(this.d);
            jsonArray.put(this.e);
            jsonArray.put(this.g.a);
            jsonArray.put(this.g.b);
            if (this.r != null) {
                final JSONArray jsonArray2 = new JSONArray();
                jsonArray2.put(this.r[0]);
                jsonArray2.put(this.r[1]);
                jsonArray.put((Object)jsonArray2);
            }
            return jsonArray;
        }
        catch (Exception ex) {
            System.out.println("Failed to create statsArray");
            ex.printStackTrace();
            return null;
        }
    }
    
    public final void d(final long d) {
        this.q = true;
        this.d = d;
    }
    
    @Override
    public final String e() {
        return this.n;
    }
    
    public final void e(final long a) {
        this.a = a;
        this.l = true;
    }
    
    public final void f() {
        this.h.f = true;
    }
    
    public final void f(final long k) {
        this.k = k;
        this.m = true;
    }
    
    @Override
    public final String toString() {
        String s = "" + "URI            : " + this.i + "\n" + "URI Builder    : " + this.h.toString() + "\n" + "\n" + "Logged by      : " + this.c.toString() + "\n" + "Error type:         : " + this.g.a + "\n" + "Error code:         : " + this.g.b + "\n" + "\n" + "Response time  : " + this.g() + "\n" + "Start time     : " + this.a + "\n" + "End time       : " + this.k + "\n" + "\n" + "Bytes out    : " + this.d + "\n" + "Bytes in     : " + this.o + "\n" + "\n" + "Response code  : " + this.e + "\n" + "Request method : " + this.f + "\n";
        if (this.r != null) {
            s = s + "Location       : " + Arrays.toString(this.r) + "\n";
        }
        return s;
    }
}
