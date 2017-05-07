// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient;

class RemoteMediaPlayer$11 extends RemoteMediaPlayer$b
{
    final /* synthetic */ RemoteMediaPlayer FK;
    final /* synthetic */ GoogleApiClient FL;
    
    RemoteMediaPlayer$11(final RemoteMediaPlayer fk, final GoogleApiClient fl) {
        this.FK = fk;
        this.FL = fl;
    }
    
    @Override
    protected void a(final ij p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //     7: astore_1       
        //     8: aload_1        
        //     9: monitorenter   
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
        //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.Gb:Lcom/google/android/gms/internal/is;
        //    35: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;)J
        //    38: pop2           
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    43: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    46: aconst_null    
        //    47: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    50: aload_1        
        //    51: monitorexit    
        //    52: return         
        //    53: astore_2       
        //    54: aload_0        
        //    55: aload_0        
        //    56: new             Lcom/google/android/gms/common/api/Status;
        //    59: dup            
        //    60: sipush          2100
        //    63: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
        //    66: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$11.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //    69: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$11.b:(Lcom/google/android/gms/common/api/Result;)V
        //    72: aload_0        
        //    73: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    76: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    79: aconst_null    
        //    80: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    83: goto            50
        //    86: astore_2       
        //    87: aload_1        
        //    88: monitorexit    
        //    89: aload_2        
        //    90: athrow         
        //    91: astore_2       
        //    92: aload_0        
        //    93: getfield        com/google/android/gms/cast/RemoteMediaPlayer$11.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    96: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    99: aconst_null    
        //   100: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   103: aload_2        
        //   104: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     24     86     91     Any
        //  24     39     53     86     Ljava/io/IOException;
        //  24     39     91     105    Any
        //  39     50     86     91     Any
        //  50     52     86     91     Any
        //  54     72     91     105    Any
        //  72     83     86     91     Any
        //  87     89     86     91     Any
        //  92     105    86     91     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
