// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import java.net.URISyntaxException;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.net.URI;
import com.google.gson.TypeAdapter;

final class TypeAdapters$19 extends TypeAdapter<URI>
{
    @Override
    public URI read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
        }
        else {
            try {
                final String nextString = jsonReader.nextString();
                if (!"null".equals(nextString)) {
                    return new URI(nextString);
                }
            }
            catch (URISyntaxException ex) {
                throw new JsonIOException(ex);
            }
        }
        return null;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final URI uri) {
        String asciiString;
        if (uri == null) {
            asciiString = null;
        }
        else {
            asciiString = uri.toASCIIString();
        }
        jsonWriter.value(asciiString);
    }
}
