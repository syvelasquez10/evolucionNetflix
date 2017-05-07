// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$28 implements TypeAdapterFactory
{
    final /* synthetic */ Class val$type;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    TypeAdapters$28(final Class val$type, final TypeAdapter val$typeAdapter) {
        this.val$type = val$type;
        this.val$typeAdapter = val$typeAdapter;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == this.val$type) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}
