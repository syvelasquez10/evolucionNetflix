// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.android.app.Status;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import org.json.JSONObject;

public class RefreshOfflineManifestRequest extends FetchManifestsRequest
{
    private static String LINKS;
    private static String LINK_LICENSE;
    private static String TAG;
    private static JSONObject mOldLinks;
    
    static {
        RefreshOfflineManifestRequest.TAG = RefreshOfflineManifestRequest.class.getSimpleName();
        RefreshOfflineManifestRequest.LINKS = "links";
        RefreshOfflineManifestRequest.LINK_LICENSE = "license";
    }
    
    public RefreshOfflineManifestRequest(final String s, final NfManifest nfManifest, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        super(s, bladeRunnerWebCallback);
        RefreshOfflineManifestRequest.mOldLinks = nfManifest.getLinks();
    }
    
    private JSONObject replaceLinks(final JSONObject jsonObject, JSONObject optJSONObject) {
        try {
            final JSONObject optJSONObject2 = jsonObject.optJSONObject(RefreshOfflineManifestRequest.LINKS);
            optJSONObject = optJSONObject.optJSONObject(RefreshOfflineManifestRequest.LINK_LICENSE);
            if (optJSONObject != null) {
                optJSONObject2.put(RefreshOfflineManifestRequest.LINK_LICENSE, (Object)optJSONObject);
            }
            jsonObject.putOpt(RefreshOfflineManifestRequest.LINKS, (Object)optJSONObject2);
            return jsonObject;
        }
        catch (JSONException ex) {
            Log.e(RefreshOfflineManifestRequest.TAG, "error injecting old links into manifest", (Throwable)ex);
            return jsonObject;
        }
    }
    
    private Status replaceLinksAndGetStatus(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/netflix/mediaclient/android/app/CommonStatus.BLADERUNNER_FAILURE:Lcom/netflix/mediaclient/android/app/NetflixImmutableStatus;
        //     3: astore_2       
        //     4: aload_2        
        //     5: astore_3       
        //     6: aload_1        
        //     7: ifnull          141
        //    10: aload_1        
        //    11: invokevirtual   org/json/JSONObject.keys:()Ljava/util/Iterator;
        //    14: astore          4
        //    16: aload           4
        //    18: invokeinterface java/util/Iterator.hasNext:()Z
        //    23: ifeq            139
        //    26: aload           4
        //    28: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    33: checkcast       Ljava/lang/String;
        //    36: astore          5
        //    38: aload_1        
        //    39: aload           5
        //    41: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    44: astore          6
        //    46: aload           6
        //    48: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerErrorStatus$BrRequestType.OfflineManifestRefresh:Lcom/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerErrorStatus$BrRequestType;
        //    51: invokestatic    com/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerParseUtils.getStatus:(Lorg/json/JSONObject;Lcom/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerErrorStatus$BrRequestType;)Lcom/netflix/mediaclient/android/app/Status;
        //    54: astore_3       
        //    55: aload_3        
        //    56: invokeinterface com/netflix/mediaclient/android/app/Status.isError:()Z
        //    61: ifeq            93
        //    64: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/volley/RefreshOfflineManifestRequest.TAG:Ljava/lang/String;
        //    67: ldc             "manifest for %s has errors, status: %s"
        //    69: iconst_2       
        //    70: anewarray       Ljava/lang/Object;
        //    73: dup            
        //    74: iconst_0       
        //    75: aload           5
        //    77: aastore        
        //    78: dup            
        //    79: iconst_1       
        //    80: aload_3        
        //    81: invokeinterface com/netflix/mediaclient/android/app/Status.getStatusCode:()Lcom/netflix/mediaclient/StatusCode;
        //    86: aastore        
        //    87: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //    90: pop            
        //    91: aload_3        
        //    92: areturn        
        //    93: aload_3        
        //    94: astore_2       
        //    95: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/volley/RefreshOfflineManifestRequest.mOldLinks:Lorg/json/JSONObject;
        //    98: ifnull          16
        //   101: aload_0        
        //   102: aload           6
        //   104: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/volley/RefreshOfflineManifestRequest.mOldLinks:Lorg/json/JSONObject;
        //   107: invokespecial   com/netflix/mediaclient/service/player/bladerunnerclient/volley/RefreshOfflineManifestRequest.replaceLinks:(Lorg/json/JSONObject;Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //   110: pop            
        //   111: aload_3        
        //   112: astore_2       
        //   113: goto            16
        //   116: astore_1       
        //   117: aload_3        
        //   118: astore_2       
        //   119: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/volley/RefreshOfflineManifestRequest.TAG:Ljava/lang/String;
        //   122: ldc             "parsing manifest error"
        //   124: aload_1        
        //   125: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   128: pop            
        //   129: aload_2        
        //   130: areturn        
        //   131: astore_1       
        //   132: goto            119
        //   135: astore_1       
        //   136: goto            119
        //   139: aload_2        
        //   140: astore_3       
        //   141: aload_3        
        //   142: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  10     16     131    135    Lorg/json/JSONException;
        //  16     55     135    139    Lorg/json/JSONException;
        //  55     91     116    119    Lorg/json/JSONException;
        //  95     111    116    119    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0016:
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
    
    @Override
    protected void onSuccess(JSONObject resultObject) {
        resultObject = this.getResultObject(resultObject);
        final Status replaceLinksAndGetStatus = this.replaceLinksAndGetStatus(resultObject);
        if (this.responseCallback != null) {
            this.responseCallback.onManifestsFetched(resultObject, replaceLinksAndGetStatus);
            return;
        }
        Log.w(RefreshOfflineManifestRequest.TAG, "callback null?");
    }
}
