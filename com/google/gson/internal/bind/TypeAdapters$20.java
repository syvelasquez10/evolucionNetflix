// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.net.InetAddress;
import com.google.gson.TypeAdapter;

final class TypeAdapters$20 extends TypeAdapter<InetAddress>
{
    @Override
    public InetAddress read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return InetAddress.getByName(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final InetAddress inetAddress) {
        String hostAddress;
        if (inetAddress == null) {
            hostAddress = null;
        }
        else {
            hostAddress = inetAddress.getHostAddress();
        }
        jsonWriter.value(hostAddress);
    }
}
