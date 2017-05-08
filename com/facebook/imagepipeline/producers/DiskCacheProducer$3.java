// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.concurrent.atomic.AtomicBoolean;

class DiskCacheProducer$3 extends BaseProducerContextCallbacks
{
    final /* synthetic */ DiskCacheProducer this$0;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    
    DiskCacheProducer$3(final DiskCacheProducer this$0, final AtomicBoolean val$isCancelled) {
        this.this$0 = this$0;
        this.val$isCancelled = val$isCancelled;
    }
    
    @Override
    public void onCancellationRequested() {
        this.val$isCancelled.set(true);
    }
}
