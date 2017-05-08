// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build;
import android.net.Uri;
import android.content.Intent;
import java.util.List;
import org.json.JSONObject;
import java.util.Collection;
import java.util.Arrays;
import org.json.JSONArray;
import java.util.Locale;
import android.content.SharedPreferences$Editor;
import com.crittercism.app.Transaction;
import java.util.concurrent.TimeUnit;
import android.webkit.WebView;
import android.content.SharedPreferences;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.os.Build$VERSION;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.annotation.TargetApi;
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
    
    private String J() {
        try {
            if (this.K == null || this.K.equals("")) {
                this.K = this.c.getPackageName();
            }
            return this.K;
        }
        catch (Exception ex) {
            dw.b("Call to getPackageName() failed.  Please contact us at support@crittercism.com.");
            this.K = new String();
            return this.K;
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
    
    @TargetApi(5)
    public final void E() {
        if (this.A != null) {
            this.A.d();
        }
    }
    
    @TargetApi(5)
    public final String F() {
        final PackageManager packageManager = this.c.getPackageManager();
        final String j = this.J();
        String a = null;
        if (j != null) {
            a = a;
            if (j.length() > 0) {
                final dm a2 = do.a(packageManager.getInstallerPackageName(j));
                if (a2 == null) {
                    dw.b("Could not find app market for this app.  Will try rate-my-app test target in config.");
                    return this.u.getRateMyAppTestTarget();
                }
                a = a2.a(j).a();
            }
        }
        return a;
    }
    
    public final void G() {
        if (!this.s) {
            final ax$4 ax$4 = new ax$4(this, this);
            if (!this.o.a(ax$4)) {
                this.q.execute(ax$4);
            }
        }
    }
    
    public final AlertDialog a(final Context context, final String title, final String message) {
        int n = 0;
        if (this.g.a()) {
            dw.d("User has opted out of crittercism. generateRateMyAppAlertDialog returning null.");
        }
        else if (!(context instanceof Activity)) {
            dw.a("Context object must be an instance of Activity for AlertDialog to form correctly.  generateRateMyAppAlertDialog returning null.");
        }
        else if (message == null || (message != null && message.length() == 0)) {
            dw.a("Message has to be a non-empty string.  generateRateMyAppAlertDialog returning null.");
        }
        else if (Build$VERSION.SDK_INT < 5) {
            dw.a("Rate my app not supported below api level 5");
        }
        else {
            n = 1;
        }
        if (n == 0) {
            return null;
        }
        final String f = this.F();
        if (f == null) {
            dw.a("Cannot create proper URI to open app market.  Returning null.");
            return null;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        alertDialog$Builder.setTitle((CharSequence)title).setMessage((CharSequence)message);
        try {
            final AlertDialog create = alertDialog$Builder.create();
            create.setButton(-1, (CharSequence)"Yes", (DialogInterface$OnClickListener)new ax$12(this, f));
            create.setButton(-2, (CharSequence)"No", (DialogInterface$OnClickListener)new ax$13(this));
            create.setButton(-3, (CharSequence)"Maybe Later", (DialogInterface$OnClickListener)new ax$2(this));
            return create;
        }
        catch (Exception ex) {
            dw.a("Failed to create AlertDialog instance from AlertDialog.Builder.  Did you remember to call Looper.prepare() before calling Crittercism.generateRateMyAppAlertDialog()?");
            return null;
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
    
    public final void a(final WebView p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    android/os/Looper.myLooper:()Landroid/os/Looper;
        //     3: invokestatic    android/os/Looper.getMainLooper:()Landroid/os/Looper;
        //     6: if_acmpeq       16
        //     9: ldc_w           "Crittercism.instrumentWebView(WebView) not called on the UI thread. Skipping instrumentation"
        //    12: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;)V
        //    15: return         
        //    16: aload_0        
        //    17: getfield        com/crittercism/internal/ax.L:Ljava/util/Set;
        //    20: astore_2       
        //    21: aload_2        
        //    22: monitorenter   
        //    23: aload_0        
        //    24: getfield        com/crittercism/internal/ax.L:Ljava/util/Set;
        //    27: aload_1        
        //    28: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    33: ifeq            44
        //    36: aload_2        
        //    37: monitorexit    
        //    38: return         
        //    39: astore_1       
        //    40: aload_2        
        //    41: monitorexit    
        //    42: aload_1        
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/crittercism/internal/ax.L:Ljava/util/Set;
        //    48: aload_1        
        //    49: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    54: pop            
        //    55: aload_2        
        //    56: monitorexit    
        //    57: new             Lcom/crittercism/internal/ef;
        //    60: dup            
        //    61: aload_0        
        //    62: aload_0        
        //    63: getfield        com/crittercism/internal/ax.v:Lcom/crittercism/internal/e;
        //    66: aload_0        
        //    67: getfield        com/crittercism/internal/ax.c:Landroid/content/Context;
        //    70: invokespecial   com/crittercism/internal/ef.<init>:(Lcom/crittercism/internal/ax;Lcom/crittercism/internal/e;Landroid/content/Context;)V
        //    73: astore_3       
        //    74: new             Lcom/crittercism/internal/ee;
        //    77: dup            
        //    78: invokespecial   com/crittercism/internal/ee.<init>:()V
        //    81: pop            
        //    82: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    85: bipush          15
        //    87: if_icmpgt       157
        //    90: aload_1        
        //    91: invokestatic    com/crittercism/internal/ee.a:(Landroid/webkit/WebView;)Landroid/webkit/WebViewClient;
        //    94: astore_2       
        //    95: aload_1        
        //    96: new             Lcom/crittercism/internal/ed;
        //    99: dup            
        //   100: aload_2        
        //   101: aload_3        
        //   102: getfield        com/crittercism/internal/ef.b:Lcom/crittercism/internal/e;
        //   105: aload_3        
        //   106: getfield        com/crittercism/internal/ef.c:Lcom/crittercism/internal/d;
        //   109: aload_3        
        //   110: getfield        com/crittercism/internal/ef.d:Ljava/lang/String;
        //   113: invokespecial   com/crittercism/internal/ed.<init>:(Landroid/webkit/WebViewClient;Lcom/crittercism/internal/e;Lcom/crittercism/internal/d;Ljava/lang/String;)V
        //   116: invokevirtual   android/webkit/WebView.setWebViewClient:(Landroid/webkit/WebViewClient;)V
        //   119: aload_1        
        //   120: invokevirtual   android/webkit/WebView.getSettings:()Landroid/webkit/WebSettings;
        //   123: invokevirtual   android/webkit/WebSettings.getJavaScriptEnabled:()Z
        //   126: ifeq            15
        //   129: aload_1        
        //   130: new             Lcom/crittercism/webview/CritterJSInterface;
        //   133: dup            
        //   134: aload_3        
        //   135: getfield        com/crittercism/internal/ef.a:Lcom/crittercism/internal/ax;
        //   138: invokespecial   com/crittercism/webview/CritterJSInterface.<init>:(Lcom/crittercism/internal/ax;)V
        //   141: ldc_w           "_crttr"
        //   144: invokevirtual   android/webkit/WebView.addJavascriptInterface:(Ljava/lang/Object;Ljava/lang/String;)V
        //   147: return         
        //   148: astore_1       
        //   149: aload_1        
        //   150: invokevirtual   com/crittercism/internal/cj.getMessage:()Ljava/lang/String;
        //   153: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/String;)V
        //   156: return         
        //   157: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   160: bipush          18
        //   162: if_icmpgt       173
        //   165: aload_1        
        //   166: invokestatic    com/crittercism/internal/ee.b:(Landroid/webkit/WebView;)Landroid/webkit/WebViewClient;
        //   169: astore_2       
        //   170: goto            95
        //   173: aload_1        
        //   174: invokestatic    com/crittercism/internal/ee.c:(Landroid/webkit/WebView;)Landroid/webkit/WebViewClient;
        //   177: astore_2       
        //   178: goto            95
        //   181: astore_1       
        //   182: aload_1        
        //   183: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   186: ldc_w           "Failed to find WebViewClient. WebView will not be instrumented."
        //   189: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;)V
        //   192: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  23     38     39     44     Any
        //  40     42     39     44     Any
        //  44     57     39     44     Any
        //  74     82     148    157    Lcom/crittercism/internal/cj;
        //  82     95     181    193    Lcom/crittercism/internal/cj;
        //  157    170    181    193    Lcom/crittercism/internal/cj;
        //  173    178    181    193    Lcom/crittercism/internal/cj;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
    
    public final void a(final String s, final int n) {
        if (this.s) {
            dw.b("Transactions are not supported for services. Ignoring Crittercism.setTransactionValue() call for " + s + ".");
            return;
        }
        synchronized (this.z) {
            final Transaction transaction = this.z.get(s);
            if (transaction != null) {
                transaction.a(n);
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
    
    public final void a(final String s, final String s2, final long n, final long n2, final long n3, final int n4, final int n5, final long n6) {
        this.a(s, s2, n, n2, n3, n4, new ax$6(this, n5), n6);
    }
    
    public final void a(final String s, final String s2, final long n, final long n2, final long n3, final int e, final ax$b ax$b, final long n4) {
        if (s == null) {
            dw.a("Null HTTP request method provided. Endpoint will not be logged.");
            return;
        }
        final String upperCase = s.toUpperCase(Locale.US);
        if (!com.crittercism.internal.c.c(upperCase)) {
            dw.b("Logging endpoint with invalid HTTP request method: " + s);
        }
        if (s2 == null) {
            dw.a("Null url provided. Endpoint will not be logged");
            return;
        }
        if (n2 < 0L || n3 < 0L) {
            dw.a("Invalid byte values. Bytes need to be non-negative. Endpoint will not be logged.");
            return;
        }
        if (e != 0) {
            if (e < 100 || e >= 600) {
                dw.b("Logging endpoint with invalid HTTP response code: " + Integer.toString(e));
            }
        }
        else if (ax$b.a()) {
            dw.b("Logging endpoint with null error and response code of 0.");
        }
        final b a = new d(this.c).a();
        if (n < 0L) {
            dw.a("Invalid latency. Endpoint will not be logged.");
            return;
        }
        if (n4 < 0L) {
            dw.a("Invalid start time. Endpoint will not be logged.");
            return;
        }
        final c c = new c();
        c.f = upperCase;
        c.a(s2);
        c.a(n2);
        c.b(n3);
        c.e = e;
        c.j = a;
        c.c(n4);
        c.d(n4 + n);
        if (ba.b()) {
            c.a(ba.a());
        }
        ax$b.a(c);
        this.v.a(c, c$a.l);
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
    
    @TargetApi(5)
    public final void b(final String s) {
        if (this.A != null) {
            this.A.d();
        }
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(Uri.parse(s));
        this.c.startActivity(intent);
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
    
    public final void e(final String s) {
        if (this.s) {
            dw.b("Transactions are not supported for services. Ignoring Crittercism.failTransaction() call for " + s + ".");
        }
        else {
            synchronized (this.z) {
                final Transaction transaction = this.z.remove(s);
                // monitorexit(this.z)
                if (transaction != null) {
                    transaction.c();
                }
            }
        }
    }
    
    @Override
    public final String f() {
        return new bv$f().a;
    }
    
    public final void f(final String s) {
        if (this.s) {
            dw.b("Transactions are not supported for services. Ignoring cancelTransaction() call for " + s + ".");
        }
        else {
            synchronized (this.z) {
                final Transaction transaction = this.z.remove(s);
                // monitorexit(this.z)
                if (transaction != null) {
                    transaction.d();
                }
            }
        }
    }
    
    @Override
    public final int g() {
        return new bv$o().a;
    }
    
    public final int g(final String s) {
        if (this.s) {
            dw.b("Transactions are not supported for services. Returning default value of -1 for " + s + ".");
            return -1;
        }
        while (true) {
            synchronized (this.z) {
                final Transaction transaction = this.z.get(s);
                if (transaction != null) {
                    return transaction.a_();
                }
            }
            return -1;
        }
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
