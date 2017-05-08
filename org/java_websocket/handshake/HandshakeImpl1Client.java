// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.handshake;

public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder
{
    private String resourcedescriptor;
    
    @Override
    public String getResourceDescriptor() {
        return this.resourcedescriptor;
    }
    
    @Override
    public void setResourceDescriptor(final String resourcedescriptor) {
        this.resourcedescriptor = resourcedescriptor;
    }
}
