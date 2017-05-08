// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

class AbstractDataSource$2 implements Runnable
{
    final /* synthetic */ AbstractDataSource this$0;
    final /* synthetic */ DataSubscriber val$subscriber;
    
    AbstractDataSource$2(final AbstractDataSource this$0, final DataSubscriber val$subscriber) {
        this.this$0 = this$0;
        this.val$subscriber = val$subscriber;
    }
    
    @Override
    public void run() {
        this.val$subscriber.onProgressUpdate(this.this$0);
    }
}
