// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import java.util.Collections;
import com.facebook.react.uimanager.ViewManager;
import java.util.Iterator;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.ModuleSpec;
import java.util.ArrayList;
import com.facebook.react.bridge.NativeModule;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfoProvider;

public abstract class LazyReactPackage implements ReactPackage
{
    public static ReactModuleInfoProvider getReactModuleInfoProviderViaReflection(final LazyReactPackage lazyReactPackage) {
        Class<?> forName;
        try {
            forName = Class.forName(lazyReactPackage.getClass().getCanonicalName() + "$$ReactModuleInfoProvider");
            if (forName == null) {
                throw new RuntimeException("ReactModuleInfoProvider class for " + lazyReactPackage.getClass().getCanonicalName() + " not found.");
            }
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            return (ReactModuleInfoProvider)forName.newInstance();
        }
        catch (InstantiationException ex2) {
            throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), ex3);
        }
    }
    
    @Override
    public final List<NativeModule> createNativeModules(final ReactApplicationContext reactApplicationContext) {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<NativeModule>();
        for (final ModuleSpec moduleSpec : this.getNativeModules(reactApplicationContext)) {
            SystraceMessage.beginSection(0L, "createNativeModule").arg("module", moduleSpec.getType()).flush();
            try {
                list.add(moduleSpec.getProvider().get());
                continue;
            }
            finally {
                Systrace.endSection(0L);
            }
            break;
        }
        return (List<NativeModule>)list;
    }
    
    @Override
    public List<ViewManager> createViewManagers(final ReactApplicationContext reactApplicationContext) {
        final List<ModuleSpec> viewManagers = this.getViewManagers(reactApplicationContext);
        if (viewManagers == null || viewManagers.isEmpty()) {
            return (List<ViewManager>)Collections.emptyList();
        }
        final ArrayList<ViewManager> list = new ArrayList<ViewManager>();
        final Iterator<ModuleSpec> iterator = viewManagers.iterator();
        while (iterator.hasNext()) {
            list.add((ViewManager)iterator.next().getProvider().get());
        }
        return (List<ViewManager>)list;
    }
    
    public abstract List<ModuleSpec> getNativeModules(final ReactApplicationContext p0);
    
    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();
    
    public List<ModuleSpec> getViewManagers(final ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
