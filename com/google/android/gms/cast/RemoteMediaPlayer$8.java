// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;
import org.json.JSONObject;
import com.google.android.gms.common.api.GoogleApiClient;

class RemoteMediaPlayer$8 extends RemoteMediaPlayer$b
{
    final /* synthetic */ RemoteMediaPlayer FK;
    final /* synthetic */ GoogleApiClient FL;
    final /* synthetic */ JSONObject FS;
    final /* synthetic */ long FT;
    final /* synthetic */ int FU;
    
    RemoteMediaPlayer$8(final RemoteMediaPlayer fk, final GoogleApiClient fl, final long ft, final int fu, final JSONObject fs) {
        this.FK = fk;
        this.FL = fl;
        this.FT = ft;
        this.FU = fu;
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
        //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //     7: astore_1       
        //     8: aload_1        
        //     9: monitorenter   
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
        //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.Gb:Lcom/google/android/gms/internal/is;
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FT:J
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FU:I
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FS:Lorg/json/JSONObject;
        //    47: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;JILorg/json/JSONObject;)J
        //    50: pop2           
        //    51: aload_0        
        //    52: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    55: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    58: aconst_null    
        //    59: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    62: aload_1        
        //    63: monitorexit    
        //    64: return         
        //    65: astore_2       
        //    66: aload_0        
        //    67: aload_0        
        //    68: new             Lcom/google/android/gms/common/api/Status;
        //    71: dup            
        //    72: sipush          2100
        //    75: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
        //    78: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$8.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //    81: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$8.b:(Lcom/google/android/gms/common/api/Result;)V
        //    84: aload_0        
        //    85: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    88: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    91: aconst_null    
        //    92: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    95: goto            62
        //    98: astore_2       
        //    99: aload_1        
        //   100: monitorexit    
        //   101: aload_2        
        //   102: athrow         
        //   103: astore_2       
        //   104: aload_0        
        //   105: getfield        com/google/android/gms/cast/RemoteMediaPlayer$8.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   108: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //   111: aconst_null    
        //   112: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   115: aload_2        
        //   116: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     24     98     103    Any
        //  24     51     65     98     Ljava/io/IOException;
        //  24     51     103    117    Any
        //  51     62     98     103    Any
        //  62     64     98     103    Any
        //  66     84     103    117    Any
        //  84     95     98     103    Any
        //  99     101    98     103    Any
        //  104    117    98     103    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
