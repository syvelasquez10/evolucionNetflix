// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import java.util.Collection;
import com.facebook.jni.HybridData;

public class ModuleRegistryHolder
{
    private final HybridData mHybridData;
    
    public ModuleRegistryHolder(final CatalystInstanceImpl catalystInstanceImpl, final Collection<JavaModuleWrapper> collection, final Collection<CxxModuleWrapper> collection2) {
        this.mHybridData = initHybrid(catalystInstanceImpl, collection, collection2);
    }
    
    private static native HybridData initHybrid(final CatalystInstanceImpl p0, final Collection<JavaModuleWrapper> p1, final Collection<CxxModuleWrapper> p2);
}
