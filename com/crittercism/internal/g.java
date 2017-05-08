// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.util.LinkedList;
import java.net.URL;
import java.util.List;
import android.os.ConditionVariable;

public final class g implements f, Runnable
{
    public ConditionVariable a;
    public volatile long b;
    private List c;
    private URL d;
    private long e;
    private ConditionVariable f;
    private ar g;
    private volatile boolean h;
    private final Object i;
    private int j;
    
    public g(final ar ar, final URL url) {
        this(ar, url, (byte)0);
    }
    
    private g(final ar g, final URL d, final byte b) {
        this.c = new LinkedList();
        this.d = null;
        this.e = System.currentTimeMillis();
        this.f = new ConditionVariable(false);
        this.a = new ConditionVariable(false);
        this.h = false;
        this.i = new Object();
        this.j = 50;
        this.b = 10000L;
        this.g = g;
        this.d = d;
        this.j = 50;
        this.b = 10000L;
    }
    
    private long a() {
        final long n = 0L;
        final long b = this.b;
        final long n2 = System.currentTimeMillis() - this.e;
        long n3 = b;
        if (n2 > 0L && (n3 = b - n2) < 0L) {
            n3 = n;
        }
        final long b2 = this.b;
        return n3;
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
            dw.c("Request failed for " + httpURLConnection.getURL().toExternalForm(), ex);
            return false;
        }
        catch (Exception ex2) {
            dw.c("Request failed for " + httpURLConnection.getURL().toExternalForm(), ex2);
            return false;
        }
    }
    
    private HttpURLConnection b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/crittercism/internal/g.d:Ljava/net/URL;
        //     4: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //     7: checkcast       Ljava/net/HttpURLConnection;
        //    10: astore_2       
        //    11: aload_2        
        //    12: sipush          2500
        //    15: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    18: aload_2        
        //    19: ldc             "User-Agent"
        //    21: ldc             "5.6.4"
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
        //    83: instanceof      Lcom/crittercism/internal/p;
        //    86: ifeq            97
        //    89: aload_3        
        //    90: checkcast       Lcom/crittercism/internal/p;
        //    93: invokevirtual   com/crittercism/internal/p.a:()Ljavax/net/ssl/SSLSocketFactory;
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
        //   127: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;)V
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
        //   152: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;)V
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
    
    private boolean c() {
        return !this.h && this.c.size() < this.j;
    }
    
    @Override
    public final void a(final c c) {
        int n = 0;
        if (!this.c()) {
            return;
        }
        synchronized (this.i) {
            if (!this.c()) {
                return;
            }
        }
        final c c2;
        this.c.add(c2);
        if (!c2.a().contains(this.d.getHost())) {
            final String f = c2.f;
            if (f == null || !f.toLowerCase().equals("connect")) {
                n = 1;
            }
        }
        if (n != 0) {
            this.f.open();
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
        //     1: getfield        com/crittercism/internal/g.h:Z
        //     4: ifne            180
        //     7: aload_0        
        //     8: getfield        com/crittercism/internal/g.a:Landroid/os/ConditionVariable;
        //    11: invokevirtual   android/os/ConditionVariable.block:()V
        //    14: aload_0        
        //    15: getfield        com/crittercism/internal/g.f:Landroid/os/ConditionVariable;
        //    18: invokevirtual   android/os/ConditionVariable.block:()V
        //    21: aload_0        
        //    22: getfield        com/crittercism/internal/g.h:Z
        //    25: istore_1       
        //    26: iload_1        
        //    27: ifne            180
        //    30: aload_0        
        //    31: invokespecial   com/crittercism/internal/g.a:()J
        //    34: lconst_0       
        //    35: lcmp           
        //    36: ifle            46
        //    39: aload_0        
        //    40: invokespecial   com/crittercism/internal/g.a:()J
        //    43: invokestatic    java/lang/Thread.sleep:(J)V
        //    46: aload_0        
        //    47: invokestatic    java/lang/System.currentTimeMillis:()J
        //    50: putfield        com/crittercism/internal/g.e:J
        //    53: aload_0        
        //    54: invokespecial   com/crittercism/internal/g.b:()Ljava/net/HttpURLConnection;
        //    57: astore_2       
        //    58: aload_2        
        //    59: ifnonnull       74
        //    62: aload_0        
        //    63: iconst_1       
        //    64: putfield        com/crittercism/internal/g.h:Z
        //    67: ldc_w           "Disabling APM due to failure instantiating connection"
        //    70: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;)V
        //    73: return         
        //    74: aload_0        
        //    75: getfield        com/crittercism/internal/g.i:Ljava/lang/Object;
        //    78: astore_3       
        //    79: aload_3        
        //    80: monitorenter   
        //    81: aload_0        
        //    82: getfield        com/crittercism/internal/g.c:Ljava/util/List;
        //    85: astore          4
        //    87: aload_0        
        //    88: new             Ljava/util/LinkedList;
        //    91: dup            
        //    92: invokespecial   java/util/LinkedList.<init>:()V
        //    95: putfield        com/crittercism/internal/g.c:Ljava/util/List;
        //    98: aload_0        
        //    99: getfield        com/crittercism/internal/g.f:Landroid/os/ConditionVariable;
        //   102: invokevirtual   android/os/ConditionVariable.close:()V
        //   105: aload_3        
        //   106: monitorexit    
        //   107: aload_0        
        //   108: getfield        com/crittercism/internal/g.g:Lcom/crittercism/internal/ar;
        //   111: aload           4
        //   113: invokestatic    com/crittercism/internal/a.a:(Lcom/crittercism/internal/ar;Ljava/util/List;)Lcom/crittercism/internal/a;
        //   116: astore_3       
        //   117: aload_3        
        //   118: ifnonnull       164
        //   121: aload_0        
        //   122: iconst_1       
        //   123: putfield        com/crittercism/internal/g.h:Z
        //   126: ldc_w           "Disabling APM due to failure building request"
        //   129: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;)V
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
        //   166: getfield        com/crittercism/internal/a.a:Lorg/json/JSONObject;
        //   169: invokestatic    com/crittercism/internal/g.a:(Ljava/net/HttpURLConnection;Lorg/json/JSONObject;)Z
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
        //  160    162    159    164    Any
        //  162    164    133    159    Ljava/lang/Exception;
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
