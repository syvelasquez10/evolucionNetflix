// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.util.Iterator;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.internal.ObjectConstructor;
import java.util.Map;
import com.google.gson.TypeAdapter;

public final class ReflectiveTypeAdapterFactory$Adapter<T> extends TypeAdapter<T>
{
    private final Map<String, ReflectiveTypeAdapterFactory$BoundField> boundFields;
    private final ObjectConstructor<T> constructor;
    final /* synthetic */ ReflectiveTypeAdapterFactory this$0;
    
    private ReflectiveTypeAdapterFactory$Adapter(final ReflectiveTypeAdapterFactory this$0, final ObjectConstructor<T> constructor, final Map<String, ReflectiveTypeAdapterFactory$BoundField> boundFields) {
        this.this$0 = this$0;
        this.constructor = constructor;
        this.boundFields = boundFields;
    }
    
    @Override
    public T read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final T construct = this.constructor.construct();
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                final ReflectiveTypeAdapterFactory$BoundField reflectiveTypeAdapterFactory$BoundField = this.boundFields.get(jsonReader.nextName());
                if (reflectiveTypeAdapterFactory$BoundField != null && reflectiveTypeAdapterFactory$BoundField.deserialized) {
                    goto Label_0084;
                }
                jsonReader.skipValue();
            }
        }
        catch (IllegalStateException ex) {
            throw new JsonSyntaxException(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new AssertionError((Object)ex2);
        }
        jsonReader.endObject();
        return construct;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) {
        if (t == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        try {
            for (final ReflectiveTypeAdapterFactory$BoundField reflectiveTypeAdapterFactory$BoundField : this.boundFields.values()) {
                if (reflectiveTypeAdapterFactory$BoundField.serialized) {
                    jsonWriter.name(reflectiveTypeAdapterFactory$BoundField.name);
                    reflectiveTypeAdapterFactory$BoundField.write(jsonWriter, t);
                }
            }
        }
        catch (IllegalAccessException ex) {
            throw new AssertionError();
        }
        jsonWriter.endObject();
    }
}
