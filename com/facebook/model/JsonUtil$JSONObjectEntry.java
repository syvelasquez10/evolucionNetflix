// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import android.annotation.SuppressLint;
import java.util.Map;

final class JsonUtil$JSONObjectEntry implements Entry<String, Object>
{
    private final String key;
    private final Object value;
    
    JsonUtil$JSONObjectEntry(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }
    
    @SuppressLint({ "FieldGetter" })
    public String getKey() {
        return this.key;
    }
    
    @Override
    public Object getValue() {
        return this.value;
    }
    
    @Override
    public Object setValue(final Object o) {
        throw new UnsupportedOperationException("JSONObjectEntry is immutable");
    }
}
