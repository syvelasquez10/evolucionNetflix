// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.util.Iterator;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import java.util.Collection;
import com.google.gson.TypeAdapter;

final class CollectionTypeAdapterFactory$Adapter<E> extends TypeAdapter<Collection<E>>
{
    private final ObjectConstructor<? extends Collection<E>> constructor;
    private final TypeAdapter<E> elementTypeAdapter;
    final /* synthetic */ CollectionTypeAdapterFactory this$0;
    
    public CollectionTypeAdapterFactory$Adapter(final CollectionTypeAdapterFactory this$0, final Gson gson, final Type type, final TypeAdapter<E> typeAdapter, final ObjectConstructor<? extends Collection<E>> constructor) {
        this.this$0 = this$0;
        this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(gson, typeAdapter, type);
        this.constructor = constructor;
    }
    
    @Override
    public Collection<E> read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final Collection collection = (Collection)this.constructor.construct();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            collection.add(this.elementTypeAdapter.read(jsonReader));
        }
        jsonReader.endArray();
        return (Collection<E>)collection;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Collection<E> collection) {
        if (collection == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        final Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.elementTypeAdapter.write(jsonWriter, iterator.next());
        }
        jsonWriter.endArray();
    }
}
