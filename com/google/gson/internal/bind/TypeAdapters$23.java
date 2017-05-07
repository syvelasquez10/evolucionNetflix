// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import java.util.GregorianCalendar;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.util.Calendar;
import com.google.gson.TypeAdapter;

final class TypeAdapters$23 extends TypeAdapter<Calendar>
{
    @Override
    public Calendar read(final JsonReader jsonReader) {
        int n = 0;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        while (jsonReader.peek() != JsonToken.END_OBJECT) {
            final String nextName = jsonReader.nextName();
            final int nextInt = jsonReader.nextInt();
            if ("year".equals(nextName)) {
                n6 = nextInt;
            }
            else if ("month".equals(nextName)) {
                n5 = nextInt;
            }
            else if ("dayOfMonth".equals(nextName)) {
                n4 = nextInt;
            }
            else if ("hourOfDay".equals(nextName)) {
                n3 = nextInt;
            }
            else if ("minute".equals(nextName)) {
                n2 = nextInt;
            }
            else {
                if (!"second".equals(nextName)) {
                    continue;
                }
                n = nextInt;
            }
        }
        jsonReader.endObject();
        return new GregorianCalendar(n6, n5, n4, n3, n2, n);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Calendar calendar) {
        if (calendar == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        jsonWriter.name("year");
        jsonWriter.value(calendar.get(1));
        jsonWriter.name("month");
        jsonWriter.value(calendar.get(2));
        jsonWriter.name("dayOfMonth");
        jsonWriter.value(calendar.get(5));
        jsonWriter.name("hourOfDay");
        jsonWriter.value(calendar.get(11));
        jsonWriter.name("minute");
        jsonWriter.value(calendar.get(12));
        jsonWriter.name("second");
        jsonWriter.value(calendar.get(13));
        jsonWriter.endObject();
    }
}
