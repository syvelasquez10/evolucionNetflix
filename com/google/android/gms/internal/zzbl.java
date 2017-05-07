// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.MessageDigest;

public abstract class zzbl
{
    private static MessageDigest zzsd;
    protected Object zzpc;
    
    static {
        zzbl.zzsd = null;
    }
    
    public zzbl() {
        this.zzpc = new Object();
    }
    
    protected MessageDigest zzcy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzbl.zzpc:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: getstatic       com/google/android/gms/internal/zzbl.zzsd:Ljava/security/MessageDigest;
        //    10: ifnull          21
        //    13: getstatic       com/google/android/gms/internal/zzbl.zzsd:Ljava/security/MessageDigest;
        //    16: astore_3       
        //    17: aload_2        
        //    18: monitorexit    
        //    19: aload_3        
        //    20: areturn        
        //    21: iconst_0       
        //    22: istore_1       
        //    23: iload_1        
        //    24: iconst_2       
        //    25: if_icmpge       43
        //    28: ldc             "MD5"
        //    30: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    33: putstatic       com/google/android/gms/internal/zzbl.zzsd:Ljava/security/MessageDigest;
        //    36: iload_1        
        //    37: iconst_1       
        //    38: iadd           
        //    39: istore_1       
        //    40: goto            23
        //    43: getstatic       com/google/android/gms/internal/zzbl.zzsd:Ljava/security/MessageDigest;
        //    46: astore_3       
        //    47: aload_2        
        //    48: monitorexit    
        //    49: aload_3        
        //    50: areturn        
        //    51: astore_3       
        //    52: aload_2        
        //    53: monitorexit    
        //    54: aload_3        
        //    55: athrow         
        //    56: astore_3       
        //    57: goto            36
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  7      19     51     56     Any
        //  28     36     56     60     Ljava/security/NoSuchAlgorithmException;
        //  28     36     51     56     Any
        //  43     49     51     56     Any
        //  52     54     51     56     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    abstract byte[] zzz(final String p0);
}
