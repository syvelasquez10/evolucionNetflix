// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.framing;

import java.nio.ByteBuffer;

public interface FrameBuilder extends Framedata
{
    void setFin(final boolean p0);
    
    void setOptcode(final Framedata$Opcode p0);
    
    void setPayload(final ByteBuffer p0);
    
    void setTransferemasked(final boolean p0);
}
