// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.JavaScriptModule;
import java.util.List;

public interface ReactPackage
{
    List<Class<? extends JavaScriptModule>> createJSModules();
    
    List<NativeModule> createNativeModules(final ReactApplicationContext p0);
    
    List<ViewManager> createViewManagers(final ReactApplicationContext p0);
}
