// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;

class MessageQueueThreadImpl$1 implements Runnable
{
    final /* synthetic */ MessageQueueThreadImpl this$0;
    final /* synthetic */ Callable val$callable;
    final /* synthetic */ SimpleSettableFuture val$future;
    
    MessageQueueThreadImpl$1(final MessageQueueThreadImpl this$0, final SimpleSettableFuture val$future, final Callable val$callable) {
        this.this$0 = this$0;
        this.val$future = val$future;
        this.val$callable = val$callable;
    }
    
    @Override
    public void run() {
        try {
            this.val$future.set(this.val$callable.call());
        }
        catch (Exception exception) {
            this.val$future.setException(exception);
        }
    }
}
