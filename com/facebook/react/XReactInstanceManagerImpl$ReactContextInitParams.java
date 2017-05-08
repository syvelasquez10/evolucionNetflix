// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.cxxbridge.JavaScriptExecutor$Factory;
import com.facebook.react.cxxbridge.JSBundleLoader;

class XReactInstanceManagerImpl$ReactContextInitParams
{
    private final JSBundleLoader mJsBundleLoader;
    private final JavaScriptExecutor$Factory mJsExecutorFactory;
    final /* synthetic */ XReactInstanceManagerImpl this$0;
    
    public XReactInstanceManagerImpl$ReactContextInitParams(final XReactInstanceManagerImpl this$0, final JavaScriptExecutor$Factory javaScriptExecutor$Factory, final JSBundleLoader jsBundleLoader) {
        this.this$0 = this$0;
        this.mJsExecutorFactory = Assertions.assertNotNull(javaScriptExecutor$Factory);
        this.mJsBundleLoader = Assertions.assertNotNull(jsBundleLoader);
    }
    
    public JSBundleLoader getJsBundleLoader() {
        return this.mJsBundleLoader;
    }
    
    public JavaScriptExecutor$Factory getJsExecutorFactory() {
        return this.mJsExecutorFactory;
    }
}
