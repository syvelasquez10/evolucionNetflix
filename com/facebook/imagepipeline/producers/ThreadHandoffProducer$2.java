// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;

class ThreadHandoffProducer$2 extends BaseProducerContextCallbacks
{
    final /* synthetic */ ThreadHandoffProducer this$0;
    final /* synthetic */ StatefulProducerRunnable val$statefulRunnable;
    
    ThreadHandoffProducer$2(final ThreadHandoffProducer this$0, final StatefulProducerRunnable val$statefulRunnable) {
        this.this$0 = this$0;
        this.val$statefulRunnable = val$statefulRunnable;
    }
    
    @Override
    public void onCancellationRequested() {
        this.val$statefulRunnable.cancel();
        this.this$0.mThreadHandoffProducerQueue.remove(this.val$statefulRunnable);
    }
}
