// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;

public class User implements com.netflix.mediaclient.servicemgr.User
{
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_FIRST_NAME = "firstName";
    private static final String FIELD_ID = "userId";
    private static final String FIELD_LAST_NAME = "lastName";
    private static final String TAG = "User";
    public SubtitlePreference subtitleDefaults;
    public Summary summary;
    
    public User() {
        this.summary = new Summary();
    }
    
    public User(final String s) {
        while (true) {
            final SubtitlePreference subtitlePreference = null;
            this.summary = new Summary();
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
                    this.summary.userId = JsonUtils.getString(jsonObject, "userId", null);
                    this.summary.email = JsonUtils.getString(jsonObject, "email", null);
                    this.summary.firstName = JsonUtils.getString(jsonObject, "firstName", null);
                    this.summary.lastName = JsonUtils.getString(jsonObject, "lastName", null);
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
    public String getUserId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.userId;
    }
    
    @Override
    public String toString() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("userId", (Object)this.getUserId());
                jsonObject.put("email", (Object)this.getEmail());
                jsonObject.put("firstName", (Object)this.getFirstName());
                jsonObject.put("lastName", (Object)this.getLastName());
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
    
    public class Summary
    {
        private String email;
        private String firstName;
        private String lastName;
        private String userId;
    }
}
