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
        //     4: new             Ljava/util/HashMap;
        //     7: dup            
        //     8: invokespecial   java/util/HashMap.<init>:()V
        //    11: astore          9
        //    13: new             Ljava/util/HashMap;
        //    16: dup            
        //    17: invokespecial   java/util/HashMap.<init>:()V
        //    20: astore          7
        //    22: aload_0        
        //    23: aload           7
        //    25: aload_1        
        //    26: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //    29: invokespecial   com/android/volley/toolbox/BasicNetwork.addCacheHeaders:(Ljava/util/Map;Lcom/android/volley/Cache$Entry;)V
        //    32: aload_0        
        //    33: getfield        com/android/volley/toolbox/BasicNetwork.mHttpStack:Lcom/android/volley/toolbox/HttpStack;
        //    36: aload_1        
        //    37: aload           7
        //    39: invokeinterface com/android/volley/toolbox/HttpStack.performRequest:(Lcom/android/volley/Request;Ljava/util/Map;)Lorg/apache/http/HttpResponse;
        //    44: astore          7
        //    46: aload           7
        //    48: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    53: astore          11
        //    55: aload           11
        //    57: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //    62: istore_2       
        //    63: ldc_w           "Http status: %d"
        //    66: iconst_1       
        //    67: anewarray       Ljava/lang/Object;
        //    70: dup            
        //    71: iconst_0       
        //    72: iload_2        
        //    73: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    76: aastore        
        //    77: invokestatic    com/android/volley/VolleyLog.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    80: iload_2        
        //    81: sipush          500
        //    84: if_icmpne       118
        //    87: new             Ljava/net/HttpRetryException;
        //    90: dup            
        //    91: ldc_w           "retry"
        //    94: iload_2        
        //    95: invokespecial   java/net/HttpRetryException.<init>:(Ljava/lang/String;I)V
        //    98: athrow         
        //    99: astore          7
        //   101: ldc_w           "Http500"
        //   104: aload_1        
        //   105: new             Lcom/android/volley/TimeoutError;
        //   108: dup            
        //   109: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   112: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   115: goto            4
        //   118: iload_2        
        //   119: sipush          410
        //   122: if_icmpne       168
        //   125: aload_0        
        //   126: aload           7
        //   128: invokevirtual   com/android/volley/toolbox/BasicNetwork.getRedirectedHost:(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
        //   131: astore          8
        //   133: aload_1        
        //   134: aload           8
        //   136: invokevirtual   com/android/volley/Request.changeHostUrl:(Ljava/lang/String;)V
        //   139: new             Lorg/apache/http/client/RedirectException;
        //   142: dup            
        //   143: aload           8
        //   145: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   148: athrow         
        //   149: astore          7
        //   151: ldc_w           "socket"
        //   154: aload_1        
        //   155: new             Lcom/android/volley/TimeoutError;
        //   158: dup            
        //   159: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   162: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   165: goto            4
        //   168: aload           7
        //   170: invokeinterface org/apache/http/HttpResponse.getAllHeaders:()[Lorg/apache/http/Header;
        //   175: invokestatic    com/android/volley/toolbox/BasicNetwork.convertHeaders:([Lorg/apache/http/Header;)Ljava/util/Map;
        //   178: astore          8
        //   180: iload_2        
        //   181: sipush          301
        //   184: if_icmpeq       201
        //   187: iload_2        
        //   188: sipush          302
        //   191: if_icmpeq       201
        //   194: iload_2        
        //   195: sipush          307
        //   198: if_icmpne       299
        //   201: aload           8
        //   203: ldc_w           "Location"
        //   206: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   211: checkcast       Ljava/lang/String;
        //   214: astore          9
        //   216: aload           9
        //   218: ifnull          256
        //   221: aload_1        
        //   222: aload           9
        //   224: invokevirtual   com/android/volley/Request.changeToRedirectedUrl:(Ljava/lang/String;)V
        //   227: new             Lorg/apache/http/client/RedirectException;
        //   230: dup            
        //   231: aload           9
        //   233: invokespecial   org/apache/http/client/RedirectException.<init>:(Ljava/lang/String;)V
        //   236: athrow         
        //   237: astore          7
        //   239: ldc_w           "connection"
        //   242: aload_1        
        //   243: new             Lcom/android/volley/TimeoutError;
        //   246: dup            
        //   247: invokespecial   com/android/volley/TimeoutError.<init>:()V
        //   250: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   253: goto            4
        //   256: new             Ljava/io/IOException;
        //   259: dup            
        //   260: invokespecial   java/io/IOException.<init>:()V
        //   263: athrow         
        //   264: astore          7
        //   266: new             Ljava/lang/RuntimeException;
        //   269: dup            
        //   270: new             Ljava/lang/StringBuilder;
        //   273: dup            
        //   274: invokespecial   java/lang/StringBuilder.<init>:()V
        //   277: ldc_w           "Bad URL "
        //   280: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: aload_1        
        //   284: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   290: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   293: aload           7
        //   295: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   298: athrow         
        //   299: iload_2        
        //   300: sipush          304
        //   303: if_icmpne       327
        //   306: new             Lcom/android/volley/NetworkResponse;
        //   309: dup            
        //   310: sipush          304
        //   313: aload_1        
        //   314: invokevirtual   com/android/volley/Request.getCacheEntry:()Lcom/android/volley/Cache$Entry;
        //   317: getfield        com/android/volley/Cache$Entry.data:[B
        //   320: aload           8
        //   322: iconst_1       
        //   323: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   326: areturn        
        //   327: aload_1        
        //   328: instanceof      Lcom/android/volley/toolbox/ProgressiveRequest;
        //   331: ifeq            472
        //   334: new             Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   337: dup            
        //   338: iload_2        
        //   339: aload           7
        //   341: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   346: aload           8
        //   348: iconst_0       
        //   349: invokespecial   com/android/volley/toolbox/ProgressiveNetworkResponse.<init>:(ILorg/apache/http/HttpEntity;Ljava/util/Map;Z)V
        //   352: astore          10
        //   354: iconst_0       
        //   355: newarray        B
        //   357: astore          9
        //   359: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   362: lload_3        
        //   363: lsub           
        //   364: lstore          5
        //   366: aload_0        
        //   367: lload           5
        //   369: aload_1        
        //   370: aload           10
        //   372: aload           11
        //   374: invokespecial   com/android/volley/toolbox/BasicNetwork.logSlowRequests:(JLcom/android/volley/Request;Lcom/android/volley/NetworkResponse;Lorg/apache/http/StatusLine;)V
        //   377: aload_0        
        //   378: aload_1        
        //   379: aload           9
        //   381: arraylength    
        //   382: lload           5
        //   384: invokespecial   com/android/volley/toolbox/BasicNetwork.collectNetworkStats:(Lcom/android/volley/Request;IJ)V
        //   387: iload_2        
        //   388: sipush          200
        //   391: if_icmpeq       748
        //   394: iload_2        
        //   395: sipush          204
        //   398: if_icmpeq       748
        //   401: iload_2        
        //   402: sipush          206
        //   405: if_icmpeq       748
        //   408: iload_2        
        //   409: sipush          202
        //   412: if_icmpeq       748
        //   415: aload           10
        //   417: instanceof      Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   420: ifeq            440
        //   423: aload           10
        //   425: checkcast       Lcom/android/volley/toolbox/ProgressiveNetworkResponse;
        //   428: invokevirtual   com/android/volley/toolbox/ProgressiveNetworkResponse.getHttpEntity:()Lorg/apache/http/HttpEntity;
        //   431: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   436: aload_1        
        //   437: invokevirtual   com/android/volley/Request.releaseResources:()V
        //   440: new             Ljava/io/IOException;
        //   443: dup            
        //   444: invokespecial   java/io/IOException.<init>:()V
        //   447: athrow         
        //   448: astore          7
        //   450: ldc_w           "redirect"
        //   453: aload_1        
        //   454: new             Lcom/android/volley/VolleyError;
        //   457: dup            
        //   458: aload           7
        //   460: invokevirtual   org/apache/http/client/RedirectException.getMessage:()Ljava/lang/String;
        //   463: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;)V
        //   466: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   469: goto            4
        //   472: aload_0        
        //   473: aload           7
        //   475: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   480: aload_1        
        //   481: invokespecial   com/android/volley/toolbox/BasicNetwork.entityToBytes:(Lorg/apache/http/HttpEntity;Lcom/android/volley/Request;)[B
        //   484: astore          9
        //   486: new             Lcom/android/volley/NetworkResponse;
        //   489: dup            
        //   490: iload_2        
        //   491: aload           9
        //   493: aload           8
        //   495: iconst_0       
        //   496: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   499: astore          10
        //   501: goto            359
        //   504: astore          7
        //   506: aconst_null    
        //   507: astore          10
        //   509: aconst_null    
        //   510: astore          11
        //   512: aload           9
        //   514: astore          8
        //   516: aload           11
        //   518: astore          9
        //   520: ldc_w           "ioexception:"
        //   523: iconst_1       
        //   524: anewarray       Ljava/lang/Object;
        //   527: dup            
        //   528: iconst_0       
        //   529: aload           7
        //   531: aastore        
        //   532: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   535: iconst_0       
        //   536: istore_2       
        //   537: aload           10
        //   539: ifnull          555
        //   542: aload           10
        //   544: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   549: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   554: istore_2       
        //   555: ldc_w           "Unexpected response code %d for %s"
        //   558: iconst_2       
        //   559: anewarray       Ljava/lang/Object;
        //   562: dup            
        //   563: iconst_0       
        //   564: iload_2        
        //   565: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   568: aastore        
        //   569: dup            
        //   570: iconst_1       
        //   571: aload_1        
        //   572: invokevirtual   com/android/volley/Request.getUrl:()Ljava/lang/String;
        //   575: aastore        
        //   576: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   579: aload           9
        //   581: ifnull          642
        //   584: new             Lcom/android/volley/NetworkResponse;
        //   587: dup            
        //   588: iload_2        
        //   589: aload           9
        //   591: aload           8
        //   593: iconst_0       
        //   594: invokespecial   com/android/volley/NetworkResponse.<init>:(I[BLjava/util/Map;Z)V
        //   597: astore          7
        //   599: iload_2        
        //   600: sipush          401
        //   603: if_icmpeq       613
        //   606: iload_2        
        //   607: sipush          403
        //   610: if_icmpne       632
        //   613: ldc_w           "auth"
        //   616: aload_1        
        //   617: new             Lcom/android/volley/AuthFailureError;
        //   620: dup            
        //   621: aload           7
        //   623: invokespecial   com/android/volley/AuthFailureError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   626: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   629: goto            4
        //   632: new             Lcom/android/volley/ServerError;
        //   635: dup            
        //   636: aload           7
        //   638: invokespecial   com/android/volley/ServerError.<init>:(Lcom/android/volley/NetworkResponse;)V
        //   641: athrow         
        //   642: ldc_w           "retrying"
        //   645: iconst_0       
        //   646: anewarray       Ljava/lang/Object;
        //   649: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   652: ldc_w           "IOException"
        //   655: aload_1        
        //   656: new             Lcom/android/volley/VolleyError;
        //   659: dup            
        //   660: aload           7
        //   662: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   665: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;)V
        //   668: invokestatic    com/android/volley/toolbox/BasicNetwork.attemptRetryOnException:(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
        //   671: goto            4
        //   674: astore          12
        //   676: aconst_null    
        //   677: astore          11
        //   679: aload           7
        //   681: astore          10
        //   683: aload           9
        //   685: astore          8
        //   687: aload           12
        //   689: astore          7
        //   691: aload           11
        //   693: astore          9
        //   695: goto            520
        //   698: astore          9
        //   700: aconst_null    
        //   701: astore          11
        //   703: aload           7
        //   705: astore          10
        //   707: aload           9
        //   709: astore          7
        //   711: aload           11
        //   713: astore          9
        //   715: goto            520
        //   718: astore          11
        //   720: aload           7
        //   722: astore          10
        //   724: aload           11
        //   726: astore          7
        //   728: goto            520
        //   731: astore          10
        //   733: aload           7
        //   735: astore          11
        //   737: aload           10
        //   739: astore          7
        //   741: aload           11
        //   743: astore          10
        //   745: goto            520
        //   748: aload           10
        //   750: areturn        
        //    Signature:
        //  (Lcom/android/volley/Request<*>;)Lcom/android/volley/NetworkResponse;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                          
        //  -----  -----  -----  -----  ----------------------------------------------
        //  13     46     99     118    Ljava/net/HttpRetryException;
        //  13     46     149    168    Ljava/net/SocketTimeoutException;
        //  13     46     237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  13     46     264    299    Ljava/net/MalformedURLException;
        //  13     46     448    472    Lorg/apache/http/client/RedirectException;
        //  13     46     504    520    Ljava/io/IOException;
        //  46     80     99     118    Ljava/net/HttpRetryException;
        //  46     80     149    168    Ljava/net/SocketTimeoutException;
        //  46     80     237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  46     80     264    299    Ljava/net/MalformedURLException;
        //  46     80     448    472    Lorg/apache/http/client/RedirectException;
        //  46     80     674    698    Ljava/io/IOException;
        //  87     99     99     118    Ljava/net/HttpRetryException;
        //  87     99     149    168    Ljava/net/SocketTimeoutException;
        //  87     99     237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  87     99     264    299    Ljava/net/MalformedURLException;
        //  87     99     448    472    Lorg/apache/http/client/RedirectException;
        //  87     99     674    698    Ljava/io/IOException;
        //  125    149    99     118    Ljava/net/HttpRetryException;
        //  125    149    149    168    Ljava/net/SocketTimeoutException;
        //  125    149    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  125    149    264    299    Ljava/net/MalformedURLException;
        //  125    149    448    472    Lorg/apache/http/client/RedirectException;
        //  125    149    674    698    Ljava/io/IOException;
        //  168    180    99     118    Ljava/net/HttpRetryException;
        //  168    180    149    168    Ljava/net/SocketTimeoutException;
        //  168    180    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  168    180    264    299    Ljava/net/MalformedURLException;
        //  168    180    448    472    Lorg/apache/http/client/RedirectException;
        //  168    180    674    698    Ljava/io/IOException;
        //  201    216    99     118    Ljava/net/HttpRetryException;
        //  201    216    149    168    Ljava/net/SocketTimeoutException;
        //  201    216    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  201    216    264    299    Ljava/net/MalformedURLException;
        //  201    216    448    472    Lorg/apache/http/client/RedirectException;
        //  201    216    698    718    Ljava/io/IOException;
        //  221    237    99     118    Ljava/net/HttpRetryException;
        //  221    237    149    168    Ljava/net/SocketTimeoutException;
        //  221    237    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  221    237    264    299    Ljava/net/MalformedURLException;
        //  221    237    448    472    Lorg/apache/http/client/RedirectException;
        //  221    237    698    718    Ljava/io/IOException;
        //  256    264    99     118    Ljava/net/HttpRetryException;
        //  256    264    149    168    Ljava/net/SocketTimeoutException;
        //  256    264    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  256    264    264    299    Ljava/net/MalformedURLException;
        //  256    264    448    472    Lorg/apache/http/client/RedirectException;
        //  256    264    698    718    Ljava/io/IOException;
        //  306    327    99     118    Ljava/net/HttpRetryException;
        //  306    327    149    168    Ljava/net/SocketTimeoutException;
        //  306    327    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  306    327    264    299    Ljava/net/MalformedURLException;
        //  306    327    448    472    Lorg/apache/http/client/RedirectException;
        //  306    327    698    718    Ljava/io/IOException;
        //  327    359    99     118    Ljava/net/HttpRetryException;
        //  327    359    149    168    Ljava/net/SocketTimeoutException;
        //  327    359    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  327    359    264    299    Ljava/net/MalformedURLException;
        //  327    359    448    472    Lorg/apache/http/client/RedirectException;
        //  327    359    698    718    Ljava/io/IOException;
        //  359    387    99     118    Ljava/net/HttpRetryException;
        //  359    387    149    168    Ljava/net/SocketTimeoutException;
        //  359    387    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  359    387    264    299    Ljava/net/MalformedURLException;
        //  359    387    448    472    Lorg/apache/http/client/RedirectException;
        //  359    387    731    748    Ljava/io/IOException;
        //  415    440    99     118    Ljava/net/HttpRetryException;
        //  415    440    149    168    Ljava/net/SocketTimeoutException;
        //  415    440    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  415    440    264    299    Ljava/net/MalformedURLException;
        //  415    440    448    472    Lorg/apache/http/client/RedirectException;
        //  415    440    731    748    Ljava/io/IOException;
        //  440    448    99     118    Ljava/net/HttpRetryException;
        //  440    448    149    168    Ljava/net/SocketTimeoutException;
        //  440    448    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  440    448    264    299    Ljava/net/MalformedURLException;
        //  440    448    448    472    Lorg/apache/http/client/RedirectException;
        //  440    448    731    748    Ljava/io/IOException;
        //  472    486    99     118    Ljava/net/HttpRetryException;
        //  472    486    149    168    Ljava/net/SocketTimeoutException;
        //  472    486    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  472    486    264    299    Ljava/net/MalformedURLException;
        //  472    486    448    472    Lorg/apache/http/client/RedirectException;
        //  472    486    698    718    Ljava/io/IOException;
        //  486    501    99     118    Ljava/net/HttpRetryException;
        //  486    501    149    168    Ljava/net/SocketTimeoutException;
        //  486    501    237    256    Lorg/apache/http/conn/ConnectTimeoutException;
        //  486    501    264    299    Ljava/net/MalformedURLException;
        //  486    501    448    472    Lorg/apache/http/client/RedirectException;
        //  486    501    718    731    Ljava/io/IOException;
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
