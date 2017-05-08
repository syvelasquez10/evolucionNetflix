// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

import java.io.IOException;

public class JsonProcessingException extends IOException
{
    protected JsonLocation _location;
    
    protected JsonProcessingException(final String s, final JsonLocation jsonLocation) {
        this(s, jsonLocation, null);
    }
    
    protected JsonProcessingException(final String s, final JsonLocation location, final Throwable t) {
        super(s);
        if (t != null) {
            this.initCause(t);
        }
        this._location = location;
    }
    
    public JsonLocation getLocation() {
        return this._location;
    }
    
    @Override
    public String getMessage() {
        String message;
        if ((message = super.getMessage()) == null) {
            message = "N/A";
        }
        final JsonLocation location = this.getLocation();
        final String messageSuffix = this.getMessageSuffix();
        if (location == null) {
            final String string = message;
            if (messageSuffix == null) {
                return string;
            }
        }
        final StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (messageSuffix != null) {
            sb.append(messageSuffix);
        }
        if (location != null) {
            sb.append('\n');
            sb.append(" at ");
            sb.append(location.toString());
        }
        return sb.toString();
    }
    
    protected String getMessageSuffix() {
        return null;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.getMessage();
    }
}
