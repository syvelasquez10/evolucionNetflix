// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.util.Iterator;
import java.util.Map;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;

final class TypeAdapters$25 extends TypeAdapter<JsonElement>
{
    @Override
    public JsonElement read(final JsonReader jsonReader) {
        switch (TypeAdapters$32.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
            default: {
                throw new IllegalArgumentException();
            }
            case 3: {
                return new JsonPrimitive(jsonReader.nextString());
            }
            case 1: {
                return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
            }
            case 2: {
                return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
            }
            case 4: {
                jsonReader.nextNull();
                return JsonNull.INSTANCE;
            }
            case 5: {
                final JsonArray jsonArray = new JsonArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonArray.add(this.read(jsonReader));
                }
                jsonReader.endArray();
                return jsonArray;
            }
            case 6: {
                final JsonObject jsonObject = new JsonObject();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    jsonObject.add(jsonReader.nextName(), this.read(jsonReader));
                }
                jsonReader.endObject();
                return jsonObject;
            }
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            jsonWriter.nullValue();
            return;
        }
        if (jsonElement.isJsonPrimitive()) {
            final JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                jsonWriter.value(asJsonPrimitive.getAsNumber());
                return;
            }
            if (asJsonPrimitive.isBoolean()) {
                jsonWriter.value(asJsonPrimitive.getAsBoolean());
                return;
            }
            jsonWriter.value(asJsonPrimitive.getAsString());
        }
        else {
            if (jsonElement.isJsonArray()) {
                jsonWriter.beginArray();
                final Iterator<JsonElement> iterator = jsonElement.getAsJsonArray().iterator();
                while (iterator.hasNext()) {
                    this.write(jsonWriter, iterator.next());
                }
                jsonWriter.endArray();
                return;
            }
            if (jsonElement.isJsonObject()) {
                jsonWriter.beginObject();
                for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                    jsonWriter.name(entry.getKey());
                    this.write(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
                return;
            }
            throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
        }
    }
}
