// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Method;
import java.util.HashMap;
import com.facebook.systrace.Systrace;
import java.util.Map;

final class BaseJavaModule$9 extends BaseJavaModule$ArgumentExtractor<Promise>
{
    BaseJavaModule$9() {
        super(null);
    }
    
    @Override
    public Promise extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return new PromiseImpl(BaseJavaModule.ARGUMENT_EXTRACTOR_CALLBACK.extractArgument(catalystInstance, executorToken, readableNativeArray, n), BaseJavaModule.ARGUMENT_EXTRACTOR_CALLBACK.extractArgument(catalystInstance, executorToken, readableNativeArray, n + 1));
    }
    
    @Override
    public int getJSArgumentsNeeded() {
        return 2;
    }
}
