// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.util.Date;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;

final class DateTypeAdapter$1 implements TypeAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == Date.class) {
            return (TypeAdapter<T>)new DateTypeAdapter();
        }
        return null;
    }
}
