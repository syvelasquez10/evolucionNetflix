// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;

final class TypeAdapters$17 extends TypeAdapter<StringBuffer>
{
    @Override
    public StringBuffer read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return new StringBuffer(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final StringBuffer sb) {
        String string;
        if (sb == null) {
            string = null;
        }
        else {
            string = sb.toString();
        }
        jsonWriter.value(string);
    }
}
