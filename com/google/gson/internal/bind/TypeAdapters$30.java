// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$30 implements TypeAdapterFactory
{
    final /* synthetic */ Class val$base;
    final /* synthetic */ Class val$sub;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    TypeAdapters$30(final Class val$base, final Class val$sub, final TypeAdapter val$typeAdapter) {
        this.val$base = val$base;
        this.val$sub = val$sub;
        this.val$typeAdapter = val$typeAdapter;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (rawType == this.val$base || rawType == this.val$sub) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}
