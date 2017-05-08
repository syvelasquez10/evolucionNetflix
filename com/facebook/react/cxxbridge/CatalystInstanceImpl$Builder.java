// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.JavaScriptModuleRegistry;

public class CatalystInstanceImpl$Builder
{
    private JSBundleLoader mJSBundleLoader;
    private JavaScriptExecutor mJSExecutor;
    private JavaScriptModuleRegistry mJSModuleRegistry;
    private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private ReactQueueConfigurationSpec mReactQueueConfigurationSpec;
    private NativeModuleRegistry mRegistry;
    
    public CatalystInstanceImpl build() {
        return new CatalystInstanceImpl(Assertions.assertNotNull(this.mReactQueueConfigurationSpec), Assertions.assertNotNull(this.mJSExecutor), Assertions.assertNotNull(this.mRegistry), Assertions.assertNotNull(this.mJSModuleRegistry), Assertions.assertNotNull(this.mJSBundleLoader), Assertions.assertNotNull(this.mNativeModuleCallExceptionHandler), null);
    }
    
    public CatalystInstanceImpl$Builder setJSBundleLoader(final JSBundleLoader mjsBundleLoader) {
        this.mJSBundleLoader = mjsBundleLoader;
        return this;
    }
    
    public CatalystInstanceImpl$Builder setJSExecutor(final JavaScriptExecutor mjsExecutor) {
        this.mJSExecutor = mjsExecutor;
        return this;
    }
    
    public CatalystInstanceImpl$Builder setJSModuleRegistry(final JavaScriptModuleRegistry mjsModuleRegistry) {
        this.mJSModuleRegistry = mjsModuleRegistry;
        return this;
    }
    
    public CatalystInstanceImpl$Builder setNativeModuleCallExceptionHandler(final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler) {
        this.mNativeModuleCallExceptionHandler = mNativeModuleCallExceptionHandler;
        return this;
    }
    
    public CatalystInstanceImpl$Builder setReactQueueConfigurationSpec(final ReactQueueConfigurationSpec mReactQueueConfigurationSpec) {
        this.mReactQueueConfigurationSpec = mReactQueueConfigurationSpec;
        return this;
    }
    
    public CatalystInstanceImpl$Builder setRegistry(final NativeModuleRegistry mRegistry) {
        this.mRegistry = mRegistry;
        return this;
    }
}
