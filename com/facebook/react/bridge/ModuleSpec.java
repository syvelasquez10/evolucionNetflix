// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import javax.inject.Provider;

public class ModuleSpec
{
    private static final Class[] CONTEXT_SIGNATURE;
    private static final Class[] EMPTY_SIGNATURE;
    private final Provider<? extends NativeModule> mProvider;
    private final Class<? extends NativeModule> mType;
    
    static {
        EMPTY_SIGNATURE = new Class[0];
        CONTEXT_SIGNATURE = new Class[] { ReactApplicationContext.class };
    }
    
    public ModuleSpec(final Class<? extends NativeModule> mType, final Provider<? extends NativeModule> mProvider) {
        this.mType = mType;
        this.mProvider = mProvider;
    }
    
    public Provider<? extends NativeModule> getProvider() {
        return this.mProvider;
    }
    
    public Class<? extends NativeModule> getType() {
        return this.mType;
    }
}
