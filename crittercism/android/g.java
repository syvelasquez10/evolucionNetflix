// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.util.LinkedList;
import android.os.ConditionVariable;
import java.net.URL;
import java.util.List;

public final class g implements f, Runnable
{
    private List a;
    private URL b;
    private long c;
    private ConditionVariable d;
    private au e;
    private ConditionVariable f;
    private volatile boolean g;
    private final Object h;
    private int i;
    private volatile long j;
    
    public g(final au au, final URL url) {
        this(au, url, (byte)0);
    }
    
    private g(final au e, final URL b, final byte b2) {
        this.a = new LinkedList();
        this.b = null;
        this.c = System.currentTimeMillis();
        this.d = new ConditionVariable(false);
        this.f = new ConditionVariable(false);
        this.g = false;
        this.h = new Object();
        this.i = 50;
        this.j = 10000L;
        this.e = e;
        this.b = b;
        this.i = 50;
        this.j = 10000L;
    }
    
    private static boolean a(final HttpURLConnection httpURLConnection, final JSONObject jsonObject) {
        boolean b = false;
        try {
            httpURLConnection.getOutputStream().write(jsonObject.toString().getBytes("UTF8"));
            final int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            if (responseCode == 202) {
                b = true;
            }
            return b;
        }
        catch (IOException ex) {
            new StringBuilder("Request failed for ").append(httpURLConnection.getURL().toExternalForm());
            dy.a();
            return false;
        }
        catch (Exception ex2) {
            new StringBuilder("Request failed for ").append(httpURLConnection.getURL().toExternalForm());
            dy.a();
            return false;
        }
    }
    
    private long b() {
        final long n = 0L;
        final long j = this.j;
        final long n2 = System.currentTimeMillis() - this.c;
        long n3 = j;
        if (n2 > 0L && (n3 = j - n2) < 0L) {
            n3 = n;
        }
        final long i = this.j;
        return n3;
    }
    
    private HttpURLConnection c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        crittercism/android/g.b:Ljava/net/URL;
        //     4: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //     7: checkcast       Ljava/net/HttpURLConnection;
        //    10: astore_2       
        //    11: aload_2        
        //    12: sipush          2500
        //    15: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    18: aload_2        
        //    19: ldc             "User-Agent"
        //    21: ldc             "5.0.6"
        //    23: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    26: aload_2        
        //    27: ldc             "Content-Type"
        //    29: ldc             "application/json"
        //    31: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    34: aload_2        
        //    35: iconst_1       
        //    36: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //    39: aload_2        
        //    40: ldc             "POST"
        //    42: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    45: aload_2        
        //    46: instanceof      Ljavax/net/ssl/HttpsURLConnection;
        //    49: ifeq            103
        //    52: aload_2        
        //    53: checkcast       Ljavax/net/ssl/HttpsURLConnection;
        //    56: astore          4
        //    58: ldc             "TLS"
        //    60: invokestatic    javax/net/ssl/SSLContext.getInstance:(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
        //    63: astore_1       
        //    64: aload_1        
        //    65: aconst_null    
        //    66: aconst_null    
        //    67: aconst_null    
        //    68: invokevirtual   javax/net/ssl/SSLContext.init:([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
        //    71: aload_1        
        //    72: invokevirtual   javax/net/ssl/SSLContext.getSocketFactory:()Ljavax/net/ssl/SSLSocketFactory;
        //    75: astore_3       
        //    76: aload_3        
        //    77: ifnull          103
        //    80: aload_3        
        //    81: astore_1       
        //    82: aload_3        
        //    83: instanceof      Lcrittercism/android/ab;
        //    86: ifeq            97
        //    89: aload_3        
        //    90: checkcast       Lcrittercism/android/ab;
        //    93: invokevirtual   crittercism/android/ab.a:()Ljavax/net/ssl/SSLSocketFactory;
        //    96: astore_1       
        //    97: aload           4
        //    99: aload_1        
        //   100: invokevirtual   javax/net/ssl/HttpsURLConnection.setSSLSocketFactory:(Ljavax/net/ssl/SSLSocketFactory;)V
        //   103: aload_2        
        //   104: areturn        
        //   105: astore_2       
        //   106: aconst_null    
        //   107: astore_1       
        //   108: new             Ljava/lang/StringBuilder;
        //   111: dup            
        //   112: ldc             "Failed to instantiate URLConnection to APM server: "
        //   114: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   117: aload_2        
        //   118: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: invokestatic    crittercism/android/dy.b:(Ljava/lang/String;)V
        //   130: aload_1        
        //   131: areturn        
        //   132: astore_1       
        //   133: new             Ljava/lang/StringBuilder;
        //   136: dup            
        //   137: ldc             "Failed to instantiate URLConnection to APM server: "
        //   139: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   142: aload_1        
        //   143: invokevirtual   java/security/GeneralSecurityException.getMessage:()Ljava/lang/String;
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   152: invokestatic    crittercism/android/dy.b:(Ljava/lang/String;)V
        //   155: aconst_null    
        //   156: areturn        
        //   157: astore_3       
        //   158: aload_2        
        //   159: astore_1       
        //   160: aload_3        
        //   161: astore_2       
        //   162: goto            108
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  0      11     105    108    Ljava/io/IOException;
        //  0      11     132    157    Ljava/security/GeneralSecurityException;
        //  11     76     157    165    Ljava/io/IOException;
        //  11     76     132    157    Ljava/security/GeneralSecurityException;
        //  82     97     157    165    Ljava/io/IOException;
        //  82     97     132    157    Ljava/security/GeneralSecurityException;
        //  97     103    157    165    Ljava/io/IOException;
        //  97     103    132    157    Ljava/security/GeneralSecurityException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
    
    private boolean d() {
        return !this.g && this.a.size() < this.i;
    }
    
    public final void a() {
        this.f.open();
    }
    
    public final void a(final int n, final TimeUnit timeUnit) {
        this.j = timeUnit.toMillis(n);
    }
    
    @Override
    public final void a(final c c) {
        int n = 0;
        if (!this.d()) {
            return;
        }
        synchronized (this.h) {
            if (!this.d()) {
                return;
            }
        }
        final c c2;
        this.a.add(c2);
        if (!c2.a().contains(this.b.getHost())) {
            final String f = c2.f;
            if (f == null || !f.toLowerCase().equals("connect")) {
                n = 1;
            }
        }
        if (n != 0) {
            this.d.open();
        }
    }
    // monitorexit(o)
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        crittercism/android/g.g:Z
        //     4: ifne            180
        //     7: aload_0        
        //     8: getfield        crittercism/android/g.f:Landroid/os/ConditionVariable;
        //    11: invokevirtual   android/os/ConditionVariable.block:()V
        //    14: aload_0        
        //    15: getfield        crittercism/android/g.d:Landroid/os/ConditionVariable;
        //    18: invokevirtual   android/os/ConditionVariable.block:()V
        //    21: aload_0        
        //    22: getfield        crittercism/android/g.g:Z
        //    25: istore_1       
        //    26: iload_1        
        //    27: ifne            180
        //    30: aload_0        
        //    31: invokespecial   crittercism/android/g.b:()J
        //    34: lconst_0       
        //    35: lcmp           
        //    36: ifle            46
        //    39: aload_0        
        //    40: invokespecial   crittercism/android/g.b:()J
        //    43: invokestatic    java/lang/Thread.sleep:(J)V
        //    46: aload_0        
        //    47: invokestatic    java/lang/System.currentTimeMillis:()J
        //    50: putfield        crittercism/android/g.c:J
        //    53: aload_0        
        //    54: invokespecial   crittercism/android/g.c:()Ljava/net/HttpURLConnection;
        //    57: astore_2       
        //    58: aload_2        
        //    59: ifnonnull       74
        //    62: aload_0        
        //    63: iconst_1       
        //    64: putfield        crittercism/android/g.g:Z
        //    67: ldc_w           "Disabling APM due to failure instantiating connection"
        //    70: invokestatic    crittercism/android/dy.b:(Ljava/lang/String;)V
        //    73: return         
        //    74: aload_0        
        //    75: getfield        crittercism/android/g.h:Ljava/lang/Object;
        //    78: astore_3       
        //    79: aload_3        
        //    80: monitorenter   
        //    81: aload_0        
        //    82: getfield        crittercism/android/g.a:Ljava/util/List;
        //    85: astore          4
        //    87: aload_0        
        //    88: new             Ljava/util/LinkedList;
        //    91: dup            
        //    92: invokespecial   java/util/LinkedList.<init>:()V
        //    95: putfield        crittercism/android/g.a:Ljava/util/List;
        //    98: aload_0        
        //    99: getfield        crittercism/android/g.d:Landroid/os/ConditionVariable;
        //   102: invokevirtual   android/os/ConditionVariable.close:()V
        //   105: aload_3        
        //   106: monitorexit    
        //   107: aload_0        
        //   108: getfield        crittercism/android/g.e:Lcrittercism/android/au;
        //   111: aload           4
        //   113: invokestatic    crittercism/android/a.a:(Lcrittercism/android/au;Ljava/util/List;)Lcrittercism/android/a;
        //   116: astore_3       
        //   117: aload_3        
        //   118: ifnonnull       164
        //   121: aload_0        
        //   122: iconst_1       
        //   123: putfield        crittercism/android/g.g:Z
        //   126: ldc_w           "Disabling APM due to failure building request"
        //   129: invokestatic    crittercism/android/dy.b:(Ljava/lang/String;)V
        //   132: return         
        //   133: astore_2       
        //   134: ldc_w           "Crittercism"
        //   137: new             Ljava/lang/StringBuilder;
        //   140: dup            
        //   141: ldc_w           "Exited APM send task due to: \n"
        //   144: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   147: aload_2        
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   157: pop            
        //   158: return         
        //   159: astore_2       
        //   160: aload_3        
        //   161: monitorexit    
        //   162: aload_2        
        //   163: athrow         
        //   164: aload_2        
        //   165: aload_3        
        //   166: getfield        crittercism/android/a.a:Lorg/json/JSONObject;
        //   169: invokestatic    crittercism/android/g.a:(Ljava/net/HttpURLConnection;Lorg/json/JSONObject;)Z
        //   172: pop            
        //   173: goto            0
        //   176: astore_2       
        //   177: goto            46
        //   180: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      26     133    159    Ljava/lang/Exception;
        //  30     46     176    180    Ljava/lang/InterruptedException;
        //  30     46     133    159    Ljava/lang/Exception;
        //  46     58     133    159    Ljava/lang/Exception;
        //  62     73     133    159    Ljava/lang/Exception;
        //  74     81     133    159    Ljava/lang/Exception;
        //  81     107    159    164    Any
        //  107    117    133    159    Ljava/lang/Exception;
        //  121    132    133    159    Ljava/lang/Exception;
        //  160    164    133    159    Ljava/lang/Exception;
        //  164    173    133    159    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
}
