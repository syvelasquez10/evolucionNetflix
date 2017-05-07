// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import java.util.concurrent.Future;

public class zzn extends zzd
{
    private volatile String zzLd;
    private Future<String> zzMG;
    
    protected zzn(final zzf zzf) {
        super(zzf);
    }
    
    private boolean zzg(final Context context, final String s) {
        boolean b = false;
        zzx.zzcs(s);
        zzx.zzci("ClientId should be saved from worker thread");
        final FileOutputStream fileOutputStream = null;
        final FileOutputStream fileOutputStream2 = null;
        FileOutputStream openFileOutput;
        final FileOutputStream fileOutputStream3 = openFileOutput = null;
        FileOutputStream fileOutputStream4 = fileOutputStream;
        FileOutputStream fileOutputStream5 = fileOutputStream2;
        try {
            this.zza("Storing clientId", s);
            openFileOutput = fileOutputStream3;
            fileOutputStream4 = fileOutputStream;
            fileOutputStream5 = fileOutputStream2;
            final FileOutputStream fileOutputStream6 = fileOutputStream5 = (fileOutputStream4 = (openFileOutput = context.openFileOutput("gaClientId", 0)));
            fileOutputStream6.write(s.getBytes());
            b = true;
            if (fileOutputStream6 == null) {
                return b;
            }
            try {
                fileOutputStream6.close();
                b = b;
                return b;
            }
            catch (IOException ex) {
                this.zze("Failed to close clientId writing stream", ex);
                return true;
            }
        }
        catch (FileNotFoundException ex2) {
            fileOutputStream5 = openFileOutput;
            this.zze("Error creating clientId file", ex2);
            if (openFileOutput == null) {
                return b;
            }
            try {
                openFileOutput.close();
                return false;
            }
            catch (IOException ex3) {
                this.zze("Failed to close clientId writing stream", ex3);
                return false;
            }
        }
        catch (IOException ex4) {
            fileOutputStream5 = fileOutputStream4;
            this.zze("Error writing to clientId file", ex4);
            if (fileOutputStream4 == null) {
                return b;
            }
            try {
                fileOutputStream4.close();
                return false;
            }
            catch (IOException ex5) {
                this.zze("Failed to close clientId writing stream", ex5);
                return false;
            }
        }
        finally {
            Label_0185: {
                if (fileOutputStream5 == null) {
                    break Label_0185;
                }
                try {
                    fileOutputStream5.close();
                }
                catch (IOException ex6) {
                    this.zze("Failed to close clientId writing stream", ex6);
                }
            }
        }
    }
    
    private String zzjg() {
        String zzjh = this.zzjh();
        try {
            if (!this.zzg(this.zzig().getContext(), zzjh)) {
                zzjh = "0";
            }
            return zzjh;
        }
        catch (Exception ex) {
            this.zze("Error saving clientId file", ex);
            return "0";
        }
    }
    
    protected String zzZ(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "ClientId should be loaded from worker thread"
        //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzci:(Ljava/lang/String;)V
        //     5: aload_1        
        //     6: ldc             "gaClientId"
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore_3       
        //    12: aload_3        
        //    13: astore          4
        //    15: bipush          36
        //    17: newarray        B
        //    19: astore          5
        //    21: aload_3        
        //    22: astore          4
        //    24: aload_3        
        //    25: aload           5
        //    27: iconst_0       
        //    28: aload           5
        //    30: arraylength    
        //    31: invokevirtual   java/io/FileInputStream.read:([BII)I
        //    34: istore_2       
        //    35: aload_3        
        //    36: astore          4
        //    38: aload_3        
        //    39: invokevirtual   java/io/FileInputStream.available:()I
        //    42: ifle            91
        //    45: aload_3        
        //    46: astore          4
        //    48: aload_0        
        //    49: ldc             "clientId file seems corrupted, deleting it."
        //    51: invokevirtual   com/google/android/gms/analytics/internal/zzn.zzbb:(Ljava/lang/String;)V
        //    54: aload_3        
        //    55: astore          4
        //    57: aload_3        
        //    58: invokevirtual   java/io/FileInputStream.close:()V
        //    61: aload_3        
        //    62: astore          4
        //    64: aload_1        
        //    65: ldc             "gaClientId"
        //    67: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //    70: pop            
        //    71: aload_3        
        //    72: ifnull          79
        //    75: aload_3        
        //    76: invokevirtual   java/io/FileInputStream.close:()V
        //    79: aconst_null    
        //    80: areturn        
        //    81: astore_1       
        //    82: aload_0        
        //    83: ldc             "Failed to close client id reading stream"
        //    85: aload_1        
        //    86: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //    89: aconst_null    
        //    90: areturn        
        //    91: iload_2        
        //    92: bipush          14
        //    94: if_icmpge       143
        //    97: aload_3        
        //    98: astore          4
        //   100: aload_0        
        //   101: ldc             "clientId file is empty, deleting it."
        //   103: invokevirtual   com/google/android/gms/analytics/internal/zzn.zzbb:(Ljava/lang/String;)V
        //   106: aload_3        
        //   107: astore          4
        //   109: aload_3        
        //   110: invokevirtual   java/io/FileInputStream.close:()V
        //   113: aload_3        
        //   114: astore          4
        //   116: aload_1        
        //   117: ldc             "gaClientId"
        //   119: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   122: pop            
        //   123: aload_3        
        //   124: ifnull          79
        //   127: aload_3        
        //   128: invokevirtual   java/io/FileInputStream.close:()V
        //   131: aconst_null    
        //   132: areturn        
        //   133: astore_1       
        //   134: aload_0        
        //   135: ldc             "Failed to close client id reading stream"
        //   137: aload_1        
        //   138: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   141: aconst_null    
        //   142: areturn        
        //   143: aload_3        
        //   144: astore          4
        //   146: aload_3        
        //   147: invokevirtual   java/io/FileInputStream.close:()V
        //   150: aload_3        
        //   151: astore          4
        //   153: new             Ljava/lang/String;
        //   156: dup            
        //   157: aload           5
        //   159: iconst_0       
        //   160: iload_2        
        //   161: invokespecial   java/lang/String.<init>:([BII)V
        //   164: astore          5
        //   166: aload_3        
        //   167: astore          4
        //   169: aload_0        
        //   170: ldc             "Read client id from disk"
        //   172: aload           5
        //   174: invokevirtual   com/google/android/gms/analytics/internal/zzn.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //   177: aload_3        
        //   178: ifnull          185
        //   181: aload_3        
        //   182: invokevirtual   java/io/FileInputStream.close:()V
        //   185: aload           5
        //   187: areturn        
        //   188: astore_1       
        //   189: aload_0        
        //   190: ldc             "Failed to close client id reading stream"
        //   192: aload_1        
        //   193: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   196: goto            185
        //   199: astore_1       
        //   200: aconst_null    
        //   201: astore_1       
        //   202: aload_1        
        //   203: ifnull          79
        //   206: aload_1        
        //   207: invokevirtual   java/io/FileInputStream.close:()V
        //   210: aconst_null    
        //   211: areturn        
        //   212: astore_1       
        //   213: aload_0        
        //   214: ldc             "Failed to close client id reading stream"
        //   216: aload_1        
        //   217: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   220: aconst_null    
        //   221: areturn        
        //   222: astore          5
        //   224: aconst_null    
        //   225: astore_3       
        //   226: aload_3        
        //   227: astore          4
        //   229: aload_0        
        //   230: ldc             "Error reading client id file, deleting it"
        //   232: aload           5
        //   234: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   237: aload_3        
        //   238: astore          4
        //   240: aload_1        
        //   241: ldc             "gaClientId"
        //   243: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   246: pop            
        //   247: aload_3        
        //   248: ifnull          79
        //   251: aload_3        
        //   252: invokevirtual   java/io/FileInputStream.close:()V
        //   255: aconst_null    
        //   256: areturn        
        //   257: astore_1       
        //   258: aload_0        
        //   259: ldc             "Failed to close client id reading stream"
        //   261: aload_1        
        //   262: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   265: aconst_null    
        //   266: areturn        
        //   267: astore_1       
        //   268: aconst_null    
        //   269: astore          4
        //   271: aload           4
        //   273: ifnull          281
        //   276: aload           4
        //   278: invokevirtual   java/io/FileInputStream.close:()V
        //   281: aload_1        
        //   282: athrow         
        //   283: astore_3       
        //   284: aload_0        
        //   285: ldc             "Failed to close client id reading stream"
        //   287: aload_3        
        //   288: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   291: goto            281
        //   294: astore_1       
        //   295: goto            271
        //   298: astore          5
        //   300: goto            226
        //   303: astore_1       
        //   304: aload_3        
        //   305: astore_1       
        //   306: goto            202
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  5      12     199    202    Ljava/io/FileNotFoundException;
        //  5      12     222    226    Ljava/io/IOException;
        //  5      12     267    271    Any
        //  15     21     303    309    Ljava/io/FileNotFoundException;
        //  15     21     298    303    Ljava/io/IOException;
        //  15     21     294    298    Any
        //  24     35     303    309    Ljava/io/FileNotFoundException;
        //  24     35     298    303    Ljava/io/IOException;
        //  24     35     294    298    Any
        //  38     45     303    309    Ljava/io/FileNotFoundException;
        //  38     45     298    303    Ljava/io/IOException;
        //  38     45     294    298    Any
        //  48     54     303    309    Ljava/io/FileNotFoundException;
        //  48     54     298    303    Ljava/io/IOException;
        //  48     54     294    298    Any
        //  57     61     303    309    Ljava/io/FileNotFoundException;
        //  57     61     298    303    Ljava/io/IOException;
        //  57     61     294    298    Any
        //  64     71     303    309    Ljava/io/FileNotFoundException;
        //  64     71     298    303    Ljava/io/IOException;
        //  64     71     294    298    Any
        //  75     79     81     91     Ljava/io/IOException;
        //  100    106    303    309    Ljava/io/FileNotFoundException;
        //  100    106    298    303    Ljava/io/IOException;
        //  100    106    294    298    Any
        //  109    113    303    309    Ljava/io/FileNotFoundException;
        //  109    113    298    303    Ljava/io/IOException;
        //  109    113    294    298    Any
        //  116    123    303    309    Ljava/io/FileNotFoundException;
        //  116    123    298    303    Ljava/io/IOException;
        //  116    123    294    298    Any
        //  127    131    133    143    Ljava/io/IOException;
        //  146    150    303    309    Ljava/io/FileNotFoundException;
        //  146    150    298    303    Ljava/io/IOException;
        //  146    150    294    298    Any
        //  153    166    303    309    Ljava/io/FileNotFoundException;
        //  153    166    298    303    Ljava/io/IOException;
        //  153    166    294    298    Any
        //  169    177    303    309    Ljava/io/FileNotFoundException;
        //  169    177    298    303    Ljava/io/IOException;
        //  169    177    294    298    Any
        //  181    185    188    199    Ljava/io/IOException;
        //  206    210    212    222    Ljava/io/IOException;
        //  229    237    294    298    Any
        //  240    247    294    298    Any
        //  251    255    257    267    Ljava/io/IOException;
        //  276    281    283    294    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
    protected void zzhB() {
    }
    
    public String zzjd() {
        this.zzio();
        // monitorenter(this)
        try {
            if (this.zzLd == null) {
                this.zzMG = this.zzig().zzb((Callable<String>)new zzn$1(this));
            }
            Label_0085: {
                if (this.zzMG == null) {
                    break Label_0085;
                }
                try {
                    this.zzLd = this.zzMG.get();
                    if (this.zzLd == null) {
                        this.zzLd = "0";
                    }
                    this.zza("Loaded clientId", this.zzLd);
                    this.zzMG = null;
                    return this.zzLd;
                }
                catch (InterruptedException ex) {
                    this.zzd("ClientId loading or generation was interrupted", ex);
                    this.zzLd = "0";
                }
                catch (ExecutionException ex2) {
                    this.zze("Failed to load or generate client id", ex2);
                    this.zzLd = "0";
                }
            }
        }
        finally {}
    }
    
    String zzje() {
        synchronized (this) {
            this.zzLd = null;
            this.zzMG = this.zzig().zzb((Callable<String>)new zzn$2(this));
            return this.zzjd();
        }
    }
    
    String zzjf() {
        String s;
        if ((s = this.zzZ(this.zzig().getContext())) == null) {
            s = this.zzjg();
        }
        return s;
    }
    
    protected String zzjh() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
