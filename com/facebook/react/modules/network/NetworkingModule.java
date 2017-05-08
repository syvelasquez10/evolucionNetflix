// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okio.ByteString;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Request$Builder;
import okhttp3.CookieJar;
import java.net.CookieHandler;
import okhttp3.JavaNetCookieJar;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import okhttp3.Headers$Builder;
import java.io.InputStream;
import android.content.Context;
import okhttp3.RequestBody;
import java.io.IOException;
import com.facebook.react.bridge.ReadableMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody$Builder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ExecutorToken;
import okhttp3.ResponseBody;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.WritableMap;
import okhttp3.Headers;
import java.util.Iterator;
import okhttp3.OkHttpClient$Builder;
import java.util.HashSet;
import com.facebook.react.bridge.ReactContext;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Set;
import okhttp3.OkHttpClient;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public final class NetworkingModule extends ReactContextBaseJavaModule
{
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    protected static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    private final String mDefaultUserAgent;
    private final Set<Integer> mRequestIds;
    private boolean mShuttingDown;
    
    public NetworkingModule(final ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, OkHttpClientProvider.getOkHttpClient(), null);
    }
    
    public NetworkingModule(final ReactApplicationContext reactApplicationContext, final String s) {
        this(reactApplicationContext, s, OkHttpClientProvider.getOkHttpClient(), null);
    }
    
    NetworkingModule(final ReactApplicationContext reactApplicationContext, final String s, final OkHttpClient okHttpClient) {
        this(reactApplicationContext, s, okHttpClient, null);
    }
    
    NetworkingModule(final ReactApplicationContext reactApplicationContext, final String mDefaultUserAgent, final OkHttpClient okHttpClient, final List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        OkHttpClient build = okHttpClient;
        if (list != null) {
            final OkHttpClient$Builder builder = okHttpClient.newBuilder();
            final Iterator<NetworkInterceptorCreator> iterator = list.iterator();
            while (iterator.hasNext()) {
                builder.addNetworkInterceptor(iterator.next().create());
            }
            build = builder.build();
        }
        OkHttpClientProvider.replaceOkHttpClient(this.mClient = build);
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
        this.mCookieJarContainer = (CookieJarContainer)this.mClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = mDefaultUserAgent;
        this.mRequestIds = new HashSet<Integer>();
    }
    
    public NetworkingModule(final ReactApplicationContext reactApplicationContext, final List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, null, OkHttpClientProvider.getOkHttpClient(), list);
    }
    
    private void addRequest(final int n) {
        synchronized (this) {
            this.mRequestIds.add(n);
        }
    }
    
    private void cancelAllRequests() {
        synchronized (this) {
            final Iterator<Integer> iterator = this.mRequestIds.iterator();
            while (iterator.hasNext()) {
                this.cancelRequest(iterator.next());
            }
        }
        this.mRequestIds.clear();
    }
    // monitorexit(this)
    
    private void cancelRequest(final int n) {
        new NetworkingModule$4(this, this.getReactApplicationContext(), n).execute((Object[])new Void[0]);
    }
    
    private MultipartBody$Builder constructMultipartBody(final ExecutorToken executorToken, final ReadableArray readableArray, final String s, final int n) {
        final DeviceEventManagerModule$RCTDeviceEventEmitter eventEmitter = this.getEventEmitter(executorToken);
        final MultipartBody$Builder multipartBody$Builder = new MultipartBody$Builder();
        multipartBody$Builder.setType(MediaType.parse(s));
        for (int size = readableArray.size(), i = 0; i < size; ++i) {
            final ReadableMap map = readableArray.getMap(i);
            Headers headers = this.extractHeaders(map.getArray("headers"), null);
            if (headers == null) {
                ResponseUtil.onRequestError(eventEmitter, n, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            final String value = headers.get("content-type");
            MediaType parse;
            if (value != null) {
                parse = MediaType.parse(value);
                headers = headers.newBuilder().removeAll("content-type").build();
            }
            else {
                parse = null;
            }
            if (map.hasKey("string")) {
                multipartBody$Builder.addPart(headers, RequestBody.create(parse, map.getString("string")));
            }
            else if (map.hasKey("uri")) {
                if (parse == null) {
                    ResponseUtil.onRequestError(eventEmitter, n, "Binary FormData part needs a content-type header.", null);
                    return null;
                }
                final String string = map.getString("uri");
                final InputStream fileInputStream = RequestBodyUtil.getFileInputStream((Context)this.getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, n, "Could not retrieve file for uri " + string, null);
                    return null;
                }
                multipartBody$Builder.addPart(headers, RequestBodyUtil.create(parse, fileInputStream));
            }
            else {
                ResponseUtil.onRequestError(eventEmitter, n, "Unrecognized FormData part.", null);
            }
        }
        return multipartBody$Builder;
    }
    
    private Headers extractHeaders(final ReadableArray readableArray, final ReadableMap readableMap) {
        if (readableArray != null) {
            final Headers$Builder headers$Builder = new Headers$Builder();
            for (int size = readableArray.size(), i = 0; i < size; ++i) {
                final ReadableArray array = readableArray.getArray(i);
                if (array == null || array.size() != 2) {
                    return null;
                }
                final String string = array.getString(0);
                final String string2 = array.getString(1);
                if (string == null || string2 == null) {
                    return null;
                }
                headers$Builder.add(string, string2);
            }
            if (headers$Builder.get("user-agent") == null && this.mDefaultUserAgent != null) {
                headers$Builder.add("user-agent", this.mDefaultUserAgent);
            }
            int n;
            if (readableMap != null && readableMap.hasKey("string")) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n == 0) {
                headers$Builder.removeAll("content-encoding");
            }
            return headers$Builder.build();
        }
        return null;
    }
    
    private DeviceEventManagerModule$RCTDeviceEventEmitter getEventEmitter(final ExecutorToken executorToken) {
        return this.getReactApplicationContext().getJSModule(executorToken, DeviceEventManagerModule$RCTDeviceEventEmitter.class);
    }
    
    private void readWithProgress(final DeviceEventManagerModule$RCTDeviceEventEmitter p0, final int p1, final ResponseBody p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc2_w          -1
        //     3: lstore          7
        //     5: aload_3        
        //     6: checkcast       Lcom/facebook/react/modules/network/ProgressResponseBody;
        //     9: astore          11
        //    11: aload           11
        //    13: invokevirtual   com/facebook/react/modules/network/ProgressResponseBody.totalBytesRead:()J
        //    16: lstore          5
        //    18: aload           11
        //    20: invokevirtual   com/facebook/react/modules/network/ProgressResponseBody.contentLength:()J
        //    23: lstore          9
        //    25: lload           9
        //    27: lstore          7
        //    29: aload_3        
        //    30: invokevirtual   okhttp3/ResponseBody.charStream:()Ljava/io/Reader;
        //    33: astore_3       
        //    34: sipush          8192
        //    37: newarray        C
        //    39: astore          11
        //    41: aload_3        
        //    42: aload           11
        //    44: invokevirtual   java/io/Reader.read:([C)I
        //    47: istore          4
        //    49: iload           4
        //    51: iconst_m1      
        //    52: if_icmpeq       86
        //    55: aload_1        
        //    56: iload_2        
        //    57: new             Ljava/lang/String;
        //    60: dup            
        //    61: aload           11
        //    63: iconst_0       
        //    64: iload           4
        //    66: invokespecial   java/lang/String.<init>:([CII)V
        //    69: lload           5
        //    71: lload           7
        //    73: invokestatic    com/facebook/react/modules/network/ResponseUtil.onIncrementalDataReceived:(Lcom/facebook/react/modules/core/DeviceEventManagerModule$RCTDeviceEventEmitter;ILjava/lang/String;JJ)V
        //    76: goto            41
        //    79: astore_1       
        //    80: aload_3        
        //    81: invokevirtual   java/io/Reader.close:()V
        //    84: aload_1        
        //    85: athrow         
        //    86: aload_3        
        //    87: invokevirtual   java/io/Reader.close:()V
        //    90: return         
        //    91: astore          11
        //    93: ldc2_w          -1
        //    96: lstore          5
        //    98: goto            29
        //   101: astore          11
        //   103: goto            29
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                          
        //  -----  -----  -----  -----  ------------------------------
        //  5      18     91     101    Ljava/lang/ClassCastException;
        //  18     25     101    106    Ljava/lang/ClassCastException;
        //  34     41     79     86     Any
        //  41     49     79     86     Any
        //  55     76     79     86     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    
    private void removeRequest(final int n) {
        synchronized (this) {
            this.mRequestIds.remove(n);
        }
    }
    
    private static boolean shouldDispatch(final long n, final long n2) {
        return 100000000L + n2 < n;
    }
    
    private static WritableMap translateHeaders(final Headers headers) {
        final WritableMap map = Arguments.createMap();
        for (int i = 0; i < headers.size(); ++i) {
            final String name = headers.name(i);
            if (map.hasKey(name)) {
                map.putString(name, map.getString(name) + ", " + headers.value(i));
            }
            else {
                map.putString(name, headers.value(i));
            }
        }
        return map;
    }
    
    @ReactMethod
    public void abortRequest(final ExecutorToken executorToken, final int n) {
        this.cancelRequest(n);
        this.removeRequest(n);
    }
    
    @ReactMethod
    public void clearCookies(final ExecutorToken executorToken, final Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }
    
    @Override
    public String getName() {
        return "Networking";
    }
    
    @Override
    public void initialize() {
        this.mCookieJarContainer.setCookieJar((CookieJar)new JavaNetCookieJar((CookieHandler)this.mCookieHandler));
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        this.cancelAllRequests();
        this.mCookieHandler.destroy();
        this.mCookieJarContainer.removeCookieJar();
    }
    
    @ReactMethod
    public void sendRequest(final ExecutorToken executorToken, final String s, String value, final int n, final ReadableArray readableArray, final ReadableMap readableMap, final String s2, final boolean b, final int n2) {
        final Request$Builder url = new Request$Builder().url(value);
        if (n != 0) {
            url.tag((Object)n);
        }
        final DeviceEventManagerModule$RCTDeviceEventEmitter eventEmitter = this.getEventEmitter(executorToken);
        final OkHttpClient$Builder builder = this.mClient.newBuilder();
        if (b) {
            builder.addNetworkInterceptor((Interceptor)new NetworkingModule$1(this, s2, eventEmitter, n));
        }
        if (n2 != this.mClient.connectTimeoutMillis()) {
            builder.readTimeout((long)n2, TimeUnit.MILLISECONDS);
        }
        final OkHttpClient build = builder.build();
        final Headers headers = this.extractHeaders(readableArray, readableMap);
        if (headers != null) {
            final String value2 = headers.get("content-type");
            value = headers.get("content-encoding");
            url.headers(headers);
            if (readableMap == null) {
                url.method(s, RequestBodyUtil.getEmptyBody(s));
            }
            else if (readableMap.hasKey("string")) {
                if (value2 == null) {
                    ResponseUtil.onRequestError(eventEmitter, n, "Payload is set but no content-type header specified", null);
                    return;
                }
                final String string = readableMap.getString("string");
                final MediaType parse = MediaType.parse(value2);
                if (RequestBodyUtil.isGzipEncoding(value)) {
                    final RequestBody gzip = RequestBodyUtil.createGzip(parse, string);
                    if (gzip == null) {
                        ResponseUtil.onRequestError(eventEmitter, n, "Failed to gzip request body", null);
                        return;
                    }
                    url.method(s, gzip);
                }
                else {
                    url.method(s, RequestBody.create(parse, string));
                }
            }
            else if (readableMap.hasKey("base64")) {
                if (value2 == null) {
                    ResponseUtil.onRequestError(eventEmitter, n, "Payload is set but no content-type header specified", null);
                    return;
                }
                url.method(s, RequestBody.create(MediaType.parse(value2), ByteString.decodeBase64(readableMap.getString("base64"))));
            }
            else if (readableMap.hasKey("uri")) {
                if (value2 == null) {
                    ResponseUtil.onRequestError(eventEmitter, n, "Payload is set but no content-type header specified", null);
                    return;
                }
                final String string2 = readableMap.getString("uri");
                final InputStream fileInputStream = RequestBodyUtil.getFileInputStream((Context)this.getReactApplicationContext(), string2);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, n, "Could not retrieve file for uri " + string2, null);
                    return;
                }
                url.method(s, RequestBodyUtil.create(MediaType.parse(value2), fileInputStream));
            }
            else if (readableMap.hasKey("formData")) {
                if ((value = value2) == null) {
                    value = "multipart/form-data";
                }
                final MultipartBody$Builder constructMultipartBody = this.constructMultipartBody(executorToken, readableMap.getArray("formData"), value, n);
                if (constructMultipartBody == null) {
                    return;
                }
                url.method(s, (RequestBody)RequestBodyUtil.createProgressRequest((RequestBody)constructMultipartBody.build(), new NetworkingModule$2(this, eventEmitter, n)));
            }
            else {
                url.method(s, RequestBodyUtil.getEmptyBody(s));
            }
            this.addRequest(n);
            build.newCall(url.build()).enqueue((okhttp3.Callback)new NetworkingModule$3(this, n, eventEmitter, b, s2));
            return;
        }
        ResponseUtil.onRequestError(eventEmitter, n, "Unrecognized headers format", null);
    }
    
    @Override
    public boolean supportsWebWorkers() {
        return true;
    }
}
