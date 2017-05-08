// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.app.Activity;

public abstract class ReactContextBaseJavaModule extends BaseJavaModule
{
    private final ReactApplicationContext mReactApplicationContext;
    
    public ReactContextBaseJavaModule(final ReactApplicationContext mReactApplicationContext) {
        this.mReactApplicationContext = mReactApplicationContext;
    }
    
    protected final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }
    
    protected final ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }
}
