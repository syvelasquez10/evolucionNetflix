// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.stream.JsonReader;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.google.gson.stream.JsonWriter;
import com.netflix.mediaclient.NetflixApplication;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public class PQLAdapter extends TypeAdapter<PQL>
{
    private static final Gson gson;
    
    static {
        gson = NetflixApplication.getGson();
    }
    
    private void writeObject(final JsonWriter jsonWriter, final Object o) {
        if (o instanceof List) {
            jsonWriter.beginArray();
            final Iterator<Object> iterator = ((List)o).iterator();
            while (iterator.hasNext()) {
                this.writeObject(jsonWriter, iterator.next());
            }
            jsonWriter.endArray();
            return;
        }
        if (o instanceof Map) {
            final Map map = (Map)o;
            jsonWriter.beginObject();
            final Integer n = map.get("from");
            if (n != null) {
                jsonWriter.name("from");
                jsonWriter.value((long)n);
            }
            jsonWriter.name("to");
            jsonWriter.value((long)map.get("to"));
            jsonWriter.endObject();
            return;
        }
        jsonWriter.value(o.toString());
    }
    
    @Override
    public PQL read(final JsonReader jsonReader) {
        return PQL.fromList(PQLAdapter.gson.fromJson(jsonReader, ArrayList.class));
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final PQL pql) {
        this.writeObject(jsonWriter, pql.getKeySegments());
    }
}
