// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

final class UnsafeAllocator$4 extends UnsafeAllocator
{
    @Override
    public <T> T newInstance(final Class<T> clazz) {
        throw new UnsupportedOperationException("Cannot allocate " + clazz);
    }
}
