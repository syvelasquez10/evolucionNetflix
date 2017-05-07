// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.Streams;
import java.io.StringWriter;
import java.util.Iterator;
import java.io.StringReader;
import java.io.Reader;
import java.io.EOFException;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.Primitives;
import java.io.Writer;
import java.io.IOException;
import com.google.gson.stream.MalformedJsonException;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.DateTypeAdapter;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Collection;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.Collections;
import com.google.gson.internal.Excluder;
import java.util.List;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;

class Gson$4 extends TypeAdapter<Number>
{
    final /* synthetic */ Gson this$0;
    
    Gson$4(final Gson this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Double read(final JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return jsonReader.nextDouble();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) {
        if (n == null) {
            jsonWriter.nullValue();
            return;
        }
        this.this$0.checkValidFloatingPoint(n.doubleValue());
        jsonWriter.value(n);
    }
}
