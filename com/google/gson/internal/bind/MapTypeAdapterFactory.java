// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.$Gson$Types;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.TypeAdapterFactory;

public final class MapTypeAdapterFactory implements TypeAdapterFactory
{
    private final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    
    public MapTypeAdapterFactory(final ConstructorConstructor constructorConstructor, final boolean complexMapKeySerialization) {
        this.constructorConstructor = constructorConstructor;
        this.complexMapKeySerialization = complexMapKeySerialization;
    }
    
    private TypeAdapter<?> getKeyAdapter(final Gson gson, final Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return TypeAdapters.BOOLEAN_AS_STRING;
        }
        return gson.getAdapter(TypeToken.get(type));
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        if (!Map.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        final Type[] mapKeyAndValueTypes = $Gson$Types.getMapKeyAndValueTypes(type, $Gson$Types.getRawType(type));
        return (TypeAdapter<T>)new MapTypeAdapterFactory$Adapter(gson, mapKeyAndValueTypes[0], (TypeAdapter<Object>)this.getKeyAdapter(gson, mapKeyAndValueTypes[0]), mapKeyAndValueTypes[1], (TypeAdapter<Object>)gson.getAdapter(TypeToken.get(mapKeyAndValueTypes[1])), (ObjectConstructor<? extends Map<Object, Object>>)this.constructorConstructor.get(typeToken));
    }
}
