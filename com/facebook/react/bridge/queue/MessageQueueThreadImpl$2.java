// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import android.os.Process;

final class MessageQueueThreadImpl$2 implements Runnable
{
    final /* synthetic */ MessageQueueThreadImpl val$mqt;
    
    MessageQueueThreadImpl$2(final MessageQueueThreadImpl val$mqt) {
        this.val$mqt = val$mqt;
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(-4);
        MessageQueueThreadRegistry.register(this.val$mqt);
    }
}
