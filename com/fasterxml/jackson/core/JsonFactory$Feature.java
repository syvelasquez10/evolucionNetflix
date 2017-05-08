// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

public enum JsonFactory$Feature
{
    CANONICALIZE_FIELD_NAMES(true), 
    FAIL_ON_SYMBOL_HASH_OVERFLOW(true), 
    INTERN_FIELD_NAMES(true), 
    USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);
    
    private final boolean _defaultState;
    
    private JsonFactory$Feature(final boolean defaultState) {
        this._defaultState = defaultState;
    }
    
    public static int collectDefaults() {
        int n = 0;
        final JsonFactory$Feature[] values = values();
        int n2;
        for (int length = values.length, i = 0; i < length; ++i, n = n2) {
            final JsonFactory$Feature jsonFactory$Feature = values[i];
            n2 = n;
            if (jsonFactory$Feature.enabledByDefault()) {
                n2 = (n | jsonFactory$Feature.getMask());
            }
        }
        return n;
    }
    
    public boolean enabledByDefault() {
        return this._defaultState;
    }
    
    public boolean enabledIn(final int n) {
        return (this.getMask() & n) != 0x0;
    }
    
    public int getMask() {
        return 1 << this.ordinal();
    }
}
