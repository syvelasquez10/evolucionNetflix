// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONObject;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.ConnectivityUtils$NetType;

public class AuthorizationParams
{
    private static final String PARAM_ENABLE_PREVIEW_CONTENT = "supportsPreviewContent";
    private static final String PARAM_IS_BROWSE_PLAY = "isBrowsePlay";
    private static final String PARAM_PIN_VERIFY_CAPABILITY = "pinCapableClient";
    private static final String PARAM_PLAY_CONTEXT = "uiplaycontext";
    private static final String PARAM_PLAY_CONTEXT_LIST_POS = "row";
    private static final String PARAM_PLAY_CONTEXT_REQ_ID = "request_id";
    private static final String PARAM_PLAY_CONTEXT_VIDEO_POS = "rank";
    private static final String PARAM_PLAY_MOBILE_ASN_FILTERING = "filterBasedOnMobileASN";
    protected static final String TAG = "nf_invoke";
    private boolean mDontFilterForMobileAsn;
    private ConnectivityUtils$NetType mNetType;
    private PlayContext mPlayContext;
    private boolean mPreviewContentEnabled;
    
    public AuthorizationParams(final Context context, final PlayContext mPlayContext, final ConnectivityUtils$NetType mNetType, final boolean mPreviewContentEnabled) {
        if (mPlayContext == null) {
            throw new IllegalArgumentException("Play context can not be null!");
        }
        this.mPlayContext = mPlayContext;
        this.mNetType = mNetType;
        this.mPreviewContentEnabled = mPreviewContentEnabled;
        if (!BandwidthUtility.isDataSaverDisabled(context)) {
            this.mDontFilterForMobileAsn = true;
        }
    }
    
    public ConnectivityUtils$NetType getNetType() {
        return this.mNetType;
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
        //    10: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mNetType:Lcom/netflix/mediaclient/util/ConnectivityUtils$NetType;
        //    13: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.fillNetworkType:(Lorg/json/JSONObject;Lcom/netflix/mediaclient/util/ConnectivityUtils$NetType;)Lorg/json/JSONObject;
        //    16: pop            
        //    17: aload_1        
        //    18: ldc             "pinCapableClient"
        //    20: iconst_1       
        //    21: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    24: pop            
        //    25: aload_1        
        //    26: ldc             "supportsPreviewContent"
        //    28: aload_0        
        //    29: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPreviewContentEnabled:Z
        //    32: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    35: pop            
        //    36: aload_0        
        //    37: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    40: ifnull          186
        //    43: new             Lorg/json/JSONObject;
        //    46: dup            
        //    47: invokespecial   org/json/JSONObject.<init>:()V
        //    50: astore_2       
        //    51: aload_2        
        //    52: ldc             "request_id"
        //    54: aload_0        
        //    55: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    58: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getRequestId:()Ljava/lang/String;
        //    63: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    66: pop            
        //    67: aload_2        
        //    68: ldc             "row"
        //    70: aload_0        
        //    71: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    74: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getListPos:()I
        //    79: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    82: pop            
        //    83: aload_2        
        //    84: ldc             "rank"
        //    86: aload_0        
        //    87: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //    90: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getVideoPos:()I
        //    95: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    98: pop            
        //    99: aload_1        
        //   100: ldc             "uiplaycontext"
        //   102: aload_2        
        //   103: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   106: pop            
        //   107: aload_1        
        //   108: ldc             "isBrowsePlay"
        //   110: aload_0        
        //   111: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   114: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getBrowsePlay:()Z
        //   119: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   122: pop            
        //   123: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   126: ifeq            186
        //   129: ldc             "nf_invoke"
        //   131: ldc             "DEBUG info: reqId %s, listPos %d,  videoPos %d"
        //   133: iconst_3       
        //   134: anewarray       Ljava/lang/Object;
        //   137: dup            
        //   138: iconst_0       
        //   139: aload_0        
        //   140: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   143: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getRequestId:()Ljava/lang/String;
        //   148: aastore        
        //   149: dup            
        //   150: iconst_1       
        //   151: aload_0        
        //   152: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   155: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getListPos:()I
        //   160: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   163: aastore        
        //   164: dup            
        //   165: iconst_2       
        //   166: aload_0        
        //   167: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mPlayContext:Lcom/netflix/mediaclient/ui/common/PlayContext;
        //   170: invokeinterface com/netflix/mediaclient/ui/common/PlayContext.getVideoPos:()I
        //   175: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   178: aastore        
        //   179: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   182: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   185: pop            
        //   186: aload_0        
        //   187: getfield        com/netflix/mediaclient/javabridge/invoke/media/AuthorizationParams.mDontFilterForMobileAsn:Z
        //   190: ifeq            201
        //   193: aload_1        
        //   194: ldc             "filterBasedOnMobileASN"
        //   196: iconst_0       
        //   197: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   200: pop            
        //   201: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   204: ifeq            229
        //   207: ldc             "nf_invoke"
        //   209: ldc             "DEBUG info: sessionParams: %s"
        //   211: iconst_1       
        //   212: anewarray       Ljava/lang/Object;
        //   215: dup            
        //   216: iconst_0       
        //   217: aload_1        
        //   218: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   221: aastore        
        //   222: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   225: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   228: pop            
        //   229: aload_1        
        //   230: areturn        
        //   231: astore_2       
        //   232: aconst_null    
        //   233: astore_1       
        //   234: ldc             "nf_invoke"
        //   236: ldc             "Failed to create JSON object"
        //   238: aload_2        
        //   239: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   242: pop            
        //   243: aload_1        
        //   244: areturn        
        //   245: astore_2       
        //   246: goto            234
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      231    234    Lorg/json/JSONException;
        //  8      186    245    249    Lorg/json/JSONException;
        //  186    201    245    249    Lorg/json/JSONException;
        //  201    229    245    249    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
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
    
    public PlayContext getPlayContext() {
        return this.mPlayContext;
    }
}
