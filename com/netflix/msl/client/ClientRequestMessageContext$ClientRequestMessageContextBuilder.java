// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import java.util.Arrays;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.msg.MessageDebugContext;

public class ClientRequestMessageContext$ClientRequestMessageContextBuilder
{
    private MessageDebugContext debugContext;
    private Boolean encrypted;
    private Boolean integrityProtected;
    private KeyRequestDataProvider keyRequestDataProvider;
    private Boolean nonReplayable;
    private byte[] payload;
    private String recipient;
    private UserAuthenticationData userAuthData;
    private String userId;
    
    public ClientRequestMessageContext build() {
        return new ClientRequestMessageContext(this.encrypted, this.integrityProtected, this.nonReplayable, this.userId, this.debugContext, this.userAuthData, this.payload, this.recipient, this.keyRequestDataProvider, null);
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder debugContext(final MessageDebugContext debugContext) {
        this.debugContext = debugContext;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder encrypted(final Boolean encrypted) {
        this.encrypted = encrypted;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder integrityProtected(final Boolean integrityProtected) {
        this.integrityProtected = integrityProtected;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder keyRequestDataProvider(final KeyRequestDataProvider keyRequestDataProvider) {
        this.keyRequestDataProvider = keyRequestDataProvider;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder nonReplayable(final Boolean nonReplayable) {
        this.nonReplayable = nonReplayable;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder payload(final byte[] payload) {
        this.payload = payload;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder recipient(final String recipient) {
        this.recipient = recipient;
        return this;
    }
    
    @Override
    public String toString() {
        return "ClientRequestMessageContext.ClientRequestMessageContextBuilder(encrypted=" + this.encrypted + ", integrityProtected=" + this.integrityProtected + ", nonReplayable=" + this.nonReplayable + ", userId=" + this.userId + ", debugContext=" + this.debugContext + ", userAuthData=" + this.userAuthData + ", payload=" + Arrays.toString(this.payload) + ", recipient=" + this.recipient + ", keyRequestDataProvider=" + this.keyRequestDataProvider + ")";
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder userAuthData(final UserAuthenticationData userAuthData) {
        this.userAuthData = userAuthData;
        return this;
    }
    
    public ClientRequestMessageContext$ClientRequestMessageContextBuilder userId(final String userId) {
        this.userId = userId;
        return this;
    }
}
