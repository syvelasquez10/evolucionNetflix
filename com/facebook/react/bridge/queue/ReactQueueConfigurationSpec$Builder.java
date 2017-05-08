// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import com.facebook.infer.annotation.Assertions;

public class ReactQueueConfigurationSpec$Builder
{
    private MessageQueueThreadSpec mJSQueueSpec;
    private MessageQueueThreadSpec mNativeModulesQueueSpec;
    
    public ReactQueueConfigurationSpec build() {
        return new ReactQueueConfigurationSpec(Assertions.assertNotNull(this.mNativeModulesQueueSpec), Assertions.assertNotNull(this.mJSQueueSpec), null);
    }
    
    public ReactQueueConfigurationSpec$Builder setJSQueueThreadSpec(final MessageQueueThreadSpec mjsQueueSpec) {
        Assertions.assertCondition(this.mJSQueueSpec == null, "Setting JS queue multiple times!");
        this.mJSQueueSpec = mjsQueueSpec;
        return this;
    }
    
    public ReactQueueConfigurationSpec$Builder setNativeModulesQueueThreadSpec(final MessageQueueThreadSpec mNativeModulesQueueSpec) {
        Assertions.assertCondition(this.mNativeModulesQueueSpec == null, "Setting native modules queue spec multiple times!");
        this.mNativeModulesQueueSpec = mNativeModulesQueueSpec;
        return this;
    }
}
