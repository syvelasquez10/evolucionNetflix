// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONStringer;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslUserAuthException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;
import com.netflix.android.org.json.JSONString;

public abstract class UserAuthenticationData implements JSONString
{
    private static final String KEY_AUTHDATA = "authdata";
    private static final String KEY_SCHEME = "scheme";
    private final UserAuthenticationScheme scheme;
    
    protected UserAuthenticationData(final UserAuthenticationScheme scheme) {
        this.scheme = scheme;
    }
    
    public static UserAuthenticationData create(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        UserAuthenticationScheme userAuthenticationScheme;
        try {
            final String string = jsonObject.getString("scheme");
            userAuthenticationScheme = mslContext.getUserAuthenticationScheme(string);
            if (userAuthenticationScheme == null) {
                throw new MslUserAuthException(MslError.UNIDENTIFIED_USERAUTH_SCHEME, string);
            }
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "userauthdata " + jsonObject.toString(), ex);
        }
        final UserAuthenticationFactory userAuthenticationFactory = mslContext.getUserAuthenticationFactory(userAuthenticationScheme);
        if (userAuthenticationFactory == null) {
            throw new MslUserAuthException(MslError.USERAUTH_FACTORY_NOT_FOUND, userAuthenticationScheme.name());
        }
        return userAuthenticationFactory.createData(mslContext, masterToken, jsonObject.getJSONObject("authdata"));
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof UserAuthenticationData && this.scheme.equals(((UserAuthenticationData)o).scheme));
    }
    
    public abstract JSONObject getAuthData();
    
    public UserAuthenticationScheme getScheme() {
        return this.scheme;
    }
    
    @Override
    public int hashCode() {
        return this.scheme.hashCode();
    }
    
    @Override
    public final String toJSONString() {
        try {
            return new JSONStringer().object().key("scheme").value(this.scheme.name()).key("authdata").value(this.getAuthData()).endObject().toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
        catch (MslEncodingException ex2) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex2);
        }
    }
}
