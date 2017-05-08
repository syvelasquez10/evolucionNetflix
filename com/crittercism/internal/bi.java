// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.List;
import org.json.JSONException;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONObject;
import org.json.JSONArray;

public final class bi implements cf
{
    public long a;
    public String b;
    public String c;
    public JSONArray d;
    public String e;
    public JSONObject f;
    public boolean g;
    private JSONObject h;
    private JSONArray i;
    private JSONArray j;
    private JSONArray k;
    private String l;
    private JSONArray m;
    private String n;
    private int o;
    private boolean p;
    private String q;
    
    public bi(final Throwable t, final long a) {
        int i = 0;
        this.c = "";
        this.o = -1;
        this.p = false;
        this.g = false;
        this.p = (t instanceof cp);
        this.q = ce.a.a();
        this.e = "uhe";
        final bs bs = new bs();
        bs.a(new bv$a()).a(new bv$c()).a(new bv$b()).a(new bv$d()).a(new bv$e()).a(new bv$f()).a(new bv$o()).a(new bv$p()).a(new bv$i()).a(new bv$j()).a(new bv$h()).a(new bv$z()).a(new bv$aa()).a(new bv$k()).a(new bv$l()).a(new bv$n()).a(new bv$m()).a(new bv$q()).a(new bv$r()).a(new bv$s()).a(new bv$t()).a(new bv$u()).a(new bv$v()).a(new bv$w()).a(new bv$x()).a(new bv$y());
        this.f = bs.a();
        this.h = new JSONObject();
        this.a = a;
        this.b = this.a(t);
        if (t.getMessage() != null) {
            this.c = t.getMessage();
        }
        if (!this.p) {
            this.o = c(t);
        }
        this.l = "android";
        this.n = eb.a.a();
        this.m = new JSONArray();
        for (String[] b = b(t); i < b.length; ++i) {
            this.m.put((Object)b[i]);
        }
    }
    
    private String a(Throwable cause) {
        Throwable t = cause;
        if (this.p) {
            return ((cp)cause).b;
        }
        String name = null;
        Label_0019: {
            break Label_0019;
            do {
                t = cause;
                name = t.getClass().getName();
                cause = t.getCause();
            } while (cause != null && cause != t);
        }
        return name;
    }
    
    private static String[] b(Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        while (true) {
            t.printStackTrace(printWriter);
            final Throwable cause = t.getCause();
            if (cause == null || cause == t) {
                break;
            }
            t = cause;
        }
        return stringWriter.toString().split("\n");
    }
    
    private static int c(final Throwable t) {
        final StackTraceElement[] stackTrace = t.getStackTrace();
        for (int i = 0; i < stackTrace.length; ++i) {
            while (true) {
                final StackTraceElement stackTraceElement = stackTrace[i];
                while (true) {
                    Label_0071: {
                        try {
                            final Class<?> forName = Class.forName(stackTraceElement.getClassName());
                            Block_5: {
                                for (ClassLoader classLoader = ClassLoader.getSystemClassLoader(); classLoader != null; classLoader = classLoader.getParent()) {
                                    if (forName.getClassLoader() == classLoader) {
                                        break Block_5;
                                    }
                                }
                                break Label_0071;
                            }
                            final int n = 1;
                            if (n == 0) {
                                return i + 1;
                            }
                        }
                        catch (ClassNotFoundException ex) {}
                        break;
                    }
                    final int n = 0;
                    continue;
                }
            }
        }
        return -1;
    }
    
    public final JSONObject a() {
        final HashMap<String, JSONObject> hashMap = new HashMap<String, JSONObject>();
        hashMap.put("app_state", this.f);
        hashMap.put("breadcrumbs", this.h);
        hashMap.put("current_thread_id", (JSONObject)this.a);
        if (this.i != null) {
            hashMap.put("endpoints", (JSONObject)this.i);
        }
        if (this.j != null) {
            hashMap.put("systemBreadcrumbs", (JSONObject)this.j);
        }
        if (this.k != null && this.k.length() > 0) {
            hashMap.put("transactions", (JSONObject)this.k);
        }
        hashMap.put("exception_name", (JSONObject)this.b);
        hashMap.put("exception_reason", (JSONObject)this.c);
        hashMap.put("platform", (JSONObject)this.l);
        if (this.d != null) {
            hashMap.put("threads", (JSONObject)this.d);
        }
        hashMap.put("ts", (JSONObject)this.n);
        String s2;
        final String s = s2 = this.e;
        if (this.a != 1L) {
            s2 = s + "-bg";
        }
        hashMap.put("type", (JSONObject)s2);
        hashMap.put("unsymbolized_stacktrace", (JSONObject)this.m);
        if (!this.p) {
            hashMap.put("suspect_line_index", (JSONObject)this.o);
        }
        hashMap.put("is_manually_logged", (JSONObject)this.g);
        return new JSONObject((Map)hashMap);
    }
    
    public final void a(final bq bq) {
        this.i = new bm(bq).a;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        outputStream.write(this.a().toString().getBytes());
    }
    
    public final void a(final String s, final bq bq) {
        final JSONArray a = new bm(bq).a;
        try {
            this.h.put(s, (Object)a);
        }
        catch (JSONException ex) {}
    }
    
    public final void a(List iterator) {
        this.k = new JSONArray();
        iterator = ((List)iterator).iterator();
        while (iterator.hasNext()) {
            final be be = iterator.next();
            try {
                this.k.put((Object)be.j());
            }
            catch (JSONException ex) {
                dw.b((Throwable)ex);
            }
        }
    }
    
    public final void b(final bq bq) {
        this.j = new bm(bq).a;
    }
    
    @Override
    public final String e() {
        return this.q;
    }
}
