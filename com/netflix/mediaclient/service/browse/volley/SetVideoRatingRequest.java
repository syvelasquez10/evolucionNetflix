// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class SetVideoRatingRequest extends FalkorVolleyWebClientRequest<UserRating>
{
    private static final String FIELD_PATH = "path";
    private static final String FIELD_VALUE = "value";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_setvideoratingrequest";
    private final BrowseWebClientCache browseCache;
    private final int mNewRating;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int trackId;
    
    public SetVideoRatingRequest(final Context context, final BrowseWebClientCache browseCache, final String mVideoId, final int mNewRating, final int trackId, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mVideoId = mVideoId;
        this.mNewRating = mNewRating;
        this.trackId = trackId;
        this.browseCache = browseCache;
        this.pqlQuery = String.format("['videos', '%s', 'setRating']", this.mVideoId);
        if (Log.isLoggable("nf_service_browse_setvideoratingrequest", 2)) {
            Log.v("nf_service_browse_setvideoratingrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static void updateMdpWithNewRating(final BrowseWebClientCache browseWebClientCache, final String s, final String s2, final float n) {
        synchronized (SetVideoRatingRequest.class) {
            if (s2.equals("movies")) {
                final MovieDetails movieDetails = (MovieDetails)browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, s, "0", "0"));
                if (movieDetails != null && movieDetails.rating != null) {
                    movieDetails.rating.userRating = n;
                }
            }
            else {
                final ShowDetails showDetails = (ShowDetails)browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, s, "0", "0"));
                if (showDetails != null && showDetails.rating != null) {
                    showDetails.rating.userRating = n;
                }
            }
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final String string = "&" + "param" + "=";
        final StringBuilder sb = new StringBuilder();
        sb.append(string).append(this.mNewRating);
        sb.append(string).append(this.trackId);
        Log.d("nf_service_browse_setvideoratingrequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_setvideoratingrequest", "SetVideoRatingRequest6 finished onFailure statusCode=" + status);
            this.responseCallback.onVideoRatingSet(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final UserRating userRating) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_setvideoratingrequest", "SetVideoRatingRequest6 finished onSuccess");
            this.responseCallback.onVideoRatingSet(userRating, CommonStatus.OK);
        }
    }
    
    @Override
    protected UserRating parseFalkorResponse(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "nf_service_browse_setvideoratingrequest"
        //     2: iconst_2       
        //     3: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //     6: ifeq            34
        //     9: ldc             "nf_service_browse_setvideoratingrequest"
        //    11: new             Ljava/lang/StringBuilder;
        //    14: dup            
        //    15: invokespecial   java/lang/StringBuilder.<init>:()V
        //    18: ldc             "String response to parse = "
        //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    23: aload_1        
        //    24: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    27: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    30: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    33: pop            
        //    34: new             Lcom/google/gson/JsonParser;
        //    37: dup            
        //    38: invokespecial   com/google/gson/JsonParser.<init>:()V
        //    41: aload_1        
        //    42: invokevirtual   com/google/gson/JsonParser.parse:(Ljava/lang/String;)Lcom/google/gson/JsonElement;
        //    45: invokevirtual   com/google/gson/JsonElement.getAsJsonObject:()Lcom/google/gson/JsonObject;
        //    48: astore_2       
        //    49: aload_2        
        //    50: ldc             "value"
        //    52: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //    55: astore_3       
        //    56: aload_3        
        //    57: invokestatic    com/netflix/mediaclient/service/webclient/volley/FalkorParseUtils.isEmpty:(Lcom/google/gson/JsonObject;)Z
        //    60: ifeq            121
        //    63: new             Lcom/netflix/mediaclient/service/webclient/volley/FalkorParseException;
        //    66: dup            
        //    67: ldc             "setRating failed ?"
        //    69: invokespecial   com/netflix/mediaclient/service/webclient/volley/FalkorParseException.<init>:(Ljava/lang/String;)V
        //    72: athrow         
        //    73: astore_2       
        //    74: ldc             "nf_service_browse_setvideoratingrequest"
        //    76: bipush          6
        //    78: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    81: ifeq            110
        //    84: ldc             "nf_service_browse_setvideoratingrequest"
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: invokespecial   java/lang/StringBuilder.<init>:()V
        //    93: ldc             "String response to parse = "
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: aload_1        
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   105: aload_2        
        //   106: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   109: pop            
        //   110: new             Lcom/netflix/mediaclient/service/webclient/volley/FalkorParseException;
        //   113: dup            
        //   114: ldc             "Error in creating JsonObject"
        //   116: aload_2        
        //   117: invokespecial   com/netflix/mediaclient/service/webclient/volley/FalkorParseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   120: athrow         
        //   121: aload_2        
        //   122: invokestatic    com/netflix/mediaclient/service/webclient/volley/FalkorParseUtils.hasErrors:(Lcom/google/gson/JsonObject;)Z
        //   125: ifeq            140
        //   128: new             Lcom/netflix/mediaclient/service/webclient/volley/FalkorServerException;
        //   131: dup            
        //   132: aload_2        
        //   133: invokestatic    com/netflix/mediaclient/service/webclient/volley/FalkorParseUtils.getErrorMessage:(Lcom/google/gson/JsonObject;)Ljava/lang/String;
        //   136: invokespecial   com/netflix/mediaclient/service/webclient/volley/FalkorServerException.<init>:(Ljava/lang/String;)V
        //   139: athrow         
        //   140: aload_3        
        //   141: ldc             "videos"
        //   143: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //   146: aload_0        
        //   147: getfield        com/netflix/mediaclient/service/browse/volley/SetVideoRatingRequest.mVideoId:Ljava/lang/String;
        //   150: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //   153: astore_3       
        //   154: aload_3        
        //   155: ldc             "path"
        //   157: invokevirtual   com/google/gson/JsonObject.getAsJsonArray:(Ljava/lang/String;)Lcom/google/gson/JsonArray;
        //   160: iconst_0       
        //   161: invokevirtual   com/google/gson/JsonArray.get:(I)Lcom/google/gson/JsonElement;
        //   164: invokevirtual   com/google/gson/JsonElement.getAsString:()Ljava/lang/String;
        //   167: astore_1       
        //   168: aload_3        
        //   169: ldc             "rating"
        //   171: ldc             Lcom/netflix/mediaclient/service/webclient/model/branches/Video$UserRating;.class
        //   173: invokestatic    com/netflix/mediaclient/service/webclient/volley/FalkorParseUtils.getPropertyObject:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
        //   176: checkcast       Lcom/netflix/mediaclient/service/webclient/model/branches/Video$UserRating;
        //   179: astore_2       
        //   180: ldc             "nf_service_browse_setvideoratingrequest"
        //   182: ldc_w           "VideoId:%s, videoType: %s, newRating:%f"
        //   185: iconst_3       
        //   186: anewarray       Ljava/lang/Object;
        //   189: dup            
        //   190: iconst_0       
        //   191: aload_0        
        //   192: getfield        com/netflix/mediaclient/service/browse/volley/SetVideoRatingRequest.mVideoId:Ljava/lang/String;
        //   195: aastore        
        //   196: dup            
        //   197: iconst_1       
        //   198: aload_1        
        //   199: aastore        
        //   200: dup            
        //   201: iconst_2       
        //   202: aload_2        
        //   203: getfield        com/netflix/mediaclient/service/webclient/model/branches/Video$UserRating.userRating:F
        //   206: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   209: aastore        
        //   210: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   213: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   216: pop            
        //   217: aload_0        
        //   218: getfield        com/netflix/mediaclient/service/browse/volley/SetVideoRatingRequest.browseCache:Lcom/netflix/mediaclient/service/browse/cache/BrowseWebClientCache;
        //   221: aload_0        
        //   222: getfield        com/netflix/mediaclient/service/browse/volley/SetVideoRatingRequest.mVideoId:Ljava/lang/String;
        //   225: aload_1        
        //   226: aload_2        
        //   227: getfield        com/netflix/mediaclient/service/webclient/model/branches/Video$UserRating.userRating:F
        //   230: invokestatic    com/netflix/mediaclient/service/browse/volley/SetVideoRatingRequest.updateMdpWithNewRating:(Lcom/netflix/mediaclient/service/browse/cache/BrowseWebClientCache;Ljava/lang/String;Ljava/lang/String;F)V
        //   233: aload_2        
        //   234: areturn        
        //   235: astore_2       
        //   236: ldc             "nf_service_browse_setvideoratingrequest"
        //   238: bipush          6
        //   240: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   243: ifeq            272
        //   246: ldc             "nf_service_browse_setvideoratingrequest"
        //   248: new             Ljava/lang/StringBuilder;
        //   251: dup            
        //   252: invokespecial   java/lang/StringBuilder.<init>:()V
        //   255: ldc             "String response to parse = "
        //   257: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   260: aload_1        
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   267: aload_2        
        //   268: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   271: pop            
        //   272: new             Lcom/netflix/mediaclient/service/webclient/volley/FalkorParseException;
        //   275: dup            
        //   276: ldc_w           "response missing expected json objects"
        //   279: aload_2        
        //   280: invokespecial   com/netflix/mediaclient/service/webclient/volley/FalkorParseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   283: athrow         
        //   284: astore_1       
        //   285: ldc             "nf_service_browse_setvideoratingrequest"
        //   287: bipush          6
        //   289: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   292: ifeq            325
        //   295: ldc             "nf_service_browse_setvideoratingrequest"
        //   297: new             Ljava/lang/StringBuilder;
        //   300: dup            
        //   301: invokespecial   java/lang/StringBuilder.<init>:()V
        //   304: ldc_w           "setRating PathObj missing in: "
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: aload_2        
        //   311: invokevirtual   com/google/gson/JsonObject.toString:()Ljava/lang/String;
        //   314: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   317: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   320: aload_1        
        //   321: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   324: pop            
        //   325: new             Lcom/netflix/mediaclient/service/webclient/volley/FalkorParseException;
        //   328: dup            
        //   329: ldc_w           "Missing setRatingPathObj"
        //   332: aload_1        
        //   333: invokespecial   com/netflix/mediaclient/service/webclient/volley/FalkorParseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   336: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  34     49     73     121    Ljava/lang/Exception;
        //  140    154    235    284    Ljava/lang/Exception;
        //  154    168    284    337    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0272:
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
