// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.module.model.Info;

public class LegacyModuleInfo implements Info
{
    public final NativeModule mNativeModule;
    public final Class<?> mType;
    
    public LegacyModuleInfo(final Class<?> mType, final NativeModule mNativeModule) {
        this.mType = mType;
        this.mNativeModule = mNativeModule;
    }
    
    @Override
    public boolean canOverrideExistingModule() {
        return this.mNativeModule.canOverrideExistingModule();
    }
    
    @Override
    public String name() {
        return this.mNativeModule.getName();
    }
    
    @Override
    public boolean needsEagerInit() {
        return true;
    }
}
