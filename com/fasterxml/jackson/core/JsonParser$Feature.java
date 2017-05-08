// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

public enum JsonParser$Feature
{
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false), 
    ALLOW_COMMENTS(false), 
    ALLOW_NON_NUMERIC_NUMBERS(false), 
    ALLOW_NUMERIC_LEADING_ZEROS(false), 
    ALLOW_SINGLE_QUOTES(false), 
    ALLOW_UNQUOTED_CONTROL_CHARS(false), 
    ALLOW_UNQUOTED_FIELD_NAMES(false), 
    ALLOW_YAML_COMMENTS(false), 
    AUTO_CLOSE_SOURCE(true), 
    IGNORE_UNDEFINED(false), 
    STRICT_DUPLICATE_DETECTION(false);
    
    private final boolean _defaultState;
    private final int _mask;
    
    private JsonParser$Feature(final boolean defaultState) {
        this._mask = 1 << this.ordinal();
        this._defaultState = defaultState;
    }
    
    public static int collectDefaults() {
        int n = 0;
        final JsonParser$Feature[] values = values();
        int n2;
        for (int length = values.length, i = 0; i < length; ++i, n = n2) {
            final JsonParser$Feature jsonParser$Feature = values[i];
            n2 = n;
            if (jsonParser$Feature.enabledByDefault()) {
                n2 = (n | jsonParser$Feature.getMask());
            }
        }
        return n;
    }
    
    public boolean enabledByDefault() {
        return this._defaultState;
    }
    
    public boolean enabledIn(final int n) {
        return (this._mask & n) != 0x0;
    }
    
    public int getMask() {
        return this._mask;
    }
}
