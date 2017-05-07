// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

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
    private static FileLruCache responseCache;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final GraphObject graphObject;
    private final GraphObjectList<GraphObject> graphObjectList;
    private final boolean isFromCache;
    private final String rawResponse;
    private final Request request;
    
    Response(final Request request, final HttpURLConnection httpURLConnection, final FacebookRequestError facebookRequestError) {
        this(request, httpURLConnection, null, null, null, false, facebookRequestError);
    }
    
    Response(final Request request, final HttpURLConnection connection, final String rawResponse, final GraphObject graphObject, final GraphObjectList<GraphObject> graphObjectList, final boolean isFromCache, final FacebookRequestError error) {
        this.request = request;
        this.connection = connection;
        this.rawResponse = rawResponse;
        this.graphObject = graphObject;
        this.graphObjectList = graphObjectList;
        this.isFromCache = isFromCache;
        this.error = error;
    }
    
    Response(final Request request, final HttpURLConnection httpURLConnection, final String s, final GraphObject graphObject, final boolean b) {
        this(request, httpURLConnection, s, graphObject, null, b, null);
    }
    
    Response(final Request request, final HttpURLConnection httpURLConnection, final String s, final GraphObjectList<GraphObject> list, final boolean b) {
        this(request, httpURLConnection, s, null, list, b, null);
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
                return new Response(request, httpURLConnection, stringPropertyAsJSON.toString(), GraphObject$Factory.create((JSONObject)stringPropertyAsJSON), b);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new Response(request, httpURLConnection, stringPropertyAsJSON.toString(), GraphObject$Factory.createList((JSONArray)stringPropertyAsJSON, GraphObject.class), b);
            }
            null = JSONObject.NULL;
        }
        if (null == JSONObject.NULL) {
            return new Response(request, httpURLConnection, null.toString(), (GraphObject)null, b);
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
        //    10: astore_3       
        //    11: aload_1        
        //    12: instanceof      Lcom/facebook/internal/CacheableRequestBatch;
        //    15: ifeq            562
        //    18: aload_1        
        //    19: checkcast       Lcom/facebook/internal/CacheableRequestBatch;
        //    22: astore          8
        //    24: invokestatic    com/facebook/Response.getResponseCache:()Lcom/facebook/internal/FileLruCache;
        //    27: astore          4
        //    29: aload           8
        //    31: invokevirtual   com/facebook/internal/CacheableRequestBatch.getCacheKeyOverride:()Ljava/lang/String;
        //    34: astore_3       
        //    35: aload_3        
        //    36: astore_2       
        //    37: aload_3        
        //    38: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    41: ifeq            61
        //    44: aload_1        
        //    45: invokevirtual   com/facebook/RequestBatch.size:()I
        //    48: iconst_1       
        //    49: if_icmpne       117
        //    52: aload_1        
        //    53: iconst_0       
        //    54: invokevirtual   com/facebook/RequestBatch.get:(I)Lcom/facebook/Request;
        //    57: invokevirtual   com/facebook/Request.getUrlForSingleRequest:()Ljava/lang/String;
        //    60: astore_2       
        //    61: aload           8
        //    63: invokevirtual   com/facebook/internal/CacheableRequestBatch.getForceRoundTrip:()Z
        //    66: ifne            552
        //    69: aload           4
        //    71: ifnull          552
        //    74: aload_2        
        //    75: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    78: ifne            552
        //    81: aload           4
        //    83: aload_2        
        //    84: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;)Ljava/io/InputStream;
        //    87: astore_3       
        //    88: aload_3        
        //    89: ifnull          134
        //    92: aload_3        
        //    93: astore          5
        //    95: aload_3        
        //    96: astore          6
        //    98: aload_3        
        //    99: astore          7
        //   101: aload_3        
        //   102: aconst_null    
        //   103: aload_1        
        //   104: iconst_1       
        //   105: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   108: astore          8
        //   110: aload_3        
        //   111: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   114: aload           8
        //   116: areturn        
        //   117: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   120: ldc_w           "ResponseCache"
        //   123: ldc_w           "Not using cache for cacheable request because no key was specified"
        //   126: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   129: aload_3        
        //   130: astore_2       
        //   131: goto            61
        //   134: aload_3        
        //   135: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   138: aload_2        
        //   139: astore          5
        //   141: aload_3        
        //   142: astore_2       
        //   143: aload_2        
        //   144: astore          7
        //   146: aload_2        
        //   147: astore          8
        //   149: aload_2        
        //   150: astore          9
        //   152: aload_2        
        //   153: astore          10
        //   155: aload_2        
        //   156: astore_3       
        //   157: aload_0        
        //   158: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   161: sipush          400
        //   164: if_icmplt       271
        //   167: aload_2        
        //   168: astore          7
        //   170: aload_2        
        //   171: astore          8
        //   173: aload_2        
        //   174: astore          9
        //   176: aload_2        
        //   177: astore          10
        //   179: aload_2        
        //   180: astore_3       
        //   181: aload_0        
        //   182: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   185: astore_2       
        //   186: aload_2        
        //   187: astore          7
        //   189: aload_2        
        //   190: astore          8
        //   192: aload_2        
        //   193: astore          9
        //   195: aload_2        
        //   196: astore          10
        //   198: aload_2        
        //   199: astore_3       
        //   200: aload_2        
        //   201: aload_0        
        //   202: aload_1        
        //   203: iconst_0       
        //   204: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   207: astore          4
        //   209: aload_2        
        //   210: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   213: aload           4
        //   215: areturn        
        //   216: astore_3       
        //   217: aconst_null    
        //   218: astore_3       
        //   219: aload_3        
        //   220: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   223: aload_2        
        //   224: astore          5
        //   226: aload_3        
        //   227: astore_2       
        //   228: goto            143
        //   231: astore_3       
        //   232: aload           5
        //   234: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   237: aload_2        
        //   238: astore_3       
        //   239: aload           5
        //   241: astore_2       
        //   242: aload_3        
        //   243: astore          5
        //   245: goto            143
        //   248: astore_3       
        //   249: aload           6
        //   251: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   254: aload_2        
        //   255: astore          5
        //   257: aload           6
        //   259: astore_2       
        //   260: goto            143
        //   263: astore_0       
        //   264: aload           7
        //   266: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   269: aload_0        
        //   270: athrow         
        //   271: aload_2        
        //   272: astore          7
        //   274: aload_2        
        //   275: astore          8
        //   277: aload_2        
        //   278: astore          9
        //   280: aload_2        
        //   281: astore          10
        //   283: aload_2        
        //   284: astore_3       
        //   285: aload_0        
        //   286: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   289: astore          6
        //   291: aload           6
        //   293: astore_2       
        //   294: aload           4
        //   296: ifnull          186
        //   299: aload           6
        //   301: astore_2       
        //   302: aload           5
        //   304: ifnull          186
        //   307: aload           6
        //   309: astore_2       
        //   310: aload           6
        //   312: ifnull          186
        //   315: aload           6
        //   317: astore          7
        //   319: aload           6
        //   321: astore          8
        //   323: aload           6
        //   325: astore          9
        //   327: aload           6
        //   329: astore          10
        //   331: aload           6
        //   333: astore_3       
        //   334: aload           4
        //   336: aload           5
        //   338: aload           6
        //   340: invokevirtual   com/facebook/internal/FileLruCache.interceptAndPut:(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;
        //   343: astore          4
        //   345: aload           6
        //   347: astore_2       
        //   348: aload           4
        //   350: ifnull          186
        //   353: aload           4
        //   355: astore_2       
        //   356: goto            186
        //   359: astore_2       
        //   360: aload           7
        //   362: astore_3       
        //   363: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   366: ldc             "Response"
        //   368: ldc_w           "Response <Error>: %s"
        //   371: iconst_1       
        //   372: anewarray       Ljava/lang/Object;
        //   375: dup            
        //   376: iconst_0       
        //   377: aload_2        
        //   378: aastore        
        //   379: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   382: aload           7
        //   384: astore_3       
        //   385: aload_1        
        //   386: aload_0        
        //   387: aload_2        
        //   388: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   391: astore_0       
        //   392: aload           7
        //   394: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   397: aload_0        
        //   398: areturn        
        //   399: astore_2       
        //   400: aload           8
        //   402: astore_3       
        //   403: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   406: ldc             "Response"
        //   408: ldc_w           "Response <Error>: %s"
        //   411: iconst_1       
        //   412: anewarray       Ljava/lang/Object;
        //   415: dup            
        //   416: iconst_0       
        //   417: aload_2        
        //   418: aastore        
        //   419: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   422: aload           8
        //   424: astore_3       
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
        //   449: astore_3       
        //   450: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   453: ldc             "Response"
        //   455: ldc_w           "Response <Error>: %s"
        //   458: iconst_1       
        //   459: anewarray       Ljava/lang/Object;
        //   462: dup            
        //   463: iconst_0       
        //   464: aload_2        
        //   465: aastore        
        //   466: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   469: aload           9
        //   471: astore_3       
        //   472: aload_1        
        //   473: aload_0        
        //   474: new             Lcom/facebook/FacebookException;
        //   477: dup            
        //   478: aload_2        
        //   479: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   482: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   485: astore_0       
        //   486: aload           9
        //   488: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   491: aload_0        
        //   492: areturn        
        //   493: astore_2       
        //   494: aload           10
        //   496: astore_3       
        //   497: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   500: ldc             "Response"
        //   502: ldc_w           "Response <Error>: %s"
        //   505: iconst_1       
        //   506: anewarray       Ljava/lang/Object;
        //   509: dup            
        //   510: iconst_0       
        //   511: aload_2        
        //   512: aastore        
        //   513: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   516: aload           10
        //   518: astore_3       
        //   519: aload_1        
        //   520: aload_0        
        //   521: new             Lcom/facebook/FacebookException;
        //   524: dup            
        //   525: aload_2        
        //   526: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   529: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   532: astore_0       
        //   533: aload           10
        //   535: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   538: aload_0        
        //   539: areturn        
        //   540: astore_0       
        //   541: aload_3        
        //   542: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   545: aload_0        
        //   546: athrow         
        //   547: astore          5
        //   549: goto            219
        //   552: aconst_null    
        //   553: astore_3       
        //   554: aload_2        
        //   555: astore          5
        //   557: aload_3        
        //   558: astore_2       
        //   559: goto            143
        //   562: aconst_null    
        //   563: astore          4
        //   565: aconst_null    
        //   566: astore_2       
        //   567: aload_3        
        //   568: astore          5
        //   570: goto            143
        //    Signature:
        //  (Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;)Ljava/util/List<Lcom/facebook/Response;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  81     88     216    219    Lcom/facebook/FacebookException;
        //  81     88     231    248    Lorg/json/JSONException;
        //  81     88     248    263    Ljava/io/IOException;
        //  81     88     263    271    Any
        //  101    110    547    552    Lcom/facebook/FacebookException;
        //  101    110    231    248    Lorg/json/JSONException;
        //  101    110    248    263    Ljava/io/IOException;
        //  101    110    263    271    Any
        //  157    167    359    399    Lcom/facebook/FacebookException;
        //  157    167    399    446    Lorg/json/JSONException;
        //  157    167    446    493    Ljava/io/IOException;
        //  157    167    493    540    Ljava/lang/SecurityException;
        //  157    167    540    547    Any
        //  181    186    359    399    Lcom/facebook/FacebookException;
        //  181    186    399    446    Lorg/json/JSONException;
        //  181    186    446    493    Ljava/io/IOException;
        //  181    186    493    540    Ljava/lang/SecurityException;
        //  181    186    540    547    Any
        //  200    209    359    399    Lcom/facebook/FacebookException;
        //  200    209    399    446    Lorg/json/JSONException;
        //  200    209    446    493    Ljava/io/IOException;
        //  200    209    493    540    Ljava/lang/SecurityException;
        //  200    209    540    547    Any
        //  285    291    359    399    Lcom/facebook/FacebookException;
        //  285    291    399    446    Lorg/json/JSONException;
        //  285    291    446    493    Ljava/io/IOException;
        //  285    291    493    540    Ljava/lang/SecurityException;
        //  285    291    540    547    Any
        //  334    345    359    399    Lcom/facebook/FacebookException;
        //  334    345    399    446    Lorg/json/JSONException;
        //  334    345    446    493    Ljava/io/IOException;
        //  334    345    493    540    Ljava/lang/SecurityException;
        //  334    345    540    547    Any
        //  363    382    540    547    Any
        //  385    392    540    547    Any
        //  403    422    540    547    Any
        //  425    439    540    547    Any
        //  450    469    540    547    Any
        //  472    486    540    547    Any
        //  497    516    540    547    Any
        //  519    533    540    547    Any
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
