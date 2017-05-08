// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;

class CoreModulesPackage$5 implements Provider<NativeModule>
{
    final /* synthetic */ CoreModulesPackage this$0;
    final /* synthetic */ ReactApplicationContext val$reactContext;
    
    CoreModulesPackage$5(final CoreModulesPackage this$0, final ReactApplicationContext val$reactContext) {
        this.this$0 = this$0;
        this.val$reactContext = val$reactContext;
    }
    
    public NativeModule get() {
        return new HeadlessJsTaskSupportModule(this.val$reactContext);
    }
}
