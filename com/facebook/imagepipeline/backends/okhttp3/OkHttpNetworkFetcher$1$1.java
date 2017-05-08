// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.backends.okhttp3;

class OkHttpNetworkFetcher$1$1 implements Runnable
{
    final /* synthetic */ OkHttpNetworkFetcher$1 this$1;
    
    OkHttpNetworkFetcher$1$1(final OkHttpNetworkFetcher$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.val$call.cancel();
    }
}
