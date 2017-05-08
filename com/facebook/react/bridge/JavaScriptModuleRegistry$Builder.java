// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.List;

public class JavaScriptModuleRegistry$Builder
{
    private List<JavaScriptModuleRegistration> mModules;
    
    public JavaScriptModuleRegistry$Builder() {
        this.mModules = new ArrayList<JavaScriptModuleRegistration>();
    }
    
    public JavaScriptModuleRegistry$Builder add(final Class<? extends JavaScriptModule> clazz) {
        this.mModules.add(new JavaScriptModuleRegistration(clazz));
        return this;
    }
    
    public JavaScriptModuleRegistry build() {
        return new JavaScriptModuleRegistry(this.mModules);
    }
}
