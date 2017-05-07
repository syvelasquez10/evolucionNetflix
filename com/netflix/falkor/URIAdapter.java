// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.google.gson.stream.JsonWriter;
import java.net.URISyntaxException;
import java.io.IOException;
import com.google.gson.stream.JsonReader;
import java.net.URI;
import com.google.gson.TypeAdapter;

public class URIAdapter extends TypeAdapter<URI>
{
    @Override
    public URI read(final JsonReader jsonReader) throws IOException {
        try {
            return new URI(jsonReader.nextString());
        }
        catch (URISyntaxException ex) {
            return null;
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final URI uri) throws IOException {
        jsonWriter.value(uri.toString());
    }
}
