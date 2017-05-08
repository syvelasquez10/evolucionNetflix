// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class HeadlessJsTaskSupportModule extends ReactContextBaseJavaModule
{
    protected static final String MODULE_NAME = "HeadlessJsTaskSupport";
    
    public HeadlessJsTaskSupportModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @Override
    public String getName() {
        return "HeadlessJsTaskSupport";
    }
    
    @ReactMethod
    public void notifyTaskFinished(final int n) {
        final HeadlessJsTaskContext instance = HeadlessJsTaskContext.getInstance(this.getReactApplicationContext());
        if (instance.isTaskRunning(n)) {
            instance.finishTask(n);
            return;
        }
        FLog.w(HeadlessJsTaskSupportModule.class, "Tried to finish non-active task with id %d. Did it time out?", n);
    }
}
