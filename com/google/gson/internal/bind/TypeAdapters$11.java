// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;

final class TypeAdapters$11 extends TypeAdapter<Number>
{
    @Override
    public Number read(final JsonReader jsonReader) {
        final JsonToken peek = jsonReader.peek();
        switch (TypeAdapters$32.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()]) {
            default: {
                throw new JsonSyntaxException("Expecting number, got: " + peek);
            }
            case 4: {
                jsonReader.nextNull();
                return null;
            }
            case 1: {
                return new LazilyParsedNumber(jsonReader.nextString());
            }
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) {
        jsonWriter.value(n);
    }
}
