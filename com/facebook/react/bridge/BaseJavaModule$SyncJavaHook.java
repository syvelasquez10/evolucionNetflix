// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.HashMap;
import com.facebook.systrace.Systrace;
import java.util.Map;
import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Method;

public class BaseJavaModule$SyncJavaHook
{
    private Method mMethod;
    private final String mSignature;
    final /* synthetic */ BaseJavaModule this$0;
    
    public BaseJavaModule$SyncJavaHook(final BaseJavaModule this$0, final Method mMethod) {
        this.this$0 = this$0;
        (this.mMethod = mMethod).setAccessible(true);
        this.mSignature = this.buildSignature(mMethod);
    }
    
    private String buildSignature(final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final StringBuilder sb = new StringBuilder(parameterTypes.length + 2);
        sb.append(returnTypeToChar(method.getReturnType()));
        sb.append('.');
        for (int i = 0; i < parameterTypes.length; ++i) {
            final Class<?> clazz = parameterTypes[i];
            if (clazz == ExecutorToken.class) {
                if (!this.this$0.supportsWebWorkers()) {
                    throw new RuntimeException("Module " + this.this$0 + " doesn't support web workers, but " + this.mMethod.getName() + " takes an ExecutorToken.");
                }
            }
            else if (clazz == Promise.class) {
                Assertions.assertCondition(i == parameterTypes.length - 1, "Promise must be used as last parameter only");
            }
            sb.append(paramTypeToChar(clazz));
        }
        return sb.toString();
    }
    
    public Method getMethod() {
        return this.mMethod;
    }
    
    public String getSignature() {
        return this.mSignature;
    }
}
