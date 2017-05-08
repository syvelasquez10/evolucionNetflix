// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import org.json.JSONObject;
import com.netflix.mediaclient.service.msl.volley.ApiFalkorMSLVolleyRequest;

public class FetchManifestsRequest extends ApiFalkorMSLVolleyRequest<JSONObject>
{
    private static final String TAG = "nf_msl_volley_FetchManifestsRequest";
    private final String pqlQuery1;
    private final String requestParam;
    protected final BladeRunnerWebCallback responseCallback;
    
    public FetchManifestsRequest(final String requestParam, final BladeRunnerWebCallback responseCallback) {
        this.requestParam = requestParam;
        this.responseCallback = responseCallback;
        this.pqlQuery1 = "['manifests']";
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected Map<String, String> getParams() {
        final Map<String, String> params = super.getParams();
        params.put("bladerunnerParams", this.requestParam);
        return params;
    }
    
    protected JSONObject getResultObject(JSONObject jsonObject) {
        jsonObject = BladerunnerParseUtils.getJSONObject("nf_msl_volley_FetchManifestsRequest", "manifests", jsonObject);
        if (jsonObject != null) {
            return jsonObject.optJSONObject("result");
        }
        return null;
    }
    
    protected Status getStatus(final JSONObject p0) {
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
        //     7: ifnull          92
        //    10: aload_1        
        //    11: invokevirtual   org/json/JSONObject.keys:()Ljava/util/Iterator;
        //    14: astore          4
        //    16: aload_2        
        //    17: astore_3       
        //    18: aload           4
        //    20: invokeinterface java/util/Iterator.hasNext:()Z
        //    25: ifeq            116
        //    28: aload           4
        //    30: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    35: checkcast       Ljava/lang/String;
        //    38: astore          5
        //    40: aload_1        
        //    41: aload           5
        //    43: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    46: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerErrorStatus$BrRequestType.OfflineManifest:Lcom/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerErrorStatus$BrRequestType;
        //    49: invokestatic    com/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerParseUtils.getStatus:(Lorg/json/JSONObject;Lcom/netflix/mediaclient/service/player/bladerunnerclient/volley/BladerunnerErrorStatus$BrRequestType;)Lcom/netflix/mediaclient/android/app/Status;
        //    52: astore_2       
        //    53: aload_2        
        //    54: astore_3       
        //    55: aload_2        
        //    56: invokeinterface com/netflix/mediaclient/android/app/Status.isError:()Z
        //    61: ifeq            18
        //    64: ldc             "nf_msl_volley_FetchManifestsRequest"
        //    66: ldc             "manifest for %s has errors, status: %s"
        //    68: iconst_2       
        //    69: anewarray       Ljava/lang/Object;
        //    72: dup            
        //    73: iconst_0       
        //    74: aload           5
        //    76: aastore        
        //    77: dup            
        //    78: iconst_1       
        //    79: aload_2        
        //    80: invokeinterface com/netflix/mediaclient/android/app/Status.getStatusCode:()Lcom/netflix/mediaclient/StatusCode;
        //    85: aastore        
        //    86: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //    89: pop            
        //    90: aload_2        
        //    91: astore_3       
        //    92: aload_3        
        //    93: areturn        
        //    94: astore_1       
        //    95: ldc             "nf_msl_volley_FetchManifestsRequest"
        //    97: ldc             "parsing manifest error"
        //    99: aload_1        
        //   100: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   103: pop            
        //   104: aload_2        
        //   105: areturn        
        //   106: astore_1       
        //   107: goto            95
        //   110: astore_1       
        //   111: aload_3        
        //   112: astore_2       
        //   113: goto            95
        //   116: aload_3        
        //   117: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  10     16     94     95     Lorg/json/JSONException;
        //  18     53     110    116    Lorg/json/JSONException;
        //  55     90     106    110    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onManifestsFetched(null, status);
            return;
        }
        Log.w("nf_msl_volley_FetchManifestsRequest", "callback null?");
    }
    
    @Override
    protected void onSuccess(JSONObject resultObject) {
        resultObject = this.getResultObject(resultObject);
        final Status status = this.getStatus(resultObject);
        if (this.responseCallback != null) {
            this.responseCallback.onManifestsFetched(resultObject, status);
            return;
        }
        Log.w("nf_msl_volley_FetchManifestsRequest", "callback null?");
    }
    
    @Override
    protected JSONObject parseFalkorResponse(final String s) {
        Log.dumpVerbose("nf_msl_volley_FetchManifestsRequest", "parseFalkorResponse " + s);
        try {
            return new JSONObject(s);
        }
        catch (JSONException ex) {
            Log.e("nf_msl_volley_FetchManifestsRequest", "error parsing json", (Throwable)ex);
            return null;
        }
    }
}
