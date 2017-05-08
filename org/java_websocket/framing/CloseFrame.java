// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.framing;

public interface CloseFrame extends Framedata
{
    int getCloseCode();
    
    String getMessage();
}
