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
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import okhttp3.Headers$Builder;
import java.io.InputStream;
import android.content.Context;
import okhttp3.RequestBody;
import com.facebook.react.bridge.ReadableMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody$Builder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ExecutorToken;
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
import okhttp3.ResponseBody;
import android.util.Base64;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.Call;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import okhttp3.Callback;

class NetworkingModule$3 implements Callback
{
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ DeviceEventManagerModule$RCTDeviceEventEmitter val$eventEmitter;
    final /* synthetic */ int val$requestId;
    final /* synthetic */ String val$responseType;
    final /* synthetic */ boolean val$useIncrementalUpdates;
    
    NetworkingModule$3(final NetworkingModule this$0, final int val$requestId, final DeviceEventManagerModule$RCTDeviceEventEmitter val$eventEmitter, final boolean val$useIncrementalUpdates, final String val$responseType) {
        this.this$0 = this$0;
        this.val$requestId = val$requestId;
        this.val$eventEmitter = val$eventEmitter;
        this.val$useIncrementalUpdates = val$useIncrementalUpdates;
        this.val$responseType = val$responseType;
    }
    
    public void onFailure(final Call call, final IOException ex) {
        if (this.this$0.mShuttingDown) {
            return;
        }
        this.this$0.removeRequest(this.val$requestId);
        ResponseUtil.onRequestError(this.val$eventEmitter, this.val$requestId, ex.getMessage(), ex);
    }
    
    public void onResponse(final Call call, final Response response) {
        if (this.this$0.mShuttingDown) {
            return;
        }
        this.this$0.removeRequest(this.val$requestId);
        ResponseUtil.onResponseReceived(this.val$eventEmitter, this.val$requestId, response.code(), translateHeaders(response.headers()), response.request().url().toString());
        final ResponseBody body = response.body();
        try {
            if (this.val$useIncrementalUpdates && this.val$responseType.equals("text")) {
                this.this$0.readWithProgress(this.val$eventEmitter, this.val$requestId, body);
                ResponseUtil.onRequestSuccess(this.val$eventEmitter, this.val$requestId);
                return;
            }
        }
        catch (IOException ex) {
            ResponseUtil.onRequestError(this.val$eventEmitter, this.val$requestId, ex.getMessage(), ex);
            return;
        }
        String s = "";
        if (this.val$responseType.equals("text")) {
            s = body.string();
        }
        else if (this.val$responseType.equals("base64")) {
            s = Base64.encodeToString(body.bytes(), 2);
        }
        ResponseUtil.onDataReceived(this.val$eventEmitter, this.val$requestId, s);
        ResponseUtil.onRequestSuccess(this.val$eventEmitter, this.val$requestId);
    }
}
