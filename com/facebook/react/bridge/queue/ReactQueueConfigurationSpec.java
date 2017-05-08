// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import android.os.Build$VERSION;

public class ReactQueueConfigurationSpec
{
    private final MessageQueueThreadSpec mJSQueueThreadSpec;
    private final MessageQueueThreadSpec mNativeModulesQueueThreadSpec;
    
    private ReactQueueConfigurationSpec(final MessageQueueThreadSpec mNativeModulesQueueThreadSpec, final MessageQueueThreadSpec mjsQueueThreadSpec) {
        this.mNativeModulesQueueThreadSpec = mNativeModulesQueueThreadSpec;
        this.mJSQueueThreadSpec = mjsQueueThreadSpec;
    }
    
    public static ReactQueueConfigurationSpec$Builder builder() {
        return new ReactQueueConfigurationSpec$Builder();
    }
    
    public static ReactQueueConfigurationSpec createDefault() {
        MessageQueueThreadSpec nativeModulesQueueThreadSpec;
        if (Build$VERSION.SDK_INT < 21) {
            nativeModulesQueueThreadSpec = MessageQueueThreadSpec.newBackgroundThreadSpec("native_modules", 2000000L);
        }
        else {
            nativeModulesQueueThreadSpec = MessageQueueThreadSpec.newBackgroundThreadSpec("native_modules");
        }
        return builder().setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("js")).setNativeModulesQueueThreadSpec(nativeModulesQueueThreadSpec).build();
    }
    
    public MessageQueueThreadSpec getJSQueueThreadSpec() {
        return this.mJSQueueThreadSpec;
    }
    
    public MessageQueueThreadSpec getNativeModulesQueueThreadSpec() {
        return this.mNativeModulesQueueThreadSpec;
    }
}
