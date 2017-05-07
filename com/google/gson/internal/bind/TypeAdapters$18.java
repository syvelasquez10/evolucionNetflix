// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.net.URL;
import com.google.gson.TypeAdapter;

final class TypeAdapters$18 extends TypeAdapter<URL>
{
    @Override
    public URL read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
        }
        else {
            final String nextString = jsonReader.nextString();
            if (!"null".equals(nextString)) {
                return new URL(nextString);
            }
        }
        return null;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final URL url) {
        String externalForm;
        if (url == null) {
            externalForm = null;
        }
        else {
            externalForm = url.toExternalForm();
        }
        jsonWriter.value(externalForm);
    }
}
