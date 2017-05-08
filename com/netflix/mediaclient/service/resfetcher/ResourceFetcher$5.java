// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;

class ResourceFetcher$5 implements Runnable
{
    final /* synthetic */ ResourceFetcher this$0;
    final /* synthetic */ ResourceFetcherCallback val$callback;
    final /* synthetic */ String val$resourceUrl;
    
    ResourceFetcher$5(final ResourceFetcher this$0, final ResourceFetcherCallback val$callback, final String val$resourceUrl) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$resourceUrl = val$resourceUrl;
    }
    
    @Override
    public void run() {
        this.val$callback.onResourcePrefetched(this.val$resourceUrl, 0, CommonStatus.INT_ERR_RESOURCE_URL_NULL);
    }
}
