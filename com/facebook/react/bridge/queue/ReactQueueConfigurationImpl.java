// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import java.util.HashMap;
import com.facebook.react.common.MapBuilder;

public class ReactQueueConfigurationImpl implements ReactQueueConfiguration
{
    private final MessageQueueThreadImpl mJSQueueThread;
    private final MessageQueueThreadImpl mNativeModulesQueueThread;
    private final MessageQueueThreadImpl mUIQueueThread;
    
    private ReactQueueConfigurationImpl(final MessageQueueThreadImpl muiQueueThread, final MessageQueueThreadImpl mNativeModulesQueueThread, final MessageQueueThreadImpl mjsQueueThread) {
        this.mUIQueueThread = muiQueueThread;
        this.mNativeModulesQueueThread = mNativeModulesQueueThread;
        this.mJSQueueThread = mjsQueueThread;
    }
    
    public static ReactQueueConfigurationImpl create(final ReactQueueConfigurationSpec reactQueueConfigurationSpec, final QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final HashMap<Object, Object> hashMap = MapBuilder.newHashMap();
        final MessageQueueThreadSpec mainThreadSpec = MessageQueueThreadSpec.mainThreadSpec();
        final MessageQueueThreadImpl create = MessageQueueThreadImpl.create(mainThreadSpec, queueThreadExceptionHandler);
        hashMap.put(mainThreadSpec, create);
        MessageQueueThreadImpl create2 = hashMap.get(reactQueueConfigurationSpec.getJSQueueThreadSpec());
        if (create2 == null) {
            create2 = MessageQueueThreadImpl.create(reactQueueConfigurationSpec.getJSQueueThreadSpec(), queueThreadExceptionHandler);
        }
        MessageQueueThreadImpl create3;
        if ((create3 = hashMap.get(reactQueueConfigurationSpec.getNativeModulesQueueThreadSpec())) == null) {
            create3 = MessageQueueThreadImpl.create(reactQueueConfigurationSpec.getNativeModulesQueueThreadSpec(), queueThreadExceptionHandler);
        }
        return new ReactQueueConfigurationImpl(create, create3, create2);
    }
    
    @Override
    public MessageQueueThread getJSQueueThread() {
        return this.mJSQueueThread;
    }
    
    @Override
    public MessageQueueThread getNativeModulesQueueThread() {
        return this.mNativeModulesQueueThread;
    }
    
    @Override
    public MessageQueueThread getUIQueueThread() {
        return this.mUIQueueThread;
    }
}
