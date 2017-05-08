// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import android.os.SystemClock;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import java.util.Map;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.InputStream;

class NetworkFetchProducer$1 implements NetworkFetcher$Callback
{
    final /* synthetic */ NetworkFetchProducer this$0;
    final /* synthetic */ FetchState val$fetchState;
    
    NetworkFetchProducer$1(final NetworkFetchProducer this$0, final FetchState val$fetchState) {
        this.this$0 = this$0;
        this.val$fetchState = val$fetchState;
    }
    
    @Override
    public void onCancellation() {
        this.this$0.onCancellation(this.val$fetchState);
    }
    
    @Override
    public void onFailure(final Throwable t) {
        this.this$0.onFailure(this.val$fetchState, t);
    }
    
    @Override
    public void onResponse(final InputStream inputStream, final int n) {
        this.this$0.onResponse(this.val$fetchState, inputStream, n);
    }
}
