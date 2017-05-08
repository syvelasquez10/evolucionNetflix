// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

class AbstractDataSource$1 implements Runnable
{
    final /* synthetic */ AbstractDataSource this$0;
    final /* synthetic */ DataSubscriber val$dataSubscriber;
    final /* synthetic */ boolean val$isCancellation;
    final /* synthetic */ boolean val$isFailure;
    
    AbstractDataSource$1(final AbstractDataSource this$0, final boolean val$isFailure, final DataSubscriber val$dataSubscriber, final boolean val$isCancellation) {
        this.this$0 = this$0;
        this.val$isFailure = val$isFailure;
        this.val$dataSubscriber = val$dataSubscriber;
        this.val$isCancellation = val$isCancellation;
    }
    
    @Override
    public void run() {
        if (this.val$isFailure) {
            this.val$dataSubscriber.onFailure(this.this$0);
            return;
        }
        if (this.val$isCancellation) {
            this.val$dataSubscriber.onCancellation(this.this$0);
            return;
        }
        this.val$dataSubscriber.onNewResult(this.this$0);
    }
}
