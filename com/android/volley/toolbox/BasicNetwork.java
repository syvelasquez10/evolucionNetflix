// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import org.apache.http.HttpResponse;
import java.io.Serializable;
import org.apache.http.StatusLine;
import com.android.volley.NetworkResponse;
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
    
    private byte[] entityToBytes(final HttpEntity p0, final Request p1) {
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
        //    18: astore          6
        //    20: aconst_null    
        //    21: astore          5
        //    23: aload           5
        //    25: astore          4
        //    27: aload_1        
        //    28: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    33: astore          7
        //    35: aload           7
        //    37: ifnonnull       81
        //    40: aload           5
        //    42: astore          4
        //    44: new             Lcom/android/volley/ServerError;
        //    47: dup            
        //    48: invokespecial   com/android/volley/ServerError.<init>:()V
        //    51: athrow         
        //    52: astore          5
        //    54: aload_1        
        //    55: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //    60: aload_2        
        //    61: invokevirtual   com/android/volley/Request.releaseResources:()V
        //    64: aload_0        
        //    65: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //    68: aload           4
        //    70: invokevirtual   com/android/volley/toolbox/ByteArrayPool.returnBuf:([B)V
        //    73: aload           6
        //    75: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.close:()V
        //    78: aload           5
        //    80: athrow         
        //    81: aload           5
        //    83: astore          4
        //    85: aload_0        
        //    86: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //    89: sipush          1024
        //    92: invokevirtual   com/android/volley/toolbox/ByteArrayPool.getBuf:(I)[B
        //    95: astore          5
        //    97: aload           5
        //    99: astore          4
        //   101: aload           7
        //   103: aload           5
        //   105: invokevirtual   java/io/InputStream.read:([B)I
        //   108: istore_3       
        //   109: iload_3        
        //   110: iconst_m1      
        //   111: if_icmpeq       130
        //   114: aload           5
        //   116: astore          4
        //   118: aload           6
        //   120: aload           5
        //   122: iconst_0       
        //   123: iload_3        
        //   124: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.write:([BII)V
        //   127: goto            97
        //   130: aload           5
        //   132: astore          4
        //   134: aload           6
        //   136: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.toByteArray:()[B
        //   139: astore          7
        //   141: aload_1        
        //   142: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   147: aload_2        
        //   148: invokevirtual   com/android/volley/Request.releaseResources:()V
        //   151: aload_0        
        //   152: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //   155: aload           5
        //   157: invokevirtual   com/android/volley/toolbox/ByteArrayPool.returnBuf:([B)V
        //   160: aload           6
        //   162: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.close:()V
        //   165: aload           7
        //   167: areturn        
        //   168: astore_1       
        //   169: ldc             "Error occured when calling consumingContent"
        //   171: iconst_0       
        //   172: anewarray       Ljava/lang/Object;
        //   175: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   178: goto            151
        //   181: astore_1       
        //   182: ldc             "Error occured when calling consumingContent"
        //   184: iconst_0       
        //   185: anewarray       Ljava/lang/Object;
        //   188: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   191: goto            64
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  27     35     52     81     Any
        //  44     52     52     81     Any
        //  54     64     181    194    Ljava/io/IOException;
        //  85     97     52     81     Any
        //  101    109    52     81     Any
        //  118    127    52     81     Any
        //  134    141    52     81     Any
        //  141    151    168    181    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
    
    private void logSlowRequests(final long n, final Request<?> request, final NetworkResponse networkResponse, final StatusLine statusLine) {
        if (n > BasicNetwork.SLOW_REQUEST_THRESHOLD_MS || BasicNetwork.DEBUG) {
            Serializable value;
            if (networkResponse != null && networkResponse.data != null) {
                value = networkResponse.data.length;
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
        //    72: ldc_w           "IO exception reading redirect response %s"
        //    75: iconst_1       
        //    76: anewarray       Ljava/lang/Object;
        //    79: dup            
        //    80: iconst_0       
        //    81: aload_1        
        //    82: aastore        
        //    83: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    86: aconst_null    
        //    87: areturn        
        //    88: astore_1       
        //    89: ldc_w           "Json exception reading redirect response %s"
        //    92: iconst_1       
        //    93: anewarray       Ljava/lang/Object;
        //    96: dup            
        //    97: iconst_0       
        //    98: aload_1        
        //    99: aastore        
        //   100: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   103: aconst_null    
        //   104: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  32     42     71     88     Ljava/io/IOException;
        //  42     51     88     105    Lorg/json/JSONException;
        //  53     69     88     105    Lorg/json/JSONException;
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
        //     5: astore          6
        //     7: aconst_null    
        //     8: astore          9
        //    10: new             Ljava/util/HashMap;
        //    13: dup            
        //    14: invokespecial   java/util/HashMap.<init>:()V
        //    17: astore          7
        //    19: new             Ljava/util/HashMap;
        //    22: dup            
        //    23: invokespecial   java/util/HashMap.<init>:()V
        //    26: astore          5
        //    28: aload_0        
        //    29: aload           5
        //    31: aload_1        
        //    32: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //    35: invokespecial   com/android/volley/toolbox/BasicNetwork.addCacheHeaders:(Ljava/util/Map;Lcom/android/volley/Cache$Entry;)V
        //    38: aload_0        
        //    39: getfield        com/android/volley/toolbox/BasicNetwork.mHttpStack:Lcom/android/volley/toolbox/HttpStack;
        //    42: aload_1        
        //    43: aload           5
        //    45: invokeinterface com/android/volley/toolbox/HttpStack.performRequest:(Lcom/android/volley/Request;Ljava/util/Map;)Lorg/apache/http/HttpResponse;
        //    50: astore          5
        //    52: aload           5
        //    54: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    59: astore          10
        //    61: aload           10
        //    63: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //    68: istore_2       
        //    69: ldc_w           "Http status: %d"
        //    72: iconst_1       
        //    73: anewarray       Ljava/lang/Object;
        //    76: dup            
        //    77: iconst_0       
        //    78: iload_2        
        //    79: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    82: aastore        
        //    83: invokestatic    com/android/volley/VolleyLog.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    86: iload_2        
        //    87: sipush          500
        //    90: if_icmpne       124
        //    93: new             Ljava/net/HttpRetryException;
        //    96: dup            
        //    97: ldc_w           "retry"
        //   100: iload_2        
        //   101: invokespecial   java/net/HttpRetryException.<init>:(Ljava/lang/String;I)V
        //   104: athrow         
        //   105: astore          5
        //   107: ldc_w           "Http500"
        //   110: aload_1        
        //   111: new             Lcom/android/volley/TimeoutError;
        //   114: dup            
        //   115: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   118: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   121: goto            4
        //   124: iload_2        
        //   125: sipush          410
        //   128: if_icmpne       174
        //   131: aload_0        
        //   132: aload           5
        //   134: invokevirtual   com/android/volley/toolbox/BasicNetwork.getRedirectedHost:(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
        //   137: astore          6
        //   139: aload_1        
        //   140: aload           6
        //   142: invokevirtual   com/android/volley/Request.changeHostUrl:(Ljava/lang/String;)V
        //   145: new             Lorg/apache/http/client/RedirectException;
        //   148: dup            
        //   149: aload           6
        //   151: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: astore          5
        //   157: ldc_w           "socket"
        //   160: aload_1        
        //   161: new             Lcom/android/volley/TimeoutError;
        //   164: dup            
        //   165: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   168: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   171: goto            4
        //   174: aload           5
        //   176: invokeinterface org/apache/http/HttpResponse.getAllHeaders:()[Lorg/apache/http/Header;
        //   181: invokestatic    com/android/volley/toolbox/BasicNetwork.convertHeaders:([Lorg/apache/http/Header;)Ljava/util/Map;
        //   184: astore          6
        //   186: iload_2        
        //   187: sipush          301
        //   190: if_icmpeq       207
        //   193: iload_2        
        //   194: sipush          302
        //   197: if_icmpeq       207
        //   200: iload_2        
        //   201: sipush          307
        //   204: if_icmpne       305
        //   207: aload           6
        //   209: ldc_w           "Location"
        //   212: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   217: checkcast       Ljava/lang/String;
        //   220: astore          7
        //   222: aload           7
        //   224: ifnull          262
        //   227: aload_1        
        //   228: aload           7
        //   230: invokevirtual   com/android/volley/Request.changeToRedirectedUrl:(Ljava/lang/String;)V
        //   233: new             Lorg/apache/http/client/RedirectException;
        //   236: dup            
        //   237: aload           7
        //   239: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   242: athrow         
        //   243: astore          5
        //   245: ldc_w           "connection"
        //   248: aload_1        
        //   249: new             Lcom/android/volley/TimeoutError;
        //   252: dup            
        //   253: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   256: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   259: goto            4
        //   262: new             Ljava/io/IOException;
        //   265: dup            
        //   266: invokespecial   java/io/IOException.<init>:()V
        //   269: athrow         
        //   270: astore          5
        //   272: new             Ljava/lang/RuntimeException;
        //   275: dup            
        //   276: new             Ljava/lang/StringBuilder;
        //   279: dup            
        //   280: invokespecial   java/lang/StringBuilder.<init>:()V
        //   283: ldc_w           "Bad URL "
        //   286: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   289: aload_1        
        //   290: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   299: aload           5
        //   301: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   304: athrow         
        //   305: iload_2        
        //   306: sipush          304
        //   309: if_icmpne       333
        //   312: new             Lcom/android/volley/NetworkResponse;
        //   315: dup            
        //   316: sipush          304
        //   319: aload_1        
        //   320: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //   323: getfield        com/android/volley/Cache$Entry.data:[B
        //   326: aload           6
        //   328: iconst_1       
        //   329: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   332: areturn        
        //   333: aload_1        
        //   334: instanceof      Lcom/android/volley/toolbox/ProgressiveRequest;
        //   337: ifeq            464
        //   340: new             Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   343: dup            
        //   344: iload_2        
        //   345: aload           5
        //   347: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   352: aload           6
        //   354: iconst_0       
        //   355: invokespecial   com/android/volley/toolbox/ProgressiveNetworkResponse.<init>:(ILorg/apache/http/HttpEntity;Ljava/util/Map;Z)V
        //   358: astore          8
        //   360: iconst_0       
        //   361: newarray        B
        //   363: astore          7
        //   365: aload_0        
        //   366: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   369: lload_3        
        //   370: lsub           
        //   371: aload_1        
        //   372: aload           8
        //   374: aload           10
        //   376: invokespecial   com/android/volley/toolbox/BasicNetwork.logSlowRequests:(JLcom/android/volley/Request;Lcom/android/volley/NetworkResponse;Lorg/apache/http/StatusLine;)V
        //   379: iload_2        
        //   380: sipush          200
        //   383: if_icmpeq       733
        //   386: iload_2        
        //   387: sipush          204
        //   390: if_icmpeq       733
        //   393: iload_2        
        //   394: sipush          206
        //   397: if_icmpeq       733
        //   400: iload_2        
        //   401: sipush          202
        //   404: if_icmpeq       733
        //   407: aload           8
        //   409: instanceof      Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   412: ifeq            432
        //   415: aload           8
        //   417: checkcast       Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   420: invokevirtual   com/android/volley/toolbox/ProgressiveNetworkResponse.getHttpEntity:()Lorg/apache/http/HttpEntity;
        //   423: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   428: aload_1        
        //   429: invokevirtual   com/android/volley/Request.releaseResources:()V
        //   432: new             Ljava/io/IOException;
        //   435: dup            
        //   436: invokespecial   java/io/IOException.<init>:()V
        //   439: athrow         
        //   440: astore          5
        //   442: ldc_w           "redirect"
        //   445: aload_1        
        //   446: new             Lcom/android/volley/VolleyError;
        //   449: dup            
        //   450: aload           5
        //   452: invokevirtual   org/apache/http/client/RedirectException.getMessage:()Ljava/lang/String;
        //   455: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;)V
        //   458: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   461: goto            4
        //   464: aload_0        
        //   465: aload           5
        //   467: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   472: aload_1        
        //   473: invokespecial   com/android/volley/toolbox/BasicNetwork.entityToBytes:(Lorg/apache/http/HttpEntity;Lcom/android/volley/Request;)[B
        //   476: astore          8
        //   478: new             Lcom/android/volley/NetworkResponse;
        //   481: dup            
        //   482: iload_2        
        //   483: aload           8
        //   485: aload           6
        //   487: iconst_0       
        //   488: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   491: astore          9
        //   493: aload           8
        //   495: astore          7
        //   497: aload           9
        //   499: astore          8
        //   501: goto            365
        //   504: astore          5
        //   506: aload           9
        //   508: astore          8
        //   510: ldc_w           "ioexception:"
        //   513: iconst_1       
        //   514: anewarray       Ljava/lang/Object;
        //   517: dup            
        //   518: iconst_0       
        //   519: aload           5
        //   521: aastore        
        //   522: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   525: aload           6
        //   527: ifnull          620
        //   530: aload           6
        //   532: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   537: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   542: istore_2       
        //   543: ldc_w           "Unexpected response code %d for %s"
        //   546: iconst_2       
        //   547: anewarray       Ljava/lang/Object;
        //   550: dup            
        //   551: iconst_0       
        //   552: iload_2        
        //   553: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   556: aastore        
        //   557: dup            
        //   558: iconst_1       
        //   559: aload_1        
        //   560: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   563: aastore        
        //   564: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   567: aload           8
        //   569: ifnull          640
        //   572: new             Lcom/android/volley/NetworkResponse;
        //   575: dup            
        //   576: iload_2        
        //   577: aload           8
        //   579: aload           7
        //   581: iconst_0       
        //   582: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   585: astore          5
        //   587: iload_2        
        //   588: sipush          401
        //   591: if_icmpeq       601
        //   594: iload_2        
        //   595: sipush          403
        //   598: if_icmpne       630
        //   601: ldc_w           "auth"
        //   604: aload_1        
        //   605: new             Lcom/android/volley/AuthFailureError;
        //   608: dup            
        //   609: aload           5
        //   611: invokespecial   com/android/volley/AuthFailureError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   614: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   617: goto            4
        //   620: new             Lcom/android/volley/NoConnectionError;
        //   623: dup            
        //   624: aload           5
        //   626: invokespecial   com/android/volley/NoConnectionError.<init>:(Ljava/lang/Throwable;)V
        //   629: athrow         
        //   630: new             Lcom/android/volley/ServerError;
        //   633: dup            
        //   634: aload           5
        //   636: invokespecial   com/android/volley/ServerError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   639: athrow         
        //   640: new             Lcom/android/volley/NetworkError;
        //   643: dup            
        //   644: aconst_null    
        //   645: invokespecial   com/android/volley/NetworkError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   648: athrow         
        //   649: astore          8
        //   651: aload           5
        //   653: astore          6
        //   655: aload           8
        //   657: astore          5
        //   659: aload           9
        //   661: astore          8
        //   663: goto            510
        //   666: astore          8
        //   668: aload           6
        //   670: astore          7
        //   672: aload           5
        //   674: astore          6
        //   676: aload           8
        //   678: astore          5
        //   680: aload           9
        //   682: astore          8
        //   684: goto            510
        //   687: astore          7
        //   689: aload           5
        //   691: astore          9
        //   693: aload           7
        //   695: astore          5
        //   697: aload           6
        //   699: astore          7
        //   701: aload           9
        //   703: astore          6
        //   705: goto            510
        //   708: astore          10
        //   710: aload           7
        //   712: astore          8
        //   714: aload           5
        //   716: astore          9
        //   718: aload           10
        //   720: astore          5
        //   722: aload           6
        //   724: astore          7
        //   726: aload           9
        //   728: astore          6
        //   730: goto            510
        //   733: aload           8
        //   735: areturn        
        //    Signature:
        //  (Lcom/android/volley/Request<*>;)Lcom/android/volley/NetworkResponse;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                          
        //  -----  -----  -----  -----  ----------------------------------------------
        //  19     52     105    124    Ljava/net/HttpRetryException;
        //  19     52     155    174    Ljava/net/SocketTimeoutException;
        //  19     52     243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  19     52     270    305    Ljava/net/MalformedURLException;
        //  19     52     440    464    Lorg/apache/http/client/RedirectException;
        //  19     52     504    510    Ljava/io/IOException;
        //  52     86     105    124    Ljava/net/HttpRetryException;
        //  52     86     155    174    Ljava/net/SocketTimeoutException;
        //  52     86     243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  52     86     270    305    Ljava/net/MalformedURLException;
        //  52     86     440    464    Lorg/apache/http/client/RedirectException;
        //  52     86     649    666    Ljava/io/IOException;
        //  93     105    105    124    Ljava/net/HttpRetryException;
        //  93     105    155    174    Ljava/net/SocketTimeoutException;
        //  93     105    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  93     105    270    305    Ljava/net/MalformedURLException;
        //  93     105    440    464    Lorg/apache/http/client/RedirectException;
        //  93     105    649    666    Ljava/io/IOException;
        //  131    155    105    124    Ljava/net/HttpRetryException;
        //  131    155    155    174    Ljava/net/SocketTimeoutException;
        //  131    155    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  131    155    270    305    Ljava/net/MalformedURLException;
        //  131    155    440    464    Lorg/apache/http/client/RedirectException;
        //  131    155    649    666    Ljava/io/IOException;
        //  174    186    105    124    Ljava/net/HttpRetryException;
        //  174    186    155    174    Ljava/net/SocketTimeoutException;
        //  174    186    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  174    186    270    305    Ljava/net/MalformedURLException;
        //  174    186    440    464    Lorg/apache/http/client/RedirectException;
        //  174    186    649    666    Ljava/io/IOException;
        //  207    222    105    124    Ljava/net/HttpRetryException;
        //  207    222    155    174    Ljava/net/SocketTimeoutException;
        //  207    222    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  207    222    270    305    Ljava/net/MalformedURLException;
        //  207    222    440    464    Lorg/apache/http/client/RedirectException;
        //  207    222    666    687    Ljava/io/IOException;
        //  227    243    105    124    Ljava/net/HttpRetryException;
        //  227    243    155    174    Ljava/net/SocketTimeoutException;
        //  227    243    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  227    243    270    305    Ljava/net/MalformedURLException;
        //  227    243    440    464    Lorg/apache/http/client/RedirectException;
        //  227    243    666    687    Ljava/io/IOException;
        //  262    270    105    124    Ljava/net/HttpRetryException;
        //  262    270    155    174    Ljava/net/SocketTimeoutException;
        //  262    270    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  262    270    270    305    Ljava/net/MalformedURLException;
        //  262    270    440    464    Lorg/apache/http/client/RedirectException;
        //  262    270    666    687    Ljava/io/IOException;
        //  312    333    105    124    Ljava/net/HttpRetryException;
        //  312    333    155    174    Ljava/net/SocketTimeoutException;
        //  312    333    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  312    333    270    305    Ljava/net/MalformedURLException;
        //  312    333    440    464    Lorg/apache/http/client/RedirectException;
        //  312    333    666    687    Ljava/io/IOException;
        //  333    365    105    124    Ljava/net/HttpRetryException;
        //  333    365    155    174    Ljava/net/SocketTimeoutException;
        //  333    365    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  333    365    270    305    Ljava/net/MalformedURLException;
        //  333    365    440    464    Lorg/apache/http/client/RedirectException;
        //  333    365    666    687    Ljava/io/IOException;
        //  365    379    105    124    Ljava/net/HttpRetryException;
        //  365    379    155    174    Ljava/net/SocketTimeoutException;
        //  365    379    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  365    379    270    305    Ljava/net/MalformedURLException;
        //  365    379    440    464    Lorg/apache/http/client/RedirectException;
        //  365    379    708    733    Ljava/io/IOException;
        //  407    432    105    124    Ljava/net/HttpRetryException;
        //  407    432    155    174    Ljava/net/SocketTimeoutException;
        //  407    432    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  407    432    270    305    Ljava/net/MalformedURLException;
        //  407    432    440    464    Lorg/apache/http/client/RedirectException;
        //  407    432    708    733    Ljava/io/IOException;
        //  432    440    105    124    Ljava/net/HttpRetryException;
        //  432    440    155    174    Ljava/net/SocketTimeoutException;
        //  432    440    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  432    440    270    305    Ljava/net/MalformedURLException;
        //  432    440    440    464    Lorg/apache/http/client/RedirectException;
        //  432    440    708    733    Ljava/io/IOException;
        //  464    478    105    124    Ljava/net/HttpRetryException;
        //  464    478    155    174    Ljava/net/SocketTimeoutException;
        //  464    478    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  464    478    270    305    Ljava/net/MalformedURLException;
        //  464    478    440    464    Lorg/apache/http/client/RedirectException;
        //  464    478    666    687    Ljava/io/IOException;
        //  478    493    105    124    Ljava/net/HttpRetryException;
        //  478    493    155    174    Ljava/net/SocketTimeoutException;
        //  478    493    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  478    493    270    305    Ljava/net/MalformedURLException;
        //  478    493    440    464    Lorg/apache/http/client/RedirectException;
        //  478    493    687    708    Ljava/io/IOException;
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
