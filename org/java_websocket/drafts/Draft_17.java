// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.drafts;

import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ClientHandshake;

public class Draft_17 extends Draft_10
{
    @Override
    public Draft$HandshakeState acceptHandshakeAsServer(final ClientHandshake clientHandshake) {
        if (Draft_10.readVersion(clientHandshake) == 13) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft copyInstance() {
        return new Draft_17();
    }
    
    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(final ClientHandshakeBuilder clientHandshakeBuilder) {
        super.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        clientHandshakeBuilder.put("Sec-WebSocket-Version", "13");
        return clientHandshakeBuilder;
    }
}
