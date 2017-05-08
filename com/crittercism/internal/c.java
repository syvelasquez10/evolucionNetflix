// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Arrays;
import java.util.Date;
import org.json.JSONArray;
import java.io.OutputStream;
import android.location.Location;
import java.util.Locale;
import java.util.HashSet;
import java.util.Set;

public final class c extends bn
{
    private static final Set s;
    public long a;
    boolean b;
    c$a c;
    public long d;
    public int e;
    public String f;
    public cl g;
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
    
    static {
        (s = new HashSet()).add("GET");
        c.s.add("POST");
        c.s.add("HEAD");
        c.s.add("PUT");
        c.s.add("DELETE");
        c.s.add("TRACE");
        c.s.add("OPTIONS");
        c.s.add("CONNECT");
        c.s.add("PATCH");
    }
    
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
        this.g = new cl(null);
        this.h = new k();
        this.j = com.crittercism.internal.b.a;
        this.n = ce.a.a();
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
        this.g = new cl(null);
        this.h = new k();
        this.j = com.crittercism.internal.b.a;
        this.n = ce.a.a();
        if (i != null) {
            this.i = i;
        }
    }
    
    public static boolean c(String upperCase) {
        if (upperCase != null) {
            upperCase = upperCase.toUpperCase(Locale.US);
            if (c.s.contains(upperCase)) {
                return true;
            }
        }
        return false;
    }
    
    private long f() {
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
    
    public final void a(final int n) {
        this.g = new cl(cm.e - 1, n);
    }
    
    public final void a(final long o) {
        this.p = true;
        this.o = o;
    }
    
    public final void a(final Location location) {
        this.r = new double[] { location.getLatitude(), location.getLongitude() };
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
        this.g = new cl(t);
    }
    
    public final void b() {
        if (!this.l && this.a == Long.MAX_VALUE) {
            this.a = System.currentTimeMillis();
        }
    }
    
    public final void b(final long d) {
        this.q = true;
        this.d = d;
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
    
    public final void c(final long a) {
        this.a = a;
        this.l = true;
    }
    
    public final JSONArray d() {
        final JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put((Object)this.f);
            jsonArray.put((Object)this.a());
            jsonArray.put((Object)eb.a.a(new Date(this.a)));
            jsonArray.put(this.f());
            jsonArray.put(this.j.e);
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
    
    public final void d(final long k) {
        this.k = k;
        this.m = true;
    }
    
    @Override
    public final String e() {
        return this.n;
    }
    
    @Override
    public final String toString() {
        String s = "" + "URI            : " + this.i + "\n" + "URI Builder    : " + this.h.toString() + "\n" + "\n" + "Logged by      : " + this.c.toString() + "\n" + "Error type:         : " + this.g.a + "\n" + "Error code:         : " + this.g.b + "\n" + "\n" + "Response time  : " + this.f() + "\n" + "Start time     : " + this.a + "\n" + "End time       : " + this.k + "\n" + "\n" + "Bytes out    : " + this.d + "\n" + "Bytes in     : " + this.o + "\n" + "\n" + "Response code  : " + this.e + "\n" + "Request method : " + this.f + "\n";
        if (this.r != null) {
            s = s + "Location       : " + Arrays.toString(this.r) + "\n";
        }
        return s;
    }
}
