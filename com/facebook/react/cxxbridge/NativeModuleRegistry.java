// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.ReactMarker;
import com.facebook.systrace.Systrace;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.CatalystInstance;
import java.util.Iterator;
import java.util.Collection;
import com.facebook.react.bridge.NativeModule;
import java.util.Map;
import com.facebook.react.bridge.OnBatchCompleteListener;
import java.util.ArrayList;

public class NativeModuleRegistry
{
    private final ArrayList<OnBatchCompleteListener> mBatchCompleteListenerModules;
    private final Map<Class<? extends NativeModule>, ModuleHolder> mModules;
    
    public NativeModuleRegistry(final Map<Class<? extends NativeModule>, ModuleHolder> mModules, final ArrayList<OnBatchCompleteListener> mBatchCompleteListenerModules) {
        this.mModules = mModules;
        this.mBatchCompleteListenerModules = mBatchCompleteListenerModules;
    }
    
    Collection<CxxModuleWrapper> getCxxModules() {
        final ArrayList<CxxModuleWrapper> list = new ArrayList<CxxModuleWrapper>();
        for (final Map.Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            if (CxxModuleWrapper.class.isAssignableFrom(entry.getKey())) {
                list.add((CxxModuleWrapper)entry.getValue().getModule());
            }
        }
        return list;
    }
    
    Collection<JavaModuleWrapper> getJavaModules(final CatalystInstanceImpl catalystInstanceImpl) {
        final ArrayList<JavaModuleWrapper> list = new ArrayList<JavaModuleWrapper>();
        for (final Map.Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            if (!CxxModuleWrapper.class.isAssignableFrom(entry.getKey())) {
                list.add(new JavaModuleWrapper(catalystInstanceImpl, entry.getValue()));
            }
        }
        return list;
    }
    
    public <T extends NativeModule> T getModule(final Class<T> clazz) {
        return (T)Assertions.assertNotNull(this.mModules.get(clazz)).getModule();
    }
    
    void notifyCatalystInstanceDestroy() {
        UiThreadUtil.assertOnUiThread();
        Systrace.beginSection(0L, "NativeModuleRegistry_notifyCatalystInstanceDestroy");
        try {
            final Iterator<ModuleHolder> iterator = this.mModules.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().destroy();
            }
        }
        finally {
            Systrace.endSection(0L);
        }
        Systrace.endSection(0L);
    }
    
    void notifyCatalystInstanceInitialized() {
        UiThreadUtil.assertOnUiThread();
        ReactMarker.logMarker("NativeModule_start");
        Systrace.beginSection(0L, "NativeModuleRegistry_notifyCatalystInstanceInitialized");
        try {
            final Iterator<ModuleHolder> iterator = this.mModules.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().initialize();
            }
        }
        finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker("NativeModule_end");
        }
        Systrace.endSection(0L);
        ReactMarker.logMarker("NativeModule_end");
    }
    
    public void onBatchComplete() {
        for (int i = 0; i < this.mBatchCompleteListenerModules.size(); ++i) {
            this.mBatchCompleteListenerModules.get(i).onBatchComplete();
        }
    }
}
