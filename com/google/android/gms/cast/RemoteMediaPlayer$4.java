// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;
import org.json.JSONObject;
import com.google.android.gms.common.api.GoogleApiClient;

class RemoteMediaPlayer$4 extends RemoteMediaPlayer$b
{
    final /* synthetic */ RemoteMediaPlayer FK;
    final /* synthetic */ GoogleApiClient FL;
    final /* synthetic */ MediaInfo FO;
    final /* synthetic */ boolean FP;
    final /* synthetic */ long FQ;
    final /* synthetic */ long[] FR;
    final /* synthetic */ JSONObject FS;
    
    RemoteMediaPlayer$4(final RemoteMediaPlayer fk, final GoogleApiClient fl, final MediaInfo fo, final boolean fp, final long fq, final long[] fr, final JSONObject fs) {
        this.FK = fk;
        this.FL = fl;
        this.FO = fo;
        this.FP = fp;
        this.FQ = fq;
        this.FR = fr;
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
        //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //     7: astore_1       
        //     8: aload_1        
        //     9: monitorenter   
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
        //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.Gb:Lcom/google/android/gms/internal/is;
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FO:Lcom/google/android/gms/cast/MediaInfo;
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FP:Z
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FQ:J
        //    47: aload_0        
        //    48: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FR:[J
        //    51: aload_0        
        //    52: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FS:Lorg/json/JSONObject;
        //    55: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;Lcom/google/android/gms/cast/MediaInfo;ZJ[JLorg/json/JSONObject;)J
        //    58: pop2           
        //    59: aload_0        
        //    60: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    63: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    66: aconst_null    
        //    67: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //    70: aload_1        
        //    71: monitorexit    
        //    72: return         
        //    73: astore_2       
        //    74: aload_0        
        //    75: aload_0        
        //    76: new             Lcom/google/android/gms/common/api/Status;
        //    79: dup            
        //    80: sipush          2100
        //    83: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
        //    86: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //    89: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.b:(Lcom/google/android/gms/common/api/Result;)V
        //    92: aload_0        
        //    93: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //    96: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //    99: aconst_null    
        //   100: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   103: goto            70
        //   106: astore_2       
        //   107: aload_1        
        //   108: monitorexit    
        //   109: aload_2        
        //   110: athrow         
        //   111: astore_2       
        //   112: aload_0        
        //   113: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   116: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
        //   119: aconst_null    
        //   120: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   123: aload_2        
        //   124: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     24     106    111    Any
        //  24     59     73     106    Ljava/io/IOException;
        //  24     59     111    125    Any
        //  59     70     106    111    Any
        //  70     72     106    111    Any
        //  74     92     111    125    Any
        //  92     103    106    111    Any
        //  107    109    106    111    Any
        //  112    125    106    111    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
