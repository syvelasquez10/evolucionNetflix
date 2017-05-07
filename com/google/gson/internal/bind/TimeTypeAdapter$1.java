// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.sql.Time;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;

final class TimeTypeAdapter$1 implements TypeAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == Time.class) {
            return (TypeAdapter<T>)new TimeTypeAdapter();
        }
        return null;
    }
}
