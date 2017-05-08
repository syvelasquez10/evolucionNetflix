// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.msl.userauth.UserAuthenticationData;

public interface IMSLClient$MSLUserCredentialRegistry
{
    UserAuthenticationData getUserAuthenticationData();
    
    String getUserId();
    
    void updateApiEndpointHost(final String p0);
}
