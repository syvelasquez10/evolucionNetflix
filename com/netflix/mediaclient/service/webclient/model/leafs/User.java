// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;

public class User implements com.netflix.mediaclient.servicemgr.interface_.user.User
{
    private static final String FIELD_AGE_VERIFIED = "ageVerified";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_FIRST_NAME = "firstName";
    private static final String FIELD_LAST_NAME = "lastName";
    private static final String FIELD_USER_TOKEN = "userId";
    private static final String TAG = "User";
    public SubtitlePreference subtitleDefaults;
    public User$Summary summary;
    
    public User() {
        this.summary = new User$Summary(this);
    }
    
    public User(final String s) {
        while (true) {
            final SubtitlePreference subtitlePreference = null;
            this.summary = new User$Summary(this);
            while (true) {
                String string;
                try {
                    JSONObject jsonObject;
                    if (StringUtils.isEmpty(s)) {
                        jsonObject = new JSONObject();
                    }
                    else {
                        jsonObject = new JSONObject(s);
                    }
                    this.summary.userToken = JsonUtils.getString(jsonObject, "userId", null);
                    this.summary.email = JsonUtils.getString(jsonObject, "email", null);
                    this.summary.firstName = JsonUtils.getString(jsonObject, "firstName", null);
                    this.summary.lastName = JsonUtils.getString(jsonObject, "lastName", null);
                    this.summary.isAgeVerified = JsonUtils.getBoolean(jsonObject, "ageVerified", false);
                    string = JsonUtils.getString(jsonObject, "subtitleOverride", null);
                    if (StringUtils.isEmpty(string)) {
                        final SubtitlePreference subtitleDefaults = subtitlePreference;
                        this.subtitleDefaults = subtitleDefaults;
                        return;
                    }
                }
                catch (JSONException ex) {
                    Log.d("User", "failed to create json string=" + s + " exception =" + ex);
                    return;
                }
                final SubtitlePreference subtitleDefaults = new SubtitlePreference(string);
                continue;
            }
        }
    }
    
    @Override
    public String getEmail() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.email;
    }
    
    @Override
    public String getFirstName() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.firstName;
    }
    
    @Override
    public String getLastName() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.lastName;
    }
    
    public SubtitlePreference getSubtitleDefaults() {
        return this.subtitleDefaults;
    }
    
    @Override
    public String getUserToken() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.userToken;
    }
    
    @Override
    public boolean isAgeVerified() {
        return this.summary != null && this.summary.isAgeVerified;
    }
    
    @Override
    public String toString() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("userId", (Object)this.getUserToken());
                jsonObject.put("email", (Object)this.getEmail());
                jsonObject.put("firstName", (Object)this.getFirstName());
                jsonObject.put("lastName", (Object)this.getLastName());
                jsonObject.put("ageVerified", this.isAgeVerified());
                if (this.subtitleDefaults != null) {
                    jsonObject.put("subtitleOverride", (Object)this.subtitleDefaults.toString());
                }
                Log.d("User", "user string=" + jsonObject.toString());
                return jsonObject.toString();
            }
            catch (JSONException ex) {
                Log.d("User", "failed in json string " + ex);
                continue;
            }
            break;
        }
    }
}
