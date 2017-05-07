// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;

final class TypeAdapters$8 extends TypeAdapter<Number>
{
    @Override
    public Number read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return jsonReader.nextLong();
        }
        catch (NumberFormatException ex) {
            throw new JsonSyntaxException(ex);
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) {
        jsonWriter.value(n);
    }
}
