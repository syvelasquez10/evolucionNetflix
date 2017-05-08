// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.handshake;

public class HandshakeImpl1Server extends HandshakedataImpl1 implements ServerHandshakeBuilder
{
    private short httpstatus;
    private String httpstatusmessage;
    
    @Override
    public String getHttpStatusMessage() {
        return this.httpstatusmessage;
    }
    
    @Override
    public void setHttpStatus(final short httpstatus) {
        this.httpstatus = httpstatus;
    }
    
    @Override
    public void setHttpStatusMessage(final String httpstatusmessage) {
        this.httpstatusmessage = httpstatusmessage;
    }
}
