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
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Set;
import okhttp3.OkHttpClient;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.GuardedAsyncTask;

class NetworkingModule$4 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ int val$requestId;
    
    NetworkingModule$4(final NetworkingModule this$0, final ReactContext reactContext, final int val$requestId) {
        this.this$0 = this$0;
        this.val$requestId = val$requestId;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        OkHttpCallUtil.cancelTag(this.this$0.mClient, this.val$requestId);
    }
}
