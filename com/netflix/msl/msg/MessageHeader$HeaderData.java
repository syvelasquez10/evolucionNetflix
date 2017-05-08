// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.ServiceToken;
import com.netflix.msl.keyx.KeyResponseData;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;

public class MessageHeader$HeaderData
{
    public final MessageCapabilities capabilities;
    public final boolean handshake;
    public final Set<KeyRequestData> keyRequestData;
    public final KeyResponseData keyResponseData;
    public final long messageId;
    public final Long nonReplayableId;
    public final String recipient;
    public final boolean renewable;
    public final Set<ServiceToken> serviceTokens;
    public final UserAuthenticationData userAuthData;
    public final UserIdToken userIdToken;
    
    public MessageHeader$HeaderData(final String recipient, final long messageId, final Long nonReplayableId, final boolean renewable, final boolean handshake, final MessageCapabilities capabilities, final Set<KeyRequestData> keyRequestData, final KeyResponseData keyResponseData, final UserAuthenticationData userAuthData, final UserIdToken userIdToken, final Set<ServiceToken> serviceTokens) {
        this.recipient = recipient;
        this.messageId = messageId;
        this.nonReplayableId = nonReplayableId;
        this.renewable = renewable;
        this.handshake = handshake;
        this.capabilities = capabilities;
        this.keyRequestData = keyRequestData;
        this.keyResponseData = keyResponseData;
        this.userAuthData = userAuthData;
        this.userIdToken = userIdToken;
        this.serviceTokens = serviceTokens;
    }
}
