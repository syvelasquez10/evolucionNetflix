// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.ReactMarker;
import com.facebook.systrace.Systrace;
import java.util.Collection;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.infer.annotation.Assertions;
import java.util.Iterator;
import android.util.Pair;
import java.util.HashMap;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.bridge.ModuleSpec;
import java.util.List;
import com.facebook.react.bridge.NativeModule;
import java.util.Map;
import com.facebook.react.bridge.OnBatchCompleteListener;
import java.util.ArrayList;

public class NativeModuleRegistry
{
    private final ArrayList<OnBatchCompleteListener> mBatchCompleteListenerModules;
    private final Map<Class<? extends NativeModule>, ModuleHolder> mModules;
    
    public NativeModuleRegistry(final List<ModuleSpec> list, final Map<Class, ReactModuleInfo> map) {
        final HashMap<Object, Pair> hashMap = new HashMap<Object, Pair>();
        for (final ModuleSpec moduleSpec : list) {
            final Class<? extends NativeModule> type = moduleSpec.getType();
            final ModuleHolder moduleHolder = new ModuleHolder(type, map.get(type), moduleSpec.getProvider());
            final String name = moduleHolder.getInfo().name();
            Class clazz;
            if (hashMap.containsKey(name)) {
                clazz = (Class)hashMap.get(name).first;
            }
            else {
                clazz = null;
            }
            if (clazz != null && !moduleHolder.getInfo().canOverrideExistingModule()) {
                throw new IllegalStateException("Native module " + type.getSimpleName() + " tried to override " + clazz.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
            }
            hashMap.put(name, new Pair((Object)type, (Object)moduleHolder));
        }
        this.mModules = new HashMap<Class<? extends NativeModule>, ModuleHolder>();
        for (final Pair pair : hashMap.values()) {
            this.mModules.put((Class<? extends NativeModule>)pair.first, (ModuleHolder)pair.second);
        }
        this.mBatchCompleteListenerModules = new ArrayList<OnBatchCompleteListener>();
        for (final Class<? extends NativeModule> clazz2 : this.mModules.keySet()) {
            if (OnBatchCompleteListener.class.isAssignableFrom(clazz2)) {
                this.mBatchCompleteListenerModules.add(new NativeModuleRegistry$1(this, this.mModules.get(clazz2)));
            }
        }
    }
    
    public <T extends NativeModule> T getModule(final Class<T> clazz) {
        return (T)Assertions.assertNotNull(this.mModules.get(clazz)).getModule();
    }
    
    ModuleRegistryHolder getModuleRegistryHolder(final CatalystInstanceImpl catalystInstanceImpl) {
        final ArrayList<JavaModuleWrapper> list = new ArrayList<JavaModuleWrapper>();
        final ArrayList<CxxModuleWrapper> list2 = new ArrayList<CxxModuleWrapper>();
        for (final Map.Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            final Class<? extends NativeModule> clazz = entry.getKey();
            final ModuleHolder moduleHolder = entry.getValue();
            if (BaseJavaModule.class.isAssignableFrom(clazz)) {
                list.add(new JavaModuleWrapper(catalystInstanceImpl, moduleHolder));
            }
            else {
                if (!CxxModuleWrapper.class.isAssignableFrom(clazz)) {
                    throw new IllegalArgumentException("Unknown module type " + clazz);
                }
                list2.add((CxxModuleWrapper)moduleHolder.getModule());
            }
        }
        return new ModuleRegistryHolder(catalystInstanceImpl, list, list2);
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
