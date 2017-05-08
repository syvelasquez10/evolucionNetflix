// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import org.json.JSONObject;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.ConnectivityUtils$NetType;

public final class SessionParams
{
    private static final String PARAM_ENABLE_PREVIEW_CONTENT = "supportsPreviewContent";
    private static final String PARAM_IS_BROWSE_PLAY = "isBrowsePlay";
    private static final String PARAM_PIN_VERIFY_CAPABILITY = "pinCapableClient";
    public static final String PARAM_PLAY_CONTEXT = "uiplaycontext";
    private static final String PARAM_PLAY_CONTEXT_LIST_POS = "row";
    private static final String PARAM_PLAY_CONTEXT_REQ_ID = "request_id";
    private static final String PARAM_PLAY_CONTEXT_VIDEO_POS = "rank";
    private static final String PARAM_PLAY_MOBILE_ASN_FILTERING = "filterBasedOnMobileASN";
    protected static final String TAG = "nf_invoke";
    private boolean mDontFilterForMobileAsn;
    private ConnectivityUtils$NetType mNetType;
    private PlayContext mPlayContext;
    
    public SessionParams(final Context context, final PlayContext mPlayContext, final ConnectivityUtils$NetType mNetType) {
        if (mPlayContext == null) {
            throw new IllegalArgumentException("Play context can not be null!");
        }
        this.mPlayContext = mPlayContext;
        this.mNetType = mNetType;
        if (!BandwidthUtility.isDataSaverDisabled(context)) {
            this.mDontFilterForMobileAsn = true;
        }
    }
    
    public JSONObject getParams() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_1       
        //     8: aload_1        
        //     9: aload_0        
        //    10: getfield        com/netflix/mediaclient/service/player/SessionParams.mNetType:Lcom/netflix/mediaclient/util/ConnectivityUtils$NetType;
        //    13: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.fillNetworkType:(Lorg/json/JSONObject;Lcom/netflix/mediaclient/util/ConnectivityUtils$NetType;)Lorg/json/JSONObject;
        //    16: pop            
        //    17: aload_1        
        //    18: ldc             "pinCapableClient"
        //    20: iconst_1       
        //    21: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    24: pop            
        //    25: aload_0        
        //    26: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    29: ifnull          175
        //    32: new             Lorg/json/JSONObject;
        //    35: dup            
        //    36: invokespecial   org/json/JSONObject.<init>:()V
        //    39: astore_2       
        //    40: aload_2        
        //    41: ldc             "request_id"
        //    43: aload_0        
        //    44: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    47: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getRequestId:()Ljava/lang/String;
        //    52: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    55: pop            
        //    56: aload_2        
        //    57: ldc             "row"
        //    59: aload_0        
        //    60: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    63: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getListPos:()I
        //    68: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    71: pop            
        //    72: aload_2        
        //    73: ldc             "rank"
        //    75: aload_0        
        //    76: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    79: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getVideoPos:()I
        //    84: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    87: pop            
        //    88: aload_1        
        //    89: ldc             "uiplaycontext"
        //    91: aload_2        
        //    92: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    95: pop            
        //    96: aload_1        
        //    97: ldc             "isBrowsePlay"
        //    99: aload_0        
        //   100: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   103: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getBrowsePlay:()Z
        //   108: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   111: pop            
        //   112: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   115: ifeq            175
        //   118: ldc             "nf_invoke"
        //   120: ldc             "DEBUG info: reqId %s, listPos %d,  videoPos %d"
        //   122: iconst_3       
        //   123: anewarray       Ljava/lang/Object;
        //   126: dup            
        //   127: iconst_0       
        //   128: aload_0        
        //   129: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   132: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getRequestId:()Ljava/lang/String;
        //   137: aastore        
        //   138: dup            
        //   139: iconst_1       
        //   140: aload_0        
        //   141: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   144: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getListPos:()I
        //   149: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   152: aastore        
        //   153: dup            
        //   154: iconst_2       
        //   155: aload_0        
        //   156: getfield        com/netflix/mediaclient/service/player/SessionParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   159: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getVideoPos:()I
        //   164: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   167: aastore        
        //   168: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   171: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   174: pop            
        //   175: aload_0        
        //   176: getfield        com/netflix/mediaclient/service/player/SessionParams.mDontFilterForMobileAsn:Z
        //   179: ifeq            190
        //   182: aload_1        
        //   183: ldc             "filterBasedOnMobileASN"
        //   185: iconst_0       
        //   186: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   189: pop            
        //   190: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   193: ifeq            218
        //   196: ldc             "nf_invoke"
        //   198: ldc             "DEBUG info: sessionParams: %s"
        //   200: iconst_1       
        //   201: anewarray       Ljava/lang/Object;
        //   204: dup            
        //   205: iconst_0       
        //   206: aload_1        
        //   207: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   217: pop            
        //   218: aload_1        
        //   219: areturn        
        //   220: astore_2       
        //   221: aconst_null    
        //   222: astore_1       
        //   223: ldc             "nf_invoke"
        //   225: ldc             "Failed to create JSON object"
        //   227: aload_2        
        //   228: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   231: pop            
        //   232: aload_1        
        //   233: areturn        
        //   234: astore_2       
        //   235: goto            223
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      220    223    Lorg/json/JSONException;
        //  8      175    234    238    Lorg/json/JSONException;
        //  175    190    234    238    Lorg/json/JSONException;
        //  190    218    234    238    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0175:
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
