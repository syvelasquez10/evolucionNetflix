// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import org.apache.http.client.RedirectException;
import java.net.MalformedURLException;
import org.apache.http.conn.ConnectTimeoutException;
import java.net.SocketTimeoutException;
import com.android.volley.TimeoutError;
import java.net.HttpRetryException;
import com.android.volley.NetworkResponse;
import android.os.SystemClock;
import org.apache.http.HttpResponse;
import java.io.Serializable;
import org.apache.http.StatusLine;
import com.android.volley.ServerError;
import java.io.IOException;
import org.apache.http.HttpEntity;
import java.util.HashMap;
import org.apache.http.Header;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Date;
import com.android.volley.Cache;
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
    
    private void addCacheHeaders(final Map<String, String> map, final Cache.Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.serverDate > 0L) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
            }
        }
    }
    
    private static void attemptRetryOnException(final String s, final Request<?> request, final VolleyError volleyError) throws VolleyError {
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
    
    private byte[] entityToBytes(final HttpEntity p0) throws IOException, ServerError {
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
        //  throws java.io.IOException
        //  throws com.android.volley.ServerError
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
    
    protected void logError(final String s, final String s2, final long n) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", s, SystemClock.elapsedRealtime() - n, s2);
    }
    
    @Override
    public NetworkResponse performRequest(final Request<?> request) throws VolleyError {
        SystemClock.elapsedRealtime();
        while (true) {
            final HttpResponse httpResponse = null;
            final byte[] array = null;
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            byte[] array2 = array;
            HttpResponse httpResponse2 = httpResponse;
            HashMap<String, String> hashMap2 = hashMap;
            try {
                final HashMap<String, String> hashMap3 = new HashMap<String, String>();
                array2 = array;
                httpResponse2 = httpResponse;
                hashMap2 = hashMap;
                this.addCacheHeaders(hashMap3, request.getCacheEntry());
                array2 = array;
                httpResponse2 = httpResponse;
                hashMap2 = hashMap;
                final HttpResponse performRequest = this.mHttpStack.performRequest(request, hashMap3);
                array2 = array;
                httpResponse2 = performRequest;
                hashMap2 = hashMap;
                final StatusLine statusLine = performRequest.getStatusLine();
                array2 = array;
                httpResponse2 = performRequest;
                hashMap2 = hashMap;
                final int statusCode = statusLine.getStatusCode();
                array2 = array;
                httpResponse2 = performRequest;
                hashMap2 = hashMap;
                VolleyLog.d("Http status: %d", statusCode);
                if (statusCode == 500) {
                    array2 = array;
                    httpResponse2 = performRequest;
                    hashMap2 = hashMap;
                    throw new HttpRetryException("retry", statusCode);
                }
                goto Label_0208;
            }
            catch (HttpRetryException ex4) {
                attemptRetryOnException("Http500", request, new TimeoutError());
            }
            catch (SocketTimeoutException ex5) {
                attemptRetryOnException("socket", request, new TimeoutError());
            }
            catch (ConnectTimeoutException ex6) {
                attemptRetryOnException("connection", request, new TimeoutError());
            }
            catch (MalformedURLException ex) {
                throw new RuntimeException("Bad URL " + request.getUrl(), ex);
            }
            catch (RedirectException ex2) {
                attemptRetryOnException("redirect", request, new VolleyError(ex2.getMessage()));
            }
            catch (IOException ex3) {
                if (httpResponse2 == null) {
                    throw new NoConnectionError(ex3);
                }
                final int statusCode2 = httpResponse2.getStatusLine().getStatusCode();
                VolleyLog.e("Unexpected response code %d for %s", statusCode2, request.getUrl());
                if (array2 == null) {
                    throw new NetworkError((NetworkResponse)null);
                }
                final NetworkResponse networkResponse = new NetworkResponse(statusCode2, array2, hashMap2, false);
                if (statusCode2 != 401 && statusCode2 != 403) {
                    throw new ServerError(networkResponse);
                }
                attemptRetryOnException("auth", request, new AuthFailureError(networkResponse));
            }
        }
    }
}
