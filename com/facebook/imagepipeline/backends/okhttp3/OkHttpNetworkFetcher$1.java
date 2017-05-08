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
import okhttp3.OkHttpClient;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import android.os.Looper;
import okhttp3.Call;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;

class OkHttpNetworkFetcher$1 extends BaseProducerContextCallbacks
{
    final /* synthetic */ OkHttpNetworkFetcher this$0;
    final /* synthetic */ Call val$call;
    
    OkHttpNetworkFetcher$1(final OkHttpNetworkFetcher this$0, final Call val$call) {
        this.this$0 = this$0;
        this.val$call = val$call;
    }
    
    @Override
    public void onCancellationRequested() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.val$call.cancel();
            return;
        }
        this.this$0.mCancellationExecutor.execute(new OkHttpNetworkFetcher$1$1(this));
    }
}
