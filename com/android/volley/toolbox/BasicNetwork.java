// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import org.apache.http.HttpResponse;
import java.io.Serializable;
import org.apache.http.StatusLine;
import org.apache.http.HttpEntity;
import java.util.HashMap;
import org.apache.http.Header;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Date;
import com.android.volley.Cache$Entry;
import java.util.Map;
import com.android.volley.VolleyLog;
import com.android.volley.Network;

public class BasicNetwork implements Network
{
    protected static final boolean DEBUG;
    private static int DEFAULT_POOL_SIZE;
    private static int SLOW_REQUEST_THRESHOLD_MS;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;
    
    static {
        DEBUG = VolleyLog.DEBUG;
        BasicNetwork.SLOW_REQUEST_THRESHOLD_MS = 3000;
        BasicNetwork.DEFAULT_POOL_SIZE = 4096;
    }
    
    public BasicNetwork(final HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(BasicNetwork.DEFAULT_POOL_SIZE));
    }
    
    public BasicNetwork(final HttpStack mHttpStack, final ByteArrayPool mPool) {
        this.mHttpStack = mHttpStack;
        this.mPool = mPool;
    }
    
    private void addCacheHeaders(final Map<String, String> map, final Cache$Entry cache$Entry) {
        if (cache$Entry != null) {
            if (cache$Entry.etag != null) {
                map.put("If-None-Match", cache$Entry.etag);
            }
            if (cache$Entry.serverDate > 0L) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(cache$Entry.serverDate)));
            }
        }
    }
    
    private static void attemptRetryOnException(final String s, final Request<?> request, final VolleyError volleyError) {
        final RetryPolicy retryPolicy = request.getRetryPolicy();
        final int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", s, timeoutMs));
        }
        catch (VolleyError volleyError) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", s, timeoutMs));
            throw volleyError;
        }
    }
    
    private static Map<String, String> convertHeaders(final Header[] array) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < array.length; ++i) {
            hashMap.put(array[i].getName(), array[i].getValue());
        }
        return hashMap;
    }
    
    private byte[] entityToBytes(final HttpEntity p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/android/volley/toolbox/PoolingByteArrayOutputStream;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //     8: aload_1        
        //     9: invokeinterface org/apache/http/HttpEntity.getContentLength:()J
        //    14: l2i            
        //    15: invokespecial   com/android/volley/toolbox/PoolingByteArrayOutputStream.<init>:(Lcom/android/volley/toolbox/ByteArrayPool;I)V
        //    18: astore          5
        //    20: aconst_null    
        //    21: astore          4
        //    23: aload           4
        //    25: astore_3       
        //    26: aload_1        
        //    27: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    32: astore          6
        //    34: aload           6
        //    36: ifnonnull       74
        //    39: aload           4
        //    41: astore_3       
        //    42: new             Lcom/android/volley/ServerError;
        //    45: dup            
        //    46: invokespecial   com/android/volley/ServerError.<init>:()V
        //    49: athrow         
        //    50: astore          4
        //    52: aload_1        
        //    53: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //    58: aload_0        
        //    59: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //    62: aload_3        
        //    63: invokevirtual   com/android/volley/toolbox/ByteArrayPool.returnBuf:([B)V
        //    66: aload           5
        //    68: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.close:()V
        //    71: aload           4
        //    73: athrow         
        //    74: aload           4
        //    76: astore_3       
        //    77: aload_0        
        //    78: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //    81: sipush          1024
        //    84: invokevirtual   com/android/volley/toolbox/ByteArrayPool.getBuf:(I)[B
        //    87: astore          4
        //    89: aload           4
        //    91: astore_3       
        //    92: aload           6
        //    94: aload           4
        //    96: invokevirtual   java/io/InputStream.read:([B)I
        //    99: istore_2       
        //   100: iload_2        
        //   101: iconst_m1      
        //   102: if_icmpeq       120
        //   105: aload           4
        //   107: astore_3       
        //   108: aload           5
        //   110: aload           4
        //   112: iconst_0       
        //   113: iload_2        
        //   114: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.write:([BII)V
        //   117: goto            89
        //   120: aload           4
        //   122: astore_3       
        //   123: aload           5
        //   125: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.toByteArray:()[B
        //   128: astore          6
        //   130: aload_1        
        //   131: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   136: aload_0        
        //   137: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //   140: aload           4
        //   142: invokevirtual   com/android/volley/toolbox/ByteArrayPool.returnBuf:([B)V
        //   145: aload           5
        //   147: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.close:()V
        //   150: aload           6
        //   152: areturn        
        //   153: astore_1       
        //   154: ldc             "Error occured when calling consumingContent"
        //   156: iconst_0       
        //   157: anewarray       Ljava/lang/Object;
        //   160: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   163: goto            136
        //   166: astore_1       
        //   167: ldc             "Error occured when calling consumingContent"
        //   169: iconst_0       
        //   170: anewarray       Ljava/lang/Object;
        //   173: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   176: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  26     34     50     74     Any
        //  42     50     50     74     Any
        //  52     58     166    179    Ljava/io/IOException;
        //  77     89     50     74     Any
        //  92     100    50     74     Any
        //  108    117    50     74     Any
        //  123    130    50     74     Any
        //  130    136    153    166    Ljava/io/IOException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void logSlowRequests(final long n, final Request<?> request, final byte[] array, final StatusLine statusLine) {
        if (n > BasicNetwork.SLOW_REQUEST_THRESHOLD_MS || BasicNetwork.DEBUG) {
            Serializable value;
            if (array != null) {
                value = array.length;
            }
            else {
                value = "null";
            }
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", request, n, value, statusLine.getStatusCode(), request.getRetryPolicy().getCurrentRetryCount());
        }
    }
    
    public String getRedirectedHost(final HttpResponse p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aload_1        
        //     3: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //     8: astore_3       
        //     9: aload_1        
        //    10: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    15: ifnull          22
        //    18: aload_3        
        //    19: ifnonnull       32
        //    22: new             Ljava/lang/NullPointerException;
        //    25: dup            
        //    26: ldc             "Status/entity is NULL. It should NOT happen!"
        //    28: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    31: athrow         
        //    32: aload_3        
        //    33: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    38: invokestatic    com/android/volley/toolbox/InputStreamUtil.convertStreamToString:(Ljava/io/InputStream;)Ljava/lang/String;
        //    41: astore_1       
        //    42: new             Lorg/json/JSONObject;
        //    45: dup            
        //    46: aload_1        
        //    47: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    50: astore_3       
        //    51: aload_2        
        //    52: astore_1       
        //    53: aload_3        
        //    54: ldc             "host"
        //    56: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    59: ifeq            69
        //    62: aload_3        
        //    63: ldc             "host"
        //    65: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    68: astore_1       
        //    69: aload_1        
        //    70: areturn        
        //    71: astore_1       
        //    72: ldc             "IO exception reading redirect response %s"
        //    74: iconst_1       
        //    75: anewarray       Ljava/lang/Object;
        //    78: dup            
        //    79: iconst_0       
        //    80: aload_1        
        //    81: aastore        
        //    82: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    85: aconst_null    
        //    86: areturn        
        //    87: astore_1       
        //    88: ldc_w           "Json exception reading redirect response %s"
        //    91: iconst_1       
        //    92: anewarray       Ljava/lang/Object;
        //    95: dup            
        //    96: iconst_0       
        //    97: aload_1        
        //    98: aastore        
        //    99: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   102: aconst_null    
        //   103: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  32     42     71     87     Ljava/io/IOException;
        //  42     51     87     104    Lorg/json/JSONException;
        //  53     69     87     104    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    public NetworkResponse performRequest(final Request<?> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //     3: lstore_3       
        //     4: aconst_null    
        //     5: astore          9
        //     7: aconst_null    
        //     8: astore          8
        //    10: aconst_null    
        //    11: astore          10
        //    13: new             Ljava/util/HashMap;
        //    16: dup            
        //    17: invokespecial   java/util/HashMap.<init>:()V
        //    20: astore          7
        //    22: new             Ljava/util/HashMap;
        //    25: dup            
        //    26: invokespecial   java/util/HashMap.<init>:()V
        //    29: astore          5
        //    31: aload_0        
        //    32: aload           5
        //    34: aload_1        
        //    35: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //    38: invokespecial   com/android/volley/toolbox/BasicNetwork.addCacheHeaders:(Ljava/util/Map;Lcom/android/volley/Cache$Entry;)V
        //    41: aload_0        
        //    42: getfield        com/android/volley/toolbox/BasicNetwork.mHttpStack:Lcom/android/volley/toolbox/HttpStack;
        //    45: aload_1        
        //    46: aload           5
        //    48: invokeinterface com/android/volley/toolbox/HttpStack.performRequest:(Lcom/android/volley/Request;Ljava/util/Map;)Lorg/apache/http/HttpResponse;
        //    53: astore          6
        //    55: aload           6
        //    57: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    62: astore          11
        //    64: aload           11
        //    66: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //    71: istore_2       
        //    72: ldc_w           "Http status: %d"
        //    75: iconst_1       
        //    76: anewarray       Ljava/lang/Object;
        //    79: dup            
        //    80: iconst_0       
        //    81: iload_2        
        //    82: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    85: aastore        
        //    86: invokestatic    com/android/volley/VolleyLog.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    89: iload_2        
        //    90: sipush          500
        //    93: if_icmpne       127
        //    96: new             Ljava/net/HttpRetryException;
        //    99: dup            
        //   100: ldc_w           "retry"
        //   103: iload_2        
        //   104: invokespecial   java/net/HttpRetryException.<init>:(Ljava/lang/String;I)V
        //   107: athrow         
        //   108: astore          5
        //   110: ldc_w           "Http500"
        //   113: aload_1        
        //   114: new             Lcom/android/volley/TimeoutError;
        //   117: dup            
        //   118: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   121: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   124: goto            4
        //   127: iload_2        
        //   128: sipush          410
        //   131: if_icmpne       177
        //   134: aload_0        
        //   135: aload           6
        //   137: invokevirtual   com/android/volley/toolbox/BasicNetwork.getRedirectedHost:(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
        //   140: astore          5
        //   142: aload_1        
        //   143: aload           5
        //   145: invokevirtual   com/android/volley/Request.changeHostUrl:(Ljava/lang/String;)V
        //   148: new             Lorg/apache/http/client/RedirectException;
        //   151: dup            
        //   152: aload           5
        //   154: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   157: athrow         
        //   158: astore          5
        //   160: ldc_w           "socket"
        //   163: aload_1        
        //   164: new             Lcom/android/volley/TimeoutError;
        //   167: dup            
        //   168: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   171: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   174: goto            4
        //   177: aload           6
        //   179: invokeinterface org/apache/http/HttpResponse.getAllHeaders:()[Lorg/apache/http/Header;
        //   184: invokestatic    com/android/volley/toolbox/BasicNetwork.convertHeaders:([Lorg/apache/http/Header;)Ljava/util/Map;
        //   187: astore          9
        //   189: iload_2        
        //   190: sipush          301
        //   193: if_icmpeq       210
        //   196: iload_2        
        //   197: sipush          302
        //   200: if_icmpeq       210
        //   203: iload_2        
        //   204: sipush          307
        //   207: if_icmpne       324
        //   210: aload           10
        //   212: astore          5
        //   214: aload           9
        //   216: ldc_w           "Location"
        //   219: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   224: checkcast       Ljava/lang/String;
        //   227: astore          7
        //   229: aload           7
        //   231: ifnull          277
        //   234: aload           10
        //   236: astore          5
        //   238: aload_1        
        //   239: aload           7
        //   241: invokevirtual   com/android/volley/Request.changeToRedirectedUrl:(Ljava/lang/String;)V
        //   244: aload           10
        //   246: astore          5
        //   248: new             Lorg/apache/http/client/RedirectException;
        //   251: dup            
        //   252: aload           7
        //   254: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   257: athrow         
        //   258: astore          5
        //   260: ldc_w           "connection"
        //   263: aload_1        
        //   264: new             Lcom/android/volley/TimeoutError;
        //   267: dup            
        //   268: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   271: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   274: goto            4
        //   277: aload           10
        //   279: astore          5
        //   281: new             Ljava/io/IOException;
        //   284: dup            
        //   285: invokespecial   java/io/IOException.<init>:()V
        //   288: athrow         
        //   289: astore          5
        //   291: new             Ljava/lang/RuntimeException;
        //   294: dup            
        //   295: new             Ljava/lang/StringBuilder;
        //   298: dup            
        //   299: invokespecial   java/lang/StringBuilder.<init>:()V
        //   302: ldc_w           "Bad URL "
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: aload_1        
        //   309: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   318: aload           5
        //   320: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   323: athrow         
        //   324: iload_2        
        //   325: sipush          304
        //   328: if_icmpne       356
        //   331: aload           10
        //   333: astore          5
        //   335: new             Lcom/android/volley/NetworkResponse;
        //   338: dup            
        //   339: sipush          304
        //   342: aload_1        
        //   343: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //   346: getfield        com/android/volley/Cache$Entry.data:[B
        //   349: aload           9
        //   351: iconst_1       
        //   352: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   355: areturn        
        //   356: aload           10
        //   358: astore          5
        //   360: aload_0        
        //   361: aload           6
        //   363: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   368: invokespecial   com/android/volley/toolbox/BasicNetwork.entityToBytes:(Lorg/apache/http/HttpEntity;)[B
        //   371: astore          7
        //   373: aload           7
        //   375: astore          5
        //   377: aload_0        
        //   378: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   381: lload_3        
        //   382: lsub           
        //   383: aload_1        
        //   384: aload           7
        //   386: aload           11
        //   388: invokespecial   com/android/volley/toolbox/BasicNetwork.logSlowRequests:(JLcom/android/volley/Request;[BLorg/apache/http/StatusLine;)V
        //   391: iload_2        
        //   392: sipush          200
        //   395: if_icmpeq       455
        //   398: iload_2        
        //   399: sipush          204
        //   402: if_icmpeq       455
        //   405: iload_2        
        //   406: sipush          206
        //   409: if_icmpeq       455
        //   412: iload_2        
        //   413: sipush          202
        //   416: if_icmpeq       455
        //   419: aload           7
        //   421: astore          5
        //   423: new             Ljava/io/IOException;
        //   426: dup            
        //   427: invokespecial   java/io/IOException.<init>:()V
        //   430: athrow         
        //   431: astore          5
        //   433: ldc_w           "redirect"
        //   436: aload_1        
        //   437: new             Lcom/android/volley/VolleyError;
        //   440: dup            
        //   441: aload           5
        //   443: invokevirtual   org/apache/http/client/RedirectException.getMessage:()Ljava/lang/String;
        //   446: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;)V
        //   449: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   452: goto            4
        //   455: aload           7
        //   457: astore          5
        //   459: new             Lcom/android/volley/NetworkResponse;
        //   462: dup            
        //   463: iload_2        
        //   464: aload           7
        //   466: aload           9
        //   468: iconst_0       
        //   469: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   472: astore          7
        //   474: aload           7
        //   476: areturn        
        //   477: astore          10
        //   479: aload           9
        //   481: astore          7
        //   483: aload           5
        //   485: astore          8
        //   487: aload           10
        //   489: astore          5
        //   491: aload           6
        //   493: ifnull          586
        //   496: aload           6
        //   498: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   503: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   508: istore_2       
        //   509: ldc_w           "Unexpected response code %d for %s"
        //   512: iconst_2       
        //   513: anewarray       Ljava/lang/Object;
        //   516: dup            
        //   517: iconst_0       
        //   518: iload_2        
        //   519: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   522: aastore        
        //   523: dup            
        //   524: iconst_1       
        //   525: aload_1        
        //   526: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   529: aastore        
        //   530: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   533: aload           8
        //   535: ifnull          606
        //   538: new             Lcom/android/volley/NetworkResponse;
        //   541: dup            
        //   542: iload_2        
        //   543: aload           8
        //   545: aload           7
        //   547: iconst_0       
        //   548: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   551: astore          5
        //   553: iload_2        
        //   554: sipush          401
        //   557: if_icmpeq       567
        //   560: iload_2        
        //   561: sipush          403
        //   564: if_icmpne       596
        //   567: ldc_w           "auth"
        //   570: aload_1        
        //   571: new             Lcom/android/volley/AuthFailureError;
        //   574: dup            
        //   575: aload           5
        //   577: invokespecial   com/android/volley/AuthFailureError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   580: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   583: goto            4
        //   586: new             Lcom/android/volley/NoConnectionError;
        //   589: dup            
        //   590: aload           5
        //   592: invokespecial   com/android/volley/NoConnectionError.<init>:(Ljava/lang/Throwable;)V
        //   595: athrow         
        //   596: new             Lcom/android/volley/ServerError;
        //   599: dup            
        //   600: aload           5
        //   602: invokespecial   com/android/volley/ServerError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   605: athrow         
        //   606: new             Lcom/android/volley/NetworkError;
        //   609: dup            
        //   610: aconst_null    
        //   611: invokespecial   com/android/volley/NetworkError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   614: athrow         
        //   615: astore          5
        //   617: aload           9
        //   619: astore          6
        //   621: goto            491
        //   624: astore          5
        //   626: goto            491
        //    Signature:
        //  (Lcom/android/volley/Request<*>;)Lcom/android/volley/NetworkResponse;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                          
        //  -----  -----  -----  -----  ----------------------------------------------
        //  22     55     108    127    Ljava/net/HttpRetryException;
        //  22     55     158    177    Ljava/net/SocketTimeoutException;
        //  22     55     258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  22     55     289    324    Ljava/net/MalformedURLException;
        //  22     55     431    455    Lorg/apache/http/client/RedirectException;
        //  22     55     615    624    Ljava/io/IOException;
        //  55     89     108    127    Ljava/net/HttpRetryException;
        //  55     89     158    177    Ljava/net/SocketTimeoutException;
        //  55     89     258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  55     89     289    324    Ljava/net/MalformedURLException;
        //  55     89     431    455    Lorg/apache/http/client/RedirectException;
        //  55     89     624    629    Ljava/io/IOException;
        //  96     108    108    127    Ljava/net/HttpRetryException;
        //  96     108    158    177    Ljava/net/SocketTimeoutException;
        //  96     108    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  96     108    289    324    Ljava/net/MalformedURLException;
        //  96     108    431    455    Lorg/apache/http/client/RedirectException;
        //  96     108    624    629    Ljava/io/IOException;
        //  134    158    108    127    Ljava/net/HttpRetryException;
        //  134    158    158    177    Ljava/net/SocketTimeoutException;
        //  134    158    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  134    158    289    324    Ljava/net/MalformedURLException;
        //  134    158    431    455    Lorg/apache/http/client/RedirectException;
        //  134    158    624    629    Ljava/io/IOException;
        //  177    189    108    127    Ljava/net/HttpRetryException;
        //  177    189    158    177    Ljava/net/SocketTimeoutException;
        //  177    189    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  177    189    289    324    Ljava/net/MalformedURLException;
        //  177    189    431    455    Lorg/apache/http/client/RedirectException;
        //  177    189    624    629    Ljava/io/IOException;
        //  214    229    108    127    Ljava/net/HttpRetryException;
        //  214    229    158    177    Ljava/net/SocketTimeoutException;
        //  214    229    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  214    229    289    324    Ljava/net/MalformedURLException;
        //  214    229    431    455    Lorg/apache/http/client/RedirectException;
        //  214    229    477    491    Ljava/io/IOException;
        //  238    244    108    127    Ljava/net/HttpRetryException;
        //  238    244    158    177    Ljava/net/SocketTimeoutException;
        //  238    244    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  238    244    289    324    Ljava/net/MalformedURLException;
        //  238    244    431    455    Lorg/apache/http/client/RedirectException;
        //  238    244    477    491    Ljava/io/IOException;
        //  248    258    108    127    Ljava/net/HttpRetryException;
        //  248    258    158    177    Ljava/net/SocketTimeoutException;
        //  248    258    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  248    258    289    324    Ljava/net/MalformedURLException;
        //  248    258    431    455    Lorg/apache/http/client/RedirectException;
        //  248    258    477    491    Ljava/io/IOException;
        //  281    289    108    127    Ljava/net/HttpRetryException;
        //  281    289    158    177    Ljava/net/SocketTimeoutException;
        //  281    289    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  281    289    289    324    Ljava/net/MalformedURLException;
        //  281    289    431    455    Lorg/apache/http/client/RedirectException;
        //  281    289    477    491    Ljava/io/IOException;
        //  335    356    108    127    Ljava/net/HttpRetryException;
        //  335    356    158    177    Ljava/net/SocketTimeoutException;
        //  335    356    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  335    356    289    324    Ljava/net/MalformedURLException;
        //  335    356    431    455    Lorg/apache/http/client/RedirectException;
        //  335    356    477    491    Ljava/io/IOException;
        //  360    373    108    127    Ljava/net/HttpRetryException;
        //  360    373    158    177    Ljava/net/SocketTimeoutException;
        //  360    373    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  360    373    289    324    Ljava/net/MalformedURLException;
        //  360    373    431    455    Lorg/apache/http/client/RedirectException;
        //  360    373    477    491    Ljava/io/IOException;
        //  377    391    108    127    Ljava/net/HttpRetryException;
        //  377    391    158    177    Ljava/net/SocketTimeoutException;
        //  377    391    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  377    391    289    324    Ljava/net/MalformedURLException;
        //  377    391    431    455    Lorg/apache/http/client/RedirectException;
        //  377    391    477    491    Ljava/io/IOException;
        //  423    431    108    127    Ljava/net/HttpRetryException;
        //  423    431    158    177    Ljava/net/SocketTimeoutException;
        //  423    431    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  423    431    289    324    Ljava/net/MalformedURLException;
        //  423    431    431    455    Lorg/apache/http/client/RedirectException;
        //  423    431    477    491    Ljava/io/IOException;
        //  459    474    108    127    Ljava/net/HttpRetryException;
        //  459    474    158    177    Ljava/net/SocketTimeoutException;
        //  459    474    258    277    Lorg/apache/http/conn/ConnectTimeoutException;
        //  459    474    289    324    Ljava/net/MalformedURLException;
        //  459    474    431    455    Lorg/apache/http/client/RedirectException;
        //  459    474    477    491    Ljava/io/IOException;
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
}
