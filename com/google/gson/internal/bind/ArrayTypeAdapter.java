// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.TypeAdapter;

public final class ArrayTypeAdapter<E> extends TypeAdapter<Object>
{
    public static final TypeAdapterFactory FACTORY;
    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;
    
    static {
        FACTORY = new ArrayTypeAdapter$1();
    }
    
    public ArrayTypeAdapter(final Gson gson, final TypeAdapter<E> typeAdapter, final Class<E> componentType) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(gson, typeAdapter, componentType);
        this.componentType = componentType;
    }
    
    @Override
    public Object read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final ArrayList<E> list = new ArrayList<E>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            list.add(this.componentTypeAdapter.read(jsonReader));
        }
        jsonReader.endArray();
        final Object instance = Array.newInstance(this.componentType, list.size());
        for (int i = 0; i < list.size(); ++i) {
            Array.set(instance, i, list.get(i));
        }
        return instance;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Object o) {
        if (o == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        for (int i = 0; i < Array.getLength(o); ++i) {
            this.componentTypeAdapter.write(jsonWriter, (E)Array.get(o, i));
        }
        jsonWriter.endArray();
    }
}
