// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

public interface ReactQueueConfiguration
{
    MessageQueueThread getJSQueueThread();
    
    MessageQueueThread getNativeModulesQueueThread();
    
    MessageQueueThread getUIQueueThread();
}
