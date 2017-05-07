// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;

class Gson$FutureTypeAdapter<T> extends TypeAdapter<T>
{
    private TypeAdapter<T> delegate;
    
    @Override
    public T read(final JsonReader jsonReader) {
        if (this.delegate == null) {
            throw new IllegalStateException();
        }
        return this.delegate.read(jsonReader);
    }
    
    public void setDelegate(final TypeAdapter<T> delegate) {
        if (this.delegate != null) {
            throw new AssertionError();
        }
        this.delegate = delegate;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) {
        if (this.delegate == null) {
            throw new IllegalStateException();
        }
        this.delegate.write(jsonWriter, t);
    }
}
