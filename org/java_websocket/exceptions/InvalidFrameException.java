// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.exceptions;

public class InvalidFrameException extends InvalidDataException
{
    public InvalidFrameException() {
        super(1002);
    }
    
    public InvalidFrameException(final String s) {
        super(1002, s);
    }
    
    public InvalidFrameException(final Throwable t) {
        super(1002, t);
    }
}
