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
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.tokens.UserIdToken;

public class SwitchProfileAuthenticationData extends UserAuthenticationData
{
    private static final String KEY_PROFILE_GUID = "profileguid";
    private static final String KEY_USER_ID_TOKEN = "useridtoken";
    private final String profileGuid;
    private final UserIdToken userIdToken;
    
    public SwitchProfileAuthenticationData(final UserIdToken userIdToken, final String profileGuid) {
        super(NetflixUserAuthenticationScheme.SWITCH_PROFILE);
        this.userIdToken = userIdToken;
        this.profileGuid = profileGuid;
    }
    
    public SwitchProfileAuthenticationData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        super(NetflixUserAuthenticationScheme.SWITCH_PROFILE);
        if (masterToken == null) {
            throw new MslUserAuthException(MslError.USERAUTH_MASTERTOKEN_MISSING, "switch profile authdata " + jsonObject.toString());
        }
        try {
            this.userIdToken = new UserIdToken(mslContext, jsonObject.getJSONObject("useridtoken"), masterToken);
            this.profileGuid = jsonObject.getString("profileguid");
            if (!this.userIdToken.isDecrypted()) {
                throw new MslUserAuthException(MslError.USERAUTH_USERIDTOKEN_NOT_DECRYPTED, "switch profile authdata " + jsonObject.toString());
            }
        }
        catch (MslException ex) {
            throw new MslUserAuthException(MslError.USERAUTH_USERIDTOKEN_INVALID, "switch profile authdata " + jsonObject.toString(), ex);
        }
        catch (JSONException ex2) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "switch profile authdata " + jsonObject.toString(), ex2);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SwitchProfileAuthenticationData)) {
            return false;
        }
        final SwitchProfileAuthenticationData switchProfileAuthenticationData = (SwitchProfileAuthenticationData)o;
        return super.equals(o) && this.userIdToken.equals(switchProfileAuthenticationData.userIdToken) && this.profileGuid.equals(switchProfileAuthenticationData.profileGuid);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("useridtoken", new JSONObject(this.userIdToken.toJSONString()));
            jsonObject.put("profileguid", this.profileGuid);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "switch profile authdata", ex);
        }
    }
    
    public String getProfileGuid() {
        return this.profileGuid;
    }
    
    public UserIdToken getUserIdToken() {
        return this.userIdToken;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.userIdToken.hashCode() ^ this.profileGuid.hashCode();
    }
}
