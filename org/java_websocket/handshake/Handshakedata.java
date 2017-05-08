// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.handshake;

import java.util.Iterator;

public interface Handshakedata
{
    byte[] getContent();
    
    String getFieldValue(final String p0);
    
    boolean hasFieldValue(final String p0);
    
    Iterator<String> iterateHttpFields();
}
