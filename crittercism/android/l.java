// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.pm.PackageManager;
import android.os.Build;
import android.net.Uri;
import android.content.Intent;
import android.content.SharedPreferences$Editor;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.os.Build$VERSION;
import android.app.Activity;
import android.util.Log;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import java.util.concurrent.Executors;
import java.io.File;
import java.util.concurrent.ExecutorService;
import android.content.Context;

public final class l implements g, h
{
    static l a;
    public boolean b;
    public i c;
    public as d;
    public at e;
    public Context f;
    public n g;
    public x h;
    public u i;
    public v j;
    public ai k;
    public ExecutorService l;
    public q m;
    public q n;
    public q o;
    public boolean p;
    public boolean q;
    public Thread r;
    public File s;
    public m t;
    private c u;
    private ExecutorService v;
    private boolean w;
    private String x;
    
    private l() {
        this.b = false;
        this.d = null;
        this.e = new at();
        this.f = null;
        this.u = null;
        this.k = null;
        this.l = Executors.newCachedThreadPool();
        this.v = Executors.newSingleThreadExecutor();
        this.m = new q(100, true, ae.a.b(), new w$a());
        this.n = new q(100, false, ae.b.b(), new w$a());
        this.o = new q(10, false, ae.c.b(), new b$a());
        this.w = false;
        this.p = false;
        this.q = false;
        this.x = "";
        this.r = null;
        this.s = null;
        this.t = new m();
    }
    
    public static l i() {
        if (l.a == null) {
            l.a = new l();
        }
        return l.a;
    }
    
    @Override
    public final int a(final String s, final String s2) {
        int int1 = 0;
        final SharedPreferences sharedPreferences = this.f.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            int1 = sharedPreferences.getInt(s2, 0);
        }
        return int1;
    }
    
    public final AlertDialog a(final Context context, final String title, final String message) {
        int n = 0;
        if (this.e.d()) {
            Log.e("CrittercismInstance", "User has opted out of crittercism.  generateRateMyAppAlertDialog returning null.");
        }
        else if (!(context instanceof Activity)) {
            Log.e("CrittercismInstance", "Context object must be an instance of Activity for AlertDialog to form correctly.  generateRateMyAppAlertDialog returning null.");
        }
        else if (message == null || (message != null && message.length() == 0)) {
            Log.e("CrittercismInstance", "Message has to be a non-empty string.  generateRateMyAppAlertDialog returning null.");
        }
        else if (Build$VERSION.SDK_INT < 5) {
            Log.e("Crittercism", "Rate my app not supported below api level 5");
        }
        else {
            n = 1;
        }
        if (n == 0) {
            return null;
        }
        final String p3 = this.p();
        if (p3 == null) {
            Log.e("Crittercism", "Cannot create proper URI to open app market.  Returning null.");
            return null;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        alertDialog$Builder.setTitle((CharSequence)title).setMessage((CharSequence)message);
        try {
            final AlertDialog create = alertDialog$Builder.create();
            create.setButton(-1, (CharSequence)"Yes", (DialogInterface$OnClickListener)new l$6(this, p3));
            create.setButton(-2, (CharSequence)"No", (DialogInterface$OnClickListener)new l$7(this));
            create.setButton(-3, (CharSequence)"Maybe Later", (DialogInterface$OnClickListener)new l$8(this));
            return create;
        }
        catch (Exception ex) {
            Log.e("Crittercism", "Failed to create AlertDialog instance from AlertDialog.Builder.  Did you remember to call Looper.prepare() before calling Crittercism.generateRateMyAppAlertDialog()?");
            return null;
        }
    }
    
    @Override
    public final String a() {
        if (this.c == null) {
            Log.w("CrittercismInstance", "Failed to get app id.  Please contact us at support@crittercism.com.");
            return new String();
        }
        return this.c.a();
    }
    
    @Override
    public final String a(final String s, final String s2, final String s3) {
        final SharedPreferences sharedPreferences = this.f.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(s2, s3);
        }
        return null;
    }
    
    public final void a(final String s) {
        synchronized (this) {
            if (!this.e.d()) {
                this.m.a(new w(s));
            }
        }
    }
    
    @Override
    public final void a(final String s, final String s2, final int n) {
        final SharedPreferences sharedPreferences = this.f.getSharedPreferences(s, 0);
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
    public final void a(final String s, final String s2, final boolean b) {
        final SharedPreferences sharedPreferences = this.f.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.remove(s2);
                edit.putBoolean(s2, b);
                edit.commit();
            }
        }
    }
    
    public final void a(final boolean b) {
        new af(this.g, b).a();
        new af(this.h, b).a();
        new af(this.i, b).a();
    }
    
    @Override
    public final String b() {
        return this.c.k();
    }
    
    public final void b(final String s) {
        this.e.e().b = true;
        new Thread(new l$4(this)).start();
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(Uri.parse(s));
        this.f.startActivity(intent);
    }
    
    @Override
    public final void b(final String s, final String s2, final String s3) {
        final SharedPreferences sharedPreferences = this.f.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.remove(s2);
                edit.putString(s2, s3);
                edit.commit();
            }
        }
    }
    
    @Override
    public final boolean b(final String s, final String s2) {
        boolean boolean1 = false;
        final SharedPreferences sharedPreferences = this.f.getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            boolean1 = sharedPreferences.getBoolean(s2, false);
        }
        return boolean1;
    }
    
    @Override
    public final String c() {
        if (this.c == null) {
            Log.w("CrittercismInstance", "Failed to get hashed device id.  Please contact us at support@crittercism.com.");
            return new String();
        }
        return this.c.c();
    }
    
    @Override
    public final int d() {
        if (this.c == null) {
            Log.w("CrittercismInstance", "Failed to get session id.  Please contact us at support@crittercism.com.");
            return -1;
        }
        return this.c.b();
    }
    
    @Override
    public final String e() {
        if (this.c == null) {
            Log.w("CrittercismInstance", "Failed to get carrier.  Please contact us at support@crittercism.com.");
            return new String();
        }
        return this.c.e();
    }
    
    @Override
    public final String f() {
        return "Android";
    }
    
    @Override
    public final String g() {
        return Build.MODEL;
    }
    
    @Override
    public final String h() {
        return Build$VERSION.RELEASE;
    }
    
    public final String j() {
        try {
            if (this.x == null || this.x.equals("")) {
                this.x = this.f.getPackageName();
            }
            return this.x;
        }
        catch (Exception ex) {
            Log.w("CrittercismInstance", "Call to getPackageName() failed.  Please contact us at support@crittercism.com.");
            this.x = new String();
            return this.x;
        }
    }
    
    public final boolean k() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_3       
        //     2: iconst_0       
        //     3: istore_1       
        //     4: new             Ljava/util/concurrent/FutureTask;
        //     7: dup            
        //     8: new             Lcrittercism/android/l$2;
        //    11: dup            
        //    12: aload_0        
        //    13: invokespecial   crittercism/android/l$2.<init>:(Lcrittercism/android/l;)V
        //    16: invokespecial   java/util/concurrent/FutureTask.<init>:(Ljava/util/concurrent/Callable;)V
        //    19: astore          4
        //    21: bipush          10
        //    23: invokestatic    java/util/concurrent/Executors.newFixedThreadPool:(I)Ljava/util/concurrent/ExecutorService;
        //    26: astore          5
        //    28: aload           5
        //    30: aload           4
        //    32: invokeinterface java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
        //    37: iconst_0       
        //    38: istore_2       
        //    39: aload           4
        //    41: invokevirtual   java/util/concurrent/FutureTask.isDone:()Z
        //    44: ifne            68
        //    47: aload           4
        //    49: ldc2_w          8000
        //    52: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //    55: invokevirtual   java/util/concurrent/FutureTask.get:(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //    58: checkcast       Ljava/lang/Boolean;
        //    61: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    64: istore_2       
        //    65: goto            39
        //    68: iload_1        
        //    69: ifeq            86
        //    72: aload_0        
        //    73: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    76: invokevirtual   crittercism/android/v.a:()V
        //    79: aload_0        
        //    80: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    83: invokevirtual   crittercism/android/v.g:()V
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: ldc_w           "sentNdkCrashes = "
        //    93: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    96: astore          5
        //    98: iload_2        
        //    99: ifeq            160
        //   102: ldc_w           "TRUE"
        //   105: astore          4
        //   107: aload           5
        //   109: aload           4
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: pop            
        //   115: iload_2        
        //   116: ireturn        
        //   117: astore          4
        //   119: iconst_1       
        //   120: istore_1       
        //   121: iload_3        
        //   122: istore_2       
        //   123: goto            68
        //   126: astore          4
        //   128: iconst_0       
        //   129: istore_2       
        //   130: goto            68
        //   133: astore          4
        //   135: new             Ljava/lang/StringBuilder;
        //   138: dup            
        //   139: ldc_w           "Exception in startNdkSendingThreads when attempting to flush pending ndk crashes: "
        //   142: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   145: aload           4
        //   147: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   150: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: pop            
        //   157: goto            86
        //   160: ldc_w           "FALSE"
        //   163: astore          4
        //   165: goto            107
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  28     37     117    126    Ljava/util/concurrent/TimeoutException;
        //  28     37     126    133    Ljava/lang/Exception;
        //  39     65     117    126    Ljava/util/concurrent/TimeoutException;
        //  39     65     126    133    Ljava/lang/Exception;
        //  72     86     133    160    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0086:
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
    
    public final boolean l() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_2       
        //     2: iconst_0       
        //     3: istore_3       
        //     4: new             Lorg/json/JSONObject;
        //     7: dup            
        //     8: invokespecial   org/json/JSONObject.<init>:()V
        //    11: astore          4
        //    13: new             Lorg/json/JSONObject;
        //    16: dup            
        //    17: invokespecial   org/json/JSONObject.<init>:()V
        //    20: pop            
        //    21: aload_0        
        //    22: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    25: invokevirtual   crittercism/android/v.b:()Lorg/json/JSONObject;
        //    28: astore          5
        //    30: aload           5
        //    32: astore          4
        //    34: aload_0        
        //    35: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    38: aload           4
        //    40: invokevirtual   crittercism/android/i.a:(Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //    43: astore          4
        //    45: aload           4
        //    47: ldc_w           "success"
        //    50: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    53: ifeq            150
        //    56: aload           4
        //    58: ldc_w           "success"
        //    61: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //    64: istore_1       
        //    65: iload_1        
        //    66: iconst_1       
        //    67: if_icmpne       150
        //    70: iload_2        
        //    71: ifeq            88
        //    74: aload_0        
        //    75: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    78: invokevirtual   crittercism/android/v.a:()V
        //    81: aload_0        
        //    82: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    85: invokevirtual   crittercism/android/v.g:()V
        //    88: iload_2        
        //    89: ireturn        
        //    90: astore          4
        //    92: new             Ljava/lang/StringBuilder;
        //    95: dup            
        //    96: ldc_w           "Exception obtaining or handling response object or clearing pending ndk filenames vector in attemptToSendNdkCrashes: "
        //    99: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   102: aload           4
        //   104: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   107: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: pop            
        //   114: iload_3        
        //   115: istore_2       
        //   116: goto            70
        //   119: astore          4
        //   121: new             Ljava/lang/StringBuilder;
        //   124: dup            
        //   125: ldc_w           "Exception removing ndk dump files from disk in attemptToSendNdkCrashes: "
        //   128: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   131: aload           4
        //   133: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   136: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: pop            
        //   143: iload_2        
        //   144: ireturn        
        //   145: astore          5
        //   147: goto            34
        //   150: iconst_0       
        //   151: istore_2       
        //   152: goto            70
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     30     145    150    Ljava/lang/Exception;
        //  34     65     90     119    Ljava/lang/Exception;
        //  74     88     119    145    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    public final SharedPreferences m() {
        return this.f.getSharedPreferences("com.crittercism.prefs", 0);
    }
    
    public final int n() {
        try {
            return (int)(this.c.g() * 10.0f / 160.0f);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public final void o() {
        this.e.e().b = true;
        new Thread(new l$5(this)).start();
    }
    
    public final String p() {
        final PackageManager packageManager = this.f.getPackageManager();
        final String j = this.j();
        String a = null;
        if (j != null) {
            a = a;
            if (j.length() > 0) {
                final ak a2 = am.a(packageManager.getInstallerPackageName(j));
                if (a2 == null) {
                    Log.w("Crittercism", "Could not find app market for this app.  Will try rate-my-app test target in config.");
                    return this.t.getRateMyAppTestTarget();
                }
                a = a2.a(j).a();
            }
        }
        return a;
    }
}
