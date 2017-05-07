// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build;
import java.util.List;
import org.json.JSONObject;
import java.util.Collection;
import java.util.Arrays;
import org.json.JSONArray;
import android.content.SharedPreferences$Editor;
import java.util.concurrent.TimeUnit;
import android.app.Application$ActivityLifecycleCallbacks;
import android.os.MessageQueue$IdleHandler;
import android.os.Looper;
import android.app.Application;
import android.os.Build$VERSION;
import java.net.URL;
import com.crittercism.app.CrittercismConfig;
import android.content.SharedPreferences;
import java.util.Iterator;
import android.app.ActivityManager$RunningServiceInfo;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import java.util.concurrent.Executor;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import android.os.ConditionVariable;
import android.content.Context;

public final class az implements au, aw, ax, f
{
    static az a;
    public du A;
    int B;
    public boolean C;
    private String D;
    private bs E;
    private bs F;
    private g G;
    private at H;
    private boolean I;
    private String J;
    public boolean b;
    public Context c;
    public final ConditionVariable d;
    public final ConditionVariable e;
    public dx f;
    bs g;
    bs h;
    bs i;
    bs j;
    bs k;
    bs l;
    bs m;
    bs n;
    bs o;
    cw p;
    public dh q;
    ExecutorService r;
    public ExecutorService s;
    public boolean t;
    public bb u;
    protected e v;
    public ds w;
    dw x;
    public bi y;
    public Map z;
    
    protected az() {
        this.b = false;
        this.c = null;
        this.D = null;
        this.d = new ConditionVariable(false);
        this.e = new ConditionVariable(false);
        this.f = new dx();
        this.p = null;
        this.q = null;
        this.G = null;
        this.r = Executors.newCachedThreadPool(new ea());
        this.s = Executors.newSingleThreadExecutor(new ea());
        this.I = false;
        this.t = false;
        this.J = "";
        this.x = null;
        this.z = new HashMap();
        this.A = null;
        this.B = 0;
        this.C = false;
        this.v = new e(this.s);
    }
    
    public static az A() {
        if (az.a == null) {
            az.a = new az();
        }
        return az.a;
    }
    
    private static boolean F() {
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
    
    private void G() {
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
            this.t = false;
        }
        else {
            final Iterator iterator2 = activityManager.getRunningServices(Integer.MAX_VALUE).iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().pid == myPid) {
                    this.t = true;
                }
            }
        }
    }
    
    public final boolean B() {
        this.d.block();
        return this.f.b();
    }
    
    public final void E() {
        if (!this.t) {
            final az$3 az$3 = new az$3(this, this);
            if (!this.q.a(az$3)) {
                this.r.execute(az$3);
            }
        }
    }
    
    @Override
    public final String a() {
        String d;
        if ((d = this.D) == null) {
            d = "";
        }
        return d;
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
    
    public final void a(Context defaultUncaughtExceptionHandler, final String d, final CrittercismConfig crittercismConfig) {
        dy.a("Initializing Crittercism 5.0.6 for App ID " + d);
        this.D = d;
        this.u = new bb(d, crittercismConfig);
        this.c = defaultUncaughtExceptionHandler;
        this.H = new at(this.c, this.u);
        this.J = defaultUncaughtExceptionHandler.getPackageName();
        this.w = new ds(defaultUncaughtExceptionHandler);
        this.G();
        long n = 60000000000L;
        if (this.t) {
            n = 12000000000L;
        }
        this.p = new cw(n);
        if (!F()) {
            dy.c("Crittercism should be initialized in onCreate() of MainActivity");
        }
    Label_0402_Outer:
        while (true) {
            bx.a(this.H);
            bx.a(this.c);
            bx.a(new cc());
            bx.a(new bf(this.c, this.u));
            while (true) {
                Label_0570: {
                Label_0542:
                    while (true) {
                        try {
                            this.v.a(this.u.a());
                            this.v.b(this.u.getPreserveQueryStringPatterns());
                            this.G = new g(this, new URL(this.u.c() + "/api/apm/network"));
                            this.v.a(this.G);
                            this.v.a(this);
                            new dz(this.G, "OPTMZ").start();
                            if (crittercism.android.h.a(this.c).exists() && this.u.isServiceMonitoringEnabled()) {
                                this.I = new i(this.v, new d(this.c)).a();
                                new StringBuilder("installedApm = ").append(this.I);
                                dy.b();
                            }
                            this.q = new dh(this.u, defaultUncaughtExceptionHandler, this, this, this);
                            if (!this.t) {
                                dy.a(new ed(this, this.s, this.q, this.f));
                            }
                            defaultUncaughtExceptionHandler = (Context)Thread.getDefaultUncaughtExceptionHandler();
                            if (!(defaultUncaughtExceptionHandler instanceof ay)) {
                                Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ay(this, (Thread.UncaughtExceptionHandler)defaultUncaughtExceptionHandler));
                            }
                            if (Build$VERSION.SDK_INT < 14) {
                                break Label_0570;
                            }
                            if (!(this.c instanceof Application)) {
                                dy.c("Application context not provided. Automatic breadcrumbs will not be recorded.");
                                if (!this.t) {
                                    bg.b(this);
                                    if (Looper.myLooper() == Looper.getMainLooper()) {
                                        Looper.myQueue().addIdleHandler((MessageQueue$IdleHandler)new az$a((byte)0));
                                    }
                                }
                                new dz(this.q).start();
                                this.b = true;
                                return;
                            }
                            break Label_0542;
                        }
                        catch (Exception ex) {
                            new StringBuilder("Exception in startApm: ").append(ex.getClass().getName());
                            dy.b();
                            dy.c();
                            continue Label_0402_Outer;
                        }
                        continue Label_0402_Outer;
                    }
                    dy.b();
                    ((Application)this.c).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new av(this.c, this));
                    continue;
                }
                dy.a("API Level is less than 14. Automatic breadcrumbs are not supported.");
                continue;
            }
        }
    }
    
    public final void a(final bh bh) {
        final bi y = this.y;
        if (this.y != null) {
            bg.a(bh);
            bg.i();
            if (bh.a) {
                this.y.a(bh.b, TimeUnit.SECONDS);
                this.y.b();
            }
        }
    }
    
    @Override
    public final void a(final c c) {
        final az$8 az$8 = new az$8(this, c);
        if (!this.q.a(az$8)) {
            this.s.execute(az$8);
        }
    }
    
    public final void a(final ci ci) {
        if (!this.f.b()) {
            final az$9 az$9 = new az$9(this, ci);
            if (!this.q.a(az$9)) {
                this.s.execute(az$9);
            }
        }
    }
    
    public final void a(final h h) {
        if (this.G != null && h.a && !h.c) {
            dy.a("Enabling OPTMZ");
            this.G.a(h.d, TimeUnit.SECONDS);
            this.G.a();
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
    
    final void a(final Throwable t) {
        final List a = bg.a(this, t instanceof cq);
        final bk bk = new bk(t, Thread.currentThread().getId());
        bk.a("crashed_session", this.k);
        if (this.F.b() > 0) {
            bk.a("previous_session", this.F);
        }
        bk.a(this.l);
        bk.b = new bo(this.m).a;
        bk.e = new JSONArray();
        for (final Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            final HashMap<String, JSONArray> hashMap = new HashMap<String, JSONArray>();
            final Thread thread = entry.getKey();
            if (thread.getId() != bk.a) {
                hashMap.put("name", (JSONArray)thread.getName());
                hashMap.put("id", (JSONArray)thread.getId());
                hashMap.put("state", (JSONArray)thread.getState().name());
                hashMap.put("stacktrace", new JSONArray((Collection)Arrays.asList((StackTraceElement[])entry.getValue())));
                bk.e.put((Object)new JSONObject((Map)hashMap));
            }
        }
        bk.a(a);
        this.j.a(bk);
        final dg dg = new dg(this.c);
        dg.a(this.g, new db$a(), this.u.e(), "/v0/appload/", null, this, new ct$b());
        dg.a(this.h, new db$a(), this.u.b(), "/android_v2/handle_exceptions", null, this, new cv$a());
        dg.a(this.i, new db$a(), this.u.b(), "/android_v2/handle_ndk_crashes", null, this, new cv$a());
        dg.a(this.j, new db$a(), this.u.b(), "/android_v2/handle_crashes", null, this, new cv$a());
        try {
            dg.a();
        }
        catch (InterruptedException ex) {
            new StringBuilder("InterruptedException in logCrashException: ").append(ex.getMessage());
            dy.b();
            dy.c();
        }
        catch (Throwable t) {
            new StringBuilder("Unexpected throwable in logCrashException: ").append(t.getMessage());
            dy.b();
            dy.c();
        }
    }
    
    public final void a(final JSONObject jsonObject) {
        if (!this.t) {
            final az$2 az$2 = new az$2(this, this, jsonObject);
            if (!this.q.a(az$2)) {
                this.s.execute(az$2);
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
        return this.H.a;
    }
    
    public final void b(final Throwable t) {
        // monitorenter(this)
        Label_0015: {
            if (t != null) {
                break Label_0015;
            }
            while (true) {
                try {
                    dy.c("Calling logHandledException with a null java.lang.Throwable. Nothing will be reported to Crittercism");
                    Label_0012: {
                        return;
                    }
                    while (true) {
                        final az$5 az$5 = new az$5(this, t, Thread.currentThread().getId());
                        this.s.execute(az$5);
                        return;
                        continue;
                    }
                }
                // iftrue(Label_0012:, this.q.a((Runnable)az$5))
                // iftrue(Label_0067:, !this.t)
                finally {
                }
                // monitorexit(this)
                final az$6 az$6;
                Label_0067: {
                    az$6 = new az$6(this, t, Thread.currentThread().getId());
                }
                if (!this.q.a(az$6)) {
                    this.s.execute(az$6);
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
        return "5.0.6";
    }
    
    @Override
    public final int e() {
        int intValue = -1;
        if (this.f != null) {
            intValue = Integer.valueOf(this.f.a().a);
        }
        return intValue;
    }
    
    @Override
    public final String f() {
        return new bx$f().a;
    }
    
    @Override
    public final int g() {
        return new bx$o().a;
    }
    
    @Override
    public final int h() {
        return new bx$p().a;
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
    public final dx l() {
        return this.f;
    }
    
    @Override
    public final du m() {
        return this.A;
    }
    
    @Override
    public final bs n() {
        return this.g;
    }
    
    @Override
    public final bs o() {
        return this.h;
    }
    
    @Override
    public final bs p() {
        return this.E;
    }
    
    @Override
    public final bs q() {
        return this.i;
    }
    
    @Override
    public final bs r() {
        return this.j;
    }
    
    @Override
    public final bs s() {
        return this.k;
    }
    
    @Override
    public final bs t() {
        return this.l;
    }
    
    @Override
    public final bs u() {
        return this.F;
    }
    
    @Override
    public final bs v() {
        return this.m;
    }
    
    @Override
    public final bs w() {
        return this.n;
    }
    
    @Override
    public final bs x() {
        return this.o;
    }
    
    @Override
    public final dw y() {
        return this.x;
    }
    
    @Override
    public final void z() {
        if (this.t) {
            this.k = new bs(this.c, br.f).a(this.c);
        }
        else {
            this.k = new bs(this.c, br.f);
        }
        this.F = new bs(this.c, br.h);
        this.l = new bs(this.c, br.g);
        this.m = new bs(this.c, br.k);
        this.g = new bs(this.c, br.a);
        this.h = new bs(this.c, br.b);
        this.E = new bs(this.c, br.c);
        this.i = new bs(this.c, br.d);
        this.j = new bs(this.c, br.e);
        this.n = new bs(this.c, br.i);
        this.o = new bs(this.c, br.j);
        if (!this.t) {
            this.x = new dw(this.c, this.D);
        }
    }
}
