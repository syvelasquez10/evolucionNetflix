// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.net.MalformedURLException;
import java.net.URL;
import android.content.Context;
import org.json.JSONTokener;
import com.facebook.internal.Logger;
import java.io.InputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONArray;
import com.facebook.internal.Utility;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphObject;
import java.net.HttpURLConnection;
import com.facebook.internal.FileLruCache;

public class Response
{
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_CACHE_TAG = "ResponseCache";
    private static final String RESPONSE_LOG_TAG = "Response";
    private static FileLruCache responseCache;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final GraphObject graphObject;
    private final GraphObjectList<GraphObject> graphObjectList;
    private final boolean isFromCache;
    private final Request request;
    
    Response(final Request request, final HttpURLConnection connection, final FacebookRequestError error) {
        this.request = request;
        this.connection = connection;
        this.graphObject = null;
        this.graphObjectList = null;
        this.isFromCache = false;
        this.error = error;
    }
    
    Response(final Request request, final HttpURLConnection connection, final GraphObject graphObject, final boolean isFromCache) {
        this.request = request;
        this.connection = connection;
        this.graphObject = graphObject;
        this.graphObjectList = null;
        this.isFromCache = isFromCache;
        this.error = null;
    }
    
    Response(final Request request, final HttpURLConnection connection, final GraphObjectList<GraphObject> graphObjectList, final boolean isFromCache) {
        this.request = request;
        this.connection = connection;
        this.graphObject = null;
        this.graphObjectList = graphObjectList;
        this.isFromCache = isFromCache;
        this.error = null;
    }
    
    static List<Response> constructErrorResponses(final List<Request> list, final HttpURLConnection httpURLConnection, final FacebookException ex) {
        final int size = list.size();
        final ArrayList list2 = new ArrayList<Response>(size);
        for (int i = 0; i < size; ++i) {
            list2.add(new Response(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, ex)));
        }
        return (List<Response>)list2;
    }
    
    private static Response createResponseFromObject(final Request request, final HttpURLConnection httpURLConnection, Object stringPropertyAsJSON, final boolean b, final Object o) throws JSONException {
        Object null = stringPropertyAsJSON;
        if (stringPropertyAsJSON instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)stringPropertyAsJSON;
            final FacebookRequestError checkResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jsonObject, o, httpURLConnection);
            if (checkResponseAndCreateError != null) {
                if (checkResponseAndCreateError.getErrorCode() == 190) {
                    final Session session = request.getSession();
                    if (session != null) {
                        session.closeAndClearTokenInformation();
                    }
                }
                return new Response(request, httpURLConnection, checkResponseAndCreateError);
            }
            stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new Response(request, httpURLConnection, GraphObject.Factory.create((JSONObject)stringPropertyAsJSON), b);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new Response(request, httpURLConnection, GraphObject.Factory.createList((JSONArray)stringPropertyAsJSON, GraphObject.class), b);
            }
            null = JSONObject.NULL;
        }
        if (null == JSONObject.NULL) {
            return new Response(request, httpURLConnection, (GraphObject)null, b);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + null.getClass().getSimpleName());
    }
    
    private static List<Response> createResponsesFromObject(final HttpURLConnection httpURLConnection, final List<Request> list, final Object o, final boolean b) throws FacebookException, JSONException {
        assert !(!b);
        final int size = list.size();
        final ArrayList list2 = new ArrayList<Response>(size);
        Object o2 = o;
        while (true) {
            if (size == 1) {
                final Request request = list.get(0);
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("body", o);
                    int responseCode;
                    if (httpURLConnection != null) {
                        responseCode = httpURLConnection.getResponseCode();
                    }
                    else {
                        responseCode = 200;
                    }
                    jsonObject.put("code", responseCode);
                    o2 = new JSONArray();
                    ((JSONArray)o2).put((Object)jsonObject);
                    if (!(o2 instanceof JSONArray) || ((JSONArray)o2).length() != size) {
                        throw new FacebookException("Unexpected number of results");
                    }
                }
                catch (JSONException ex) {
                    list2.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex)));
                    o2 = o;
                    continue;
                }
                catch (IOException ex2) {
                    list2.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, ex2)));
                    o2 = o;
                    continue;
                }
                final JSONArray jsonArray = (JSONArray)o2;
                int i = 0;
            Label_0284_Outer:
                while (i < jsonArray.length()) {
                    final Request request2 = list.get(i);
                    while (true) {
                        try {
                            list2.add(createResponseFromObject(request2, httpURLConnection, jsonArray.get(i), b, o));
                            ++i;
                            continue Label_0284_Outer;
                        }
                        catch (JSONException ex3) {
                            list2.add(new Response(request2, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex3)));
                            continue;
                        }
                        catch (FacebookException ex4) {
                            list2.add(new Response(request2, httpURLConnection, new FacebookRequestError(httpURLConnection, ex4)));
                            continue;
                        }
                        break;
                    }
                    break;
                }
                return (List<Response>)list2;
            }
            continue;
        }
    }
    
    static List<Response> createResponsesFromStream(final InputStream inputStream, final HttpURLConnection httpURLConnection, final RequestBatch requestBatch, final boolean b) throws FacebookException, JSONException, IOException {
        final String streamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", streamToString.length(), streamToString);
        return createResponsesFromString(streamToString, httpURLConnection, requestBatch, b);
    }
    
    static List<Response> createResponsesFromString(final String s, final HttpURLConnection httpURLConnection, final RequestBatch requestBatch, final boolean b) throws FacebookException, JSONException, IOException {
        final List<Response> responsesFromObject = createResponsesFromObject(httpURLConnection, requestBatch, new JSONTokener(s).nextValue(), b);
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", requestBatch.getId(), s.length(), responsesFromObject);
        return responsesFromObject;
    }
    
    static List<Response> fromHttpConnection(final HttpURLConnection p0, final RequestBatch p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          11
        //     3: aconst_null    
        //     4: astore          5
        //     6: aconst_null    
        //     7: astore          6
        //     9: aconst_null    
        //    10: astore          7
        //    12: aconst_null    
        //    13: astore          4
        //    15: aconst_null    
        //    16: astore          8
        //    18: aconst_null    
        //    19: astore          9
        //    21: aload           4
        //    23: astore_2       
        //    24: aload_1        
        //    25: instanceof      Lcom/facebook/internal/CacheableRequestBatch;
        //    28: ifeq            194
        //    31: aload_1        
        //    32: checkcast       Lcom/facebook/internal/CacheableRequestBatch;
        //    35: astore          12
        //    37: invokestatic    com/facebook/Response.getResponseCache:()Lcom/facebook/internal/FileLruCache;
        //    40: astore          10
        //    42: aload           12
        //    44: invokevirtual   com/facebook/internal/CacheableRequestBatch.getCacheKeyOverride:()Ljava/lang/String;
        //    47: astore_2       
        //    48: aload_2        
        //    49: astore_3       
        //    50: aload_2        
        //    51: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    54: ifeq            74
        //    57: aload_1        
        //    58: invokevirtual   com/facebook/RequestBatch.size:()I
        //    61: iconst_1       
        //    62: if_icmpne       167
        //    65: aload_1        
        //    66: iconst_0       
        //    67: invokevirtual   com/facebook/RequestBatch.get:(I)Lcom/facebook/Request;
        //    70: invokevirtual   com/facebook/Request.getUrlForSingleRequest:()Ljava/lang/String;
        //    73: astore_3       
        //    74: aload           10
        //    76: astore          8
        //    78: aload_3        
        //    79: astore          9
        //    81: aload           4
        //    83: astore_2       
        //    84: aload           12
        //    86: invokevirtual   com/facebook/internal/CacheableRequestBatch.getForceRoundTrip:()Z
        //    89: ifne            194
        //    92: aload           10
        //    94: astore          8
        //    96: aload_3        
        //    97: astore          9
        //    99: aload           4
        //   101: astore_2       
        //   102: aload           10
        //   104: ifnull          194
        //   107: aload           10
        //   109: astore          8
        //   111: aload_3        
        //   112: astore          9
        //   114: aload           4
        //   116: astore_2       
        //   117: aload_3        
        //   118: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   121: ifne            194
        //   124: aload           11
        //   126: astore          4
        //   128: aload           10
        //   130: aload_3        
        //   131: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;)Ljava/io/InputStream;
        //   134: astore_2       
        //   135: aload_2        
        //   136: ifnull          183
        //   139: aload_2        
        //   140: astore          4
        //   142: aload_2        
        //   143: astore          5
        //   145: aload_2        
        //   146: astore          6
        //   148: aload_2        
        //   149: astore          7
        //   151: aload_2        
        //   152: aconst_null    
        //   153: aload_1        
        //   154: iconst_1       
        //   155: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   158: astore          8
        //   160: aload_2        
        //   161: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   164: aload           8
        //   166: areturn        
        //   167: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   170: ldc             "ResponseCache"
        //   172: ldc_w           "Not using cache for cacheable request because no key was specified"
        //   175: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   178: aload_2        
        //   179: astore_3       
        //   180: goto            74
        //   183: aload_2        
        //   184: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   187: aload_3        
        //   188: astore          9
        //   190: aload           10
        //   192: astore          8
        //   194: aload_2        
        //   195: astore          5
        //   197: aload_2        
        //   198: astore          6
        //   200: aload_2        
        //   201: astore          7
        //   203: aload_2        
        //   204: astore_3       
        //   205: aload_0        
        //   206: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   209: sipush          400
        //   212: if_icmplt       323
        //   215: aload_2        
        //   216: astore          5
        //   218: aload_2        
        //   219: astore          6
        //   221: aload_2        
        //   222: astore          7
        //   224: aload_2        
        //   225: astore_3       
        //   226: aload_0        
        //   227: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   230: astore_2       
        //   231: aload_2        
        //   232: astore          5
        //   234: aload_2        
        //   235: astore          6
        //   237: aload_2        
        //   238: astore          7
        //   240: aload_2        
        //   241: astore_3       
        //   242: aload_2        
        //   243: aload_0        
        //   244: aload_1        
        //   245: iconst_0       
        //   246: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   249: astore          4
        //   251: aload_2        
        //   252: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   255: aload           4
        //   257: areturn        
        //   258: astore_2       
        //   259: aload           4
        //   261: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   264: aload           10
        //   266: astore          8
        //   268: aload_3        
        //   269: astore          9
        //   271: aload           4
        //   273: astore_2       
        //   274: goto            194
        //   277: astore_2       
        //   278: aload           5
        //   280: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   283: aload           10
        //   285: astore          8
        //   287: aload_3        
        //   288: astore          9
        //   290: aload           5
        //   292: astore_2       
        //   293: goto            194
        //   296: astore_2       
        //   297: aload           6
        //   299: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   302: aload           10
        //   304: astore          8
        //   306: aload_3        
        //   307: astore          9
        //   309: aload           6
        //   311: astore_2       
        //   312: goto            194
        //   315: astore_0       
        //   316: aload           7
        //   318: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   321: aload_0        
        //   322: athrow         
        //   323: aload_2        
        //   324: astore          5
        //   326: aload_2        
        //   327: astore          6
        //   329: aload_2        
        //   330: astore          7
        //   332: aload_2        
        //   333: astore_3       
        //   334: aload_0        
        //   335: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   338: astore          4
        //   340: aload           4
        //   342: astore_2       
        //   343: aload           8
        //   345: ifnull          231
        //   348: aload           4
        //   350: astore_2       
        //   351: aload           9
        //   353: ifnull          231
        //   356: aload           4
        //   358: astore_2       
        //   359: aload           4
        //   361: ifnull          231
        //   364: aload           4
        //   366: astore          5
        //   368: aload           4
        //   370: astore          6
        //   372: aload           4
        //   374: astore          7
        //   376: aload           4
        //   378: astore_3       
        //   379: aload           8
        //   381: aload           9
        //   383: aload           4
        //   385: invokevirtual   com/facebook/internal/FileLruCache.interceptAndPut:(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;
        //   388: astore          8
        //   390: aload           4
        //   392: astore_2       
        //   393: aload           8
        //   395: ifnull          231
        //   398: aload           8
        //   400: astore_2       
        //   401: goto            231
        //   404: astore_2       
        //   405: aload           5
        //   407: astore_3       
        //   408: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   411: ldc             "Response"
        //   413: ldc_w           "Response <Error>: %s"
        //   416: iconst_1       
        //   417: anewarray       Ljava/lang/Object;
        //   420: dup            
        //   421: iconst_0       
        //   422: aload_2        
        //   423: aastore        
        //   424: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   427: aload           5
        //   429: astore_3       
        //   430: aload_1        
        //   431: aload_0        
        //   432: aload_2        
        //   433: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   436: astore_0       
        //   437: aload           5
        //   439: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   442: aload_0        
        //   443: areturn        
        //   444: astore_2       
        //   445: aload           6
        //   447: astore_3       
        //   448: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   451: ldc             "Response"
        //   453: ldc_w           "Response <Error>: %s"
        //   456: iconst_1       
        //   457: anewarray       Ljava/lang/Object;
        //   460: dup            
        //   461: iconst_0       
        //   462: aload_2        
        //   463: aastore        
        //   464: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   467: aload           6
        //   469: astore_3       
        //   470: aload_1        
        //   471: aload_0        
        //   472: new             Lcom/facebook/FacebookException;
        //   475: dup            
        //   476: aload_2        
        //   477: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   480: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   483: astore_0       
        //   484: aload           6
        //   486: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   489: aload_0        
        //   490: areturn        
        //   491: astore_2       
        //   492: aload           7
        //   494: astore_3       
        //   495: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   498: ldc             "Response"
        //   500: ldc_w           "Response <Error>: %s"
        //   503: iconst_1       
        //   504: anewarray       Ljava/lang/Object;
        //   507: dup            
        //   508: iconst_0       
        //   509: aload_2        
        //   510: aastore        
        //   511: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   514: aload           7
        //   516: astore_3       
        //   517: aload_1        
        //   518: aload_0        
        //   519: new             Lcom/facebook/FacebookException;
        //   522: dup            
        //   523: aload_2        
        //   524: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   527: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   530: astore_0       
        //   531: aload           7
        //   533: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   536: aload_0        
        //   537: areturn        
        //   538: astore_0       
        //   539: aload_3        
        //   540: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   543: aload_0        
        //   544: athrow         
        //    Signature:
        //  (Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;)Ljava/util/List<Lcom/facebook/Response;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  128    135    258    277    Lcom/facebook/FacebookException;
        //  128    135    277    296    Lorg/json/JSONException;
        //  128    135    296    315    Ljava/io/IOException;
        //  128    135    315    323    Any
        //  151    160    258    277    Lcom/facebook/FacebookException;
        //  151    160    277    296    Lorg/json/JSONException;
        //  151    160    296    315    Ljava/io/IOException;
        //  151    160    315    323    Any
        //  205    215    404    444    Lcom/facebook/FacebookException;
        //  205    215    444    491    Lorg/json/JSONException;
        //  205    215    491    538    Ljava/io/IOException;
        //  205    215    538    545    Any
        //  226    231    404    444    Lcom/facebook/FacebookException;
        //  226    231    444    491    Lorg/json/JSONException;
        //  226    231    491    538    Ljava/io/IOException;
        //  226    231    538    545    Any
        //  242    251    404    444    Lcom/facebook/FacebookException;
        //  242    251    444    491    Lorg/json/JSONException;
        //  242    251    491    538    Ljava/io/IOException;
        //  242    251    538    545    Any
        //  334    340    404    444    Lcom/facebook/FacebookException;
        //  334    340    444    491    Lorg/json/JSONException;
        //  334    340    491    538    Ljava/io/IOException;
        //  334    340    538    545    Any
        //  379    390    404    444    Lcom/facebook/FacebookException;
        //  379    390    444    491    Lorg/json/JSONException;
        //  379    390    491    538    Ljava/io/IOException;
        //  379    390    538    545    Any
        //  408    427    538    545    Any
        //  430    437    538    545    Any
        //  448    467    538    545    Any
        //  470    484    538    545    Any
        //  495    514    538    545    Any
        //  517    531    538    545    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0231:
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
    
    static FileLruCache getResponseCache() {
        if (Response.responseCache == null) {
            final Context staticContext = Session.getStaticContext();
            if (staticContext != null) {
                Response.responseCache = new FileLruCache(staticContext, "ResponseCache", new FileLruCache.Limits());
            }
        }
        return Response.responseCache;
    }
    
    public final HttpURLConnection getConnection() {
        return this.connection;
    }
    
    public final FacebookRequestError getError() {
        return this.error;
    }
    
    public final GraphObject getGraphObject() {
        return this.graphObject;
    }
    
    public final <T extends GraphObject> T getGraphObjectAs(final Class<T> clazz) {
        if (this.graphObject == null) {
            return null;
        }
        if (clazz == null) {
            throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
        }
        return this.graphObject.cast(clazz);
    }
    
    public final GraphObjectList<GraphObject> getGraphObjectList() {
        return this.graphObjectList;
    }
    
    public final <T extends GraphObject> GraphObjectList<T> getGraphObjectListAs(final Class<T> clazz) {
        if (this.graphObjectList == null) {
            return null;
        }
        return this.graphObjectList.castToListOf(clazz);
    }
    
    public final boolean getIsFromCache() {
        return this.isFromCache;
    }
    
    public Request getRequest() {
        return this.request;
    }
    
    public Request getRequestForPagedResults(final PagingDirection pagingDirection) {
        String s2;
        final String s = s2 = null;
        if (this.graphObject != null) {
            final PagingInfo paging = this.graphObject.cast(PagedResults.class).getPaging();
            s2 = s;
            if (paging != null) {
                if (pagingDirection == PagingDirection.NEXT) {
                    s2 = paging.getNext();
                }
                else {
                    s2 = paging.getPrevious();
                }
            }
        }
        if (Utility.isNullOrEmpty(s2)) {
            return null;
        }
        if (s2 != null && s2.equals(this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new Request(this.request.getSession(), new URL(s2));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        try {
            int responseCode;
            if (this.connection != null) {
                responseCode = this.connection.getResponseCode();
            }
            else {
                responseCode = 200;
            }
            final String format = String.format("%d", responseCode);
            return "{Response: " + " responseCode: " + format + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
        }
        catch (IOException ex) {
            final String format = "unknown";
            return "{Response: " + " responseCode: " + format + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
        }
    }
    
    interface PagedResults extends GraphObject
    {
        GraphObjectList<GraphObject> getData();
        
        PagingInfo getPaging();
    }
    
    public enum PagingDirection
    {
        NEXT, 
        PREVIOUS;
    }
    
    interface PagingInfo extends GraphObject
    {
        String getNext();
        
        String getPrevious();
    }
}
