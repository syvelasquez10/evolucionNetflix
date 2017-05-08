// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import android.net.Uri;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.common.logging.FLog;
import android.content.Intent;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.devsupport.DevServerHelper$PackagerStatusCallback;
import android.os.AsyncTask;
import java.util.Iterator;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.JavaScriptModuleRegistry$Builder;
import com.facebook.react.cxxbridge.JSCJavaScriptExecutor$Factory;
import com.facebook.soloader.SoLoader;
import com.facebook.react.uimanager.AppRegistry;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.cxxbridge.Arguments;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.cxxbridge.UiThreadUtil;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.cxxbridge.JavaScriptExecutor;
import com.facebook.react.cxxbridge.JavaScriptExecutor$Factory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.common.ApplicationHolder;
import android.app.Application;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import java.util.Collection;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.devsupport.ReactInstanceDevCommandsHandler;
import com.facebook.react.bridge.ReactContext;
import android.app.Activity;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import java.util.List;
import android.content.Context;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

class ReactInstanceManager$2 implements DefaultHardwareBackBtnHandler
{
    final /* synthetic */ ReactInstanceManager this$0;
    
    ReactInstanceManager$2(final ReactInstanceManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void invokeDefaultOnBackPressed() {
        this.this$0.invokeDefaultOnBackPressed();
    }
}
