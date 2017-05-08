// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Method;

public class BaseJavaModule$JavaMethod implements NativeModule$NativeMethod
{
    private final BaseJavaModule$ArgumentExtractor[] mArgumentExtractors;
    private final Object[] mArguments;
    private final int mJSArgumentsNeeded;
    private Method mMethod;
    private final String mSignature;
    private final String mTraceName;
    private String mType;
    final /* synthetic */ BaseJavaModule this$0;
    
    public BaseJavaModule$JavaMethod(final BaseJavaModule this$0, final Method mMethod, final boolean b) {
        this.this$0 = this$0;
        this.mType = "async";
        (this.mMethod = mMethod).setAccessible(true);
        if (b) {
            this.mType = "sync";
        }
        final Class<?>[] parameterTypes = mMethod.getParameterTypes();
        this.mArgumentExtractors = this.buildArgumentExtractors(parameterTypes);
        this.mSignature = this.buildSignature(this.mMethod, parameterTypes, b);
        this.mArguments = new Object[parameterTypes.length];
        this.mJSArgumentsNeeded = this.calculateJSArgumentsNeeded();
        this.mTraceName = this$0.getName() + "." + this.mMethod.getName();
    }
    
    private BaseJavaModule$ArgumentExtractor[] buildArgumentExtractors(final Class[] array) {
        int n;
        if (this.this$0.supportsWebWorkers()) {
            if (array[0] != ExecutorToken.class) {
                throw new RuntimeException("Module " + this.this$0 + " supports web workers, but " + this.mMethod.getName() + "does not take an ExecutorToken as its first parameter.");
            }
            n = 1;
        }
        else {
            n = 0;
        }
        final BaseJavaModule$ArgumentExtractor[] array2 = new BaseJavaModule$ArgumentExtractor[array.length - n];
        for (int i = 0; i < array.length - n; i += array2[i].getJSArgumentsNeeded()) {
            final int n2 = i + n;
            final Class clazz = array[n2];
            if (clazz == Boolean.class || clazz == Boolean.TYPE) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_BOOLEAN;
            }
            else if (clazz == Integer.class || clazz == Integer.TYPE) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_INTEGER;
            }
            else if (clazz == Double.class || clazz == Double.TYPE) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_DOUBLE;
            }
            else if (clazz == Float.class || clazz == Float.TYPE) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_FLOAT;
            }
            else if (clazz == String.class) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_STRING;
            }
            else if (clazz == Callback.class) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_CALLBACK;
            }
            else if (clazz == Promise.class) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_PROMISE;
                Assertions.assertCondition(n2 == array.length - 1, "Promise must be used as last parameter only");
                this.mType = "promise";
            }
            else if (clazz == ReadableMap.class) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_MAP;
            }
            else if (clazz == ReadableArray.class) {
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_ARRAY;
            }
            else {
                if (clazz != Dynamic.class) {
                    throw new RuntimeException("Got unknown argument class: " + clazz.getSimpleName());
                }
                array2[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_DYNAMIC;
            }
        }
        return array2;
    }
    
    private String buildSignature(final Method method, final Class[] array, final boolean b) {
        final StringBuilder sb = new StringBuilder(array.length + 2);
        if (b) {
            sb.append(returnTypeToChar(method.getReturnType()));
            sb.append('.');
        }
        else {
            sb.append("v.");
        }
        for (int i = 0; i < array.length; ++i) {
            final Class clazz = array[i];
            if (clazz == ExecutorToken.class) {
                if (!this.this$0.supportsWebWorkers()) {
                    throw new RuntimeException("Module " + this.this$0 + " doesn't support web workers, but " + this.mMethod.getName() + " takes an ExecutorToken.");
                }
            }
            else if (clazz == Promise.class) {
                Assertions.assertCondition(i == array.length - 1, "Promise must be used as last parameter only");
                if (!b) {
                    this.mType = "promise";
                }
            }
            sb.append(paramTypeToChar(clazz));
        }
        if (this.this$0.supportsWebWorkers() && sb.charAt(2) != 'T') {
            throw new RuntimeException("Module " + this.this$0 + " supports web workers, but " + this.mMethod.getName() + "does not take an ExecutorToken as its first parameter.");
        }
        return sb.toString();
    }
    
    private int calculateJSArgumentsNeeded() {
        int i = 0;
        final BaseJavaModule$ArgumentExtractor[] mArgumentExtractors = this.mArgumentExtractors;
        final int length = mArgumentExtractors.length;
        int n = 0;
        while (i < length) {
            n += mArgumentExtractors[i].getJSArgumentsNeeded();
            ++i;
        }
        return n;
    }
    
    private String getAffectedRange(final int n, final int n2) {
        if (n2 > 1) {
            return "" + n + "-" + (n + n2 - 1);
        }
        return "" + n;
    }
    
    public Method getMethod() {
        return this.mMethod;
    }
    
    public String getSignature() {
        return this.mSignature;
    }
    
    @Override
    public String getType() {
        return this.mType;
    }
    
    @Override
    public void invoke(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray) {
        int n = 0;
        SystraceMessage.beginSection(0L, "callJavaModuleMethod").arg("method", this.mTraceName).flush();
        try {
            if (this.mJSArgumentsNeeded != readableNativeArray.size()) {
                throw new NativeArgumentsParseException(this.this$0.getName() + "." + this.mMethod.getName() + " got " + readableNativeArray.size() + " arguments, expected " + this.mJSArgumentsNeeded);
            }
        }
        finally {
            Systrace.endSection(0L);
        }
        while (true) {
            Label_0475: {
                if (!this.this$0.supportsWebWorkers()) {
                    break Label_0475;
                }
                this.mArguments[0] = executorToken;
                final int n2 = 1;
                int i = 0;
                try {
                    while (i < this.mArgumentExtractors.length) {
                        final CatalystInstance catalystInstance2;
                        this.mArguments[i + n2] = this.mArgumentExtractors[i].extractArgument(catalystInstance2, executorToken, readableNativeArray, n);
                        n += this.mArgumentExtractors[i].getJSArgumentsNeeded();
                        ++i;
                    }
                }
                catch (UnexpectedNativeTypeException ex) {
                    throw new NativeArgumentsParseException(ex.getMessage() + " (constructing arguments for " + this.this$0.getName() + "." + this.mMethod.getName() + " at argument index " + this.getAffectedRange(n, this.mArgumentExtractors[i].getJSArgumentsNeeded()) + ")", ex);
                }
                try {
                    this.mMethod.invoke(this.this$0, this.mArguments);
                    Systrace.endSection(0L);
                    return;
                }
                catch (IllegalArgumentException ex2) {
                    throw new RuntimeException("Could not invoke " + this.this$0.getName() + "." + this.mMethod.getName(), ex2);
                }
                catch (IllegalAccessException ex3) {
                    throw new RuntimeException("Could not invoke " + this.this$0.getName() + "." + this.mMethod.getName(), ex3);
                }
                catch (InvocationTargetException ex4) {
                    if (ex4.getCause() instanceof RuntimeException) {
                        throw (RuntimeException)ex4.getCause();
                    }
                    throw new RuntimeException("Could not invoke " + this.this$0.getName() + "." + this.mMethod.getName(), ex4);
                }
            }
            final int n2 = 0;
            int i = 0;
            continue;
        }
    }
}
