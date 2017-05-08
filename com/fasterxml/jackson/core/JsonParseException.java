// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

public class JsonParseException extends JsonProcessingException
{
    protected JsonParser _processor;
    
    public JsonParseException(final JsonParser processor, final String s) {
        JsonLocation currentLocation;
        if (processor == null) {
            currentLocation = null;
        }
        else {
            currentLocation = processor.getCurrentLocation();
        }
        super(s, currentLocation);
        this._processor = processor;
    }
    
    public JsonParseException(final JsonParser processor, final String s, final Throwable t) {
        JsonLocation currentLocation;
        if (processor == null) {
            currentLocation = null;
        }
        else {
            currentLocation = processor.getCurrentLocation();
        }
        super(s, currentLocation, t);
        this._processor = processor;
    }
}
