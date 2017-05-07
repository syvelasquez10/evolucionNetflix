// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.text.TextUtils;
import android.content.Context;

public final class ce extends cd.a
{
    private static final Object hC;
    private static ce hD;
    private final aq hE;
    private final Context mContext;
    
    static {
        hC = new Object();
    }
    
    private ce(final Context mContext, final aq he) {
        this.mContext = mContext;
        this.hE = he;
    }
    
    private static cb a(final Context context, final aq aq, final bz bz) {
        ct.r("Starting ad request from service.");
        aq.init();
        final ci ci = new ci(context);
        if (ci.ik == -1) {
            ct.r("Device is offline.");
            return new cb(2);
        }
        final cg cg = new cg(bz.applicationInfo.packageName);
        if (bz.hr.extras != null) {
            final String string = bz.hr.extras.getString("_ad");
            if (string != null) {
                return cf.a(context, bz, string);
            }
        }
        final String a = cf.a(bz, ci, aq.a(250L));
        if (a == null) {
            return new cb(0);
        }
        cs.iI.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final cw a = cw.a(context, new x(), false, false, null, bz.ej);
                a.setWillNotDraw(true);
                cg.b(a);
                final cx ac = a.aC();
                ac.a("/invalidRequest", cg.hM);
                ac.a("/loadAdURL", cg.hN);
                ac.a("/log", am.fs);
                ct.r("Getting the ad request URL.");
                a.loadDataWithBaseURL("http://googleads.g.doubleclick.net", "<!DOCTYPE html><html><head><script src=\"http://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.js\"></script><script>AFMA_buildAdURL(" + a + ");</script></head><body></body></html>", "text/html", "UTF-8", (String)null);
            }
        });
        final String ap = cg.ap();
        if (TextUtils.isEmpty((CharSequence)ap)) {
            return new cb(cg.getErrorCode());
        }
        return a(context, bz.ej.iJ, ap);
    }
    
    public static cb a(final Context p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/google/android/gms/internal/ch;
        //     3: dup            
        //     4: invokespecial   com/google/android/gms/internal/ch.<init>:()V
        //     7: astore          6
        //     9: new             Ljava/net/URL;
        //    12: dup            
        //    13: aload_2        
        //    14: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    17: astore_2       
        //    18: iconst_0       
        //    19: istore_3       
        //    20: aload_2        
        //    21: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    24: checkcast       Ljava/net/HttpURLConnection;
        //    27: astore          5
        //    29: aload_0        
        //    30: aload_1        
        //    31: iconst_0       
        //    32: aload           5
        //    34: invokestatic    com/google/android/gms/internal/co.a:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //    37: aload           5
        //    39: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    42: istore          4
        //    44: aload           5
        //    46: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //    49: astore          7
        //    51: iload           4
        //    53: sipush          200
        //    56: if_icmplt       119
        //    59: iload           4
        //    61: sipush          300
        //    64: if_icmpge       119
        //    67: aload_2        
        //    68: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //    71: astore_0       
        //    72: new             Ljava/io/InputStreamReader;
        //    75: dup            
        //    76: aload           5
        //    78: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //    81: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    84: invokestatic    com/google/android/gms/internal/co.a:(Ljava/lang/Readable;)Ljava/lang/String;
        //    87: astore_1       
        //    88: aload_0        
        //    89: aload           7
        //    91: aload_1        
        //    92: iload           4
        //    94: invokestatic    com/google/android/gms/internal/ce.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //    97: aload           6
        //    99: aload_0        
        //   100: aload           7
        //   102: aload_1        
        //   103: invokevirtual   com/google/android/gms/internal/ch.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
        //   106: aload           6
        //   108: invokevirtual   com/google/android/gms/internal/ch.aq:()Lcom/google/android/gms/internal/cb;
        //   111: astore_0       
        //   112: aload           5
        //   114: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   117: aload_0        
        //   118: areturn        
        //   119: aload_2        
        //   120: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   123: aload           7
        //   125: aconst_null    
        //   126: iload           4
        //   128: invokestatic    com/google/android/gms/internal/ce.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   131: iload           4
        //   133: sipush          300
        //   136: if_icmplt       222
        //   139: iload           4
        //   141: sipush          400
        //   144: if_icmpge       222
        //   147: aload           5
        //   149: ldc             "Location"
        //   151: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   154: astore_2       
        //   155: aload_2        
        //   156: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   159: ifeq            183
        //   162: ldc             "No location header to follow redirect."
        //   164: invokestatic    com/google/android/gms/internal/ct.v:(Ljava/lang/String;)V
        //   167: new             Lcom/google/android/gms/internal/cb;
        //   170: dup            
        //   171: iconst_0       
        //   172: invokespecial   com/google/android/gms/internal/cb.<init>:(I)V
        //   175: astore_0       
        //   176: aload           5
        //   178: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   181: aload_0        
        //   182: areturn        
        //   183: new             Ljava/net/URL;
        //   186: dup            
        //   187: aload_2        
        //   188: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   191: astore_2       
        //   192: iload_3        
        //   193: iconst_1       
        //   194: iadd           
        //   195: istore_3       
        //   196: iload_3        
        //   197: iconst_5       
        //   198: if_icmple       261
        //   201: ldc             "Too many redirects."
        //   203: invokestatic    com/google/android/gms/internal/ct.v:(Ljava/lang/String;)V
        //   206: new             Lcom/google/android/gms/internal/cb;
        //   209: dup            
        //   210: iconst_0       
        //   211: invokespecial   com/google/android/gms/internal/cb.<init>:(I)V
        //   214: astore_0       
        //   215: aload           5
        //   217: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   220: aload_0        
        //   221: areturn        
        //   222: new             Ljava/lang/StringBuilder;
        //   225: dup            
        //   226: invokespecial   java/lang/StringBuilder.<init>:()V
        //   229: ldc             "Received error HTTP response code: "
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   234: iload           4
        //   236: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   239: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   242: invokestatic    com/google/android/gms/internal/ct.v:(Ljava/lang/String;)V
        //   245: new             Lcom/google/android/gms/internal/cb;
        //   248: dup            
        //   249: iconst_0       
        //   250: invokespecial   com/google/android/gms/internal/cb.<init>:(I)V
        //   253: astore_0       
        //   254: aload           5
        //   256: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   259: aload_0        
        //   260: areturn        
        //   261: aload           6
        //   263: aload           7
        //   265: invokevirtual   com/google/android/gms/internal/ch.d:(Ljava/util/Map;)V
        //   268: aload           5
        //   270: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   273: goto            20
        //   276: astore_0       
        //   277: new             Ljava/lang/StringBuilder;
        //   280: dup            
        //   281: invokespecial   java/lang/StringBuilder.<init>:()V
        //   284: ldc             "Error while connecting to ad server: "
        //   286: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   289: aload_0        
        //   290: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   299: invokestatic    com/google/android/gms/internal/ct.v:(Ljava/lang/String;)V
        //   302: new             Lcom/google/android/gms/internal/cb;
        //   305: dup            
        //   306: iconst_2       
        //   307: invokespecial   com/google/android/gms/internal/cb.<init>:(I)V
        //   310: areturn        
        //   311: astore_0       
        //   312: aload           5
        //   314: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   317: aload_0        
        //   318: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      18     276    311    Ljava/io/IOException;
        //  20     29     276    311    Ljava/io/IOException;
        //  29     51     311    319    Any
        //  67     112    311    319    Any
        //  112    117    276    311    Ljava/io/IOException;
        //  119    131    311    319    Any
        //  147    176    311    319    Any
        //  176    181    276    311    Ljava/io/IOException;
        //  183    192    311    319    Any
        //  201    215    311    319    Any
        //  215    220    276    311    Ljava/io/IOException;
        //  222    254    311    319    Any
        //  254    259    276    311    Ljava/io/IOException;
        //  261    268    311    319    Any
        //  268    273    276    311    Ljava/io/IOException;
        //  312    319    276    311    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119:
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
    
    public static ce a(final Context context, final aq aq) {
        synchronized (ce.hC) {
            if (ce.hD == null) {
                ce.hD = new ce(context.getApplicationContext(), aq);
            }
            return ce.hD;
        }
    }
    
    private static void a(final String s, final Map<String, List<String>> map, final String s2, final int n) {
        if (ct.n(2)) {
            ct.u("Http Response: {\n  URL:\n    " + s + "\n  Headers:");
            if (map != null) {
                for (final String s3 : map.keySet()) {
                    ct.u("    " + s3 + ":");
                    final Iterator<String> iterator2 = map.get(s3).iterator();
                    while (iterator2.hasNext()) {
                        ct.u("      " + iterator2.next());
                    }
                }
            }
            ct.u("  Body:");
            if (s2 != null) {
                for (int i = 0; i < Math.min(s2.length(), 100000); i += 1000) {
                    ct.u(s2.substring(i, Math.min(s2.length(), i + 1000)));
                }
            }
            else {
                ct.u("    null");
            }
            ct.u("  Response Code:\n    " + n + "\n}");
        }
    }
    
    public cb b(final bz bz) {
        return a(this.mContext, this.hE, bz);
    }
}
