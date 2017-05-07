// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.lang.reflect.Method;

final class UnsafeAllocator$2 extends UnsafeAllocator
{
    final /* synthetic */ Method val$newInstance;
    
    UnsafeAllocator$2(final Method val$newInstance) {
        this.val$newInstance = val$newInstance;
    }
    
    @Override
    public <T> T newInstance(final Class<T> clazz) {
        return (T)this.val$newInstance.invoke(null, clazz, Object.class);
    }
}
