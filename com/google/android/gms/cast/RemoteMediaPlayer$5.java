// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;
import org.json.JSONObject;
import com.google.android.gms.common.api.GoogleApiClient;

class RemoteMediaPlayer$5 extends RemoteMediaPlayer$b
{
    final /* synthetic */ RemoteMediaPlayer FK;
    final /* synthetic */ GoogleApiClient FL;
    final /* synthetic */ JSONObject FS;
    
    RemoteMediaPlayer$5(final RemoteMediaPlayer fk, final GoogleApiClient fl, final JSONObject fs) {
        this.FK = fk;
        this.FL = fl;
        this.FS = fs;
    }
    
    @Override
    protected void a(final ij p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //     7: astore_1       
        //     8: aload_1        
        //     9: monitorenter   
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
        //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.Gb:Lcom/google/android/gms/internal/is;
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FS:Lorg/json/JSONObject;
        //    39: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;Lorg/json/JSONObject;)J
        //    42: pop2           
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    47: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    50: aconst_null    
        //    51: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    54: aload_1        
        //    55: monitorexit    
        //    56: return         
        //    57: astore_2       
        //    58: aload_0        
        //    59: aload_0        
        //    60: new             Lcom/google/android/gms/common/api/Status;
        //    63: dup            
        //    64: sipush          2100
        //    67: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
        //    70: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$5.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //    73: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$5.b:(Lcom/google/android/gms/common/api/Result;)V
        //    76: aload_0        
        //    77: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    80: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    83: aconst_null    
        //    84: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    87: goto            54
        //    90: astore_2       
        //    91: aload_1        
        //    92: monitorexit    
        //    93: aload_2        
        //    94: athrow         
        //    95: astore_2       
        //    96: aload_0        
        //    97: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   100: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //   103: aconst_null    
        //   104: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   107: aload_2        
        //   108: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     24     90     95     Any
        //  24     43     57     90     Ljava/io/IOException;
        //  24     43     95     109    Any
        //  43     54     90     95     Any
        //  54     56     90     95     Any
        //  58     76     95     109    Any
        //  76     87     90     95     Any
        //  91     93     90     95     Any
        //  96     109    90     95     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
