// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import android.os.Looper;
import com.facebook.react.common.futures.SimpleSettableFuture;

final class MessageQueueThreadImpl$3 implements Runnable
{
    final /* synthetic */ SimpleSettableFuture val$looperFuture;
    final /* synthetic */ SimpleSettableFuture val$mqtFuture;
    
    MessageQueueThreadImpl$3(final SimpleSettableFuture val$looperFuture, final SimpleSettableFuture val$mqtFuture) {
        this.val$looperFuture = val$looperFuture;
        this.val$mqtFuture = val$mqtFuture;
    }
    
    @Override
    public void run() {
        Looper.prepare();
        this.val$looperFuture.set(Looper.myLooper());
        MessageQueueThreadRegistry.register(this.val$mqtFuture.getOrThrow());
        Looper.loop();
    }
}
