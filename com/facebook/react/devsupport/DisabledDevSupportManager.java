// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;

public class DisabledDevSupportManager implements DevSupportManager
{
    private final DefaultNativeModuleCallExceptionHandler mDefaultNativeModuleCallExceptionHandler;
    
    public DisabledDevSupportManager() {
        this.mDefaultNativeModuleCallExceptionHandler = new DefaultNativeModuleCallExceptionHandler();
    }
    
    @Override
    public DeveloperSettings getDevSettings() {
        return null;
    }
    
    @Override
    public boolean getDevSupportEnabled() {
        return false;
    }
    
    @Override
    public String getDownloadedJSBundleFile() {
        return null;
    }
    
    @Override
    public String getSourceUrl() {
        return null;
    }
    
    @Override
    public void handleException(final Exception ex) {
        this.mDefaultNativeModuleCallExceptionHandler.handleException(ex);
    }
    
    @Override
    public void handleReloadJS() {
    }
    
    @Override
    public boolean hasUpToDateJSBundleInCache() {
        return false;
    }
    
    @Override
    public void hideRedboxDialog() {
    }
    
    @Override
    public void isPackagerRunning(final DevServerHelper$PackagerStatusCallback devServerHelper$PackagerStatusCallback) {
    }
    
    @Override
    public void onNewReactContextCreated(final ReactContext reactContext) {
    }
    
    @Override
    public void onReactInstanceDestroyed(final ReactContext reactContext) {
    }
    
    @Override
    public void setDevSupportEnabled(final boolean b) {
    }
    
    @Override
    public void showNewJSError(final String s, final ReadableArray readableArray, final int n) {
    }
    
    @Override
    public void updateJSError(final String s, final ReadableArray readableArray, final int n) {
    }
}
