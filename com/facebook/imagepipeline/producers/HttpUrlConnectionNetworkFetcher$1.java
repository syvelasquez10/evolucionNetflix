// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

class HttpUrlConnectionNetworkFetcher$1 implements Runnable
{
    final /* synthetic */ HttpUrlConnectionNetworkFetcher this$0;
    final /* synthetic */ NetworkFetcher$Callback val$callback;
    final /* synthetic */ FetchState val$fetchState;
    
    HttpUrlConnectionNetworkFetcher$1(final HttpUrlConnectionNetworkFetcher this$0, final FetchState val$fetchState, final NetworkFetcher$Callback val$callback) {
        this.this$0 = this$0;
        this.val$fetchState = val$fetchState;
        this.val$callback = val$callback;
    }
    
    @Override
    public void run() {
        this.this$0.fetchSync(this.val$fetchState, this.val$callback);
    }
}
