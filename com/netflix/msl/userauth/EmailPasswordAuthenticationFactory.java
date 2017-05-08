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

public class EmailPasswordAuthenticationFactory extends UserAuthenticationFactory
{
    private final AuthenticationUtils authutils;
    private final EmailPasswordStore store;
    
    public EmailPasswordAuthenticationFactory(final EmailPasswordStore store, final AuthenticationUtils authutils) {
        super(UserAuthenticationScheme.EMAIL_PASSWORD);
        this.store = store;
        this.authutils = authutils;
    }
    
    @Override
    public MslUser authenticate(final MslContext mslContext, final String s, final UserAuthenticationData userAuthenticationData, final UserIdToken userIdToken) {
        if (!(userAuthenticationData instanceof EmailPasswordAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + userAuthenticationData.getClass().getName() + ".");
        }
        final EmailPasswordAuthenticationData userAuthenticationData2 = (EmailPasswordAuthenticationData)userAuthenticationData;
        if (!this.authutils.isSchemePermitted(s, this.getScheme())) {
            throw new MslUserAuthException(MslError.USERAUTH_ENTITY_INCORRECT_DATA, "Authentication scheme " + this.getScheme() + " not permitted for entity " + s + ".").setUserAuthenticationData(userAuthenticationData);
        }
        final String email = userAuthenticationData2.getEmail();
        final String password = userAuthenticationData2.getPassword();
        if (email == null || password == null) {
            throw new MslUserAuthException(MslError.EMAILPASSWORD_BLANK).setUserAuthenticationData(userAuthenticationData2);
        }
        final String trim = email.trim();
        final String trim2 = password.trim();
        if (trim.isEmpty() || trim2.isEmpty()) {
            throw new MslUserAuthException(MslError.EMAILPASSWORD_BLANK).setUserAuthenticationData(userAuthenticationData2);
        }
        final MslUser user = this.store.isUser(trim, trim2);
        if (user == null) {
            throw new MslUserAuthException(MslError.EMAILPASSWORD_INCORRECT).setUserAuthenticationData(userAuthenticationData2);
        }
        if (!this.authutils.isSchemePermitted(s, user, this.getScheme())) {
            throw new MslUserAuthException(MslError.USERAUTH_ENTITYUSER_INCORRECT_DATA, "Authentication scheme " + this.getScheme() + " not permitted for entity " + s + ".").setUserAuthenticationData(userAuthenticationData);
        }
        if (userIdToken != null) {
            final MslUser user2 = userIdToken.getUser();
            if (!user.equals(user2)) {
                throw new MslUserAuthException(MslError.USERIDTOKEN_USERAUTH_DATA_MISMATCH, "uad user " + user + "; uit user " + user2);
            }
        }
        return user;
    }
    
    @Override
    public UserAuthenticationData createData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new EmailPasswordAuthenticationData(jsonObject);
    }
}
