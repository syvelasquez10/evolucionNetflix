// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.websocket;

import okio.ByteString;
import okhttp3.RequestBody;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import okhttp3.OkHttpClient;
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
import java.net.URISyntaxException;
import java.net.URI;
import java.util.List;
import java.util.HashMap;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Map;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import okio.Buffer;
import okhttp3.ws.WebSocket;
import okhttp3.ResponseBody;
import okhttp3.Response;
import java.io.IOException;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import okhttp3.ws.WebSocketListener;

class WebSocketModule$1 implements WebSocketListener
{
    final /* synthetic */ WebSocketModule this$0;
    final /* synthetic */ int val$id;
    
    WebSocketModule$1(final WebSocketModule this$0, final int val$id) {
        this.this$0 = this$0;
        this.val$id = val$id;
    }
    
    public void onClose(final int n, final String s) {
        final WritableMap map = Arguments.createMap();
        map.putInt("id", this.val$id);
        map.putInt("code", n);
        map.putString("reason", s);
        this.this$0.sendEvent("websocketClosed", map);
    }
    
    public void onFailure(final IOException ex, final Response response) {
        this.this$0.notifyWebSocketFailed(this.val$id, ex.getMessage());
    }
    
    public void onMessage(final ResponseBody p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   okhttp3/ResponseBody.contentType:()Lokhttp3/MediaType;
        //     4: getstatic       okhttp3/ws/WebSocket.BINARY:Lokhttp3/MediaType;
        //     7: if_acmpne       91
        //    10: aload_1        
        //    11: invokevirtual   okhttp3/ResponseBody.source:()Lokio/BufferedSource;
        //    14: invokeinterface okio/BufferedSource.readByteArray:()[B
        //    19: iconst_2       
        //    20: invokestatic    android/util/Base64.encodeToString:([BI)Ljava/lang/String;
        //    23: astore_2       
        //    24: aload_1        
        //    25: invokevirtual   okhttp3/ResponseBody.source:()Lokio/BufferedSource;
        //    28: invokeinterface okio/BufferedSource.close:()V
        //    33: invokestatic    com/facebook/react/bridge/Arguments.createMap:()Lcom/facebook/react/bridge/WritableMap;
        //    36: astore_3       
        //    37: aload_3        
        //    38: ldc             "id"
        //    40: aload_0        
        //    41: getfield        com/facebook/react/modules/websocket/WebSocketModule$1.val$id:I
        //    44: invokeinterface com/facebook/react/bridge/WritableMap.putInt:(Ljava/lang/String;I)V
        //    49: aload_3        
        //    50: ldc             "data"
        //    52: aload_2        
        //    53: invokeinterface com/facebook/react/bridge/WritableMap.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    58: aload_1        
        //    59: invokevirtual   okhttp3/ResponseBody.contentType:()Lokhttp3/MediaType;
        //    62: getstatic       okhttp3/ws/WebSocket.BINARY:Lokhttp3/MediaType;
        //    65: if_acmpne       153
        //    68: ldc             "binary"
        //    70: astore_1       
        //    71: aload_3        
        //    72: ldc             "type"
        //    74: aload_1        
        //    75: invokeinterface com/facebook/react/bridge/WritableMap.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    80: aload_0        
        //    81: getfield        com/facebook/react/modules/websocket/WebSocketModule$1.this$0:Lcom/facebook/react/modules/websocket/WebSocketModule;
        //    84: ldc             "websocketMessage"
        //    86: aload_3        
        //    87: invokestatic    com/facebook/react/modules/websocket/WebSocketModule.access$100:(Lcom/facebook/react/modules/websocket/WebSocketModule;Ljava/lang/String;Lcom/facebook/react/bridge/WritableMap;)V
        //    90: return         
        //    91: aload_1        
        //    92: invokevirtual   okhttp3/ResponseBody.source:()Lokio/BufferedSource;
        //    95: invokeinterface okio/BufferedSource.readUtf8:()Ljava/lang/String;
        //   100: astore_2       
        //   101: goto            24
        //   104: astore_1       
        //   105: aload_0        
        //   106: getfield        com/facebook/react/modules/websocket/WebSocketModule$1.this$0:Lcom/facebook/react/modules/websocket/WebSocketModule;
        //   109: aload_0        
        //   110: getfield        com/facebook/react/modules/websocket/WebSocketModule$1.val$id:I
        //   113: aload_1        
        //   114: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   117: invokestatic    com/facebook/react/modules/websocket/WebSocketModule.access$200:(Lcom/facebook/react/modules/websocket/WebSocketModule;ILjava/lang/String;)V
        //   120: return         
        //   121: astore_3       
        //   122: ldc             "React"
        //   124: new             Ljava/lang/StringBuilder;
        //   127: dup            
        //   128: invokespecial   java/lang/StringBuilder.<init>:()V
        //   131: ldc             "Could not close BufferedSource for WebSocket id "
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: aload_0        
        //   137: getfield        com/facebook/react/modules/websocket/WebSocketModule$1.val$id:I
        //   140: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   143: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   146: aload_3        
        //   147: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   150: goto            33
        //   153: ldc             "text"
        //   155: astore_1       
        //   156: goto            71
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      24     104    121    Ljava/io/IOException;
        //  24     33     121    153    Ljava/io/IOException;
        //  91     101    104    121    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public void onOpen(final WebSocket webSocket, final Response response) {
        this.this$0.mWebSocketConnections.put(this.val$id, webSocket);
        final WritableMap map = Arguments.createMap();
        map.putInt("id", this.val$id);
        this.this$0.sendEvent("websocketOpen", map);
    }
    
    public void onPong(final Buffer buffer) {
    }
}
