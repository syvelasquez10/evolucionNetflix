// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.backends.okhttp3;

import java.util.HashMap;
import java.util.Map;
import okhttp3.Callback;
import com.facebook.imagepipeline.producers.ProducerContextCallbacks;
import okhttp3.CacheControl$Builder;
import okhttp3.Request$Builder;
import android.os.SystemClock;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.NetworkFetcher$Callback;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;

public class OkHttpNetworkFetcher extends BaseNetworkFetcher<OkHttpNetworkFetcher$OkHttpNetworkFetchState>
{
    private Executor mCancellationExecutor;
    private final OkHttpClient mOkHttpClient;
    
    public OkHttpNetworkFetcher(final OkHttpClient mOkHttpClient) {
        this.mOkHttpClient = mOkHttpClient;
        this.mCancellationExecutor = mOkHttpClient.dispatcher().executorService();
    }
    
    private void handleException(final Call call, final Exception ex, final NetworkFetcher$Callback networkFetcher$Callback) {
        if (call.isCanceled()) {
            networkFetcher$Callback.onCancellation();
            return;
        }
        networkFetcher$Callback.onFailure(ex);
    }
    
    @Override
    public OkHttpNetworkFetcher$OkHttpNetworkFetchState createFetchState(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        return new OkHttpNetworkFetcher$OkHttpNetworkFetchState(consumer, producerContext);
    }
    
    @Override
    public void fetch(final OkHttpNetworkFetcher$OkHttpNetworkFetchState okHttpNetworkFetcher$OkHttpNetworkFetchState, final NetworkFetcher$Callback networkFetcher$Callback) {
        okHttpNetworkFetcher$OkHttpNetworkFetchState.submitTime = SystemClock.elapsedRealtime();
        final Call call = this.mOkHttpClient.newCall(new Request$Builder().cacheControl(new CacheControl$Builder().noStore().build()).url(okHttpNetworkFetcher$OkHttpNetworkFetchState.getUri().toString()).get().build());
        okHttpNetworkFetcher$OkHttpNetworkFetchState.getContext().addCallbacks(new OkHttpNetworkFetcher$1(this, call));
        call.enqueue((Callback)new OkHttpNetworkFetcher$2(this, okHttpNetworkFetcher$OkHttpNetworkFetchState, networkFetcher$Callback));
    }
    
    @Override
    public Map<String, String> getExtraMap(final OkHttpNetworkFetcher$OkHttpNetworkFetchState okHttpNetworkFetcher$OkHttpNetworkFetchState, final int n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(4);
        hashMap.put("queue_time", Long.toString(okHttpNetworkFetcher$OkHttpNetworkFetchState.responseTime - okHttpNetworkFetcher$OkHttpNetworkFetchState.submitTime));
        hashMap.put("fetch_time", Long.toString(okHttpNetworkFetcher$OkHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetcher$OkHttpNetworkFetchState.responseTime));
        hashMap.put("total_time", Long.toString(okHttpNetworkFetcher$OkHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetcher$OkHttpNetworkFetchState.submitTime));
        hashMap.put("image_size", Integer.toString(n));
        return hashMap;
    }
    
    @Override
    public void onFetchCompletion(final OkHttpNetworkFetcher$OkHttpNetworkFetchState okHttpNetworkFetcher$OkHttpNetworkFetchState, final int n) {
        okHttpNetworkFetcher$OkHttpNetworkFetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }
}
