// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.apache.http.HttpEntity;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Date;
import java.io.Serializable;
import org.apache.http.StatusLine;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
import org.apache.http.Header;

public class zzt implements zzf
{
    protected static final boolean DEBUG;
    private static int zzao;
    private static int zzap;
    protected final zzy zzaq;
    protected final zzu zzar;
    
    static {
        DEBUG = zzs.DEBUG;
        zzt.zzao = 3000;
        zzt.zzap = 4096;
    }
    
    public zzt(final zzy zzy) {
        this(zzy, new zzu(zzt.zzap));
    }
    
    public zzt(final zzy zzaq, final zzu zzar) {
        this.zzaq = zzaq;
        this.zzar = zzar;
    }
    
    protected static Map<String, String> zza(final Header[] array) {
        final TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < array.length; ++i) {
            treeMap.put(array[i].getName(), array[i].getValue());
        }
        return treeMap;
    }
    
    private void zza(final long n, final zzk<?> zzk, final byte[] array, final StatusLine statusLine) {
        if (zzt.DEBUG || n > zzt.zzao) {
            Serializable value;
            if (array != null) {
                value = array.length;
            }
            else {
                value = "null";
            }
            zzs.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", zzk, n, value, statusLine.getStatusCode(), zzk.zzu().zze());
        }
    }
    
    private static void zza(final String s, final zzk<?> zzk, final zzr zzr) {
        final zzo zzu = zzk.zzu();
        final int zzt = zzk.zzt();
        try {
            zzu.zza(zzr);
            zzk.zzc(String.format("%s-retry [timeout=%s]", s, zzt));
        }
        catch (zzr zzr) {
            zzk.zzc(String.format("%s-timeout-giveup [timeout=%s]", s, zzt));
            throw zzr;
        }
    }
    
    private void zza(final Map<String, String> map, final zzb$zza zzb$zza) {
        if (zzb$zza != null) {
            if (zzb$zza.zzb != null) {
                map.put("If-None-Match", zzb$zza.zzb);
            }
            if (zzb$zza.zzd > 0L) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(zzb$zza.zzd)));
            }
        }
    }
    
    private byte[] zza(final HttpEntity p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/google/android/gms/internal/zzaa;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/internal/zzt.zzar:Lcom/google/android/gms/internal/zzu;
        //     8: aload_1        
        //     9: invokeinterface org/apache/http/HttpEntity.getContentLength:()J
        //    14: l2i            
        //    15: invokespecial   com/google/android/gms/internal/zzaa.<init>:(Lcom/google/android/gms/internal/zzu;I)V
        //    18: astore          5
        //    20: aconst_null    
        //    21: astore          4
        //    23: aload           4
        //    25: astore_3       
        //    26: aload_1        
        //    27: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    32: astore          6
        //    34: aload           6
        //    36: ifnonnull       74
        //    39: aload           4
        //    41: astore_3       
        //    42: new             Lcom/google/android/gms/internal/zzp;
        //    45: dup            
        //    46: invokespecial   com/google/android/gms/internal/zzp.<init>:()V
        //    49: athrow         
        //    50: astore          4
        //    52: aload_1        
        //    53: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //    58: aload_0        
        //    59: getfield        com/google/android/gms/internal/zzt.zzar:Lcom/google/android/gms/internal/zzu;
        //    62: aload_3        
        //    63: invokevirtual   com/google/android/gms/internal/zzu.zza:([B)V
        //    66: aload           5
        //    68: invokevirtual   com/google/android/gms/internal/zzaa.close:()V
        //    71: aload           4
        //    73: athrow         
        //    74: aload           4
        //    76: astore_3       
        //    77: aload_0        
        //    78: getfield        com/google/android/gms/internal/zzt.zzar:Lcom/google/android/gms/internal/zzu;
        //    81: sipush          1024
        //    84: invokevirtual   com/google/android/gms/internal/zzu.zzb:(I)[B
        //    87: astore          4
        //    89: aload           4
        //    91: astore_3       
        //    92: aload           6
        //    94: aload           4
        //    96: invokevirtual   java/io/InputStream.read:([B)I
        //    99: istore_2       
        //   100: iload_2        
        //   101: iconst_m1      
        //   102: if_icmpeq       120
        //   105: aload           4
        //   107: astore_3       
        //   108: aload           5
        //   110: aload           4
        //   112: iconst_0       
        //   113: iload_2        
        //   114: invokevirtual   com/google/android/gms/internal/zzaa.write:([BII)V
        //   117: goto            89
        //   120: aload           4
        //   122: astore_3       
        //   123: aload           5
        //   125: invokevirtual   com/google/android/gms/internal/zzaa.toByteArray:()[B
        //   128: astore          6
        //   130: aload_1        
        //   131: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   136: aload_0        
        //   137: getfield        com/google/android/gms/internal/zzt.zzar:Lcom/google/android/gms/internal/zzu;
        //   140: aload           4
        //   142: invokevirtual   com/google/android/gms/internal/zzu.zza:([B)V
        //   145: aload           5
        //   147: invokevirtual   com/google/android/gms/internal/zzaa.close:()V
        //   150: aload           6
        //   152: areturn        
        //   153: astore_1       
        //   154: ldc             "Error occured when calling consumingContent"
        //   156: iconst_0       
        //   157: anewarray       Ljava/lang/Object;
        //   160: invokestatic    com/google/android/gms/internal/zzs.zza:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   163: goto            136
        //   166: astore_1       
        //   167: ldc             "Error occured when calling consumingContent"
        //   169: iconst_0       
        //   170: anewarray       Ljava/lang/Object;
        //   173: invokestatic    com/google/android/gms/internal/zzs.zza:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   176: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  26     34     50     74     Any
        //  42     50     50     74     Any
        //  52     58     166    179    Ljava/io/IOException;
        //  77     89     50     74     Any
        //  92     100    50     74     Any
        //  108    117    50     74     Any
        //  123    130    50     74     Any
        //  130    136    153    166    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    @Override
    public zzi zza(final zzk<?> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //     3: lstore_3       
        //     4: aconst_null    
        //     5: astore          9
        //     7: invokestatic    java/util/Collections.emptyMap:()Ljava/util/Map;
        //    10: astore          7
        //    12: new             Ljava/util/HashMap;
        //    15: dup            
        //    16: invokespecial   java/util/HashMap.<init>:()V
        //    19: astore          5
        //    21: aload_0        
        //    22: aload           5
        //    24: aload_1        
        //    25: invokevirtual   com/google/android/gms/internal/zzk.zzi:()Lcom/google/android/gms/internal/zzb$zza;
        //    28: invokespecial   com/google/android/gms/internal/zzt.zza:(Ljava/util/Map;Lcom/google/android/gms/internal/zzb$zza;)V
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/zzt.zzaq:Lcom/google/android/gms/internal/zzy;
        //    35: aload_1        
        //    36: aload           5
        //    38: invokeinterface com/google/android/gms/internal/zzy.zza:(Lcom/google/android/gms/internal/zzk;Ljava/util/Map;)Lorg/apache/http/HttpResponse;
        //    43: astore          6
        //    45: aload           7
        //    47: astore          5
        //    49: aload           6
        //    51: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    56: astore          8
        //    58: aload           7
        //    60: astore          5
        //    62: aload           8
        //    64: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //    69: istore_2       
        //    70: aload           7
        //    72: astore          5
        //    74: aload           6
        //    76: invokeinterface org/apache/http/HttpResponse.getAllHeaders:()[Lorg/apache/http/Header;
        //    81: invokestatic    com/google/android/gms/internal/zzt.zza:([Lorg/apache/http/Header;)Ljava/util/Map;
        //    84: astore          9
        //    86: iload_2        
        //    87: sipush          304
        //    90: if_icmpne       179
        //    93: aload           9
        //    95: astore          5
        //    97: aload_1        
        //    98: invokevirtual   com/google/android/gms/internal/zzk.zzi:()Lcom/google/android/gms/internal/zzb$zza;
        //   101: astore          7
        //   103: aload           7
        //   105: ifnonnull       132
        //   108: aload           9
        //   110: astore          5
        //   112: new             Lcom/google/android/gms/internal/zzi;
        //   115: dup            
        //   116: sipush          304
        //   119: aconst_null    
        //   120: aload           9
        //   122: iconst_1       
        //   123: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   126: lload_3        
        //   127: lsub           
        //   128: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   131: areturn        
        //   132: aload           9
        //   134: astore          5
        //   136: aload           7
        //   138: getfield        com/google/android/gms/internal/zzb$zza.zzg:Ljava/util/Map;
        //   141: aload           9
        //   143: invokeinterface java/util/Map.putAll:(Ljava/util/Map;)V
        //   148: aload           9
        //   150: astore          5
        //   152: new             Lcom/google/android/gms/internal/zzi;
        //   155: dup            
        //   156: sipush          304
        //   159: aload           7
        //   161: getfield        com/google/android/gms/internal/zzb$zza.data:[B
        //   164: aload           7
        //   166: getfield        com/google/android/gms/internal/zzb$zza.zzg:Ljava/util/Map;
        //   169: iconst_1       
        //   170: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   173: lload_3        
        //   174: lsub           
        //   175: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   178: areturn        
        //   179: aload           9
        //   181: astore          5
        //   183: aload           6
        //   185: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   190: ifnull          269
        //   193: aload           9
        //   195: astore          5
        //   197: aload_0        
        //   198: aload           6
        //   200: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   205: invokespecial   com/google/android/gms/internal/zzt.zza:(Lorg/apache/http/HttpEntity;)[B
        //   208: astore          7
        //   210: aload           7
        //   212: astore          5
        //   214: aload_0        
        //   215: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   218: lload_3        
        //   219: lsub           
        //   220: aload_1        
        //   221: aload           5
        //   223: aload           8
        //   225: invokespecial   com/google/android/gms/internal/zzt.zza:(JLcom/google/android/gms/internal/zzk;[BLorg/apache/http/StatusLine;)V
        //   228: iload_2        
        //   229: sipush          200
        //   232: if_icmplt       242
        //   235: iload_2        
        //   236: sipush          299
        //   239: if_icmple       285
        //   242: new             Ljava/io/IOException;
        //   245: dup            
        //   246: invokespecial   java/io/IOException.<init>:()V
        //   249: athrow         
        //   250: astore          5
        //   252: ldc_w           "socket"
        //   255: aload_1        
        //   256: new             Lcom/google/android/gms/internal/zzq;
        //   259: dup            
        //   260: invokespecial   com/google/android/gms/internal/zzq.<init>:()V
        //   263: invokestatic    com/google/android/gms/internal/zzt.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
        //   266: goto            4
        //   269: aload           9
        //   271: astore          5
        //   273: iconst_0       
        //   274: newarray        B
        //   276: astore          7
        //   278: aload           7
        //   280: astore          5
        //   282: goto            214
        //   285: new             Lcom/google/android/gms/internal/zzi;
        //   288: dup            
        //   289: iload_2        
        //   290: aload           5
        //   292: aload           9
        //   294: iconst_0       
        //   295: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   298: lload_3        
        //   299: lsub           
        //   300: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   303: astore          7
        //   305: aload           7
        //   307: areturn        
        //   308: astore          5
        //   310: ldc_w           "connection"
        //   313: aload_1        
        //   314: new             Lcom/google/android/gms/internal/zzq;
        //   317: dup            
        //   318: invokespecial   com/google/android/gms/internal/zzq.<init>:()V
        //   321: invokestatic    com/google/android/gms/internal/zzt.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
        //   324: goto            4
        //   327: astore          5
        //   329: new             Ljava/lang/RuntimeException;
        //   332: dup            
        //   333: new             Ljava/lang/StringBuilder;
        //   336: dup            
        //   337: invokespecial   java/lang/StringBuilder.<init>:()V
        //   340: ldc_w           "Bad URL "
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: aload_1        
        //   347: invokevirtual   com/google/android/gms/internal/zzk.getUrl:()Ljava/lang/String;
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   356: aload           5
        //   358: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   361: athrow         
        //   362: astore          6
        //   364: aconst_null    
        //   365: astore          8
        //   367: aload           7
        //   369: astore          5
        //   371: aload           9
        //   373: astore          7
        //   375: aload           7
        //   377: ifnull          475
        //   380: aload           7
        //   382: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   387: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   392: istore_2       
        //   393: ldc_w           "Unexpected response code %d for %s"
        //   396: iconst_2       
        //   397: anewarray       Ljava/lang/Object;
        //   400: dup            
        //   401: iconst_0       
        //   402: iload_2        
        //   403: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   406: aastore        
        //   407: dup            
        //   408: iconst_1       
        //   409: aload_1        
        //   410: invokevirtual   com/google/android/gms/internal/zzk.getUrl:()Ljava/lang/String;
        //   413: aastore        
        //   414: invokestatic    com/google/android/gms/internal/zzs.zzc:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   417: aload           8
        //   419: ifnull          495
        //   422: new             Lcom/google/android/gms/internal/zzi;
        //   425: dup            
        //   426: iload_2        
        //   427: aload           8
        //   429: aload           5
        //   431: iconst_0       
        //   432: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   435: lload_3        
        //   436: lsub           
        //   437: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   440: astore          5
        //   442: iload_2        
        //   443: sipush          401
        //   446: if_icmpeq       456
        //   449: iload_2        
        //   450: sipush          403
        //   453: if_icmpne       485
        //   456: ldc_w           "auth"
        //   459: aload_1        
        //   460: new             Lcom/google/android/gms/internal/zza;
        //   463: dup            
        //   464: aload           5
        //   466: invokespecial   com/google/android/gms/internal/zza.<init>:(Lcom/google/android/gms/internal/zzi;)V
        //   469: invokestatic    com/google/android/gms/internal/zzt.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
        //   472: goto            4
        //   475: new             Lcom/google/android/gms/internal/zzj;
        //   478: dup            
        //   479: aload           6
        //   481: invokespecial   com/google/android/gms/internal/zzj.<init>:(Ljava/lang/Throwable;)V
        //   484: athrow         
        //   485: new             Lcom/google/android/gms/internal/zzp;
        //   488: dup            
        //   489: aload           5
        //   491: invokespecial   com/google/android/gms/internal/zzp.<init>:(Lcom/google/android/gms/internal/zzi;)V
        //   494: athrow         
        //   495: new             Lcom/google/android/gms/internal/zzh;
        //   498: dup            
        //   499: aconst_null    
        //   500: invokespecial   com/google/android/gms/internal/zzh.<init>:(Lcom/google/android/gms/internal/zzi;)V
        //   503: athrow         
        //   504: astore          9
        //   506: aconst_null    
        //   507: astore          8
        //   509: aload           6
        //   511: astore          7
        //   513: aload           9
        //   515: astore          6
        //   517: goto            375
        //   520: astore          8
        //   522: aload           6
        //   524: astore          7
        //   526: aload           8
        //   528: astore          6
        //   530: aload           5
        //   532: astore          8
        //   534: aload           9
        //   536: astore          5
        //   538: goto            375
        //    Signature:
        //  (Lcom/google/android/gms/internal/zzk<*>;)Lcom/google/android/gms/internal/zzi;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                          
        //  -----  -----  -----  -----  ----------------------------------------------
        //  12     45     250    269    Ljava/net/SocketTimeoutException;
        //  12     45     308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  12     45     327    362    Ljava/net/MalformedURLException;
        //  12     45     362    375    Ljava/io/IOException;
        //  49     58     250    269    Ljava/net/SocketTimeoutException;
        //  49     58     308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  49     58     327    362    Ljava/net/MalformedURLException;
        //  49     58     504    520    Ljava/io/IOException;
        //  62     70     250    269    Ljava/net/SocketTimeoutException;
        //  62     70     308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  62     70     327    362    Ljava/net/MalformedURLException;
        //  62     70     504    520    Ljava/io/IOException;
        //  74     86     250    269    Ljava/net/SocketTimeoutException;
        //  74     86     308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  74     86     327    362    Ljava/net/MalformedURLException;
        //  74     86     504    520    Ljava/io/IOException;
        //  97     103    250    269    Ljava/net/SocketTimeoutException;
        //  97     103    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  97     103    327    362    Ljava/net/MalformedURLException;
        //  97     103    504    520    Ljava/io/IOException;
        //  112    132    250    269    Ljava/net/SocketTimeoutException;
        //  112    132    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  112    132    327    362    Ljava/net/MalformedURLException;
        //  112    132    504    520    Ljava/io/IOException;
        //  136    148    250    269    Ljava/net/SocketTimeoutException;
        //  136    148    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  136    148    327    362    Ljava/net/MalformedURLException;
        //  136    148    504    520    Ljava/io/IOException;
        //  152    179    250    269    Ljava/net/SocketTimeoutException;
        //  152    179    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  152    179    327    362    Ljava/net/MalformedURLException;
        //  152    179    504    520    Ljava/io/IOException;
        //  183    193    250    269    Ljava/net/SocketTimeoutException;
        //  183    193    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  183    193    327    362    Ljava/net/MalformedURLException;
        //  183    193    504    520    Ljava/io/IOException;
        //  197    210    250    269    Ljava/net/SocketTimeoutException;
        //  197    210    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  197    210    327    362    Ljava/net/MalformedURLException;
        //  197    210    504    520    Ljava/io/IOException;
        //  214    228    250    269    Ljava/net/SocketTimeoutException;
        //  214    228    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  214    228    327    362    Ljava/net/MalformedURLException;
        //  214    228    520    541    Ljava/io/IOException;
        //  242    250    250    269    Ljava/net/SocketTimeoutException;
        //  242    250    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  242    250    327    362    Ljava/net/MalformedURLException;
        //  242    250    520    541    Ljava/io/IOException;
        //  273    278    250    269    Ljava/net/SocketTimeoutException;
        //  273    278    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  273    278    327    362    Ljava/net/MalformedURLException;
        //  273    278    504    520    Ljava/io/IOException;
        //  285    305    250    269    Ljava/net/SocketTimeoutException;
        //  285    305    308    327    Lorg/apache/http/conn/ConnectTimeoutException;
        //  285    305    327    362    Ljava/net/MalformedURLException;
        //  285    305    520    541    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
