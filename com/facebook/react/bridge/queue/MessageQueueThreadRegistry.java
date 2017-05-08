// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class MessageQueueThreadRegistry
{
    private static ThreadLocal<MessageQueueThread> sMyMessageQueueThread;
    
    static {
        MessageQueueThreadRegistry.sMyMessageQueueThread = new ThreadLocal<MessageQueueThread>();
    }
    
    @DoNotStrip
    public static MessageQueueThread myMessageQueueThread() {
        return Assertions.assertNotNull(MessageQueueThreadRegistry.sMyMessageQueueThread.get(), "This thread doesn't have a MessageQueueThread registered to it!");
    }
    
    static void register(final MessageQueueThread messageQueueThread) {
        messageQueueThread.assertIsOnThread();
        MessageQueueThreadRegistry.sMyMessageQueueThread.set(messageQueueThread);
    }
}
