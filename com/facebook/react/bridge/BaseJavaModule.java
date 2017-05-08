// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Method;
import java.util.HashMap;
import com.facebook.systrace.Systrace;
import java.util.Map;

public abstract class BaseJavaModule implements NativeModule
{
    private static final BaseJavaModule$ArgumentExtractor<ReadableNativeArray> ARGUMENT_EXTRACTOR_ARRAY;
    private static final BaseJavaModule$ArgumentExtractor<Boolean> ARGUMENT_EXTRACTOR_BOOLEAN;
    private static final BaseJavaModule$ArgumentExtractor<Callback> ARGUMENT_EXTRACTOR_CALLBACK;
    private static final BaseJavaModule$ArgumentExtractor<Double> ARGUMENT_EXTRACTOR_DOUBLE;
    private static final BaseJavaModule$ArgumentExtractor<Dynamic> ARGUMENT_EXTRACTOR_DYNAMIC;
    private static final BaseJavaModule$ArgumentExtractor<Float> ARGUMENT_EXTRACTOR_FLOAT;
    private static final BaseJavaModule$ArgumentExtractor<Integer> ARGUMENT_EXTRACTOR_INTEGER;
    private static final BaseJavaModule$ArgumentExtractor<ReadableMap> ARGUMENT_EXTRACTOR_MAP;
    private static final BaseJavaModule$ArgumentExtractor<Promise> ARGUMENT_EXTRACTOR_PROMISE;
    private static final BaseJavaModule$ArgumentExtractor<String> ARGUMENT_EXTRACTOR_STRING;
    public static final String METHOD_TYPE_ASYNC = "async";
    public static final String METHOD_TYPE_PROMISE = "promise";
    public static final String METHOD_TYPE_SYNC = "sync";
    private Map<String, NativeModule$NativeMethod> mMethods;
    
    static {
        ARGUMENT_EXTRACTOR_BOOLEAN = new BaseJavaModule$1();
        ARGUMENT_EXTRACTOR_DOUBLE = new BaseJavaModule$2();
        ARGUMENT_EXTRACTOR_FLOAT = new BaseJavaModule$3();
        ARGUMENT_EXTRACTOR_INTEGER = new BaseJavaModule$4();
        ARGUMENT_EXTRACTOR_STRING = new BaseJavaModule$5();
        ARGUMENT_EXTRACTOR_ARRAY = new BaseJavaModule$6();
        ARGUMENT_EXTRACTOR_DYNAMIC = new BaseJavaModule$7();
        ARGUMENT_EXTRACTOR_MAP = new BaseJavaModule$8();
        ARGUMENT_EXTRACTOR_CALLBACK = new BaseJavaModule$9();
        ARGUMENT_EXTRACTOR_PROMISE = new BaseJavaModule$10();
    }
    
    private static char commonTypeToChar(final Class clazz) {
        if (clazz == Boolean.TYPE) {
            return 'z';
        }
        if (clazz == Boolean.class) {
            return 'Z';
        }
        if (clazz == Integer.TYPE) {
            return 'i';
        }
        if (clazz == Integer.class) {
            return 'I';
        }
        if (clazz == Double.TYPE) {
            return 'd';
        }
        if (clazz == Double.class) {
            return 'D';
        }
        if (clazz == Float.TYPE) {
            return 'f';
        }
        if (clazz == Float.class) {
            return 'F';
        }
        if (clazz == String.class) {
            return 'S';
        }
        return '\0';
    }
    
    private void findMethods() {
        if (this.mMethods == null) {
            Systrace.beginSection(0L, "findMethods");
            this.mMethods = new HashMap<String, NativeModule$NativeMethod>();
            final Method[] declaredMethods = this.getClass().getDeclaredMethods();
            for (int length = declaredMethods.length, i = 0; i < length; ++i) {
                final Method method = declaredMethods[i];
                final ReactMethod reactMethod = method.getAnnotation(ReactMethod.class);
                if (reactMethod != null) {
                    final String name = method.getName();
                    if (this.mMethods.containsKey(name)) {
                        throw new IllegalArgumentException("Java Module " + this.getName() + " method name already registered: " + name);
                    }
                    this.mMethods.put(name, new BaseJavaModule$JavaMethod(this, method, reactMethod.isBlockingSynchronousMethod()));
                }
            }
            Systrace.endSection(0L);
        }
    }
    
    private static char paramTypeToChar(final Class clazz) {
        final char commonTypeToChar = commonTypeToChar(clazz);
        if (commonTypeToChar != '\0') {
            return commonTypeToChar;
        }
        if (clazz == ExecutorToken.class) {
            return 'T';
        }
        if (clazz == Callback.class) {
            return 'X';
        }
        if (clazz == Promise.class) {
            return 'P';
        }
        if (clazz == ReadableMap.class) {
            return 'M';
        }
        if (clazz == ReadableArray.class) {
            return 'A';
        }
        if (clazz == Dynamic.class) {
            return 'Y';
        }
        throw new RuntimeException("Got unknown param class: " + clazz.getSimpleName());
    }
    
    private static char returnTypeToChar(final Class clazz) {
        final char commonTypeToChar = commonTypeToChar(clazz);
        if (commonTypeToChar != '\0') {
            return commonTypeToChar;
        }
        if (clazz == Void.TYPE) {
            return 'v';
        }
        if (clazz == WritableMap.class) {
            return 'M';
        }
        if (clazz == WritableArray.class) {
            return 'A';
        }
        throw new RuntimeException("Got unknown return class: " + clazz.getSimpleName());
    }
    
    @Override
    public boolean canOverrideExistingModule() {
        return false;
    }
    
    public Map<String, Object> getConstants() {
        return null;
    }
    
    public final Map<String, NativeModule$NativeMethod> getMethods() {
        this.findMethods();
        return Assertions.assertNotNull(this.mMethods);
    }
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
    }
    
    public boolean supportsWebWorkers() {
        return false;
    }
}
