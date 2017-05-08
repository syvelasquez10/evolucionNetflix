// 
// Decompiled by Procyon v0.5.30
// 

package com.BV.LinearGradient;

import java.util.Arrays;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Collections;
import com.facebook.react.bridge.JavaScriptModule;
import java.util.List;
import com.facebook.react.ReactPackage;

public class LinearGradientPackage implements ReactPackage
{
    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
    
    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
    
    @Override
    public List<ViewManager> createViewManagers(final ReactApplicationContext reactApplicationContext) {
        return (List<ViewManager>)Arrays.asList(new LinearGradientManager());
    }
}
