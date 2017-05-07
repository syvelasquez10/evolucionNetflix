// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.util.Date;
import java.sql.Timestamp;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;

final class TypeAdapters$22 implements TypeAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() != Timestamp.class) {
            return null;
        }
        return (TypeAdapter<T>)new TypeAdapters$22$1(this, gson.getAdapter(Date.class));
    }
}
