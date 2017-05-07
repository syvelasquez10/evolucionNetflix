// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$29 implements TypeAdapterFactory
{
    final /* synthetic */ Class val$boxed;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    final /* synthetic */ Class val$unboxed;
    
    TypeAdapters$29(final Class val$unboxed, final Class val$boxed, final TypeAdapter val$typeAdapter) {
        this.val$unboxed = val$unboxed;
        this.val$boxed = val$boxed;
        this.val$typeAdapter = val$typeAdapter;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (rawType == this.val$unboxed || rawType == this.val$boxed) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}
