// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

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

class NetworkingModule$1$1 implements ProgressListener
{
    long last;
    final /* synthetic */ NetworkingModule$1 this$1;
    
    NetworkingModule$1$1(final NetworkingModule$1 this$1) {
        this.this$1 = this$1;
        this.last = System.nanoTime();
    }
    
    @Override
    public void onProgress(final long n, final long n2, final boolean b) {
        final long nanoTime = System.nanoTime();
        if ((b || shouldDispatch(nanoTime, this.last)) && !this.this$1.val$responseType.equals("text")) {
            ResponseUtil.onDataReceivedProgress(this.this$1.val$eventEmitter, this.this$1.val$requestId, n, n2);
            this.last = nanoTime;
        }
    }
}
