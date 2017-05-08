// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import android.net.Uri;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.content.Intent;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.devsupport.DevServerHelper$PackagerStatusCallback;
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
import android.app.Activity;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import java.util.List;
import com.facebook.react.bridge.ReactContext;
import com.facebook.common.logging.FLog;
import android.content.Context;
import com.facebook.infer.annotation.Assertions;
import android.os.Process;
import com.facebook.react.bridge.ReactApplicationContext;
import android.os.AsyncTask;

final class ReactInstanceManager$ReactContextInitAsyncTask extends AsyncTask<ReactInstanceManager$ReactContextInitParams, Void, ReactInstanceManager$Result<ReactApplicationContext>>
{
    final /* synthetic */ ReactInstanceManager this$0;
    
    private ReactInstanceManager$ReactContextInitAsyncTask(final ReactInstanceManager this$0) {
        this.this$0 = this$0;
    }
    
    protected ReactInstanceManager$Result<ReactApplicationContext> doInBackground(final ReactInstanceManager$ReactContextInitParams... array) {
        final boolean b = false;
        Process.setThreadPriority(0);
        boolean b2 = b;
        if (array != null) {
            b2 = b;
            if (array.length > 0) {
                b2 = b;
                if (array[0] != null) {
                    b2 = true;
                }
            }
        }
        Assertions.assertCondition(b2);
        try {
            return ReactInstanceManager$Result.of(this.this$0.createReactContext(array[0].getJsExecutorFactory().create(), array[0].getJsBundleLoader()));
        }
        catch (Exception ex) {
            return ReactInstanceManager$Result.of(ex);
        }
    }
    
    protected void onCancelled(final ReactInstanceManager$Result<ReactApplicationContext> reactInstanceManager$Result) {
        try {
            this.this$0.mMemoryPressureRouter.destroy((Context)reactInstanceManager$Result.get());
        }
        catch (Exception ex) {
            FLog.w("React", "Caught exception after cancelling react context init", ex);
        }
        finally {
            this.this$0.mReactContextInitAsyncTask = null;
        }
    }
    
    protected void onPostExecute(final ReactInstanceManager$Result<ReactApplicationContext> reactInstanceManager$Result) {
        while (true) {
            try {
                this.this$0.setupReactContext(reactInstanceManager$Result.get());
                this.this$0.mReactContextInitAsyncTask = null;
                if (this.this$0.mPendingReactContextInitParams != null) {
                    this.this$0.recreateReactContextInBackground(this.this$0.mPendingReactContextInitParams.getJsExecutorFactory(), this.this$0.mPendingReactContextInitParams.getJsBundleLoader());
                    this.this$0.mPendingReactContextInitParams = null;
                }
            }
            catch (Exception ex) {
                this.this$0.mDevSupportManager.handleException(ex);
                this.this$0.mReactContextInitAsyncTask = null;
                continue;
            }
            finally {
                this.this$0.mReactContextInitAsyncTask = null;
            }
            break;
        }
    }
    
    protected void onPreExecute() {
        if (this.this$0.mCurrentReactContext != null) {
            this.this$0.tearDownReactContext(this.this$0.mCurrentReactContext);
            this.this$0.mCurrentReactContext = null;
        }
    }
}
