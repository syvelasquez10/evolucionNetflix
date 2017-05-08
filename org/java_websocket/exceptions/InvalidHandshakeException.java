// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.exceptions;

public class InvalidHandshakeException extends InvalidDataException
{
    public InvalidHandshakeException() {
        super(1002);
    }
    
    public InvalidHandshakeException(final String s) {
        super(1002, s);
    }
}
