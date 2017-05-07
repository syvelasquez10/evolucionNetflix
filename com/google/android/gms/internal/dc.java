// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.location.Location;
import android.text.TextUtils;
import android.content.Context;

public final class dc extends db.a
{
    private static final Object px;
    private static dc py;
    private final Context mContext;
    private final ax pA;
    private final bf pz;
    
    static {
        px = new Object();
    }
    
    private dc(final Context mContext, final ax pa, final bf pz) {
        this.mContext = mContext;
        this.pz = pz;
        this.pA = pa;
    }
    
    private static cz a(final Context context, final ax ax, final bf bf, final cx cx) {
        dw.v("Starting ad request from service.");
        bf.init();
        final dg dg = new dg(context);
        if (dg.qk == -1) {
            dw.v("Device is offline.");
            return new cz(2);
        }
        final de de = new de(cx.applicationInfo.packageName);
        if (cx.pg.extras != null) {
            final String string = cx.pg.extras.getString("_ad");
            if (string != null) {
                return dd.a(context, cx, string);
            }
        }
        final Location a = bf.a(250L);
        final String ah = ax.aH();
        final String a2 = dd.a(cx, dg, a, ax.aI());
        if (a2 == null) {
            return new cz(0);
        }
        dv.rp.post((Runnable)new Runnable() {
            final /* synthetic */ ea.a pE = p(a2);
            
            @Override
            public void run() {
                final dz a = dz.a(context, new ak(), false, false, null, cx.kK);
                a.setWillNotDraw(true);
                de.b(a);
                final ea bi = a.bI();
                bi.a("/invalidRequest", de.pK);
                bi.a("/loadAdURL", de.pL);
                bi.a("/log", ba.mM);
                bi.a(this.pE);
                dw.v("Loading the JS library.");
                a.loadUrl(ah);
            }
        });
        final String bj = de.bj();
        if (TextUtils.isEmpty((CharSequence)bj)) {
            return new cz(de.getErrorCode());
        }
        return a(context, cx.kK.rq, bj);
    }
    
    public static cz a(final Context p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/google/android/gms/internal/df;
        //     3: dup            
        //     4: invokespecial   com/google/android/gms/internal/df.<init>:()V
        //     7: astore          8
        //     9: new             Ljava/net/URL;
        //    12: dup            
        //    13: aload_2        
        //    14: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    17: astore_2       
        //    18: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    21: lstore          5
        //    23: iconst_0       
        //    24: istore_3       
        //    25: aload_2        
        //    26: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    29: checkcast       Ljava/net/HttpURLConnection;
        //    32: astore          7
        //    34: aload_0        
        //    35: aload_1        
        //    36: iconst_0       
        //    37: aload           7
        //    39: invokestatic    com/google/android/gms/internal/dq.a:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //    42: aload           7
        //    44: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    47: istore          4
        //    49: aload           7
        //    51: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //    54: astore          9
        //    56: iload           4
        //    58: sipush          200
        //    61: if_icmplt       126
        //    64: iload           4
        //    66: sipush          300
        //    69: if_icmpge       126
        //    72: aload_2        
        //    73: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //    76: astore_0       
        //    77: new             Ljava/io/InputStreamReader;
        //    80: dup            
        //    81: aload           7
        //    83: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //    86: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    89: invokestatic    com/google/android/gms/internal/dq.a:(Ljava/lang/Readable;)Ljava/lang/String;
        //    92: astore_1       
        //    93: aload_0        
        //    94: aload           9
        //    96: aload_1        
        //    97: iload           4
        //    99: invokestatic    com/google/android/gms/internal/dc.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   102: aload           8
        //   104: aload_0        
        //   105: aload           9
        //   107: aload_1        
        //   108: invokevirtual   com/google/android/gms/internal/df.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
        //   111: aload           8
        //   113: lload           5
        //   115: invokevirtual   com/google/android/gms/internal/df.g:(J)Lcom/google/android/gms/internal/cz;
        //   118: astore_0       
        //   119: aload           7
        //   121: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   124: aload_0        
        //   125: areturn        
        //   126: aload_2        
        //   127: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   130: aload           9
        //   132: aconst_null    
        //   133: iload           4
        //   135: invokestatic    com/google/android/gms/internal/dc.a:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   138: iload           4
        //   140: sipush          300
        //   143: if_icmplt       229
        //   146: iload           4
        //   148: sipush          400
        //   151: if_icmpge       229
        //   154: aload           7
        //   156: ldc             "Location"
        //   158: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   161: astore_2       
        //   162: aload_2        
        //   163: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   166: ifeq            190
        //   169: ldc             "No location header to follow redirect."
        //   171: invokestatic    com/google/android/gms/internal/dw.z:(Ljava/lang/String;)V
        //   174: new             Lcom/google/android/gms/internal/cz;
        //   177: dup            
        //   178: iconst_0       
        //   179: invokespecial   com/google/android/gms/internal/cz.<init>:(I)V
        //   182: astore_0       
        //   183: aload           7
        //   185: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   188: aload_0        
        //   189: areturn        
        //   190: new             Ljava/net/URL;
        //   193: dup            
        //   194: aload_2        
        //   195: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   198: astore_2       
        //   199: iload_3        
        //   200: iconst_1       
        //   201: iadd           
        //   202: istore_3       
        //   203: iload_3        
        //   204: iconst_5       
        //   205: if_icmple       268
        //   208: ldc             "Too many redirects."
        //   210: invokestatic    com/google/android/gms/internal/dw.z:(Ljava/lang/String;)V
        //   213: new             Lcom/google/android/gms/internal/cz;
        //   216: dup            
        //   217: iconst_0       
        //   218: invokespecial   com/google/android/gms/internal/cz.<init>:(I)V
        //   221: astore_0       
        //   222: aload           7
        //   224: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   227: aload_0        
        //   228: areturn        
        //   229: new             Ljava/lang/StringBuilder;
        //   232: dup            
        //   233: invokespecial   java/lang/StringBuilder.<init>:()V
        //   236: ldc             "Received error HTTP response code: "
        //   238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: iload           4
        //   243: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   246: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   249: invokestatic    com/google/android/gms/internal/dw.z:(Ljava/lang/String;)V
        //   252: new             Lcom/google/android/gms/internal/cz;
        //   255: dup            
        //   256: iconst_0       
        //   257: invokespecial   com/google/android/gms/internal/cz.<init>:(I)V
        //   260: astore_0       
        //   261: aload           7
        //   263: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   266: aload_0        
        //   267: areturn        
        //   268: aload           8
        //   270: aload           9
        //   272: invokevirtual   com/google/android/gms/internal/df.e:(Ljava/util/Map;)V
        //   275: aload           7
        //   277: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   280: goto            25
        //   283: astore_0       
        //   284: new             Ljava/lang/StringBuilder;
        //   287: dup            
        //   288: invokespecial   java/lang/StringBuilder.<init>:()V
        //   291: ldc_w           "Error while connecting to ad server: "
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: aload_0        
        //   298: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   307: invokestatic    com/google/android/gms/internal/dw.z:(Ljava/lang/String;)V
        //   310: new             Lcom/google/android/gms/internal/cz;
        //   313: dup            
        //   314: iconst_2       
        //   315: invokespecial   com/google/android/gms/internal/cz.<init>:(I)V
        //   318: areturn        
        //   319: astore_0       
        //   320: aload           7
        //   322: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   325: aload_0        
        //   326: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      23     283    319    Ljava/io/IOException;
        //  25     34     283    319    Ljava/io/IOException;
        //  34     56     319    327    Any
        //  72     119    319    327    Any
        //  119    124    283    319    Ljava/io/IOException;
        //  126    138    319    327    Any
        //  154    183    319    327    Any
        //  183    188    283    319    Ljava/io/IOException;
        //  190    199    319    327    Any
        //  208    222    319    327    Any
        //  222    227    283    319    Ljava/io/IOException;
        //  229    261    319    327    Any
        //  261    266    283    319    Ljava/io/IOException;
        //  268    275    319    327    Any
        //  275    280    283    319    Ljava/io/IOException;
        //  320    327    283    319    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    public static dc a(final Context context, final ax ax, final bf bf) {
        synchronized (dc.px) {
            if (dc.py == null) {
                dc.py = new dc(context.getApplicationContext(), ax, bf);
            }
            return dc.py;
        }
    }
    
    private static void a(final String s, final Map<String, List<String>> map, final String s2, final int n) {
        if (dw.n(2)) {
            dw.y("Http Response: {\n  URL:\n    " + s + "\n  Headers:");
            if (map != null) {
                for (final String s3 : map.keySet()) {
                    dw.y("    " + s3 + ":");
                    final Iterator<String> iterator2 = map.get(s3).iterator();
                    while (iterator2.hasNext()) {
                        dw.y("      " + iterator2.next());
                    }
                }
            }
            dw.y("  Body:");
            if (s2 != null) {
                for (int i = 0; i < Math.min(s2.length(), 100000); i += 1000) {
                    dw.y(s2.substring(i, Math.min(s2.length(), i + 1000)));
                }
            }
            else {
                dw.y("    null");
            }
            dw.y("  Response Code:\n    " + n + "\n}");
        }
    }
    
    private static ea.a p(final String s) {
        return new ea.a() {
            @Override
            public void a(final dz dz) {
                final String format = String.format("javascript:%s(%s);", "AFMA_buildAdURL", s);
                dw.y("About to execute: " + format);
                dz.loadUrl(format);
            }
        };
    }
    
    public cz b(final cx cx) {
        return a(this.mContext, this.pA, this.pz, cx);
    }
}
