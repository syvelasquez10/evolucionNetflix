// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

class PServiceFetchAgent$2 implements Runnable
{
    final /* synthetic */ PServiceFetchAgent this$0;
    final /* synthetic */ PDiskDataRepository$LoadCallback val$loadCallback;
    
    PServiceFetchAgent$2(final PServiceFetchAgent this$0, final PDiskDataRepository$LoadCallback val$loadCallback) {
        this.this$0 = this$0;
        this.val$loadCallback = val$loadCallback;
    }
    
    @Override
    public void run() {
        PDiskDataRepository.startLoadFromDisk(this.this$0.getContext(), this.val$loadCallback);
    }
}
