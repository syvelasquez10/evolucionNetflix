// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.dn;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Result;
import java.io.IOException;
import com.google.android.gms.internal.dg;
import com.google.android.gms.common.api.Api;
import org.json.JSONObject;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.dm;
import com.google.android.gms.internal.dl;

public class RemoteMediaPlayer implements MessageReceivedCallback
{
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2;
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_REPLACED = 4;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 3;
    private final Object fx;
    private final dl ld;
    private final a le;
    private OnMetadataUpdatedListener lf;
    private OnStatusUpdatedListener lg;
    
    public RemoteMediaPlayer() {
        this.fx = new Object();
        this.le = new a();
        (this.ld = new dl() {
            @Override
            protected void onMetadataUpdated() {
                RemoteMediaPlayer.this.onMetadataUpdated();
            }
            
            @Override
            protected void onStatusUpdated() {
                RemoteMediaPlayer.this.onStatusUpdated();
            }
        }).a(this.le);
    }
    
    private void onMetadataUpdated() {
        if (this.lf != null) {
            this.lf.onMetadataUpdated();
        }
    }
    
    private void onStatusUpdated() {
        if (this.lg != null) {
            this.lg.onStatusUpdated();
        }
    }
    
    public long getApproximateStreamPosition() {
        synchronized (this.fx) {
            return this.ld.getApproximateStreamPosition();
        }
    }
    
    public MediaInfo getMediaInfo() {
        synchronized (this.fx) {
            return this.ld.getMediaInfo();
        }
    }
    
    public MediaStatus getMediaStatus() {
        synchronized (this.fx) {
            return this.ld.getMediaStatus();
        }
    }
    
    public String getNamespace() {
        return this.ld.getNamespace();
    }
    
    public long getStreamDuration() {
        synchronized (this.fx) {
            return this.ld.getStreamDuration();
        }
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo) {
        return this.load(googleApiClient, mediaInfo, true, 0L, null);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b) {
        return this.load(googleApiClient, mediaInfo, b, 0L, null);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n) {
        return this.load(googleApiClient, mediaInfo, b, n, null);
    }
    
    public PendingResult<MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final dg p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.li:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/dl;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lv:Lcom/google/android/gms/internal/dn;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lj:Lcom/google/android/gms/cast/MediaInfo;
                //    39: aload_0        
                //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lk:Z
                //    43: aload_0        
                //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.ll:J
                //    47: aload_0        
                //    48: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lm:Lorg/json/JSONObject;
                //    51: invokevirtual   com/google/android/gms/internal/dl.a:(Lcom/google/android/gms/internal/dn;Lcom/google/android/gms/cast/MediaInfo;ZJLorg/json/JSONObject;)J
                //    54: pop2           
                //    55: aload_0        
                //    56: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    59: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    62: aconst_null    
                //    63: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    66: aload_1        
                //    67: monitorexit    
                //    68: return         
                //    69: astore_2       
                //    70: aload_0        
                //    71: aload_0        
                //    72: new             Lcom/google/android/gms/common/api/Status;
                //    75: dup            
                //    76: iconst_1       
                //    77: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //    80: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$2.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    83: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$2.a:(Lcom/google/android/gms/common/api/Result;)V
                //    86: aload_0        
                //    87: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    90: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    93: aconst_null    
                //    94: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    97: goto            66
                //   100: astore_2       
                //   101: aload_1        
                //   102: monitorexit    
                //   103: aload_2        
                //   104: athrow         
                //   105: astore_2       
                //   106: aload_0        
                //   107: getfield        com/google/android/gms/cast/RemoteMediaPlayer$2.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   110: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   113: aconst_null    
                //   114: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   117: aload_2        
                //   118: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  10     24     100    105    Any
                //  24     55     69     100    Ljava/io/IOException;
                //  24     55     105    119    Any
                //  55     66     100    105    Any
                //  66     68     100    105    Any
                //  70     86     105    119    Any
                //  86     97     100    105    Any
                //  101    103    100    105    Any
                //  106    119    100    105    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
        this.ld.B(s2);
    }
    
    public void pause(final GoogleApiClient googleApiClient) throws IOException {
        this.pause(googleApiClient, null);
    }
    
    public void pause(final GoogleApiClient googleApiClient, final JSONObject jsonObject) throws IOException {
        synchronized (this.fx) {
            this.le.b(googleApiClient);
            try {
                this.ld.c(jsonObject);
            }
            finally {
                this.le.b(null);
            }
        }
    }
    
    public void play(final GoogleApiClient googleApiClient) throws IOException, IllegalStateException {
        this.play(googleApiClient, null);
    }
    
    public void play(final GoogleApiClient googleApiClient, final JSONObject jsonObject) throws IOException, IllegalStateException {
        synchronized (this.fx) {
            this.le.b(googleApiClient);
            try {
                this.ld.e(jsonObject);
            }
            finally {
                this.le.b(null);
            }
        }
    }
    
    public PendingResult<MediaChannelResult> requestStatus(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final dg p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.li:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/dl;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lv:Lcom/google/android/gms/internal/dn;
                //    35: invokevirtual   com/google/android/gms/internal/dl.a:(Lcom/google/android/gms/internal/dn;)J
                //    38: pop2           
                //    39: aload_0        
                //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    60: iconst_1       
                //    61: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //    64: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$6.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    67: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$6.a:(Lcom/google/android/gms/common/api/Result;)V
                //    70: aload_0        
                //    71: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    74: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    77: aconst_null    
                //    78: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    81: goto            50
                //    84: astore_2       
                //    85: aload_1        
                //    86: monitorexit    
                //    87: aload_2        
                //    88: athrow         
                //    89: astore_2       
                //    90: aload_0        
                //    91: getfield        com/google/android/gms/cast/RemoteMediaPlayer$6.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    94: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    97: aconst_null    
                //    98: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   101: aload_2        
                //   102: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  10     24     84     89     Any
                //  24     39     53     84     Ljava/io/IOException;
                //  24     39     89     103    Any
                //  39     50     84     89     Any
                //  50     52     84     89     Any
                //  54     70     89     103    Any
                //  70     81     84     89     Any
                //  85     87     84     89     Any
                //  90     103    84     89     Any
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
            protected void a(final dg p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.li:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/dl;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lv:Lcom/google/android/gms/internal/dn;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.ln:J
                //    39: aload_0        
                //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lo:I
                //    43: aload_0        
                //    44: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lm:Lorg/json/JSONObject;
                //    47: invokevirtual   com/google/android/gms/internal/dl.a:(Lcom/google/android/gms/internal/dn;JILorg/json/JSONObject;)J
                //    50: pop2           
                //    51: aload_0        
                //    52: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    72: iconst_1       
                //    73: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //    76: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$3.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    79: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$3.a:(Lcom/google/android/gms/common/api/Result;)V
                //    82: aload_0        
                //    83: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    86: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    89: aconst_null    
                //    90: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    93: goto            62
                //    96: astore_2       
                //    97: aload_1        
                //    98: monitorexit    
                //    99: aload_2        
                //   100: athrow         
                //   101: astore_2       
                //   102: aload_0        
                //   103: getfield        com/google/android/gms/cast/RemoteMediaPlayer$3.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   106: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   109: aconst_null    
                //   110: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   113: aload_2        
                //   114: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  10     24     96     101    Any
                //  24     51     65     96     Ljava/io/IOException;
                //  24     51     101    115    Any
                //  51     62     96     101    Any
                //  62     64     96     101    Any
                //  66     82     101    115    Any
                //  82     93     96     101    Any
                //  97     99     96     101    Any
                //  102    115    96     101    Any
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
    
    public void setOnMetadataUpdatedListener(final OnMetadataUpdatedListener lf) {
        this.lf = lf;
    }
    
    public void setOnStatusUpdatedListener(final OnStatusUpdatedListener lg) {
        this.lg = lg;
    }
    
    public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient googleApiClient, final boolean b) {
        return this.setStreamMute(googleApiClient, b, null);
    }
    
    public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient googleApiClient, final boolean b, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<MediaChannelResult>)new b() {
            protected void a(final dg p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.li:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/dl;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lv:Lcom/google/android/gms/internal/dn;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lq:Z
                //    39: aload_0        
                //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lm:Lorg/json/JSONObject;
                //    43: invokevirtual   com/google/android/gms/internal/dl.a:(Lcom/google/android/gms/internal/dn;ZLorg/json/JSONObject;)J
                //    46: pop2           
                //    47: aload_0        
                //    48: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    68: iconst_1       
                //    69: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //    72: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$5.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    75: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$5.a:(Lcom/google/android/gms/common/api/Result;)V
                //    78: aload_0        
                //    79: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    82: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    85: aconst_null    
                //    86: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    89: goto            58
                //    92: astore_2       
                //    93: aload_1        
                //    94: monitorexit    
                //    95: aload_2        
                //    96: athrow         
                //    97: astore_2       
                //    98: aload_0        
                //    99: aload_0        
                //   100: new             Lcom/google/android/gms/common/api/Status;
                //   103: dup            
                //   104: iconst_1       
                //   105: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //   108: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$5.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //   111: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$5.a:(Lcom/google/android/gms/common/api/Result;)V
                //   114: aload_0        
                //   115: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   118: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   121: aconst_null    
                //   122: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   125: goto            58
                //   128: astore_2       
                //   129: aload_0        
                //   130: getfield        com/google/android/gms/cast/RemoteMediaPlayer$5.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   133: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   136: aconst_null    
                //   137: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   140: aload_2        
                //   141: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  10     24     92     97     Any
                //  24     47     61     92     Ljava/lang/IllegalStateException;
                //  24     47     97     128    Ljava/io/IOException;
                //  24     47     128    142    Any
                //  47     58     92     97     Any
                //  58     60     92     97     Any
                //  62     78     128    142    Any
                //  78     89     92     97     Any
                //  93     95     92     97     Any
                //  98     114    128    142    Any
                //  114    125    92     97     Any
                //  129    142    92     97     Any
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
            protected void a(final dg p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //     4: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.c:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    14: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    17: aload_0        
                //    18: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.li:Lcom/google/android/gms/common/api/GoogleApiClient;
                //    21: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    24: aload_0        
                //    25: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    28: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.e:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/internal/dl;
                //    31: aload_0        
                //    32: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lv:Lcom/google/android/gms/internal/dn;
                //    35: aload_0        
                //    36: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lp:D
                //    39: aload_0        
                //    40: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lm:Lorg/json/JSONObject;
                //    43: invokevirtual   com/google/android/gms/internal/dl.a:(Lcom/google/android/gms/internal/dn;DLorg/json/JSONObject;)J
                //    46: pop2           
                //    47: aload_0        
                //    48: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
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
                //    68: iconst_1       
                //    69: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //    72: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //    75: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.a:(Lcom/google/android/gms/common/api/Result;)V
                //    78: aload_0        
                //    79: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //    82: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //    85: aconst_null    
                //    86: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //    89: goto            58
                //    92: astore_2       
                //    93: aload_1        
                //    94: monitorexit    
                //    95: aload_2        
                //    96: athrow         
                //    97: astore_2       
                //    98: aload_0        
                //    99: aload_0        
                //   100: new             Lcom/google/android/gms/common/api/Status;
                //   103: dup            
                //   104: iconst_1       
                //   105: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //   108: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //   111: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.a:(Lcom/google/android/gms/common/api/Result;)V
                //   114: aload_0        
                //   115: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   118: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   121: aconst_null    
                //   122: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   125: goto            58
                //   128: astore_2       
                //   129: aload_0        
                //   130: aload_0        
                //   131: new             Lcom/google/android/gms/common/api/Status;
                //   134: dup            
                //   135: iconst_1       
                //   136: invokespecial   com/google/android/gms/common/api/Status.<init>:(I)V
                //   139: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.k:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
                //   142: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$4.a:(Lcom/google/android/gms/common/api/Result;)V
                //   145: aload_0        
                //   146: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   149: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   152: aconst_null    
                //   153: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   156: goto            58
                //   159: astore_2       
                //   160: aload_0        
                //   161: getfield        com/google/android/gms/cast/RemoteMediaPlayer$4.lh:Lcom/google/android/gms/cast/RemoteMediaPlayer;
                //   164: invokestatic    com/google/android/gms/cast/RemoteMediaPlayer.d:(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$a;
                //   167: aconst_null    
                //   168: invokevirtual   com/google/android/gms/cast/RemoteMediaPlayer$a.b:(Lcom/google/android/gms/common/api/GoogleApiClient;)V
                //   171: aload_2        
                //   172: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  10     24     92     97     Any
                //  24     47     61     92     Ljava/lang/IllegalStateException;
                //  24     47     97     128    Ljava/lang/IllegalArgumentException;
                //  24     47     128    159    Ljava/io/IOException;
                //  24     47     159    173    Any
                //  47     58     92     97     Any
                //  58     60     92     97     Any
                //  62     78     159    173    Any
                //  78     89     92     97     Any
                //  93     95     92     97     Any
                //  98     114    159    173    Any
                //  114    125    92     97     Any
                //  129    145    159    173    Any
                //  145    156    92     97     Any
                //  160    173    92     97     Any
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
    
    public void stop(final GoogleApiClient googleApiClient) throws IOException {
        this.stop(googleApiClient, null);
    }
    
    public void stop(final GoogleApiClient googleApiClient, final JSONObject jsonObject) throws IOException {
        synchronized (this.fx) {
            this.le.b(googleApiClient);
            try {
                this.ld.d(jsonObject);
            }
            finally {
                this.le.b(null);
            }
        }
    }
    
    public interface MediaChannelResult extends Result
    {
    }
    
    public interface OnMetadataUpdatedListener
    {
        void onMetadataUpdated();
    }
    
    public interface OnStatusUpdatedListener
    {
        void onStatusUpdated();
    }
    
    private class a implements dm
    {
        private GoogleApiClient lr;
        private long ls;
        
        public a() {
            this.ls = 0L;
        }
        
        @Override
        public void a(final String s, final String s2, final long n, final String s3) throws IOException {
            if (this.lr == null) {
                throw new IOException("No GoogleApiClient available");
            }
            Cast.CastApi.sendMessage(this.lr, s, s2).setResultCallback(new RemoteMediaPlayer.a.a(n));
        }
        
        @Override
        public long aR() {
            return ++this.ls;
        }
        
        public void b(final GoogleApiClient lr) {
            this.lr = lr;
        }
        
        private final class a implements ResultCallback<Status>
        {
            private final long lt;
            
            a(final long lt) {
                this.lt = lt;
            }
            
            public void j(final Status status) {
                if (!status.isSuccess()) {
                    RemoteMediaPlayer.this.ld.a(this.lt, status.getStatusCode());
                }
            }
        }
    }
    
    private abstract static class b extends Cast.a<MediaChannelResult>
    {
        dn lv;
        
        b() {
            this.lv = new dn() {
                @Override
                public void a(final long n, final int n2, final JSONObject jsonObject) {
                    ((a.a<R, A>)b.this).a((R)new RemoteMediaPlayer.c(new Status(n2), jsonObject));
                }
                
                @Override
                public void g(final long n) {
                    ((a.a<R, A>)b.this).a((R)b.this.k(new Status(4)));
                }
            };
        }
        
        public MediaChannelResult k(final Status status) {
            return new MediaChannelResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private static final class c implements MediaChannelResult
    {
        private final Status jY;
        private final JSONObject kM;
        
        c(final Status jy, final JSONObject km) {
            this.jY = jy;
            this.kM = km;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
}
