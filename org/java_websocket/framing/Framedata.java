// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.framing;

import java.nio.ByteBuffer;

public interface Framedata
{
    Framedata$Opcode getOpcode();
    
    ByteBuffer getPayloadData();
    
    boolean getTransfereMasked();
    
    boolean isFin();
}
