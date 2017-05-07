// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.location.Location;
import java.util.concurrent.TimeUnit;
import android.content.Context;

@ez
public final class fr extends fm.a
{
    private static final Object uf;
    private static fr ug;
    private final Context mContext;
    private final fx uh;
    private final ci ui;
    private final bm uj;
    
    static {
        uf = new Object();
    }
    
    fr(final Context mContext, final bm uj, final ci ui, final fx uh) {
        this.mContext = mContext;
        this.uh = uh;
        this.ui = ui;
        this.uj = uj;
    }
    
    private static gw.a I(final String s) {
        return new gw.a() {
            @Override
            public void a(final gv gv) {
                final String format = String.format("javascript:%s(%s);", "AFMA_buildAdURL", s);
                gs.V("About to execute: " + format);
                gv.loadUrl(format);
            }
        };
    }
    
    private static fk a(final Context context, final bm bm, final ci ci, final fx fx, final fi fi) {
        gs.S("Starting ad request from service.");
        ci.init();
        final fw fw = new fw(context);
        if (fw.vd == -1) {
            gs.S("Device is offline.");
            return new fk(2);
        }
        final ft ft = new ft(fi.applicationInfo.packageName);
        if (fi.tx.extras != null) {
            final String string = fi.tx.extras.getString("_ad");
            if (string != null) {
                return fs.a(context, fi, string);
            }
        }
        final Location a = ci.a(250L);
        final String bp = bm.bp();
        final String a2 = fs.a(fi, fw, a, bm.bq(), bm.br());
        if (a2 == null) {
            return new fk(0);
        }
        gr.wC.post((Runnable)new Runnable() {
            final /* synthetic */ gw.a um = I(a2);
            
            @Override
            public void run() {
                final gv a = gv.a(context, new ay(), false, false, null, fi.lD);
                a.setWillNotDraw(true);
                ft.b(a);
                final gw dv = a.dv();
                dv.a("/invalidRequest", ft.us);
                dv.a("/loadAdURL", ft.ut);
                dv.a("/log", bx.pG);
                dv.a(this.um);
                gs.S("Loading the JS library.");
                a.loadUrl(bp);
            }
        });
        fv fv;
        try {
            fv = ft.cL().get(10L, TimeUnit.SECONDS);
            if (fv == null) {
                return new fk(0);
            }
        }
        catch (Exception ex) {
            return new fk(0);
        }
        if (fv.getErrorCode() != -2) {
            return new fk(fv.getErrorCode());
        }
        String k = null;
        if (fv.cO()) {
            k = fx.K(fi.ty.packageName);
        }
        return a(context, fi.lD.wD, fv.getUrl(), k, fv);
    }
    
    public static fk a(final Context p0, final String p1, final String p2, final String p3, final fv p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/google/android/gms/internal/fu;
        //     3: dup            
        //     4: invokespecial   com/google/android/gms/internal/fu.<init>:()V
        //     7: astore          10
        //     9: new             Ljava/lang/StringBuilder;
        //    12: dup            
        //    13: invokespecial   java/lang/StringBuilder.<init>:()V
        //    16: ldc             "AdRequestServiceImpl: Sending request: "
        //    18: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    21: aload_2        
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    28: invokestatic    com/google/android/gms/internal/gs.S:(Ljava/lang/String;)V
        //    31: new             Ljava/net/URL;
        //    34: dup            
        //    35: aload_2        
        //    36: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    39: astore_2       
        //    40: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    43: lstore          7
        //    45: iconst_0       
        //    46: istore          5
        //    48: aload_2        
        //    49: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    52: checkcast       Ljava/net/HttpURLConnection;
        //    55: astore          9
        //    57: aload_0        
        //    58: aload_1        
        //    59: iconst_0       
        //    60: aload           9
        //    62: invokestatic    com/google/android/gms/internal/gj.a:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //    65: aload_3        
        //    66: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    69: ifne            80
        //    72: aload           9
        //    74: ldc             "x-afma-drt-cookie"
        //    76: aload_3        
        //    77: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    80: aload           4
        //    82: ifnull          146
        //    85: aload           4
        //    87: invokevirtual   com/google/android/gms/internal/fv.cN:()Ljava/lang/String;
        //    90: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    93: ifne            146
        //    96: aload           9
        //    98: iconst_1       
        //    99: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   102: aload           4
        //   104: invokevirtual   com/google/android/gms/internal/fv.cN:()Ljava/lang/String;
        //   107: invokevirtual   java/lang/String.getBytes:()[B
        //   110: astore          11
        //   112: aload           9
        //   114: aload           11
        //   116: arraylength    
        //   117: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   120: new             Ljava/io/BufferedOutputStream;
        //   123: dup            
        //   124: aload           9
        //   126: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   129: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   132: astore          12
        //   134: aload           12
        //   136: aload           11
        //   138: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //   141: aload           12
        //   143: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   146: aload           9
        //   148: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   151: istore          6
        //   153: aload           9
        //   155: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   158: astore          11
        //   160: iload           6
        //   162: sipush          200
        //   165: if_icmplt       230
        //   168: iload           6
        //   170: sipush          300
        //   173: if_icmpge       230
        //   176: aload_2        
        //   177: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   180: astore_0       
        //   181: new             Ljava/io/InputStreamReader;
        //   184: dup            
        //   185: aload           9
        //   187: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   190: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   193: invokestatic    com/google/android/gms/internal/gj.a:(Ljava/lang/Readable;)Ljava/lang/String;
        //   196: astore_1       
        //   197: aload_0        
        //   198: aload           11
        //   200: aload_1        
        //   201: iload           6
        //   203: invokestatic    com/google/android/gms/internal/fr.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   206: aload           10
        //   208: aload_0        
        //   209: aload           11
        //   211: aload_1        
        //   212: invokevirtual   com/google/android/gms/internal/fu.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
        //   215: aload           10
        //   217: lload           7
        //   219: invokevirtual   com/google/android/gms/internal/fu.i:(J)Lcom/google/android/gms/internal/fk;
        //   222: astore_0       
        //   223: aload           9
        //   225: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   228: aload_0        
        //   229: areturn        
        //   230: aload_2        
        //   231: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   234: aload           11
        //   236: aconst_null    
        //   237: iload           6
        //   239: invokestatic    com/google/android/gms/internal/fr.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   242: iload           6
        //   244: sipush          300
        //   247: if_icmplt       339
        //   250: iload           6
        //   252: sipush          400
        //   255: if_icmpge       339
        //   258: aload           9
        //   260: ldc_w           "Location"
        //   263: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   266: astore_2       
        //   267: aload_2        
        //   268: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   271: ifeq            296
        //   274: ldc_w           "No location header to follow redirect."
        //   277: invokestatic    com/google/android/gms/internal/gs.W:(Ljava/lang/String;)V
        //   280: new             Lcom/google/android/gms/internal/fk;
        //   283: dup            
        //   284: iconst_0       
        //   285: invokespecial   com/google/android/gms/internal/fk.<init>:(I)V
        //   288: astore_0       
        //   289: aload           9
        //   291: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   294: aload_0        
        //   295: areturn        
        //   296: new             Ljava/net/URL;
        //   299: dup            
        //   300: aload_2        
        //   301: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   304: astore_2       
        //   305: iload           5
        //   307: iconst_1       
        //   308: iadd           
        //   309: istore          5
        //   311: iload           5
        //   313: iconst_5       
        //   314: if_icmple       379
        //   317: ldc_w           "Too many redirects."
        //   320: invokestatic    com/google/android/gms/internal/gs.W:(Ljava/lang/String;)V
        //   323: new             Lcom/google/android/gms/internal/fk;
        //   326: dup            
        //   327: iconst_0       
        //   328: invokespecial   com/google/android/gms/internal/fk.<init>:(I)V
        //   331: astore_0       
        //   332: aload           9
        //   334: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   337: aload_0        
        //   338: areturn        
        //   339: new             Ljava/lang/StringBuilder;
        //   342: dup            
        //   343: invokespecial   java/lang/StringBuilder.<init>:()V
        //   346: ldc_w           "Received error HTTP response code: "
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: iload           6
        //   354: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   357: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   360: invokestatic    com/google/android/gms/internal/gs.W:(Ljava/lang/String;)V
        //   363: new             Lcom/google/android/gms/internal/fk;
        //   366: dup            
        //   367: iconst_0       
        //   368: invokespecial   com/google/android/gms/internal/fk.<init>:(I)V
        //   371: astore_0       
        //   372: aload           9
        //   374: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   377: aload_0        
        //   378: areturn        
        //   379: aload           10
        //   381: aload           11
        //   383: invokevirtual   com/google/android/gms/internal/fu.e:(Ljava/util/Map;)V
        //   386: aload           9
        //   388: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   391: goto            48
        //   394: astore_0       
        //   395: new             Ljava/lang/StringBuilder;
        //   398: dup            
        //   399: invokespecial   java/lang/StringBuilder.<init>:()V
        //   402: ldc_w           "Error while connecting to ad server: "
        //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   408: aload_0        
        //   409: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   415: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   418: invokestatic    com/google/android/gms/internal/gs.W:(Ljava/lang/String;)V
        //   421: new             Lcom/google/android/gms/internal/fk;
        //   424: dup            
        //   425: iconst_2       
        //   426: invokespecial   com/google/android/gms/internal/fk.<init>:(I)V
        //   429: areturn        
        //   430: astore_0       
        //   431: aload           9
        //   433: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   436: aload_0        
        //   437: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      45     394    430    Ljava/io/IOException;
        //  48     57     394    430    Ljava/io/IOException;
        //  57     80     430    438    Any
        //  85     146    430    438    Any
        //  146    160    430    438    Any
        //  176    223    430    438    Any
        //  223    228    394    430    Ljava/io/IOException;
        //  230    242    430    438    Any
        //  258    289    430    438    Any
        //  289    294    394    430    Ljava/io/IOException;
        //  296    305    430    438    Any
        //  317    332    430    438    Any
        //  332    337    394    430    Ljava/io/IOException;
        //  339    372    430    438    Any
        //  372    377    394    430    Ljava/io/IOException;
        //  379    386    430    438    Any
        //  386    391    394    430    Ljava/io/IOException;
        //  431    438    394    430    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0080:
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
    
    public static fr a(final Context context, final bm bm, final ci ci, final fx fx) {
        synchronized (fr.uf) {
            if (fr.ug == null) {
                fr.ug = new fr(context.getApplicationContext(), bm, ci, fx);
            }
            return fr.ug;
        }
    }
    
    private static void a(final String s, final Map<String, List<String>> map, final String s2, final int n) {
        if (gs.u(2)) {
            gs.V("Http Response: {\n  URL:\n    " + s + "\n  Headers:");
            if (map != null) {
                for (final String s3 : map.keySet()) {
                    gs.V("    " + s3 + ":");
                    final Iterator<String> iterator2 = map.get(s3).iterator();
                    while (iterator2.hasNext()) {
                        gs.V("      " + iterator2.next());
                    }
                }
            }
            gs.V("  Body:");
            if (s2 != null) {
                for (int i = 0; i < Math.min(s2.length(), 100000); i += 1000) {
                    gs.V(s2.substring(i, Math.min(s2.length(), i + 1000)));
                }
            }
            else {
                gs.V("    null");
            }
            gs.V("  Response Code:\n    " + n + "\n}");
        }
    }
    
    public fk b(final fi fi) {
        return a(this.mContext, this.uj, this.ui, this.uh, fi);
    }
}
