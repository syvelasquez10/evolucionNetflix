// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import java.util.StringTokenizer;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.util.Locale;
import com.google.gson.TypeAdapter;

final class TypeAdapters$24 extends TypeAdapter<Locale>
{
    @Override
    public Locale read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
        String nextToken;
        if (stringTokenizer.hasMoreElements()) {
            nextToken = stringTokenizer.nextToken();
        }
        else {
            nextToken = null;
        }
        String nextToken2;
        if (stringTokenizer.hasMoreElements()) {
            nextToken2 = stringTokenizer.nextToken();
        }
        else {
            nextToken2 = null;
        }
        String nextToken3;
        if (stringTokenizer.hasMoreElements()) {
            nextToken3 = stringTokenizer.nextToken();
        }
        else {
            nextToken3 = null;
        }
        if (nextToken2 == null && nextToken3 == null) {
            return new Locale(nextToken);
        }
        if (nextToken3 == null) {
            return new Locale(nextToken, nextToken2);
        }
        return new Locale(nextToken, nextToken2, nextToken3);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Locale locale) {
        String string;
        if (locale == null) {
            string = null;
        }
        else {
            string = locale.toString();
        }
        jsonWriter.value(string);
    }
}
