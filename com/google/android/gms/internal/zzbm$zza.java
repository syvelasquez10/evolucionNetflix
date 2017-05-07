// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.OutputStream;
import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;

class zzbm$zza
{
    ByteArrayOutputStream zzsj;
    Base64OutputStream zzsk;
    
    public zzbm$zza() {
        this.zzsj = new ByteArrayOutputStream(4096);
        this.zzsk = new Base64OutputStream((OutputStream)this.zzsj, 10);
    }
    
    @Override
    public String toString() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzbm$zza.zzsk:Landroid/util/Base64OutputStream;
        //     4: invokevirtual   android/util/Base64OutputStream.close:()V
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/zzbm$zza.zzsj:Ljava/io/ByteArrayOutputStream;
        //    11: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/zzbm$zza.zzsj:Ljava/io/ByteArrayOutputStream;
        //    18: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
        //    21: astore_1       
        //    22: aload_0        
        //    23: aconst_null    
        //    24: putfield        com/google/android/gms/internal/zzbm$zza.zzsj:Ljava/io/ByteArrayOutputStream;
        //    27: aload_0        
        //    28: aconst_null    
        //    29: putfield        com/google/android/gms/internal/zzbm$zza.zzsk:Landroid/util/Base64OutputStream;
        //    32: aload_1        
        //    33: areturn        
        //    34: astore_1       
        //    35: ldc             "HashManager: Unable to convert to Base64."
        //    37: aload_1        
        //    38: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    41: goto            7
        //    44: astore_1       
        //    45: ldc             "HashManager: Unable to convert to Base64."
        //    47: aload_1        
        //    48: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    51: aload_0        
        //    52: aconst_null    
        //    53: putfield        com/google/android/gms/internal/zzbm$zza.zzsj:Ljava/io/ByteArrayOutputStream;
        //    56: aload_0        
        //    57: aconst_null    
        //    58: putfield        com/google/android/gms/internal/zzbm$zza.zzsk:Landroid/util/Base64OutputStream;
        //    61: ldc             ""
        //    63: areturn        
        //    64: astore_1       
        //    65: aload_0        
        //    66: aconst_null    
        //    67: putfield        com/google/android/gms/internal/zzbm$zza.zzsj:Ljava/io/ByteArrayOutputStream;
        //    70: aload_0        
        //    71: aconst_null    
        //    72: putfield        com/google/android/gms/internal/zzbm$zza.zzsk:Landroid/util/Base64OutputStream;
        //    75: aload_1        
        //    76: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      7      34     44     Ljava/io/IOException;
        //  7      22     44     64     Ljava/io/IOException;
        //  7      22     64     77     Any
        //  45     51     64     77     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0007:
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
    
    public void write(final byte[] array) {
        this.zzsk.write(array);
    }
}
