// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.Request;
import com.facebook.RequestBatch;

class GraphObjectPagingLoader$3 implements Runnable
{
    final /* synthetic */ GraphObjectPagingLoader this$0;
    final /* synthetic */ RequestBatch val$batch;
    
    GraphObjectPagingLoader$3(final GraphObjectPagingLoader this$0, final RequestBatch val$batch) {
        this.this$0 = this$0;
        this.val$batch = val$batch;
    }
    
    @Override
    public void run() {
        Request.executeBatchAsync(this.val$batch);
    }
}
