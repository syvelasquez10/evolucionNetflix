// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.util.BitSet;
import com.google.gson.TypeAdapter;

final class TypeAdapters$2 extends TypeAdapter<BitSet>
{
    @Override
    public BitSet read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final BitSet set = new BitSet();
        jsonReader.beginArray();
        JsonToken jsonToken = jsonReader.peek();
        int n = 0;
    Label_0214:
        while (jsonToken != JsonToken.END_ARRAY) {
            int nextBoolean = 0;
            switch (TypeAdapters$32.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()]) {
                default: {
                    throw new JsonSyntaxException("Invalid bitset value type: " + jsonToken);
                }
                case 1: {
                    if (jsonReader.nextInt() != 0) {
                        nextBoolean = 1;
                        break;
                    }
                    nextBoolean = 0;
                    break;
                }
                case 2: {
                    nextBoolean = (jsonReader.nextBoolean() ? 1 : 0);
                    break;
                }
                case 3: {
                    final String nextString = jsonReader.nextString();
                    try {
                        if (Integer.parseInt(nextString) != 0) {
                            nextBoolean = 1;
                            break;
                        }
                        nextBoolean = 0;
                        break;
                    }
                    catch (NumberFormatException ex) {
                        throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + nextString);
                    }
                    break Label_0214;
                }
            }
            if (nextBoolean != 0) {
                set.set(n);
            }
            ++n;
            jsonToken = jsonReader.peek();
        }
        jsonReader.endArray();
        return set;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final BitSet set) {
        if (set == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        for (int i = 0; i < set.length(); ++i) {
            boolean b;
            if (set.get(i)) {
                b = true;
            }
            else {
                b = false;
            }
            jsonWriter.value(b ? 1 : 0);
        }
        jsonWriter.endArray();
    }
}
