// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import android.content.Intent;

public class zzdn$zza extends zzhq
{
    private final String zzF;
    private final zzip zzoL;
    private final String zzxn;
    private final String zzxo;
    private final int zzxp;
    
    public zzdn$zza(final zzip zzoL, final String zzF) {
        this.zzxn = "play.google.com";
        this.zzxo = "market";
        this.zzxp = 10;
        this.zzoL = zzoL;
        this.zzF = zzF;
    }
    
    public Intent zzY(final String s) {
        final Uri parse = Uri.parse(s);
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(parse);
        return intent;
    }
    
    @Override
    public void zzdG() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzdn$zza.zzF:Ljava/lang/String;
        //     6: astore          4
        //     8: iload_1        
        //     9: bipush          10
        //    11: if_icmpge       415
        //    14: new             Ljava/net/URL;
        //    17: dup            
        //    18: aload           4
        //    20: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    23: astore          5
        //    25: ldc             "play.google.com"
        //    27: aload           5
        //    29: invokevirtual   java/net/URL.getHost:()Ljava/lang/String;
        //    32: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    35: istore_3       
        //    36: iload_3        
        //    37: ifeq            63
        //    40: aload_0        
        //    41: aload           4
        //    43: invokevirtual   com/google/android/gms/internal/zzdn$zza.zzY:(Ljava/lang/String;)Landroid/content/Intent;
        //    46: astore          4
        //    48: aload_0        
        //    49: getfield        com/google/android/gms/internal/zzdn$zza.zzoL:Lcom/google/android/gms/internal/zzip;
        //    52: invokeinterface com/google/android/gms/internal/zzip.getContext:()Landroid/content/Context;
        //    57: aload           4
        //    59: invokevirtual   android/content/Context.startActivity:(Landroid/content/Intent;)V
        //    62: return         
        //    63: ldc             "market"
        //    65: aload           5
        //    67: invokevirtual   java/net/URL.getProtocol:()Ljava/lang/String;
        //    70: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    73: ifeq            79
        //    76: goto            40
        //    79: aload           5
        //    81: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    84: checkcast       Ljava/net/HttpURLConnection;
        //    87: astore          6
        //    89: invokestatic    com/google/android/gms/ads/internal/zzp.zzbx:()Lcom/google/android/gms/internal/zzhu;
        //    92: aload_0        
        //    93: getfield        com/google/android/gms/internal/zzdn$zza.zzoL:Lcom/google/android/gms/internal/zzip;
        //    96: invokeinterface com/google/android/gms/internal/zzip.getContext:()Landroid/content/Context;
        //   101: aload_0        
        //   102: getfield        com/google/android/gms/internal/zzdn$zza.zzoL:Lcom/google/android/gms/internal/zzip;
        //   105: invokeinterface com/google/android/gms/internal/zzip.zzgV:()Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
        //   110: getfield        com/google/android/gms/ads/internal/util/client/VersionInfoParcel.zzIz:Ljava/lang/String;
        //   113: iconst_0       
        //   114: aload           6
        //   116: invokevirtual   com/google/android/gms/internal/zzhu.zza:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //   119: aload           6
        //   121: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   124: istore_2       
        //   125: aload           6
        //   127: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   130: astore          7
        //   132: iload_2        
        //   133: sipush          300
        //   136: if_icmplt       408
        //   139: iload_2        
        //   140: sipush          399
        //   143: if_icmpgt       408
        //   146: aconst_null    
        //   147: astore          5
        //   149: aload           7
        //   151: ldc             "Location"
        //   153: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   158: ifeq            224
        //   161: aload           7
        //   163: ldc             "Location"
        //   165: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   170: checkcast       Ljava/util/List;
        //   173: astore          5
        //   175: aload           5
        //   177: ifnull          408
        //   180: aload           5
        //   182: invokeinterface java/util/List.size:()I
        //   187: ifle            408
        //   190: aload           5
        //   192: iconst_0       
        //   193: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   198: checkcast       Ljava/lang/String;
        //   201: astore          5
        //   203: aload           5
        //   205: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   208: ifeq            253
        //   211: ldc             "Arrived at landing page, this ideally should not happen. Will open it in browser."
        //   213: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaE:(Ljava/lang/String;)V
        //   216: aload           6
        //   218: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   221: goto            40
        //   224: aload           7
        //   226: ldc             "location"
        //   228: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   233: ifeq            175
        //   236: aload           7
        //   238: ldc             "location"
        //   240: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   245: checkcast       Ljava/util/List;
        //   248: astore          5
        //   250: goto            175
        //   253: aload           6
        //   255: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   258: iload_1        
        //   259: iconst_1       
        //   260: iadd           
        //   261: istore_1       
        //   262: aload           5
        //   264: astore          4
        //   266: goto            8
        //   269: astore          5
        //   271: aload           6
        //   273: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   276: aload           5
        //   278: athrow         
        //   279: astore          5
        //   281: new             Ljava/lang/StringBuilder;
        //   284: dup            
        //   285: invokespecial   java/lang/StringBuilder.<init>:()V
        //   288: ldc             "Error while parsing ping URL: "
        //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: aload           4
        //   295: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   298: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   301: aload           5
        //   303: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   306: goto            40
        //   309: astore          5
        //   311: new             Ljava/lang/StringBuilder;
        //   314: dup            
        //   315: invokespecial   java/lang/StringBuilder.<init>:()V
        //   318: ldc             "Error while pinging URL: "
        //   320: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: aload           4
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   331: aload           5
        //   333: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   336: goto            40
        //   339: astore          5
        //   341: new             Ljava/lang/StringBuilder;
        //   344: dup            
        //   345: invokespecial   java/lang/StringBuilder.<init>:()V
        //   348: ldc             "Error while pinging URL: "
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: aload           4
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   361: aload           5
        //   363: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   366: goto            40
        //   369: astore          6
        //   371: aload           5
        //   373: astore          4
        //   375: aload           6
        //   377: astore          5
        //   379: goto            341
        //   382: astore          6
        //   384: aload           5
        //   386: astore          4
        //   388: aload           6
        //   390: astore          5
        //   392: goto            311
        //   395: astore          6
        //   397: aload           5
        //   399: astore          4
        //   401: aload           6
        //   403: astore          5
        //   405: goto            281
        //   408: ldc             ""
        //   410: astore          5
        //   412: goto            203
        //   415: goto            40
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  14     36     279    281    Ljava/lang/IndexOutOfBoundsException;
        //  14     36     309    311    Ljava/io/IOException;
        //  14     36     339    341    Ljava/lang/RuntimeException;
        //  63     76     279    281    Ljava/lang/IndexOutOfBoundsException;
        //  63     76     309    311    Ljava/io/IOException;
        //  63     76     339    341    Ljava/lang/RuntimeException;
        //  79     89     279    281    Ljava/lang/IndexOutOfBoundsException;
        //  79     89     309    311    Ljava/io/IOException;
        //  79     89     339    341    Ljava/lang/RuntimeException;
        //  89     132    269    279    Any
        //  149    175    269    279    Any
        //  180    203    269    279    Any
        //  203    216    269    279    Any
        //  216    221    279    281    Ljava/lang/IndexOutOfBoundsException;
        //  216    221    309    311    Ljava/io/IOException;
        //  216    221    339    341    Ljava/lang/RuntimeException;
        //  224    250    269    279    Any
        //  253    258    395    408    Ljava/lang/IndexOutOfBoundsException;
        //  253    258    382    395    Ljava/io/IOException;
        //  253    258    369    382    Ljava/lang/RuntimeException;
        //  271    279    279    281    Ljava/lang/IndexOutOfBoundsException;
        //  271    279    309    311    Ljava/io/IOException;
        //  271    279    339    341    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0253:
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
