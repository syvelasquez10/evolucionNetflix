// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.google.gson.stream.JsonWriter;
import java.net.URISyntaxException;
import com.google.gson.stream.JsonReader;
import java.net.URI;
import com.google.gson.TypeAdapter;

public class URIAdapter extends TypeAdapter<URI>
{
    @Override
    public URI read(final JsonReader jsonReader) {
        try {
            return new URI(jsonReader.nextString());
        }
        catch (URISyntaxException ex) {
            return null;
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final URI uri) {
        jsonWriter.value(uri.toString());
    }
}
