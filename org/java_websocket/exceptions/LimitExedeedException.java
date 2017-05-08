// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.exceptions;

public class LimitExedeedException extends InvalidDataException
{
    public LimitExedeedException() {
        super(1009);
    }
    
    public LimitExedeedException(final String s) {
        super(1009, s);
    }
}
