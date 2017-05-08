// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.app.Activity;
import com.facebook.react.bridge.LifecycleEventListener;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;

public class ThemedReactContext extends ReactContext
{
    private final ReactApplicationContext mReactApplicationContext;
    
    public ThemedReactContext(final ReactApplicationContext mReactApplicationContext, final Context context) {
        super(context);
        this.initializeWithInstance(mReactApplicationContext.getCatalystInstance());
        this.mReactApplicationContext = mReactApplicationContext;
    }
    
    @Override
    public void addLifecycleEventListener(final LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.addLifecycleEventListener(lifecycleEventListener);
    }
    
    @Override
    public Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }
    
    @Override
    public void removeLifecycleEventListener(final LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.removeLifecycleEventListener(lifecycleEventListener);
    }
}
