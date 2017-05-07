// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.io.Writer;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import com.google.gson.JsonIOException;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonNull;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

public final class Streams
{
    public static JsonElement parse(final JsonReader jsonReader) {
        boolean b = true;
        try {
            jsonReader.peek();
            b = false;
            return TypeAdapters.JSON_ELEMENT.read(jsonReader);
        }
        catch (EOFException ex) {
            if (b) {
                return JsonNull.INSTANCE;
            }
            throw new JsonSyntaxException(ex);
        }
        catch (MalformedJsonException ex2) {
            throw new JsonSyntaxException(ex2);
        }
        catch (IOException ex3) {
            throw new JsonIOException(ex3);
        }
        catch (NumberFormatException ex4) {
            throw new JsonSyntaxException(ex4);
        }
    }
    
    public static void write(final JsonElement jsonElement, final JsonWriter jsonWriter) {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }
    
    public static Writer writerForAppendable(final Appendable appendable) {
        if (appendable instanceof Writer) {
            return (Writer)appendable;
        }
        return new Streams$AppendableWriter(appendable, null);
    }
}
