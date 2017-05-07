// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;

public interface VoipAuthorizationTokensUpdater
{
    boolean refreshAuthorizationTokens();
    
    void removeUserAuthorizationTokens();
    
    void updateAuthorizationData(final VoipAuthorizationData p0);
}
