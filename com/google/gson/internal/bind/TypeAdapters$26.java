// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$26 implements TypeAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
            return null;
        }
        Class<? super T> superclass = rawType;
        if (!rawType.isEnum()) {
            superclass = rawType.getSuperclass();
        }
        return new TypeAdapters$EnumTypeAdapter<T>((Class<T>)superclass);
    }
}
