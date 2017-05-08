// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.tokens;

import com.netflix.android.org.json.JSONObject;
import javax.crypto.SecretKey;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.MslError;
import com.netflix.msl.util.MslContext;

public interface TokenFactory
{
    MslError acceptNonReplayableId(final MslContext p0, final MasterToken p1, final long p2);
    
    MasterToken createMasterToken(final MslContext p0, final EntityAuthenticationData p1, final SecretKey p2, final SecretKey p3, final JSONObject p4);
    
    MslUser createUser(final MslContext p0, final String p1);
    
    UserIdToken createUserIdToken(final MslContext p0, final MslUser p1, final MasterToken p2);
    
    MslError isMasterTokenRenewable(final MslContext p0, final MasterToken p1);
    
    MslError isMasterTokenRevoked(final MslContext p0, final MasterToken p1);
    
    MslError isUserIdTokenRevoked(final MslContext p0, final MasterToken p1, final UserIdToken p2);
    
    MasterToken renewMasterToken(final MslContext p0, final MasterToken p1, final SecretKey p2, final SecretKey p3, final JSONObject p4);
    
    UserIdToken renewUserIdToken(final MslContext p0, final UserIdToken p1, final MasterToken p2);
}
