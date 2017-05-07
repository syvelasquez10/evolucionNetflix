// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;

class Gson$6 extends TypeAdapter<Number>
{
    final /* synthetic */ Gson this$0;
    
    Gson$6(final Gson this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Number read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return jsonReader.nextLong();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) {
        if (n == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(n.toString());
    }
}
