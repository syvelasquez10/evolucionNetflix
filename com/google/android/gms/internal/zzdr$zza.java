// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import android.content.Intent;

public class zzdr$zza extends zzhz
{
    private final String zzF;
    private final zziz zzoM;
    private final String zzxU;
    private final String zzxV;
    private final int zzxW;
    
    public zzdr$zza(final zziz zzoM, final String zzF) {
        this.zzxU = "play.google.com";
        this.zzxV = "market";
        this.zzxW = 10;
        this.zzoM = zzoM;
        this.zzF = zzF;
    }
    
    public Intent zzaa(final String s) {
        final Uri parse = Uri.parse(s);
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(parse);
        return intent;
    }
    
    @Override
    public void zzbn() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzdr$zza.zzF:Ljava/lang/String;
        //     6: astore          4
        //     8: iload_1        
        //     9: bipush          10
        //    11: if_icmpge       418
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
        //    37: ifeq            66
        //    40: aload_0        
        //    41: aload           4
        //    43: invokevirtual   com/google/android/gms/internal/zzdr$zza.zzaa:(Ljava/lang/String;)Landroid/content/Intent;
        //    46: astore          4
        //    48: invokestatic    com/google/android/gms/ads/internal/zzp.zzbv:()Lcom/google/android/gms/internal/zzid;
        //    51: aload_0        
        //    52: getfield        com/google/android/gms/internal/zzdr$zza.zzoM:Lcom/google/android/gms/internal/zziz;
        //    55: invokeinterface com/google/android/gms/internal/zziz.getContext:()Landroid/content/Context;
        //    60: aload           4
        //    62: invokevirtual   com/google/android/gms/internal/zzid.zzb:(Landroid/content/Context;Landroid/content/Intent;)V
        //    65: return         
        //    66: ldc             "market"
        //    68: aload           5
        //    70: invokevirtual   java/net/URL.getProtocol:()Ljava/lang/String;
        //    73: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    76: ifeq            82
        //    79: goto            40
        //    82: aload           5
        //    84: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    87: checkcast       Ljava/net/HttpURLConnection;
        //    90: astore          6
        //    92: invokestatic    com/google/android/gms/ads/internal/zzp.zzbv:()Lcom/google/android/gms/internal/zzid;
        //    95: aload_0        
        //    96: getfield        com/google/android/gms/internal/zzdr$zza.zzoM:Lcom/google/android/gms/internal/zziz;
        //    99: invokeinterface com/google/android/gms/internal/zziz.getContext:()Landroid/content/Context;
        //   104: aload_0        
        //   105: getfield        com/google/android/gms/internal/zzdr$zza.zzoM:Lcom/google/android/gms/internal/zziz;
        //   108: invokeinterface com/google/android/gms/internal/zziz.zzhh:()Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
        //   113: getfield        com/google/android/gms/ads/internal/util/client/VersionInfoParcel.zzJu:Ljava/lang/String;
        //   116: iconst_0       
        //   117: aload           6
        //   119: invokevirtual   com/google/android/gms/internal/zzid.zza:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //   122: aload           6
        //   124: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   127: istore_2       
        //   128: aload           6
        //   130: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   133: astore          7
        //   135: iload_2        
        //   136: sipush          300
        //   139: if_icmplt       411
        //   142: iload_2        
        //   143: sipush          399
        //   146: if_icmpgt       411
        //   149: aconst_null    
        //   150: astore          5
        //   152: aload           7
        //   154: ldc             "Location"
        //   156: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   161: ifeq            227
        //   164: aload           7
        //   166: ldc             "Location"
        //   168: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   173: checkcast       Ljava/util/List;
        //   176: astore          5
        //   178: aload           5
        //   180: ifnull          411
        //   183: aload           5
        //   185: invokeinterface java/util/List.size:()I
        //   190: ifle            411
        //   193: aload           5
        //   195: iconst_0       
        //   196: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   201: checkcast       Ljava/lang/String;
        //   204: astore          5
        //   206: aload           5
        //   208: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   211: ifeq            256
        //   214: ldc             "Arrived at landing page, this ideally should not happen. Will open it in browser."
        //   216: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaH:(Ljava/lang/String;)V
        //   219: aload           6
        //   221: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   224: goto            40
        //   227: aload           7
        //   229: ldc             "location"
        //   231: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   236: ifeq            178
        //   239: aload           7
        //   241: ldc             "location"
        //   243: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   248: checkcast       Ljava/util/List;
        //   251: astore          5
        //   253: goto            178
        //   256: aload           6
        //   258: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   261: iload_1        
        //   262: iconst_1       
        //   263: iadd           
        //   264: istore_1       
        //   265: aload           5
        //   267: astore          4
        //   269: goto            8
        //   272: astore          5
        //   274: aload           6
        //   276: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   279: aload           5
        //   281: athrow         
        //   282: astore          5
        //   284: new             Ljava/lang/StringBuilder;
        //   287: dup            
        //   288: invokespecial   java/lang/StringBuilder.<init>:()V
        //   291: ldc             "Error while parsing ping URL: "
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: aload           4
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   301: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   304: aload           5
        //   306: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   309: goto            40
        //   312: astore          5
        //   314: new             Ljava/lang/StringBuilder;
        //   317: dup            
        //   318: invokespecial   java/lang/StringBuilder.<init>:()V
        //   321: ldc             "Error while pinging URL: "
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: aload           4
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   334: aload           5
        //   336: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   339: goto            40
        //   342: astore          5
        //   344: new             Ljava/lang/StringBuilder;
        //   347: dup            
        //   348: invokespecial   java/lang/StringBuilder.<init>:()V
        //   351: ldc             "Error while pinging URL: "
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: aload           4
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   364: aload           5
        //   366: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   369: goto            40
        //   372: astore          6
        //   374: aload           5
        //   376: astore          4
        //   378: aload           6
        //   380: astore          5
        //   382: goto            344
        //   385: astore          6
        //   387: aload           5
        //   389: astore          4
        //   391: aload           6
        //   393: astore          5
        //   395: goto            314
        //   398: astore          6
        //   400: aload           5
        //   402: astore          4
        //   404: aload           6
        //   406: astore          5
        //   408: goto            284
        //   411: ldc             ""
        //   413: astore          5
        //   415: goto            206
        //   418: goto            40
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  14     36     282    284    Ljava/lang/IndexOutOfBoundsException;
        //  14     36     312    314    Ljava/io/IOException;
        //  14     36     342    344    Ljava/lang/RuntimeException;
        //  66     79     282    284    Ljava/lang/IndexOutOfBoundsException;
        //  66     79     312    314    Ljava/io/IOException;
        //  66     79     342    344    Ljava/lang/RuntimeException;
        //  82     92     282    284    Ljava/lang/IndexOutOfBoundsException;
        //  82     92     312    314    Ljava/io/IOException;
        //  82     92     342    344    Ljava/lang/RuntimeException;
        //  92     135    272    282    Any
        //  152    178    272    282    Any
        //  183    206    272    282    Any
        //  206    219    272    282    Any
        //  219    224    282    284    Ljava/lang/IndexOutOfBoundsException;
        //  219    224    312    314    Ljava/io/IOException;
        //  219    224    342    344    Ljava/lang/RuntimeException;
        //  227    253    272    282    Any
        //  256    261    398    411    Ljava/lang/IndexOutOfBoundsException;
        //  256    261    385    398    Ljava/io/IOException;
        //  256    261    372    385    Ljava/lang/RuntimeException;
        //  274    282    282    284    Ljava/lang/IndexOutOfBoundsException;
        //  274    282    312    314    Ljava/io/IOException;
        //  274    282    342    344    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0256:
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
