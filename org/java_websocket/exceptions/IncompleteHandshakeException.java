// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.exceptions;

public class IncompleteHandshakeException extends RuntimeException
{
    private int newsize;
    
    public IncompleteHandshakeException() {
        this.newsize = 0;
    }
    
    public IncompleteHandshakeException(final int newsize) {
        this.newsize = newsize;
    }
    
    public int getPreferedSize() {
        return this.newsize;
    }
}
