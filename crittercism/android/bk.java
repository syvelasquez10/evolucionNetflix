// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

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

public final class bk implements ch
{
    public long a;
    public JSONArray b;
    public String c;
    public String d;
    public JSONArray e;
    public String f;
    public JSONObject g;
    private JSONObject h;
    private JSONArray i;
    private JSONArray j;
    private String k;
    private JSONArray l;
    private String m;
    private int n;
    private boolean o;
    private String p;
    
    public bk(final Throwable t, final long a) {
        int i = 0;
        this.d = "";
        this.n = -1;
        this.o = false;
        this.o = (t instanceof cq);
        this.p = cg.a.a();
        this.f = "uhe";
        final bu bu = new bu();
        bu.a(new bx$a()).a(new bx$c()).a(new bx$b()).a(new bx$d()).a(new bx$e()).a(new bx$f()).a(new bx$o()).a(new bx$p()).a(new bx$i()).a(new bx$j()).a(new bx$h()).a(new bx$z()).a(new bx$aa()).a(new bx$k()).a(new bx$l()).a(new bx$n()).a(new bx$m()).a(new bx$q()).a(new bx$r()).a(new bx$s()).a(new bx$t()).a(new bx$u()).a(new bx$v()).a(new bx$w()).a(new bx$x()).a(new bx$y());
        this.g = bu.a();
        this.h = new JSONObject();
        this.a = a;
        this.c = this.a(t);
        if (t.getMessage() != null) {
            this.d = t.getMessage();
        }
        if (!this.o) {
            this.n = c(t);
        }
        this.k = "android";
        this.m = ee.a.a();
        this.l = new JSONArray();
        for (String[] b = b(t); i < b.length; ++i) {
            this.l.put((Object)b[i]);
        }
    }
    
    private String a(Throwable cause) {
        Throwable t = cause;
        if (this.o) {
            return ((cq)cause).a();
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
        hashMap.put("app_state", this.g);
        hashMap.put("breadcrumbs", this.h);
        hashMap.put("current_thread_id", (JSONObject)this.a);
        if (this.i != null) {
            hashMap.put("endpoints", (JSONObject)this.i);
        }
        if (this.b != null) {
            hashMap.put("systemBreadcrumbs", (JSONObject)this.b);
        }
        if (this.j != null && this.j.length() > 0) {
            hashMap.put("transactions", (JSONObject)this.j);
        }
        hashMap.put("exception_name", (JSONObject)this.c);
        hashMap.put("exception_reason", (JSONObject)this.d);
        hashMap.put("platform", (JSONObject)this.k);
        if (this.e != null) {
            hashMap.put("threads", (JSONObject)this.e);
        }
        hashMap.put("ts", (JSONObject)this.m);
        String s2;
        final String s = s2 = this.f;
        if (this.a != 1L) {
            s2 = s + "-bg";
        }
        hashMap.put("type", (JSONObject)s2);
        hashMap.put("unsymbolized_stacktrace", (JSONObject)this.l);
        if (!this.o) {
            hashMap.put("suspect_line_index", (JSONObject)this.n);
        }
        return new JSONObject((Map)hashMap);
    }
    
    public final void a(final bs bs) {
        this.i = new bo(bs).a;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        outputStream.write(this.a().toString().getBytes());
    }
    
    public final void a(final String s, final bs bs) {
        final JSONArray a = new bo(bs).a;
        try {
            this.h.put(s, (Object)a);
        }
        catch (JSONException ex) {}
    }
    
    public final void a(List iterator) {
        this.j = new JSONArray();
        iterator = ((List)iterator).iterator();
        while (iterator.hasNext()) {
            final bg bg = iterator.next();
            try {
                this.j.put((Object)bg.j());
            }
            catch (JSONException ex) {
                dy.a((Throwable)ex);
            }
        }
    }
    
    @Override
    public final String e() {
        return this.p;
    }
}
