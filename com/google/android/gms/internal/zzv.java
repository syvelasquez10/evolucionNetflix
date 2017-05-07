// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Collections;
import android.os.SystemClock;
import java.io.IOException;
import java.util.Iterator;
import java.io.OutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.io.File;
import java.util.Map;

public class zzv implements zzb
{
    private final int zzaA;
    private final Map<String, zzv$zza> zzax;
    private long zzay;
    private final File zzaz;
    
    public zzv(final File file) {
        this(file, 5242880);
    }
    
    public zzv(final File zzaz, final int zzaA) {
        this.zzax = new LinkedHashMap<String, zzv$zza>(16, 0.75f, true);
        this.zzay = 0L;
        this.zzaz = zzaz;
        this.zzaA = zzaA;
    }
    
    private void removeEntry(final String s) {
        final zzv$zza zzv$zza = this.zzax.get(s);
        if (zzv$zza != null) {
            this.zzay -= zzv$zza.zzaB;
            this.zzax.remove(s);
        }
    }
    
    private static int zza(final InputStream inputStream) {
        final int read = inputStream.read();
        if (read == -1) {
            throw new EOFException();
        }
        return read;
    }
    
    static void zza(final OutputStream outputStream, final int n) {
        outputStream.write(n >> 0 & 0xFF);
        outputStream.write(n >> 8 & 0xFF);
        outputStream.write(n >> 16 & 0xFF);
        outputStream.write(n >> 24 & 0xFF);
    }
    
    static void zza(final OutputStream outputStream, final long n) {
        outputStream.write((byte)(n >>> 0));
        outputStream.write((byte)(n >>> 8));
        outputStream.write((byte)(n >>> 16));
        outputStream.write((byte)(n >>> 24));
        outputStream.write((byte)(n >>> 32));
        outputStream.write((byte)(n >>> 40));
        outputStream.write((byte)(n >>> 48));
        outputStream.write((byte)(n >>> 56));
    }
    
    static void zza(final OutputStream outputStream, final String s) {
        final byte[] bytes = s.getBytes("UTF-8");
        zza(outputStream, (long)bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }
    
    private void zza(final String s, final zzv$zza zzv$zza) {
        if (!this.zzax.containsKey(s)) {
            this.zzay += zzv$zza.zzaB;
        }
        else {
            this.zzay += zzv$zza.zzaB - this.zzax.get(s).zzaB;
        }
        this.zzax.put(s, zzv$zza);
    }
    
    static void zza(final Map<String, String> map, final OutputStream outputStream) {
        if (map != null) {
            zza(outputStream, map.size());
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                zza(outputStream, entry.getKey());
                zza(outputStream, entry.getValue());
            }
        }
        else {
            zza(outputStream, 0);
        }
    }
    
    private static byte[] zza(final InputStream inputStream, final int n) {
        final byte[] array = new byte[n];
        int i;
        int read;
        for (i = 0; i < n; i += read) {
            read = inputStream.read(array, i, n - i);
            if (read == -1) {
                break;
            }
        }
        if (i != n) {
            throw new IOException("Expected " + n + " bytes, read " + i + " bytes");
        }
        return array;
    }
    
    static int zzb(final InputStream inputStream) {
        return 0x0 | zza(inputStream) << 0 | zza(inputStream) << 8 | zza(inputStream) << 16 | zza(inputStream) << 24;
    }
    
    static long zzc(final InputStream inputStream) {
        return 0x0L | (zza(inputStream) & 0xFFL) << 0 | (zza(inputStream) & 0xFFL) << 8 | (zza(inputStream) & 0xFFL) << 16 | (zza(inputStream) & 0xFFL) << 24 | (zza(inputStream) & 0xFFL) << 32 | (zza(inputStream) & 0xFFL) << 40 | (zza(inputStream) & 0xFFL) << 48 | (zza(inputStream) & 0xFFL) << 56;
    }
    
    private void zzc(final int n) {
        if (this.zzay + n >= this.zzaA) {
            if (zzs.DEBUG) {
                zzs.zza("Pruning old cache entries.", new Object[0]);
            }
            final long zzay = this.zzay;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Iterator<Map.Entry<String, zzv$zza>> iterator = this.zzax.entrySet().iterator();
            int n2 = 0;
            while (true) {
                while (iterator.hasNext()) {
                    final zzv$zza zzv$zza = iterator.next().getValue();
                    if (this.zzf(zzv$zza.key).delete()) {
                        this.zzay -= zzv$zza.zzaB;
                    }
                    else {
                        zzs.zzb("Could not delete cache entry for key=%s, filename=%s", zzv$zza.key, this.zze(zzv$zza.key));
                    }
                    iterator.remove();
                    ++n2;
                    if (this.zzay + n < this.zzaA * 0.9f) {
                        if (zzs.DEBUG) {
                            zzs.zza("pruned %d files, %d bytes, %d ms", n2, this.zzay - zzay, SystemClock.elapsedRealtime() - elapsedRealtime);
                        }
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    static String zzd(final InputStream inputStream) {
        return new String(zza(inputStream, (int)zzc(inputStream)), "UTF-8");
    }
    
    private String zze(final String s) {
        final int n = s.length() / 2;
        return String.valueOf(s.substring(0, n).hashCode()) + String.valueOf(s.substring(n).hashCode());
    }
    
    static Map<String, String> zze(final InputStream inputStream) {
        final int zzb = zzb(inputStream);
        Map<String, String> emptyMap;
        if (zzb == 0) {
            emptyMap = Collections.emptyMap();
        }
        else {
            emptyMap = new HashMap<String, String>(zzb);
        }
        for (int i = 0; i < zzb; ++i) {
            emptyMap.put(zzd(inputStream).intern(), zzd(inputStream).intern());
        }
        return emptyMap;
    }
    
    public void remove(final String s) {
        synchronized (this) {
            final boolean delete = this.zzf(s).delete();
            this.removeEntry(s);
            if (!delete) {
                zzs.zzb("Could not delete cache entry for key=%s, filename=%s", s, this.zze(s));
            }
        }
    }
    
    @Override
    public zzb$zza zza(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzv.zzax:Ljava/util/Map;
        //     6: aload_1        
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: checkcast       Lcom/google/android/gms/internal/zzv$zza;
        //    15: astore          4
        //    17: aload           4
        //    19: ifnonnull       28
        //    22: aconst_null    
        //    23: astore_1       
        //    24: aload_0        
        //    25: monitorexit    
        //    26: aload_1        
        //    27: areturn        
        //    28: aload_0        
        //    29: aload_1        
        //    30: invokevirtual   com/google/android/gms/internal/zzv.zzf:(Ljava/lang/String;)Ljava/io/File;
        //    33: astore          5
        //    35: new             Lcom/google/android/gms/internal/zzv$zzb;
        //    38: dup            
        //    39: new             Ljava/io/FileInputStream;
        //    42: dup            
        //    43: aload           5
        //    45: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    48: aconst_null    
        //    49: invokespecial   com/google/android/gms/internal/zzv$zzb.<init>:(Ljava/io/InputStream;Lcom/google/android/gms/internal/zzv$1;)V
        //    52: astore_3       
        //    53: aload_3        
        //    54: astore_2       
        //    55: aload_3        
        //    56: invokestatic    com/google/android/gms/internal/zzv$zza.zzf:(Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzv$zza;
        //    59: pop            
        //    60: aload_3        
        //    61: astore_2       
        //    62: aload           4
        //    64: aload_3        
        //    65: aload           5
        //    67: invokevirtual   java/io/File.length:()J
        //    70: aload_3        
        //    71: invokestatic    com/google/android/gms/internal/zzv$zzb.zza:(Lcom/google/android/gms/internal/zzv$zzb;)I
        //    74: i2l            
        //    75: lsub           
        //    76: l2i            
        //    77: invokestatic    com/google/android/gms/internal/zzv.zza:(Ljava/io/InputStream;I)[B
        //    80: invokevirtual   com/google/android/gms/internal/zzv$zza.zzb:([B)Lcom/google/android/gms/internal/zzb$zza;
        //    83: astore          4
        //    85: aload           4
        //    87: astore_2       
        //    88: aload_2        
        //    89: astore_1       
        //    90: aload_3        
        //    91: ifnull          24
        //    94: aload_3        
        //    95: invokevirtual   com/google/android/gms/internal/zzv$zzb.close:()V
        //    98: aload_2        
        //    99: astore_1       
        //   100: goto            24
        //   103: astore_1       
        //   104: aconst_null    
        //   105: astore_1       
        //   106: goto            24
        //   109: astore          4
        //   111: aconst_null    
        //   112: astore_3       
        //   113: aload_3        
        //   114: astore_2       
        //   115: ldc_w           "%s: %s"
        //   118: iconst_2       
        //   119: anewarray       Ljava/lang/Object;
        //   122: dup            
        //   123: iconst_0       
        //   124: aload           5
        //   126: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   129: aastore        
        //   130: dup            
        //   131: iconst_1       
        //   132: aload           4
        //   134: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   137: aastore        
        //   138: invokestatic    com/google/android/gms/internal/zzs.zzb:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   141: aload_3        
        //   142: astore_2       
        //   143: aload_0        
        //   144: aload_1        
        //   145: invokevirtual   com/google/android/gms/internal/zzv.remove:(Ljava/lang/String;)V
        //   148: aload_3        
        //   149: ifnull          156
        //   152: aload_3        
        //   153: invokevirtual   com/google/android/gms/internal/zzv$zzb.close:()V
        //   156: aconst_null    
        //   157: astore_1       
        //   158: goto            24
        //   161: astore_1       
        //   162: aconst_null    
        //   163: astore_1       
        //   164: goto            24
        //   167: astore_1       
        //   168: aconst_null    
        //   169: astore_2       
        //   170: aload_2        
        //   171: ifnull          178
        //   174: aload_2        
        //   175: invokevirtual   com/google/android/gms/internal/zzv$zzb.close:()V
        //   178: aload_1        
        //   179: athrow         
        //   180: astore_1       
        //   181: aload_0        
        //   182: monitorexit    
        //   183: aload_1        
        //   184: athrow         
        //   185: astore_1       
        //   186: aconst_null    
        //   187: astore_1       
        //   188: goto            24
        //   191: astore_1       
        //   192: goto            170
        //   195: astore          4
        //   197: goto            113
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      17     180    185    Any
        //  28     35     180    185    Any
        //  35     53     109    113    Ljava/io/IOException;
        //  35     53     167    170    Any
        //  55     60     195    200    Ljava/io/IOException;
        //  55     60     191    195    Any
        //  62     85     195    200    Ljava/io/IOException;
        //  62     85     191    195    Any
        //  94     98     103    109    Ljava/io/IOException;
        //  94     98     180    185    Any
        //  115    141    191    195    Any
        //  143    148    191    195    Any
        //  152    156    161    167    Ljava/io/IOException;
        //  152    156    180    185    Any
        //  174    178    185    191    Ljava/io/IOException;
        //  174    178    180    185    Any
        //  178    180    180    185    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
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
    public void zza() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzv.zzaz:Ljava/io/File;
        //     6: invokevirtual   java/io/File.exists:()Z
        //     9: ifne            45
        //    12: aload_0        
        //    13: getfield        com/google/android/gms/internal/zzv.zzaz:Ljava/io/File;
        //    16: invokevirtual   java/io/File.mkdirs:()Z
        //    19: ifne            42
        //    22: ldc_w           "Unable to create cache dir %s"
        //    25: iconst_1       
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: iconst_0       
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/zzv.zzaz:Ljava/io/File;
        //    35: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    38: aastore        
        //    39: invokestatic    com/google/android/gms/internal/zzs.zzc:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    42: aload_0        
        //    43: monitorexit    
        //    44: return         
        //    45: aload_0        
        //    46: getfield        com/google/android/gms/internal/zzv.zzaz:Ljava/io/File;
        //    49: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    52: astore          5
        //    54: aload           5
        //    56: ifnull          42
        //    59: aload           5
        //    61: arraylength    
        //    62: istore_2       
        //    63: iconst_0       
        //    64: istore_1       
        //    65: iload_1        
        //    66: iload_2        
        //    67: if_icmpge       42
        //    70: aload           5
        //    72: iload_1        
        //    73: aaload         
        //    74: astore          6
        //    76: aconst_null    
        //    77: astore_3       
        //    78: new             Ljava/io/BufferedInputStream;
        //    81: dup            
        //    82: new             Ljava/io/FileInputStream;
        //    85: dup            
        //    86: aload           6
        //    88: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    91: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    94: astore          4
        //    96: aload           4
        //    98: astore_3       
        //    99: aload           4
        //   101: invokestatic    com/google/android/gms/internal/zzv$zza.zzf:(Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzv$zza;
        //   104: astore          7
        //   106: aload           4
        //   108: astore_3       
        //   109: aload           7
        //   111: aload           6
        //   113: invokevirtual   java/io/File.length:()J
        //   116: putfield        com/google/android/gms/internal/zzv$zza.zzaB:J
        //   119: aload           4
        //   121: astore_3       
        //   122: aload_0        
        //   123: aload           7
        //   125: getfield        com/google/android/gms/internal/zzv$zza.key:Ljava/lang/String;
        //   128: aload           7
        //   130: invokespecial   com/google/android/gms/internal/zzv.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzv$zza;)V
        //   133: aload           4
        //   135: ifnull          143
        //   138: aload           4
        //   140: invokevirtual   java/io/BufferedInputStream.close:()V
        //   143: iload_1        
        //   144: iconst_1       
        //   145: iadd           
        //   146: istore_1       
        //   147: goto            65
        //   150: astore_3       
        //   151: aconst_null    
        //   152: astore          4
        //   154: aload           6
        //   156: ifnull          168
        //   159: aload           4
        //   161: astore_3       
        //   162: aload           6
        //   164: invokevirtual   java/io/File.delete:()Z
        //   167: pop            
        //   168: aload           4
        //   170: ifnull          143
        //   173: aload           4
        //   175: invokevirtual   java/io/BufferedInputStream.close:()V
        //   178: goto            143
        //   181: astore_3       
        //   182: goto            143
        //   185: astore          5
        //   187: aload_3        
        //   188: astore          4
        //   190: aload           5
        //   192: astore_3       
        //   193: aload           4
        //   195: ifnull          203
        //   198: aload           4
        //   200: invokevirtual   java/io/BufferedInputStream.close:()V
        //   203: aload_3        
        //   204: athrow         
        //   205: astore_3       
        //   206: aload_0        
        //   207: monitorexit    
        //   208: aload_3        
        //   209: athrow         
        //   210: astore_3       
        //   211: goto            143
        //   214: astore          4
        //   216: goto            203
        //   219: astore          5
        //   221: aload_3        
        //   222: astore          4
        //   224: aload           5
        //   226: astore_3       
        //   227: goto            193
        //   230: astore_3       
        //   231: goto            154
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      42     205    210    Any
        //  45     54     205    210    Any
        //  59     63     205    210    Any
        //  78     96     150    154    Ljava/io/IOException;
        //  78     96     185    193    Any
        //  99     106    230    234    Ljava/io/IOException;
        //  99     106    219    230    Any
        //  109    119    230    234    Ljava/io/IOException;
        //  109    119    219    230    Any
        //  122    133    230    234    Ljava/io/IOException;
        //  122    133    219    230    Any
        //  138    143    210    214    Ljava/io/IOException;
        //  138    143    205    210    Any
        //  162    168    219    230    Any
        //  173    178    181    185    Ljava/io/IOException;
        //  173    178    205    210    Any
        //  198    203    214    219    Ljava/io/IOException;
        //  198    203    205    210    Any
        //  203    205    205    210    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    @Override
    public void zza(final String s, final zzb$zza zzb$zza) {
        synchronized (this) {
            this.zzc(zzb$zza.data.length);
            final File zzf = this.zzf(s);
            FileOutputStream fileOutputStream = null;
            zzv$zza zzv$zza = null;
            Label_0106: {
                try {
                    fileOutputStream = new FileOutputStream(zzf);
                    zzv$zza = new zzv$zza(s, zzb$zza);
                    if (!zzv$zza.zza(fileOutputStream)) {
                        fileOutputStream.close();
                        zzs.zzb("Failed to write header for %s", zzf.getAbsolutePath());
                        throw new IOException();
                    }
                    break Label_0106;
                }
                catch (IOException ex) {
                    if (!zzf.delete()) {
                        zzs.zzb("Could not clean up file %s", zzf.getAbsolutePath());
                    }
                }
                return;
            }
            fileOutputStream.write(zzb$zza.data);
            fileOutputStream.close();
            this.zza(s, zzv$zza);
        }
    }
    
    public File zzf(final String s) {
        return new File(this.zzaz, this.zze(s));
    }
}
