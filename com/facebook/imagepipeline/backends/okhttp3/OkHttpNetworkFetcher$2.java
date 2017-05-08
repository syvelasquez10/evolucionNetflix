// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.backends.okhttp3;

import java.util.HashMap;
import java.util.Map;
import com.facebook.imagepipeline.producers.ProducerContextCallbacks;
import okhttp3.CacheControl$Builder;
import okhttp3.Request$Builder;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Consumer;
import okhttp3.OkHttpClient;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import okhttp3.ResponseBody;
import com.facebook.common.logging.FLog;
import android.os.SystemClock;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.Call;
import com.facebook.imagepipeline.producers.NetworkFetcher$Callback;
import okhttp3.Callback;

class OkHttpNetworkFetcher$2 implements Callback
{
    final /* synthetic */ OkHttpNetworkFetcher this$0;
    final /* synthetic */ NetworkFetcher$Callback val$callback;
    final /* synthetic */ OkHttpNetworkFetcher$OkHttpNetworkFetchState val$fetchState;
    
    OkHttpNetworkFetcher$2(final OkHttpNetworkFetcher this$0, final OkHttpNetworkFetcher$OkHttpNetworkFetchState val$fetchState, final NetworkFetcher$Callback val$callback) {
        this.this$0 = this$0;
        this.val$fetchState = val$fetchState;
        this.val$callback = val$callback;
    }
    
    public void onFailure(final Call call, final IOException ex) {
        this.this$0.handleException(call, ex, this.val$callback);
    }
    
    public void onResponse(final Call ex, final Response response) {
        while (true) {
            long n = 0L;
            this.val$fetchState.responseTime = SystemClock.elapsedRealtime();
            final ResponseBody body = response.body();
            while (true) {
                long contentLength;
                try {
                    if (!response.isSuccessful()) {
                        this.this$0.handleException((Call)ex, new IOException("Unexpected HTTP code " + response), this.val$callback);
                        try {
                            body.close();
                            return;
                        }
                        catch (Exception ex) {
                            FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", ex);
                            return;
                        }
                    }
                    contentLength = body.contentLength();
                    if (contentLength < 0L) {
                        this.val$callback.onResponse(body.byteStream(), (int)n);
                        try {
                            body.close();
                            return;
                        }
                        catch (Exception ex) {
                            FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", ex);
                            return;
                        }
                    }
                }
                catch (Exception ex2) {
                    this.this$0.handleException((Call)ex, ex2, this.val$callback);
                    try {
                        body.close();
                        return;
                    }
                    catch (Exception ex3) {
                        FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", ex3);
                        return;
                    }
                }
                finally {
                    try {
                        body.close();
                    }
                    catch (Exception ex4) {
                        FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", ex4);
                    }
                }
                n = contentLength;
                continue;
            }
        }
    }
}
