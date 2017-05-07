// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;

final class TypeAdapters$1 extends TypeAdapter<Class>
{
    @Override
    public Class read(final JsonReader jsonReader) {
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Class clazz) {
        throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + clazz.getName() + ". Forgot to register a type adapter?");
    }
}
