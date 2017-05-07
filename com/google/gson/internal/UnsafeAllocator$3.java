// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.lang.reflect.Method;

final class UnsafeAllocator$3 extends UnsafeAllocator
{
    final /* synthetic */ int val$constructorId;
    final /* synthetic */ Method val$newInstance;
    
    UnsafeAllocator$3(final Method val$newInstance, final int val$constructorId) {
        this.val$newInstance = val$newInstance;
        this.val$constructorId = val$constructorId;
    }
    
    @Override
    public <T> T newInstance(final Class<T> clazz) {
        return (T)this.val$newInstance.invoke(null, clazz, this.val$constructorId);
    }
}
