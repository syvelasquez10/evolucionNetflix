// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

public enum JsonToken
{
    END_ARRAY("]", 4), 
    END_OBJECT("}", 2), 
    FIELD_NAME((String)null, 5), 
    NOT_AVAILABLE((String)null, -1), 
    START_ARRAY("[", 3), 
    START_OBJECT("{", 1), 
    VALUE_EMBEDDED_OBJECT((String)null, 12), 
    VALUE_FALSE("false", 10), 
    VALUE_NULL("null", 11), 
    VALUE_NUMBER_FLOAT((String)null, 8), 
    VALUE_NUMBER_INT((String)null, 7), 
    VALUE_STRING((String)null, 6), 
    VALUE_TRUE("true", 9);
    
    final int _id;
    final boolean _isBoolean;
    final boolean _isNumber;
    final boolean _isScalar;
    final boolean _isStructEnd;
    final boolean _isStructStart;
    final String _serialized;
    final byte[] _serializedBytes;
    final char[] _serializedChars;
    
    private JsonToken(final String serialized, final int id) {
        final boolean b = true;
        super(s, i);
        if (serialized == null) {
            this._serialized = null;
            this._serializedChars = null;
            this._serializedBytes = null;
        }
        else {
            this._serialized = serialized;
            this._serializedChars = serialized.toCharArray();
            final int length = this._serializedChars.length;
            this._serializedBytes = new byte[length];
            for (i = 0; i < length; ++i) {
                this._serializedBytes[i] = (byte)this._serializedChars[i];
            }
        }
        this._id = id;
        this._isBoolean = (id == 10 || id == 9);
        this._isNumber = (id == 7 || id == 8);
        this._isStructStart = (id == 1 || id == 3);
        this._isStructEnd = (id == 2 || id == 4);
        this._isScalar = (!this._isStructStart && !this._isStructEnd && id != 5 && id != -1 && b);
    }
    
    public final String asString() {
        return this._serialized;
    }
    
    public final int id() {
        return this._id;
    }
    
    public final boolean isScalarValue() {
        return this._isScalar;
    }
}
