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
import com.netflix.cstatssamurai.ClientStats;
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
    
    private void collectNetworkStats(final Request<?> request, final int n, final long n2) {
        try {
            final String networkHistogramType = this.getNetworkHistogramType(request);
            if (networkHistogramType != null) {
                ClientStats.getInstance().addCount(networkHistogramType, "duration", (int)n2, 1);
                ClientStats.getInstance().addCount(networkHistogramType, "size", n, 1);
            }
        }
        catch (Exception ex) {
            VolleyLog.e(ex, "ClientStats: Exception:", new Object[0]);
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
    
    private String getNetworkHistogramType(final Request<?> request) {
        return ClientStats.getInstance().getNetworkHistogramType(request.getUrl());
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
        //    19: ifnonnull       33
        //    22: new             Ljava/lang/NullPointerException;
        //    25: dup            
        //    26: ldc_w           "Status/entity is NULL. It should NOT happen!"
        //    29: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    32: athrow         
        //    33: aload_3        
        //    34: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    39: invokestatic    com/android/volley/toolbox/InputStreamUtil.convertStreamToString:(Ljava/io/InputStream;)Ljava/lang/String;
        //    42: astore_1       
        //    43: new             Lorg/json/JSONObject;
        //    46: dup            
        //    47: aload_1        
        //    48: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    51: astore_3       
        //    52: aload_2        
        //    53: astore_1       
        //    54: aload_3        
        //    55: ldc_w           "host"
        //    58: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    61: ifeq            72
        //    64: aload_3        
        //    65: ldc_w           "host"
        //    68: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    71: astore_1       
        //    72: aload_1        
        //    73: areturn        
        //    74: astore_1       
        //    75: ldc_w           "IO exception reading redirect response %s"
        //    78: iconst_1       
        //    79: anewarray       Ljava/lang/Object;
        //    82: dup            
        //    83: iconst_0       
        //    84: aload_1        
        //    85: aastore        
        //    86: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    89: aconst_null    
        //    90: areturn        
        //    91: astore_1       
        //    92: ldc_w           "Json exception reading redirect response %s"
        //    95: iconst_1       
        //    96: anewarray       Ljava/lang/Object;
        //    99: dup            
        //   100: iconst_0       
        //   101: aload_1        
        //   102: aastore        
        //   103: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   106: aconst_null    
        //   107: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  33     43     74     91     Ljava/io/IOException;
        //  43     52     91     108    Lorg/json/JSONException;
        //  54     72     91     108    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
        //     5: astore          8
        //     7: aconst_null    
        //     8: astore          11
        //    10: new             Ljava/util/HashMap;
        //    13: dup            
        //    14: invokespecial   java/util/HashMap.<init>:()V
        //    17: astore          9
        //    19: new             Ljava/util/HashMap;
        //    22: dup            
        //    23: invokespecial   java/util/HashMap.<init>:()V
        //    26: astore          7
        //    28: aload_0        
        //    29: aload           7
        //    31: aload_1        
        //    32: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //    35: invokespecial   com/android/volley/toolbox/BasicNetwork.addCacheHeaders:(Ljava/util/Map;Lcom/android/volley/Cache$Entry;)V
        //    38: aload_0        
        //    39: getfield        com/android/volley/toolbox/BasicNetwork.mHttpStack:Lcom/android/volley/toolbox/HttpStack;
        //    42: aload_1        
        //    43: aload           7
        //    45: invokeinterface com/android/volley/toolbox/HttpStack.performRequest:(Lcom/android/volley/Request;Ljava/util/Map;)Lorg/apache/http/HttpResponse;
        //    50: astore          7
        //    52: aload           7
        //    54: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    59: astore          12
        //    61: aload           12
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
        //   105: astore          7
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
        //   132: aload           7
        //   134: invokevirtual   com/android/volley/toolbox/BasicNetwork.getRedirectedHost:(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
        //   137: astore          8
        //   139: aload_1        
        //   140: aload           8
        //   142: invokevirtual   com/android/volley/Request.changeHostUrl:(Ljava/lang/String;)V
        //   145: new             Lorg/apache/http/client/RedirectException;
        //   148: dup            
        //   149: aload           8
        //   151: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: astore          7
        //   157: ldc_w           "socket"
        //   160: aload_1        
        //   161: new             Lcom/android/volley/TimeoutError;
        //   164: dup            
        //   165: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   168: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   171: goto            4
        //   174: aload           7
        //   176: invokeinterface org/apache/http/HttpResponse.getAllHeaders:()[Lorg/apache/http/Header;
        //   181: invokestatic    com/android/volley/toolbox/BasicNetwork.convertHeaders:([Lorg/apache/http/Header;)Ljava/util/Map;
        //   184: astore          8
        //   186: iload_2        
        //   187: sipush          301
        //   190: if_icmpeq       207
        //   193: iload_2        
        //   194: sipush          302
        //   197: if_icmpeq       207
        //   200: iload_2        
        //   201: sipush          307
        //   204: if_icmpne       305
        //   207: aload           8
        //   209: ldc_w           "Location"
        //   212: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   217: checkcast       Ljava/lang/String;
        //   220: astore          9
        //   222: aload           9
        //   224: ifnull          262
        //   227: aload_1        
        //   228: aload           9
        //   230: invokevirtual   com/android/volley/Request.changeToRedirectedUrl:(Ljava/lang/String;)V
        //   233: new             Lorg/apache/http/client/RedirectException;
        //   236: dup            
        //   237: aload           9
        //   239: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   242: athrow         
        //   243: astore          7
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
        //   270: astore          7
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
        //   299: aload           7
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
        //   326: aload           8
        //   328: iconst_1       
        //   329: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   332: areturn        
        //   333: aload_1        
        //   334: instanceof      Lcom/android/volley/toolbox/ProgressiveRequest;
        //   337: ifeq            478
        //   340: new             Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   343: dup            
        //   344: iload_2        
        //   345: aload           7
        //   347: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   352: aload           8
        //   354: iconst_0       
        //   355: invokespecial   com/android/volley/toolbox/ProgressiveNetworkResponse.<init>:(ILorg/apache/http/HttpEntity;Ljava/util/Map;Z)V
        //   358: astore          10
        //   360: iconst_0       
        //   361: newarray        B
        //   363: astore          9
        //   365: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   368: lload_3        
        //   369: lsub           
        //   370: lstore          5
        //   372: aload_0        
        //   373: lload           5
        //   375: aload_1        
        //   376: aload           10
        //   378: aload           12
        //   380: invokespecial   com/android/volley/toolbox/BasicNetwork.logSlowRequests:(JLcom/android/volley/Request;Lcom/android/volley/NetworkResponse;Lorg/apache/http/StatusLine;)V
        //   383: aload_0        
        //   384: aload_1        
        //   385: aload           9
        //   387: arraylength    
        //   388: lload           5
        //   390: invokespecial   com/android/volley/toolbox/BasicNetwork.collectNetworkStats:(Lcom/android/volley/Request;IJ)V
        //   393: iload_2        
        //   394: sipush          200
        //   397: if_icmpeq       747
        //   400: iload_2        
        //   401: sipush          204
        //   404: if_icmpeq       747
        //   407: iload_2        
        //   408: sipush          206
        //   411: if_icmpeq       747
        //   414: iload_2        
        //   415: sipush          202
        //   418: if_icmpeq       747
        //   421: aload           10
        //   423: instanceof      Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   426: ifeq            446
        //   429: aload           10
        //   431: checkcast       Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   434: invokevirtual   com/android/volley/toolbox/ProgressiveNetworkResponse.getHttpEntity:()Lorg/apache/http/HttpEntity;
        //   437: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   442: aload_1        
        //   443: invokevirtual   com/android/volley/Request.releaseResources:()V
        //   446: new             Ljava/io/IOException;
        //   449: dup            
        //   450: invokespecial   java/io/IOException.<init>:()V
        //   453: athrow         
        //   454: astore          7
        //   456: ldc_w           "redirect"
        //   459: aload_1        
        //   460: new             Lcom/android/volley/VolleyError;
        //   463: dup            
        //   464: aload           7
        //   466: invokevirtual   org/apache/http/client/RedirectException.getMessage:()Ljava/lang/String;
        //   469: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;)V
        //   472: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   475: goto            4
        //   478: aload_0        
        //   479: aload           7
        //   481: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   486: aload_1        
        //   487: invokespecial   com/android/volley/toolbox/BasicNetwork.entityToBytes:(Lorg/apache/http/HttpEntity;Lcom/android/volley/Request;)[B
        //   490: astore          10
        //   492: new             Lcom/android/volley/NetworkResponse;
        //   495: dup            
        //   496: iload_2        
        //   497: aload           10
        //   499: aload           8
        //   501: iconst_0       
        //   502: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   505: astore          11
        //   507: aload           10
        //   509: astore          9
        //   511: aload           11
        //   513: astore          10
        //   515: goto            365
        //   518: astore          7
        //   520: aload           11
        //   522: astore          10
        //   524: ldc_w           "ioexception:"
        //   527: iconst_1       
        //   528: anewarray       Ljava/lang/Object;
        //   531: dup            
        //   532: iconst_0       
        //   533: aload           7
        //   535: aastore        
        //   536: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   539: aload           8
        //   541: ifnull          634
        //   544: aload           8
        //   546: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   551: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   556: istore_2       
        //   557: ldc_w           "Unexpected response code %d for %s"
        //   560: iconst_2       
        //   561: anewarray       Ljava/lang/Object;
        //   564: dup            
        //   565: iconst_0       
        //   566: iload_2        
        //   567: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   570: aastore        
        //   571: dup            
        //   572: iconst_1       
        //   573: aload_1        
        //   574: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   577: aastore        
        //   578: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   581: aload           10
        //   583: ifnull          654
        //   586: new             Lcom/android/volley/NetworkResponse;
        //   589: dup            
        //   590: iload_2        
        //   591: aload           10
        //   593: aload           9
        //   595: iconst_0       
        //   596: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   599: astore          7
        //   601: iload_2        
        //   602: sipush          401
        //   605: if_icmpeq       615
        //   608: iload_2        
        //   609: sipush          403
        //   612: if_icmpne       644
        //   615: ldc_w           "auth"
        //   618: aload_1        
        //   619: new             Lcom/android/volley/AuthFailureError;
        //   622: dup            
        //   623: aload           7
        //   625: invokespecial   com/android/volley/AuthFailureError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   628: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   631: goto            4
        //   634: new             Lcom/android/volley/NoConnectionError;
        //   637: dup            
        //   638: aload           7
        //   640: invokespecial   com/android/volley/NoConnectionError.<init>:(Ljava/lang/Throwable;)V
        //   643: athrow         
        //   644: new             Lcom/android/volley/ServerError;
        //   647: dup            
        //   648: aload           7
        //   650: invokespecial   com/android/volley/ServerError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   653: athrow         
        //   654: new             Lcom/android/volley/NetworkError;
        //   657: dup            
        //   658: aconst_null    
        //   659: invokespecial   com/android/volley/NetworkError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   662: athrow         
        //   663: astore          10
        //   665: aload           7
        //   667: astore          8
        //   669: aload           10
        //   671: astore          7
        //   673: aload           11
        //   675: astore          10
        //   677: goto            524
        //   680: astore          10
        //   682: aload           8
        //   684: astore          9
        //   686: aload           7
        //   688: astore          8
        //   690: aload           10
        //   692: astore          7
        //   694: aload           11
        //   696: astore          10
        //   698: goto            524
        //   701: astore          9
        //   703: aload           7
        //   705: astore          11
        //   707: aload           9
        //   709: astore          7
        //   711: aload           8
        //   713: astore          9
        //   715: aload           11
        //   717: astore          8
        //   719: goto            524
        //   722: astore          12
        //   724: aload           9
        //   726: astore          10
        //   728: aload           7
        //   730: astore          11
        //   732: aload           12
        //   734: astore          7
        //   736: aload           8
        //   738: astore          9
        //   740: aload           11
        //   742: astore          8
        //   744: goto            524
        //   747: aload           10
        //   749: areturn        
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
        //  19     52     454    478    Lorg/apache/http/client/RedirectException;
        //  19     52     518    524    Ljava/io/IOException;
        //  52     86     105    124    Ljava/net/HttpRetryException;
        //  52     86     155    174    Ljava/net/SocketTimeoutException;
        //  52     86     243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  52     86     270    305    Ljava/net/MalformedURLException;
        //  52     86     454    478    Lorg/apache/http/client/RedirectException;
        //  52     86     663    680    Ljava/io/IOException;
        //  93     105    105    124    Ljava/net/HttpRetryException;
        //  93     105    155    174    Ljava/net/SocketTimeoutException;
        //  93     105    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  93     105    270    305    Ljava/net/MalformedURLException;
        //  93     105    454    478    Lorg/apache/http/client/RedirectException;
        //  93     105    663    680    Ljava/io/IOException;
        //  131    155    105    124    Ljava/net/HttpRetryException;
        //  131    155    155    174    Ljava/net/SocketTimeoutException;
        //  131    155    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  131    155    270    305    Ljava/net/MalformedURLException;
        //  131    155    454    478    Lorg/apache/http/client/RedirectException;
        //  131    155    663    680    Ljava/io/IOException;
        //  174    186    105    124    Ljava/net/HttpRetryException;
        //  174    186    155    174    Ljava/net/SocketTimeoutException;
        //  174    186    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  174    186    270    305    Ljava/net/MalformedURLException;
        //  174    186    454    478    Lorg/apache/http/client/RedirectException;
        //  174    186    663    680    Ljava/io/IOException;
        //  207    222    105    124    Ljava/net/HttpRetryException;
        //  207    222    155    174    Ljava/net/SocketTimeoutException;
        //  207    222    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  207    222    270    305    Ljava/net/MalformedURLException;
        //  207    222    454    478    Lorg/apache/http/client/RedirectException;
        //  207    222    680    701    Ljava/io/IOException;
        //  227    243    105    124    Ljava/net/HttpRetryException;
        //  227    243    155    174    Ljava/net/SocketTimeoutException;
        //  227    243    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  227    243    270    305    Ljava/net/MalformedURLException;
        //  227    243    454    478    Lorg/apache/http/client/RedirectException;
        //  227    243    680    701    Ljava/io/IOException;
        //  262    270    105    124    Ljava/net/HttpRetryException;
        //  262    270    155    174    Ljava/net/SocketTimeoutException;
        //  262    270    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  262    270    270    305    Ljava/net/MalformedURLException;
        //  262    270    454    478    Lorg/apache/http/client/RedirectException;
        //  262    270    680    701    Ljava/io/IOException;
        //  312    333    105    124    Ljava/net/HttpRetryException;
        //  312    333    155    174    Ljava/net/SocketTimeoutException;
        //  312    333    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  312    333    270    305    Ljava/net/MalformedURLException;
        //  312    333    454    478    Lorg/apache/http/client/RedirectException;
        //  312    333    680    701    Ljava/io/IOException;
        //  333    365    105    124    Ljava/net/HttpRetryException;
        //  333    365    155    174    Ljava/net/SocketTimeoutException;
        //  333    365    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  333    365    270    305    Ljava/net/MalformedURLException;
        //  333    365    454    478    Lorg/apache/http/client/RedirectException;
        //  333    365    680    701    Ljava/io/IOException;
        //  365    393    105    124    Ljava/net/HttpRetryException;
        //  365    393    155    174    Ljava/net/SocketTimeoutException;
        //  365    393    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  365    393    270    305    Ljava/net/MalformedURLException;
        //  365    393    454    478    Lorg/apache/http/client/RedirectException;
        //  365    393    722    747    Ljava/io/IOException;
        //  421    446    105    124    Ljava/net/HttpRetryException;
        //  421    446    155    174    Ljava/net/SocketTimeoutException;
        //  421    446    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  421    446    270    305    Ljava/net/MalformedURLException;
        //  421    446    454    478    Lorg/apache/http/client/RedirectException;
        //  421    446    722    747    Ljava/io/IOException;
        //  446    454    105    124    Ljava/net/HttpRetryException;
        //  446    454    155    174    Ljava/net/SocketTimeoutException;
        //  446    454    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  446    454    270    305    Ljava/net/MalformedURLException;
        //  446    454    454    478    Lorg/apache/http/client/RedirectException;
        //  446    454    722    747    Ljava/io/IOException;
        //  478    492    105    124    Ljava/net/HttpRetryException;
        //  478    492    155    174    Ljava/net/SocketTimeoutException;
        //  478    492    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  478    492    270    305    Ljava/net/MalformedURLException;
        //  478    492    454    478    Lorg/apache/http/client/RedirectException;
        //  478    492    680    701    Ljava/io/IOException;
        //  492    507    105    124    Ljava/net/HttpRetryException;
        //  492    507    155    174    Ljava/net/SocketTimeoutException;
        //  492    507    243    262    Lorg/apache/http/conn/ConnectTimeoutException;
        //  492    507    270    305    Ljava/net/MalformedURLException;
        //  492    507    454    478    Lorg/apache/http/client/RedirectException;
        //  492    507    701    722    Ljava/io/IOException;
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
