// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import android.content.Context;
import com.facebook.infer.annotation.Assertions;
import java.util.ArrayList;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.devsupport.RedBoxHandler;
import java.util.List;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import android.app.Activity;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import android.app.Application;

public class ReactInstanceManager$Builder
{
    protected Application mApplication;
    protected NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    protected Activity mCurrentActivity;
    protected DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    protected LifecycleState mInitialLifecycleState;
    protected String mJSBundleAssetUrl;
    protected JSBundleLoader mJSBundleLoader;
    protected JSCConfig mJSCConfig;
    protected String mJSMainModuleName;
    protected boolean mLazyNativeModulesEnabled;
    protected boolean mLazyViewManagersEnabled;
    protected NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    protected final List<ReactPackage> mPackages;
    protected RedBoxHandler mRedBoxHandler;
    protected UIImplementationProvider mUIImplementationProvider;
    protected boolean mUseDeveloperSupport;
    
    protected ReactInstanceManager$Builder() {
        this.mPackages = new ArrayList<ReactPackage>();
        this.mJSCConfig = JSCConfig.EMPTY;
    }
    
    public ReactInstanceManager$Builder addPackage(final ReactPackage reactPackage) {
        this.mPackages.add(reactPackage);
        return this;
    }
    
    public ReactInstanceManager build() {
        Assertions.assertNotNull(this.mApplication, "Application property has not been set with this builder");
        Assertions.assertCondition(this.mUseDeveloperSupport || this.mJSBundleAssetUrl != null || this.mJSBundleLoader != null, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        Assertions.assertCondition(this.mJSMainModuleName != null || this.mJSBundleAssetUrl != null || this.mJSBundleLoader != null, "Either MainModuleName or JS Bundle File needs to be provided");
        if (this.mUIImplementationProvider == null) {
            this.mUIImplementationProvider = new UIImplementationProvider();
        }
        final Application mApplication = this.mApplication;
        final Activity mCurrentActivity = this.mCurrentActivity;
        final DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        JSBundleLoader jsBundleLoader;
        if (this.mJSBundleLoader == null && this.mJSBundleAssetUrl != null) {
            jsBundleLoader = JSBundleLoader.createAssetLoader((Context)this.mApplication, this.mJSBundleAssetUrl);
        }
        else {
            jsBundleLoader = this.mJSBundleLoader;
        }
        return new XReactInstanceManagerImpl((Context)mApplication, mCurrentActivity, mDefaultHardwareBackBtnHandler, jsBundleLoader, this.mJSMainModuleName, this.mPackages, this.mUseDeveloperSupport, this.mBridgeIdleDebugListener, Assertions.assertNotNull(this.mInitialLifecycleState, "Initial lifecycle state was not set"), this.mUIImplementationProvider, this.mNativeModuleCallExceptionHandler, this.mJSCConfig, this.mRedBoxHandler, this.mLazyNativeModulesEnabled, this.mLazyViewManagersEnabled);
    }
    
    public ReactInstanceManager$Builder setApplication(final Application mApplication) {
        this.mApplication = mApplication;
        return this;
    }
    
    public ReactInstanceManager$Builder setBundleAssetName(String string) {
        if (string == null) {
            string = null;
        }
        else {
            string = "assets://" + string;
        }
        this.mJSBundleAssetUrl = string;
        this.mJSBundleLoader = null;
        return this;
    }
    
    public ReactInstanceManager$Builder setInitialLifecycleState(final LifecycleState mInitialLifecycleState) {
        this.mInitialLifecycleState = mInitialLifecycleState;
        return this;
    }
    
    public ReactInstanceManager$Builder setJSMainModuleName(final String mjsMainModuleName) {
        this.mJSMainModuleName = mjsMainModuleName;
        return this;
    }
    
    public ReactInstanceManager$Builder setUseDeveloperSupport(final boolean mUseDeveloperSupport) {
        this.mUseDeveloperSupport = mUseDeveloperSupport;
        return this;
    }
}
