// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.module.model;

public class ReactModuleInfo implements Info
{
    private final boolean mCanOverrideExistingModule;
    private final String mName;
    private final boolean mNeedsEagerInit;
    
    @Override
    public boolean canOverrideExistingModule() {
        return this.mCanOverrideExistingModule;
    }
    
    @Override
    public String name() {
        return this.mName;
    }
    
    @Override
    public boolean needsEagerInit() {
        return this.mNeedsEagerInit;
    }
}
