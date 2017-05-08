// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okhttp3.ResponseBody;
import okhttp3.Response;
import okhttp3.Interceptor$Chain;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import okhttp3.Interceptor;

class NetworkingModule$1 implements Interceptor
{
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ DeviceEventManagerModule$RCTDeviceEventEmitter val$eventEmitter;
    final /* synthetic */ int val$requestId;
    final /* synthetic */ String val$responseType;
    
    NetworkingModule$1(final NetworkingModule this$0, final String val$responseType, final DeviceEventManagerModule$RCTDeviceEventEmitter val$eventEmitter, final int val$requestId) {
        this.this$0 = this$0;
        this.val$responseType = val$responseType;
        this.val$eventEmitter = val$eventEmitter;
        this.val$requestId = val$requestId;
    }
    
    public Response intercept(final Interceptor$Chain interceptor$Chain) {
        final Response proceed = interceptor$Chain.proceed(interceptor$Chain.request());
        return proceed.newBuilder().body((ResponseBody)new ProgressResponseBody(proceed.body(), new NetworkingModule$1$1(this))).build();
    }
}
