// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$7 extends BaseJavaModule$ArgumentExtractor<Dynamic>
{
    BaseJavaModule$7() {
        super(null);
    }
    
    @Override
    public Dynamic extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return DynamicFromArray.create(readableNativeArray, n);
    }
}
