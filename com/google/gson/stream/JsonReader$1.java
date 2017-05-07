// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.stream;

import java.io.IOException;
import java.io.EOFException;
import java.io.Reader;
import java.io.Closeable;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.JsonReaderInternalAccess;

final class JsonReader$1 extends JsonReaderInternalAccess
{
    @Override
    public void promoteNameToValue(final JsonReader jsonReader) {
        if (jsonReader instanceof JsonTreeReader) {
            ((JsonTreeReader)jsonReader).promoteNameToValue();
            return;
        }
        jsonReader.peek();
        if (jsonReader.token != JsonToken.NAME) {
            throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + " " + " at line " + jsonReader.getLineNumber() + " column " + jsonReader.getColumnNumber());
        }
        jsonReader.value = jsonReader.name;
        jsonReader.name = null;
        jsonReader.token = JsonToken.STRING;
    }
}
