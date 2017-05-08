// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

class LocalVideoThumbnailProducer$2 extends BaseProducerContextCallbacks
{
    final /* synthetic */ LocalVideoThumbnailProducer this$0;
    final /* synthetic */ StatefulProducerRunnable val$cancellableProducerRunnable;
    
    LocalVideoThumbnailProducer$2(final LocalVideoThumbnailProducer this$0, final StatefulProducerRunnable val$cancellableProducerRunnable) {
        this.this$0 = this$0;
        this.val$cancellableProducerRunnable = val$cancellableProducerRunnable;
    }
    
    @Override
    public void onCancellationRequested() {
        this.val$cancellableProducerRunnable.cancel();
    }
}
