// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.MslUserAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class UserIdTokenAuthenticationFactory extends UserAuthenticationFactory
{
    private final AuthenticationUtils authutils;
    
    public UserIdTokenAuthenticationFactory(final AuthenticationUtils authutils) {
        super(UserAuthenticationScheme.USER_ID_TOKEN);
        this.authutils = authutils;
    }
    
    @Override
    public MslUser authenticate(final MslContext mslContext, final String s, final UserAuthenticationData userAuthenticationData, final UserIdToken userIdToken) {
        if (!(userAuthenticationData instanceof UserIdTokenAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + userAuthenticationData.getClass().getName() + ".");
        }
        final UserIdTokenAuthenticationData userIdTokenAuthenticationData = (UserIdTokenAuthenticationData)userAuthenticationData;
        if (!this.authutils.isSchemePermitted(s, this.getScheme())) {
            throw new MslUserAuthException(MslError.USERAUTH_ENTITY_INCORRECT_DATA, "Authentication scheme " + this.getScheme() + " not permitted for entity " + s + ".").setUserAuthenticationData(userAuthenticationData);
        }
        final String identity = userIdTokenAuthenticationData.getMasterToken().getIdentity();
        if (identity == null) {
            throw new MslUserAuthException(MslError.USERAUTH_MASTERTOKEN_NOT_DECRYPTED).setUserAuthenticationData(userIdTokenAuthenticationData);
        }
        if (!s.equals(identity)) {
            throw new MslUserAuthException(MslError.USERAUTH_ENTITY_MISMATCH, "entity identity " + s + "; uad identity " + identity).setUserAuthenticationData(userIdTokenAuthenticationData);
        }
        final MslUser user = userIdTokenAuthenticationData.getUserIdToken().getUser();
        if (user == null) {
            throw new MslUserAuthException(MslError.USERAUTH_USERIDTOKEN_NOT_DECRYPTED).setUserAuthenticationData(userIdTokenAuthenticationData);
        }
        if (!this.authutils.isSchemePermitted(s, user, this.getScheme())) {
            throw new MslUserAuthException(MslError.USERAUTH_ENTITYUSER_INCORRECT_DATA, "Authentication scheme " + this.getScheme() + " not permitted for entity " + s + ".").setUserAuthenticationData(userAuthenticationData);
        }
        if (userIdToken != null) {
            final MslUser user2 = userIdToken.getUser();
            if (!user.equals(user2)) {
                throw new MslUserAuthException(MslError.USERIDTOKEN_USERAUTH_DATA_MISMATCH, "uad user " + user + "; uit user " + user2).setUserAuthenticationData(userIdTokenAuthenticationData);
            }
        }
        return user;
    }
    
    @Override
    public UserAuthenticationData createData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new UserIdTokenAuthenticationData(mslContext, jsonObject);
    }
}
