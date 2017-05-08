// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslException;
import com.netflix.msl.MslUserAuthException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.MasterToken;

public class UserIdTokenAuthenticationData extends UserAuthenticationData
{
    private static final String KEY_MASTER_TOKEN = "mastertoken";
    private static final String KEY_USER_ID_TOKEN = "useridtoken";
    private final MasterToken masterToken;
    private final UserIdToken userIdToken;
    
    public UserIdTokenAuthenticationData(final MasterToken masterToken, final UserIdToken userIdToken) {
        super(UserAuthenticationScheme.USER_ID_TOKEN);
        if (!userIdToken.isBoundTo(masterToken)) {
            throw new MslInternalException("User ID token must be bound to master token.");
        }
        this.masterToken = masterToken;
        this.userIdToken = userIdToken;
    }
    
    public UserIdTokenAuthenticationData(final MslContext mslContext, final JSONObject jsonObject) {
        super(UserAuthenticationScheme.USER_ID_TOKEN);
        try {
            this.masterToken = new MasterToken(mslContext, jsonObject.getJSONObject("mastertoken"));
            final UserIdTokenAuthenticationData userIdTokenAuthenticationData = this;
            final MslContext mslContext2 = mslContext;
            final JSONObject jsonObject2 = jsonObject;
            final String s = "useridtoken";
            final JSONObject jsonObject3 = jsonObject2.getJSONObject(s);
            final UserIdTokenAuthenticationData userIdTokenAuthenticationData2 = this;
            final MasterToken masterToken = userIdTokenAuthenticationData2.masterToken;
            final UserIdToken userIdToken = new UserIdToken(mslContext2, jsonObject3, masterToken);
            userIdTokenAuthenticationData.userIdToken = userIdToken;
            return;
        }
        catch (MslException ex) {
            throw new MslUserAuthException(MslError.USERAUTH_MASTERTOKEN_INVALID, "user ID token authdata " + jsonObject.toString(), ex);
        }
        catch (JSONException ex2) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "user ID token authdata " + jsonObject.toString(), ex2);
        }
        try {
            final UserIdTokenAuthenticationData userIdTokenAuthenticationData = this;
            final MslContext mslContext2 = mslContext;
            final JSONObject jsonObject2 = jsonObject;
            final String s = "useridtoken";
            final JSONObject jsonObject3 = jsonObject2.getJSONObject(s);
            final UserIdTokenAuthenticationData userIdTokenAuthenticationData2 = this;
            final MasterToken masterToken = userIdTokenAuthenticationData2.masterToken;
            final UserIdToken userIdToken = new UserIdToken(mslContext2, jsonObject3, masterToken);
            userIdTokenAuthenticationData.userIdToken = userIdToken;
        }
        catch (MslException ex3) {
            throw new MslUserAuthException(MslError.USERAUTH_USERIDTOKEN_INVALID, "user ID token authdata " + jsonObject.toString(), ex3);
        }
        catch (JSONException ex4) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "user ID token authdata " + jsonObject.toString(), ex4);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserIdTokenAuthenticationData)) {
            return false;
        }
        final UserIdTokenAuthenticationData userIdTokenAuthenticationData = (UserIdTokenAuthenticationData)o;
        return super.equals(o) && this.masterToken.equals(userIdTokenAuthenticationData.masterToken) && this.userIdToken.equals(userIdTokenAuthenticationData.userIdToken);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("mastertoken", new JSONObject(this.masterToken.toJSONString()));
            jsonObject.put("useridtoken", new JSONObject(this.userIdToken.toJSONString()));
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "user ID token authdata", ex);
        }
    }
    
    public MasterToken getMasterToken() {
        return this.masterToken;
    }
    
    public UserIdToken getUserIdToken() {
        return this.userIdToken;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.masterToken.hashCode() ^ this.userIdToken.hashCode();
    }
}
