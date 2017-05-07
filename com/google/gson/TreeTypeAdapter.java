// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import com.google.gson.stream.JsonWriter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;

final class TreeTypeAdapter<T> extends TypeAdapter<T>
{
    private TypeAdapter<T> delegate;
    private final JsonDeserializer<T> deserializer;
    private final Gson gson;
    private final JsonSerializer<T> serializer;
    private final TypeAdapterFactory skipPast;
    private final TypeToken<T> typeToken;
    
    private TreeTypeAdapter(final JsonSerializer<T> serializer, final JsonDeserializer<T> deserializer, final Gson gson, final TypeToken<T> typeToken, final TypeAdapterFactory skipPast) {
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.gson = gson;
        this.typeToken = typeToken;
        this.skipPast = skipPast;
    }
    
    private TypeAdapter<T> delegate() {
        final TypeAdapter<T> delegate = this.delegate;
        if (delegate != null) {
            return delegate;
        }
        return this.delegate = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
    }
    
    public static TypeAdapterFactory newFactory(final TypeToken<?> typeToken, final Object o) {
        return new TreeTypeAdapter$SingleTypeFactory(o, typeToken, false, null, null);
    }
    
    public static TypeAdapterFactory newFactoryWithMatchRawType(final TypeToken<?> typeToken, final Object o) {
        return new TreeTypeAdapter$SingleTypeFactory(o, typeToken, typeToken.getType() == typeToken.getRawType(), null, null);
    }
    
    @Override
    public T read(final JsonReader jsonReader) {
        if (this.deserializer == null) {
            return this.delegate().read(jsonReader);
        }
        final JsonElement parse = Streams.parse(jsonReader);
        if (parse.isJsonNull()) {
            return null;
        }
        return this.deserializer.deserialize(parse, this.typeToken.getType(), this.gson.deserializationContext);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) {
        if (this.serializer == null) {
            this.delegate().write(jsonWriter, t);
            return;
        }
        if (t == null) {
            jsonWriter.nullValue();
            return;
        }
        Streams.write(this.serializer.serialize(t, this.typeToken.getType(), this.gson.serializationContext), jsonWriter);
    }
}
