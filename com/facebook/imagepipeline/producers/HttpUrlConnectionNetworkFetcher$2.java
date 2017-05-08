// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.concurrent.Future;

class HttpUrlConnectionNetworkFetcher$2 extends BaseProducerContextCallbacks
{
    final /* synthetic */ HttpUrlConnectionNetworkFetcher this$0;
    final /* synthetic */ NetworkFetcher$Callback val$callback;
    final /* synthetic */ Future val$future;
    
    HttpUrlConnectionNetworkFetcher$2(final HttpUrlConnectionNetworkFetcher this$0, final Future val$future, final NetworkFetcher$Callback val$callback) {
        this.this$0 = this$0;
        this.val$future = val$future;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onCancellationRequested() {
        if (this.val$future.cancel(false)) {
            this.val$callback.onCancellation();
        }
    }
}
