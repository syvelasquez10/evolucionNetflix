// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.internal.is;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import java.io.IOException;
import com.google.android.gms.common.api.Result;
import android.os.RemoteException;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api;
import org.json.JSONObject;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.iq;

public class RemoteMediaPlayer implements MessageReceivedCallback
{
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private final iq FG;
    private final a FH;
    private OnMetadataUpdatedListener FI;
    private OnStatusUpdatedListener FJ;
    private final Object mw;
    
    public RemoteMediaPlayer() {
        this.mw = new Object();
        this.FH = new a();
        (this.FG = new iq() {
            @Override
            protected void onMetadataUpdated() {
                RemoteMediaPlayer.this.onMetadataUpdated();
            }
            
            @Override
            protected void onStatusUpdated() {
                RemoteMediaPlayer.this.onStatusUpdated();
            }
        }).a(this.FH);
    }
    
    private void onMetadataUpdated() {
        if (this.FI != null) {
            this.FI.onMetadataUpdated();
        }
    }
    
    private void onStatusUpdated() {
        if (this.FJ != null) {
            this.FJ.onStatusUpdated();
        }
    }
    
    public long getApproximateStreamPosition() {
        synchronized (this.mw) {
            return this.FG.getApproximateStreamPosition();
        }
    }
    
    public MediaInfo getMediaInfo() {
        synchronized (this.mw) {
            return this.FG.getMediaInfo();
        }
    }
    
    public MediaStatus getMediaStatus() {
        synchronized (this.mw) {
            return this.FG.getMediaStatus();
        }
    }
    
    public String getNamespace() {
        return this.FG.getNamespace();
    }
    
    public long getStreamDuration() {
        synchronized (this.mw) {
            return this.FG.getStreamDuration();
        }
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo) {
        return this.load(googleApiClient, mediaInfo, true, 0L, null, null);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b) {
        return this.load(googleApiClient, mediaInfo, b, 0L, null, null);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n) {
        return this.load(googleApiClient, mediaInfo, b, n, null, null);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n, final JSONObject jsonObject) {
        return this.load(googleApiClient, mediaInfo, b, n, null, jsonObject);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n, final long[] array, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    @Override
    public void onMessageReceived(final CastDevice castDevice, final String s, final String s2) {
        this.FG.aD(s2);
    }
    
    public PendingResult<MediaChannelResult> pause(final GoogleApiClient googleApiClient) {
        return this.pause(googleApiClient, null);
    }
    
    public PendingResult<MediaChannelResult> pause(final GoogleApiClient googleApiClient, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> play(final GoogleApiClient googleApiClient) {
        return this.play(googleApiClient, null);
    }
    
    public PendingResult<MediaChannelResult> play(final GoogleApiClient googleApiClient, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final ij p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.Gb:Lcom/google/android/gms/internal/is;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FS:Lorg/json/JSONObject;
                //    39: invokevirtual   com/google/android/gms/internal/iq.c:(Lcom/google/android/gms/internal/is;Lorg/json/JSONObject;)J
                //    42: pop2           
                //    43: aload_0        
                //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    70: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$7.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    73: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$7.b:(Lcom/google/android/gms/common/api/Result;)V
                //    76: aload_0        
                //    77: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    97: getfield        com/google/android/gms/cast/RemoteMediaPlayer$7.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> requestStatus(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> seek(final GoogleApiClient googleApiClient, final long n) {
        return this.seek(googleApiClient, n, 0, null);
    }
    
    public PendingResult<MediaChannelResult> seek(final GoogleApiClient googleApiClient, final long n, final int n2) {
        return this.seek(googleApiClient, n, n2, null);
    }
    
    public PendingResult<MediaChannelResult> seek(final GoogleApiClient googleApiClient, final long n, final int n2, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> setActiveMediaTracks(final GoogleApiClient googleApiClient, final long[] array) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final ij p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.Gb:Lcom/google/android/gms/internal/is;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FM:[J
                //    39: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;[J)J
                //    42: pop2           
                //    43: aload_0        
                //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    70: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$2.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    73: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$2.b:(Lcom/google/android/gms/common/api/Result;)V
                //    76: aload_0        
                //    77: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    97: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public void setOnMetadataUpdatedListener(final OnMetadataUpdatedListener fi) {
        this.FI = fi;
    }
    
    public void setOnStatusUpdatedListener(final OnStatusUpdatedListener fj) {
        this.FJ = fj;
    }
    
    public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient googleApiClient, final boolean b) {
        return this.setStreamMute(googleApiClient, b, null);
    }
    
    public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient googleApiClient, final boolean b, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> setStreamVolume(final GoogleApiClient googleApiClient, final double n) throws IllegalArgumentException {
        return this.setStreamVolume(googleApiClient, n, null);
    }
    
    public PendingResult<MediaChannelResult> setStreamVolume(final GoogleApiClient googleApiClient, final double n, final JSONObject jsonObject) throws IllegalArgumentException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final ij p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.Gb:Lcom/google/android/gms/internal/is;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FV:D
                //    39: aload_0        
                //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FS:Lorg/json/JSONObject;
                //    43: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;DLorg/json/JSONObject;)J
                //    46: pop2           
                //    47: aload_0        
                //    48: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    74: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$9.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    77: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$9.b:(Lcom/google/android/gms/common/api/Result;)V
                //    80: aload_0        
                //    81: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //   112: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$9.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //   115: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$9.b:(Lcom/google/android/gms/common/api/Result;)V
                //   118: aload_0        
                //   119: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   122: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   125: aconst_null    
                //   126: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   129: goto            58
                //   132: astore_2       
                //   133: aload_0        
                //   134: aload_0        
                //   135: new             Lcom/google/android/gms/common/api/Status;
                //   138: dup            
                //   139: sipush          2100
                //   142: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //   145: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$9.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //   148: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$9.b:(Lcom/google/android/gms/common/api/Result;)V
                //   151: aload_0        
                //   152: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   155: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   158: aconst_null    
                //   159: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   162: goto            58
                //   165: astore_2       
                //   166: aload_0        
                //   167: getfield        com/google/android/gms/cast/RemoteMediaPlayer$9.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   170: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   173: aconst_null    
                //   174: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   177: aload_2        
                //   178: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  10     24     94     99     Any
                //  24     47     61     94     Ljava/lang/IllegalStateException;
                //  24     47     99     132    Ljava/lang/IllegalArgumentException;
                //  24     47     132    165    Ljava/io/IOException;
                //  24     47     165    179    Any
                //  47     58     94     99     Any
                //  58     60     94     99     Any
                //  62     80     165    179    Any
                //  80     91     94     99     Any
                //  95     97     94     99     Any
                //  100    118    165    179    Any
                //  118    129    94     99     Any
                //  133    151    165    179    Any
                //  151    162    94     99     Any
                //  166    179    94     99     Any
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> setTextTrackStyle(final GoogleApiClient googleApiClient, final TextTrackStyle textTrackStyle) {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final ij p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.Gb:Lcom/google/android/gms/internal/is;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FN:Lcom/google/android/gms/cast/TextTrackStyle;
                //    39: invokevirtual   com/google/android/gms/internal/iq.a:(Lcom/google/android/gms/internal/is;Lcom/google/android/gms/cast/TextTrackStyle;)J
                //    42: pop2           
                //    43: aload_0        
                //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    70: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$3.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    73: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$3.b:(Lcom/google/android/gms/common/api/Result;)V
                //    76: aload_0        
                //    77: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    97: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public PendingResult<MediaChannelResult> stop(final GoogleApiClient googleApiClient) {
        return this.stop(googleApiClient, null);
    }
    
    public PendingResult<MediaChannelResult> stop(final GoogleApiClient googleApiClient, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final ij p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FL:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/iq;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.Gb:Lcom/google/android/gms/internal/is;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FS:Lorg/json/JSONObject;
                //    39: invokevirtual   com/google/android/gms/internal/iq.b:(Lcom/google/android/gms/internal/is;Lorg/json/JSONObject;)J
                //    42: pop2           
                //    43: aload_0        
                //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    70: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$6.l:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    73: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$6.b:(Lcom/google/android/gms/common/api/Result;)V
                //    76: aload_0        
                //    77: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    97: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.FK:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public interface MediaChannelResult extends Result
    {
        JSONObject getCustomData();
    }
    
    public interface OnMetadataUpdatedListener
    {
        void onMetadataUpdated();
    }
    
    public interface OnStatusUpdatedListener
    {
        void onStatusUpdated();
    }
    
    private class a implements ir
    {
        private GoogleApiClient FX;
        private long FY;
        
        public a() {
            this.FY = 0L;
        }
        
        @Override
        public void a(final String s, final String s2, final long n, final String s3) throws IOException {
            if (this.FX == null) {
                throw new IOException("No GoogleApiClient available");
            }
            Cast.CastApi.sendMessage(this.FX, s, s2).setResultCallback(new RemoteMediaPlayer.a.a(n));
        }
        
        public void b(final GoogleApiClient fx) {
            this.FX = fx;
        }
        
        @Override
        public long fy() {
            return ++this.FY;
        }
        
        private final class a implements ResultCallback<Status>
        {
            private final long FZ;
            
            a(final long fz) {
                this.FZ = fz;
            }
            
            public void k(final Status status) {
                if (!status.isSuccess()) {
                    RemoteMediaPlayer.this.FG.b(this.FZ, status.getStatusCode());
                }
            }
        }
    }
    
    private abstract static class b extends Cast.a<MediaChannelResult>
    {
        is Gb;
        
        b() {
            this.Gb = new is() {
                @Override
                public void a(final long n, final int n2, final JSONObject jsonObject) {
                    ((BaseImplementation.AbstractPendingResult<R>)b.this).b((R)new RemoteMediaPlayer.c(new Status(n2), jsonObject));
                }
                
                @Override
                public void n(final long n) {
                    ((BaseImplementation.AbstractPendingResult<R>)b.this).b((R)b.this.l(new Status(2103)));
                }
            };
        }
        
        public MediaChannelResult l(final Status status) {
            return new MediaChannelResult() {
                @Override
                public JSONObject getCustomData() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private static final class c implements MediaChannelResult
    {
        private final Status CM;
        private final JSONObject Fl;
        
        c(final Status cm, final JSONObject fl) {
            this.CM = cm;
            this.Fl = fl;
        }
        
        @Override
        public JSONObject getCustomData() {
            return this.Fl;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
}
