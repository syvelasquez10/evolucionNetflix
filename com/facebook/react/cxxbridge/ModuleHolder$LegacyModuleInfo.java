// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.module.model.Info;

class ModuleHolder$LegacyModuleInfo implements Info
{
    public final Class<?> mType;
    final /* synthetic */ ModuleHolder this$0;
    
    public ModuleHolder$LegacyModuleInfo(final ModuleHolder this$0, final Class<?> mType) {
        this.this$0 = this$0;
        this.mType = mType;
    }
    
    @Override
    public boolean canOverrideExistingModule() {
        return this.this$0.getModule().canOverrideExistingModule();
    }
    
    @Override
    public String name() {
        return this.this$0.getModule().getName();
    }
    
    @Override
    public boolean needsEagerInit() {
        return true;
    }
}
