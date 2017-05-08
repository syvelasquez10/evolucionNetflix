// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build$VERSION;
import android.os.Build;
import com.crittercism.app.Transaction;
import java.util.List;
import org.json.JSONObject;
import java.util.Collection;
import java.util.Arrays;
import org.json.JSONArray;
import android.content.SharedPreferences$Editor;
import java.util.concurrent.TimeUnit;
import android.content.SharedPreferences;
import java.util.Iterator;
import android.app.ActivityManager$RunningServiceInfo;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import java.util.concurrent.Executor;
import java.util.HashSet;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.Map;
import com.crittercism.app.CrittercismConfig;
import java.util.concurrent.ExecutorService;
import android.os.ConditionVariable;
import android.content.Context;
import java.util.Set;

public final class ax implements ar, au, av, f
{
    static ax a;
    public ds A;
    int B;
    public ap C;
    private dv D;
    private bq E;
    private bq F;
    private bq G;
    private bq H;
    private bq I;
    private aq J;
    private String K;
    private Set L;
    public boolean b;
    public Context c;
    public at d;
    public String e;
    public final ConditionVariable f;
    public dr g;
    bq h;
    bq i;
    bq j;
    bq k;
    bq l;
    bq m;
    cv n;
    public dg o;
    g p;
    public ExecutorService q;
    ExecutorService r;
    public boolean s;
    public CrittercismConfig t;
    public az u;
    protected e v;
    public dq w;
    du x;
    public bg y;
    Map z;
    
    protected ax() {
        this.b = false;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = new ConditionVariable(false);
        this.g = new dr();
        this.D = new dv();
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = Executors.newCachedThreadPool(new dy());
        this.r = Executors.newSingleThreadExecutor(new dy());
        this.s = false;
        this.K = "";
        this.x = null;
        this.z = new HashMap();
        this.A = null;
        this.B = 0;
        this.L = new HashSet();
        this.v = new e(this.r);
    }
    
    public static ax C() {
        if (ax.a == null) {
            ax.a = new ax();
        }
        return ax.a;
    }
    
    private static boolean H() {
        final boolean b = false;
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= stackTrace.length) {
                break;
            }
            final StackTraceElement stackTraceElement = stackTrace[n];
            if (stackTraceElement.getMethodName().equals("onCreate") || stackTraceElement.getMethodName().equals("onResume")) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    private void I() {
        final int myUid = Process.myUid();
        final int myPid = Process.myPid();
        final ActivityManager activityManager = (ActivityManager)this.c.getSystemService("activity");
        final Iterator iterator = activityManager.getRunningAppProcesses().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().uid == myUid) {
                ++n;
            }
        }
        if (n <= 1) {
            this.s = false;
        }
        else {
            final Iterator iterator2 = activityManager.getRunningServices(Integer.MAX_VALUE).iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().pid == myPid) {
                    this.s = true;
                }
            }
        }
    }
    
    @Override
    public final void A() {
        if (this.s) {
            this.i = new bq(this.c, bp.f).a(this.c);
        }
        else {
            this.i = new bq(this.c, bp.f);
        }
        this.I = new bq(this.c, bp.h);
        this.j = new bq(this.c, bp.g);
        this.k = new bq(this.c, bp.k);
        this.E = new bq(this.c, bp.a);
        this.h = new bq(this.c, bp.b);
        this.F = new bq(this.c, bp.c);
        this.G = new bq(this.c, bp.d);
        this.H = new bq(this.c, bp.e);
        this.l = new bq(this.c, bp.i);
        this.m = new bq(this.c, bp.j);
        if (!this.s) {
            this.x = new du(this.c, this.e);
        }
    }
    
    @Override
    public final boolean B() {
        return this.c != null;
    }
    
    public final void D() {
        dw.c("Initializing Crittercism 5.6.4 for App ID " + this.e);
        this.u = new az(new bl(this.e), this.t);
        this.J = new aq(this.c, this.u);
        this.K = this.c.getPackageName();
        this.w = new dq(this.c);
        this.I();
        long n = 60000000000L;
        if (this.s) {
            n = 12000000000L;
        }
        this.n = new cv(n);
        if (!H()) {
            dw.b("Crittercism should be initialized in onCreate() of MainActivity");
        }
        bv.a(this.J);
        bv.a(this.c);
        bv.a(new ca());
        bv.a(new bd(this.c, this.u));
        final Thread thread = new Thread(new ax$a((byte)0));
        thread.start();
        while (true) {
            try {
                thread.join();
                this.C = new ap(this.u, this.g, this.c, this);
                this.o = new dg(this.u, this.c, this, this, this, this.C);
                if (!this.s) {
                    dw.a(new ea(this, this.r, this.o, this.g));
                }
                final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                if (!(defaultUncaughtExceptionHandler instanceof aw)) {
                    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new aw(this, defaultUncaughtExceptionHandler));
                }
                as.a(this.d, new as(this.d, this, this.C));
                new dx(this.o).start();
                this.b = true;
            }
            catch (InterruptedException ex) {
                dw.b(ex);
                continue;
            }
            break;
        }
    }
    
    public final void G() {
        if (!this.s) {
            final ax$4 ax$4 = new ax$4(this, this);
            if (!this.o.a(ax$4)) {
                this.q.execute(ax$4);
            }
        }
    }
    
    @Override
    public final String a() {
        String e;
        if ((e = this.e) == null) {
            e = "";
        }
        return e;
    }
    
    @Override
    public final String a(String string, final String s) {
        final String s2 = null;
        final SharedPreferences sharedPreferences = this.c.getSharedPreferences(string, 0);
        string = s2;
        if (sharedPreferences != null) {
            string = sharedPreferences.getString(s, (String)null);
        }
        return string;
    }
    
    public final void a(final bf bf) {
        if (this.y != null) {
            be.a(bf);
            be.i();
            if (bf.a) {
                this.y.a = TimeUnit.SECONDS.toMillis(bf.b);
                this.y.b.open();
            }
        }
    }
    
    @Override
    public final void a(final c c) {
        final ax$10 ax$10 = new ax$10(this, c);
        if (!this.o.a(ax$10)) {
            this.r.execute(ax$10);
        }
    }
    
    public final void a(final cg cg) {
        if (!this.g.a()) {
            final ax$11 ax$11 = new ax$11(this, cg);
            if (!this.o.a(ax$11)) {
                this.r.execute(ax$11);
            }
        }
    }
    
    public final void a(final h h) {
        if (this.p != null && h.a && !h.c) {
            dw.c("Enabling Service Monitoring");
            this.p.b = TimeUnit.SECONDS.toMillis(h.d);
            this.p.a.open();
        }
    }
    
    public final void a(final Runnable runnable) {
        if (!this.o.a(runnable)) {
            this.r.execute(runnable);
        }
    }
    
    public final void a(final String s) {
        if (!this.g.a()) {
            final ax$9 ax$9 = new ax$9(this, new cd(s, cd$a.a));
            if (!this.o.a(ax$9)) {
                dw.d("SENDING " + s + " TO EXECUTOR");
                this.r.execute(ax$9);
            }
        }
    }
    
    @Override
    public final void a(final String s, final String s2, final int n) {
        final SharedPreferences sharedPreferences = this.c.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.remove(s2);
                edit.putInt(s2, n);
                edit.commit();
            }
        }
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3) {
        final SharedPreferences sharedPreferences = this.c.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.remove(s2);
                edit.putString(s2, s3);
                edit.commit();
            }
        }
    }
    
    final void a(final Throwable t) {
        final List a = be.a(this, t instanceof cp);
        final bi bi = new bi(t, Thread.currentThread().getId());
        bi.a("crashed_session", this.i);
        if (this.I.b() > 0) {
            bi.a("previous_session", this.I);
        }
        bi.a(this.j);
        bi.b(this.k);
        bi.d = new JSONArray();
        for (final Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            final HashMap<String, JSONArray> hashMap = new HashMap<String, JSONArray>();
            final Thread thread = entry.getKey();
            if (thread.getId() != bi.a) {
                hashMap.put("name", (JSONArray)thread.getName());
                hashMap.put("id", (JSONArray)thread.getId());
                hashMap.put("state", (JSONArray)thread.getState().name());
                hashMap.put("stacktrace", new JSONArray((Collection)Arrays.asList((StackTraceElement[])entry.getValue())));
                bi.d.put((Object)new JSONObject((Map)hashMap));
            }
        }
        bi.a(a);
        this.H.a(bi);
        final df df = new df(this.c);
        df.a(this.E, new da$a(), this.u.c.d, "/v0/appload", null, this, new cs$b());
        df.a(this.h, new da$a(), this.u.c.b, "/android_v2/handle_exceptions", null, this, new cu$a());
        df.a(this.G, new da$a(), this.u.c.b, "/android_v2/handle_ndk_crashes", null, this, new cu$a());
        df.a(this.H, new da$a(), this.u.c.b, "/android_v2/handle_crashes", null, this, new cu$a());
        try {
            df.a();
        }
        catch (InterruptedException ex) {
            dw.d("InterruptedException in logCrashException: " + ex.getMessage());
            dw.a(ex);
        }
        catch (Throwable t) {
            dw.d("Unexpected throwable in logCrashException: " + t.getMessage());
            dw.a(t);
        }
    }
    
    public final void a(final JSONObject jsonObject) {
        if (!this.s) {
            final ax$3 ax$3 = new ax$3(this, this, jsonObject);
            if (!this.o.a(ax$3)) {
                this.r.execute(ax$3);
            }
        }
    }
    
    @Override
    public final int b(final String s, final String s2) {
        int int1 = 0;
        final SharedPreferences sharedPreferences = this.c.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            int1 = sharedPreferences.getInt(s2, 0);
        }
        return int1;
    }
    
    @Override
    public final String b() {
        return this.J.a;
    }
    
    public final void b(final Throwable t) {
        // monitorenter(this)
        Label_0015: {
            if (t != null) {
                break Label_0015;
            }
            while (true) {
                try {
                    dw.b("Calling logHandledException with a null java.lang.Throwable. Nothing will be reported to Crittercism");
                    Label_0012: {
                        return;
                    }
                    // iftrue(Label_0067:, !this.s)
                    final ax$7 ax$7 = new ax$7(this, t, Thread.currentThread().getId());
                    // iftrue(Label_0012:, this.o.a((Runnable)ax$7))
                    this.r.execute(ax$7);
                    return;
                }
                finally {
                }
                // monitorexit(this)
                final ax$8 ax$8;
                Label_0067: {
                    ax$8 = new ax$8(this, t, Thread.currentThread().getId());
                }
                if (!this.o.a(ax$8)) {
                    this.r.execute(ax$8);
                }
            }
        }
    }
    
    @Override
    public final String c() {
        String a = "";
        if (this.w != null) {
            a = this.w.a();
        }
        return a;
    }
    
    public final void c(final String s) {
        if (this.s) {
            dw.b("Transactions are not supported for services. Ignoring Crittercism.beginTransaction() call for " + s + ".");
        }
        else {
            final Transaction a = Transaction.a(s);
            if (a instanceof be) {
                synchronized (this.z) {
                    final Transaction transaction = this.z.remove(s);
                    if (transaction != null) {
                        ((be)transaction).d();
                    }
                    if (this.z.size() > 50) {
                        dw.b("Crittercism only supports a maximum of 50 concurrent transactions. Ignoring Crittercism.beginTransaction() call for " + s + ".");
                        return;
                    }
                }
                final Throwable t;
                this.z.put(t, a);
                a.a();
            }
            // monitorexit(map)
        }
    }
    
    @Override
    public final boolean c(final String s, final String s2) {
        boolean boolean1 = false;
        final SharedPreferences sharedPreferences = this.c.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            boolean1 = sharedPreferences.getBoolean(s2, false);
        }
        return boolean1;
    }
    
    @Override
    public final String d() {
        return "5.6.4";
    }
    
    public final void d(final String s) {
        if (this.s) {
            dw.b("Transactions are not supported for services. Ignoring Crittercism.endTransaction() call for " + s + ".");
        }
        else {
            synchronized (this.z) {
                final Transaction transaction = this.z.remove(s);
                // monitorexit(this.z)
                if (transaction != null) {
                    transaction.b();
                }
            }
        }
    }
    
    @Override
    public final int e() {
        int intValue = -1;
        if (this.D != null) {
            intValue = Integer.valueOf(this.D.a().a);
        }
        return intValue;
    }
    
    @Override
    public final String f() {
        return new bv$f().a;
    }
    
    @Override
    public final int g() {
        return new bv$o().a;
    }
    
    @Override
    public final int h() {
        return new bv$p().a;
    }
    
    @Override
    public final String i() {
        return "Android";
    }
    
    @Override
    public final String j() {
        return Build.MODEL;
    }
    
    @Override
    public final String k() {
        return Build$VERSION.RELEASE;
    }
    
    @Override
    public final dv l() {
        return this.D;
    }
    
    @Override
    public final dr m() {
        return this.g;
    }
    
    @Override
    public final ds n() {
        return this.A;
    }
    
    @Override
    public final bq o() {
        return this.E;
    }
    
    @Override
    public final bq p() {
        return this.h;
    }
    
    @Override
    public final bq q() {
        return this.F;
    }
    
    @Override
    public final bq r() {
        return this.G;
    }
    
    @Override
    public final bq s() {
        return this.H;
    }
    
    @Override
    public final bq t() {
        return this.i;
    }
    
    @Override
    public final bq u() {
        return this.j;
    }
    
    @Override
    public final bq v() {
        return this.I;
    }
    
    @Override
    public final bq w() {
        return this.k;
    }
    
    @Override
    public final bq x() {
        return this.l;
    }
    
    @Override
    public final bq y() {
        return this.m;
    }
    
    @Override
    public final du z() {
        return this.x;
    }
}
