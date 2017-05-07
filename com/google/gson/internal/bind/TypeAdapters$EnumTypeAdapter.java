// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.TypeAdapter;

final class TypeAdapters$EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T>
{
    private final Map<T, String> constantToName;
    private final Map<String, T> nameToConstant;
    
    public TypeAdapters$EnumTypeAdapter(final Class<T> clazz) {
        while (true) {
            this.nameToConstant = new HashMap<String, T>();
            this.constantToName = new HashMap<T, String>();
            while (true) {
                Label_0134: {
                    try {
                        final T[] array = clazz.getEnumConstants();
                        for (int length = array.length, i = 0; i < length; ++i) {
                            final Enum<T> enum1 = array[i];
                            String s = enum1.name();
                            final SerializedName serializedName = clazz.getField(s).getAnnotation(SerializedName.class);
                            if (serializedName == null) {
                                break Label_0134;
                            }
                            s = serializedName.value();
                            this.nameToConstant.put(s, (T)enum1);
                            this.constantToName.put((T)enum1, s);
                        }
                    }
                    catch (NoSuchFieldException ex) {
                        throw new AssertionError();
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @Override
    public T read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return this.nameToConstant.get(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) {
        String s;
        if (t == null) {
            s = null;
        }
        else {
            s = this.constantToName.get(t);
        }
        jsonWriter.value(s);
    }
}
