// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

class ReflectiveTypeAdapterFactory$1 extends ReflectiveTypeAdapterFactory$BoundField
{
    final /* synthetic */ ReflectiveTypeAdapterFactory this$0;
    final TypeAdapter<?> typeAdapter;
    final /* synthetic */ Gson val$context;
    final /* synthetic */ Field val$field;
    final /* synthetic */ TypeToken val$fieldType;
    final /* synthetic */ boolean val$isPrimitive;
    
    ReflectiveTypeAdapterFactory$1(final ReflectiveTypeAdapterFactory this$0, final String s, final boolean b, final boolean b2, final Gson val$context, final TypeToken val$fieldType, final Field val$field, final boolean val$isPrimitive) {
        this.this$0 = this$0;
        this.val$context = val$context;
        this.val$fieldType = val$fieldType;
        this.val$field = val$field;
        this.val$isPrimitive = val$isPrimitive;
        super(s, b, b2);
        this.typeAdapter = this.val$context.getAdapter((TypeToken<?>)this.val$fieldType);
    }
    
    @Override
    void read(final JsonReader jsonReader, final Object o) {
        final Object read = this.typeAdapter.read(jsonReader);
        if (read != null || !this.val$isPrimitive) {
            this.val$field.set(o, read);
        }
    }
    
    @Override
    void write(final JsonWriter jsonWriter, Object value) {
        value = this.val$field.get(value);
        new TypeAdapterRuntimeTypeWrapper<Object>(this.val$context, this.typeAdapter, this.val$fieldType.getType()).write(jsonWriter, value);
    }
}
