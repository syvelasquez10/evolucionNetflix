// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

class NetworkImageView$1$1 implements Runnable
{
    final /* synthetic */ NetworkImageView$1 this$1;
    final /* synthetic */ ImageLoader$ImageContainer val$response;
    
    NetworkImageView$1$1(final NetworkImageView$1 this$1, final ImageLoader$ImageContainer val$response) {
        this.this$1 = this$1;
        this.val$response = val$response;
    }
    
    @Override
    public void run() {
        this.this$1.onResponse(this.val$response, false);
    }
}
