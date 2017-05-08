// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;

public interface DevSupportManager extends NativeModuleCallExceptionHandler
{
    DeveloperSettings getDevSettings();
    
    boolean getDevSupportEnabled();
    
    String getDownloadedJSBundleFile();
    
    String getSourceUrl();
    
    void handleReloadJS();
    
    boolean hasUpToDateJSBundleInCache();
    
    void hideRedboxDialog();
    
    void isPackagerRunning(final DevServerHelper$PackagerStatusCallback p0);
    
    void onNewReactContextCreated(final ReactContext p0);
    
    void onReactInstanceDestroyed(final ReactContext p0);
    
    void setDevSupportEnabled(final boolean p0);
    
    void showDevOptionsDialog();
    
    void showNewJSError(final String p0, final ReadableArray p1, final int p2);
    
    void updateJSError(final String p0, final ReadableArray p1, final int p2);
}
