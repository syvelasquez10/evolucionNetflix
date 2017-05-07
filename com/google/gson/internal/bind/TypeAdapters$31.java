// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$31 implements TypeAdapterFactory
{
    final /* synthetic */ Class val$clazz;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    TypeAdapters$31(final Class val$clazz, final TypeAdapter val$typeAdapter) {
        this.val$clazz = val$clazz;
        this.val$typeAdapter = val$typeAdapter;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (this.val$clazz.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}
