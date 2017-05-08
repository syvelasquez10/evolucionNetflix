// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.io;

import java.io.Serializable;
import com.fasterxml.jackson.core.SerializableString;

public class SerializedString implements SerializableString, Serializable
{
    protected final String _value;
    
    public SerializedString(final String value) {
        if (value == null) {
            throw new IllegalStateException("Null String illegal for SerializedString");
        }
        this._value = value;
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o == this || (o != null && o.getClass() == this.getClass() && this._value.equals(((SerializedString)o)._value));
    }
    
    @Override
    public final int hashCode() {
        return this._value.hashCode();
    }
    
    @Override
    public final String toString() {
        return this._value;
    }
}
