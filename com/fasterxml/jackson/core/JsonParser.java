// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

import java.io.Closeable;

public abstract class JsonParser implements Closeable
{
    protected int _features;
    
    protected JsonParser() {
    }
    
    protected JsonParser(final int features) {
        this._features = features;
    }
    
    protected JsonParseException _constructError(final String s) {
        return new JsonParseException(this, s);
    }
    
    public abstract void clearCurrentToken();
    
    public boolean getBooleanValue() {
        final JsonToken currentToken = this.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return false;
        }
        throw new JsonParseException(this, String.format("Current token (%s) not of boolean type", currentToken));
    }
    
    public abstract JsonLocation getCurrentLocation();
    
    public abstract String getCurrentName();
    
    public abstract JsonToken getCurrentToken();
    
    public abstract double getDoubleValue();
    
    public abstract Object getEmbeddedObject();
    
    public abstract int getIntValue();
    
    public abstract long getLongValue();
    
    public boolean getValueAsBoolean() {
        return this.getValueAsBoolean(false);
    }
    
    public boolean getValueAsBoolean(final boolean b) {
        return b;
    }
    
    public double getValueAsDouble() {
        return this.getValueAsDouble(0.0);
    }
    
    public double getValueAsDouble(final double n) {
        return n;
    }
    
    public int getValueAsInt() {
        return this.getValueAsInt(0);
    }
    
    public int getValueAsInt(final int n) {
        return n;
    }
    
    public long getValueAsLong() {
        return this.getValueAsLong(0L);
    }
    
    public long getValueAsLong(final long n) {
        return n;
    }
    
    public String getValueAsString() {
        return this.getValueAsString(null);
    }
    
    public abstract String getValueAsString(final String p0);
    
    public boolean isEnabled(final JsonParser$Feature jsonParser$Feature) {
        return jsonParser$Feature.enabledIn(this._features);
    }
    
    public abstract JsonToken nextToken();
}
