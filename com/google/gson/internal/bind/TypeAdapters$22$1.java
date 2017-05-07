// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import java.util.Date;
import com.google.gson.stream.JsonReader;
import java.sql.Timestamp;
import com.google.gson.TypeAdapter;

class TypeAdapters$22$1 extends TypeAdapter<Timestamp>
{
    final /* synthetic */ TypeAdapters$22 this$0;
    final /* synthetic */ TypeAdapter val$dateTypeAdapter;
    
    TypeAdapters$22$1(final TypeAdapters$22 this$0, final TypeAdapter val$dateTypeAdapter) {
        this.this$0 = this$0;
        this.val$dateTypeAdapter = val$dateTypeAdapter;
    }
    
    @Override
    public Timestamp read(final JsonReader jsonReader) {
        final Date date = this.val$dateTypeAdapter.read(jsonReader);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Timestamp timestamp) {
        this.val$dateTypeAdapter.write(jsonWriter, timestamp);
    }
}
