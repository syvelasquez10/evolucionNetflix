// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;

public class EagerModuleProvider implements Provider<NativeModule>
{
    private final NativeModule mModule;
    
    public EagerModuleProvider(final NativeModule mModule) {
        this.mModule = mModule;
    }
    
    public NativeModule get() {
        return this.mModule;
    }
}
