// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class EmailPasswordAuthenticationData extends UserAuthenticationData
{
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private final String email;
    private final String password;
    
    public EmailPasswordAuthenticationData(final JSONObject jsonObject) {
        super(UserAuthenticationScheme.EMAIL_PASSWORD);
        try {
            this.email = jsonObject.getString("email");
            this.password = jsonObject.getString("password");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "email/password authdata " + jsonObject.toString(), ex);
        }
    }
    
    public EmailPasswordAuthenticationData(final String email, final String password) {
        super(UserAuthenticationScheme.EMAIL_PASSWORD);
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EmailPasswordAuthenticationData)) {
            return false;
        }
        final EmailPasswordAuthenticationData emailPasswordAuthenticationData = (EmailPasswordAuthenticationData)o;
        return super.equals(o) && this.email.equals(emailPasswordAuthenticationData.email) && this.password.equals(emailPasswordAuthenticationData.password);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", this.email);
            jsonObject.put("password", this.password);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "email/password authdata", ex);
        }
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.email.hashCode() ^ this.password.hashCode();
    }
}
