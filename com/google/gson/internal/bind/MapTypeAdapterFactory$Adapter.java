// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.TypeAdapterFactory;
import java.util.Iterator;
import com.google.gson.internal.Streams;
import java.util.ArrayList;
import com.google.gson.stream.JsonWriter;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import java.util.Map;
import com.google.gson.TypeAdapter;

final class MapTypeAdapterFactory$Adapter<K, V> extends TypeAdapter<Map<K, V>>
{
    private final ObjectConstructor<? extends Map<K, V>> constructor;
    private final TypeAdapter<K> keyTypeAdapter;
    final /* synthetic */ MapTypeAdapterFactory this$0;
    private final TypeAdapter<V> valueTypeAdapter;
    
    public MapTypeAdapterFactory$Adapter(final MapTypeAdapterFactory this$0, final Gson gson, final Type type, final TypeAdapter<K> typeAdapter, final Type type2, final TypeAdapter<V> typeAdapter2, final ObjectConstructor<? extends Map<K, V>> constructor) {
        this.this$0 = this$0;
        this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper<K>(gson, typeAdapter, type);
        this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper<V>(gson, typeAdapter2, type2);
        this.constructor = constructor;
    }
    
    private String keyToString(final JsonElement jsonElement) {
        if (jsonElement.isJsonPrimitive()) {
            final JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                return String.valueOf(asJsonPrimitive.getAsNumber());
            }
            if (asJsonPrimitive.isBoolean()) {
                return Boolean.toString(asJsonPrimitive.getAsBoolean());
            }
            if (asJsonPrimitive.isString()) {
                return asJsonPrimitive.getAsString();
            }
            throw new AssertionError();
        }
        else {
            if (jsonElement.isJsonNull()) {
                return "null";
            }
            throw new AssertionError();
        }
    }
    
    @Override
    public Map<K, V> read(final JsonReader jsonReader) {
        final JsonToken peek = jsonReader.peek();
        if (peek == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final Map map = (Map)this.constructor.construct();
        if (peek == JsonToken.BEGIN_ARRAY) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginArray();
                final K read = this.keyTypeAdapter.read(jsonReader);
                if (map.put(read, this.valueTypeAdapter.read(jsonReader)) != null) {
                    throw new JsonSyntaxException("duplicate key: " + read);
                }
                jsonReader.endArray();
            }
            jsonReader.endArray();
            return (Map<K, V>)map;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader);
            final K read2 = this.keyTypeAdapter.read(jsonReader);
            if (map.put(read2, this.valueTypeAdapter.read(jsonReader)) != null) {
                throw new JsonSyntaxException("duplicate key: " + read2);
            }
        }
        jsonReader.endObject();
        return (Map<K, V>)map;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Map<K, V> map) {
        final int n = 0;
        final int n2 = 0;
        if (map == null) {
            jsonWriter.nullValue();
            return;
        }
        if (!this.this$0.complexMapKeySerialization) {
            jsonWriter.beginObject();
            for (final Map.Entry<K, V> entry : map.entrySet()) {
                jsonWriter.name(String.valueOf(entry.getKey()));
                this.valueTypeAdapter.write(jsonWriter, entry.getValue());
            }
            jsonWriter.endObject();
            return;
        }
        final ArrayList<JsonElement> list = new ArrayList<JsonElement>(map.size());
        final ArrayList<V> list2 = new ArrayList<V>(map.size());
        final Iterator<Map.Entry<K, V>> iterator2 = map.entrySet().iterator();
        boolean b = false;
        while (iterator2.hasNext()) {
            final Map.Entry<K, V> entry2 = iterator2.next();
            final JsonElement jsonTree = this.keyTypeAdapter.toJsonTree(entry2.getKey());
            list.add(jsonTree);
            list2.add(entry2.getValue());
            boolean b2;
            if (jsonTree.isJsonArray() || jsonTree.isJsonObject()) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            b |= b2;
        }
        if (b) {
            jsonWriter.beginArray();
            for (int i = n2; i < list.size(); ++i) {
                jsonWriter.beginArray();
                Streams.write((JsonElement)list.get(i), jsonWriter);
                this.valueTypeAdapter.write(jsonWriter, (V)list2.get(i));
                jsonWriter.endArray();
            }
            jsonWriter.endArray();
            return;
        }
        jsonWriter.beginObject();
        for (int j = n; j < list.size(); ++j) {
            jsonWriter.name(this.keyToString((JsonElement)list.get(j)));
            this.valueTypeAdapter.write(jsonWriter, (V)list2.get(j));
        }
        jsonWriter.endObject();
    }
}
