// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import android.app.Activity;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;

public abstract class ReactInstanceManager
{
    public static ReactInstanceManager$Builder builder() {
        return new ReactInstanceManager$Builder();
    }
    
    public abstract void attachMeasuredRootView(final ReactRootView p0);
    
    public abstract List<ViewManager> createAllViewManagers(final ReactApplicationContext p0);
    
    public abstract void createReactContextInBackground();
    
    public abstract ReactContext getCurrentReactContext();
    
    public abstract DevSupportManager getDevSupportManager();
    
    public abstract String getSourceUrl();
    
    public abstract boolean hasStartedCreatingInitialContext();
    
    @Deprecated
    public abstract void onHostPause();
    
    public abstract void onHostResume(final Activity p0, final DefaultHardwareBackBtnHandler p1);
}
