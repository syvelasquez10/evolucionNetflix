// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import java.lang.reflect.Method;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;

class JavaScriptModuleRegistry$JavaScriptModuleInvocationHandler implements InvocationHandler
{
    private final CatalystInstance mCatalystInstance;
    private final WeakReference<ExecutorToken> mExecutorToken;
    private final JavaScriptModuleRegistration mModuleRegistration;
    
    public JavaScriptModuleRegistry$JavaScriptModuleInvocationHandler(final ExecutorToken executorToken, final CatalystInstance mCatalystInstance, final JavaScriptModuleRegistration mModuleRegistration) {
        this.mExecutorToken = new WeakReference<ExecutorToken>(executorToken);
        this.mCatalystInstance = mCatalystInstance;
        this.mModuleRegistration = mModuleRegistration;
    }
    
    @Override
    public Object invoke(final Object o, final Method method, final Object[] array) {
        final ExecutorToken executorToken = this.mExecutorToken.get();
        if (executorToken == null) {
            FLog.w("React", "Dropping JS call, ExecutorToken went away...");
            return null;
        }
        WritableNativeArray fromJavaArgs;
        if (array != null) {
            fromJavaArgs = Arguments.fromJavaArgs(array);
        }
        else {
            fromJavaArgs = new WritableNativeArray();
        }
        this.mCatalystInstance.callFunction(executorToken, this.mModuleRegistration.getName(), method.getName(), fromJavaArgs);
        return null;
    }
}
