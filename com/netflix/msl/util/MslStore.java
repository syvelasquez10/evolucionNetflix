// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import java.util.Set;

public interface MslStore
{
    void addServiceTokens(final Set<ServiceToken> p0);
    
    void addUserIdToken(final String p0, final UserIdToken p1);
    
    void clearCryptoContexts();
    
    void clearServiceTokens();
    
    void clearUserIdTokens();
    
    ICryptoContext getCryptoContext(final MasterToken p0);
    
    MasterToken getMasterToken();
    
    long getNonReplayableId(final MasterToken p0);
    
    Set<ServiceToken> getServiceTokens(final MasterToken p0, final UserIdToken p1);
    
    UserIdToken getUserIdToken(final String p0);
    
    void removeCryptoContext(final MasterToken p0);
    
    void removeServiceTokens(final String p0, final MasterToken p1, final UserIdToken p2);
    
    void removeUserIdToken(final UserIdToken p0);
    
    void setCryptoContext(final MasterToken p0, final ICryptoContext p1);
}
