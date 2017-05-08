// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;

class CoreModulesPackage$1 implements Provider<NativeModule>
{
    final /* synthetic */ CoreModulesPackage this$0;
    
    CoreModulesPackage$1(final CoreModulesPackage this$0) {
        this.this$0 = this$0;
    }
    
    public NativeModule get() {
        return new AndroidInfoModule();
    }
}
