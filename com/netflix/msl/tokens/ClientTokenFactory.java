// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.tokens;

import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONObject;
import javax.crypto.SecretKey;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.MslError;
import com.netflix.msl.util.MslContext;

public class ClientTokenFactory implements TokenFactory
{
    @Override
    public MslError acceptNonReplayableId(final MslContext mslContext, final MasterToken masterToken, final long n) {
        return null;
    }
    
    @Override
    public MasterToken createMasterToken(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData, final SecretKey secretKey, final SecretKey secretKey2, final JSONObject jsonObject) {
        throw new MslInternalException("Creating master tokens is unsupported by the token factory.");
    }
    
    @Override
    public MslUser createUser(final MslContext mslContext, final String s) {
        throw new MslInternalException("Creating users is unsupported by the token factory.");
    }
    
    @Override
    public UserIdToken createUserIdToken(final MslContext mslContext, final MslUser mslUser, final MasterToken masterToken) {
        throw new MslInternalException("Creating user ID tokens is unsupported by the token factory.");
    }
    
    @Override
    public MslError isMasterTokenRenewable(final MslContext mslContext, final MasterToken masterToken) {
        return null;
    }
    
    @Override
    public MslError isMasterTokenRevoked(final MslContext mslContext, final MasterToken masterToken) {
        return null;
    }
    
    @Override
    public MslError isUserIdTokenRevoked(final MslContext mslContext, final MasterToken masterToken, final UserIdToken userIdToken) {
        return null;
    }
    
    @Override
    public MasterToken renewMasterToken(final MslContext mslContext, final MasterToken masterToken, final SecretKey secretKey, final SecretKey secretKey2, final JSONObject jsonObject) {
        throw new MslInternalException("Renewing master tokens is unsupported by the token factory.");
    }
    
    @Override
    public UserIdToken renewUserIdToken(final MslContext mslContext, final UserIdToken userIdToken, final MasterToken masterToken) {
        throw new MslInternalException("Renewing master tokens is unsupported by the token factory.");
    }
}
