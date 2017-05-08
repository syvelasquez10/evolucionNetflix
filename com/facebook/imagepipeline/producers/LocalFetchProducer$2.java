// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

class LocalFetchProducer$2 extends BaseProducerContextCallbacks
{
    final /* synthetic */ LocalFetchProducer this$0;
    final /* synthetic */ StatefulProducerRunnable val$cancellableProducerRunnable;
    
    LocalFetchProducer$2(final LocalFetchProducer this$0, final StatefulProducerRunnable val$cancellableProducerRunnable) {
        this.this$0 = this$0;
        this.val$cancellableProducerRunnable = val$cancellableProducerRunnable;
    }
    
    @Override
    public void onCancellationRequested() {
        this.val$cancellableProducerRunnable.cancel();
    }
}
