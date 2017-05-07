// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;
import org.json.JSONObject;
import com.google.android.gms.common.api.GoogleApiClient;

class RemoteMediaPlayer$10 extends RemoteMediaPlayer$b
{
    final /* synthetic */ RemoteMediaPlayer FK;
    final /* synthetic */ GoogleApiClient FL;
    final /* synthetic */ JSONObject FS;
    final /* synthetic */ boolean FW;
    
    RemoteMediaPlayer$10(final RemoteMediaPlayer fk, final GoogleApiClient fl, final boolean fw, final JSONObject fs) {
        this.FK = fk;
        this.FL = fl;
        this.FW = fw;
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
        //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //     7: astore_1       
        //     8: aload_1        
        //     9: monitorenter   
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
        //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.Gb:Lcom/google/android/gms/internal/is;
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FW:Z
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FS:Lorg/json/JSONObject;
        //    43: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;ZLorg/json/JSONObject;)J
        //    46: pop2           
        //    47: aload_0        
        //    48: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    51: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    54: aconst_null    
        //    55: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    58: aload_1        
        //    59: monitorexit    
        //    60: return         
        //    61: astore_2       
        //    62: aload_0        
        //    63: aload_0        
        //    64: new             Lcom/google/android/gms/common/api/Status;
        //    67: dup            
        //    68: sipush          2100
        //    71: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
        //    74: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$10.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //    77: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$10.b:(Lcom/google/android/gms/common/api/Result;)V
        //    80: aload_0        
        //    81: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    84: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    87: aconst_null    
        //    88: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    91: goto            58
        //    94: astore_2       
        //    95: aload_1        
        //    96: monitorexit    
        //    97: aload_2        
        //    98: athrow         
        //    99: astore_2       
        //   100: aload_0        
        //   101: aload_0        
        //   102: new             Lcom/google/android/gms/common/api/Status;
        //   105: dup            
        //   106: sipush          2100
        //   109: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
        //   112: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$10.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //   115: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$10.b:(Lcom/google/android/gms/common/api/Result;)V
        //   118: aload_0        
        //   119: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   122: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //   125: aconst_null    
        //   126: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   129: goto            58
        //   132: astore_2       
        //   133: aload_0        
        //   134: getfield        com/google/android/gms/cast/RemoteMediaPlayer$10.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   137: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //   140: aconst_null    
        //   141: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   144: aload_2        
        //   145: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  10     24     94     99     Any
        //  24     47     61     94     Ljava/lang/IllegalStateException;
        //  24     47     99     132    Ljava/io/IOException;
        //  24     47     132    146    Any
        //  47     58     94     99     Any
        //  58     60     94     99     Any
        //  62     80     132    146    Any
        //  80     91     94     99     Any
        //  95     97     94     99     Any
        //  100    118    132    146    Any
        //  118    129    94     99     Any
        //  133    146    94     99     Any
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
}
