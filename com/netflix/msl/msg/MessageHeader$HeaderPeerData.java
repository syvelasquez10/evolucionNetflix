// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import java.util.Set;
import com.netflix.msl.tokens.MasterToken;

public class MessageHeader$HeaderPeerData
{
    public final MasterToken peerMasterToken;
    public final Set<ServiceToken> peerServiceTokens;
    public final UserIdToken peerUserIdToken;
    
    public MessageHeader$HeaderPeerData(final MasterToken peerMasterToken, final UserIdToken peerUserIdToken, final Set<ServiceToken> peerServiceTokens) {
        this.peerMasterToken = peerMasterToken;
        this.peerUserIdToken = peerUserIdToken;
        this.peerServiceTokens = peerServiceTokens;
    }
}
