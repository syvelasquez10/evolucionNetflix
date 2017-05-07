// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import java.text.ParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import com.google.gson.TypeAdapterFactory;
import java.sql.Date;
import com.google.gson.TypeAdapter;

public final class SqlDateTypeAdapter extends TypeAdapter<Date>
{
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;
    
    static {
        FACTORY = new SqlDateTypeAdapter$1();
    }
    
    public SqlDateTypeAdapter() {
        this.format = new SimpleDateFormat("MMM d, yyyy");
    }
    
    @Override
    public Date read(final JsonReader jsonReader) {
        synchronized (this) {
            Date date;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                date = null;
            }
            else {
                try {
                    date = new Date(this.format.parse(jsonReader.nextString()).getTime());
                }
                catch (ParseException ex) {
                    throw new JsonSyntaxException(ex);
                }
            }
            return date;
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Date date) {
        // monitorenter(this)
        Label_0017: {
            if (date != null) {
                break Label_0017;
            }
            String format = null;
            try {
                while (true) {
                    jsonWriter.value(format);
                    return;
                    format = this.format.format(date);
                    continue;
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
}
