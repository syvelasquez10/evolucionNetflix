// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$9 extends BaseJavaModule$ArgumentExtractor<Callback>
{
    BaseJavaModule$9() {
        super(null);
    }
    
    @Override
    public Callback extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        if (readableNativeArray.isNull(n)) {
            return null;
        }
        return new CallbackImpl(catalystInstance, executorToken, (int)readableNativeArray.getDouble(n));
    }
}
