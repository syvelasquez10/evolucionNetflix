// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import java.util.List;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.bridge.ModuleSpec;
import java.util.Iterator;
import com.facebook.react.bridge.OnBatchCompleteListener;
import java.util.ArrayList;
import com.facebook.react.cxxbridge.NativeModuleRegistry;
import com.facebook.react.cxxbridge.LegacyModuleInfo;
import java.util.HashMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.cxxbridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import java.util.Map;

public class NativeModuleRegistryBuilder
{
    private final boolean mLazyNativeModulesEnabled;
    private final Map<Class<? extends NativeModule>, ModuleHolder> mModules;
    private final ReactApplicationContext mReactApplicationContext;
    private final Map<String, Class<? extends NativeModule>> namesToType;
    
    public NativeModuleRegistryBuilder(final ReactApplicationContext mReactApplicationContext, final boolean mLazyNativeModulesEnabled) {
        this.mModules = new HashMap<Class<? extends NativeModule>, ModuleHolder>();
        this.namesToType = new HashMap<String, Class<? extends NativeModule>>();
        this.mReactApplicationContext = mReactApplicationContext;
        this.mLazyNativeModulesEnabled = mLazyNativeModulesEnabled;
    }
    
    public void addNativeModule(final NativeModule nativeModule) {
        final String name = nativeModule.getName();
        final Class<? extends NativeModule> class1 = nativeModule.getClass();
        if (this.namesToType.containsKey(name)) {
            final Class<? extends NativeModule> clazz = this.namesToType.get(name);
            if (!nativeModule.canOverrideExistingModule()) {
                throw new IllegalStateException("Native module " + class1.getSimpleName() + " tried to override " + clazz.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
            }
            this.mModules.remove(clazz);
        }
        this.namesToType.put(name, class1);
        this.mModules.put(class1, new ModuleHolder(new LegacyModuleInfo(class1, nativeModule), nativeModule));
    }
    
    public NativeModuleRegistry build() {
        final ArrayList<OnBatchCompleteListener> list = new ArrayList<OnBatchCompleteListener>();
        for (final Map.Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            if (OnBatchCompleteListener.class.isAssignableFrom(entry.getKey())) {
                list.add(new NativeModuleRegistryBuilder$1(this, entry.getValue()));
            }
        }
        return new NativeModuleRegistry(this.mModules, list);
    }
    
    public void processPackage(final ReactPackage reactPackage) {
        if (this.mLazyNativeModulesEnabled) {
            if (!(reactPackage instanceof LazyReactPackage)) {
                throw new IllegalStateException("Lazy native modules requires all ReactPackage to inherit from LazyReactPackage");
            }
            final LazyReactPackage lazyReactPackage = (LazyReactPackage)reactPackage;
            final List<ModuleSpec> nativeModules = lazyReactPackage.getNativeModules(this.mReactApplicationContext);
            final Map<Class, ReactModuleInfo> reactModuleInfos = lazyReactPackage.getReactModuleInfoProvider().getReactModuleInfos();
            for (final ModuleSpec moduleSpec : nativeModules) {
                final Class<? extends NativeModule> type = moduleSpec.getType();
                final ReactModuleInfo reactModuleInfo = reactModuleInfos.get(type);
                ModuleHolder moduleHolder;
                if (reactModuleInfo == null) {
                    if (BaseJavaModule.class.isAssignableFrom(type)) {
                        throw new IllegalStateException("Native Java module " + type.getSimpleName() + " should be annotated with @ReactModule and added to a @ReactModuleList.");
                    }
                    final NativeModule nativeModule = (NativeModule)moduleSpec.getProvider().get();
                    moduleHolder = new ModuleHolder(new LegacyModuleInfo(type, nativeModule), nativeModule);
                }
                else {
                    moduleHolder = new ModuleHolder(reactModuleInfo, moduleSpec.getProvider());
                }
                final String name = moduleHolder.getInfo().name();
                if (this.namesToType.containsKey(name)) {
                    final Class<? extends NativeModule> clazz = this.namesToType.get(name);
                    if (!moduleHolder.getInfo().canOverrideExistingModule()) {
                        throw new IllegalStateException("Native module " + type.getSimpleName() + " tried to override " + clazz.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
                    }
                    this.mModules.remove(clazz);
                }
                this.namesToType.put(name, type);
                this.mModules.put(type, moduleHolder);
            }
        }
        else {
            FLog.d("React", reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
            final Iterator<NativeModule> iterator2 = reactPackage.createNativeModules(this.mReactApplicationContext).iterator();
            while (iterator2.hasNext()) {
                this.addNativeModule(iterator2.next());
            }
        }
    }
}
