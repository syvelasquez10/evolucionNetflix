// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.devsupport.RedBoxHandler;
import java.util.List;
import java.util.Iterator;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.LifecycleState;
import android.app.Application;

public abstract class ReactNativeHost
{
    private final Application mApplication;
    private ReactInstanceManager mReactInstanceManager;
    
    protected ReactNativeHost(final Application mApplication) {
        this.mApplication = mApplication;
    }
    
    public void clear() {
        if (this.mReactInstanceManager != null) {
            this.mReactInstanceManager.destroy();
            this.mReactInstanceManager = null;
        }
    }
    
    protected ReactInstanceManager createReactInstanceManager() {
        final ReactInstanceManagerBuilder setInitialLifecycleState = ReactInstanceManager.builder().setApplication(this.mApplication).setJSMainModuleName(this.getJSMainModuleName()).setUseDeveloperSupport(this.getUseDeveloperSupport()).setRedBoxHandler(this.getRedBoxHandler()).setUIImplementationProvider(this.getUIImplementationProvider()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE);
        final Iterator<ReactPackage> iterator = this.getPackages().iterator();
        while (iterator.hasNext()) {
            setInitialLifecycleState.addPackage(iterator.next());
        }
        final String jsBundleFile = this.getJSBundleFile();
        if (jsBundleFile != null) {
            setInitialLifecycleState.setJSBundleFile(jsBundleFile);
        }
        else {
            setInitialLifecycleState.setBundleAssetName(Assertions.assertNotNull(this.getBundleAssetName()));
        }
        return setInitialLifecycleState.build();
    }
    
    protected final Application getApplication() {
        return this.mApplication;
    }
    
    protected String getBundleAssetName() {
        return "index.android.bundle";
    }
    
    protected String getJSBundleFile() {
        return null;
    }
    
    protected String getJSMainModuleName() {
        return "index.android";
    }
    
    protected abstract List<ReactPackage> getPackages();
    
    public ReactInstanceManager getReactInstanceManager() {
        if (this.mReactInstanceManager == null) {
            this.mReactInstanceManager = this.createReactInstanceManager();
        }
        return this.mReactInstanceManager;
    }
    
    protected RedBoxHandler getRedBoxHandler() {
        return null;
    }
    
    protected UIImplementationProvider getUIImplementationProvider() {
        return new UIImplementationProvider();
    }
    
    public abstract boolean getUseDeveloperSupport();
    
    public boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }
}
