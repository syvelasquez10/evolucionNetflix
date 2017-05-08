// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

public enum JsonGenerator$Feature
{
    AUTO_CLOSE_JSON_CONTENT(true), 
    AUTO_CLOSE_TARGET(true), 
    ESCAPE_NON_ASCII(false), 
    FLUSH_PASSED_TO_STREAM(true), 
    IGNORE_UNKNOWN(false), 
    QUOTE_FIELD_NAMES(true), 
    QUOTE_NON_NUMERIC_NUMBERS(true), 
    STRICT_DUPLICATE_DETECTION(false), 
    WRITE_BIGDECIMAL_AS_PLAIN(false), 
    WRITE_NUMBERS_AS_STRINGS(false);
    
    private final boolean _defaultState;
    private final int _mask;
    
    private JsonGenerator$Feature(final boolean defaultState) {
        this._defaultState = defaultState;
        this._mask = 1 << this.ordinal();
    }
    
    public static int collectDefaults() {
        int n = 0;
        final JsonGenerator$Feature[] values = values();
        int n2;
        for (int length = values.length, i = 0; i < length; ++i, n = n2) {
            final JsonGenerator$Feature jsonGenerator$Feature = values[i];
            n2 = n;
            if (jsonGenerator$Feature.enabledByDefault()) {
                n2 = (n | jsonGenerator$Feature.getMask());
            }
        }
        return n;
    }
    
    public boolean enabledByDefault() {
        return this._defaultState;
    }
    
    public int getMask() {
        return this._mask;
    }
}
