// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;

public interface MessageContext
{
    Map<String, ICryptoContext> getCryptoContexts();
    
    MessageDebugContext getDebugContext();
    
    Set<KeyRequestData> getKeyRequestData();
    
    String getRecipient();
    
    MslUser getUser();
    
    UserAuthenticationData getUserAuthData(final MessageContext$ReauthCode p0, final boolean p1, final boolean p2);
    
    String getUserId();
    
    boolean isEncrypted();
    
    boolean isIntegrityProtected();
    
    boolean isNonReplayable();
    
    boolean isRequestingTokens();
    
    void updateServiceTokens(final MessageServiceTokenBuilder p0, final boolean p1);
    
    void write(final MessageOutputStream p0);
}
