// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.net.MalformedURLException;
import java.net.URL;
import android.content.Context;
import com.facebook.internal.FileLruCache$Limits;
import org.json.JSONTokener;
import com.facebook.internal.Logger;
import java.io.InputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONArray;
import com.facebook.model.GraphObject$Factory;
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
    
    private static Response createResponseFromObject(final Request request, final HttpURLConnection httpURLConnection, Object stringPropertyAsJSON, final boolean b, final Object o) {
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
                return new Response(request, httpURLConnection, GraphObject$Factory.create((JSONObject)stringPropertyAsJSON), b);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new Response(request, httpURLConnection, GraphObject$Factory.createList((JSONArray)stringPropertyAsJSON, GraphObject.class), b);
            }
            null = JSONObject.NULL;
        }
        if (null == JSONObject.NULL) {
            return new Response(request, httpURLConnection, (GraphObject)null, b);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + null.getClass().getSimpleName());
    }
    
    private static List<Response> createResponsesFromObject(final HttpURLConnection httpURLConnection, final List<Request> list, final Object o, final boolean b) {
        final int n = 0;
        assert !(!b);
        final int size = list.size();
        final ArrayList list2 = new ArrayList<Response>(size);
        while (true) {
            Label_0222: {
                if (size != 1) {
                    break Label_0222;
                }
                final Request request = list.get(0);
                Object o2 = null;
                Label_0228: {
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
                        break Label_0228;
                    }
                    catch (JSONException ex) {
                        list2.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex)));
                        o2 = o;
                        continue;
                    }
                    catch (IOException ex2) {
                        list2.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, ex2)));
                    }
                    break Label_0222;
                }
                final JSONArray jsonArray = (JSONArray)o2;
                int i = n;
            Label_0285_Outer:
                while (i < jsonArray.length()) {
                    final Request request2 = list.get(i);
                    while (true) {
                        try {
                            list2.add(createResponseFromObject(request2, httpURLConnection, jsonArray.get(i), b, o));
                            ++i;
                            continue Label_0285_Outer;
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
            Object o2 = o;
            continue;
        }
    }
    
    static List<Response> createResponsesFromStream(final InputStream inputStream, final HttpURLConnection httpURLConnection, final RequestBatch requestBatch, final boolean b) {
        final String streamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", streamToString.length(), streamToString);
        return createResponsesFromString(streamToString, httpURLConnection, requestBatch, b);
    }
    
    static List<Response> createResponsesFromString(final String s, final HttpURLConnection httpURLConnection, final RequestBatch requestBatch, final boolean b) {
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
        //     1: astore          5
        //     3: aconst_null    
        //     4: astore          6
        //     6: aconst_null    
        //     7: astore          7
        //     9: aconst_null    
        //    10: astore          4
        //    12: aload_1        
        //    13: instanceof      Lcom/facebook/internal/CacheableRequestBatch;
        //    16: ifeq            523
        //    19: aload_1        
        //    20: checkcast       Lcom/facebook/internal/CacheableRequestBatch;
        //    23: astore          8
        //    25: invokestatic    com/facebook/Response.getResponseCache:()Lcom/facebook/internal/FileLruCache;
        //    28: astore          4
        //    30: aload           8
        //    32: invokevirtual   com/facebook/internal/CacheableRequestBatch.getCacheKeyOverride:()Ljava/lang/String;
        //    35: astore_3       
        //    36: aload_3        
        //    37: astore_2       
        //    38: aload_3        
        //    39: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    42: ifeq            62
        //    45: aload_1        
        //    46: invokevirtual   com/facebook/RequestBatch.size:()I
        //    49: iconst_1       
        //    50: if_icmpne       118
        //    53: aload_1        
        //    54: iconst_0       
        //    55: invokevirtual   com/facebook/RequestBatch.get:(I)Lcom/facebook/Request;
        //    58: invokevirtual   com/facebook/Request.getUrlForSingleRequest:()Ljava/lang/String;
        //    61: astore_2       
        //    62: aload           8
        //    64: invokevirtual   com/facebook/internal/CacheableRequestBatch.getForceRoundTrip:()Z
        //    67: ifne            508
        //    70: aload           4
        //    72: ifnull          508
        //    75: aload_2        
        //    76: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    79: ifne            508
        //    82: aload           4
        //    84: aload_2        
        //    85: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;)Ljava/io/InputStream;
        //    88: astore_3       
        //    89: aload_3        
        //    90: ifnull          134
        //    93: aload_3        
        //    94: astore          5
        //    96: aload_3        
        //    97: astore          6
        //    99: aload_3        
        //   100: astore          7
        //   102: aload_3        
        //   103: aconst_null    
        //   104: aload_1        
        //   105: iconst_1       
        //   106: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   109: astore          8
        //   111: aload_3        
        //   112: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   115: aload           8
        //   117: areturn        
        //   118: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   121: ldc             "ResponseCache"
        //   123: ldc_w           "Not using cache for cacheable request because no key was specified"
        //   126: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   129: aload_3        
        //   130: astore_2       
        //   131: goto            62
        //   134: aload_3        
        //   135: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   138: aload_2        
        //   139: astore          5
        //   141: aload_3        
        //   142: astore_2       
        //   143: aload           4
        //   145: astore_3       
        //   146: aload_2        
        //   147: astore          7
        //   149: aload_2        
        //   150: astore          8
        //   152: aload_2        
        //   153: astore          9
        //   155: aload_2        
        //   156: astore          4
        //   158: aload_0        
        //   159: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   162: sipush          400
        //   165: if_icmplt       277
        //   168: aload_2        
        //   169: astore          7
        //   171: aload_2        
        //   172: astore          8
        //   174: aload_2        
        //   175: astore          9
        //   177: aload_2        
        //   178: astore          4
        //   180: aload_0        
        //   181: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   184: astore_2       
        //   185: aload_2        
        //   186: astore          7
        //   188: aload_2        
        //   189: astore          8
        //   191: aload_2        
        //   192: astore          9
        //   194: aload_2        
        //   195: astore          4
        //   197: aload_2        
        //   198: aload_0        
        //   199: aload_1        
        //   200: iconst_0       
        //   201: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   204: astore_3       
        //   205: aload_2        
        //   206: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   209: aload_3        
        //   210: areturn        
        //   211: astore_3       
        //   212: aconst_null    
        //   213: astore_3       
        //   214: aload_3        
        //   215: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   218: aload_2        
        //   219: astore          5
        //   221: aload_3        
        //   222: astore_2       
        //   223: aload           4
        //   225: astore_3       
        //   226: goto            146
        //   229: astore_3       
        //   230: aload           5
        //   232: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   235: aload           4
        //   237: astore_3       
        //   238: aload_2        
        //   239: astore          4
        //   241: aload           5
        //   243: astore_2       
        //   244: aload           4
        //   246: astore          5
        //   248: goto            146
        //   251: astore_3       
        //   252: aload           6
        //   254: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   257: aload           4
        //   259: astore_3       
        //   260: aload_2        
        //   261: astore          5
        //   263: aload           6
        //   265: astore_2       
        //   266: goto            146
        //   269: astore_0       
        //   270: aload           7
        //   272: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   275: aload_0        
        //   276: athrow         
        //   277: aload_2        
        //   278: astore          7
        //   280: aload_2        
        //   281: astore          8
        //   283: aload_2        
        //   284: astore          9
        //   286: aload_2        
        //   287: astore          4
        //   289: aload_0        
        //   290: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   293: astore          6
        //   295: aload           6
        //   297: astore_2       
        //   298: aload_3        
        //   299: ifnull          185
        //   302: aload           6
        //   304: astore_2       
        //   305: aload           5
        //   307: ifnull          185
        //   310: aload           6
        //   312: astore_2       
        //   313: aload           6
        //   315: ifnull          185
        //   318: aload           6
        //   320: astore          7
        //   322: aload           6
        //   324: astore          8
        //   326: aload           6
        //   328: astore          9
        //   330: aload           6
        //   332: astore          4
        //   334: aload_3        
        //   335: aload           5
        //   337: aload           6
        //   339: invokevirtual   com/facebook/internal/FileLruCache.interceptAndPut:(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;
        //   342: astore_3       
        //   343: aload           6
        //   345: astore_2       
        //   346: aload_3        
        //   347: ifnull          185
        //   350: aload_3        
        //   351: astore_2       
        //   352: goto            185
        //   355: astore_2       
        //   356: aload           7
        //   358: astore          4
        //   360: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   363: ldc             "Response"
        //   365: ldc_w           "Response <Error>: %s"
        //   368: iconst_1       
        //   369: anewarray       Ljava/lang/Object;
        //   372: dup            
        //   373: iconst_0       
        //   374: aload_2        
        //   375: aastore        
        //   376: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   379: aload           7
        //   381: astore          4
        //   383: aload_1        
        //   384: aload_0        
        //   385: aload_2        
        //   386: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   389: astore_0       
        //   390: aload           7
        //   392: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   395: aload_0        
        //   396: areturn        
        //   397: astore_2       
        //   398: aload           8
        //   400: astore          4
        //   402: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   405: ldc             "Response"
        //   407: ldc_w           "Response <Error>: %s"
        //   410: iconst_1       
        //   411: anewarray       Ljava/lang/Object;
        //   414: dup            
        //   415: iconst_0       
        //   416: aload_2        
        //   417: aastore        
        //   418: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   421: aload           8
        //   423: astore          4
        //   425: aload_1        
        //   426: aload_0        
        //   427: new             Lcom/facebook/FacebookException;
        //   430: dup            
        //   431: aload_2        
        //   432: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   435: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   438: astore_0       
        //   439: aload           8
        //   441: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   444: aload_0        
        //   445: areturn        
        //   446: astore_2       
        //   447: aload           9
        //   449: astore          4
        //   451: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   454: ldc             "Response"
        //   456: ldc_w           "Response <Error>: %s"
        //   459: iconst_1       
        //   460: anewarray       Ljava/lang/Object;
        //   463: dup            
        //   464: iconst_0       
        //   465: aload_2        
        //   466: aastore        
        //   467: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   470: aload           9
        //   472: astore          4
        //   474: aload_1        
        //   475: aload_0        
        //   476: new             Lcom/facebook/FacebookException;
        //   479: dup            
        //   480: aload_2        
        //   481: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   484: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   487: astore_0       
        //   488: aload           9
        //   490: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   493: aload_0        
        //   494: areturn        
        //   495: astore_0       
        //   496: aload           4
        //   498: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   501: aload_0        
        //   502: athrow         
        //   503: astore          5
        //   505: goto            214
        //   508: aload           4
        //   510: astore_3       
        //   511: aconst_null    
        //   512: astore          4
        //   514: aload_2        
        //   515: astore          5
        //   517: aload           4
        //   519: astore_2       
        //   520: goto            146
        //   523: aconst_null    
        //   524: astore_3       
        //   525: aconst_null    
        //   526: astore_2       
        //   527: aload           4
        //   529: astore          5
        //   531: goto            146
        //    Signature:
        //  (Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;)Ljava/util/List<Lcom/facebook/Response;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  82     89     211    214    Lcom/facebook/FacebookException;
        //  82     89     229    251    Lorg/json/JSONException;
        //  82     89     251    269    Ljava/io/IOException;
        //  82     89     269    277    Any
        //  102    111    503    508    Lcom/facebook/FacebookException;
        //  102    111    229    251    Lorg/json/JSONException;
        //  102    111    251    269    Ljava/io/IOException;
        //  102    111    269    277    Any
        //  158    168    355    397    Lcom/facebook/FacebookException;
        //  158    168    397    446    Lorg/json/JSONException;
        //  158    168    446    495    Ljava/io/IOException;
        //  158    168    495    503    Any
        //  180    185    355    397    Lcom/facebook/FacebookException;
        //  180    185    397    446    Lorg/json/JSONException;
        //  180    185    446    495    Ljava/io/IOException;
        //  180    185    495    503    Any
        //  197    205    355    397    Lcom/facebook/FacebookException;
        //  197    205    397    446    Lorg/json/JSONException;
        //  197    205    446    495    Ljava/io/IOException;
        //  197    205    495    503    Any
        //  289    295    355    397    Lcom/facebook/FacebookException;
        //  289    295    397    446    Lorg/json/JSONException;
        //  289    295    446    495    Ljava/io/IOException;
        //  289    295    495    503    Any
        //  334    343    355    397    Lcom/facebook/FacebookException;
        //  334    343    397    446    Lorg/json/JSONException;
        //  334    343    446    495    Ljava/io/IOException;
        //  334    343    495    503    Any
        //  360    379    495    503    Any
        //  383    390    495    503    Any
        //  402    421    495    503    Any
        //  425    439    495    503    Any
        //  451    470    495    503    Any
        //  474    488    495    503    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
                Response.responseCache = new FileLruCache(staticContext, "ResponseCache", new FileLruCache$Limits());
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
    
    public Request getRequestForPagedResults(final Response$PagingDirection response$PagingDirection) {
        while (true) {
            Label_0111: {
                if (this.graphObject == null) {
                    break Label_0111;
                }
                final Response$PagingInfo paging = this.graphObject.cast(Response$PagedResults.class).getPaging();
                if (paging == null) {
                    break Label_0111;
                }
                String s;
                if (response$PagingDirection == Response$PagingDirection.NEXT) {
                    s = paging.getNext();
                }
                else {
                    s = paging.getPrevious();
                }
                if (!Utility.isNullOrEmpty(s)) {
                    if (s != null) {
                        if (s.equals(this.request.getUrlForSingleRequest())) {
                            return null;
                        }
                    }
                    try {
                        return new Request(this.request.getSession(), new URL(s));
                    }
                    catch (MalformedURLException ex) {
                        return null;
                    }
                    break Label_0111;
                }
                return null;
            }
            String s = null;
            continue;
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
}
