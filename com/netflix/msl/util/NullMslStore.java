// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.util.Collections;
import com.netflix.msl.MslException;
import com.netflix.msl.MslError;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import java.util.Set;

public class NullMslStore implements MslStore
{
    @Override
    public void addServiceTokens(final Set<ServiceToken> set) {
    }
    
    @Override
    public void addUserIdToken(final String s, final UserIdToken userIdToken) {
    }
    
    @Override
    public void clearCryptoContexts() {
    }
    
    @Override
    public void clearServiceTokens() {
    }
    
    @Override
    public void clearUserIdTokens() {
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MasterToken masterToken) {
        return null;
    }
    
    @Override
    public MasterToken getMasterToken() {
        return null;
    }
    
    @Override
    public long getNonReplayableId(final MasterToken masterToken) {
        return 1L;
    }
    
    @Override
    public Set<ServiceToken> getServiceTokens(final MasterToken masterToken, final UserIdToken userIdToken) {
        if (userIdToken != null) {
            if (masterToken == null) {
                throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_NULL);
            }
            if (!userIdToken.isBoundTo(masterToken)) {
                throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber() + "; mt " + masterToken.getSerialNumber());
            }
        }
        return Collections.emptySet();
    }
    
    @Override
    public UserIdToken getUserIdToken(final String s) {
        return null;
    }
    
    @Override
    public void removeCryptoContext(final MasterToken masterToken) {
    }
    
    @Override
    public void removeServiceTokens(final String s, final MasterToken masterToken, final UserIdToken userIdToken) {
        if (userIdToken != null && masterToken != null && !userIdToken.isBoundTo(masterToken)) {
            throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber() + "; mt " + masterToken.getSerialNumber());
        }
    }
    
    @Override
    public void removeUserIdToken(final UserIdToken userIdToken) {
    }
    
    @Override
    public void setCryptoContext(final MasterToken masterToken, final ICryptoContext cryptoContext) {
    }
}
