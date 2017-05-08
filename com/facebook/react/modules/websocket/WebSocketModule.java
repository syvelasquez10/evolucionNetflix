// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.websocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import okio.ByteString;
import okhttp3.RequestBody;
import okio.Buffer;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import okhttp3.OkHttpClient;
import okhttp3.ws.WebSocketListener;
import okhttp3.ws.WebSocketCall;
import com.facebook.react.bridge.ReadableType;
import okhttp3.Request$Builder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient$Builder;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.Arguments;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.List;
import com.facebook.react.bridge.WritableMap;
import java.util.HashMap;
import com.facebook.react.bridge.ReactApplicationContext;
import okhttp3.ws.WebSocket;
import java.util.Map;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class WebSocketModule extends ReactContextBaseJavaModule
{
    private ForwardingCookieHandler mCookieHandler;
    private ReactContext mReactContext;
    private final Map<Integer, WebSocket> mWebSocketConnections;
    
    public WebSocketModule(final ReactApplicationContext mReactContext) {
        super(mReactContext);
        this.mWebSocketConnections = new HashMap<Integer, WebSocket>();
        this.mReactContext = mReactContext;
        this.mCookieHandler = new ForwardingCookieHandler(mReactContext);
    }
    
    private String getCookie(final String s) {
        try {
            final List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(s)), new HashMap<String, List<String>>()).get("Cookie");
            if (list != null) {
                if (!list.isEmpty()) {
                    return list.get(0);
                }
            }
        }
        catch (URISyntaxException ex) {}
        catch (IOException ex2) {
            goto Label_0068;
        }
        return null;
    }
    
    private static String getDefaultOrigin(final String s) {
        String s2 = "";
        try {
            final URI uri = new URI(s);
            if (uri.getScheme().equals("wss")) {
                s2 = "" + "https";
            }
            else if (uri.getScheme().equals("ws")) {
                s2 = "" + "http";
            }
            if (uri.getPort() != -1) {
                return String.format("%s://%s:%s", s2, uri.getHost(), uri.getPort());
            }
            return String.format("%s://%s/", s2, uri.getHost());
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Unable to set " + s + " as default origin header");
        }
    }
    
    private void notifyWebSocketFailed(final int n, final String s) {
        final WritableMap map = Arguments.createMap();
        map.putInt("id", n);
        map.putString("message", s);
        this.sendEvent("websocketFailed", map);
    }
    
    private void sendEvent(final String s, final WritableMap writableMap) {
        this.mReactContext.getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit(s, writableMap);
    }
    
    @ReactMethod
    public void close(final int n, final String s, final int n2) {
        final WebSocket webSocket = this.mWebSocketConnections.get(n2);
        if (webSocket == null) {
            return;
        }
        try {
            webSocket.close(n, s);
            this.mWebSocketConnections.remove(n2);
        }
        catch (Exception ex) {
            FLog.e("React", "Could not close WebSocket connection for id " + n2, ex);
        }
    }
    
    @ReactMethod
    public void connect(String nextKey, final ReadableArray readableArray, final ReadableMap readableMap, final int n) {
        final OkHttpClient build = new OkHttpClient$Builder().connectTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MINUTES).build();
        final Request$Builder url = new Request$Builder().tag((Object)n).url(nextKey);
        final String cookie = this.getCookie(nextKey);
        if (cookie != null) {
            url.addHeader("Cookie", cookie);
        }
        if (readableMap != null) {
            final ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            if (!readableMap.hasKey("origin")) {
                url.addHeader("origin", getDefaultOrigin(nextKey));
            }
            while (keySetIterator.hasNextKey()) {
                nextKey = keySetIterator.nextKey();
                if (ReadableType.String.equals(readableMap.getType(nextKey))) {
                    url.addHeader(nextKey, readableMap.getString(nextKey));
                }
                else {
                    FLog.w("React", "Ignoring: requested " + nextKey + ", value not a string");
                }
            }
        }
        else {
            url.addHeader("origin", getDefaultOrigin(nextKey));
        }
        if (readableArray != null && readableArray.size() > 0) {
            final StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < readableArray.size(); ++i) {
                final String trim = readableArray.getString(i).trim();
                if (!trim.isEmpty() && !trim.contains(",")) {
                    sb.append(trim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                url.addHeader("Sec-WebSocket-Protocol", sb.toString());
            }
        }
        WebSocketCall.create(build, url.build()).enqueue((WebSocketListener)new WebSocketModule$1(this, n));
        build.dispatcher().executorService().shutdown();
    }
    
    @Override
    public String getName() {
        return "WebSocketModule";
    }
    
    @ReactMethod
    public void ping(final int n) {
        final WebSocket webSocket = this.mWebSocketConnections.get(n);
        if (webSocket == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + n);
        }
        try {
            webSocket.sendPing(new Buffer());
        }
        catch (IllegalStateException ex) {}
        catch (IOException webSocket) {
            goto Label_0064;
        }
    }
    
    @ReactMethod
    public void send(final String ex, final int n) {
        final WebSocket webSocket = this.mWebSocketConnections.get(n);
        if (webSocket == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + n);
        }
        try {
            webSocket.sendMessage(RequestBody.create(WebSocket.TEXT, (String)ex));
        }
        catch (IllegalStateException ex2) {}
        catch (IOException ex) {
            goto Label_0064;
        }
    }
    
    @ReactMethod
    public void sendBinary(final String ex, final int n) {
        final WebSocket webSocket = this.mWebSocketConnections.get(n);
        if (webSocket == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + n);
        }
        try {
            webSocket.sendMessage(RequestBody.create(WebSocket.BINARY, ByteString.decodeBase64((String)ex)));
        }
        catch (IllegalStateException ex2) {}
        catch (IOException ex) {
            goto Label_0067;
        }
    }
}
