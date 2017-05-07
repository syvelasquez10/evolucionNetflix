// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$27 implements TypeAdapterFactory
{
    final /* synthetic */ TypeToken val$type;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    TypeAdapters$27(final TypeToken val$type, final TypeAdapter val$typeAdapter) {
        this.val$type = val$type;
        this.val$typeAdapter = val$typeAdapter;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.equals(this.val$type)) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
}
