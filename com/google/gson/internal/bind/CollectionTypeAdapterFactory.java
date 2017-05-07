// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.lang.reflect.Type;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.$Gson$Types;
import java.util.Collection;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.TypeAdapterFactory;

public final class CollectionTypeAdapterFactory implements TypeAdapterFactory
{
    private final ConstructorConstructor constructorConstructor;
    
    public CollectionTypeAdapterFactory(final ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        final Class<? super T> rawType = typeToken.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        final Type collectionElementType = $Gson$Types.getCollectionElementType(type, rawType);
        return (TypeAdapter<T>)new CollectionTypeAdapterFactory$Adapter(gson, collectionElementType, (TypeAdapter<Object>)gson.getAdapter(TypeToken.get(collectionElementType)), (ObjectConstructor<? extends Collection<Object>>)this.constructorConstructor.get((TypeToken<? extends Collection<E>>)typeToken));
    }
}
