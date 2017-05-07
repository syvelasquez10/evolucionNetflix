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
    public static final String SUCCESS_KEY = "success";
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
        //    15: ifeq            561
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
        //    66: ifne            551
        //    69: aload           4
        //    71: ifnull          551
        //    74: aload_2        
        //    75: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    78: ifne            551
        //    81: aload           4
        //    83: aload_2        
        //    84: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;)Ljava/io/InputStream;
        //    87: astore_3       
        //    88: aload_3        
        //    89: ifnull          133
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
        //   120: ldc             "ResponseCache"
        //   122: ldc_w           "Not using cache for cacheable request because no key was specified"
        //   125: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   128: aload_3        
        //   129: astore_2       
        //   130: goto            61
        //   133: aload_3        
        //   134: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   137: aload_2        
        //   138: astore          5
        //   140: aload_3        
        //   141: astore_2       
        //   142: aload_2        
        //   143: astore          7
        //   145: aload_2        
        //   146: astore          8
        //   148: aload_2        
        //   149: astore          9
        //   151: aload_2        
        //   152: astore          10
        //   154: aload_2        
        //   155: astore_3       
        //   156: aload_0        
        //   157: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   160: sipush          400
        //   163: if_icmplt       270
        //   166: aload_2        
        //   167: astore          7
        //   169: aload_2        
        //   170: astore          8
        //   172: aload_2        
        //   173: astore          9
        //   175: aload_2        
        //   176: astore          10
        //   178: aload_2        
        //   179: astore_3       
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
        //   195: astore          10
        //   197: aload_2        
        //   198: astore_3       
        //   199: aload_2        
        //   200: aload_0        
        //   201: aload_1        
        //   202: iconst_0       
        //   203: invokestatic    com/facebook/Response.createResponsesFromStream:(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
        //   206: astore          4
        //   208: aload_2        
        //   209: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   212: aload           4
        //   214: areturn        
        //   215: astore_3       
        //   216: aconst_null    
        //   217: astore_3       
        //   218: aload_3        
        //   219: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   222: aload_2        
        //   223: astore          5
        //   225: aload_3        
        //   226: astore_2       
        //   227: goto            142
        //   230: astore_3       
        //   231: aload           5
        //   233: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   236: aload_2        
        //   237: astore_3       
        //   238: aload           5
        //   240: astore_2       
        //   241: aload_3        
        //   242: astore          5
        //   244: goto            142
        //   247: astore_3       
        //   248: aload           6
        //   250: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   253: aload_2        
        //   254: astore          5
        //   256: aload           6
        //   258: astore_2       
        //   259: goto            142
        //   262: astore_0       
        //   263: aload           7
        //   265: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   268: aload_0        
        //   269: athrow         
        //   270: aload_2        
        //   271: astore          7
        //   273: aload_2        
        //   274: astore          8
        //   276: aload_2        
        //   277: astore          9
        //   279: aload_2        
        //   280: astore          10
        //   282: aload_2        
        //   283: astore_3       
        //   284: aload_0        
        //   285: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   288: astore          6
        //   290: aload           6
        //   292: astore_2       
        //   293: aload           4
        //   295: ifnull          185
        //   298: aload           6
        //   300: astore_2       
        //   301: aload           5
        //   303: ifnull          185
        //   306: aload           6
        //   308: astore_2       
        //   309: aload           6
        //   311: ifnull          185
        //   314: aload           6
        //   316: astore          7
        //   318: aload           6
        //   320: astore          8
        //   322: aload           6
        //   324: astore          9
        //   326: aload           6
        //   328: astore          10
        //   330: aload           6
        //   332: astore_3       
        //   333: aload           4
        //   335: aload           5
        //   337: aload           6
        //   339: invokevirtual   com/facebook/internal/FileLruCache.interceptAndPut:(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;
        //   342: astore          4
        //   344: aload           6
        //   346: astore_2       
        //   347: aload           4
        //   349: ifnull          185
        //   352: aload           4
        //   354: astore_2       
        //   355: goto            185
        //   358: astore_2       
        //   359: aload           7
        //   361: astore_3       
        //   362: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   365: ldc             "Response"
        //   367: ldc_w           "Response <Error>: %s"
        //   370: iconst_1       
        //   371: anewarray       Ljava/lang/Object;
        //   374: dup            
        //   375: iconst_0       
        //   376: aload_2        
        //   377: aastore        
        //   378: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   381: aload           7
        //   383: astore_3       
        //   384: aload_1        
        //   385: aload_0        
        //   386: aload_2        
        //   387: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   390: astore_0       
        //   391: aload           7
        //   393: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   396: aload_0        
        //   397: areturn        
        //   398: astore_2       
        //   399: aload           8
        //   401: astore_3       
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
        //   423: astore_3       
        //   424: aload_1        
        //   425: aload_0        
        //   426: new             Lcom/facebook/FacebookException;
        //   429: dup            
        //   430: aload_2        
        //   431: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   434: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   437: astore_0       
        //   438: aload           8
        //   440: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   443: aload_0        
        //   444: areturn        
        //   445: astore_2       
        //   446: aload           9
        //   448: astore_3       
        //   449: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   452: ldc             "Response"
        //   454: ldc_w           "Response <Error>: %s"
        //   457: iconst_1       
        //   458: anewarray       Ljava/lang/Object;
        //   461: dup            
        //   462: iconst_0       
        //   463: aload_2        
        //   464: aastore        
        //   465: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   468: aload           9
        //   470: astore_3       
        //   471: aload_1        
        //   472: aload_0        
        //   473: new             Lcom/facebook/FacebookException;
        //   476: dup            
        //   477: aload_2        
        //   478: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   481: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   484: astore_0       
        //   485: aload           9
        //   487: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   490: aload_0        
        //   491: areturn        
        //   492: astore_2       
        //   493: aload           10
        //   495: astore_3       
        //   496: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //   499: ldc             "Response"
        //   501: ldc_w           "Response <Error>: %s"
        //   504: iconst_1       
        //   505: anewarray       Ljava/lang/Object;
        //   508: dup            
        //   509: iconst_0       
        //   510: aload_2        
        //   511: aastore        
        //   512: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   515: aload           10
        //   517: astore_3       
        //   518: aload_1        
        //   519: aload_0        
        //   520: new             Lcom/facebook/FacebookException;
        //   523: dup            
        //   524: aload_2        
        //   525: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/Throwable;)V
        //   528: invokestatic    com/facebook/Response.constructErrorResponses:(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
        //   531: astore_0       
        //   532: aload           10
        //   534: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   537: aload_0        
        //   538: areturn        
        //   539: astore_0       
        //   540: aload_3        
        //   541: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   544: aload_0        
        //   545: athrow         
        //   546: astore          5
        //   548: goto            218
        //   551: aconst_null    
        //   552: astore_3       
        //   553: aload_2        
        //   554: astore          5
        //   556: aload_3        
        //   557: astore_2       
        //   558: goto            142
        //   561: aconst_null    
        //   562: astore          4
        //   564: aconst_null    
        //   565: astore_2       
        //   566: aload_3        
        //   567: astore          5
        //   569: goto            142
        //    Signature:
        //  (Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;)Ljava/util/List<Lcom/facebook/Response;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  81     88     215    218    Lcom/facebook/FacebookException;
        //  81     88     230    247    Lorg/json/JSONException;
        //  81     88     247    262    Ljava/io/IOException;
        //  81     88     262    270    Any
        //  101    110    546    551    Lcom/facebook/FacebookException;
        //  101    110    230    247    Lorg/json/JSONException;
        //  101    110    247    262    Ljava/io/IOException;
        //  101    110    262    270    Any
        //  156    166    358    398    Lcom/facebook/FacebookException;
        //  156    166    398    445    Lorg/json/JSONException;
        //  156    166    445    492    Ljava/io/IOException;
        //  156    166    492    539    Ljava/lang/SecurityException;
        //  156    166    539    546    Any
        //  180    185    358    398    Lcom/facebook/FacebookException;
        //  180    185    398    445    Lorg/json/JSONException;
        //  180    185    445    492    Ljava/io/IOException;
        //  180    185    492    539    Ljava/lang/SecurityException;
        //  180    185    539    546    Any
        //  199    208    358    398    Lcom/facebook/FacebookException;
        //  199    208    398    445    Lorg/json/JSONException;
        //  199    208    445    492    Ljava/io/IOException;
        //  199    208    492    539    Ljava/lang/SecurityException;
        //  199    208    539    546    Any
        //  284    290    358    398    Lcom/facebook/FacebookException;
        //  284    290    398    445    Lorg/json/JSONException;
        //  284    290    445    492    Ljava/io/IOException;
        //  284    290    492    539    Ljava/lang/SecurityException;
        //  284    290    539    546    Any
        //  333    344    358    398    Lcom/facebook/FacebookException;
        //  333    344    398    445    Lorg/json/JSONException;
        //  333    344    445    492    Ljava/io/IOException;
        //  333    344    492    539    Ljava/lang/SecurityException;
        //  333    344    539    546    Any
        //  362    381    539    546    Any
        //  384    391    539    546    Any
        //  402    421    539    546    Any
        //  424    438    539    546    Any
        //  449    468    539    546    Any
        //  471    485    539    546    Any
        //  496    515    539    546    Any
        //  518    532    539    546    Any
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
    
    public String getRawResponse() {
        return this.rawResponse;
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
