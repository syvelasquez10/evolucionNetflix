// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.MasterToken;

public class IMSLClient$RenewUserAuthenticationData
{
    public MasterToken masterToken;
    public String userId;
    public UserIdToken userIdToken;
    
    public IMSLClient$RenewUserAuthenticationData(final String userId, final MasterToken masterToken, final UserIdToken userIdToken) {
        this.userId = userId;
        this.masterToken = masterToken;
        this.userIdToken = userIdToken;
    }
}
