// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.exceptions;

public class InvalidDataException extends Exception
{
    private int closecode;
    
    public InvalidDataException(final int closecode) {
        this.closecode = closecode;
    }
    
    public InvalidDataException(final int closecode, final String s) {
        super(s);
        this.closecode = closecode;
    }
    
    public InvalidDataException(final int closecode, final Throwable t) {
        super(t);
        this.closecode = closecode;
    }
    
    public int getCloseCode() {
        return this.closecode;
    }
}
