// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.Timing;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.debug.AnimationsDebugModule;
import javax.inject.Provider;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.bridge.ModuleSpec;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.uimanager.AppRegistry;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.modules.core.JSTimersExecution;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.JavaScriptModule;
import java.util.List;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

class CoreModulesPackage extends LazyReactPackage implements ReactPackageLogger
{
    private final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;
    private final boolean mLazyViewManagersEnabled;
    private final ReactInstanceManager mReactInstanceManager;
    private final UIImplementationProvider mUIImplementationProvider;
    
    CoreModulesPackage(final ReactInstanceManager mReactInstanceManager, final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler, final UIImplementationProvider muiImplementationProvider, final boolean mLazyViewManagersEnabled) {
        this.mReactInstanceManager = mReactInstanceManager;
        this.mHardwareBackBtnHandler = mHardwareBackBtnHandler;
        this.mUIImplementationProvider = muiImplementationProvider;
        this.mLazyViewManagersEnabled = mLazyViewManagersEnabled;
    }
    
    private UIManagerModule createUIManager(final ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker("CREATE_UI_MANAGER_MODULE_START");
        Systrace.beginSection(0L, "createUIManagerModule");
        try {
            return new UIManagerModule(reactApplicationContext, this.mReactInstanceManager.createAllViewManagers(reactApplicationContext), this.mUIImplementationProvider, this.mLazyViewManagersEnabled);
        }
        finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker("CREATE_UI_MANAGER_MODULE_END");
        }
    }
    
    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return new ArrayList<Class<? extends JavaScriptModule>>((Collection<? extends Class<? extends JavaScriptModule>>)Arrays.asList(DeviceEventManagerModule$RCTDeviceEventEmitter.class, JSTimersExecution.class, RCTEventEmitter.class, RCTNativeAppEventEmitter.class, AppRegistry.class, com.facebook.react.bridge.Systrace.class, HMRClient.class));
    }
    
    @Override
    public void endProcessPackage() {
        ReactMarker.logMarker("PROCESS_CORE_REACT_PACKAGE_END");
    }
    
    @Override
    public List<ModuleSpec> getNativeModules(final ReactApplicationContext reactApplicationContext) {
        final ArrayList<ModuleSpec> list = new ArrayList<ModuleSpec>();
        list.add(new ModuleSpec(AndroidInfoModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$1(this)));
        list.add(new ModuleSpec(AnimationsDebugModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$2(this, reactApplicationContext)));
        list.add(new ModuleSpec(DeviceEventManagerModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$3(this, reactApplicationContext)));
        list.add(new ModuleSpec(ExceptionsManagerModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$4(this)));
        list.add(new ModuleSpec(HeadlessJsTaskSupportModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$5(this, reactApplicationContext)));
        list.add(new ModuleSpec(SourceCodeModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$6(this, reactApplicationContext)));
        list.add(new ModuleSpec(Timing.class, (Provider<? extends NativeModule>)new CoreModulesPackage$7(this, reactApplicationContext)));
        list.add(new ModuleSpec(UIManagerModule.class, (Provider<? extends NativeModule>)new CoreModulesPackage$8(this, reactApplicationContext)));
        return list;
    }
    
    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        ReactMarker.logMarker("CORE_REACT_PACKAGE_GET_REACT_MODULE_INFO_PROVIDER_START");
        final ReactModuleInfoProvider reactModuleInfoProviderViaReflection = LazyReactPackage.getReactModuleInfoProviderViaReflection(this);
        ReactMarker.logMarker("CORE_REACT_PACKAGE_GET_REACT_MODULE_INFO_PROVIDER_END");
        return reactModuleInfoProviderViaReflection;
    }
    
    @Override
    public void startProcessPackage() {
        ReactMarker.logMarker("PROCESS_CORE_REACT_PACKAGE_START");
    }
}
