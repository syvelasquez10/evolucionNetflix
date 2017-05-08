// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public class JavaScriptModuleRegistration
{
    private final Class<? extends JavaScriptModule> mModuleInterface;
    private String mName;
    
    public JavaScriptModuleRegistration(final Class<? extends JavaScriptModule> mModuleInterface) {
        this.mModuleInterface = mModuleInterface;
    }
    
    public Class<? extends JavaScriptModule> getModuleInterface() {
        return this.mModuleInterface;
    }
    
    public String getName() {
        if (this.mName == null) {
            final String simpleName = this.mModuleInterface.getSimpleName();
            final int lastIndex = simpleName.lastIndexOf(36);
            String substring = simpleName;
            if (lastIndex != -1) {
                substring = simpleName.substring(lastIndex + 1);
            }
            this.mName = substring;
        }
        return this.mName;
    }
}
