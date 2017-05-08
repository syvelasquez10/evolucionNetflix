// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.Timing;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.debug.AnimationsDebugModule;
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
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;

class CoreModulesPackage$6 implements Provider<NativeModule>
{
    final /* synthetic */ CoreModulesPackage this$0;
    
    CoreModulesPackage$6(final CoreModulesPackage this$0) {
        this.this$0 = this$0;
    }
    
    public NativeModule get() {
        return new SourceCodeModule(this.this$0.mReactInstanceManager.getSourceUrl());
    }
}
